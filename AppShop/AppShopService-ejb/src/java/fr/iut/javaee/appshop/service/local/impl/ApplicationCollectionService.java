/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local.impl;

import fr.iut.javaee.appshop.commons.ApplicationCollection;
import fr.iut.javaee.appshop.commons.Collection;
import fr.iut.javaee.appshop.service.local.ApplicationCollectionServiceLocal;
import java.util.List;
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
        em.persist(em.merge(a));
    }
    
    @Override
    public void remove(ApplicationCollection a) 
    {
        em.remove(em.merge(a));
    }
}
