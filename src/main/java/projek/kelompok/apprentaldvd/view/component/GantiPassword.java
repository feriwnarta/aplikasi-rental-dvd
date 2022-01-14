/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projek.kelompok.apprentaldvd.view.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import projek.kelompok.apprentaldvd.service.AdminService;
import projek.kelompok.apprentaldvd.service.KoneksiFactory;
import projek.kelompok.apprentaldvd.service.ProdukDvdService;
import projek.kelompok.apprentaldvd.model.Admin;
import projek.kelompok.apprentaldvd.model.Dvd;
import projek.kelompok.apprentaldvd.view.AdminView;
import projek.kelompok.apprentaldvd.view.ProdukDvd;

/**
 *
 * @author Feri Winarta
 */
public class GantiPassword extends javax.swing.JFrame {    
    private AdminService service;
    private JFrame frame = this;
    
    public GantiPassword(String idAdmin) {
        initComponents();
        
        // untuk menengahkan layar
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        service = new AdminService(new KoneksiFactory().getConn());
        
        JFrame frameIni = this;
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFrame frame = new AdminView(idAdmin);
                frameIni.setVisible(false);
                frame.setVisible(true);
            }
        });
    }
    
    public void gantiPassword(){
        try {
            
            String id = inputId.getText();
            String passwordLama = inputPasswordLama.getText();
            String passwordBaru =  inputPasswordBaru.getText();
            
            Admin admin = service.getOneAdmin(id);
            if(admin.getId().equals(id) && admin.getPassword().equals(passwordLama)) {
                Admin adminUpdate = admin;
                adminUpdate.setPassword(passwordBaru);
                int status = service.updateAdmin(adminUpdate);
                if(status == 1) {
                    JOptionPane.showMessageDialog(this, "Password berhasil diganti");
                } else {
                    JOptionPane.showMessageDialog(this, "Password gagal diganti");
                }
            }
            
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "ID SALAH");
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        judul = new javax.swing.JLabel();
        kategori = new javax.swing.JLabel();
        quantity = new javax.swing.JLabel();
        inputId = new javax.swing.JTextField();
        inputPasswordLama = new javax.swing.JTextField();
        inputPasswordBaru = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        judul.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        judul.setText("ID");
        jPanel1.add(judul, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 100, 30));

        kategori.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        kategori.setText("Password lama");
        jPanel1.add(kategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 140, 30));

        quantity.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        quantity.setText("Password Baru");
        jPanel1.add(quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 130, 30));
        jPanel1.add(inputId, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 260, 30));
        jPanel1.add(inputPasswordLama, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 260, 30));
        jPanel1.add(inputPasswordBaru, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 260, 30));

        jButton1.setText("Simpan");
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, 120, 70));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("GANTI PASSWORD");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 200, 30));

        jButton2.setText("Kembali");
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 90, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 777, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GantiPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GantiPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GantiPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GantiPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField inputId;
    private javax.swing.JTextField inputPasswordBaru;
    private javax.swing.JTextField inputPasswordLama;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel judul;
    private javax.swing.JLabel kategori;
    private javax.swing.JLabel quantity;
    // End of variables declaration//GEN-END:variables
}
