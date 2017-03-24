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

    private String dia;
    private String especialidad;
    private String consultorio;
    private Date horaInicio;
    private Date horaFin;

    public CargaHoraria() {
    }

    public CargaHoraria(String dia, String especialidad, String consultorio, Date horaInicio, Date horaFin) {
        this.dia = dia;
        this.especialidad = especialidad;
        this.consultorio = consultorio;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public String getDia() {
        return this.dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
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

    @Override
    public String toString() {
        return "CargaHoraria{" + "dia=" + dia + ", especialidad=" + especialidad + ", consultorio=" + consultorio + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + '}';
    }

}
