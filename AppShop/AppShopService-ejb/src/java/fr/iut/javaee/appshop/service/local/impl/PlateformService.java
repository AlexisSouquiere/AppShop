/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local.impl;

import fr.iut.javaee.appshop.commons.Platform;
import fr.iut.javaee.appshop.service.local.PlatformServiceLocal;
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
public class PlateformService implements PlatformServiceLocal
{
    @PersistenceContext(unitName="AppShopCommonsPU")
    private EntityManager em;
    
    Logger logger = Logger.getLogger(DownloadService.class.getName());
    
    @Override
    public List<Platform> findAll() {
        
        return em.createNamedQuery("Platform.findAll").getResultList();
    }

    @Override
    public Platform findOneById(Integer id) 
    {        
        return (Platform)em.createNamedQuery("Platform.findByPlatformId")
                              .setParameter("platformId", id)
                              .getSingleResult();  
    }

    @Override
    public void persist(Platform p) 
    {
        try {
            em.persist(em.merge(p));
        }
        catch(Exception e) {
            logger.log(Level.SEVERE, "Unable to persist the given platfform object");
        }     
    }

    @Override
    public void remove(Platform p) 
    {
        try {
            em.remove(em.merge(p));
        }
        catch(Exception e) {
            logger.log(Level.SEVERE, "Unable to remove the given platfform object");
        }     
    }  
    
    public void setEM(EntityManager em)
    {
        this.em = em;
    }
}
