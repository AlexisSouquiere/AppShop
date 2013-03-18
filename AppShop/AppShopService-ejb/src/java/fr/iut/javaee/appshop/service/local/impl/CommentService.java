/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local.impl;

import fr.iut.javaee.appshop.commons.Comment;
import fr.iut.javaee.appshop.service.local.CommentServiceLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Audrey
 */
@Stateless
public class CommentService implements CommentServiceLocal
{
    @PersistenceContext(unitName="AppShopCommonsPU")
    private EntityManager em;
    
    @Override
    public List<Comment> findCommentsByApplicationId(Integer id) 
    {
        Query query = em.createNativeQuery("SELECT c FROM COMMENT c "
                + "WHERE c.commentApplicationId.applicationId = :applicationId ");
        query.setParameter("applicationId", id);
        
        return query.getResultList();        
    }   

    @Override
    public List<Comment> findLastFiveCommentsAdded() 
    {
        Query query = em.createQuery("SELECT c FROM Comment c "
                + "ORDER BY c.commentDate DESC");
        
        return query.setMaxResults(5).getResultList(); 
    }
    
    @Override
    public List<Comment> findAll() {
        return em.createNamedQuery("Comment.findAll").getResultList();       
    }

    @Override
    public Comment findCommentById(Integer id) {
        return (Comment)em.createNamedQuery("Comment.findByCommentId")
                .setParameter("commentId", id)
                .getSingleResult();       
    }

    @Override
    public List<Comment> findCommentsByUserId(Integer id) {
        Query query = em.createQuery("SELECT c FROM Comment c WHERE c.commentUser.userId = :userId ");
        query.setParameter("userId", id);
        
        return query.getResultList();     
    }
    
    @Override
    public void persist(Comment c) 
    {
        em.persist(c);
    }

    @Override
    public void remove(Comment c) {
        em.persist(c);
    } 
    
    public void setEM (EntityManager em)
    {
        this.em = em;
    }
}
