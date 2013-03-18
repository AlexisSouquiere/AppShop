/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local;

import fr.iut.javaee.appshop.commons.Users;
import java.util.List;
import javax.ejb.Local;
import javax.persistence.EntityManager;

/**
 *
 * @author Alexis
 */
@Local
public interface UserServiceLocal 
{
    public Users findOneById(Integer id);
    
    public Users findOneByUsername(String username);
    
    public void persist(Users u);
    
    public void remove(Users u);

    public List<Users> findAll();
    
    public void setEM(EntityManager em);
}
