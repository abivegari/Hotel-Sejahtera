/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Rangga
 */
@Entity
@Table(name = "reservasi")
@NamedQueries({
    @NamedQuery(name = "Reservasi.findAll", query = "SELECT r FROM Reservasi r")})
public class Reservasi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "no_reservasi")
    private Integer noReservasi;
    @Column(name = "tanggal_reservasi")
    @Temporal(TemporalType.DATE)
    private Date tanggalReservasi;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private Double total;
    @OneToMany(mappedBy = "noReservasi")
    private List<ResDetail> resDetailList;
    @JoinColumn(name = "no_ktp_customer", referencedColumnName = "no_ktp_customer")
    @ManyToOne
    private Customer noKtpCustomer;
    @JoinColumn(name = "id_pegawai", referencedColumnName = "id_pegawai")
    @ManyToOne
    private Pegawai idPegawai;

    public Reservasi() {
    }

    public Reservasi(Integer noReservasi) {
        this.noReservasi = noReservasi;
    }

    public Integer getNoReservasi() {
        return noReservasi;
    }

    public void setNoReservasi(Integer noReservasi) {
        this.noReservasi = noReservasi;
    }

    public Date getTanggalReservasi() {
        return tanggalReservasi;
    }

    public void setTanggalReservasi(Date tanggalReservasi) {
        this.tanggalReservasi = tanggalReservasi;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<ResDetail> getResDetailList() {
        return resDetailList;
    }

    public void setResDetailList(List<ResDetail> resDetailList) {
        this.resDetailList = resDetailList;
    }

    public Customer getNoKtpCustomer() {
        return noKtpCustomer;
    }

    public void setNoKtpCustomer(Customer noKtpCustomer) {
        this.noKtpCustomer = noKtpCustomer;
    }

    public Pegawai getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(Pegawai idPegawai) {
        this.idPegawai = idPegawai;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noReservasi != null ? noReservasi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservasi)) {
            return false;
        }
        Reservasi other = (Reservasi) object;
        if ((this.noReservasi == null && other.noReservasi != null) || (this.noReservasi != null && !this.noReservasi.equals(other.noReservasi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Reservasi[ noReservasi=" + noReservasi + " ]";
    }
    
}
