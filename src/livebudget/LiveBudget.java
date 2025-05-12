package livebudget;

import controllers.TransaksiController;
import java.sql.Connection;
//import koneksi.Connector;
import views.LoginRegisterView;

public class LiveBudget {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        new TransaksiController();
        LoginRegisterView v = new LoginRegisterView();
        v.setVisible(true);
        v.setLocationRelativeTo(null);
    }
    
}
