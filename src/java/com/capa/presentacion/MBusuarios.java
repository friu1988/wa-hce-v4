package com.capa.presentacion;

import com.capa.datos.TAccesos;
import com.capa.datos.TAccesosPK;
import com.capa.datos.TMenu;
import com.capa.datos.TPersonal;
import com.capa.datos.TUsuario;
import com.capa.negocios.TMenuFacade;
import com.capa.negocios.TUsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "mBusuarios")
@SessionScoped
public class MBusuarios implements Serializable {

    @EJB
    private TUsuarioFacade servicioUsuarios;
    @EJB
    private TMenuFacade servicioMenu;

    private TUsuario usuario;
    private TPersonal personal;

    private List<TUsuario> usuarios = null;

    public MBusuarios() {
    }

    public String goPermisos() {

        if (servicioUsuarios.buscarEstado(personal)) {
            usuario = servicioUsuarios.buscarUser(personal);
        } else {
            usuario = setUser();
        }
        usuarios = null;
        return "menu_asignar.xhtml";
    }

    public TUsuario setUser() {

        TUsuario user = null;
        try {
            user = new TUsuario(personal.getPerCedula(), "temporal.1", Boolean.FALSE, personal);
            servicioUsuarios.create(user);
            user = servicioUsuarios.buscarUser(personal);
            user.setTAccesosList(accesos(user));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este usuario ya dispone una Cuenta!" + e.getMessage(), null));
        }

        return user;
    }

    public List<TAccesos> accesos(TUsuario u) {

        List<TAccesos> accesos = new LinkedList<>();

        for (TMenu m : servicioMenu.findAll()) {

            TAccesos a = new TAccesos(new TAccesosPK(m.getMenSerial(), u.getUSerial()));

            if (m.getMenTipo().equals("A") && personal.getPerTipo().equals("A")) {
                setAccesoTrue(a);
            } else if (m.getMenTipo().equals("D") && personal.getPerTipo().equals("D")) {
                setAccesoTrue(a);
            } else if (m.getMenTipo().equals("M") && personal.getPerTipo().equals("M")) {
                setAccesoTrue(a);
            } else if (m.getMenTipo().equals("G")) {
                setAccesoTrue(a);
            }
            a.setTMenu(m);
            a.setTUsuario(u);
            accesos.add(a);
            System.out.println("Acceso: " + a);
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

    public String crearPermisos() {

        try {
            servicioUsuarios.edit(usuario);
            usuario = null;
            usuarios = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cuenta, permisos y accesos activados! ", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error transaccional!" + e.getMessage(), null));
        }

        return "personal.xhtml";
    }

    public String eliminarUser() {
        try {
            servicioUsuarios.remove(usuario);
            usuario = null;
            usuarios = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cuenta eliminada! ", null));
            return "personal.xhtml";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Eliminar" + e.getMessage(), null));
            return "personal.xhtml";
        }
    }

    public TPersonal getPersonal() {
        if (personal == null) {
            personal = new TPersonal();
        }
        return personal;
    }

    public void setPersonal(TPersonal personal) {
        this.personal = personal;
    }

    public TUsuario getUsuario() {
        if (usuario == null) {
            usuario = new TUsuario();
        }
        return usuario;
    }

    public void setUsuario(TUsuario usuario) {
        this.usuario = usuario;
    }

    public List<TUsuario> getUsuarios() {
        if (usuarios == null) {
            usuarios = servicioUsuarios.findAll();
        }
        return usuarios;
    }

    public void setUsuarios(List<TUsuario> usuarios) {
        this.usuarios = usuarios;
    }

}
