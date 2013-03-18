/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local;

import fr.iut.javaee.appshop.commons.Editor;
import java.util.List;
import javax.ejb.Local;
import javax.persistence.EntityManager;

/**
 *
 * @author Audrey
 */
@Local
public interface EditorServiceLocal 
{
    public List<Editor> findAll();
    
    public Editor findOneById(Integer id);
    
    public void persist(Editor e);
    
    public void remove(Editor e);
    
    public void setEM(EntityManager em);
}
