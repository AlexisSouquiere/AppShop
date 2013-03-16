/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local.impl;

import fr.iut.javaee.appshop.commons.Application;
import fr.iut.javaee.appshop.commons.Editor;
import fr.iut.javaee.appshop.commons.Message;
import fr.iut.javaee.appshop.service.local.ApplicationServiceLocal;
import fr.iut.javaee.appshop.service.local.MessageServiceLocal;
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
@DeclareRoles("Administrator")
@Stateless
public class MessageService implements MessageServiceLocal
{
    @PersistenceContext(unitName="AppShopCommonsPU")
    private EntityManager em;
    
    @RolesAllowed("Administrator")
    @Override
    public void persist(Message m) 
    {      
        em.persist(em.merge(m));       
    }

    @Override
    public void remove(Message m) 
    {
        em.remove(em.merge(m));
    }
}
