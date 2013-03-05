/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local.impl;

import fr.iut.javaee.appshop.commons.Rate;
import fr.iut.javaee.appshop.service.local.RateServiceLocal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Audrey
 */
@Stateless
public class RateService implements RateServiceLocal
{
    @PersistenceContext(unitName="AppShopCommonsPU")
    private EntityManager em;

    
    @Override
    public void persist(Rate r) 
    {
        em.persist(r);
    }

    @Override
    public Double findApplicationRateAverage(int id) 
    {        
        Query q = em.createQuery("SELECT AVG(r.rate + 0.0) FROM Rate r WHERE r.rateApplicationId.applicationId = :paramID");
        q.setParameter("paramID", id);        
        return (Double) q.getSingleResult();
    }
}
