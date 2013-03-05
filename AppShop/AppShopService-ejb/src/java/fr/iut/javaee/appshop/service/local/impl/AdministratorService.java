/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local.impl;

import fr.iut.javaee.appshop.commons.Administrator;
import fr.iut.javaee.appshop.service.local.AdministratorServiceLocal;
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
@DeclareRoles("admin")
@Stateless
public class AdministratorService implements AdministratorServiceLocal
{
    @PersistenceContext(unitName="AppShopCommonsPU")
    private EntityManager em;
    
    @Override
    public Administrator findOneById(Integer id) 
    {        
       return (Administrator)em.createNamedQuery("Administrator.findByAdministratorId")
                              .setParameter("administratorId", id)
                              .getSingleResult();
    }

    @RolesAllowed("admin")
    @Override
    public void persist(Administrator a) 
    {
       em.persist(a);
    }

    @Override
    public boolean authenticate(Administrator a) 
    {
        Query query = em.createNativeQuery("SELECT a FROM ADMINISTRATOR a "
                + "WHERE a.administratorUsername :administratorUsername "
                + "AND a.administratorPassword = :administratorPassword");
        query.setParameter("administratorUsername", a.getAdministratorUsername());
        query.setParameter("administratorPassword", a.getAdministratorPassword());
        
        Administrator admin = (Administrator)query.getSingleResult();
        
        return admin != null ? true : false;
    }
   
}
