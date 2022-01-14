/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projek.kelompok.apprentaldvd.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projek.kelompok.apprentaldvd.model.Customer;
import projek.kelompok.apprentaldvd.model.Dvd;


public class CustomerService {
    private Connection conn;
    private Customer customer;

    public CustomerService(Connection conn) {
        this.conn = conn;
        if(conn == null) {
            System.err.println("koneksi gagal di kelas " + getClass().getSimpleName());
        }
    }
    
    public int insertCustomer(Customer customer){
        int status = 100;
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO customer (nik, nama, alamat, no_telpon) values (?,?,?,?)"
            );
            
            ps.setString(1, customer.getNik());
            ps.setString(2, customer.getNama());
            ps.setString(3, customer.getAlamat());
            ps.setString(4, customer.getNoTelp());
            
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProdukDvdService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
        
    }
    
    public Customer cekNik(String nik){
        int status = 100;
        Customer cust = null;
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM customer WHERE nik = ?"
            );
            
            ps.setString(1, nik);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                cust = new Customer(rs.getString("nik"), rs.getString("nama"), rs.getString("alamat"), rs.getString("no_telpon"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdukDvdService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cust;
    }
    
    
    
    
    
}
