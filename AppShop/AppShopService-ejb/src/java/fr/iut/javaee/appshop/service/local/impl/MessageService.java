/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local.impl;

import fr.iut.javaee.appshop.commons.Message;
import fr.iut.javaee.appshop.service.local.MessageServiceLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
    Logger logger = Logger.getLogger(DownloadService.class.getName());
    
    @RolesAllowed("Administrator")
    @Override
    public void persist(Message m) 
    {      
        try {
            em.persist(em.merge(m));  
        }
        catch(Exception e) {
            logger.log(Level.SEVERE, "Unable to persist the given message object");
        }     
    }

    @Override
    public void remove(Message m) 
    {
        try {
            em.remove(em.merge(m));
        }
        catch(Exception e) {
            logger.log(Level.SEVERE, "Unable to remove the given message object");
        }
    }
}
