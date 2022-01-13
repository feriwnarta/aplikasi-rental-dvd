/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projek.kelompok.apprentaldvd;

import com.mysql.cj.protocol.Resultset;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Assertions;
import projek.kelompok.apprentaldvd.controlller.service.KoneksiFactory;
import projek.kelompok.apprentaldvd.controlller.service.ProdukDvdService;
import projek.kelompok.apprentaldvd.model.Dvd;

/**
 *
 * @author Feri Winarta
 */
public class TestDb2 {
    private KoneksiFactory koneksi;
    private ProdukDvdService service;
    
    @Before
    public void setUp() {
        koneksi = new KoneksiFactory();
        service = new ProdukDvdService(koneksi.getConn());
    }
    
    @Test
    public void testkoneksi() throws SQLException, ClassNotFoundException{  
        Assertions.assertNotNull(koneksi);
        ResultSet set = null;
        
        try {
            set = koneksi.getConn().createStatement().executeQuery("SELECT * FROM dvd");
            
            while(set.next()) {
                String kodeDvd = set.getString(1);
                String judulFilm= set.getString(2);
                String kategori = set.getString(3);
                int quantity = set.getInt(4);
                int harga = set.getInt(5);
                
                System.out.println("kode dvd = " + kodeDvd);
                System.out.println("judul = " + judulFilm);
                System.out.println("kategori = " + kategori);
                System.out.println("quantity = " + quantity);
                System.out.println("harga = " + harga);
            }    
        } catch (SQLException ex) {
            Logger.getLogger(TestDb2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetSemuaDvd(){
        List<Dvd> semuaDvd = service.getSemuaDvd();
        
        Assertions.assertNotNull(semuaDvd);
        for(Dvd result : semuaDvd) {
            System.out.println(result.getJudulFilm());
            System.out.println(result.getKategori());
            System.out.println(result.getQuantity());
            System.out.println(result.getHarga());
        }
    }
    
    //@Test
    public void testInsertDvd(){
       Dvd dvd = new Dvd("laskar plangi", "action comedy", 21, new BigDecimal(2000));
       int status = service.insertDvd(dvd);
       Assertions.assertEquals(1, status);
    }
    
    // @Test
    public void testUpdateDvd(){
        Dvd dvd = new Dvd("Laskar Pelangi", "Drama", 11, new BigDecimal(15000));
        dvd.setKode("K0011");
        int status = service.updateDvd(dvd);
        Assertions.assertEquals(1, status);
    }
    
    // @Test
    public void testDeleteDvd(){
        Dvd dvd = new Dvd("Laskar Pelangi", "Drama", 11, new BigDecimal(15000));
        dvd.setKode("K0011");
        int status = service.deleteDvd(dvd);
        Assertions.assertEquals(1, status);
        
    }
    
    
}
