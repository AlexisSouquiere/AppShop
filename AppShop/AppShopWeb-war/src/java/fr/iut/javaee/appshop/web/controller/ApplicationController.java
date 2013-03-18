package fr.iut.javaee.appshop.web.controller;

import fr.iut.javaee.appshop.commons.Application;
import fr.iut.javaee.appshop.commons.Users;
import fr.iut.javaee.appshop.service.local.ApplicationServiceLocal;
import fr.iut.javaee.appshop.service.local.EditorServiceLocal;
import fr.iut.javaee.appshop.service.local.PlatformServiceLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Alexis
 */
@ManagedBean(name = "applicationController")
@SessionScoped
public class ApplicationController implements Serializable
{
    @EJB
    private ApplicationServiceLocal service;
    @EJB
    private PlatformServiceLocal servicePlatform;
    @EJB
    private EditorServiceLocal serviceEditor;
    
    @ManagedProperty(value = "#{userController}")
    private UserController userController;
    
    private List<Application> applications = new ArrayList<Application>();
    private Application application = new Application();
    
    public List<Application> findAll()
    {
       return service.findAll();
    }
    
    public String findApplicationList()
    {
       applications = service.findApplicationList();
       return "/applications/list?faces-redirect=true";
    }
    
    public String findOneById(int id)
    {
        application = service.findOneById(id);
        if(userController.getUser().getUserGroupName() != null &&
                userController.getUser().getUserGroupName().equals("Admin")) {
            return "/admin/applications/application.xhtml?faces-redirect=true";
        }
        return "/applications/version?faces-redirect=true";
    }
    
    public String findByName(String name)
    {
        applications = service.findApplicationsByName(name);
        return "/applications/detail?faces-redirect=true";
    }
    
    public String findByPlatformId(int id)
    {
        applications = service.findApplicationsByPlatformId(id);
        return "/applications/list?faces-redirect=true";
    }
    
    public String findByEditorId(int id)
    {
        applications = service.findApplicationsByEditorId(id);
        return "/applications/list?faces-redirect=true";
    }
    
    public List<Application> findLastFive()
    {
        return service.findLastFiveApplicationsAdded();
    }
    
    public String update()
    {
        service.persist(application);
        return "/admin/applications/list.xhtml?faces-config=true";
    }
    
    public String remove(Application a)
    {
        service.remove(a);
        return "/admin/applications/list.xhtml?faces-config=true";
    }
    
    public List<Application> getApplications()
    {    
       return applications;
    }
    
    public Application getApplication()
    {
        return application;
    }
    
    public UserController getUserController()
    {
        return userController;
    }
    
    public void setUserController(UserController userController)
    {
        this.userController = userController;
    }
    
    
    //Application search //   
    
    private String choice;
    private String searchValue;

    public String searchApp()
    {
             
        if("ApplicationName".equals(choice))
        {
            applications = service.findApplicationsByName(searchValue);
            if(applications.size()>0){
                return "/applications/list?faces-redirect=true"; 
            }
            else
                return null;
        }        
        else if("Platform".equals(choice))
        {           
            applications = service.findApplicationsByPlatformName(searchValue);
            if(applications.size()>0){    
                return "/applications/list?faces-redirect=true";
            }
            else
                return null;
        }
        else if("Editor".equals(choice))
        {
            applications = service.findApplicationsByEditorName(searchValue);
            if(applications.size()>0){
                return "/applications/list?faces-redirect=true";
            }
        }        
        return null;
    }
    
    public void choiceChanged(ValueChangeEvent e){
       choice = e.getNewValue().toString(); 
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getChoice() {
        return choice;
    }

    public String getSearchValue() {
        return searchValue;
    }
    
    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
}
