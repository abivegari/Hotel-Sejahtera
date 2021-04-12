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
@Table(name = "room")
@NamedQueries({
    @NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r")})
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_room")
    private Integer idRoom;
    @Column(name = "jenis_room")
    private String jenisRoom;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "harga")
    private Double harga;
    @Column(name = "stock_room")
    private Integer stockRoom;
    @Column(name = "kapasitas")
    private Integer kapasitas;
    @OneToMany(mappedBy = "idRoom")
    private List<ResDetail> resDetailList;
    @OneToMany(mappedBy = "idRoom")
    private List<Kamar> kamarList;

    public Room() {
    }

    public Room(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public String getJenisRoom() {
        return jenisRoom;
    }

    public void setJenisRoom(String jenisRoom) {
        this.jenisRoom = jenisRoom;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public Integer getStockRoom() {
        return stockRoom;
    }

    public void setStockRoom(Integer stockRoom) {
        this.stockRoom = stockRoom;
    }

    public Integer getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(Integer kapasitas) {
        this.kapasitas = kapasitas;
    }

    public List<ResDetail> getResDetailList() {
        return resDetailList;
    }

    public void setResDetailList(List<ResDetail> resDetailList) {
        this.resDetailList = resDetailList;
    }

    public List<Kamar> getKamarList() {
        return kamarList;
    }

    public void setKamarList(List<Kamar> kamarList) {
        this.kamarList = kamarList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRoom != null ? idRoom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        if ((this.idRoom == null && other.idRoom != null) || (this.idRoom != null && !this.idRoom.equals(other.idRoom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Room[ idRoom=" + idRoom + " ]";
    }
    
}
