/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.negocios;

import com.capa.datos.TMedico;
import com.capa.datos.TPersonal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author FREDDY
 */
@Stateless
public class TMedicoFacade extends AbstractFacade<TMedico> {

    @PersistenceContext(unitName = "wa-hce-v4.1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TMedicoFacade() {
        super(TMedico.class);
    }

//    public List<TMedico> buscarMedico(TEspecialidad especialidad) {
//        Query sql = em.createNamedQuery("TMedico.findByPerSerial");
//        try {
//            sql.setParameter("perSerial", especialidad.getTMedicoList());
//            List<TMedico> lista = sql.getResultList();
//            return lista;
//        } catch (Exception e) {
//            System.out.println("Error Buscar detalle >>>>>" + e.getMessage());
//        }
//        return null;
//    }
    public boolean buscarMedico(TPersonal personal) {
        Query sql = em.createNamedQuery("TMedico.findByPerSerial");
        sql.setParameter("perSerial", personal.getPerSerial());
        try {
            TMedico medico = (TMedico) sql.getSingleResult();
            System.out.println(" Persona " + medico);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
