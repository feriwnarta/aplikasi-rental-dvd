/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projek.kelompok.apprentaldvd.view;

import java.awt.Button;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import projek.kelompok.apprentaldvd.controlller.service.KoneksiFactory;
import projek.kelompok.apprentaldvd.controlller.service.ProdukDvdService;
import projek.kelompok.apprentaldvd.model.Dvd;
import projek.kelompok.apprentaldvd.view.component.DeleteDvd;
import projek.kelompok.apprentaldvd.view.component.TambahDvd;
import projek.kelompok.apprentaldvd.view.component.UpdateDvd;

/**
 *
 * @author Feri Winarta
 */
public class ProdukDvd extends javax.swing.JFrame {
    private ProdukDvdService service;
    private JFrame frame = this;
    private String idAdmin;
    
    public ProdukDvd(String idAdmin) {
        initComponents();
        
        this.idAdmin = idAdmin;
        /**
         * setting jframe untuk bikin ketengah dan exit ketika diclose
         */
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        // untuk menengahkan layar
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        service = new ProdukDvdService(new KoneksiFactory().getConn());
        menuClicked();
        
        // tampilkan dvd ke dalam table
        DefaultTableModel dtf = (DefaultTableModel) tableDvd.getModel();
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
        
        tambahDvd();
        JFrame frameUtama = this;
        
        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFrame frame = new TambahDvd(idAdmin);
                frameUtama.setVisible(false);
                frame.setVisible(true);
            }
        });
        
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFrame frame = new UpdateDvd(idAdmin);
                frameUtama.setVisible(false);
                frame.setVisible(true);
            }
        });
    
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFrame frame = new DeleteDvd(idAdmin);
                frameUtama.setVisible(false);
                frame.setVisible(true);
            }
        });
        
    }
    
    public void tambahDvd() {
        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
            }
        });
    }
    
    public void updateDvd() {
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
            }
        });
    }
    
    public void deleteDvd() {
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
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
         
         
         // list dvd menu clicked
         dvdMenu.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent me) {
                 JFrame frame2 = new ProdukDvd(idAdmin);
                 frame.setVisible(false);
                 frame2.setVisible(true);
             }
         });
         
         // admin menu clicked
         
         adminMenu.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent me) {
                 
                 JFrame frame2 = new AdminView(idAdmin);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tableDvd = new javax.swing.JTable();
        dashbordItem2 = new projek.kelompok.apprentaldvd.view.component.DashbordItem();
        jLabel1 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableDvd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode", "Judul", "Kategori", "Jumlah", "Harga"
            }
        ));
        jScrollPane1.setViewportView(tableDvd);
        if (tableDvd.getColumnModel().getColumnCount() > 0) {
            tableDvd.getColumnModel().getColumn(0).setPreferredWidth(30);
            tableDvd.getColumnModel().getColumn(3).setPreferredWidth(40);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 920, 550));

        dashbordItem2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LIST DVD");
        dashbordItem2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 31, -1, -1));

        btnDelete.setText("Delete");
        dashbordItem2.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, -1, 56));

        btnTambah.setText("Tambah");
        dashbordItem2.add(btnTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 41, -1, 56));

        btnUpdate.setText("Update");
        dashbordItem2.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 40, -1, 56));

        getContentPane().add(dashbordItem2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, -10, 930, 120));

        dashboardMenu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dashboardMenu.setForeground(new java.awt.Color(255, 255, 255));
        dashboardMenu.setText("DASHBOARD");

        adminMenu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        adminMenu.setForeground(new java.awt.Color(255, 255, 255));
        adminMenu.setText("ADMIN");

        pemesananMenu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pemesananMenu.setForeground(new java.awt.Color(255, 255, 255));
        pemesananMenu.setText("PEMESANAN");

        dvdMenu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dvdMenu.setForeground(new java.awt.Color(255, 0, 0));
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
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidebar1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dashboardMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(sidebar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidebar1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sidebar1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pemesananMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(sidebar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidebar1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sidebar1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dvdMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(adminMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(ProdukDvd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProdukDvd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProdukDvd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProdukDvd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               // tidak bisa dirun
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adminMenu;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel dashboardMenu;
    private projek.kelompok.apprentaldvd.view.component.DashbordItem dashbordItem2;
    private javax.swing.JLabel dvdMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel pemesananMenu;
    private projek.kelompok.apprentaldvd.view.component.Sidebar sidebar1;
    private javax.swing.JTable tableDvd;
    // End of variables declaration//GEN-END:variables
}
