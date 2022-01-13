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
import projek.kelompok.apprentaldvd.controlller.service.KoneksiFactory;
import projek.kelompok.apprentaldvd.controlller.service.ProdukDvdService;
import projek.kelompok.apprentaldvd.model.Dvd;
import projek.kelompok.apprentaldvd.view.ProdukDvd;

/**
 *
 * @author Feri Winarta
 */
public class UpdateDvd extends javax.swing.JFrame {    
    private ProdukDvdService service;
    private JFrame frame = this;
    
    public UpdateDvd() {
        initComponents();
        
        // untuk menengahkan layar
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        service = new ProdukDvdService(new KoneksiFactory().getConn());
        tombolUpdateDiklik();
        tampilDataDvd();
        
        JFrame frameIni = this;
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFrame frame = new ProdukDvd();
                frameIni.setVisible(false);
                frame.setVisible(true);
            }
        });
    }
    
    public void tombolUpdateDiklik(){
        jButton1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                String kodeDvd = inputKode.getText();
                String judul = inputJudul.getText();
                String kategori = inputKategori.getText();
                BigDecimal harga = new BigDecimal(20);
                int quantity = 0;

                try {
                    if(!inputHarga.getText().isEmpty()) {
                        harga = new BigDecimal(inputHarga.getText());
                    }
                    if(!inputQuanttiy.getText().isEmpty()) {
                        quantity = Integer.valueOf(inputQuanttiy.getText());
                    }
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Tidak boleh memasukan huruf di harga dan quantity");
                }
                
                Dvd dvd = new Dvd(kodeDvd, judul, kategori, quantity, harga);
                
                int status = 100;
                if(!inputKode.getText().isEmpty() && !inputHarga.getText().isEmpty() && !inputKategori.getText().isEmpty()
                        && !inputQuanttiy.getText().isEmpty()
                        ){
                    
                    
                     status = service.updateDvd(dvd);
                     tampilDataDvd();
                     if(status != 1 && !inputKode.getText().isEmpty()) {
                         JOptionPane.showMessageDialog(frame, "Dvd tidak ditemukan, silahkan masukan kode yg benar");
                     }
                } else {
                    JOptionPane.showMessageDialog(frame, "Data tidak boleh kosong");
                }
                
                if(status == 1) {
                    JOptionPane.showMessageDialog(frame, "Update Data Dvd berhasil");
                    inputKode.setText("");
                    inputHarga.setText("");
                    inputKategori.setText("");
                    inputQuanttiy.setText("");
                }
            }
        });
    }
    
    public void tampilDataDvd() {
        DefaultTableModel dtf = (DefaultTableModel) jTable1.getModel();
        dtf.setRowCount(0);
        List<Dvd> semuaDvd = service.getSemuaDvd();
        
        for(Dvd value : semuaDvd) {
            Vector v2 = new Vector();
            v2.add(value.getKode());
            v2.add(value.getJudulFilm());
            v2.add(value.getKategori());
            v2.add(value.getQuantity());
            v2.add(value.getHarga());
            dtf.addRow(v2);
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
        harga = new javax.swing.JLabel();
        judul = new javax.swing.JLabel();
        kategori = new javax.swing.JLabel();
        quantity = new javax.swing.JLabel();
        inputHarga = new javax.swing.JTextField();
        inputKode = new javax.swing.JTextField();
        inputKategori = new javax.swing.JTextField();
        inputQuanttiy = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        judul1 = new javax.swing.JLabel();
        inputJudul = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        harga.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        harga.setText("Harga");
        jPanel1.add(harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 100, 30));

        judul.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        judul.setText("Kode Dvd");
        jPanel1.add(judul, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 100, 30));

        kategori.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        kategori.setText("Kategori");
        jPanel1.add(kategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 100, 30));

        quantity.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        quantity.setText("Quantity");
        jPanel1.add(quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 100, 30));
        jPanel1.add(inputHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 280, 30));
        jPanel1.add(inputKode, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 280, 30));
        jPanel1.add(inputKategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 280, 30));
        jPanel1.add(inputQuanttiy, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 280, 30));

        jButton1.setText("Update");
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 280, 120, 70));

        judul1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        judul1.setText("Judul DVD");
        jPanel1.add(judul1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 100, 30));
        jPanel1.add(inputJudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 280, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Kode Dvd", "Judul Dvd", "Kategori", "Quantity", "Harga"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 320, 310));

        jButton2.setText("Kembali");
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 90, 40));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Update DVD");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 200, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(UpdateDvd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateDvd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateDvd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateDvd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateDvd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel harga;
    private javax.swing.JTextField inputHarga;
    private javax.swing.JTextField inputJudul;
    private javax.swing.JTextField inputKategori;
    private javax.swing.JTextField inputKode;
    private javax.swing.JTextField inputQuanttiy;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel judul;
    private javax.swing.JLabel judul1;
    private javax.swing.JLabel kategori;
    private javax.swing.JLabel quantity;
    // End of variables declaration//GEN-END:variables
}
