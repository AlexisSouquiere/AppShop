/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local.impl;

import fr.iut.javaee.appshop.commons.ApplicationCollection;
import fr.iut.javaee.appshop.commons.Collection;
import fr.iut.javaee.appshop.service.local.ApplicationCollectionServiceLocal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alexis
 */
@Stateless
public class ApplicationCollectionService implements ApplicationCollectionServiceLocal 
{
    @PersistenceContext(unitName="AppShopCommonsPU")
    private EntityManager em;
    
    Logger logger = Logger.getLogger(DownloadService.class.getName());
    
    @Override
    public List<ApplicationCollection> findApplicationsByCollectionId(Integer id) 
    {
        return (List<ApplicationCollection>)em.createNamedQuery("ApplicationCollection.findByApplicationCollectionId")
                              .setParameter("applicationCollectionId", id)
                              .getResultList();
    }
    
    @Override
    public void add(ApplicationCollection a) 
    {
        try {
            em.persist(em.merge(a));
        }
        catch(Exception e) {
            logger.log(Level.SEVERE, "Unable to persist the given application object");
        }
    }
    
    @Override
    public void remove(ApplicationCollection a) 
    {
        try {
            em.remove(em.merge(a));
        }
        catch(Exception e) {
            logger.log(Level.SEVERE, "Unable to remove the given application object");
        }
    }
}
