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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projek.kelompok.apprentaldvd.model.Dvd;
import projek.kelompok.apprentaldvd.model.Pemesanan;

/**
 *
 * @author Feri Winarta
 */
public class PemesananService {
    private Connection conn;
    private Pemesanan pemesanan;
    private ProdukDvdService dvdService;
    private CustomerService customerService;

    public PemesananService(Connection conn) {
        this.conn = conn;
        if(conn == null) {
            System.err.println("koneksi gagal di kelas " + getClass().getSimpleName());
        }
    }
    
    public int insertPemesanan(Pemesanan pemesanan){
        int status = 100;
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO pemesanan_detail (nik, kode_dvd, quantity, lama_sewa) values (?,?,?,?)"
            );
            
            ps.setString(1, pemesanan.getNik());
            ps.setString(2, pemesanan.getKode());
            ps.setInt(3, pemesanan.getQuantity());
            ps.setInt(4, pemesanan.getLamaSewa());
            
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProdukDvdService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    public List<Pemesanan> getSemuaPemesanan() {
        
            List<Pemesanan> semuaPemesanan = new ArrayList<>();
            ResultSet set;
            try {
            set = conn.createStatement().executeQuery("SELECT * FROM pemesanan_detail");
            
            while(set.next()) {
                String kode_detail = set.getString("kode_detail");
                String nik = set.getString("nik");
                String kodeDvd = set.getString("kode_dvd");
                int quantity = set.getInt("quantity");
                int lamaSewa = set.getInt("lama_sewa");
                
                Pemesanan detail = new Pemesanan(nik, kodeDvd, quantity, lamaSewa);
                // menyimpan dvd ke dalam bentuk list
                semuaPemesanan.add(detail);
            }    
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }   
        return semuaPemesanan;
    }
    
    
    
}
