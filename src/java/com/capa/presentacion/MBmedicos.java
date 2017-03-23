package com.capa.presentacion;

import com.capa.datos.TConsultorio;
import com.capa.datos.TDias;
import com.capa.datos.TEspecialidad;
import com.capa.datos.THorario;
import com.capa.datos.THorarioPK;
import com.capa.datos.TMedico;
import com.capa.datos.TPersonal;
import com.capa.datos.TPersonalSalud;
import com.capa.datos.TTurno;
import com.capa.negocios.TDiasFacade;
import com.capa.negocios.TMedicoFacade;
import com.capa.negocios.TPersonalFacade;
import com.capa.negocios.TPersonalSaludFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "mBmedicos")
@SessionScoped
public class MBmedicos implements Serializable {

    @EJB
    private TMedicoFacade servicioMedicos;
    @EJB
    private TPersonalFacade servicioEmpleados;
    @EJB
    private TPersonalSaludFacade servicioPersonalSalud;
    @EJB
    private TDiasFacade servicioDias;

    private TPersonalSalud personalSalud;
    private THorario horario;
    private TEspecialidad especialidad;
    private TConsultorio consultorio;
    private TMedico medico;

    private List<TMedico> medicos;
    private List<TPersonalSalud> listaPersonalSalud;
    private List<TDias> dias;

    private Integer[] diasSeleccionados;

    public MBmedicos() {
    }

    @PostConstruct
    public void init() {

        medicos = null;
        for (TPersonal p : servicioEmpleados.findAll()) {
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

    public void generarCargaHoraria() {

        if (!diasSolapados()) {
            for (int i = 0; i < this.diasSeleccionados.length; i++) {
                THorario ho = new THorario(new THorarioPK(diasSeleccionados[i], medico.getPerSerial()), this.horario.getHoraInicio(), this.horario.getHoraFin(), new TDias(diasSeleccionados[i]), medico);

                listaPersonalSalud.add(new TPersonalSalud(this.especialidad, this.consultorio, medico));
                System.out.println("Carga horaria: " + listaPersonalSalud.get(i));
            }
            diasSeleccionados = null;
            consultorio = null;
            especialidad = null;
            dias = null;
            horario = null;

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Día ya ingresado!", null));
        }
    }

    private boolean diasSolapados() {
        for (int i = 0; i < this.diasSeleccionados.length; i++) {

            for (TPersonalSalud item : listaPersonalSalud) {
//                if (item.getTMedico().getTHorarioList().getDSerial().getDSerial() == diasSeleccionados[i]) {
                return true;
//                }
            }

        }
        return false;
    }

    //verificar q los tiempos sean exactos(para que retorner valores enteros exactos)
    private List<TTurno[]> generarTurnos(TMedico medico, int intervalo/*minutos*/) {
        //supongamos q tal dia es este(se debe buscar segun el dia)
        THorario horario = new THorario();
        horario.setHoraInicio(new Date(2016, 5, 3, 13, 30, 0));
        horario.setHoraFin(new Date(2017, 5, 3, 18, 30, 0));
        return null;
        /*      
        if (medico.getTHorarioList().contains(dia)) {//verificar metodo booleano
            //calculamos tiempo en minutos
            int tiempo = (horario.getHoraFin().getHours() - horario.getHoraInicio().getHours()) * 60 + Math.abs(horario.getHoraFin().getMinutes() - horario.getHoraInicio().getMinutes());
            int nTurnos = tiempo / intervalo;

            return new TTurno[nTurnos];

        } else {
            System.out.println("No hay turnos disponiblos cn tal doctor para tal dia");
            return null;
        }
         */
    }

    public void seleccionados() {
        for (Integer select : getDiasSeleccionados()) {
            System.out.println("Seleccionado: " + select);
        }
    }

    public String goAsignaciones() {
        System.out.println("Medico Seleccionado>>>" + medico);
        return "medicos_asignar.xhtml";
    }

    public List<TMedico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<TMedico> medicos) {
        this.medicos = medicos;
    }

    public List<TPersonalSalud> getListaPersonalSalud() {
        if (listaPersonalSalud == null) {
            listaPersonalSalud = new LinkedList<>();
        }
        return listaPersonalSalud;
    }

    public void setListaPersonalSalud(List<TPersonalSalud> listaPersonalSalud) {
        this.listaPersonalSalud = listaPersonalSalud;
    }

    public Integer[] getDiasSeleccionados() {
        return diasSeleccionados;
    }

    public void setDiasSeleccionados(Integer[] diasSeleccionados) {
        this.diasSeleccionados = diasSeleccionados;
    }

    public TPersonalSalud getPersonalSalud() {
        return personalSalud;
    }

    public void setPersonalSalud(TPersonalSalud personalSalud) {
        this.personalSalud = personalSalud;
    }

    public THorario getHorario() {
        if (horario == null) {
            horario = new THorario();
        }
        return horario;
    }

    public void setHorario(THorario horario) {
        this.horario = horario;
    }

    public TEspecialidad getEspecialidad() {
        if (especialidad == null) {
            especialidad = new TEspecialidad();
        }
        return especialidad;
    }

    public void setEspecialidad(TEspecialidad especialidad) {
        this.especialidad = especialidad;
    }

    public TConsultorio getConsultorio() {
        if (consultorio == null) {
            consultorio = new TConsultorio();
        }
        return consultorio;
    }

    public void setConsultorio(TConsultorio consultorio) {
        this.consultorio = consultorio;
    }

    public TMedico getMedico() {
        if (medico == null) {
            medico = new TMedico();
        }
        return medico;
    }

    public void setMedico(TMedico medico) {
        this.medico = medico;
    }

    public List<TDias> getDias() {
        if (dias == null) {
            dias = servicioDias.findAll();
        }
        return dias;
    }

    public void setDias(List<TDias> dias) {
        this.dias = dias;
    }
}
