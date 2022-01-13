/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projek.kelompok.apprentaldvd.controlller.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projek.kelompok.apprentaldvd.model.Admin;
import projek.kelompok.apprentaldvd.model.Dvd;

/**
 *
 * @author Feri Winarta
 */
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
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM admin WHERE id = ? AND status = 1"
            );
            
            ps.setString(1, id);
            
            ResultSet set = ps.executeQuery();
            
            while(set.next()) {
                String nama = set.getString("nama");
                String alamat = set.getString("alamat");
                String noTelp = set.getString("no_telpon");
                String masukJam = set.getString("masuk_jam");
                String sampaiJam = set.getString("sampai_jam");
                admin = new Admin(nama, alamat, noTelp, masukJam, sampaiJam);
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
                    "UPDATE admin SET nama = ?, alamat = ?, no_telpon = ? WHERE id = ? && status = 1"
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
                    "UPDATE admin SET password = ? WHERE id = ? && status = 1"
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
