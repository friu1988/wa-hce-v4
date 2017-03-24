/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.presentacion;

import com.capa.datos.TEspecialidad;
import com.capa.datos.TMedico;
import com.capa.negocios.TEspecialidadFacade;
import com.capa.negocios.TMedicoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author FREDDY
 */
@Named(value = "mBespecialidad")
@SessionScoped
public class MBespecialidad implements Serializable {

    @EJB
    private TEspecialidadFacade servicioEspecialidad;

    private List<TEspecialidad> especialidades;

    public MBespecialidad() {
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
