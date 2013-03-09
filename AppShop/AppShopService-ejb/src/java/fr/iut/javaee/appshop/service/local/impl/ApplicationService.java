/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local.impl;

import fr.iut.javaee.appshop.commons.Application;
import fr.iut.javaee.appshop.commons.Editor;
import fr.iut.javaee.appshop.service.local.ApplicationServiceLocal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
    
    @Override
    public List<Application> findAll() 
    {
        return em.createNamedQuery("Application.findAll").getResultList();
    }
    
    @Override
    public List<Application> findApplicationList()
    {
        Query query = em.createNativeQuery("SELECT application_Name, editor_Id, editor_Name " +
                "FROM APPSHOP.APPLICATION INNER JOIN APPSHOP.EDITOR " +
                "ON APPSHOP.APPLICATION.APPLICATION_EDITOR_ID = APPSHOP.EDITOR.EDITOR_ID " +
                "GROUP BY application_Name, editor_Id, editor_Name");
        
        List<Application> list = new ArrayList<Application>();
        List<Object[]> res = (List<Object[]>)query.getResultList();
        
        for(Object[] s : res)
        {
            Application a = new Application();
            Editor e = new Editor();
            
            a.setApplicationName((String)s[0]);            
            e.setEditorId((Integer)s[1]);
            e.setEditorName((String)s[2]);
            
            a.setApplicationEditorId(e);
            list.add(a);
        }
        
        return list;        
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
        Query query = em.createQuery("SELECT a FROM Application a "
                + "WHERE a.platform.platformId = :platformId");
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
        Query query = em.createNativeQuery("SELECT a FROM Application a "
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
