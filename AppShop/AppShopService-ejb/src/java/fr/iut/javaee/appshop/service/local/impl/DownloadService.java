/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local.impl;

import fr.iut.javaee.appshop.commons.Application;
import fr.iut.javaee.appshop.commons.Download;
import fr.iut.javaee.appshop.commons.Users;
import fr.iut.javaee.appshop.service.local.DownloadServiceLocal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Audrey
 */
@Stateless
public class DownloadService implements DownloadServiceLocal
{
    @PersistenceContext(unitName="AppShopCommonsPU")
    private EntityManager em;
    
    @Override
    public Integer findDownloadByDates(Date start, Date end) 
    {
        Query query = em.createQuery("SELECT COUNT(d) FROM Download d "
                + "WHERE d.downloadDate BETWEEN :start AND :end");
        query.setParameter("start", start);
        query.setParameter("end", end);
        
        return (Integer)query.getSingleResult();
    }

    @Override
    public Integer findDownloadByDatesAndApplicationId(Date start, Date end, int id) 
    {
        Query query = em.createQuery("SELECT COUNT(d) FROM Download d "
                + "WHERE d.downloadDate BETWEEN :start AND :end "
                + "AND d.downloadApplicationId.applicationId = :id ");
        query.setParameter("id", id);
        query.setParameter("start", start);
        query.setParameter("end", end);
        
        Integer res = Integer.parseInt(query.getSingleResult().toString());
        return res;
    }

    @Override
    public Integer findDownloadNumberByApplicationId(Integer id) 
    {
        Query query = em.createQuery("SELECT COUNT(d) FROM Download d "
                + "WHERE d.downloadApplication.applicationId = :id");
        query.setParameter("id", id);
        
        Integer res = Integer.parseInt(query.getSingleResult().toString());
        return res;
    }

    @Override
    public List<Users> findMemberByApplicationId(int id) 
    {
        Query query2 = em.createQuery("SELECT d FROM Download d WHERE d.downloadApplication.applicationId = :id");
        query2.setParameter("id", id);     

        List<Download> downloads = query2.getResultList();      

        List<Users> users = new  ArrayList<Users>();        
        for (Download d : downloads) {            
            if (!(users.contains(d.getDownloadUser())))
            {
                users.add(d.getDownloadUser()); 
            }
        }  

         return users;
    }

    @Override
    public List<Users> findMemberByApplicationName(Application a) 
    {
        Query query2 = em.createQuery("SELECT d FROM Download d WHERE d.downloadApplication.applicationName = :name");
        query2.setParameter("name", a.getApplicationName());     

        List<Download> downloads = query2.getResultList();      

        List<Users> users = new  ArrayList<Users>();        
        for (Download d : downloads) {            
            if (!(users.contains(d.getDownloadUser())))
            {
                users.add(d.getDownloadUser()); 
            }
        }  

         return users;
    }
    
    @Override
    public List<Download> findAll() 
    {
        return em.createNamedQuery("Download.findAll").getResultList();
    }

    @Override
    public void persist(Download d) 
    {
        em.persist(d);       
    }    
}
