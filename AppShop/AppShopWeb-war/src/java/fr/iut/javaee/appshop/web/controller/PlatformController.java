package fr.iut.javaee.appshop.web.controller;

import fr.iut.javaee.appshop.commons.Platform;
import fr.iut.javaee.appshop.service.local.PlatformServiceLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Alexis
 */
@ManagedBean(name = "platformController")
@RequestScoped
public class PlatformController implements Serializable 
{
    @EJB
    private PlatformServiceLocal service;
    
    private List<Platform> platforms = new ArrayList<Platform>();
    private Platform platform = new Platform();
    
    public String findAll()
    {
       platforms = service.findAll();  
       return "platforms?faces-redirect=true";
    }
    
    public String findOneById(int id)
    {
        platform = service.findOneById(id);
        return "application?faces-redirect=true";
    }
        
    public List<Platform> getPlatforms()
    {    
       return platforms;
    }
    
    public Platform getPlatform()
    {
        return platform;
    }
}
