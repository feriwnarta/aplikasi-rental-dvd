/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projek.kelompok.apprentaldvd.controlller.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Feri Winarta
 */
public class KoneksiFactory {
    private String dbUrl;
    private String username;
    private String password;
    private Connection conn = null;
    
    public KoneksiFactory(String dbUrl, String username, String password) {
        this.dbUrl = dbUrl;
        this.username = username;
        this.password = password;
        konekDatabase();
    }
    
    public void konekDatabase(){
        try {
            conn = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException ex) {
            System.err.println("koneksi gagal di " + getClass().getSimpleName());
        }         
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    
    
}
