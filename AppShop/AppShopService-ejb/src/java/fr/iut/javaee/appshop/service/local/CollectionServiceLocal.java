/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local;

import fr.iut.javaee.appshop.commons.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Audrey
 */
@Local
public interface CollectionServiceLocal 
{    
    public List<Collection> findCollectionsByMemberId(Integer id);
    
    public Collection findOneById(Integer id);
    
    public void persist(Collection c);
    
    public void remove(Collection c);
}
