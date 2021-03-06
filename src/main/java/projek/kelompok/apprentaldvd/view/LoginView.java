/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projek.kelompok.apprentaldvd.view;

import com.sun.awt.AWTUtilities;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import projek.kelompok.apprentaldvd.service.AdminService;
import projek.kelompok.apprentaldvd.service.KoneksiFactory;
import projek.kelompok.apprentaldvd.model.Admin;

public class LoginView extends javax.swing.JFrame {
    private String saveId, savePassword;
    private final JFrame frame = this;
    private AdminService service;
    
    public LoginView() {
        // inisialsi admin service + kasih koneksi database
        service = new AdminService(new KoneksiFactory().getConn());
        
        initComponents();
        authentication();
        
        
        closeOperation();
        /**
         * setting jframe untuk bikin ketengah dan exit ketika diclose
         */
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    public void authentication(){
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                /**
                 * listener untuk check id dan password
                 */
                saveId = inputId.getText();
                savePassword = String.valueOf(inputPassword.getPassword()); 
                
                idNoInput.setText("");
                passNoInput.setText("");
                wrongId.setText("");
                wrongPass.setText("");
                
                Admin admin = null;
                try {
                    if(!saveId.isEmpty()) {
                        admin = service.getOneAdmin(saveId); 
                        if(admin.getPassword().equals(savePassword)) {
                            // jika id dan password berhasil
                            // proses panggil frame home / dashboard
                            HomeView home = new HomeView(admin.getId());
                            //home.setIdAdmin(saveId);
                            // JFrame dashboard = home;
                            home.setVisible(true);
                            frame.setVisible(false);
                        } else { 
                            wrongPass.setText("password salah");
                        }
                    }
                } catch(Exception ex) {    
                    idNoInput.setText("");
                    wrongId.setText("id salah");
                }

                if(saveId.isEmpty()) {
                    wrongId.setText("");
                    idNoInput.setText("id belum di masukan");
                    
                }
                
                if(savePassword.isEmpty()) {
                    wrongPass.setText("");
                    passNoInput.setText("password belum dimasukan");
                }
            }
        });
    }
    
    /**
     * method untuk close windows
     */
    public void closeOperation(){
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
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

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        login = new javax.swing.JLabel();
        password = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        inputPassword = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        loginButton = new javax.swing.JLabel();
        inputId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        close = new javax.swing.JLabel();
        wrongPass = new javax.swing.JLabel();
        wrongId = new javax.swing.JLabel();
        passNoInput = new javax.swing.JLabel();
        idNoInput = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon("D:\\kuliah\\sem_3\\pemrograman1\\aplikasi_rental_dvd\\src\\main\\resources\\login2.png")); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, -40, 410, 590));

        login.setFont(new java.awt.Font("Century Gothic", 1, 26)); // NOI18N
        login.setForeground(new java.awt.Color(255, 255, 0));
        login.setText("Login");
        jPanel1.add(login, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, 80, 40));

        password.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        password.setForeground(new java.awt.Color(255, 255, 255));
        password.setText("Password");
        jPanel1.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 290, 100, 30));

        id.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        id.setForeground(new java.awt.Color(255, 255, 255));
        id.setText("Id");
        jPanel1.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 190, 100, 30));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 270, 310, 10));

        inputPassword.setBackground(new java.awt.Color(20, 129, 194));
        inputPassword.setForeground(new java.awt.Color(255, 255, 255));
        inputPassword.setBorder(null);
        inputPassword.setOpaque(false);
        inputPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputPasswordActionPerformed(evt);
            }
        });
        jPanel1.add(inputPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 330, 200, 30));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 370, 320, 10));

        loginButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginButton.setIcon(new javax.swing.ImageIcon("D:\\kuliah\\sem_3\\pemrograman1\\aplikasi_rental_dvd\\src\\main\\resources\\next2.png")); // NOI18N
        jPanel1.add(loginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 380, 220, 150));

        inputId.setBackground(new java.awt.Color(20, 129, 194));
        inputId.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        inputId.setForeground(new java.awt.Color(255, 255, 255));
        inputId.setBorder(null);
        inputId.setOpaque(false);
        inputId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputIdActionPerformed(evt);
            }
        });
        jPanel1.add(inputId, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 230, 200, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Aplikasi Rental DVD");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, 150, 20));

        close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        close.setIcon(new javax.swing.ImageIcon("D:\\kuliah\\sem_3\\pemrograman1\\aplikasi_rental_dvd\\src\\main\\resources\\close.png")); // NOI18N
        jPanel1.add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 60, 50));

        wrongPass.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        wrongPass.setForeground(new java.awt.Color(255, 0, 0));
        wrongPass.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel1.add(wrongPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 330, 110, 30));

        wrongId.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        wrongId.setForeground(new java.awt.Color(255, 0, 51));
        jPanel1.add(wrongId, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 230, 70, 30));

        passNoInput.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        passNoInput.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(passNoInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 290, 210, 30));

        idNoInput.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        idNoInput.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(idNoInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 190, 210, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("* Klik tombol disamping untuk login");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 420, 200, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon("D:\\kuliah\\sem_3\\pemrograman1\\aplikasi_rental_dvd\\src\\main\\resources\\loginbg2.jpg")); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 410, 630));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputPasswordActionPerformed

    private void inputIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputIdActionPerformed

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
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               new LoginView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel close;
    private javax.swing.JLabel id;
    private javax.swing.JLabel idNoInput;
    private javax.swing.JTextField inputId;
    private javax.swing.JPasswordField inputPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel login;
    private javax.swing.JLabel loginButton;
    private javax.swing.JLabel passNoInput;
    private javax.swing.JLabel password;
    private javax.swing.JLabel wrongId;
    private javax.swing.JLabel wrongPass;
    // End of variables declaration//GEN-END:variables
}
