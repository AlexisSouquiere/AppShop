/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local;

import fr.iut.javaee.appshop.commons.Application;
import fr.iut.javaee.appshop.commons.Message;
import javax.ejb.Local;

/**
 *
 * @author Alexis
 */
@Local
public interface MessageServiceLocal 
{        
    public void persist(Message m);
    
    public void remove(Message m);
}
