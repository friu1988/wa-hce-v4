/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.datos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
    @NamedQuery(name = "TPersonalSalud.findByPerSerial", query = "SELECT t FROM TPersonalSalud t WHERE t.perSerial = :perSerial")})
public class TPersonalSalud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "per_serial", nullable = false)
    private Integer perSerial;
    @JoinColumn(name = "esp_serial", referencedColumnName = "esp_serial", nullable = false)
    @ManyToOne(optional = false)
    private TEspecialidad espSerial;
    @JoinColumn(name = "co_serial", referencedColumnName = "co_serial")
    @ManyToOne
    private TConsultorio coSerial;
    @JoinColumn(name = "per_serial", referencedColumnName = "per_serial", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private TMedico tMedico;

    public TPersonalSalud() {
    }

    public TPersonalSalud(TEspecialidad espSerial, TConsultorio coSerial, TMedico tMedico) {
        this.espSerial = espSerial;
        this.coSerial = coSerial;
        this.tMedico = tMedico;
    }

    public TPersonalSalud(Integer perSerial) {
        this.perSerial = perSerial;
    }

    public Integer getPerSerial() {
        return perSerial;
    }

    public void setPerSerial(Integer perSerial) {
        this.perSerial = perSerial;
    }

    public TEspecialidad getEspSerial() {
        return espSerial;
    }

    public void setEspSerial(TEspecialidad espSerial) {
        this.espSerial = espSerial;
    }

    public TConsultorio getCoSerial() {
        return coSerial;
    }

    public void setCoSerial(TConsultorio coSerial) {
        this.coSerial = coSerial;
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
        hash += (perSerial != null ? perSerial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TPersonalSalud)) {
            return false;
        }
        TPersonalSalud other = (TPersonalSalud) object;
        if ((this.perSerial == null && other.perSerial != null) || (this.perSerial != null && !this.perSerial.equals(other.perSerial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.capa.datos.TPersonalSalud[ perSerial=" + perSerial + " ]";
    }

}
