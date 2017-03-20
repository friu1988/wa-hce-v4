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
    @EJB
    private TMedicoFacade servicioMedico;

    private TEspecialidad especialidad;
    private TreeNode root = null;

    private List<TEspecialidad> especialidades;
    private List<TMedico> medicos;

    public MBespecialidad() {
    }

    public void cargarRaiz() {
        try {
            especialidades = servicioEspecialidad.findAll();
            for (TEspecialidad it : especialidades) {
                TreeNode raiz = new DefaultTreeNode(it, this.root);
//                raiz.setExpanded(true);
                cargarNodo(it, raiz);
                System.out.println("Especialidad>>" + root.getChildren().toString());
            }
        } catch (Exception e) {
            System.out.println("Error al cargar raíz" + e.getMessage());
        }
    }

    public void cargarNodo(TEspecialidad it, TreeNode raiz) {
//        try {
//            medicos = it.getTMedicoList();
//            for (TMedico it1 : medicos) {
//                TreeNode nodo = new DefaultTreeNode(it1, raiz);
//                System.out.println("Medico>>" + raiz.getChildren().toString());
//            }
//        } catch (Exception e) {
//            System.out.println("Error al cargar nodos" + e.getMessage());
//        }
    }

    public TreeNode getRoot() {
        if (root == null) {
            root = new DefaultTreeNode();
            cargarRaiz();
        }
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public List<TMedico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<TMedico> medicos) {
        this.medicos = medicos;
    }

    public TEspecialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(TEspecialidad especialidad) {
        this.especialidad = especialidad;
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
