/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.datos;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FREDDY
 */
@Entity
@Table(name = "t_accesos", catalog = "db_hospital_dia_v4", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TAccesos.findAll", query = "SELECT t FROM TAccesos t"),
    @NamedQuery(name = "TAccesos.findByMenSerial", query = "SELECT t FROM TAccesos t WHERE t.tAccesosPK.menSerial = :menSerial"),
    @NamedQuery(name = "TAccesos.findByUSerial", query = "SELECT t FROM TAccesos t WHERE t.tAccesosPK.uSerial = :uSerial"),
    @NamedQuery(name = "TAccesos.findByAcCreate", query = "SELECT t FROM TAccesos t WHERE t.acCreate = :acCreate"),
    @NamedQuery(name = "TAccesos.findByAcRead", query = "SELECT t FROM TAccesos t WHERE t.acRead = :acRead"),
    @NamedQuery(name = "TAccesos.findByAcUpdate", query = "SELECT t FROM TAccesos t WHERE t.acUpdate = :acUpdate"),
    @NamedQuery(name = "TAccesos.findByAcDelete", query = "SELECT t FROM TAccesos t WHERE t.acDelete = :acDelete")})
public class TAccesos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TAccesosPK tAccesosPK;
    @Column(name = "ac_create")
    private Boolean acCreate;
    @Column(name = "ac_read")
    private Boolean acRead;
    @Column(name = "ac_update")
    private Boolean acUpdate;
    @Column(name = "ac_delete")
    private Boolean acDelete;
    @JoinColumn(name = "u_serial", referencedColumnName = "u_serial", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TUsuario tUsuario;
    @JoinColumn(name = "men_serial", referencedColumnName = "men_serial", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TMenu tMenu;

    public TAccesos() {
    }

    public TAccesos(TAccesosPK tAccesosPK) {
        this.tAccesosPK = tAccesosPK;
    }

    public TAccesos(int menSerial, int uSerial) {
        this.tAccesosPK = new TAccesosPK(menSerial, uSerial);
    }

    public TAccesosPK getTAccesosPK() {
        return tAccesosPK;
    }

    public void setTAccesosPK(TAccesosPK tAccesosPK) {
        this.tAccesosPK = tAccesosPK;
    }

    public Boolean getAcCreate() {
        return acCreate;
    }

    public void setAcCreate(Boolean acCreate) {
        this.acCreate = acCreate;
    }

    public Boolean getAcRead() {
        return acRead;
    }

    public void setAcRead(Boolean acRead) {
        this.acRead = acRead;
    }

    public Boolean getAcUpdate() {
        return acUpdate;
    }

    public void setAcUpdate(Boolean acUpdate) {
        this.acUpdate = acUpdate;
    }

    public Boolean getAcDelete() {
        return acDelete;
    }

    public void setAcDelete(Boolean acDelete) {
        this.acDelete = acDelete;
    }

    public TUsuario getTUsuario() {
        return tUsuario;
    }

    public void setTUsuario(TUsuario tUsuario) {
        this.tUsuario = tUsuario;
    }

    public TMenu getTMenu() {
        return tMenu;
    }

    public void setTMenu(TMenu tMenu) {
        this.tMenu = tMenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tAccesosPK != null ? tAccesosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TAccesos)) {
            return false;
        }
        TAccesos other = (TAccesos) object;
        if ((this.tAccesosPK == null && other.tAccesosPK != null) || (this.tAccesosPK != null && !this.tAccesosPK.equals(other.tAccesosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.capa.datos.TAccesos[ tAccesosPK=" + tAccesosPK + " ]";
    }
    
}
