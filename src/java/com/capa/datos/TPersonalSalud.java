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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NamedQuery(name = "TPersonalSalud.findByPsSerial", query = "SELECT t FROM TPersonalSalud t WHERE t.psSerial = :psSerial"),
    @NamedQuery(name = "TPersonalSalud.findByPerSerial", query = "SELECT t FROM TPersonalSalud t WHERE t.perSerial.perSerial = :perSerial")})
public class TPersonalSalud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ps_serial", nullable = false)
    private Integer psSerial;
    @JoinColumn(name = "esp_serial", referencedColumnName = "esp_serial", nullable = false)
    @ManyToOne(optional = false)
    private TEspecialidad espSerial;
    @JoinColumn(name = "co_serial", referencedColumnName = "co_serial", nullable = false)
    @ManyToOne(optional = false)
    private TConsultorio coSerial;
    @JoinColumn(name = "per_serial", referencedColumnName = "per_serial", nullable = false)
    @ManyToOne(optional = false)
    private TMedico perSerial;

    public TPersonalSalud() {
    }

    public TPersonalSalud(TEspecialidad espSerial, TConsultorio coSerial, TMedico perSerial) {
        this.espSerial = espSerial;
        this.coSerial = coSerial;
        this.perSerial = perSerial;
    }

    public TPersonalSalud(Integer psSerial) {
        this.psSerial = psSerial;
    }

    public Integer getPsSerial() {
        return psSerial;
    }

    public void setPsSerial(Integer psSerial) {
        this.psSerial = psSerial;
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

    public TMedico getPerSerial() {
        return perSerial;
    }

    public void setPerSerial(TMedico perSerial) {
        this.perSerial = perSerial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (psSerial != null ? psSerial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TPersonalSalud)) {
            return false;
        }
        TPersonalSalud other = (TPersonalSalud) object;
        if ((this.psSerial == null && other.psSerial != null) || (this.psSerial != null && !this.psSerial.equals(other.psSerial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TPersonalSalud{" + "psSerial=" + psSerial + ", espSerial=" + espSerial + ", coSerial=" + coSerial + ", perSerial=" + perSerial + '}';
    }

}
