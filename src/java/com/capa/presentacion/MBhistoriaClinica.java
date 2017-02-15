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
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
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
    private String fechaAdmision;
    private Integer numeroHCU;

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

    @PostConstruct
    public void init() {
    }

    public String crearHistoria() {
        System.out.println("Crear historia clinica>>>>>>>>>>>>>");
        paciente = new TPaciente();
        numeroHCU = null;
        return "historia_crear.xhtml";
    }

    public String verHistoria() {
        System.out.println("Ver>>>>>>>>>>>>>");

        return "historia_ver.xhtml";
    }

    public String editarHistoria() {
        System.out.println("Editar>>>>>>>>>>>>>");
        return "historia_crear.xhtml";
    }

    public void click() {
    }

    public String crearHCU() {
        System.out.println("Crear paciente >>>>>>>>");
        //Personal
        TUsuario user = (TUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLogin");
        TAdmisionista admisionista = new TAdmisionista();
        admisionista.setPerSerial(user.getPerSerial().getPerSerial());
        System.out.println(user);
        System.out.println(admisionista);

        //Facultad
        paciente.setFacSerial(carrera);
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

        //Por implementar N-HCU
        paciente.setHclNumeroHistoria(String.valueOf(getNumeroHCU()));
//        paciente.setHclTipoPaciente(this.paciente.getHclTipoPaciente());
        paciente.setHclSemestre(this.paciente.getHclSemestre());
        paciente.setPacApellidoPaterno(this.paciente.getPacApellidoPaterno());
        paciente.setPacApellidoMaterno(this.paciente.getPacApellidoMaterno());
        paciente.setPacPrimerNombre(this.paciente.getPacPrimerNombre());
        paciente.setPacSegundoNombre(this.paciente.getPacSegundoNombre());
        paciente.setPacCedula(this.paciente.getPacCedula());
        paciente.setPacDireccionResidencial(this.paciente.getPacDireccionResidencial());
        paciente.setPacBarrio(this.paciente.getPacBarrio());
        paciente.setPacZona(this.paciente.getPacZona());
        paciente.setPacTelefono(this.paciente.getPacTelefono());
        paciente.setPacFechaNacimiento(this.paciente.getPacFechaNacimiento());
        paciente.setPacLugarNacimiento(this.paciente.getPacLugarNacimiento());
        paciente.setPacNacionalidad(this.paciente.getPacNacionalidad());
        paciente.setPacGrupoCultural(this.paciente.getPacGrupoCultural());
        paciente.setPacSexo(this.paciente.getPacSexo());
        paciente.setPacEstadoCivil(this.paciente.getPacEstadoCivil());
        paciente.setPacInstruccion(this.paciente.getPacInstruccion());
        Calendar cal = Calendar.getInstance();
        paciente.setHclFechaAdmision(cal.getTime());
        paciente.setPacOcupacion(this.paciente.getPacOcupacion());
        paciente.setPacEmpresaLabora(this.paciente.getPacEmpresaLabora());
        paciente.setPacSeguroSalud(this.paciente.getPacSeguroSalud());
        paciente.setPacReferidoDe(this.paciente.getPacReferidoDe());
        paciente.setPacTelefonoFamiliar(this.paciente.getPacTelefonoFamiliar());
        paciente.setPacParentesco(this.paciente.getPacParentesco());
        paciente.setPacDireccionPariente(this.paciente.getPacDireccionPariente());
        paciente.setPacTelefonoFamiliar(this.paciente.getPacTelefonoFamiliar());

        try {
            servicioPaciente.create(paciente);
            pacientes = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Historia Clínica Ingresda! " + paciente, null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Ingresar" + e.getMessage(), null));
        }

        System.out.println(paciente);
        return "pacientes.xhtml";
    }

    public void onChangeFac() {
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
            cantones = null;
            parroquias = null;
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
        if (semestres == null) {
            semestres = cargarSemestres();
        }
        return semestres;
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

    public String getFechaAdmision() {
        return getFechaFormato(new SimpleDateFormat("dd'/'MMMM'/'yyyy HH:mm:ss", new Locale("es", "EC")));
    }

    public String getFechaFormato(SimpleDateFormat formateador) {
        return formateador.format(new Date());
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

}
