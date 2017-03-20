package com.capa.presentacion;

import com.capa.datos.TPersonal;
import com.capa.negocios.TPersonalFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "mBpersonal")
@SessionScoped
public class MBpersonal implements Serializable {

    @EJB
    private TPersonalFacade servicioPersonal;
    private TPersonal empleado;
    private List<TPersonal> empleados;

    public MBpersonal() {
    }

    public TPersonal prepararCreate() {
        empleado = new TPersonal();
        return empleado;
    }

    public void crearEmpleado() {
        try {
            servicioPersonal.create(empleado);
            empleado = null;
            empleados = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nomina actualizada! ", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al ingresar" + e.getMessage(), null));
        }
    }

    public void editarEmpleado() {
        try {
            servicioPersonal.edit(empleado);
            empleado = null;
            empleados = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro actualizado! ", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar" + e.getMessage(), null));
        }
    }

    public void eliminarEmpleado() {
        try {
            servicioPersonal.remove(empleado);
            empleado = null;
            empleados = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado! ", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Eliminar" + e.getMessage(), null));
        }
    }

    public TPersonal getPersona() {
        return empleado;
    }

    public void setPersona(TPersonal persona) {
        this.empleado = persona;
    }

    public List<TPersonal> getPersonas() {
        if (empleados == null) {
            empleados = servicioPersonal.findAll();
        }
        return empleados;
    }

    public void setPersonas(List<TPersonal> personas) {
        this.empleados = personas;
    }
}
