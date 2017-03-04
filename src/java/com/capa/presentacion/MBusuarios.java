package com.capa.presentacion;

import com.capa.datos.TPersonal;
import com.capa.datos.TUsuario;
import com.capa.negocios.TUsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "mBusuarios")
@SessionScoped
public class MBusuarios implements Serializable {

    @EJB
    private TUsuarioFacade servicioUsuarios;
    private TUsuario usuario;
    private TPersonal personal;
    private List<TUsuario> usuarios;

    public MBusuarios() {
    }

    public void actualizar() {

    }

    public void eliminarUser() {
        try {
            servicioUsuarios.remove(usuario);
            usuario = null;
            usuarios = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado! " + usuario, null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Eliminar" + e.getMessage(), null));
        }
    }

    public String activarUsuario() {
        usuario.setPerSerial(personal);
        usuario.setUEstado(Boolean.TRUE);
        try {
            servicioUsuarios.create(usuario);
            usuario = null;
            usuarios = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Accesos autorizados! " + usuario, null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al ingresar" + e.getMessage(), null));
        }
        return "accesos.xhtml";
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
