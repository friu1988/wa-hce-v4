/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.negocios;

import com.capa.datos.TPersonal;
import com.capa.datos.TUsuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author FREDDY
 */
@Stateless
public class TUsuarioFacade extends AbstractFacade<TUsuario> {

    @PersistenceContext(unitName = "wa-hce-v4.1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TUsuarioFacade() {
        super(TUsuario.class);
    }

    public TUsuario buscar(TUsuario usuario) {
        System.out.println("User MB: " + usuario.getUNick() + " clave: " + usuario.getUClave());
        try {
            Query sql = em.createNamedQuery("TUsuario.findByUNick");
            sql.setParameter("uNick", usuario.getUNick());
            TUsuario us = (TUsuario) sql.getSingleResult();
            System.out.println("User DB: " + us.getUNick() + " clave: " + us.getUClave());
            return us;
        } catch (Exception e) {
            TUsuario use = new TUsuario();
            return use;
        }

    }

    public boolean buscarEstado(TPersonal persona) {
        Query sql = em.createNamedQuery("TUsuario.findByPerSerial");
        sql.setParameter("perSerial", persona.getPerSerial());
        try {
            TUsuario usuario = (TUsuario) sql.getSingleResult();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public TUsuario buscarUser(TPersonal persona) {
        Query sql = em.createNamedQuery("TUsuario.findByPerSerial");
        sql.setParameter("perSerial", persona.getPerSerial());
        try {
            TUsuario usuario = (TUsuario) sql.getSingleResult();
            return usuario;
        } catch (Exception e) {
            return new TUsuario();
        }

    }

}
