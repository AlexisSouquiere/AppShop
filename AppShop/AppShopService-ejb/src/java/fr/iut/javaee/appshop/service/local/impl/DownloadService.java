/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local.impl;

import fr.iut.javaee.appshop.commons.Download;
import fr.iut.javaee.appshop.commons.Member1;
import fr.iut.javaee.appshop.service.local.DownloadServiceLocal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
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
        Query query = em.createNativeQuery("SELECT COUNT(d) FROM DOWNLOAD d "
                + "WHERE d.downloadDate BETWEEN :start AND :end");
        query.setParameter("start", start);
        query.setParameter("end", end);
        
        return (Integer)query.getSingleResult();
    }

    @Override
    public Integer findDownloadByDatesAndApplicationId(Date start, Date end, int id) 
    {
        Query query = em.createNativeQuery("SELECT COUNT(d) FROM DOWNLOAD d "
                + "WHERE d.downloadDate AND d.downloadApplicationId.applicationId = :id "
                + "BETWEEN :start AND :end");
        query.setParameter("id", id);
        query.setParameter("start", start);
        query.setParameter("end", end);
        
        return (Integer)query.getSingleResult();
    }

    @Override
    public Integer findDownloadNumberByApplicationId(Integer id) 
    {
        Query query = em.createNativeQuery("SELECT COUNT(d) FROM DOWNLOAD d "
                + "WHERE d.downloadDate AND d.downloadApplicationId.applicationId = :id");
        query.setParameter("id", id);
        
        return (Integer)query.getSingleResult();
    }

    @Override
    public List<Member1> findMemberByApplicationId(int id) 
    {
       Query query2 = em.createQuery("SELECT d FROM Download d "
               + "WHERE d.downloadApplicationId.applicationId = :paramID");
       query2.setParameter("paramID", id);
       
       //List of download --> Result of the query
       List<Download> Dls = query2.getResultList();
        
        //create a list of member --> Get back the list whithout double (member)
        List<Member1> Members = new  ArrayList<Member1>();        
        for (Download dl : Dls) {            
            if (!(Members.contains(dl.getDownloadMemberId())))
                    {
                        Members.add(dl.getDownloadMemberId()); 
                    }
        }
        
        return Members;
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
