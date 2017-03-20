package com.capa.presentacion;

import com.capa.datos.TDias;
import com.capa.negocios.TDiasFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

@Named(value = "mBdias")
@SessionScoped
public class MBdias implements Serializable {

    @EJB
    private TDiasFacade servicioDias;

    private List<TDias> dias;

    public MBdias() {
    }

    public List<TDias> getDias() {
        if (dias == null) {
            dias = servicioDias.findAll();
        }
        return dias;
    }

    public void setDias(List<TDias> dias) {
        this.dias = dias;
    }

}
