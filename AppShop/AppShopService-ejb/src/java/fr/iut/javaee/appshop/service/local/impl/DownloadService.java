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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Schedule;
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
    
    Logger logger = Logger.getLogger(DownloadService.class.getName());
    
    @Override
    public Integer findDownloadByDates(Date start, Date end) 
    {
        Query query = em.createQuery("SELECT COUNT(d) FROM Download d "
                + "WHERE d.downloadDate BETWEEN :start AND :end");
        query.setParameter("start", start);
        query.setParameter("end", end);
        
        Integer res = Integer.parseInt(query.getSingleResult().toString());
        return res;
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
            if (!(users.contains(d.getDownloadUser()))) {
                users.add(d.getDownloadUser()); 
            }
        }  

         return users;
    }
    
    @Override
    public List<Application> findApplicationsDownloadedByMember(Users u)
    {
        Query query = em.createQuery("SELECT DISTINCT(d.downloadApplication) FROM Download d "
                + "WHERE d.downloadUser.userId = :userId");
        query.setParameter("userId", u.getUserId());     

        List<Application> applicationsList = query.getResultList(); 
        
        return applicationsList;
    }
    
    @Override
    public List<Download> findByMember(Users u)
    {
        Query query = em.createQuery("SELECT d FROM Download d WHERE d.downloadUser.userId = :userId");
        query.setParameter("userId", u.getUserId());
        
        return (List<Download>)query.getResultList();        
    }
    
    @Override
    public List<Download> findAll() 
    {
        return em.createNamedQuery("Download.findAll").getResultList();
    }

    @Override
    public void persist(Download d) 
    {
        try {
            em.persist(d);
        }
        catch(Exception e) {
            logger.log(Level.SEVERE, "Unable to persist the given download object");
        }
    }    
    
    //@Schedule(dayOfWeek="Mon")
    public void computeStats() 
    {
        Calendar cal = Calendar.getInstance();
        Date currentDate = cal.getTime();
        cal.add(Calendar.DATE, -7);        
        Date lastWeek = cal.getTime();
        
        Integer stats = findDownloadByDates(lastWeek, currentDate);
        String message = String.format("Between %s and %s, %d applications were downloaded on the website",
                lastWeek, currentDate, stats);
        logger.log(Level.INFO, message);
    }

    @Override
    public Integer findTotalDownloads() 
    {
        Query query = em.createQuery("SELECT COUNT(d) FROM Download d ");        
        Integer res = Integer.parseInt(query.getSingleResult().toString());
        return res;
    }

    @Override
    public Application findApplicationMostDownloaded() 
    {
        Query query = em.createQuery("SELECT d.downloadApplication FROM Download d "
                + "GROUP BY d.downloadApplication ORDER BY COUNT(d.downloadApplication)");
        
        Application application = (Application) query.setMaxResults(1).getSingleResult();
        return application;        
    }

    @Override
    public Users findMostActiveMember() 
    {
        Query query = em.createQuery("SELECT d.downloadUser FROM Download d "
                + "GROUP BY d.downloadUser");
        
        Users user = (Users) query.getSingleResult();
        return user;            
    }
}
