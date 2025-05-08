/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helpers;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import koneksi.Connector;

/**
 *
 * @author HP
 */
public class UserDAO {
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    final String cekLogin = "SELECT * FROM user WHERE username = ? AND password = ?";
    final String register = "INSERT INTO user (username, password) VALUES (?, ?)";
    final String cekUsername = "SELECT * FROM user WHERE username = ?";
    
    public UserDAO() {
        conn = Connector.connection();
    }
    
    // Cek Login
    public boolean login(String username, String password) {
        try {
            stmt = conn.prepareStatement(cekLogin);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }        
    }
    
    // Register
    public boolean register(String username, String password) {
        // Cek username
        if (isUsername(username)) {
            return false;
        } 
        
        try {
            stmt = conn.prepareStatement(register);
            stmt.setString(1, username);
            stmt.setString(2, password);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private boolean isUsername(String username) {
        try {
            stmt = conn.prepareStatement(cekUsername);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}
