/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local;

import fr.iut.javaee.appshop.commons.Platform;
import java.util.List;
import javax.ejb.Local;
import javax.persistence.EntityManager;

/**
 *
 * @author Audrey
 */
@Local
public interface PlatformServiceLocal 
{
    public List<Platform> findAll();
    
    public Platform findOneById(Integer id);
    
    public void persist(Platform p);
    
    public void remove(Platform p);
    
    public void setEM(EntityManager em);
}
