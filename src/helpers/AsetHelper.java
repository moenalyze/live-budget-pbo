package helpers;

import java.sql.*;
import java.util.*;
import models.Aset;
import models.User;

public class AsetHelper {
    private final String dbUrl = "jdbc:mysql://localhost/livebudget";
    private final String username = "root";
    private final String password = "";
    
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private String query;
    
    public AsetHelper() {
        try {
            conn = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    // read data
    public List<Aset> getAllData() {
        List<Aset> data = new ArrayList<>();
         System.out.println("User ID in query: " + User.getId()); // Debug print
        query = "SELECT * FROM aset_keuangan where id_user = '" + User.getId() + "'";

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            
            while(rs.next()) {
                Aset aset = new Aset();
                aset.setType(rs.getString("jenis"));
                aset.setSource(rs.getString("sumber"));
                aset.setAmount(rs.getInt("saldo"));
                aset.setNotes(rs.getString("catatan"));
                data.add(aset);
//                System.out.println(aset.getType());
            }
            
            stmt.close();
        } catch (SQLException e) {
        }
        
        return data; 
    }
    
    // write data
    public boolean addNewTransaksi(String type, String source, int amount, String notes) {
        boolean value = false;
        int id_user = User.getId();
//        int id_aset = 1;
        
        System.out.println(id_user);
        
        query = "INSERT INTO aset_keuangan SET jenis = '" + type + "', sumber = '" + source + "', saldo = '" + amount + "', catatan = '" + notes + "', id_user = '" + User.getId() + "'";
//        query = "INSERT INTO transaksi (jenis, jumlah, tanggal, deskripsi) VALUES (" + type + "," + amount + ",", + "CURRENT_DATE," + description + "," + ")";
        
        try {
            stmt = conn.createStatement();
            if(stmt.executeUpdate(query) > 0){
                value = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return value;
    }
}
