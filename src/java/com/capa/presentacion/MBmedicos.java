package com.capa.presentacion;

import com.capa.datos.CargaHoraria;
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
import com.capa.negocios.TEspecialidadFacade;
import com.capa.negocios.TMedicoFacade;
import com.capa.negocios.TPersonalFacade;
import com.capa.negocios.TPersonalSaludFacade;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

@Named(value = "mBmedicos")
@ViewScoped
public class MBmedicos implements Serializable {

    @EJB
    private TMedicoFacade servicioMedicos;
    @EJB
    private TPersonalFacade servicioEmpleados;
    @EJB
    private TPersonalSaludFacade servicioPersonalSalud;
    @EJB
    private TDiasFacade servicioDias;
    @EJB
    private TEspecialidadFacade servicioEspecialidad;

    private TPersonalSalud personalSalud;
    private THorario horario;
    private TEspecialidad especialidad;
    private TConsultorio consultorio;
    private TMedico medico;

    private List<TMedico> medicos;
    private List<SelectItem> listaDias;
    private List<CargaHoraria> listasCH;
    private List<TEspecialidad> especialidades;

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

        System.out.println("Especialidad: " + especialidad);
        System.out.println("Consultorio: " + consultorio);
        System.out.println("Horario: " + horario);

        if (!diasSolapados()) {
            for (int i = 0; i < this.diasSeleccionados.length; i++) {
                CargaHoraria cargaHoraria = new CargaHoraria(Integer.toString(diasSeleccionados[i]), Integer.toString(especialidad.getEspSerial()), Integer.toString(consultorio.getCoSerial()), this.horario.getHoraInicio(), this.horario.getHoraFin());
                listasCH.add(cargaHoraria);
                System.out.println("CARGA HORARIA DIA " + diasSeleccionados[i] + ": " + cargaHoraria);
                listaDias.get(diasSeleccionados[i] - 1).setDisabled(true);
            }
            consultorio = null;
            especialidad = null;
            horario = null;

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Día ya ingresado!", null));
        }
    }

    private boolean diasSolapados() {
        for (int i = 0; i < this.diasSeleccionados.length; i++) {
            for (CargaHoraria item : listasCH) {
                if (Integer.parseInt(item.getDia()) == diasSeleccionados[i]) {
                    listaDias.get(i).setDisabled(true);
                    return true;
                }
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

    public void guardarCH() {
        for (CargaHoraria item : listasCH) {
            THorario horario = new THorario(new THorarioPK(Integer.parseInt(item.getDia()), medico.getPerSerial()), item.getHoraInicio(), item.getHoraFin(), medico);
//            TPersonalSalud personalSalud = new TPersonalSalud(item.getEspecialidad(), item.getConsultorio(), medico);
        }
    }

    public String goAsignaciones() {
        System.out.println("Medico Seleccionado>>>" + medico);
        diasSeleccionados = null;
        consultorio = null;
        especialidad = null;
        horario = null;
        listasCH = null;
        return "medicos_asignar.xhtml";
    }

    public List<TMedico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<TMedico> medicos) {
        this.medicos = medicos;
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

    public List<CargaHoraria> getListasCH() {
        if (listasCH == null) {
            listasCH = new LinkedList<>();
        }
        return listasCH;
    }

    public void setListasCH(List<CargaHoraria> listasCH) {
        this.listasCH = listasCH;
    }

    public List<SelectItem> getListaDias() {
        if (listaDias == null) {
            listaDias = new LinkedList<>();
            this.listaDias.add(new SelectItem(1, "Lunes"));
            this.listaDias.add(new SelectItem(2, "Martes"));
            this.listaDias.add(new SelectItem(3, "Miercoles"));
            this.listaDias.add(new SelectItem(4, "Jueves"));
            this.listaDias.add(new SelectItem(5, "Viernes"));
            this.listaDias.add(new SelectItem(6, "Sábado"));
        }
        return listaDias;
    }

    public void setListaDias(List<SelectItem> listaDias) {
        this.listaDias = listaDias;
    }

    public List<TEspecialidad> getEspecialidades() {
        if (especialidades == null) {
            especialidades = servicioEspecialidad.findAll();
        }
        return especialidades;
    }

    public void setEspecialidades(List<TEspecialidad> especialidades) {
        this.especialidades = especialidades;
    }

}
