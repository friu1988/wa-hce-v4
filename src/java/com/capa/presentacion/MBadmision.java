package com.capa.presentacion;

import com.capa.datos.TAdmisionista;
import com.capa.negocios.TAdmisionistaFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

@Named(value = "mBadmision")
@SessionScoped
public class MBadmision implements Serializable {

    @EJB
    private TAdmisionistaFacade servicioAdmision;
    private List<TAdmisionista> admisionistas;
    private TAdmisionista admisionista;

    public MBadmision() {
    }

    public List<TAdmisionista> getAdmisionistas() {
        if (admisionistas == null) {
            admisionistas = servicioAdmision.findAll();
        }
        return admisionistas;
    }

    public void setAdmisionistas(List<TAdmisionista> admisionistas) {
        this.admisionistas = admisionistas;
    }

    public TAdmisionista getAdmisionista() {
        if (admisionista == null) {
            admisionista = new TAdmisionista();
        }
        return admisionista;
    }

    public void setAdmisionista(TAdmisionista admisionista) {
        this.admisionista = admisionista;
    }

}
