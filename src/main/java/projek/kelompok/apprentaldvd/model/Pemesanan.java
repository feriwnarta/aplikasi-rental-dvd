/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projek.kelompok.apprentaldvd.model;

import java.sql.Date;

/**
 *
 * @author Feri Winarta
 */
public class Pemesanan {
    private String nik;
    private String kode;
    private int quantity;
    private Date lamaSewa;

    public Pemesanan(String nik, String kode, int quantity, Date lamaSewa) {
        this.nik = nik;
        this.kode = kode;
        this.quantity = quantity;
        this.lamaSewa = lamaSewa;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getLamaSewa() {
        return lamaSewa;
    }

    public void setLamaSewa(Date lamaSewa) {
        this.lamaSewa = lamaSewa;
    }

    
    
}
