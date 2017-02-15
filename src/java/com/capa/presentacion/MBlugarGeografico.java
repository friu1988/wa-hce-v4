package com.capa.presentacion;

import com.capa.datos.TLugarGeografico;
import com.capa.negocios.TLugarGeograficoFacade;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author FREDDY
 */
@Named(value = "mBlugarGeo")
//@RequestScoped
@SessionScoped
public class MBlugarGeografico implements Serializable {

    private List<TLugarGeografico> provincias;
    private List<TLugarGeografico> cantones;
    private List<TLugarGeografico> parroquias;
    private List<String> nacionalidades;

    private TLugarGeografico provincia;
    private TLugarGeografico canton;
    private TLugarGeografico parroquia;

    @EJB
    private TLugarGeograficoFacade servicioGeo;

    public MBlugarGeografico() {
        provincia = new TLugarGeografico();
        canton = new TLugarGeografico();
        parroquia = new TLugarGeografico();
    }

    public void onChangeProvincias() {
        System.out.println("Provincia ..." + provincia);
        if (provincia != null && !provincia.getLgCodigo().equals("")) {
            cantones = null;
            parroquias = null;
            getCantones();
        }

    }

    public void onChangeCantones() {
        System.out.println("Cantones ..." + canton);
        if (canton != null && !canton.getLgCodigo().equals("")) {
            parroquias = null;
            getParroquias();
        }
    }

    public List<TLugarGeografico> getProvincias() {
        if (provincias == null) {
            provincias = new LinkedList<>();
            for (TLugarGeografico prov : servicioGeo.findAll()) {
                if (prov.getPadreLgCodigo() == null) {
                    provincias.add(prov);
                }
            }
        }
        return provincias;
    }

    public List<TLugarGeografico> getCantones() {
        if (cantones == null) {
            cantones = new LinkedList<>();
            for (TLugarGeografico can : servicioGeo.buscarHijosGeo(provincia)) {
                cantones.add(can);
            }
        }
        return cantones;
    }

    public List<TLugarGeografico> getParroquias() {
        if (parroquias == null) {
            parroquias = new LinkedList<>();
            for (TLugarGeografico parr : servicioGeo.buscarHijosGeo(canton)) {
                parroquias.add(parr);
            }
        }
        return parroquias;
    }

    public TLugarGeografico getProvincia() {
//        if (provincia == null) {
//            provincia = new TLugarGeografico();
//        }
        return provincia;
    }

    public void setProvincia(TLugarGeografico provincia) {
        this.provincia = provincia;
    }

    public TLugarGeografico getCanton() {
//        if (canton == null) {
//            canton = new TLugarGeografico();
//        }
        return canton;
    }

    public void setCanton(TLugarGeografico canton) {
        this.canton = canton;
    }

    public TLugarGeografico getParroquia() {
//        if (parroquia == null) {
//            parroquia = new TLugarGeografico();
//        }
        System.out.println("Parroqia>>" + parroquia);
        return parroquia;
    }

    public void setParroquia(TLugarGeografico parroquia) {
        System.out.println("Parroqia>>" + parroquia);
        this.parroquia = parroquia;
    }

    public List<String> getNacionalidades() {
        if (nacionalidades == null) {
            nacionalidades = new LinkedList<>();
            nacionalidades.add("Canadiense");
            nacionalidades.add("Estadounidense");
            nacionalidades.add("Mexicana");
            nacionalidades.add("Beliceña");
            nacionalidades.add("Costarricense");
            nacionalidades.add("Guatemalteca");
            nacionalidades.add("Hondureña");
            nacionalidades.add("Nicaragüense");
            nacionalidades.add("Panameña");
            nacionalidades.add("Salvadoreña");
            nacionalidades.add("Cubana");
            nacionalidades.add("Arubana");
            nacionalidades.add("Bahameña");
            nacionalidades.add("Barbadense");
            nacionalidades.add("Dominiquesa");
            nacionalidades.add("Dominicana");
            nacionalidades.add("Haitiana");
            nacionalidades.add("Jamaiquina");
            nacionalidades.add("Puertorriqueña");
            nacionalidades.add("Sancristobaleña");
            nacionalidades.add("Santaluciana");
            nacionalidades.add("Sanvicentina");
            nacionalidades.add("Argentina");
            nacionalidades.add("Boliviana");
            nacionalidades.add("Brasileña");
            nacionalidades.add("Chilena");
            nacionalidades.add("Colombiana");
            nacionalidades.add("Ecuatoriana");
            nacionalidades.add("Guyanesa");
            nacionalidades.add("Paraguaya");
            nacionalidades.add("Peruana");
            nacionalidades.add("Surinamesa");
            nacionalidades.add("Uruguaya");
            nacionalidades.add("Venezolana");
        }
        Collections.sort(nacionalidades);
        return nacionalidades;
    }

}
