/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projek.kelompok.apprentaldvd.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class KoneksiFactory {
    // jdbc:mysql://127.0.0.1:33061 /aplikasi_penyewaan_dvd", "root", ""
    private final String DBURL = "jdbc:mysql://127.0.0.1:33061 /aplikasi_penyewaan_dvd";
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    private Connection conn = null;

    public KoneksiFactory() {
        konekDatabase();
    }
    
    public void konekDatabase(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                conn = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
            } catch (SQLException ex) {
                System.err.println("koneksi gagal di " + getClass().getSimpleName());
                System.err.println(ex.getMessage());
            }} catch (ClassNotFoundException ex) {
               System.err.println("driver class name tidak ketemu di " + getClass().getSimpleName());
               System.err.println(ex.getMessage());
            }
    }

    public Connection getConn() {
        return conn;
    }
}
