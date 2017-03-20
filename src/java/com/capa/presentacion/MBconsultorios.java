package com.capa.presentacion;

import com.capa.datos.TConsultorio;
import com.capa.negocios.TConsultorioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

@Named(value = "mBconsultorios")
@SessionScoped
public class MBconsultorios implements Serializable {

    @EJB
    private TConsultorioFacade servicioConsultorio;
    private List<TConsultorio> consultorios;

    public MBconsultorios() {
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

}
