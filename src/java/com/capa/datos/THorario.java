/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.datos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FREDDY
 */
@Entity
@Table(name = "t_horario", catalog = "db_hospital_dia_v4", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "THorario.findAll", query = "SELECT t FROM THorario t"),
    @NamedQuery(name = "THorario.findByPerSerial", query = "SELECT t FROM THorario t WHERE t.tHorarioPK.perSerial = :perSerial"),
    @NamedQuery(name = "THorario.findByDSerial", query = "SELECT t FROM THorario t WHERE t.tHorarioPK.dSerial = :dSerial"),
    @NamedQuery(name = "THorario.findByHoraInicio", query = "SELECT t FROM THorario t WHERE t.horaInicio = :horaInicio"),
    @NamedQuery(name = "THorario.findByHoraFin", query = "SELECT t FROM THorario t WHERE t.horaFin = :horaFin")})
public class THorario implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected THorarioPK tHorarioPK;
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Column(name = "hora_fin")
    @Temporal(TemporalType.TIME)
    private Date horaFin;
    @JoinColumn(name = "d_serial", referencedColumnName = "d_serial", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TDias tDias;
    @JoinColumn(name = "per_serial", referencedColumnName = "per_serial", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TMedico tMedico;

    public THorario() {
    }

    public THorario(THorarioPK tHorarioPK) {
        this.tHorarioPK = tHorarioPK;
    }

    public THorario(int perSerial, int dSerial) {
        this.tHorarioPK = new THorarioPK(perSerial, dSerial);
    }

    public THorarioPK getTHorarioPK() {
        return tHorarioPK;
    }

    public void setTHorarioPK(THorarioPK tHorarioPK) {
        this.tHorarioPK = tHorarioPK;
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

    public TDias getTDias() {
        return tDias;
    }

    public void setTDias(TDias tDias) {
        this.tDias = tDias;
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
        hash += (tHorarioPK != null ? tHorarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof THorario)) {
            return false;
        }
        THorario other = (THorario) object;
        if ((this.tHorarioPK == null && other.tHorarioPK != null) || (this.tHorarioPK != null && !this.tHorarioPK.equals(other.tHorarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.capa.datos.THorario[ tHorarioPK=" + tHorarioPK + " ]";
    }
    
}
