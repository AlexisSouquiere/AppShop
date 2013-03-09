package fr.iut.javaee.appshop.web.controller;

import fr.iut.javaee.appshop.commons.Application;
import fr.iut.javaee.appshop.service.local.ApplicationServiceLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Alexis
 */
@ManagedBean(name = "applicationController")
@RequestScoped
public class ApplicationController implements Serializable
{
    @EJB
    private ApplicationServiceLocal service;
    
    private List<Application> applications;
    private Application application;
    
    @PostConstruct
    public void init()
    {
        applications = new ArrayList<Application>();
        application = new Application();
    }
    
    public String findAll()
    {
       applications = service.findApplicationList();
       return "applications?faces-redirect=true";
    }
    
    public String findOneById(int id)
    {
        application = service.findOneById(id);
        return "version?faces-redirect=true";
    }
    
    public String findByName(String name)
    {
        applications = service.findApplicationsByName(name);
        return applications != null ? "application?faces-redirect=true" : null;
    }
    
    public String findByPlatformId(int id)
    {
        applications = service.findApplicationsByPlatformId(id);
        return applications != null ? "applications?faces-redirect=true" : null;
    }
    
    public List<Application> getApplications()
    {    
       return applications;
    }
    
    public Application getApplication()
    {
        return application;
    }
}
