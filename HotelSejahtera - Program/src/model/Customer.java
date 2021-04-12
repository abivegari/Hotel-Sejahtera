/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Rangga
 */
@Entity
@Table(name = "customer")
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")})
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "no_ktp_customer")
    private String noKtpCustomer;
    @Column(name = "nama_customer")
    private String namaCustomer;
    @Column(name = "alamat_customer")
    private String alamatCustomer;
    @Column(name = "no_telp_customer")
    private String noTelpCustomer;
    @Column(name = "email_customer")
    private String emailCustomer;
    @Column(name = "jenis_kelamin")
    private String jenisKelamin;
    @OneToMany(mappedBy = "noKtpCustomer")
    private List<Reservasi> reservasiList;

    public Customer() {
    }

    public Customer(String noKtpCustomer) {
        this.noKtpCustomer = noKtpCustomer;
    }

    public String getNoKtpCustomer() {
        return noKtpCustomer;
    }

    public void setNoKtpCustomer(String noKtpCustomer) {
        this.noKtpCustomer = noKtpCustomer;
    }

    public String getNamaCustomer() {
        return namaCustomer;
    }

    public void setNamaCustomer(String namaCustomer) {
        this.namaCustomer = namaCustomer;
    }

    public String getAlamatCustomer() {
        return alamatCustomer;
    }

    public void setAlamatCustomer(String alamatCustomer) {
        this.alamatCustomer = alamatCustomer;
    }

    public String getNoTelpCustomer() {
        return noTelpCustomer;
    }

    public void setNoTelpCustomer(String noTelpCustomer) {
        this.noTelpCustomer = noTelpCustomer;
    }

    public String getEmailCustomer() {
        return emailCustomer;
    }

    public void setEmailCustomer(String emailCustomer) {
        this.emailCustomer = emailCustomer;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public List<Reservasi> getReservasiList() {
        return reservasiList;
    }

    public void setReservasiList(List<Reservasi> reservasiList) {
        this.reservasiList = reservasiList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noKtpCustomer != null ? noKtpCustomer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.noKtpCustomer == null && other.noKtpCustomer != null) || (this.noKtpCustomer != null && !this.noKtpCustomer.equals(other.noKtpCustomer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Customer[ noKtpCustomer=" + noKtpCustomer + " ]";
    }
    
}
