/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.datos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author FREDDY
 */
public class CargaHoraria implements Serializable {

    private TDias dias;
    private TEspecialidad especialidad1;
    private TConsultorio consultorio1;
    private Date horaInicio;
    private Date horaFin;

    public CargaHoraria() {
    }

    public CargaHoraria(TDias dias, TEspecialidad especialidad1, TConsultorio consultorio1, Date horaInicio, Date horaFin) {
        this.dias = dias;
        this.especialidad1 = especialidad1;
        this.consultorio1 = consultorio1;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
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

    public TDias getDias() {
        return dias;
    }

    public void setDias(TDias dias) {
        this.dias = dias;
    }

    public TEspecialidad getEspecialidad1() {
        return especialidad1;
    }

    public void setEspecialidad1(TEspecialidad especialidad1) {
        this.especialidad1 = especialidad1;
    }

    public TConsultorio getConsultorio1() {
        return consultorio1;
    }

    public void setConsultorio1(TConsultorio consultorio1) {
        this.consultorio1 = consultorio1;
    }

    @Override
    public String toString() {
        return "CargaHoraria{" + "dias=" + dias + ", especialidad1=" + especialidad1 + ", consultorio1=" + consultorio1 + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + '}';
    }

}
