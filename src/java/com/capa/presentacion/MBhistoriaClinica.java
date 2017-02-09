package com.capa.presentacion;

import com.capa.datos.TFacultad;
import com.capa.datos.TKardex;
import com.capa.datos.TLugarGeografico;
import com.capa.datos.TPaciente;
import com.capa.datos.TUsuario;
import com.capa.negocios.TPacienteFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author FREDDY
 */
@Named(value = "mBhistoriaClinica")
@SessionScoped
public class MBhistoriaClinica implements Serializable {

    private TLugarGeografico lugarGeografico;
    private TPaciente paciente;
    private TKardex kardex;
    private TFacultad carrera;

    private boolean tipoPaciente;
    private String numeroHCU;

    private List<String> semestres;

    @EJB
    private TPacienteFacade servicioPaciente;

    public MBhistoriaClinica() {

    }

    @PostConstruct
    public void init() {
    }

    public String crearHistoria() {
        System.out.println("Crear paciente>>>>>>>>>>>>>");
        return "historia_crear.xhtml";
    }

    public String verHistoria() {
        System.out.println("Ver>>>>>>>>>>>>>");
        return "historia_ver.xhtml";
    }

    public String editarHistoria() {
        System.out.println("Editar>>>>>>>>>>>>>");
        return "historia_editar.xhtml";
    }

    public void click() {
    }

    public void crearHCU() {
        //Personal Admision
        TUsuario user = (TUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLogin");
        System.out.println(user);
//
//        TAdmisionista admisionista = new TAdmisionista();
//        admisionista.setPerSerial(user.getPerSerial().getPerSerial());
//        System.out.println(admisionista);

        //Paciente 
//        paciente.setPerSerial(admisionista);
//        paciente.setPacCedula(paciente.getPacCedula());
        //Facultad
//        paciente.setFacSerial(carrera);
        //HCU
//        paciente.setHclInstitucion("UCE");
//        paciente.setHclUnidadOperativa("Archivo");
//        paciente.setHclCodigoOu("OU");
//        paciente.setHclClParroquia("Belisario Quevedo");
//        paciente.setHclClCanton("Quito");
//        paciente.setHclClProvincia("Pichincha");
//        paciente.setHclNumeroHistoria("");
//
//        System.out.println(paciente);
    }

//    public List<TFacultad> filtrarFacultades() {
//        List<TFacultad> temporal = new LinkedList<>();
//        for (TFacultad fac : servicioFacultad.findAll()) {
//            if (fac.getTFFacSerial() == null) {
//                temporal.add(fac);
//            }
//        }
//        return temporal;
//    }
//
//    public List<TFacultad> filtrarCarreras(TFacultad facultad) {
//        List<TFacultad> temporal = new LinkedList<>();
//        for (TFacultad carrera : servicioFacultad.buscarCarreras(facultad)) {
//            System.out.println(carrera);
//            temporal.add(carrera);
//        }
//        return temporal;
//    }
    public List<String> cargarSemestres() {
        List<String> temporal = new LinkedList<>();
        temporal.add("Propedéutico");
        temporal.add("Primero");
        temporal.add("Segundo");
        temporal.add("Tercero");
        temporal.add("Cuarto");
        temporal.add("Quinto");
        temporal.add("Sexto");
        temporal.add("Séptimo");
        temporal.add("Octavo");
        temporal.add("Noveno");
        temporal.add("Décimo");
        temporal.add("Egresado");
        return temporal;
    }

    public TLugarGeografico getLugarGeografico() {
        if (lugarGeografico == null) {
            lugarGeografico = new TLugarGeografico();
        }
        return lugarGeografico;
    }

    public void setLugarGeografico(TLugarGeografico lugarGeografico) {
        this.lugarGeografico = lugarGeografico;
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

    public TKardex getKardex() {
        if (kardex == null) {
            kardex = new TKardex();
        }
        return kardex;
    }

    public void setKardex(TKardex kardex) {
        this.kardex = kardex;
    }

    public String getNumeroHCU() {
        return numeroHCU;
    }

    public void setNumeroHCU(String numeroHCU) {
        this.numeroHCU = numeroHCU;
    }

    public TFacultad getCarrera() {
        if (carrera == null) {
            carrera = new TFacultad();
        }
        return carrera;
    }

    public void setCarrera(TFacultad carrera) {
        this.carrera = carrera;
    }

    public List<String> getSemestres() {
        if (semestres == null) {
            semestres = cargarSemestres();
        }
        return semestres;
    }

    public void setSemestres(List<String> semestres) {
        this.semestres = semestres;
    }

    public boolean isTipoPaciente() {
        return tipoPaciente;
    }

    public void setTipoPaciente(boolean tipoPaciente) {
        this.tipoPaciente = tipoPaciente;
    }

}
