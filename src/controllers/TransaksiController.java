package controllers;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import helpers.DBHelper;
import models.Transaksi;
import views.*;

public class TransaksiController {
    DashBoard v;
    private final DefaultTableModel modelTransaksi;
    
    public TransaksiController() {
        String []header = {"Type", "Amount", "Date", "Description"};
        modelTransaksi = new DefaultTableModel(header, 0);
        refreshTable();
        
        v = new DashBoard(this);
        v.getjTableTransaksi().setModel(modelTransaksi);
        v.setVisible(true);
    }
    
    public void tambahTransaksi(String type, int amount, String description) {
        DBHelper helper = new DBHelper();
        
//        // Ambil data dari form
//        String jenis = jComboBoxType.getSelectedItem().toString();;         // misal: "Pemasukan" / "Pengeluaran"
//        int jumlah = getJumlah();                  // pastikan parsing dari text field
//        String deskripsi = getDeskripsi();         // dari textarea
        
        if(helper.addNewTransaksi(type, amount, description)) {
            refreshTable();
        } else {
            // isi dialog error handling
        }
    }
    
    private void refreshTable() {
        modelTransaksi.setRowCount(0);
        DBHelper helper = new DBHelper();
        
        List<Transaksi> data = helper.getAllData();
        
        data.forEach((m) -> {
            modelTransaksi.addRow(new Object[]{m.getType(),m.getAmount(), m.getDate(), m.getDescription()});
        });
    }
}
