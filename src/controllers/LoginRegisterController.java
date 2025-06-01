package controllers;

import helpers.UserHelper;
import javax.swing.JOptionPane;
import models.User;
import views.Dashboard;
import views.LoginRegisterView;

public class LoginRegisterController {
    LoginRegisterView v;
    
    public LoginRegisterController(LoginRegisterView v) {
        this.v = v;
    }
    
    public void login(String username, String password) {
        UserHelper helper = new UserHelper();

        if(helper.login(username, password)){
            Dashboard dashboard = new Dashboard(); // buat sekali

            // kirim dashboard ke kedua controller
            TransaksiController transaksiController = new TransaksiController(dashboard);
            AsetController asetController = new AsetController(dashboard);

            dashboard.setVisible(true); // show dashboard
            v.dispose(); // tutup login
        } else {
            // error handling
            JOptionPane.showMessageDialog(v, "Login gagal!");
        }
    }
    
    public void register(String username, String password) {
        UserHelper helper = new UserHelper();
        
        if(helper.register(username, password)){
            JOptionPane.showMessageDialog(v, "Registrasi berhasil!");
        } else {
            // error handling
            JOptionPane.showMessageDialog(v, "Registrasi gagal! Username mungkin sudah digunakan.");
        }
    }
}
