package com.capa.presentacion;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@Named(value = "test")
@SessionScoped
public class Pruebas implements Serializable {

    private List<SelectItem> listaDias = null;
    private Integer[] elejidos;

    public Pruebas() {

    }

    public void seleccionar() {
//        for (int i = 0; i < elejidos.length; i++) {
//            System.out.println("Elejidos: " + elejidos[i]);
//        }
    }

    public List<SelectItem> getListaDias() {
        if (listaDias == null) {
            listaDias = new LinkedList<>();
            listaDias.add(new SelectItem(1, "Lunes"));
            listaDias.add(new SelectItem(2, "Martes"));
            listaDias.add(new SelectItem(3, "Miercoles"));
            listaDias.add(new SelectItem(4, "Jueves"));
            listaDias.add(new SelectItem(5, "Viernes"));
            listaDias.add(new SelectItem(6, "Sábado"));
            
        }
        return listaDias;
    }

    public void setListaDias(List<SelectItem> listaDias) {
        this.listaDias = listaDias;
    }

    public Integer[] getElejidos() {
        return elejidos;
    }

    public void setElejidos(Integer[] elejidos) {
        this.elejidos = elejidos;
    }

}
