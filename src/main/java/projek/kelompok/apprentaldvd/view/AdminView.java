/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projek.kelompok.apprentaldvd.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import projek.kelompok.apprentaldvd.service.AdminService;
import projek.kelompok.apprentaldvd.service.KoneksiFactory;
import projek.kelompok.apprentaldvd.model.Admin;
import projek.kelompok.apprentaldvd.view.component.GantiPassword;

    /**
     * FRAME INI TIDAK BISA LANGSUNG DIJALANKAN, KARENA METHOD MAIN DIBAWAH TIDAK MEMANGGIL FRAME INI
     * HARAP TIDAK DIGANTI, KARENA ADA PROSES TRASNFER ID ADMIN DARI PROSES LOGIN
     * JIKA INGIN RUNNING LEWAT APLIKASI UTAMA FRAME, ATAU LOGINVIEW FRAME
     */
public class AdminView extends javax.swing.JFrame {
    private AdminService service;
    private JFrame frame = this;
    private String idAdmin;
    
    public AdminView(String idAdmin) {
        initComponents();
        menuClicked();
        gantiPasswordClicked();
        
        /**
         * setting jframe untuk bikin ketengah dan exit ketika diclose
         */
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.idAdmin = idAdmin;
        service = new AdminService(new KoneksiFactory().getConn());
        
        // tampilkan nama admin
        Admin admin = service.getOneAdmin(idAdmin);
        labelNama.setText(admin.getNama());
        labelJamMasuk.setText(admin.getMasukJam());
        labelJamAkhir.setText(admin.getSampaiJam());
        
        buttonSimpanInfoClicked(admin);
        simpanAdminBaruClicked();
    }
    
    public void gantiInfoAdmin(Admin admin1){
        String nama = gantiNamaField.getText();
        String alamat = gantiAlamatField.getText();
        String noTelp = gantiNoTelpField.getText();
        
        Admin admin = new Admin(admin1.getId(), nama, alamat, admin1.getPassword(), noTelp, admin1.getMasukJam(), admin1.getSampaiJam());
        int status = service.updateAdmin(admin);
        
        if(status == 1) {
            JOptionPane.showMessageDialog(this, "update admin berhasil");
            labelNama.setText(admin.getNama());
            labelJamMasuk.setText(admin.getMasukJam());
            labelJamAkhir.setText(admin.getSampaiJam());
            
        } else {
            JOptionPane.showMessageDialog(this, "update admin gagal");
        }
        
        
        
    }

    public void buttonSimpanInfoClicked(Admin admin){
        simpanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                if(!gantiNamaField.getText().isEmpty() && !gantiAlamatField.getText().isEmpty() && 
                        !gantiNoTelpField.getText().isEmpty()) {
                    gantiInfoAdmin(admin);
                } else {
                    JOptionPane.showMessageDialog(frame, "anda belum memasukan data atau ada field yg masih kosong");
                }
            }
        });
    }
    
    public void tambahAdmin(){
        String nama = namaField.getText();
        String alamat = alamatField.getText();
        String noTelp = noTelField.getText();
        String id = idField.getText();
        String password = passwodField.getText();
        String masuk = masukJamField.getText();
        String sampai = sampaiJamField.getText();
        
        // bikin admin baru
        Admin adminBaru = new Admin(id, nama, alamat, password, noTelp, masuk, sampai);
        int status = service.tambahAdmin(adminBaru);
        if(status == 1) {
            JOptionPane.showMessageDialog(this, "Tambah admin berhasil");
        } else {
            JOptionPane.showMessageDialog(this, "tambah admin gagal, id admin sudah ada yg punya");
        }
    }
    
    public void simpanAdminBaruClicked(){
        simpanBaruButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                if(!namaField.getText().isEmpty() && !alamatField.getText().isEmpty() && !noTelField.getText().isEmpty()
                        && !idField.getText().isEmpty() && !passwodField.getText().isEmpty() && !masukJamField.getText().isEmpty()
                        && !sampaiJamField.getText().isEmpty()
                        ){
                    // isi kode
                    tambahAdmin();
                } else {
                    JOptionPane.showMessageDialog(frame, "Field Admin baru harap diisi semuanya");
                }          
            }
        });
    }
    
    public void gantiPasswordClicked(){
        gantiPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFrame framePw = new GantiPassword(idAdmin);
                framePw.setVisible(true);
                frame.setVisible(false);
            }
        });
    }
    
    public void menuClicked(){
         // dashboard clicked
         dashboardMenu.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent me) {
                 JFrame frame2 = new HomeView(idAdmin);
                 frame.setVisible(false);
                 frame2.setVisible(true);
             }
         });
         
         // pemesanan menu clicked
         pemesananMenu.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent me) {
                 JFrame frame2 = new PemesananDvd(idAdmin);
                 frame.setVisible(false);
                 frame2.setVisible(true);
             }
         });
         
         
         // list dvd menu clicked
         dvdMenu.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent me) {
                 JFrame frame2 = new ProdukDvd(idAdmin);
                 frame.setVisible(false);
                 frame2.setVisible(true);
             }
         });
     }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        sidebar1 = new projek.kelompok.apprentaldvd.view.component.Sidebar();
        dashboardMenu = new javax.swing.JLabel();
        adminMenu = new javax.swing.JLabel();
        pemesananMenu = new javax.swing.JLabel();
        dvdMenu = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        gantiNoTelpField = new javax.swing.JTextField();
        sampaiJamField = new javax.swing.JTextField();
        gantiAlamatField = new javax.swing.JTextField();
        gantiPasswordButton = new javax.swing.JButton();
        simpanButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        gantiNamaField = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        namaField = new javax.swing.JTextField();
        alamatField = new javax.swing.JTextField();
        noTelField = new javax.swing.JTextField();
        idField = new javax.swing.JTextField();
        simpanBaruButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        dashbordItem32 = new projek.kelompok.apprentaldvd.view.component.DashbordItem3();
        jLabel4 = new javax.swing.JLabel();
        labelJamAkhir = new javax.swing.JLabel();
        logoDvd1 = new javax.swing.JLabel();
        dashbordItem22 = new projek.kelompok.apprentaldvd.view.component.DashbordItem2();
        jLabel2 = new javax.swing.JLabel();
        logoDvd = new javax.swing.JLabel();
        labelJamMasuk = new javax.swing.JLabel();
        dashbordItem1 = new projek.kelompok.apprentaldvd.view.component.DashbordItem();
        judulProfit = new javax.swing.JLabel();
        logoBabi = new javax.swing.JLabel();
        labelNama = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        masukJamField = new javax.swing.JTextField();
        passwodField = new javax.swing.JPasswordField();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dashboardMenu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dashboardMenu.setForeground(new java.awt.Color(255, 255, 255));
        dashboardMenu.setText("DASHBOARD");

        adminMenu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        adminMenu.setForeground(new java.awt.Color(255, 0, 0));
        adminMenu.setText("ADMIN");

        pemesananMenu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pemesananMenu.setForeground(new java.awt.Color(255, 255, 255));
        pemesananMenu.setText("PEMESANAN");

        dvdMenu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dvdMenu.setForeground(new java.awt.Color(255, 255, 255));
        dvdMenu.setText("PRODUK DVD");

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
                .addContainerGap()
                .addGroup(sidebar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidebar1Layout.createSequentialGroup()
                        .addGroup(sidebar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sidebar1Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dashboardMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(sidebar1Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pemesananMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(sidebar1Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(adminMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidebar1Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dvdMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(24, 24, 24))))
            .addGroup(sidebar1Layout.createSequentialGroup()
                .addGroup(sidebar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator4)
                    .addComponent(jSeparator5))
                .addContainerGap())
            .addGroup(sidebar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(sidebar1Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jLabel20)
                    .addContainerGap(161, Short.MAX_VALUE)))
        );
        sidebar1Layout.setVerticalGroup(
            sidebar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebar1Layout.createSequentialGroup()
                .addGroup(sidebar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidebar1Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sidebar1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(dashboardMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(sidebar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidebar1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sidebar1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pemesananMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(sidebar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidebar1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sidebar1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(dvdMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(adminMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
            .addGroup(sidebar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidebar1Layout.createSequentialGroup()
                    .addContainerGap(510, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(184, 184, 184)))
        );

        getContentPane().add(sidebar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 220, 740));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 204, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Nama");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 70, 40));

        jPanel3.setBackground(new java.awt.Color(0, 204, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Alamat");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, -1, -1));

        jPanel4.setBackground(new java.awt.Color(0, 204, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("No. Telp");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, -1, -1));
        jPanel1.add(gantiNoTelpField, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 450, 240, 40));
        jPanel1.add(sampaiJamField, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 620, 130, 40));
        jPanel1.add(gantiAlamatField, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 240, 40));

        gantiPasswordButton.setText("Ganti Password");
        jPanel1.add(gantiPasswordButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 520, -1, 50));

        simpanButton.setText("Simpan");
        jPanel1.add(simpanButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 520, 110, 50));

        jPanel5.setBackground(new java.awt.Color(0, 204, 255));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Nama");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 320, -1, -1));
        jPanel1.add(gantiNamaField, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, 240, 40));

        jPanel6.setBackground(new java.awt.Color(0, 204, 255));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Alamat");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 380, -1, -1));

        jPanel7.setBackground(new java.awt.Color(0, 204, 255));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("No. Telp");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 440, -1, -1));

        jPanel8.setBackground(new java.awt.Color(0, 204, 255));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Id Login");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 500, -1, -1));

        jPanel9.setBackground(new java.awt.Color(0, 204, 255));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Passwod");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 560, -1, -1));
        jPanel1.add(namaField, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 320, 240, 40));
        jPanel1.add(alamatField, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 380, 240, 40));
        jPanel1.add(noTelField, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 440, 240, 40));

        idField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idFieldActionPerformed(evt);
            }
        });
        jPanel1.add(idField, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 500, 240, 40));

        simpanBaruButton.setText("Simpan");
        jPanel1.add(simpanBaruButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 320, 90, 50));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 292, 840, 10));

        jLabel19.setIcon(new javax.swing.ImageIcon("D:\\kuliah\\sem_3\\pemrograman1\\aplikasi_rental_dvd\\src\\main\\resources\\icon_admin.png")); // NOI18N
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, 46));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setText("Admin ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, -1, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Sampai Jam");

        labelJamAkhir.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelJamAkhir.setForeground(new java.awt.Color(255, 255, 255));
        labelJamAkhir.setText("17.00");

        logoDvd1.setIcon(new javax.swing.ImageIcon("D:\\kuliah\\sem_3\\pemrograman1\\aplikasi_rental_dvd\\src\\main\\resources\\jam.png")); // NOI18N

        javax.swing.GroupLayout dashbordItem32Layout = new javax.swing.GroupLayout(dashbordItem32);
        dashbordItem32.setLayout(dashbordItem32Layout);
        dashbordItem32Layout.setHorizontalGroup(
            dashbordItem32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashbordItem32Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(dashbordItem32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(dashbordItem32Layout.createSequentialGroup()
                        .addComponent(labelJamAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 41, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(dashbordItem32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logoDvd1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dashbordItem32Layout.setVerticalGroup(
            dashbordItem32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashbordItem32Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(logoDvd1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelJamAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel1.add(dashbordItem32, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 80, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Masuk Jam");

        logoDvd.setIcon(new javax.swing.ImageIcon("D:\\kuliah\\sem_3\\pemrograman1\\aplikasi_rental_dvd\\src\\main\\resources\\jam.png")); // NOI18N

        labelJamMasuk.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelJamMasuk.setForeground(new java.awt.Color(255, 255, 255));
        labelJamMasuk.setText("07.00");

        javax.swing.GroupLayout dashbordItem22Layout = new javax.swing.GroupLayout(dashbordItem22);
        dashbordItem22.setLayout(dashbordItem22Layout);
        dashbordItem22Layout.setHorizontalGroup(
            dashbordItem22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashbordItem22Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(dashbordItem22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelJamMasuk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(dashbordItem22Layout.createSequentialGroup()
                        .addGroup(dashbordItem22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(logoDvd)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 120, Short.MAX_VALUE)))
                .addContainerGap())
        );
        dashbordItem22Layout.setVerticalGroup(
            dashbordItem22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashbordItem22Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(logoDvd, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelJamMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jPanel1.add(dashbordItem22, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, -1, -1));

        judulProfit.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        judulProfit.setForeground(new java.awt.Color(255, 255, 255));
        judulProfit.setText("Nama Admin");

        logoBabi.setIcon(new javax.swing.ImageIcon("D:\\kuliah\\sem_3\\pemrograman1\\aplikasi_rental_dvd\\src\\main\\resources\\admin_putih.png")); // NOI18N

        labelNama.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelNama.setForeground(new java.awt.Color(255, 255, 255));
        labelNama.setText("Joko Santoso");

        javax.swing.GroupLayout dashbordItem1Layout = new javax.swing.GroupLayout(dashbordItem1);
        dashbordItem1.setLayout(dashbordItem1Layout);
        dashbordItem1Layout.setHorizontalGroup(
            dashbordItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashbordItem1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(dashbordItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(dashbordItem1Layout.createSequentialGroup()
                        .addGroup(dashbordItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(judulProfit, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(logoBabi, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(79, Short.MAX_VALUE))))
        );
        dashbordItem1Layout.setVerticalGroup(
            dashbordItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashbordItem1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(logoBabi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(judulProfit, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelNama, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel1.add(dashbordItem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        jPanel10.setBackground(new java.awt.Color(0, 204, 255));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Masuk");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 620, -1, -1));

        jPanel11.setBackground(new java.awt.Color(0, 204, 255));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Sampai");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 620, -1, -1));
        jPanel1.add(masukJamField, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 620, 130, 40));
        jPanel1.add(passwodField, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 560, 240, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 920, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void idFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idFieldActionPerformed

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
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // tidak dipanggil, karena konstruktor mengandung data dari login
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adminMenu;
    private javax.swing.JTextField alamatField;
    private javax.swing.JLabel dashboardMenu;
    private projek.kelompok.apprentaldvd.view.component.DashbordItem dashbordItem1;
    private projek.kelompok.apprentaldvd.view.component.DashbordItem2 dashbordItem22;
    private projek.kelompok.apprentaldvd.view.component.DashbordItem3 dashbordItem32;
    private javax.swing.JLabel dvdMenu;
    private javax.swing.JTextField gantiAlamatField;
    private javax.swing.JTextField gantiNamaField;
    private javax.swing.JTextField gantiNoTelpField;
    private javax.swing.JButton gantiPasswordButton;
    private javax.swing.JTextField idField;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel judulProfit;
    private javax.swing.JLabel labelJamAkhir;
    private javax.swing.JLabel labelJamMasuk;
    private javax.swing.JLabel labelNama;
    private javax.swing.JLabel logoBabi;
    private javax.swing.JLabel logoDvd;
    private javax.swing.JLabel logoDvd1;
    private javax.swing.JTextField masukJamField;
    private javax.swing.JTextField namaField;
    private javax.swing.JTextField noTelField;
    private javax.swing.JPasswordField passwodField;
    private javax.swing.JLabel pemesananMenu;
    private javax.swing.JTextField sampaiJamField;
    private projek.kelompok.apprentaldvd.view.component.Sidebar sidebar1;
    private javax.swing.JButton simpanBaruButton;
    private javax.swing.JButton simpanButton;
    // End of variables declaration//GEN-END:variables
}
