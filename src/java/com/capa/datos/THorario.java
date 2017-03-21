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
    @NamedQuery(name = "THorario.findByHSerial", query = "SELECT t FROM THorario t WHERE t.hSerial = :hSerial"),
    @NamedQuery(name = "THorario.findByHoraInicio", query = "SELECT t FROM THorario t WHERE t.horaInicio = :horaInicio"),
    @NamedQuery(name = "THorario.findByHoraFin", query = "SELECT t FROM THorario t WHERE t.horaFin = :horaFin")})
public class THorario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "h_serial", nullable = false)
    private Integer hSerial;
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Column(name = "hora_fin")
    @Temporal(TemporalType.TIME)
    private Date horaFin;
    @JoinColumn(name = "d_serial", referencedColumnName = "d_serial", nullable = false)
    @ManyToOne(optional = false)
    private TDias dSerial;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tHorario")
    private List<TPersonalSalud> tPersonalSaludList;

    public THorario() {
    }

    public THorario(Integer hSerial) {
        this.hSerial = hSerial;
    }

    public THorario(Date horaInicio, Date horaFin, TDias dSerial) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.dSerial = dSerial;
    }
    
    

    public Integer getHSerial() {
        return hSerial;
    }

    public void setHSerial(Integer hSerial) {
        this.hSerial = hSerial;
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

    public TDias getDSerial() {
        return dSerial;
    }

    public void setDSerial(TDias dSerial) {
        this.dSerial = dSerial;
    }

    @XmlTransient
    public List<TPersonalSalud> getTPersonalSaludList() {
        return tPersonalSaludList;
    }

    public void setTPersonalSaludList(List<TPersonalSalud> tPersonalSaludList) {
        this.tPersonalSaludList = tPersonalSaludList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hSerial != null ? hSerial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof THorario)) {
            return false;
        }
        THorario other = (THorario) object;
        if ((this.hSerial == null && other.hSerial != null) || (this.hSerial != null && !this.hSerial.equals(other.hSerial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.capa.datos.THorario[ hSerial=" + hSerial + " ]";
    }

}
