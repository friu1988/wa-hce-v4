package com.capa.presentacion;

import com.capa.datos.TAdmisionista;
import com.capa.datos.TEspecialidad;
import com.capa.datos.TMedico;
import com.capa.datos.TPaciente;
import com.capa.datos.TTurno;
import com.capa.datos.TUsuario;
import com.capa.negocios.TAdmisionistaFacade;
import com.capa.negocios.TPersonalSaludFacade;
import com.capa.negocios.TTurnoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "mBturno")
@SessionScoped
public class MBturno implements Serializable {

    @EJB
    private TTurnoFacade servicioTurno;
    @EJB
    private TAdmisionistaFacade servicioAdmision;
    @EJB
    private TPersonalSaludFacade servicioPSalud;

    private List<TTurno> turnos;
    private TTurno turno;

    private TAdmisionista admisionista;
    private TPaciente paciente;
    private TMedico medico;
    
    private TEspecialidad especialidad;

    public MBturno() {
        admisionista = new TAdmisionista();
    }

    @PostConstruct
    public void init() {
        //Admisionista
        TUsuario user = (TUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLogin");
        admisionista.setPerSerial(user.getPerSerial().getPerSerial());

//        servicioAdmision.find(user.getPerSerial().getPerSerial());
        System.out.println("Admisionista: " + admisionista);
    }

    public String goCrearTurno() {

        return "turno_crear.xhtml";
    }
    
    public String crearTurno() {
        turno.setPerSerial(admisionista);
        turno.setPacCedula(paciente);
        turno.setTMPerSerial(medico);

        servicioTurno.create(turno);

        turnos = null;

        return "turnos.xhml";
    }

    

    public String verT() {
        System.out.println("Ver>>>");
        return "pacientes.xhtml";
    }

    public String editarT() {
        System.out.println("Editar>>>");
        return "pacientes.xhtml";
    }

    public void eliminarTurno() {
        try {
            servicioTurno.remove(turno);
            turno = null;
            turnos = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Turno Eliminado! " + turno, null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Eliminar" + e.getMessage(), null));
        }
    }

    public List<TTurno> getTurnos() {
        if (turnos == null) {
            turnos = servicioTurno.findAll();
        }
        return turnos;
    }

    public void setTurnos(List<TTurno> turnos) {
        this.turnos = turnos;
    }

    public TTurno getTurno() {
        if (turno == null) {
            turno = new TTurno();
        }
        return turno;
    }

    public void setTurno(TTurno turno) {
        this.turno = turno;
    }

    public TAdmisionista getAdmisionista() {
        return admisionista;
    }

    public void setAdmisionista(TAdmisionista admisionista) {
        this.admisionista = admisionista;
    }

    public TPaciente getPaciente() {
        if (paciente == null) {
            paciente = new TPaciente();
        }
        return paciente;
    }

    public void setPaciente(TPaciente paciente) {
        this.paciente = paciente;
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

}
