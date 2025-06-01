package controllers;

import helpers.PemasukanHelper;
import models.Pemasukan;
import views.Dashboard;
import views.TransaksiView;

public class PemasukanController {
    private TransaksiView frame;
    private PemasukanHelper helper;
    
    public PemasukanController(TransaksiView frame) {
        this.frame = frame;
        helper = new PemasukanHelper();
    }
    
    public void addNewPemasukan() {
        Pemasukan model = new Pemasukan();
        Dashboard v = new Dashboard();
        
        // Ambil data dari form
        model.setType(frame.getjComboBoxTypePemasukan().getSelectedItem().toString());
        model.setSource(frame.getjTextFieldSourcePemasukan().getText());
        model.setAmount(Integer.parseInt(frame.getjTextFieldAmountPemasukan().getText()));
        model.setNote(frame.getjTextAreaDescriptionPemasukan().getText());
        
        // Mengirim data ke helper untuk di eksekusi
        boolean success = helper.addNewAset(model);
        
        if (success) {
            AsetController asetController = new AsetController(v);
            frame.dispose();
        } else {
            System.out.println("Gagal");
        }
    }
    
    public void updatePemasukan() {
        Pemasukan model = new Pemasukan();
        Dashboard v = new Dashboard();
        
        // Ambil data dari form
        model.setType(frame.getjComboBoxTypePemasukan().getSelectedItem().toString());
        model.setSource(frame.getjTextFieldSourcePemasukan().getText());
        model.setAmount(Integer.parseInt(frame.getjTextFieldAmountPemasukan().getText()));
        model.setNote(frame.getjTextAreaDescriptionPemasukan().getText());
        
        // Mengirim data ke helper untuk di eksekusi
        boolean success = helper.updateAset(model);
        
        if (success) {
            AsetController asetController = new AsetController(v);
            frame.dispose();
        } else {
            System.out.println("Gagal");
        }
    }
}
