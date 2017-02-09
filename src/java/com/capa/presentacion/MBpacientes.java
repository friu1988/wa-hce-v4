package com.capa.presentacion;

import com.capa.datos.TPaciente;
//import com.capa.presentacion.util.JsfUtil;
//import com.capa.presentacion.util.JsfUtil.PersistAction;
import com.capa.negocios.TPacienteFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("mBpacientes")
@SessionScoped
public class MBpacientes implements Serializable {

    @EJB
    private TPacienteFacade servicioPaciente;
    private List<TPaciente> items = null;
    private TPaciente selected;

    public MBpacientes() {
    }

    public TPaciente getSelected() {
        return selected;
    }

    public void setSelected(TPaciente selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TPacienteFacade getFacade() {
        return servicioPaciente;
    }

    public TPaciente prepareCreate() {
        selected = new TPaciente();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {

    }

    public void update() {
    }

    public void destroy() {
        selected = null; // Remove selection
        items = null;    // Invalidate list of items to trigger re-query.
    }

    public List<TPaciente> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public TPaciente getTPaciente(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<TPaciente> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TPaciente> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TPaciente.class)
    public static class TPacienteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MBpacientes controller = (MBpacientes) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mBpacientes");
            return controller.getTPaciente(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TPaciente) {
                TPaciente o = (TPaciente) object;
                return getStringKey(o.getPacCedula());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TPaciente.class.getName()});
                return null;
            }
        }

    }

}
