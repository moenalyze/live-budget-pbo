package helpers;

import java.sql.*;
import java.util.*;
import models.Aset;
import models.Transaksi;
import models.User;
import views.TransaksiView;

public class TransaksiHelper {
    private final String dbUrl = "jdbc:mysql://localhost/livebudget";
    private final String username = "root";
    private final String password = "";
    
    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private String query;
    private String deleteTransaksi = "DELETE FROM transaksi WHERE id = ? AND id_user = ?";
    private String updateTransaksi = "UPDATE transaksi SET jenis = ?, jumlah = ?, deskripsi = ? WHERE id = ? AND id_user = ?";
    
    public TransaksiHelper() {
        try {
            conn = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    // read data 
    public List<Transaksi> getAllData() {
        List<Transaksi> data = new ArrayList<>();
        query = "SELECT t.id, t.jenis, t.jumlah, t.tanggal, t.deskripsi, a.sumber"
                + " FROM transaksi t"
                + " JOIN aset_keuangan a ON t.id_aset = a.id"
                + " WHERE t.id_user = '" + User.getId() + "'";
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            
            while(rs.next()) {
                Transaksi transaksi = new Transaksi();
                transaksi.setId(rs.getInt("id"));
                transaksi.setType(rs.getString("jenis"));
                transaksi.setAmount(rs.getInt("jumlah"));
                transaksi.setDate(rs.getString("tanggal"));
                transaksi.setDescription(rs.getString("deskripsi"));
                transaksi.setSource(rs.getString("sumber"));
                
                data.add(transaksi);
            }
            
            stmt.close();
        } catch (SQLException e) {
        }
        
        return data; 
    }
    
    // write data
    public boolean addNewTransaksi(String type, int amount, String description, int id_aset) {
        boolean value = false;
        int id_user = User.getId();

        String query = "INSERT INTO transaksi (jenis, jumlah, deskripsi, tanggal, id_user, id_aset) VALUES (?, ?, ?, CURRENT_DATE, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, type);
            pstmt.setInt(2, amount);
            pstmt.setString(3, description);
            pstmt.setInt(4, id_user);
            pstmt.setInt(5, id_aset);

            if (pstmt.executeUpdate() > 0) {
                value = true;
            }
            
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return value;
    }
    
    public void deleteTransaksi(int id, int id_user) {
        try {
            // 1. Ambil data transaksi sebelum dihapus
            String jenis = "";
            int jumlah = 0;
            int id_aset = 0;

            PreparedStatement selectStmt = conn.prepareStatement(
                "SELECT jenis, jumlah, id_aset FROM transaksi WHERE id = ? AND id_user = ?"
            );
            selectStmt.setInt(1, id);
            selectStmt.setInt(2, id_user);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                jenis = rs.getString("jenis");
                jumlah = rs.getInt("jumlah");
                id_aset = rs.getInt("id_aset");
            } else {
                rs.close();
                selectStmt.close();
                return; // Transaksi tidak ditemukan
            }
            rs.close();
            selectStmt.close();

            // 2. Hitung dampak pada saldo
            int selisih = 0;
            if (jenis.equals("Pemasukan")) {
                selisih = -jumlah;
            } else if (jenis.equals("Pengeluaran")) {
                selisih = jumlah;
            }

            // 3. Update saldo aset_keuangan berdasarkan id_aset
            PreparedStatement updateSaldo = conn.prepareStatement(
                "UPDATE aset_keuangan SET saldo = saldo + ? WHERE id = ? AND id_user = ?"
            );
            updateSaldo.setInt(1, selisih);
            updateSaldo.setInt(2, id_aset);
            updateSaldo.setInt(3, id_user);
            updateSaldo.executeUpdate();
            updateSaldo.close();

            // 4. Hapus transaksi
            pstmt = conn.prepareStatement(deleteTransaksi);
            pstmt.setInt(1, id);
            pstmt.setInt(2, id_user);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public boolean updateTransaksi(String type, int amount, String description, String newSource) {
        boolean value = false;

        int oldAmount = 0;
        String oldSource = "";

        try {
            // Ambil data transaksi lama
            PreparedStatement oldStmt = conn.prepareStatement(
                "SELECT transaksi.jumlah, aset_keuangan.sumber " +
                "FROM transaksi JOIN aset_keuangan ON transaksi.id_aset = aset_keuangan.id " +
                "WHERE transaksi.id = ? AND transaksi.id_user = ?"
            );
            oldStmt.setInt(1, TransaksiView.selectedTransaksiId);
            oldStmt.setInt(2, User.getId());
            ResultSet oldRs = oldStmt.executeQuery();

            if (oldRs.next()) {
                oldAmount = oldRs.getInt("jumlah");
                oldSource = oldRs.getString("sumber");
            }

            oldRs.close();
            oldStmt.close();

            // Update transaksi
            PreparedStatement pstmt = conn.prepareStatement(updateTransaksi);
            pstmt.setString(1, type); // harus "Pengeluaran"
            pstmt.setInt(2, amount);
            pstmt.setString(3, description);
            pstmt.setInt(4, TransaksiView.selectedTransaksiId);
            pstmt.setInt(5, User.getId());
            int rows = pstmt.executeUpdate();
            pstmt.close();

            if (rows > 0) {
                if (!oldSource.equals(newSource)) {
                    // Sumber berubah
                    // Tambahkan kembali ke saldo sumber lama
                    PreparedStatement kembalikanKeSumberLama = conn.prepareStatement(
                        "UPDATE aset_keuangan SET saldo = saldo + ? " +
                        "WHERE sumber = ? AND id_user = ?"
                    );
                    kembalikanKeSumberLama.setInt(1, oldAmount);
                    kembalikanKeSumberLama.setString(2, oldSource);
                    kembalikanKeSumberLama.setInt(3, User.getId());
                    kembalikanKeSumberLama.executeUpdate();
                    kembalikanKeSumberLama.close();

                    // Kurangi saldo dari sumber baru
                    PreparedStatement kurangiDariSumberBaru = conn.prepareStatement(
                        "UPDATE aset_keuangan SET saldo = saldo - ? " +
                        "WHERE sumber = ? AND id_user = ?"
                    );
                    kurangiDariSumberBaru.setInt(1, amount);
                    kurangiDariSumberBaru.setString(2, newSource);
                    kurangiDariSumberBaru.setInt(3, User.getId());
                    kurangiDariSumberBaru.executeUpdate();
                    kurangiDariSumberBaru.close();
                    
                    // Ambil id_aset baru berdasarkan sumber baru
                    int newAsetId = -1;
                    PreparedStatement getNewAsetId = conn.prepareStatement(
                        "SELECT id FROM aset_keuangan WHERE sumber = ? AND id_user = ?"
                    );
                    getNewAsetId.setString(1, newSource);
                    getNewAsetId.setInt(2, User.getId());
                    ResultSet asetRs = getNewAsetId.executeQuery();

                    if (asetRs.next()) {
                        newAsetId = asetRs.getInt("id");
                    }
                    asetRs.close();
                    getNewAsetId.close();

                    if (newAsetId != -1) {
                        // Update id_aset pada transaksi ke aset yang baru
                        PreparedStatement updateAsetId = conn.prepareStatement(
                            "UPDATE transaksi SET id_aset = ? WHERE id = ? AND id_user = ?"
                        );
                        updateAsetId.setInt(1, newAsetId);
                        updateAsetId.setInt(2, TransaksiView.selectedTransaksiId);
                        updateAsetId.setInt(3, User.getId());
                        updateAsetId.executeUpdate();
                        updateAsetId.close();
                    }

                } else {
                    // Sumber tetap, cukup sesuaikan saldo berdasarkan selisih
                    int selisih = oldAmount - amount;
                    if (selisih != 0) {
                        PreparedStatement asetStmt = conn.prepareStatement(
                            "UPDATE aset_keuangan SET saldo = saldo + ? " +
                            "WHERE sumber = ? AND id_user = ?"
                        );
                        asetStmt.setInt(1, selisih);
                        asetStmt.setString(2, oldSource);
                        asetStmt.setInt(3, User.getId());
                        asetStmt.executeUpdate();
                        asetStmt.close();
                    }
                }
                value = true;
                TransaksiView.selectedTransaksiId = 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return value;
    }

}
