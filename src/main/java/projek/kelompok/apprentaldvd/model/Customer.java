/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projek.kelompok.apprentaldvd.model;

/**
 *
 * @author Feri Winarta
 */
public class Customer {
    private long nik;
    private String nama;
    private String alamat;
    private long noTelp;

    public Customer(long nik, String nama, String alamat, long noTelp) {
        this.nik = nik;
        this.nama = nama;
        this.alamat = alamat;
        this.noTelp = noTelp;
    }

    public long getNik() {
        return nik;
    }

    public void setNik(long nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public long getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(long noTelp) {
        this.noTelp = noTelp;
    }

   
    
}