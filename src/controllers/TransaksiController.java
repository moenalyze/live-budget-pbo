package controllers;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import helpers.TransaksiHelper;
import models.Transaksi;
import models.User;
import views.*;

public class TransaksiController {
    Dashboard v;
    private final DefaultTableModel modelTransaksi;
    
    public TransaksiController(Dashboard v) {
        String []header = {"Type", "Amount", "Date", "Description"};
        modelTransaksi = new DefaultTableModel(header, 0);
//        this.userId = userId;
//        this.username = username;

        System.out.println(User.getId());
//        v = new Dashboard(this, User.getUsername());
        this.v = v;
        v.getjTableTransaksi().setModel(modelTransaksi);
        refreshTable();
        v.setVisible(true);
        v.setLocationRelativeTo(null);
        
    }
    
    public void tambahTransaksi(String type, int amount, String description) {
        TransaksiHelper helper = new TransaksiHelper();
        
        if(helper.addNewTransaksi(type, amount, description)) {
            refreshTable();
        } else {
            // isi dialog error handling
        }
    }
    
    private void refreshTable() {
        modelTransaksi.setRowCount(0);
        TransaksiHelper helper = new TransaksiHelper();
        
        List<Transaksi> data = helper.getAllData();
        
        data.forEach((m) -> {
            modelTransaksi.addRow(new Object[]{m.getType(),m.getAmount(), m.getDate(), m.getDescription()});
        });
    }
}
