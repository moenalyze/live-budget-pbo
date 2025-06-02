package controllers;

import helpers.AsetHelper;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import helpers.TransaksiHelper;
import models.Aset;
import models.Transaksi;
import models.User;
import views.*;

public class TransaksiController {
    Dashboard v;
    private final DefaultTableModel modelTransaksi;
    
    public TransaksiController(Dashboard v) {
        String []header = {"ID", "Type", "Amount", "Date", "Description", "Source"};
        modelTransaksi = new DefaultTableModel(header, 0);

        this.v = v;
        refreshTable();
        v.getjTableTransaksi().setModel(modelTransaksi);
        // Sembunyikan kolom ID (index ke-0)
        v.getjTableTransaksi().getColumnModel().getColumn(0).setMinWidth(0);
        v.getjTableTransaksi().getColumnModel().getColumn(0).setMaxWidth(0);
        v.getjTableTransaksi().getColumnModel().getColumn(0).setWidth(0);

        v.setVisible(true);
        v.setLocationRelativeTo(null);
        
    }
    
    public void tambahTransaksi(String type, int amount, String description, String source) {
        TransaksiHelper helper = new TransaksiHelper();

        // Cari Aset berdasarkan source yang dipilih
        AsetHelper asetHelper = new AsetHelper();
        Aset aset = asetHelper.getAsetBySource(source);

        if (aset != null) {
            int id_aset = asetHelper.getAsetId(source);
        
            if(helper.addNewTransaksi(type, amount, description, id_aset)) {
                asetHelper.updateSaldo(id_aset, amount);
                Transaksi.total += amount;
                AsetController asetController = new AsetController(v);
                refreshTable();
            } else {
                // error handle: gagal insert
            }
        } else {
        }
    }
    
    public void updateTransaksi(String type, int amount, String description, String source) {
        TransaksiHelper helper = new TransaksiHelper();
        
        // Cari Aset berdasarkan source yang dipilih
        AsetHelper asetHelper = new AsetHelper();
        Aset aset = asetHelper.getAsetBySource(source);

        if (aset != null) {
//            int id_aset = asetHelper.getAsetId(source);
        
            if(helper.updateTransaksi(type, amount, description, source)) {
//                asetHelper.updateSaldo(id_aset, amount);
                Transaksi.total += amount;
                AsetController asetController = new AsetController(v);
                refreshTable();
            } else {
                System.out.println("gagal update");
            }
        } else {
        }
    }

    
    private void refreshTable() {
        modelTransaksi.setRowCount(0);
        TransaksiHelper helper = new TransaksiHelper();
        
        List<Transaksi> data = helper.getAllData();
        
        if (data.isEmpty()) {
            // Tampilkan baris "tidak ada data"
            modelTransaksi.setRowCount(1); // Satu baris kosong
            modelTransaksi.setValueAt("Tidak ada data tersedia", 0, 0); // Set kolom pertama
            for (int i = 1; i < modelTransaksi.getColumnCount(); i++) {
                modelTransaksi.setValueAt("-", 0, i); // Isi kolom lain dengan tanda "-"
            }
            v.getjTableAset().setEnabled(false); // Supaya user gak bisa klik/seleksi
        } else {
            v.getjTableAset().setEnabled(true);
            data.forEach((m) -> {
                modelTransaksi.addRow(new Object[]{m.getId(),m.getType(),m.getAmount(), m.getDate(), m.getDescription(), m.getSource()});
            });
        }
    }
}
