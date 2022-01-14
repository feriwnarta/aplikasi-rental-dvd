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
import projek.kelompok.apprentaldvd.service.KoneksiFactory;
import projek.kelompok.apprentaldvd.service.ProdukDvdService;
import projek.kelompok.apprentaldvd.model.Dvd;
import projek.kelompok.apprentaldvd.view.ProdukDvd;

/**
 *
 * @author Feri Winarta
 */
public class TambahDvd extends javax.swing.JFrame {    
    private ProdukDvdService service;
    private JFrame frame = this;
    
    public TambahDvd(String idAdmin) {
        initComponents();
        
        // untuk menengahkan layar
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        service = new ProdukDvdService(new KoneksiFactory().getConn());
        
        tombolSimpanDiklik();
        tampilDataDvd();
        
        JFrame frameIni = this;
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFrame frame = new ProdukDvd(idAdmin);
                frameIni.setVisible(false);
                frame.setVisible(true);
            }
        });
        
        
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
                     tampilDataDvd();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        harga.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        harga.setText("Harga");
        jPanel1.add(harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 100, 30));

        judul.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        judul.setText("Judul DVD");
        jPanel1.add(judul, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 100, 30));

        kategori.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        kategori.setText("Kategori");
        jPanel1.add(kategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 100, 30));

        quantity.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        quantity.setText("Quantity");
        jPanel1.add(quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 100, 30));
        jPanel1.add(inputHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 280, 30));
        jPanel1.add(inputJudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 280, 30));
        jPanel1.add(inputKategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 280, 30));
        jPanel1.add(inputQuanttiy, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 280, 30));

        jButton1.setText("Simpan");
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, 120, 70));

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("TAMBAH DVD");
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
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel judul;
    private javax.swing.JLabel kategori;
    private javax.swing.JLabel quantity;
    // End of variables declaration//GEN-END:variables
}
