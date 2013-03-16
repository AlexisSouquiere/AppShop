package fr.iut.javaee.appshop.web.controller;

import fr.iut.javaee.appshop.commons.Application;
import fr.iut.javaee.appshop.commons.Comment;
import fr.iut.javaee.appshop.commons.Users;
import fr.iut.javaee.appshop.service.local.CommentServiceLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Alexis
 */
@ManagedBean(name = "commentController")
@RequestScoped
public class CommentController implements Serializable
{
    @EJB
    private CommentServiceLocal service;
    
    private List<Comment> comments;
    private String comment;
    
    @ManagedProperty(value = "#{userController.user}")
    private Users user;
    
    @ManagedProperty(value = "#{applicationController.application}")
    private Application application;
        
    @PostConstruct
    public void init()
    {
        comments = new ArrayList<Comment>();
        comment = new String();        
    }
    
    public void findCommentsByApplicationId(int id)
    {
        comments = service.findCommentsByApplicationId(id);
    }

    public void postComment()
    {
        Comment c = new Comment();
        c.setCommentBody(comment);
        c.setCommentDate(new Date());
        if(user.getUserId() != null) {
            c.setCommentUser(user);
        }
        c.setCommentApplication(application);
        
        service.persist(c);
        
        comments = application.getCommentList();
        comments.add(c);
        application.setCommentList(comments);
    }
    
    public List<Comment> findLastFive()
    {
        return service.findLastFiveCommentsAdded();
    }
    
    public List<Comment> getComments() 
    {
        return comments;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String getComment() 
    {
        return comment;
    }
    
    public Users getUser() {
        return user;
    }

    public Application getApplication() {
        return application;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
