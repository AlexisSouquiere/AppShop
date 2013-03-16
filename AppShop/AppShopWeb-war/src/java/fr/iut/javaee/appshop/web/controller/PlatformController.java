package fr.iut.javaee.appshop.web.controller;

import fr.iut.javaee.appshop.commons.Platform;
import fr.iut.javaee.appshop.service.local.PlatformServiceLocal;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

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
    
    private Platform platform = new Platform();
        
    public List<Platform> findAll()
    {
       return service.findAll();
    }
    
    public Platform findOneById(int id)
    {
        return service.findOneById(id);
    }
    
    public String editOneById(int id)
    {
        platform = service.findOneById(id);
        return "/admin/platforms/platform.xhtml";
    }
    
    public String add()
    {
        service.persist(platform);
        return "/admin/platforms/list.xhtml?faces-config=true";
    }
    
    public String update()
    {
        service.persist(platform);
        return "/admin/platforms/list.xhtml?faces-config=true";
    }
    
    public String remove(Platform e)
    {
        service.remove(e);
        return "/admin/platforms/list.xhtml?faces-config=true";
    }

    public Platform getPlatform() 
    {
        return platform;
    }
}
