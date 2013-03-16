/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local.impl;

import fr.iut.javaee.appshop.commons.Users;
import fr.iut.javaee.appshop.service.local.UserServiceLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alexis
 */
@Stateless
public class UserService implements UserServiceLocal 
{

    @PersistenceContext(unitName="AppShopCommonsPU")
    private EntityManager em;

    @Override
    public List<Users> findAll() 
    {
        return (List<Users>)em.createNamedQuery("Users.findAll")
                              .getResultList();
    }
    
    @Override
    public Users findOneById(Integer id) 
    {
        return (Users)em.createNamedQuery("Users.findByUserId")
                              .setParameter("userId", id)
                              .getSingleResult();  
    }

    @Override
    public Users findOneByUsername(String username) 
    {
        return (Users)em.createNamedQuery("Users.findByUserUsername")
                              .setParameter("userUsername", username)
                              .getSingleResult();        
    }

    @Override
    public void persist(Users u) 
    {
        em.persist(em.merge(u));
    }

    @Override
    public void remove(Users u) 
    {
        em.remove(em.merge(u));
    }
}
