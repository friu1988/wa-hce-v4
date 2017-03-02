package com.capa.presentacion;

import com.capa.datos.TPersonal;
import com.capa.datos.TUsuario;
import com.capa.negocios.TUsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author FREDDY
 */
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

    public void activarUsuario(){
        
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
