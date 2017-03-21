/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.datos;

import java.io.Serializable;
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
@Table(name = "t_personal_salud", catalog = "db_hospital_dia_v4", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TPersonalSalud.findAll", query = "SELECT t FROM TPersonalSalud t"),
    @NamedQuery(name = "TPersonalSalud.findByPerSerial", query = "SELECT t FROM TPersonalSalud t WHERE t.tPersonalSaludPK.perSerial = :perSerial"),
    @NamedQuery(name = "TPersonalSalud.findByHSerial", query = "SELECT t FROM TPersonalSalud t WHERE t.tPersonalSaludPK.hSerial = :hSerial")})
public class TPersonalSalud implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TPersonalSaludPK tPersonalSaludPK;
    @JoinColumn(name = "h_serial", referencedColumnName = "h_serial", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private THorario tHorario;
    @JoinColumn(name = "co_serial", referencedColumnName = "co_serial")
    @ManyToOne
    private TConsultorio coSerial;
    @JoinColumn(name = "esp_serial", referencedColumnName = "esp_serial", nullable = false)
    @ManyToOne(optional = false)
    private TEspecialidad espSerial;
    @JoinColumn(name = "per_serial", referencedColumnName = "per_serial", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TMedico tMedico;

    public TPersonalSalud() {
    }

    public TPersonalSalud(TPersonalSaludPK tPersonalSaludPK) {
        this.tPersonalSaludPK = tPersonalSaludPK;
    }

    public TPersonalSalud(TPersonalSaludPK tPersonalSaludPK, TConsultorio coSerial, TEspecialidad espSerial) {
        this.tPersonalSaludPK = tPersonalSaludPK;
        this.coSerial = coSerial;
        this.espSerial = espSerial;
    }

    public TPersonalSalud(TPersonalSaludPK tPersonalSaludPK, THorario tHorario, TConsultorio coSerial, TEspecialidad espSerial, TMedico tMedico) {
        this.tPersonalSaludPK = tPersonalSaludPK;
        this.tHorario = tHorario;
        this.coSerial = coSerial;
        this.espSerial = espSerial;
        this.tMedico = tMedico;
    }
    
    

    public TPersonalSalud(int perSerial, int hSerial) {
        this.tPersonalSaludPK = new TPersonalSaludPK(perSerial, hSerial);
    }

    public TPersonalSaludPK getTPersonalSaludPK() {
        return tPersonalSaludPK;
    }

    public void setTPersonalSaludPK(TPersonalSaludPK tPersonalSaludPK) {
        this.tPersonalSaludPK = tPersonalSaludPK;
    }

    public THorario getTHorario() {
        return tHorario;
    }

    public void setTHorario(THorario tHorario) {
        this.tHorario = tHorario;
    }

    public TConsultorio getCoSerial() {
        return coSerial;
    }

    public void setCoSerial(TConsultorio coSerial) {
        this.coSerial = coSerial;
    }

    public TEspecialidad getEspSerial() {
        return espSerial;
    }

    public void setEspSerial(TEspecialidad espSerial) {
        this.espSerial = espSerial;
    }

    public TMedico getTMedico() {
        return tMedico;
    }

    public void setTMedico(TMedico tMedico) {
        this.tMedico = tMedico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tPersonalSaludPK != null ? tPersonalSaludPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TPersonalSalud)) {
            return false;
        }
        TPersonalSalud other = (TPersonalSalud) object;
        if ((this.tPersonalSaludPK == null && other.tPersonalSaludPK != null) || (this.tPersonalSaludPK != null && !this.tPersonalSaludPK.equals(other.tPersonalSaludPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TPersonalSalud{" + "tPersonalSaludPK=" + tPersonalSaludPK + ", tHorario=" + tHorario + ", coSerial=" + coSerial + ", espSerial=" + espSerial + ", tMedico=" + tMedico + '}';
    }

}
