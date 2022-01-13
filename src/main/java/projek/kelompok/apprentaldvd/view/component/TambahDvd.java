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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import projek.kelompok.apprentaldvd.controlller.service.KoneksiFactory;
import projek.kelompok.apprentaldvd.controlller.service.ProdukDvdService;
import projek.kelompok.apprentaldvd.model.Dvd;

/**
 *
 * @author Feri Winarta
 */
public class TambahDvd extends javax.swing.JFrame {    
    private ProdukDvdService service;
    private JFrame frame = this;
    
    public TambahDvd() {
        initComponents();
        
        service = new ProdukDvdService(new KoneksiFactory().getConn());
        
        tombolSimpanDiklik();
        
        
    }
    
    public void tombolSimpanDiklik(){
        jButton1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                String judul = inputJudul.getText();
                String kategori = inputKategori.getText();
                BigDecimal harga = new BigDecimal(20);
                int quantity = 0;
                
                // ini adalah kode generate untuk dvd
                String kode = generateKode();
                
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
                
                Dvd dvd = new Dvd(kode, judul, kategori, quantity, harga);
                int status = 100;
                if(!inputJudul.getText().isEmpty() && !inputHarga.getText().isEmpty() && !inputKategori.getText().isEmpty()
                        && !inputQuanttiy.getText().isEmpty()
                        ) {
                     status = service.insertDvd(dvd);
                } else {
                    JOptionPane.showMessageDialog(frame, "Data tidak boleh kosong");
                }
                
                if(status == 1) {
                    JOptionPane.showMessageDialog(frame, "Input Data Dvd berhasil");
                    inputJudul.setText("");
                    inputHarga.setText("");
                    inputKategori.setText("");
                    inputQuanttiy.setText("");
                }
            }
        });
    }
    
    public String generateKode(){
        String kodeTerakhir = "";
        
        try {
            KoneksiFactory factory = new KoneksiFactory();
            Connection conn = factory.getConn();
            ResultSet set = conn.createStatement().executeQuery("SELECT kode_dvd FROM dvd");
            LinkedList<String> kodeDvd = new LinkedList<>();
            while(set.next()) {
                kodeDvd.add(set.getString("kode_dvd"));
            }
             kodeTerakhir = kodeDvd.getLast();
            
        } catch (SQLException ex) {
            Logger.getLogger(TambahDvd.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String[] kode = kodeTerakhir.split("(?!^)");
        List<Integer> parseK = new ArrayList<>();
        boolean isTrue;
        
        // ini akan memisahkan character k dengan 0012 (K0012) -> (0012)
        for(String data : kode) {
            try {
                int parseInt = Integer.parseInt(data);
                parseK.add(parseInt);
            } catch (Exception ex) {
                ex.getMessage();
            }
        }
        
        // ini akan memisahkan angka 00 dengan 12 -> (0012) -> (12)
        List<Integer> parse0 = new ArrayList<>();
        for(int data : parseK) {
            if(data != 0) {
                parse0.add(data);
            }
        }
        // menaikan incerement
        String parseIncrement = "";
        for(int data : parse0) {
            parseIncrement += Integer.toString(data) + "";
        }
        
        Integer data = Integer.parseInt(parseIncrement);
        data++;
        
        LinkedList<String> parseIncrementToString = new LinkedList<>();
        String result = "";
        
        while(data > 0) {
            parseIncrementToString.push(Integer.toString(data % 10));
            data = data / 10;
        }
        
        
        switch(parseIncrementToString.size()) {
            case 1 : 
                result += ("K000" + parseIncrementToString.get(0));
                break;
            case 2 :
                result += ("K00" + parseIncrementToString.get(0) + parseIncrementToString.get(1));
                break;
            case 3 :
                result += ("K0" + parseIncrementToString.get(0) + parseIncrementToString.get(1) + parseIncrementToString.get(2));
                break;
            case 4 :
                result += ("K" +  parseIncrementToString.get(0) + parseIncrementToString.get(1) + parseIncrementToString.get(2) + 
                        parseIncrementToString.get(3));
                break;
            default :     
                JOptionPane.showMessageDialog(this, "ada yng salah dengan generate kode");
        }
        
        
        return result;
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
        inputJudul = new javax.swing.JTextField();
        inputKategori = new javax.swing.JTextField();
        inputQuanttiy = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        harga.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        harga.setText("Harga");
        jPanel1.add(harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 100, 30));

        judul.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        judul.setText("Judul DVD");
        jPanel1.add(judul, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 100, 30));

        kategori.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        kategori.setText("Kategori");
        jPanel1.add(kategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 100, 30));

        quantity.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        quantity.setText("Quantity");
        jPanel1.add(quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 100, 30));
        jPanel1.add(inputHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 280, 30));
        jPanel1.add(inputJudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 280, 30));
        jPanel1.add(inputKategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 280, 30));
        jPanel1.add(inputQuanttiy, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 280, 30));

        jButton1.setText("Simpan");
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, 120, 70));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(TambahDvd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TambahDvd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TambahDvd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TambahDvd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TambahDvd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel harga;
    private javax.swing.JTextField inputHarga;
    private javax.swing.JTextField inputJudul;
    private javax.swing.JTextField inputKategori;
    private javax.swing.JTextField inputQuanttiy;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel judul;
    private javax.swing.JLabel kategori;
    private javax.swing.JLabel quantity;
    // End of variables declaration//GEN-END:variables
}
