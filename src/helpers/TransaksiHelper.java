package helpers;

import java.sql.*;
import java.util.*;
import models.Transaksi;
import models.User;

public class TransaksiHelper {
    private final String dbUrl = "jdbc:mysql://localhost/livebudget";
    private final String username = "root";
    private final String password = "";
    
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private String query;
    
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
        query = "SELECT * FROM transaksi where id_user = '" + User.getId() + "'";
        
        try {
            System.out.println(User.getId());
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            
            while(rs.next()) {
                Transaksi transaksi = new Transaksi();
                transaksi.setType(rs.getString("jenis"));
                transaksi.setAmount(rs.getInt("jumlah"));
                transaksi.setDate(rs.getString("tanggal"));
                transaksi.setDescription(rs.getString("deskripsi"));
                data.add(transaksi);
            }
            
            stmt.close();
        } catch (SQLException e) {
        }
        
        return data; 
    }
    
    // write data
    public boolean addNewTransaksi(String type, int amount, String description) {
        boolean value = false;
        int id_user = User.getId();
        int id_aset = 1;
        
        System.out.println(id_user);
        
        query = "INSERT INTO transaksi SET jenis = '" + type + "', jumlah = '" + amount + "', deskripsi = '" + description + "', id_user = '" + User.getId() + "', id_aset = '" + id_aset + "'";
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
