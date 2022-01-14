/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projek.kelompok.apprentaldvd.model;

import java.awt.Button;
import java.math.BigDecimal;


public class Dvd {
    private String kode;
    private String judulFilm;
    private String kategori;
    private int quantity;
    private BigDecimal harga;
    private Button button;

    public Dvd(String kode, String judulFilm, String kategori, int quantity, BigDecimal harga) {
        this.kode = kode;
        this.judulFilm = judulFilm;
        this.kategori = kategori;
        this.quantity = quantity;
        this.harga = harga;
    }
    
    public Dvd(String judulFilm, String kategori, int quantity, BigDecimal harga) {
        this.judulFilm = judulFilm;
        this.kategori = kategori;
        this.quantity = quantity;
        this.harga = harga;
    }
    
    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getJudulFilm() {
        return judulFilm;
    }

    public void setJudulFilm(String judulFilm) {
        this.judulFilm = judulFilm;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }
    
    
    
}
