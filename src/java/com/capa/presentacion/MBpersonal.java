package com.capa.presentacion;

import com.capa.datos.TAccesos;
import com.capa.datos.TAccesosPK;
import com.capa.datos.TMenu;
import com.capa.datos.TPersonal;
import com.capa.datos.TUsuario;
import com.capa.negocios.TMenuFacade;
import com.capa.negocios.TPersonalFacade;
import com.capa.negocios.TUsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "mBpersonal")
@SessionScoped
public class MBpersonal implements Serializable {

    @EJB
    private TPersonalFacade servicioPersonal;
    @EJB
    private TUsuarioFacade servicioUsuario;
    @EJB
    private TMenuFacade servicioMenu;

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

    public void eliminarEmpleado() {
        try {
            servicioPersonal.remove(empleado);
            empleado = null;
            empleados = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado! ", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Eliminar: " + e.getMessage(), null));
        }
    }

    public void editarEmpleado() {
        try {
            servicioPersonal.edit(empleado);
            actualizarAccesos();
            empleado = null;
            empleados = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro actualizado! ", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar" + e.getMessage(), null));
        }
    }

    public void actualizarAccesos() {
        TUsuario user;

        if (servicioUsuario.buscarEstado(empleado)) {
            user = servicioUsuario.buscarUser(empleado);
            servicioUsuario.remove(user);
            setUser();
        }
    }

    public TUsuario setUser() {

        TUsuario user = null;
        try {
            user = new TUsuario(empleado.getPerCedula(), "temporal.1", Boolean.FALSE, empleado);
            servicioUsuario.create(user);
            user = servicioUsuario.buscarUser(empleado);
            user.setTAccesosList(accesos(user));
            servicioUsuario.edit(user);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear Accesos! " + e.getMessage(), null));
        }

        return user;
    }

    public List<TAccesos> accesos(TUsuario u) {

        List<TAccesos> accesos = new LinkedList<>();

        for (TMenu m : servicioMenu.findAll()) {

            TAccesos a = new TAccesos(new TAccesosPK(m.getMenSerial(), u.getUSerial()));

            if (m.getMenTipo().equals("A") && empleado.getPerTipo().equals("A")) {
                setAccesoTrue(a);
            } else if (m.getMenTipo().equals("D") && empleado.getPerTipo().equals("D")) {
                setAccesoTrue(a);
            } else if (m.getMenTipo().equals("M") && empleado.getPerTipo().equals("M")) {
                setAccesoTrue(a);
            } else if (m.getMenTipo().equals("G")) {
                setAccesoTrue(a);
            }
            a.setTMenu(m);
            a.setTUsuario(u);
            accesos.add(a);
        }
        return accesos;
    }

    public void setAccesoTrue(TAccesos acceso) {
        acceso.setAcCreate(Boolean.TRUE);
        acceso.setAcRead(Boolean.TRUE);
        acceso.setAcUpdate(Boolean.TRUE);
        acceso.setAcDelete(Boolean.TRUE);
        acceso.setAcPagina(Boolean.TRUE);
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
