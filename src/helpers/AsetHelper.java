package helpers;

import controllers.AsetController;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import models.Aset;
import models.User;

public class AsetHelper {
    private final String dbUrl = "jdbc:mysql://localhost/livebudget";
    private final String username = "root";
    private final String password = "";
    
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;
    private String getAll = "SELECT id, jenis, sumber, saldo, catatan FROM aset_keuangan WHERE id_user = ?";
    private String deleteAset = "DELETE FROM aset_keuangan WHERE id = ? AND id_user = ?";
    private String getAsetId = "SELECT id from aset_keuangan WHERE sumber = ? AND id_user = ?";
    private String updateSaldo = "UPDATE aset_keuangan SET saldo = ? where id = ? AND id_user = ?";
    private String getSaldo = "SELECT saldo FROM aset_keuangan WHERE id = ? AND id_user = ?";
    
    public AsetHelper() {
        try {
            conn = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    // Read All Data
    public List<Aset> getAllData() {
        List<Aset> data = new ArrayList<>();
        
        try {
            stmt = conn.prepareStatement(getAll, stmt.RETURN_GENERATED_KEYS);
            stmt.setInt(1, User.getId());
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Aset aset = new Aset();
                aset.setId(rs.getInt("id"));
                aset.setType(rs.getString("jenis"));
                aset.setSource(rs.getString("sumber"));
                aset.setAmount(rs.getInt("saldo"));
                aset.setNotes(rs.getString("catatan"));
                data.add(aset);
            }
            stmt.close();
        } catch (SQLException e) {
        }
        return data; 
    }
    
    // Delete aset
    // Rencananya kalau klik kanan di tabel aset bakal ada opsi hapus data
    public void deleteAset(int id, int id_user) {
        try {
            // 2. Hapus transaksi terkait (opsional: soft delete)
            PreparedStatement deleteTransaksiStmt = conn.prepareStatement(
                "DELETE FROM transaksi WHERE id_aset = ? AND id_user = ?"
            );
            deleteTransaksiStmt.setInt(1, id);
            deleteTransaksiStmt.setInt(2, id_user);
            deleteTransaksiStmt.executeUpdate();
            deleteTransaksiStmt.close();

            // 3. Hapus aset
            stmt = conn.prepareStatement(deleteAset);
            stmt.setInt(1, id);
            stmt.setInt(2, id_user);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public Aset getAsetBySource(String source) {
        List<Aset> list = getAllData();
        for (Aset a : list) {
            if (a.getSource().equalsIgnoreCase(source)) {
                return a;
            }
        }
        return null;
    }
    
    public int getAsetId(String source) {
        int id_aset = 0;
        try {
            stmt = conn.prepareStatement(getAsetId);
            stmt.setString(1, source);
            stmt.setInt(2, User.getId());
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                id_aset = rs.getInt("id");
            }

            stmt.close();
        } catch (SQLException e) {
        }
        
        
        return id_aset;
    }
    
    public int getSaldo(int id_aset) {
        int saldo = 0;
        
        try {
            stmt = conn.prepareStatement(getSaldo);
            stmt.setInt(1, id_aset);
            stmt.setInt(2, User.getId());
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                saldo = rs.getInt("saldo");
            }

            stmt.close();
        } catch (SQLException e) {
        }
        
        
        return saldo;
    }
    
    public void updateSaldo(int id_aset, int amount) {
        int saldoBaru = getSaldo(id_aset) - amount;
        
        try {
            stmt = conn.prepareStatement(updateSaldo);
            stmt.setInt(1, saldoBaru);
            stmt.setInt(2, id_aset);
            stmt.setInt(3, User.getId());
            stmt.executeUpdate();
            
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
