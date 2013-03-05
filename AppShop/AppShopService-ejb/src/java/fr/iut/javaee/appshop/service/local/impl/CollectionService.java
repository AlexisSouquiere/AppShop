/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local.impl;

import fr.iut.javaee.appshop.commons.Collection;
import fr.iut.javaee.appshop.service.local.CollectionServiceLocal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Audrey
 */
@Stateless
public class CollectionService implements CollectionServiceLocal
{
    @PersistenceContext(unitName="AppShopCommonsPU")
    private EntityManager em;

    @Override
    public Collection findOneById(Integer id) 
    {
        return (Collection)em.createNamedQuery("Collection.findByCollectionId")
                              .setParameter("collectionId", id)
                              .getSingleResult();      
    }
    
    @Override
    public List<Collection> findCollectionsByMemberId(Integer id) 
    {
        Query query = em.createNativeQuery("SELECT c FROM COLLECTION c "
                + "WHERE c.collectionMemberId.memberId = :memberId ");
        query.setParameter("memberId", id);
        
        return query.getResultList();
    }
    
    @Override
    public void persist(Collection c) 
    {
        System.out.println("avant persist");
        em.persist(c);
        System.out.println("apres persist");
    }

    @Override
    public void remove(Collection c) 
    {
        em.remove(c);
    }  
}
