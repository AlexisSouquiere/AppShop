package fr.iut.javaee.appshop.web.controller;

import fr.iut.javaee.appshop.commons.Member1;
import fr.iut.javaee.appshop.service.local.MemberServiceLocal;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Alexis
 */
@ManagedBean(name = "memberController")
@SessionScoped
public class MemberController implements Serializable 
{
    @EJB
    private MemberServiceLocal service;
    
    @ManagedProperty(value = "3")
    int maxAttempts;
    
    private Member1 member = new Member1();
    
    @PostConstruct
    public void init()
    {
        member = new Member1();        
    }
    
    public String doLogin() 
    {
        return service.authenticate(member);
    }
    
    public String doRegister() 
    {
        service.persist(member);
        return "index?faces-redirect=true";
    }
    
    public Member1 getMember()
    {
        return member;
    }
}
