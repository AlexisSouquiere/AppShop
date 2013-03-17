/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local.impl;

import fr.iut.javaee.appshop.commons.Application;
import fr.iut.javaee.appshop.commons.Editor;
import fr.iut.javaee.appshop.commons.Message;
import fr.iut.javaee.appshop.commons.Users;
import fr.iut.javaee.appshop.service.local.ApplicationServiceLocal;
import fr.iut.javaee.appshop.service.local.DownloadServiceLocal;
import fr.iut.javaee.appshop.service.local.MessageServiceLocal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alexis
 */
@DeclareRoles("Administrator")
@Stateless
//@TransactionManagement(value=TransactionManagementType.CONTAINER)
public class ApplicationService implements ApplicationServiceLocal 
{
    @PersistenceContext(unitName="AppShopCommonsPU")
    private EntityManager em;
    
    @EJB
    private DownloadServiceLocal serviceDownload;
    
    @EJB
    private MessageServiceLocal serviceMessage;
    
    @Override
    public List<Application> findAll() 
    {
        return em.createNamedQuery("Application.findAll").getResultList();
    }
    
    @Override
    public List<Application> findApplicationList()
    {
        Query query = em.createNativeQuery("SELECT APPLICATION_NAME, EDITOR_ID, EDITOR_NAME " +
                "FROM APPSHOP.APPLICATION INNER JOIN APPSHOP.EDITOR " +
                "ON APPSHOP.APPLICATION.APPLICATION_EDITOR_ID = APPSHOP.EDITOR.EDITOR_ID " +
                "GROUP BY APPLICATION_NAME, EDITOR_ID, EDITOR_NAME " +
                "ORDER BY APPLICATION_NAME ");
        
        List<Application> list = new ArrayList<Application>();
        List<Object[]> res = (List<Object[]>)query.getResultList();
        
        for(Object[] s : res)
        {
            Application a = new Application();
            Editor e = new Editor();
            
            a.setApplicationName((String)s[0]);            
            e.setEditorId((Integer)s[1]);
            e.setEditorName((String)s[2]);
            
            a.setApplicationEditor(e);
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
                + "WHERE a.applicationPlatform.platformId = :platformId");
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
        Query query = em.createNativeQuery("SELECT application_Name, editor_Id, editor_Name " +
                "FROM APPSHOP.APPLICATION INNER JOIN APPSHOP.EDITOR " +
                "ON APPSHOP.APPLICATION.APPLICATION_EDITOR_ID = APPSHOP.EDITOR.EDITOR_ID " +
                "WHERE application_Editor_Id = ? " +
                "GROUP BY application_Name, editor_Id, editor_Name");
        query.setParameter(1, id);
        
        List<Application> list = new ArrayList<Application>();
        List<Object[]> res = (List<Object[]>)query.getResultList();
        
        for(Object[] s : res)
        {
            Application a = new Application();
            Editor e = new Editor();
            
            a.setApplicationName((String)s[0]);            
            e.setEditorId((Integer)s[1]);
            e.setEditorName((String)s[2]);
            
            a.setApplicationEditor(e);
            list.add(a);
        }
        
        return list;
    }
    
    @RolesAllowed("Administrator")
    @Override
    public void persist(Application a) 
    {      
        em.persist(em.merge(a));
        notifyUpdate(a);
    }

    @RolesAllowed("Administrator")
    @Override
    public void remove(Application a) 
    {
        em.remove(em.merge(a));
    }

    @Override
    public void notifyUpdate(Application a) 
    { 
        List<Users> users = serviceDownload.findMemberByApplicationName(a);
        
        for(Users u : users)
        {
            Message m = new Message();
            m.setMessageUser(u);
            String body = String.format("%s %s : #{msg['notitfy_update']}", a.getApplicationName(), a.getApplicationVersion());
            m.setMessageBody(body);
            m.setMessageDate(new Date(System.currentTimeMillis()));
            
            serviceMessage.persist(m);
        }
    }

    @Override
    public List<Application> findLastFiveApplicationsAdded() 
    {
        Query query = em.createQuery("SELECT a FROM Application a "
                + "ORDER BY a.applicationId DESC");
        
        return query.setMaxResults(5).getResultList();        
    }
}
