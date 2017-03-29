/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.negocios;

import com.capa.datos.THorario;
import com.capa.datos.TMedico;
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
public class THorarioFacade extends AbstractFacade<THorario> {

    @PersistenceContext(unitName = "wa-hce-v4.1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public THorarioFacade() {
        super(THorario.class);
    }

    public List<THorario> findHo(TMedico medico) {
        Query sql = em.createNamedQuery("THorario.findByPerSerial");
        sql.setParameter("perSerial", medico.getPerSerial());
        List<THorario> horarios = sql.getResultList();
        return horarios;
    }
}
