/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local;

import fr.iut.javaee.appshop.commons.ApplicationCollection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexis
 */
@Local
public interface ApplicationCollectionServiceLocal 
{
    public List<ApplicationCollection> findApplicationsByCollectionId(Integer id);
    
    public void remove(ApplicationCollection a);
    
    public void add(ApplicationCollection a);
}
