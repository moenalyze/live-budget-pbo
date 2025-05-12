package helpers;

import java.sql.*;
import java.util.*;
import models.User;

public class UserHelper {
    private final String dbUrl = "jdbc:mysql://localhost/livebudget";
    private final String user = "root";
    private final String pass = "";

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private String query;

    public UserHelper() {
        try {
            conn = DriverManager.getConnection(dbUrl, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // cek login
    public boolean login(String username, String password) {
        boolean result = false;
        query = "SELECT * FROM user WHERE username = '" + username + "' AND password = '" + password + "'";
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                User.setId(rs.getInt("id"));
                User.setUsername(rs.getString("username"));
                User.setPassword(rs.getString("password"));
                
                System.out.println(User.getUsername());
                System.out.println(User.getPassword());
                result = true;
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // register user baru
    public boolean register(String username, String password) {
        boolean result = false;

        // cek apakah username sudah ada
        if (isUsernameExists(username)) {
            return false;
        }

        query = "INSERT INTO user (username, password) VALUES ('" + username + "', '" + password + "')";
        
        try {
            stmt = conn.createStatement();
            if (stmt.executeUpdate(query) > 0) {
                result = true;
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // cek ketersediaan username
    private boolean isUsernameExists(String username) {
        boolean result = false;
        query = "SELECT * FROM user WHERE username = '" + username + "'";

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                result = true;
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    // read data
//    public List<User> getUserData() {
//        List<User> userData = new ArrayList<>();
//        query = "SELECT * FROM user where id_user = '" + this.userId + "'";
//        
//        try {
//            stmt = conn.createStatement();
//            rs = stmt.executeQuery(query);
//            
//            while(rs.next()) {
//                User user = new User();
//                user.setId(userId);
//                user.setUsername(rs.getString("username"));
//                user.setPassword(rs.getString("password"));
//                userData.add(user); 
//            }
//            
//            stmt.close();
//        } catch (SQLException e) {
//        }
//        
//        return userData; 
//    }
}
