/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local;

import fr.iut.javaee.appshop.commons.Comment;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Audrey
 */
@Local
public interface CommentServiceLocal 
{
    public Comment findCommentById(Integer id);
    
    public List<Comment> findCommentsByUserId(Integer id);
    
    public List<Comment> findAll();
    
    public List<Comment> findCommentsByApplicationId(Integer id);
    
    public void persist(Comment c);
    
    public void remove(Comment c);

    public List<Comment> findLastFiveCommentsAdded();
}
