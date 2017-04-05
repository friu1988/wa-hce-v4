/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.negocios;

import com.capa.datos.TAccesos;
import com.capa.datos.TUsuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author FREDDY
 */
@Stateless
public class TAccesosFacade extends AbstractFacade<TAccesos> {

    @PersistenceContext(unitName = "wa-hce-v4.1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TAccesosFacade() {
        super(TAccesos.class);
    }

    public List<TAccesos> accesos(TUsuario rol) {
        Query sql = em.createNamedQuery("TAccesos.findByUSerial");
        sql.setParameter("uSerial", rol.getUSerial());
        List<TAccesos> menus = sql.getResultList();
        return menus;
    }
}
