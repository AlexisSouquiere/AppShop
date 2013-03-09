package fr.iut.javaee.appshop.web.controller;

import fr.iut.javaee.appshop.commons.Application;
import fr.iut.javaee.appshop.commons.Comment;
import fr.iut.javaee.appshop.commons.Member1;
import fr.iut.javaee.appshop.service.local.CommentServiceLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

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
    
    @ManagedProperty(value = "#{memberController.member}")
    private Member1 member;
    
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
        if(member.getMemberId() != null) {
            c.setCommentMemberId(member);
        }
        c.setCommentApplicationId(application);
        
        service.persist(c);
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

    
    public Member1 getMember() {
        return member;
    }

    public Application getApplication() {
        return application;
    }

    public void setMember(Member1 member) {
        this.member = member;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
