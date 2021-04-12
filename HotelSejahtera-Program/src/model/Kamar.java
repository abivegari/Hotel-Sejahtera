/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Rangga
 */
@Entity
@Table(name = "kamar")
@NamedQueries({
    @NamedQuery(name = "Kamar.findAll", query = "SELECT k FROM Kamar k")})
public class Kamar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_kamar")
    private Integer idKamar;
    @Column(name = "kapasitas")
    private Integer kapasitas;
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "id_room", referencedColumnName = "id_room")
    @ManyToOne
    private Room idRoom;

    public Kamar() {
    }

    public Kamar(Integer idKamar) {
        this.idKamar = idKamar;
    }

    public Integer getIdKamar() {
        return idKamar;
    }

    public void setIdKamar(Integer idKamar) {
        this.idKamar = idKamar;
    }

    public Integer getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(Integer kapasitas) {
        this.kapasitas = kapasitas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        hash += (idKamar != null ? idKamar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kamar)) {
            return false;
        }
        Kamar other = (Kamar) object;
        if ((this.idKamar == null && other.idKamar != null) || (this.idKamar != null && !this.idKamar.equals(other.idKamar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Kamar[ idKamar=" + idKamar + " ]";
    }
    
}
