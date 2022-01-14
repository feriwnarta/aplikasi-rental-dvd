/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projek.kelompok.apprentaldvd;

import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Assertions;
import projek.kelompok.apprentaldvd.service.AdminService;
import projek.kelompok.apprentaldvd.service.KoneksiFactory;
import projek.kelompok.apprentaldvd.service.ProdukDvdService;
import projek.kelompok.apprentaldvd.model.Admin;

/**
 *
 * @author Feri Winarta
 */
public class AdminTest {
    private AdminService service;
    private KoneksiFactory koneksi;
    
    //@Before
    public void setUp() {
        koneksi = new KoneksiFactory();
        service = new AdminService(new KoneksiFactory().getConn());
    }
    
    @Test
    public void testGetOneAdmin(){
        Admin admin = service.getOneAdmin("ucong");
        System.out.println(admin.getNama());
        System.out.println(admin.getAlamat());
    }
    
    //@Test
    public void testUpdateAdmin(){
        Admin admin = new Admin("jakarr sembung", "sewan", "089603264483", "07.30", "17.00");
        admin.setId("adminlog01s");
        int status = service.updateAdmin(admin);
        Assertions.assertEquals(1, status);
    }
    
    //@Test
    public void testGantiPasswordAdmin(){
        Admin admin = new Admin("jaka sembung", "sewan", "089603264483", "07.30", "17.00");
        admin.setId("adminlog01");
        admin.setPassword("adminsaja01");
        int status = service.gantiPasswordAdmin(admin);
        Assertions.assertEquals(1, status);
    }
    
    //@Test
    public void testTambahAdmin(){
        Admin admin = new Admin("adminlog03", "ucup", "gaga", "passwordaja", "012930193", "01.00", "09.00");
        int status = service.tambahAdmin(admin);
        Assertions.assertEquals(1, status);
    }


}
