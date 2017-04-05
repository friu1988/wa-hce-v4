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
import com.capa.negocios.TConsultorioFacade;
import com.capa.negocios.TDiasFacade;
import com.capa.negocios.TEspecialidadFacade;
import com.capa.negocios.THorarioFacade;
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
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
//import javax.faces.view.ViewScoped;

@Named(value = "mBmedicos")
@SessionScoped
//@ViewScoped
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
    @EJB
    private TConsultorioFacade servicioConsultorio;
    @EJB
    private THorarioFacade servicioHorario;

    private TPersonalSalud personalSalud;
    private THorario horario;
    private TMedico medico;

    private List<TMedico> medicos;
    private List<SelectItem> listaDias;
    private List<CargaHoraria> listasCargaHoraria;
    private List<TEspecialidad> especialidades;
    private List<TConsultorio> consultorios;
    private List<CargaHoraria> cargaHorariaDB;

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

    public String eliminarCargaHoraria() {
        try {
            if (tieneCargaHoraria()) {
                List<THorario> ho = servicioHorario.findHo(medico);
                List<TPersonalSalud> pS = servicioPersonalSalud.findPS(medico);
                for (int i = 0; i < listasCargaHoraria.size(); i++) {
                    servicioPersonalSalud.remove(pS.get(i));
                    servicioHorario.remove(ho.get(i));
                }

            }
            listasCargaHoraria = null;
            listaDias = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Carga Horaria Eliminada! ", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Eliminar" + e.getMessage(), null));
        }
        return "medicos_asignar.xhtml";
    }

    public String guardarCargaHoraria() {

        for (CargaHoraria item : listasCargaHoraria) {
            THorario ho = new THorario(new THorarioPK(item.getDias().getDSerial(), medico.getPerSerial()), item.getHoraInicio(), item.getHoraFin(), medico, item.getDias());
            TPersonalSalud pS = new TPersonalSalud(item.getEspecialidad1(), item.getConsultorio1(), medico);
            try {
                if (cargaHorariaDB != null) {
                    if (!cargaHorariaDB.contains(item)) {
                        servicioPersonalSalud.create(pS);
                        servicioHorario.create(ho);
                        setDefaultValues();
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Carga Horaria Asignada! ", null));
                    }
                } else {
                    servicioPersonalSalud.create(pS);
                    servicioHorario.create(ho);
                    setDefaultValues();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Carga Horaria Asignada! ", null));
                }

            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Guardar" + e.getMessage(), null));
            }
        }
        return "medicos.xhtml";
    }

    private void setDefaultValues() {
        diasSeleccionados = null;
        personalSalud = null;
        horario = null;
        listaDias = null;
        listasCargaHoraria = null;
    }

    public void agregarCargaHoraria() {

        if (!diasSolapados()) {
            for (int i = 0; i < this.diasSeleccionados.length; i++) {
                TDias dia = servicioDias.find((new TDias(diasSeleccionados[i])).getDSerial());
                TEspecialidad esp = servicioEspecialidad.find(personalSalud.getEspSerial().getEspSerial());
                TConsultorio co = servicioConsultorio.find(personalSalud.getCoSerial().getCoSerial());

                CargaHoraria cargaHoraria = new CargaHoraria(dia, esp, co, this.horario.getHoraInicio(), this.horario.getHoraFin());
                listasCargaHoraria.add(cargaHoraria);
                listaDias.get(diasSeleccionados[i] - 1).setDisabled(true);
            }
            horario = null;
            personalSalud = null;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Día ya ingresado!", null));
        }
    }

    private boolean diasSolapados() {
        for (int i = 0; i < this.diasSeleccionados.length; i++) {
            for (CargaHoraria item : listasCargaHoraria) {
                if (item.getDias().getDSerial() == diasSeleccionados[i]) {
                    listaDias.get(i).setDisabled(true);
                    return true;
                }
            }
        }
        return false;
    }

    public String goAsignaciones() {
        if (tieneCargaHoraria()) {
            listasCargaHoraria = llenarCargaHorario();
        } else {
            setDefaultValues();
        }
        return "medicos_asignar.xhtml";
    }

    private boolean tieneCargaHoraria() {
        return (servicioHorario.findHo(medico)) != null;
    }

    private List<CargaHoraria> llenarCargaHorario() {
        this.listaDias = null;
        this.getListaDias();
        List<CargaHoraria> cargaHorarias = new LinkedList<>();
        cargaHorariaDB = new LinkedList<>();

        List<THorario> horarios = servicioHorario.findHo(medico);
        List<TPersonalSalud> personalSaluds = servicioPersonalSalud.findPS(medico);
        for (int i = 0; i < horarios.size(); i++) {
            CargaHoraria cargaHo = new CargaHoraria(horarios.get(i).getTDias(), personalSaluds.get(i).getEspSerial(), personalSaluds.get(i).getCoSerial(), horarios.get(i).getHoraInicio(), horarios.get(i).getHoraFin());
            cargaHorarias.add(cargaHo);
            cargaHorariaDB.add(cargaHo);
            listaDias.get(horarios.get(i).getTDias().getDSerial() - 1).setDisabled(true);
        }

        return cargaHorarias;
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
        if (personalSalud == null) {
            personalSalud = new TPersonalSalud(new TEspecialidad(), new TConsultorio(), medico);
        }
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
        if (listasCargaHoraria == null) {
            listasCargaHoraria = new LinkedList<>();
        }
        return listasCargaHoraria;
    }

    public void setListasCH(List<CargaHoraria> listasCH) {
        this.listasCargaHoraria = listasCH;
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

    public List<TConsultorio> getConsultorios() {
        if (consultorios == null) {
            consultorios = servicioConsultorio.findAll();
        }
        return consultorios;
    }

    public void setConsultorios(List<TConsultorio> consultorios) {
        this.consultorios = consultorios;
    }

    //verificar q los tiempos sean exactos(para que retorner valores enteros exactos)
    private List<TTurno[]> generarTurnos(TMedico medico, int intervalo/*minutos*/) {
        //supongamos q tal dia es este(se debe buscar segun el dia)
        THorario horario = new THorario();
        horario.setHoraInicio(new Date(2016, 5, 3, 13, 30, 0));
        horario.setHoraFin(new Date(2017, 5, 3, 18, 30, 0));
        return null;
        /*      
        if (medico.getTHorarioList().contains(dia)) {//verificar metodo booleanom
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

}
