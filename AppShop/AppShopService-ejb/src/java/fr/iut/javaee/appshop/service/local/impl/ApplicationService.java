/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local.impl;

import fr.iut.javaee.appshop.commons.Administrator;
import fr.iut.javaee.appshop.commons.Application;
import fr.iut.javaee.appshop.service.local.ApplicationServiceLocal;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alexis
 */
@DeclareRoles("admin")
@Stateless
//@TransactionManagement(value=TransactionManagementType.CONTAINER)
public class ApplicationService implements ApplicationServiceLocal 
{
    @PersistenceContext(unitName="AppShopCommonsPU")
    private EntityManager em;
    
    
    public List<Application> findAll() 
    {
        return em.createNamedQuery("Application.findAll").getResultList();
    }

    @Override
    public Application findOneById(Integer id) 
    {
        return (Application)em.createNamedQuery("Application.findByApplicationId")
                              .setParameter("applicationId", id)
                              .getSingleResult();        
    }
   
    @Override
    public List<Application> findApplicationsByName(String name) 
    {
        return em.createNamedQuery("Application.findByApplicationName")
                              .setParameter("applicationName", name)
                              .getResultList();        
    }

    @Override
    public List<Application> findApplicationsByPlatformId(int id) 
    {
        Query query = em.createNativeQuery("SELECT a FROM APPLICATION a "
                + "WHERE a.applicationPlatformId.platformId = :platformId ");
        query.setParameter("platformId", id);
        
        return query.getResultList();
    }

    @Override
    public List<Application> findApplicationsByVersion(String version) 
    {
        return em.createNamedQuery("Application.findByApplicationVersion")
                              .setParameter("applicationVersion", version)
                              .getResultList();        
    }

    @Override
    public List<Application> findApplicationsByEditorId(int id) 
    {
        Query query = em.createNativeQuery("SELECT a FROM APPLICATION a "
                + "WHERE a.applicationEditorId.editorId = :editorId ");
        query.setParameter("editorId", id);
        
        return query.getResultList();
    }
    
    @RolesAllowed("admin")
    @Override
    public void persist(Application a) 
    {      
        em.persist(a);       
    }

    @RolesAllowed("admin")
    @Override
    public void remove(Application a) 
    {
        em.remove(a);
    }

    @Override
    public void notifyUpdate(String name) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
