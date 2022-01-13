/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projek.kelompok.apprentaldvd.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import projek.kelompok.apprentaldvd.controlller.service.AdminService;
import projek.kelompok.apprentaldvd.controlller.service.CustomerService;
import projek.kelompok.apprentaldvd.controlller.service.KoneksiFactory;
import projek.kelompok.apprentaldvd.controlller.service.PemesananService;
import projek.kelompok.apprentaldvd.controlller.service.ProdukDvdService;
import projek.kelompok.apprentaldvd.model.Customer;
import projek.kelompok.apprentaldvd.model.Dvd;
import projek.kelompok.apprentaldvd.model.Pemesanan;

/**
 *
 * @author Feri Winarta
 */
public class PemesananDvd extends javax.swing.JFrame {
    private ProdukDvdService dvdService;
    private CustomerService customerService;
    private PemesananService pemesananService;
    private Customer cust;
    JFrame frame = this;
    boolean isOke = false;
    
    public PemesananDvd() {
        initComponents();
        
        dvdService = new ProdukDvdService(new KoneksiFactory().getConn());
        customerService = new CustomerService(new KoneksiFactory().getConn());
        pemesananService = new PemesananService(new KoneksiFactory().getConn());
        
        
        tambahFilmClicked();
        cekNikClicked();
        
        
    }
    
    public void informasiPemesanan()    {
        
        String nik = inputNikField1.getText();
        String nama = inputNamaCustField1.getText();
        String alamat = inputAlamatField1.getText();
        String noTelp = inputNoTelpFiedl.getText();
        String kode = inputKodeFilmField.getText();
        
        int lama = 0;
        int quantity = 0;
        
        if(!inputLamaSewaField.getText().isEmpty()) {
            try {
                 lama = Integer.parseInt(inputLamaSewaField.getText());
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(this, "Sialhkan masukan lama sewa sebagai angka");
            }
        }
        
        if(!inputQuantityField.getText().isEmpty()) {
            try {
                 quantity = Integer.parseInt(inputQuantityField.getText());
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(this, "Sialhkan masukan quantity sebagai angka");
            }
        }
        
        
        if(!nik.isEmpty() && !nama.isEmpty() && !alamat.isEmpty() && !noTelp.isEmpty() && !kode.isEmpty()) {
            cekKodefilm(kode); // untuk kode film salah
            
            if(cekKodefilm(kode) != null) {
                // cek jika nik sudah ada
                
                if(cekNik() == false) {
                    // insert data customer
                    Customer customer = new Customer(inputNikField1.getText(), inputNamaCustField1.getText(), 
                    inputAlamatField1.getText(), inputNoTelpFiedl.getText());
                    customerService.insertCustomer(customer);
                     // insert pemesanan service
                    Pemesanan pemesanan1 = new Pemesanan(nik, kode, quantity, lama);
                    pemesananService.insertPemesanan(pemesanan1);
                    JOptionPane.showMessageDialog(this, "proses tambah customer dan pemesanan berhasil");
                } else if(isOke == true || cekNik() == true) {
                    // insert pemesanan service
                    Pemesanan pemesanan1 = new Pemesanan(nik, kode, quantity, lama);
                    pemesananService.insertPemesanan(pemesanan1);
                    JOptionPane.showMessageDialog(this, "proses tambah berhasil");
                }else {
                    JOptionPane.showMessageDialog(this, "NIK sudah terdaftar, klik tombol tambah atau proses pembyaran lagi");
                    isOke = true;
                }
                
                
            } else {
               JOptionPane.showMessageDialog(this, "kode film salah");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Anda belum mengisi data");
        }
        
    }
    
    public boolean cekNik(){
        boolean isUdah = false;
        if(customerService.cekNik(inputNikField1.getText()) == null) {
            JOptionPane.showMessageDialog(this, "Nik tidak ketemu");
        } else {
           cust = customerService.cekNik(inputNikField1.getText());
           inputAlamatField1.setText(cust.getAlamat());
           inputAlamatField1.setForeground(Color.BLUE);
           inputNamaCustField1.setText(cust.getNama());
           inputNamaCustField1.setForeground(Color.BLUE);
           inputNikField1.setText(cust.getNik());
           inputNikField1.setForeground(Color.BLUE);
           inputNoTelpFiedl.setText(cust.getNoTelp());
           inputNoTelpFiedl.setForeground(Color.BLUE);
           isUdah = true;
        }
        return isUdah;
    }
    
    public void cekNikClicked(){
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cekNik();
            }
        });
    }
    
    public void tambahFilmClicked(){
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                informasiPemesanan();
                
            }
        });
    }
    
    public Dvd cekKodefilm(String kode){
        Dvd dvd = null;
        try {
             dvd = dvdService.getSatuDvd(kode);
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Kode film salah");
        }
        return dvd;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Fo
     * rm Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonBg1 = new projek.kelompok.apprentaldvd.view.component.ButtonBg();
        jLabel2 = new javax.swing.JLabel();
        inputLamaSewaField = new javax.swing.JTextField();
        buttonBg2 = new projek.kelompok.apprentaldvd.view.component.ButtonBg();
        jLabel3 = new javax.swing.JLabel();
        inputNamaCustField1 = new javax.swing.JTextField();
        buttonBg3 = new projek.kelompok.apprentaldvd.view.component.ButtonBg();
        jLabel4 = new javax.swing.JLabel();
        inputAlamatField1 = new javax.swing.JTextField();
        buttonBg4 = new projek.kelompok.apprentaldvd.view.component.ButtonBg();
        jLabel5 = new javax.swing.JLabel();
        inputNoTelpFiedl = new javax.swing.JTextField();
        buttonBg5 = new projek.kelompok.apprentaldvd.view.component.ButtonBg();
        jLabel6 = new javax.swing.JLabel();
        inputNikField1 = new javax.swing.JTextField();
        buttonBg6 = new projek.kelompok.apprentaldvd.view.component.ButtonBg();
        jLabel7 = new javax.swing.JLabel();
        buttonBg7 = new projek.kelompok.apprentaldvd.view.component.ButtonBg();
        jLabel8 = new javax.swing.JLabel();
        inputKodeFilmField = new javax.swing.JTextField();
        inputQuantityField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        sidebar1 = new projek.kelompok.apprentaldvd.view.component.Sidebar();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1140, 720));
        setPreferredSize(new java.awt.Dimension(1200, 740));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("NIK");

        javax.swing.GroupLayout buttonBg1Layout = new javax.swing.GroupLayout(buttonBg1);
        buttonBg1.setLayout(buttonBg1Layout);
        buttonBg1Layout.setHorizontalGroup(
            buttonBg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        buttonBg1Layout.setVerticalGroup(
            buttonBg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonBg1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(buttonBg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, 110, 40));

        inputLamaSewaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputLamaSewaFieldActionPerformed(evt);
            }
        });
        getContentPane().add(inputLamaSewaField, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 480, 310, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Nama");

        javax.swing.GroupLayout buttonBg2Layout = new javax.swing.GroupLayout(buttonBg2);
        buttonBg2.setLayout(buttonBg2Layout);
        buttonBg2Layout.setHorizontalGroup(
            buttonBg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        buttonBg2Layout.setVerticalGroup(
            buttonBg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonBg2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(buttonBg2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, -1, -1));

        inputNamaCustField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputNamaCustField1ActionPerformed(evt);
            }
        });
        getContentPane().add(inputNamaCustField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 310, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Alamat");

        javax.swing.GroupLayout buttonBg3Layout = new javax.swing.GroupLayout(buttonBg3);
        buttonBg3.setLayout(buttonBg3Layout);
        buttonBg3Layout.setHorizontalGroup(
            buttonBg3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        buttonBg3Layout.setVerticalGroup(
            buttonBg3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonBg3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(buttonBg3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, -1, 40));

        inputAlamatField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputAlamatField1ActionPerformed(evt);
            }
        });
        getContentPane().add(inputAlamatField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 240, 310, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("No. Telp");

        javax.swing.GroupLayout buttonBg4Layout = new javax.swing.GroupLayout(buttonBg4);
        buttonBg4.setLayout(buttonBg4Layout);
        buttonBg4Layout.setHorizontalGroup(
            buttonBg4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        buttonBg4Layout.setVerticalGroup(
            buttonBg4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonBg4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(buttonBg4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, -1, -1));

        inputNoTelpFiedl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputNoTelpFiedlActionPerformed(evt);
            }
        });
        getContentPane().add(inputNoTelpFiedl, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, 310, 40));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Kode Film");

        javax.swing.GroupLayout buttonBg5Layout = new javax.swing.GroupLayout(buttonBg5);
        buttonBg5.setLayout(buttonBg5Layout);
        buttonBg5Layout.setHorizontalGroup(
            buttonBg5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        buttonBg5Layout.setVerticalGroup(
            buttonBg5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonBg5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(buttonBg5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 360, -1, -1));

        inputNikField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputNikField1ActionPerformed(evt);
            }
        });
        getContentPane().add(inputNikField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, 200, 40));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Quantity");

        javax.swing.GroupLayout buttonBg6Layout = new javax.swing.GroupLayout(buttonBg6);
        buttonBg6.setLayout(buttonBg6Layout);
        buttonBg6Layout.setHorizontalGroup(
            buttonBg6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        buttonBg6Layout.setVerticalGroup(
            buttonBg6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonBg6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(buttonBg6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 420, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Lama Sewa");

        javax.swing.GroupLayout buttonBg7Layout = new javax.swing.GroupLayout(buttonBg7);
        buttonBg7.setLayout(buttonBg7Layout);
        buttonBg7Layout.setHorizontalGroup(
            buttonBg7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        buttonBg7Layout.setVerticalGroup(
            buttonBg7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonBg7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(buttonBg7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 480, -1, -1));

        inputKodeFilmField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputKodeFilmFieldActionPerformed(evt);
            }
        });
        getContentPane().add(inputKodeFilmField, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 360, 310, 40));

        inputQuantityField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputQuantityFieldActionPerformed(evt);
            }
        });
        getContentPane().add(inputQuantityField, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 420, 310, 40));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Informasi Pemesanan");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 210, 30));

        jButton2.setText("Proses Pembayaran");
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 570, 150, 70));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode", "Nama Cust", "Kode DVD", "Quantity", "Lama Sewa", "Harga"
            }
        ));
        jTable1.setOpaque(false);
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 110, 440, 200));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Detail Pesanan");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 60, 250, 40));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Informasi DVD");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 330, 180, 40));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode", "Judul", "Kategori", "Quantity", "Harga"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(722, 380, 440, 330));

        jButton3.setText("Cari NIK");
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(603, 120, 100, 40));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setText("Tambah Film");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(682, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(571, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 970, 710));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("DASHBOARD");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("ADMIN");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("PEMESANAN");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("PRODUK DVD");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon("D:\\kuliah\\sem_3\\pemrograman1\\aplikasi_rental_dvd\\src\\main\\resources\\home_icon.png")); // NOI18N

        jLabel17.setIcon(new javax.swing.ImageIcon("D:\\kuliah\\sem_3\\pemrograman1\\aplikasi_rental_dvd\\src\\main\\resources\\icon_pemesanan.png")); // NOI18N

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_kaset.png"))); // NOI18N

        jLabel20.setIcon(new javax.swing.ImageIcon("D:\\kuliah\\sem_3\\pemrograman1\\aplikasi_rental_dvd\\src\\main\\resources\\icon_admin.png")); // NOI18N

        javax.swing.GroupLayout sidebar1Layout = new javax.swing.GroupLayout(sidebar1);
        sidebar1.setLayout(sidebar1Layout);
        sidebar1Layout.setHorizontalGroup(
            sidebar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebar1Layout.createSequentialGroup()
                .addGroup(sidebar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator4)
                    .addComponent(jSeparator5))
                .addContainerGap())
            .addGroup(sidebar1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidebar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidebar1Layout.createSequentialGroup()
                        .addGroup(sidebar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sidebar1Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(sidebar1Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidebar1Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidebar1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(sidebar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(sidebar1Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jLabel20)
                    .addContainerGap(161, Short.MAX_VALUE)))
        );
        sidebar1Layout.setVerticalGroup(
            sidebar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebar1Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addGroup(sidebar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(sidebar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(sidebar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
            .addGroup(sidebar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidebar1Layout.createSequentialGroup()
                    .addContainerGap(510, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(184, 184, 184)))
        );

        getContentPane().add(sidebar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 220, 740));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputLamaSewaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputLamaSewaFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputLamaSewaFieldActionPerformed

    private void inputNamaCustField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputNamaCustField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputNamaCustField1ActionPerformed

    private void inputAlamatField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputAlamatField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputAlamatField1ActionPerformed

    private void inputNoTelpFiedlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputNoTelpFiedlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputNoTelpFiedlActionPerformed

    private void inputNikField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputNikField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputNikField1ActionPerformed

    private void inputKodeFilmFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputKodeFilmFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputKodeFilmFieldActionPerformed

    private void inputQuantityFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputQuantityFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputQuantityFieldActionPerformed

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
            java.util.logging.Logger.getLogger(PemesananDvd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PemesananDvd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PemesananDvd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PemesananDvd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PemesananDvd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private projek.kelompok.apprentaldvd.view.component.ButtonBg buttonBg1;
    private projek.kelompok.apprentaldvd.view.component.ButtonBg buttonBg2;
    private projek.kelompok.apprentaldvd.view.component.ButtonBg buttonBg3;
    private projek.kelompok.apprentaldvd.view.component.ButtonBg buttonBg4;
    private projek.kelompok.apprentaldvd.view.component.ButtonBg buttonBg5;
    private projek.kelompok.apprentaldvd.view.component.ButtonBg buttonBg6;
    private projek.kelompok.apprentaldvd.view.component.ButtonBg buttonBg7;
    private javax.swing.JTextField inputAlamatField1;
    private javax.swing.JTextField inputKodeFilmField;
    private javax.swing.JTextField inputLamaSewaField;
    private javax.swing.JTextField inputNamaCustField1;
    private javax.swing.JTextField inputNikField1;
    private javax.swing.JTextField inputNoTelpFiedl;
    private javax.swing.JTextField inputQuantityField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private projek.kelompok.apprentaldvd.view.component.Sidebar sidebar1;
    // End of variables declaration//GEN-END:variables
}
