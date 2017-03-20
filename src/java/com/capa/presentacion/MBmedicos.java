package com.capa.presentacion;

import com.capa.datos.TConsultorio;
import com.capa.datos.TDias;
import com.capa.datos.TEspecialidad;
import com.capa.datos.THorario;
import com.capa.datos.TMedico;
import com.capa.datos.TPersonal;
import com.capa.datos.TTurno;
import com.capa.negocios.TMedicoFacade;
import com.capa.negocios.TPersonalFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

@Named(value = "mBmedicos")
@SessionScoped
public class MBmedicos implements Serializable {

    @EJB
    private TMedicoFacade servicioMedicos;
    @EJB
    private TPersonalFacade servicioPersonal;

    private TMedico medico;

    private List<TMedico> medicos;

    public MBmedicos() {
    }

    @PostConstruct
    public void init() {

        medicos = null;
        for (TPersonal p : servicioPersonal.findAll()) {
            if (p.getPerTipo().equals("M")) {
                if (servicioMedicos.buscarMedico(p)) {
                    /*Nada*/
                } else {
                    TMedico m = new TMedico(p.getPerSerial());
                    m.setTPersonal(p);
                    servicioMedicos.create(m);
                }
            }
        }
        medicos = servicioMedicos.findAll();
    }

    public String goAsignaciones() {
    return "medicos_asignar.xhtml";
    }

    public TMedico getMedico() {
        return medico;
    }

    public void setMedico(TMedico medico) {
        this.medico = medico;
    }

    public List<TMedico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<TMedico> medicos) {
        this.medicos = medicos;
    }

    public void estHorarios() {
        List<TEspecialidad> especialidades = new LinkedList<>();
        TEspecialidad especialidad1 = new TEspecialidad();
        especialidad1.setEspNombre("Pediatria");
        especialidades.add(especialidad1);

        List<THorario> horarios = new LinkedList<>();
        THorario horario1 = new THorario();
        horario1.setHoraInicio(new Date(2016, 5, 3, 8, 30, 0));
        horario1.setHoraFin(new Date(2017, 5, 3, 12, 30, 0));
        horario1.setTDias(new TDias(1));

        THorario horario2 = new THorario();
        horario2.setHoraInicio(new Date(2016, 5, 3, 13, 30, 0));
        horario2.setHoraFin(new Date(2017, 5, 3, 18, 30, 0));
        horario2.setTDias(new TDias(2));

        horarios.add(horario1);
        horarios.add(horario2);

//        TMedico medico = new TMedico(2, especialidades, null, new TConsultorio(3), null/*turnos*/, horarios);
        int nTurnos = generarTurnos(medico, new TDias(2), 30);

        TTurno[] turnos = new TTurno[nTurnos];

    }

    //verificar q los tiempos sean exactos(para que retorner valores enteros exactos)
    private int generarTurnos(TMedico medico, TDias dia, int intervalo/*minutos*/) {
        //supongamos q tal dia es este(se debe buscar segun el dia)
        THorario horario = new THorario();
        horario.setHoraInicio(new Date(2016, 5, 3, 13, 30, 0));
        horario.setHoraFin(new Date(2017, 5, 3, 18, 30, 0));
        horario.setTDias(new TDias(2));

//        if (medico.getTHorarioList().contains(dia)) {//verificar metodo booleano
//            //calculamos tiempo en minutos
//            int tiempo = (horario.getHoraFin().getHours() - horario.getHoraInicio().getHours()) * 60 + Math.abs(horario.getHoraFin().getMinutes() - horario.getHoraInicio().getMinutes());
//            return (tiempo / intervalo);//retornamos el numero de turnos
//        } else {
//            System.out.println("No hay turnos disponiblos cn tal doctor para tal dia");
        return 0;
//        }
    }
}
