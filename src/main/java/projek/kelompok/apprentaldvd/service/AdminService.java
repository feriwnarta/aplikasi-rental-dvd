/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projek.kelompok.apprentaldvd.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projek.kelompok.apprentaldvd.model.Admin;
import projek.kelompok.apprentaldvd.model.Dvd;


public class AdminService {
    private Connection conn;
    private Admin admin;

    public AdminService(Connection conn) {
        this.conn = conn;
        if(conn == null) {
            System.err.println("koneksi gagal di kelas " + getClass().getSimpleName());
        }
    }
    
    public Admin getOneAdmin(String id){
        ResultSet set;
        Admin admin = null;
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM admin WHERE id = ?"
            );
            ps.setString(1, id);
            set = ps.executeQuery();   
            while(set.next()) {
                admin = new Admin(set.getString("id"), set.getString("nama"), set.getString("alamat"), set.getString("password"), 
                        set.getString("no_telpon"), set.getString("masuk_jam"), set.getString("sampai_jam"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return admin;
    }
    
    public int updateAdmin(Admin admin) {
        int status = 100;
        // update data admin yang sedang aktif 
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE admin SET nama = ?, alamat = ?, no_telpon = ? WHERE id = ?"
            );
            
            ps.setString(1, admin.getNama());
            ps.setString(2, admin.getAlamat());
            ps.setString(3, admin.getNoTelp());
            ps.setString(4, admin.getId());
            
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return status;
    }
    
    public int gantiPasswordAdmin(Admin admin){
        int status = 100;
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE admin SET password = ? WHERE id = ?"
            );
            
            ps.setString(1, admin.getPassword());
            ps.setString(2, admin.getId());
            
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return status;
    }
    
    public int tambahAdmin(Admin admin) {
        int status = 100;
        // update data admin yang sedang aktif 
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO admin (id, nama, alamat, password, no_telpon, masuk_jam, sampai_jam) VALUES (?,?,?,?,?,?,?)"
            );
            
            ps.setString(1, admin.getId());
            ps.setString(2, admin.getNama());
            ps.setString(3, admin.getAlamat());
            ps.setString(4, admin.getPassword());
            ps.setString(5, admin.getNoTelp());
            ps.setString(6, admin.getMasukJam());
            ps.setString(7, admin.getSampaiJam());
            
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return status;
    }

    public Connection getConn() {
        return conn;
    }
    
}
