package controllers;

import helpers.AsetHelper;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import models.Aset;
import models.User;
import views.*;

public class AsetController {
    Dashboard v;
    private final DefaultTableModel modelAset;
    
    public AsetController(Dashboard v) {
        String []header = {"Type", "Source", "Amount", "Notes"};
        modelAset = new DefaultTableModel(header, 0);
//        this.userId = userId;
//        this.username = username;
        

        System.out.println(User.getUsername());
//        v = new Dashboard(this, User.getUsername());
        this.v = v;
        v.getjTableAset().setModel(modelAset);
        refreshTable();
        v.setVisible(true);
        v.setLocationRelativeTo(null);
        
    }
    
//    public void tambahTransaksi(String type, int amount, String description) {
//        TransaksiHelper helper = new TransaksiHelper();
//        
//        if(helper.addNewTransaksi(type, amount, description)) {
//            refreshTable();
//        } else {
//            // isi dialog error handling
//        }
//    }
    
    private void refreshTable() {
        modelAset.setRowCount(0);
        AsetHelper helper = new AsetHelper();
        
        List<Aset> data = helper.getAllData();
        
        data.forEach((m) -> {
            modelAset.addRow(new Object[]{m.getType(), m.getSource(), m.getAmount(), m.getNotes()});
        });
//        data.forEach((m) -> {
//            System.out.println(m.getType());
//        });
    }
}
