/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.datos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FREDDY
 */
@Entity
@Table(name = "t_horario", catalog = "db_hospital_dia_v4", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "THorario.findAll", query = "SELECT t FROM THorario t"),
    @NamedQuery(name = "THorario.findByDSerial", query = "SELECT t FROM THorario t WHERE t.dSerial = :dSerial"),
    @NamedQuery(name = "THorario.findByHoraInicio", query = "SELECT t FROM THorario t WHERE t.horaInicio = :horaInicio"),
    @NamedQuery(name = "THorario.findByHoraFin", query = "SELECT t FROM THorario t WHERE t.horaFin = :horaFin")})
public class THorario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "d_serial", nullable = false)
    private Integer dSerial;
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Column(name = "hora_fin")
    @Temporal(TemporalType.TIME)
    private Date horaFin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tHorario")
    private List<TPersonalSalud> tPersonalSaludList;
    @JoinColumn(name = "d_serial", referencedColumnName = "d_serial", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private TDias tDias;

    public THorario() {
    }

    public THorario(Integer dSerial) {
        this.dSerial = dSerial;
    }

    public Integer getDSerial() {
        return dSerial;
    }

    public void setDSerial(Integer dSerial) {
        this.dSerial = dSerial;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    @XmlTransient
    public List<TPersonalSalud> getTPersonalSaludList() {
        return tPersonalSaludList;
    }

    public void setTPersonalSaludList(List<TPersonalSalud> tPersonalSaludList) {
        this.tPersonalSaludList = tPersonalSaludList;
    }

    public TDias getTDias() {
        return tDias;
    }

    public void setTDias(TDias tDias) {
        this.tDias = tDias;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dSerial != null ? dSerial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof THorario)) {
            return false;
        }
        THorario other = (THorario) object;
        if ((this.dSerial == null && other.dSerial != null) || (this.dSerial != null && !this.dSerial.equals(other.dSerial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.capa.datos.THorario[ dSerial=" + dSerial + " ]";
    }
    
}
