package com.capa.presentacion;

import com.capa.datos.TFacultad;
import com.capa.negocios.TFacultadFacade;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;

@Named(value = "mBfacultad")
//@ViewScoped
@SessionScoped
public class MBfacultad implements Serializable {

    private TFacultad facultad;
    private TFacultad carrera;
    private List<TFacultad> facultades;
    private List<TFacultad> carreras;

    @EJB
    private TFacultadFacade servicioFacultad;

    public MBfacultad() {
    }

    public void onChangeFac() {
        getCarreras();
        carreras = null;
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

    public TFacultad getCarrera() {
        if (carrera == null) {
            carrera = new TFacultad();
        }
        return carrera;
    }

    public void setCarrera(TFacultad carrera) {
        this.carrera = carrera;
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

    public void setFacultades(List<TFacultad> facultades) {
        this.facultades = facultades;
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

    public void setCarreras(List<TFacultad> carreras) {
        this.carreras = carreras;
    }

}
