/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.negocios;

import com.capa.datos.TMedico;
import com.capa.datos.TPersonalSalud;
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
public class TPersonalSaludFacade extends AbstractFacade<TPersonalSalud> {

    @PersistenceContext(unitName = "wa-hce-v4.1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TPersonalSaludFacade() {
        super(TPersonalSalud.class);
    }

    public List<TPersonalSalud> findPS(TMedico medico) {
        Query sql = em.createNamedQuery("TPersonalSalud.findByPerSerial");
        sql.setParameter("perSerial", medico.getPerSerial());
        List<TPersonalSalud> ps = sql.getResultList();
        return ps;
    }
}
