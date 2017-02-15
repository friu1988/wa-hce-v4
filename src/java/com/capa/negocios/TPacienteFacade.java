/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.negocios;

import com.capa.datos.TPaciente;
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
public class TPacienteFacade extends AbstractFacade<TPaciente> {

    @PersistenceContext(unitName = "wa-hce-v4.1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TPacienteFacade() {
        super(TPaciente.class);
    }

    public Integer findMaxHCU() {
        Query sql = em.createNamedQuery("TPaciente.findMaxHCU");
        String numeroHCU = (String) sql.getSingleResult();
        return Integer.parseInt(numeroHCU);
    }

}
