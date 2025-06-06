package views;

import controllers.AsetController;
import controllers.TransaksiController;
import javax.swing.JTable;
import helpers.AsetHelper;
import helpers.TransaksiHelper;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import models.Transaksi;
import models.User;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Component;

public class Dashboard extends javax.swing.JFrame {
    private AsetController asetController;
    private TransaksiController transaksiController;
    


    private void customizeTable(JTable table) {
        table.setRowHeight(25); // Atur tinggi baris

        // Ganti warna header
        table.getTableHeader().setBackground(new Color(100, 149, 237)); // warna biru muda
        table.getTableHeader().setForeground(Color.WHITE);

        // Ganti warna baris
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (isSelected) {
                    c.setBackground(new Color(144, 238, 144)); // warna hijau muda saat dipilih
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(245, 245, 245)); // warna selang-seling
                    c.setForeground(Color.BLACK);
                }

                return c;
            }
        };

        // Terapkan renderer ke semua kolom
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }


    public JTable getjTableTransaksi() {
        return jTableTransaksi;
    }
    
    public JTable getjTableAset() {
        return jTableAset;
    }
    
    /**
     * Creates new form Dashboard
     */
    
    public AsetController getAsetController() {
        return asetController;
    }
    
    public Dashboard() {
        initComponents();
        asetController = new AsetController(this);
        transaksiController = new TransaksiController(this);
        
         customizeTable(jTableAset);
        customizeTable(jTableTransaksi);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPopupMenuAset = new javax.swing.JPopupMenu();
        jMenuItemHapusAset = new javax.swing.JMenuItem();
        jMenuItemUpdateAset = new javax.swing.JMenuItem();
        jPopupMenuTransaksi = new javax.swing.JPopupMenu();
        jMenuItemHapusTransaksi = new javax.swing.JMenuItem();
        jMenuItemUpdateTransaksi = new javax.swing.JMenuItem();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabelName = new javax.swing.JLabel();
        jButtonLogout = new javax.swing.JButton();
        jButtonAdd = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableTransaksi = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableAset = new javax.swing.JTable();

        jScrollPane4.setViewportView(jTextPane2);

        jScrollPane3.setViewportView(jTextPane1);

        jMenuItemHapusAset.setText("Delete");
        jMenuItemHapusAset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemHapusAsetActionPerformed(evt);
            }
        });
        jPopupMenuAset.add(jMenuItemHapusAset);

        jMenuItemUpdateAset.setText("Update");
        jMenuItemUpdateAset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUpdateAsetActionPerformed(evt);
            }
        });
        jPopupMenuAset.add(jMenuItemUpdateAset);

        jMenuItemHapusTransaksi.setText("Delete");
        jMenuItemHapusTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemHapusTransaksiActionPerformed(evt);
            }
        });
        jPopupMenuTransaksi.add(jMenuItemHapusTransaksi);

        jMenuItemUpdateTransaksi.setText("Update");
        jMenuItemUpdateTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUpdateTransaksiActionPerformed(evt);
            }
        });
        jPopupMenuTransaksi.add(jMenuItemUpdateTransaksi);

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(502, 445));

        jPanel1.setBackground(new java.awt.Color(232, 245, 233));

        jLabelName.setText("Hi, " + User.getUsername() + "!");

        jButtonLogout.setBackground(new java.awt.Color(255, 38, 62));
        jButtonLogout.setText("Logout");
        jButtonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogoutActionPerformed(evt);
            }
        });

        jButtonAdd.setBackground(new java.awt.Color(223, 247, 226));
        jButtonAdd.setText("+");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jTabbedPane1.setBackground(new java.awt.Color(241, 248, 233));

        jTableTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableTransaksi.setComponentPopupMenu(jPopupMenuTransaksi);
        jTableTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableTransaksiMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableTransaksiMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTableTransaksi);

        jTabbedPane1.addTab("Transaction", jScrollPane2);

        jTableAset.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableAset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableAsetMouseReleased(evt);
            }
        });
        jScrollPane5.setViewportView(jTableAset);

        jTabbedPane1.addTab("Assets", jScrollPane5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonLogout)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLogout)
                    .addComponent(jLabelName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addComponent(jLabel6)
                .addContainerGap(192, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(384, 384, 384))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTransaksiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableTransaksiMouseClicked

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        dispose();
        TransaksiView transaksi = new TransaksiView();
        transaksi.setVisible(true);
        transaksi.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jTableAsetMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAsetMouseReleased
        // Menampilkan Pop up pas klik kanan
        if (evt.isPopupTrigger()) {
            // Ngambil baris yang diklik
            int row = jTableAset.rowAtPoint(evt.getPoint());
            if (row >= 0 && row < jTableAset.getRowCount()) {
                // Set baris itu sebagai baris yang dipilih (seleksi)
                jTableAset.setRowSelectionInterval(row, row);
            }
            // Menampilkan button Delete di posisi mouse
            jPopupMenuAset.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jTableAsetMouseReleased

    private void jMenuItemHapusAsetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemHapusAsetActionPerformed
        // Ambil baris yang dipilih
        int selectedRow = jTableAset.getSelectedRow();
        if (selectedRow >= 0) {
            // Kolom 2 index 1
            int id = Integer.parseInt(jTableAset.getValueAt(selectedRow, 0).toString()); // kolom sumber
            int id_user = User.getId();
            
            // Untuk validasi
            int confirm = JOptionPane.showConfirmDialog(null, "Yakin ingin menghapus aset ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Kirim data ke helper dan akan di eksekusi
                AsetHelper helper = new AsetHelper();
                helper.deleteAset(id, id_user);

                // Refresh tabel: panggil ulang controller
                asetController = new AsetController(this);
                transaksiController = new TransaksiController(this);
            }
        }
    }//GEN-LAST:event_jMenuItemHapusAsetActionPerformed

    private void jTableTransaksiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTransaksiMouseReleased
        // Menampilkan Pop up pas klik kanan
        if (evt.isPopupTrigger()) {
            // Ngambil baris yang diklik
            int row = jTableTransaksi.rowAtPoint(evt.getPoint());
            if (row >= 0 && row < jTableTransaksi.getRowCount()) {
                // Set baris itu sebagai baris yang dipilih (seleksi)
                jTableTransaksi.setRowSelectionInterval(row, row);
            }
            // Menampilkan button Delete di posisi mouse
            jPopupMenuTransaksi.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jTableTransaksiMouseReleased

    private void jMenuItemHapusTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemHapusTransaksiActionPerformed
        // Ambil baris yang dipilih
        int selectedRow = jTableTransaksi.getSelectedRow();
        if (selectedRow >= 0) {
            // Kolom 2 index 1
            int id = Integer.parseInt(jTableTransaksi.getValueAt(selectedRow, 0).toString());
            int id_user = User.getId();
            
            // Untuk validasi
            int confirm = JOptionPane.showConfirmDialog(null, "Yakin ingin menghapus transaksi ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Kirim data ke helper dan akan di eksekusi
                TransaksiHelper helper = new TransaksiHelper();
                
                helper.deleteTransaksi(id, id_user);

                // Refresh tabel: panggil ulang controller
                asetController = new AsetController(this);
                transaksiController = new TransaksiController(this);
            }
        }
    }//GEN-LAST:event_jMenuItemHapusTransaksiActionPerformed

    private void jMenuItemUpdateTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUpdateTransaksiActionPerformed
        int selectedRow = jTableTransaksi.getSelectedRow();
        if (selectedRow >= 0) {
           
            // Ambil data dari baris yang dipilih
            TransaksiView.selectedTransaksiId = Integer.parseInt(jTableTransaksi.getValueAt(selectedRow, 0).toString());
            String tipe = jTableTransaksi.getValueAt(selectedRow, 1).toString();
            int jumlah = Integer.parseInt(jTableTransaksi.getValueAt(selectedRow, 2).toString());
            String deskripsi = jTableTransaksi.getValueAt(selectedRow, 4).toString();
            String sumber = jTableTransaksi.getValueAt(selectedRow, 5).toString();

            // Buat objek Transaksi
            Transaksi t = new Transaksi();
            t.setId(TransaksiView.selectedTransaksiId);
            t.setType(tipe);
            t.setAmount(jumlah);
            t.setDescription(deskripsi);
            t.setSource(sumber);
            
            dispose();
            TransaksiView form = new TransaksiView();
            
            form.getjComboBoxSource().setSelectedItem(sumber);
            form.getjTextFieldAmountPengeluaran().setText(String.valueOf(jumlah));
            form.getjTextAreaDescriptionPengeluaran().setText(deskripsi);
            form.getjTabbedPane1().setSelectedIndex(0); // tab index pengeluaran
            

            // 4. Tampilkan form
            form.setVisible(true);
            form.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_jMenuItemUpdateTransaksiActionPerformed

    private void jMenuItemUpdateAsetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUpdateAsetActionPerformed
        int selectedRow = jTableAset.getSelectedRow();
        if (selectedRow >= 0) {
           
            // Ambil data dari baris yang dipilih
            TransaksiView.selectedAsetId = Integer.parseInt(jTableAset.getValueAt(selectedRow, 0).toString());
            String tipe = jTableAset.getValueAt(selectedRow, 1).toString();
            String sumber = jTableAset.getValueAt(selectedRow, 2).toString();
            int jumlah = Integer.parseInt(jTableAset.getValueAt(selectedRow, 3).toString());
            String deskripsi = jTableAset.getValueAt(selectedRow, 4).toString();

            // Buat objek Transaksi
            Transaksi t = new Transaksi();
            t.setId(TransaksiView.selectedAsetId);
            t.setType(tipe);
            t.setAmount(jumlah);
            t.setDescription(deskripsi);
            t.setSource(sumber);
            
            dispose();
            TransaksiView form = new TransaksiView();
            
            form.getjTextFieldSourcePemasukan().setText(sumber);
            form.getjTextFieldAmountPemasukan().setText(String.valueOf(jumlah));
            form.getjTextAreaDescriptionPemasukan().setText(deskripsi);
            form.getjComboBoxTypePemasukan().setSelectedItem(tipe);// default "Tunai" misalnya
            form.getjTabbedPane1().setSelectedIndex(1); // tab index pemasukan
            

            // 4. Tampilkan form
            form.setVisible(true);
            form.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_jMenuItemUpdateAsetActionPerformed

    private void jButtonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogoutActionPerformed
        // Konfirmasi dulu (opsional)
        int confirm = JOptionPane.showConfirmDialog(null, "Yakin ingin logout?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Tutup form sekarang
            dispose(); 
            
            // Hapus data session user (kalau ada)
            User.setId(0); // contoh kalau pakai static User
            
            // Buka form login lagi
            LoginRegisterView lrv = new LoginRegisterView();
            lrv.setVisible(true);
            lrv.setLocationRelativeTo(null); 
        }
    }//GEN-LAST:event_jButtonLogoutActionPerformed

   
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Dashboard().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonLogout;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JMenuItem jMenuItemHapusAset;
    private javax.swing.JMenuItem jMenuItemHapusTransaksi;
    private javax.swing.JMenuItem jMenuItemUpdateAset;
    private javax.swing.JMenuItem jMenuItemUpdateTransaksi;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenuAset;
    private javax.swing.JPopupMenu jPopupMenuTransaksi;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableAset;
    private javax.swing.JTable jTableTransaksi;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    // End of variables declaration//GEN-END:variables
    
}
