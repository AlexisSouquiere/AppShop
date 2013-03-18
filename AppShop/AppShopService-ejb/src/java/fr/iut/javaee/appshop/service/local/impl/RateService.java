/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local.impl;

import fr.iut.javaee.appshop.commons.Rate;
import fr.iut.javaee.appshop.service.local.RateServiceLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    Logger logger = Logger.getLogger(DownloadService.class.getName());

    @Override
    public void persist(Rate r) 
    {
        try {
            em.persist(r);
        }
        catch(Exception e) {
            logger.log(Level.SEVERE, "Unable to persist the given rate object");
        }     
    }

    @Override
    public Double findApplicationRateAverage(int id) 
    {        
        Query q = em.createQuery("SELECT AVG(r.rate + 0.0) FROM Rate r WHERE r.rateApplication.applicationId = :paramID");
        q.setParameter("paramID", id);  
        Double rate = (Double) q.getSingleResult();
        return rate != null ? rate : 5;
    }
    
    public void setEM(EntityManager em)
    {
        this.em = em;
    }
}
