package com.capa.presentacion;

import com.capa.datos.TAdmisionista;
import com.capa.datos.TFacultad;
import com.capa.datos.TKardex;
import com.capa.datos.TLugarGeografico;
import com.capa.datos.TPaciente;
import com.capa.datos.TUsuario;
import com.capa.negocios.TFacultadFacade;
import com.capa.negocios.TLugarGeograficoFacade;
import com.capa.negocios.TPacienteFacade;
import java.awt.BorderLayout;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "mBhistoriaClinica")
@SessionScoped
public class MBhistoriaClinica implements Serializable {

    private TFacultad facultad;
    private TFacultad carrera;
    private TLugarGeografico provincia;
    private TLugarGeografico canton;
    private TLugarGeografico parroquia;
    private TPaciente paciente;
    private TPaciente selected;
    private TKardex kardex;
    private Integer numeroHCU;
    private Long edad;
    private boolean tipo = false;

    private List<TFacultad> facultades;
    private List<TFacultad> carreras;
    private List<TLugarGeografico> provincias;
    private List<TLugarGeografico> cantones;
    private List<TLugarGeografico> parroquias;
    private List<String> semestres;
    private List<String> nacionalidades;
    private List<TPaciente> pacientes = null;

    @EJB
    private TLugarGeograficoFacade servicioGeo;
    @EJB
    private TFacultadFacade servicioFacultad;
    @EJB
    private TPacienteFacade servicioPaciente;

    public MBhistoriaClinica() {

    }

    public String crearTurno() {
        return "turno_crear.xhtml";
    }

    public void tipoPacienteCrear() {
        if (paciente.getHclTipoPaciente().equals("P")) {
            tipo = true;
            facultades = carreras = null;
            semestres = null;

            facultad = carrera = null;
            paciente.setHclSemestre(null);
        } else {
            tipo = false;
        }
    }

    public void calcularEdad() {
        edad = Utilidades.calcularEdad(paciente);
    }

    public String goPacientes() {
        paciente = null;
        pacientes = null;
        return "pacientes.xhtml";
    }

    public String goHistoriaCrear() {
        paciente = new TPaciente();
        paciente.setHclTipoPaciente("E");

        if (paciente.getHclTipoPaciente().equals("P")) {
            tipo = true;
        } else {
            tipo = false;
        }

        facultades = carreras = null;
        provincias = cantones = parroquias = null;
        semestres = null;

        facultad = carrera = null;
        provincia = canton = parroquia = null;

        numeroHCU = null;
        return "historia_crear.xhtml";
    }

    public String crearHCU() {
        try {
            //Personal
            TUsuario user = (TUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLogin");
            TAdmisionista admisionista = new TAdmisionista();
            Calendar cal = Calendar.getInstance();

            admisionista.setPerSerial(user.getPerSerial().getPerSerial());

            paciente.setHclNumeroHistoria(String.valueOf(getNumeroHCU()));

            //Facultad
            if (paciente.getHclTipoPaciente().equals("P")) {
                facultad.setFacSerial("na");
                paciente.setFacSerial(facultad);
            } else {
                paciente.setFacSerial(carrera);
            }

            //Lugar GEO
            paciente.setLgCodigo(parroquia);
            //Admisionista
            paciente.setPerSerial(admisionista);
            //HCU
            paciente.setHclInstitucion("UCE");
            paciente.setHclUnidadOperativa("Archivo");
            paciente.setHclCodigoOu("OU");
            paciente.setHclClParroquia("Belisario Quevedo");
            paciente.setHclClCanton("Quito");
            paciente.setHclClProvincia("Pichincha");
            paciente.setHclFechaAdmision(cal.getTime());

            servicioPaciente.create(paciente);

            pacientes = null;
            paciente = null;

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Historia Clínica Ingresda! ", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Ingresar" + e.getMessage(), null));
        }
        return "pacientes.xhtml";
    }

    public String goHistoriaVer() {
        edad = Utilidades.calcularEdad(paciente);
        paciente.setHclTipoPaciente(getTipoPaciente(paciente));
        paciente.setPacInstruccion(getInstruccion(paciente));
        paciente.setPacEstadoCivil(getEstadoCivil(paciente));
        paciente.setPacSexo(getSexo(paciente));
        paciente.setPacGrupoCultural(getGrupoCultural(paciente));
        paciente.setPacZona(getZona(paciente));
        return "historia_ver.xhtml";
    }

    public String getZona(TPaciente paciente) {
        try {
            return paciente.getPacZona().equals("U") ? "Urbano" : "Rural";
        } catch (Exception e) {
            return "";
        }

    }

    public String getGrupoCultural(TPaciente paciente) {
        try {
            switch (paciente.getPacGrupoCultural()) {
                case "BL":
                    paciente.setPacGrupoCultural("Blanco");
                    break;
                case "IN":
                    paciente.setPacGrupoCultural("Indigena");
                    break;
                case "ME":
                    paciente.setPacGrupoCultural("Mestizo");
                    break;
                case "MU":
                    paciente.setPacGrupoCultural("Mulato");
                    break;
                case "NE":
                    paciente.setPacGrupoCultural("Negro");
                    break;
                default:
                    paciente.setPacGrupoCultural("No Registrado");
                    break;
            }
            return paciente.getPacGrupoCultural();
        } catch (Exception e) {
            return "";
        }
    }

    public String getSexo(TPaciente paciente) {
        try {
            return paciente.getPacSexo().equals("M") ? "Masculino" : "Femenino";

        } catch (Exception e) {
            return "";
        }
    }

    public String getEstadoCivil(TPaciente paciente) {
        try {
            switch (paciente.getPacEstadoCivil()) {
                case "CA":
                    paciente.setPacEstadoCivil("Casado");
                    break;
                case "DI":
                    paciente.setPacEstadoCivil("Divorciado");
                    break;
                case "SO":
                    paciente.setPacEstadoCivil("Soltero");
                    break;
                case "UL":
                    paciente.setPacEstadoCivil("Unión Libre");
                    break;
                case "VI":
                    paciente.setPacEstadoCivil("Viudo");
                    break;
                default:
                    paciente.setPacEstadoCivil("No Registrado");
                    break;
            }
            return paciente.getPacEstadoCivil();
        } catch (Exception e) {
            return "";
        }
    }

    public String getInstruccion(TPaciente paciente) {
        try {
            switch (paciente.getPacInstruccion()) {
                case "PN":
                    paciente.setPacInstruccion("Primer Nivel");
                    break;
                case "SN":
                    paciente.setPacInstruccion("Segundo Nivel");
                    break;
                case "TN":
                    paciente.setPacInstruccion("Tercer Nivel");
                    break;
                case "CN":
                    paciente.setPacInstruccion("Cuarto Nivel");
                    break;
                case "NN":
                    paciente.setPacInstruccion("Ningun Nivel");
                    break;
                default:
                    paciente.setPacInstruccion("No Registrado");
                    break;
            }
            return paciente.getPacInstruccion();
        } catch (Exception e) {
            return "";
        }
    }

    public String getTipoPaciente(TPaciente paciente) {
        try {
            return paciente.getHclTipoPaciente().equals("P") ? "Particular" : "Estudiante";
        } catch (Exception e) {
            return "";
        }
    }

    public String goHistoriaEditar() {
        if (paciente.getHclTipoPaciente().equals("P")) {
            tipo = true;
        } else {
            tipo = false;
        }
        return "historia_editar.xhtml";
    }

    public void tipoPacienteEditar() {
        if (paciente.getHclTipoPaciente().equals("P")) {
            tipo = true;
            facultades = carreras = null;
            semestres = null;

            facultad = carrera = null;
            paciente.setHclSemestre(null);
        } else {
            tipo = false;
        }
    }

    public String editarHCU() {
        try {
            //Personal
            TUsuario user = (TUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLogin");
            TAdmisionista admisionista = new TAdmisionista();
            Calendar cal = Calendar.getInstance();
            admisionista.setPerSerial(user.getPerSerial().getPerSerial());

            //Facultad
            if (paciente.getHclTipoPaciente().equals("P")) {
                facultad.setFacSerial("na");
                paciente.setFacSerial(facultad);
                facultades = carreras = null;
                carrera = null;
            } else {
                paciente.setFacSerial(carrera);
            }

            //Lugar GEO
            paciente.setLgCodigo(parroquia);
            //Admisionista
            paciente.setPerSerial(admisionista);
            //HCU
            paciente.setHclInstitucion("UCE");
            paciente.setHclUnidadOperativa("Archivo");
            paciente.setHclCodigoOu("OU");
            paciente.setHclClParroquia("Belisario Quevedo");
            paciente.setHclClCanton("Quito");
            paciente.setHclClProvincia("Pichincha");
            paciente.setHclFechaAdmision(cal.getTime());

            servicioPaciente.edit(paciente);

            pacientes = null;
            paciente = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Historia Clínica Actualizada! ", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Actualizar" + e.getMessage(), null));
        }
        return "pacientes.xhtml";
    }

    public void eliminarHCU() {
        try {
            servicioPaciente.remove(paciente);
            paciente = null;
            pacientes = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Historia Clínica Eliminada! ", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Eliminar" + e.getMessage(), null));
        }
    }

    public void onChangeFacultades() {
        getCarreras();
        carreras = null;
    }

    public List<TFacultad> getFacultades() {
        if (facultades == null) {
            facultades = new LinkedList<>();
            for (TFacultad fac : servicioFacultad.findAll()) {
                if (fac.getTFFacSerial() == null) {
                    facultades.add(fac);
                }
            }
        }
        return facultades;
    }

    public List<TFacultad> getCarreras() {
        if (carreras == null) {
            carreras = new LinkedList<>();
            for (TFacultad car : servicioFacultad.buscarCarreras(facultad)) {
                carreras.add(car);
            }
        }
        return carreras;
    }

    public void onChangeProvincias() {
        if (provincia != null && !provincia.getLgCodigo().equals("")) {
            provincias = cantones = parroquias = null;
            canton = parroquia = null;
            getCantones();
        }

    }

    public void onChangeCantones() {
        if (canton != null && !canton.getLgCodigo().equals("")) {
            parroquias = null;
            getParroquias();
        }
    }

    public List<TLugarGeografico> getProvincias() {
        if (provincias == null) {
            provincias = new LinkedList<>();
            for (TLugarGeografico prov : servicioGeo.findAll()) {
                if (prov.getPadreLgCodigo() == null) {
                    provincias.add(prov);
                }
            }
        }
        return provincias;
    }

    public List<TLugarGeografico> getCantones() {
        if (cantones == null) {
            cantones = new LinkedList<>();
            for (TLugarGeografico can : servicioGeo.buscarHijosGeo(provincia)) {
                cantones.add(can);
            }
        }
        return cantones;
    }

    public List<TLugarGeografico> getParroquias() {
        if (parroquias == null) {
            parroquias = new LinkedList<>();
            for (TLugarGeografico parr : servicioGeo.buscarHijosGeo(canton)) {
                parroquias.add(parr);
            }
        }
        return parroquias;
    }

    public List<String> getNacionalidades() {
        if (nacionalidades == null) {
            nacionalidades = new LinkedList<>();
            nacionalidades.add("Canadiense");
            nacionalidades.add("Estadounidense");
            nacionalidades.add("Mexicana");
            nacionalidades.add("Beliceña");
            nacionalidades.add("Costarricense");
            nacionalidades.add("Guatemalteca");
            nacionalidades.add("Hondureña");
            nacionalidades.add("Nicaragüense");
            nacionalidades.add("Panameña");
            nacionalidades.add("Salvadoreña");
            nacionalidades.add("Cubana");
            nacionalidades.add("Arubana");
            nacionalidades.add("Bahameña");
            nacionalidades.add("Barbadense");
            nacionalidades.add("Dominiquesa");
            nacionalidades.add("Dominicana");
            nacionalidades.add("Haitiana");
            nacionalidades.add("Jamaiquina");
            nacionalidades.add("Puertorriqueña");
            nacionalidades.add("Sancristobaleña");
            nacionalidades.add("Santaluciana");
            nacionalidades.add("Sanvicentina");
            nacionalidades.add("Argentina");
            nacionalidades.add("Boliviana");
            nacionalidades.add("Brasileña");
            nacionalidades.add("Chilena");
            nacionalidades.add("Colombiana");
            nacionalidades.add("Ecuatoriana");
            nacionalidades.add("Guyanesa");
            nacionalidades.add("Paraguaya");
            nacionalidades.add("Peruana");
            nacionalidades.add("Surinamesa");
            nacionalidades.add("Uruguaya");
            nacionalidades.add("Venezolana");
        }
        Collections.sort(nacionalidades);
        return nacionalidades;
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

    public TPaciente getPaciente() {
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
        return cargarSemestres();
    }

    public void setSemestres(List<String> semestres) {
        this.semestres = semestres;
    }

    public TFacultad getFacultad() {
        if (facultad == null) {
            facultad = new TFacultad();
        }
        return facultad;
    }

    public void setFacultad(TFacultad facultad) {
        this.facultad = facultad;
    }

    public TLugarGeografico getProvincia() {
        if (provincia == null) {
            provincia = new TLugarGeografico();
        }
        return provincia;
    }

    public void setProvincia(TLugarGeografico provincia) {
        this.provincia = provincia;
    }

    public TLugarGeografico getCanton() {
        if (canton == null) {
            canton = new TLugarGeografico();
        }
        return canton;
    }

    public void setCanton(TLugarGeografico canton) {
        this.canton = canton;
    }

    public TLugarGeografico getParroquia() {
        if (parroquia == null) {
            parroquia = new TLugarGeografico();
        }
        return parroquia;
    }

    public void setParroquia(TLugarGeografico parroquia) {
        this.parroquia = parroquia;
    }

    public TPaciente getSelected() {
        return selected;
    }

    public void setSelected(TPaciente selected) {
        this.selected = selected;
    }

    public List<TPaciente> getPacientes() {
        if (pacientes == null) {
            pacientes = servicioPaciente.findAll();
        }
        return pacientes;
    }

    public void setPacientes(List<TPaciente> pacientes) {
        this.pacientes = pacientes;
    }

    public Integer getNumeroHCU() {
        numeroHCU = servicioPaciente.findMaxHCU();
        numeroHCU = numeroHCU + 1;
        return numeroHCU;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public Long getEdad() {
        return edad;
    }

    public void setEdad(Long edad) {
        this.edad = edad;
    }

}
