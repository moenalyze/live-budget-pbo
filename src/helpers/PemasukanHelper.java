package helpers;

import java.sql.*;
import java.util.*;
import models.Pemasukan;
import models.User;
import views.TransaksiView;


public class PemasukanHelper {
    private final String dbUrl = "jdbc:mysql://localhost/livebudget";
    private final String username = "root";
    private final String password = "";
    
    private Connection conn;
    private PreparedStatement stmt, updateStmt, insertStmt;
    private ResultSet rs;
    
    final String cekQuery = "SELECT id, saldo FROM aset_keuangan WHERE jenis = ? AND sumber = ? AND id_user = ?";
    final String addQuery = "UPDATE aset_keuangan SET saldo = saldo + ? WHERE id = ?";
    final String insertQuery = "INSERT INTO  aset_keuangan (jenis, sumber, saldo, catatan, id_user) VALUES (?, ?, ?, ?, ?)";
    final String updateQuery = "UPDATE aset_keuangan SET jenis = ?, sumber = ?, saldo = ?, catatan = ? WHERE id = ? AND id_user = ?";
    
    public PemasukanHelper() {
        try {
            conn = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public boolean addNewAset(Pemasukan pemasukan) {
        try {
            stmt = conn.prepareStatement(cekQuery);
            stmt.setString(1, pemasukan.getType());
            stmt.setString(2, pemasukan.getSource());
            stmt.setInt(3, User.getId());
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                // Sudah ada data, lakukan update saldo
                int isExist = rs.getInt("id");
                
                updateStmt = conn.prepareStatement(addQuery);
                updateStmt.setInt(1, pemasukan.getAmount());
                updateStmt.setInt(2, isExist);
                
                int rowsUpdate = updateStmt.executeUpdate();
                updateStmt.close();
                return rowsUpdate > 0;
            } else {
                insertStmt = conn.prepareStatement(insertQuery);
                insertStmt.setString(1, pemasukan.getType());
                insertStmt.setString(2, pemasukan.getSource());
                insertStmt.setInt(3, pemasukan.getAmount());
                insertStmt.setString(4, pemasukan.getNote());
                insertStmt.setInt(5, User.getId());
                
                int rowsInsert = insertStmt.executeUpdate();
                insertStmt.close();
                return rowsInsert > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public boolean updateAset(Pemasukan pemasukan) {
        try {
            stmt = conn.prepareStatement(updateQuery);
            stmt.setString(1, pemasukan.getType());
            stmt.setString(2, pemasukan.getSource());
            stmt.setInt(3, pemasukan.getAmount());
            stmt.setString(4, pemasukan.getNote());
            stmt.setInt(5, TransaksiView.selectedAsetId);
            stmt.setInt(6, User.getId());
            
            int rows = stmt.executeUpdate();
            TransaksiView.selectedAsetId = 0;
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
