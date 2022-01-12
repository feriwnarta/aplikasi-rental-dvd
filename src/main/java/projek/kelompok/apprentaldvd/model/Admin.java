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
public class Admin {
    private String id;
    private String nama;
    private String alamat;
    private String password;
    private String noTelp;
    private Date masukJam;
    private Date sampaiJam;

    public Admin(String id, String nama, String alamat, String password, String noTelp, Date masukJam, Date sampaiJam) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.password = password;
        this.noTelp = noTelp;
        this.masukJam = masukJam;
        this.sampaiJam = sampaiJam;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public Date getMasukJam() {
        return masukJam;
    }

    public void setMasukJam(Date masukJam) {
        this.masukJam = masukJam;
    }

    public Date getSampaiJam() {
        return sampaiJam;
    }

    public void setSampaiJam(Date sampaiJam) {
        this.sampaiJam = sampaiJam;
    }

   
    
}
