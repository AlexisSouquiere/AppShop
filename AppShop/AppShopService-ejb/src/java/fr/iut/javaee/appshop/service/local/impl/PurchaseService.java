/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local.impl;

import fr.iut.javaee.appshop.commons.Purchase;
import fr.iut.javaee.appshop.service.local.PurchaseServiceLocal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Audrey
 */
@Stateless
public class PurchaseService implements PurchaseServiceLocal
{
    @PersistenceContext(unitName="AppShopCommonsPU")
    private EntityManager em;
    
    Logger logger = Logger.getLogger(DownloadService.class.getName());

    @Override
    public List<Purchase> findAll() 
    {        
         return em.createNamedQuery("Purchase.findAll").getResultList();
    }

    @Override
    public Purchase findOneById(Integer id) 
    {
         return (Purchase)em.createNamedQuery("Purchase.findByPurchaseId")
                              .setParameter("purchaseId", id)
                              .getSingleResult();  
    }

    @Override
    public void persist(Purchase p) 
    {
        try {
            em.persist(em.merge(p));
        }
        catch(Exception e) {
            logger.log(Level.SEVERE, "Unable to persist the given purchase object");
        }     
    }
}
