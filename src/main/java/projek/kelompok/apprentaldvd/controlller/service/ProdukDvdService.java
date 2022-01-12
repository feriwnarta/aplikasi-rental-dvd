/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projek.kelompok.apprentaldvd.controlller.service;

import java.sql.Connection;
import java.sql.DriverManager;
import sun.util.logging.resources.logging;

/**
 *
 * @author Feri Winarta
 */
public class ProdukDvdService {
    private Connection conn;

    public ProdukDvdService(Connection conn) {
        this.conn = conn;
        cekKoneksi();
    }
    
    public void cekKoneksi(){
        if(conn == null) {
            System.err.println("koneksi gagal di kelas " + getClass().getSimpleName());
        }
    }
    
}
