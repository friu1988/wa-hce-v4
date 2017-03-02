package com.capa.presentacion;

import com.capa.datos.TMedico;
import com.capa.negocios.TMedicoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

@Named(value = "mBmedico")
@SessionScoped
public class MBmedico implements Serializable {

    @EJB
    private TMedicoFacade servicioMedico;
    private List<TMedico> medicos;
    private TMedico medico;

    public MBmedico() {
    }

    public List<TMedico> getMedicos() {
        if (medicos == null) {
            medicos = servicioMedico.findAll();
        }
        return medicos;
    }

    public void setMedicos(List<TMedico> medicos) {
        this.medicos = medicos;
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
