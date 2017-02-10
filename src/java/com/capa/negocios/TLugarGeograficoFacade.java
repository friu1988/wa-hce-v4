package com.capa.negocios;

import com.capa.datos.TFacultad;
import com.capa.datos.TLugarGeografico;
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
public class TLugarGeograficoFacade extends AbstractFacade<TLugarGeografico> {

    @PersistenceContext(unitName = "wa-hce-v4.1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TLugarGeograficoFacade() {
        super(TLugarGeografico.class);
    }

    public List<TLugarGeografico> buscarHijosGeo(TLugarGeografico padre) {
        Query sql = em.createNamedQuery("TLugarGeografico.findByPadreLgCodigo");
        sql.setParameter("padreLgCodigo", padre);
        List<TLugarGeografico> hijos = sql.getResultList();
        return hijos;
    }

}
