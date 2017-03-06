/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.presentacion;

import com.capa.datos.TPaciente;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

public class Utilidades {

    public static String getFechaHora() {
        return getFechaFormato(new SimpleDateFormat("yyyy' 'MM' 'dd' 'HH:mm:ss", new Locale("es", "EC")));
    }

    public static String getFechaFormato(SimpleDateFormat formateador) {
        return formateador.format(new Date());
    }

    public static String getFechaNacimiento(TPaciente paciente) {
        try {
            SimpleDateFormat formateador = new SimpleDateFormat("yyyy' - 'MMMM' - 'dd", new Locale("es", "EC"));
            return formateador.format(paciente.getPacFechaNacimiento()).toUpperCase();
        } catch (Exception e) {
            return "";
        }
    }

    public static String getFechaHoraFormato(TPaciente paciente) {
        try {
            SimpleDateFormat formateador = new SimpleDateFormat("yyyy' 'MM' 'dd' 'HH:mm:ss", new Locale("es", "EC"));
            return formateador.format(paciente.getHclFechaAdmision()).toUpperCase();
        } catch (Exception e) {
            return "";
        }
    }

    public String calcularAñosMesDia(TPaciente paciente) {
        LocalDate nacimiento = paciente.getPacFechaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period periodo = Period.between(nacimiento, LocalDate.now());
        String edad = " " + periodo.getYears() + " " + periodo.getMonths() + " " + periodo.getDays();
        System.out.printf("Tu edad es: %s años, %s meses y %s días",
                periodo.getYears(), periodo.getMonths(), periodo.getDays());
        return edad;
    }

    public static Long calcularEdad(TPaciente paciente) {
        try {
            LocalDate nacimiento = paciente.getPacFechaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Long edad = ChronoUnit.YEARS.between(nacimiento, LocalDate.now());
            return edad;
        } catch (Exception e) {
            return new Long(0);
        }
    }
}
