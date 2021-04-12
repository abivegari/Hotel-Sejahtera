/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Rangga
 */
@Entity
@Table(name = "res_detail")
@NamedQueries({
    @NamedQuery(name = "ResDetail.findAll", query = "SELECT r FROM ResDetail r")})
public class ResDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rd_id")
    private Integer rdId;
    @Column(name = "quantity")
    private Integer quantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "room_price")
    private Double roomPrice;
    @Column(name = "tanggal_check_in")
    @Temporal(TemporalType.DATE)
    private Date tanggalCheckIn;
    @Column(name = "tanggal_check_out")
    @Temporal(TemporalType.DATE)
    private Date tanggalCheckOut;
    @Column(name = "status_reservasi")
    private String statusReservasi;
    @JoinColumn(name = "no_reservasi", referencedColumnName = "no_reservasi")
    @ManyToOne
    private Reservasi noReservasi;
    @JoinColumn(name = "id_room", referencedColumnName = "id_room")
    @ManyToOne
    private Room idRoom;

    public ResDetail() {
    }

    public ResDetail(Integer rdId) {
        this.rdId = rdId;
    }

    public ResDetail(Integer quantity, Double roomPrice, Date tanggalCheckIn, Date tanggalCheckOut, Room idRoom) {
        this.quantity = quantity;
        this.roomPrice = roomPrice;
        this.tanggalCheckIn = tanggalCheckIn;
        this.tanggalCheckOut = tanggalCheckOut;
        this.idRoom = idRoom;
    }

    public Integer getRdId() {
        return rdId;
    }

    public void setRdId(Integer rdId) {
        this.rdId = rdId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Date getTanggalCheckIn() {
        return tanggalCheckIn;
    }

    public void setTanggalCheckIn(Date tanggalCheckIn) {
        this.tanggalCheckIn = tanggalCheckIn;
    }

    public Date getTanggalCheckOut() {
        return tanggalCheckOut;
    }

    public void setTanggalCheckOut(Date tanggalCheckOut) {
        this.tanggalCheckOut = tanggalCheckOut;
    }

    public String getStatusReservasi() {
        return statusReservasi;
    }

    public void setStatusReservasi(String statusReservasi) {
        this.statusReservasi = statusReservasi;
    }

    public Reservasi getNoReservasi() {
        return noReservasi;
    }

    public void setNoReservasi(Reservasi noReservasi) {
        this.noReservasi = noReservasi;
    }

    public Room getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Room idRoom) {
        this.idRoom = idRoom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rdId != null ? rdId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResDetail)) {
            return false;
        }
        ResDetail other = (ResDetail) object;
        if ((this.rdId == null && other.rdId != null) || (this.rdId != null && !this.rdId.equals(other.rdId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ResDetail[ rdId=" + rdId + " ]";
    }
    
}
