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
    public List<Comment> findCommentsByApplicationId(Integer id);
    
    public void persist(Comment c);
    
    public void remove(Comment c);
}
