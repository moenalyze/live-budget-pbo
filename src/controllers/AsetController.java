package controllers;

import helpers.AsetHelper;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import models.Aset;
import views.*;

public class AsetController {
    Dashboard v;
    private final DefaultTableModel modelAset;
    
    public AsetController(Dashboard v) {
        String []header = {"ID", "Type", "Source", "Amount", "Note"};
        modelAset = new DefaultTableModel(header, 0);   

        this.v = v;
        refreshTable();
        v.getjTableAset().setModel(modelAset);
         // Sembunyikan kolom ID (index ke-0)
        v.getjTableAset().getColumnModel().getColumn(0).setMinWidth(0);
        v.getjTableAset().getColumnModel().getColumn(0).setMaxWidth(0);
        v.getjTableAset().getColumnModel().getColumn(0).setWidth(0);
        v.setVisible(true);
        v.setLocationRelativeTo(null);
        
    }
    
    private void refreshTable() {
        modelAset.setRowCount(0);
        
        AsetHelper helper = new AsetHelper();
        
        List<Aset> data = helper.getAllData();
        
        if (data.isEmpty()) {
            // Tampilkan baris "tidak ada data"
            modelAset.setRowCount(1); // Satu baris kosong
            modelAset.setValueAt("Tidak ada data tersedia", 0, 0); // Set kolom pertama
            for (int i = 1; i < modelAset.getColumnCount(); i++) {
                modelAset.setValueAt("-", 0, i); // Isi kolom lain dengan tanda "-"
            }
            v.getjTableAset().setEnabled(false); // Supaya user gak bisa klik/seleksi
        } else {
            v.getjTableAset().setEnabled(true);
            data.forEach((m) -> {
                modelAset.addRow(new Object[]{m.getId(), m.getType(), m.getSource(), m.getAmount(), m.getNotes()});
            });
        }
    }
}
