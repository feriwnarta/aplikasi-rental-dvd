/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projek.kelompok.apprentaldvd.controlller.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projek.kelompok.apprentaldvd.model.Dvd;
import sun.util.logging.resources.logging;

/**
 *
 * @author Feri Winarta
 */
public class ProdukDvdService {
    private Connection conn;
    private List<Dvd> listSemuaDvd;
    private ResultSetMetaData rsMeta;
    
    public ProdukDvdService(Connection conn) {
        this.conn = conn;
        if(conn == null) {
            System.err.println("koneksi gagal di kelas " + getClass().getSimpleName());
        }
    }
    
    public List<Dvd> getSemuaDvd(){
        listSemuaDvd = new ArrayList<>();
        ResultSet set = null;
        
        try {
            /**
             * result set mengambil data dvd dari database
             */
            set = conn.createStatement().executeQuery("SELECT * FROM dvd");
            rsMeta = set.getMetaData();
            
            /**
             * while dibawah akan menyimpan data dari database ke dalam bentuk list dvd
             */
            while(set.next()) {
                String kodeDvd = set.getString("kode_dvd");
                String judulFilm = set.getString("judul_film");
                String kategori = set.getString("kategori");
                int quantity = set.getInt("quantity");
                BigDecimal harga = set.getBigDecimal("harga");
                
                Dvd dvd = new Dvd(kodeDvd, judulFilm, kategori, quantity, harga);
                // menyimpan dvd ke dalam bentuk list
                listSemuaDvd.add(dvd);
            }    
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }   
        return listSemuaDvd;
    }

    public ResultSetMetaData getRsMeta() {
        return rsMeta;
    }
    
    public Dvd getSatuDvd(String kode) {
        ResultSet set;
        Dvd dvd = null;
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM dvd WHERE kode_dvd = ?"
            );
            ps.setString(1, kode);
            set = ps.executeQuery();   
            while(set.next()) {
                dvd = new Dvd(
                        set.getString("kode_dvd"), set.getString("judul_film"),set.getString("kategori"), set.getInt("quantity"), set.getBigDecimal("harga")
                );
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return dvd;
    }
    
    public int insertDvd(Dvd dvd){
        int status = 100;
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO dvd (kode_dvd, judul_film, kategori,quantity,  harga) values (?,?,?,?,?)"
            );
            
            ps.setString(1, dvd.getKode());
            ps.setString(2, dvd.getJudulFilm());
            ps.setString(3, dvd.getKategori());
            ps.setInt(4, dvd.getQuantity());
            ps.setBigDecimal(5, dvd.getHarga());
            
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProdukDvdService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    public int updateDvd(Dvd dvd) {
        int status = 100;
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE dvd SET judul_film = ?, kategori = ?, quantity = ?, harga = ? WHERE kode_dvd = ?"
            );
            
            ps.setString(1, dvd.getJudulFilm());
            ps.setString(2, dvd.getKategori());
            ps.setInt(3, dvd.getQuantity());
            ps.setBigDecimal(4, dvd.getHarga());
            ps.setString(5, dvd.getKode());
            
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return status;
    }
    
    public int deleteDvd(Dvd dvd) {
        int status = 100;
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM dvd WHERE kode_dvd = ?"
            );
            ps.setString(1, dvd.getKode());
            
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return status;
    }
    
}
