/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.web.controller;

import fr.iut.javaee.appshop.commons.Application;
import fr.iut.javaee.appshop.commons.ApplicationCollection;
import fr.iut.javaee.appshop.commons.Collection;
import fr.iut.javaee.appshop.commons.Users;
import fr.iut.javaee.appshop.service.local.ApplicationCollectionServiceLocal;
import fr.iut.javaee.appshop.service.local.CollectionServiceLocal;
import fr.iut.javaee.appshop.service.local.DownloadServiceLocal;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Alexis
 */
@ManagedBean(name = "collectionController")
@SessionScoped
public class CollectionController implements Serializable 
{
    @EJB
    private CollectionServiceLocal service;
    
    @EJB
    ApplicationCollectionServiceLocal serviceCollection;
    
    @EJB
    private DownloadServiceLocal serviceDownload;
    
    @ManagedProperty(value = "#{userController.user}")
    private Users user;

    private List<Collection> collections = new ArrayList<Collection>();
    private Collection collection = new Collection();
    
    private Application application = new Application();
    
    public List<Collection> findCollectionsByUserId()
    {
        return service.findCollectionsByMemberId(user.getUserId());
    }
    
    public List<ApplicationCollection> findApplicationsByCollectionId()
    {
        return serviceCollection.findApplicationsByCollectionId(collection.getCollectionId());
    }
    
    public String editOneById(int id)
    {
        collection = service.findOneById(id);
        return "/protected/collections/new.xhtml?faces-redirect=true";
    }
    
    public void add()
    {
        collection.setCollectionUsers(user);
        service.persist(collection);
        collection = new Collection();
    }
    
    public void rename()
    {
        service.persist(collection);
    }
    
    public void remove()
    {
        service.remove(collection);
        collection = new Collection();
    } 
    
    public void addApplication()
    {
        ApplicationCollection a = new ApplicationCollection();
        a.setApplicationCollectionApplication(application);
        a.setApplicationCollectionCollection(collection);
        
        List<ApplicationCollection> l = collection.getApplicationCollectionList();
        l.add(a);
        collection.setApplicationCollectionList(l); 
        
        service.persist(collection);
    }
    
    public void removeApplication(ApplicationCollection a)
    {        
        List<ApplicationCollection> l = collection.getApplicationCollectionList();
        l.remove(a);
        collection.setApplicationCollectionList(l); 
        
        service.persist(collection);
    }
    
    public List<Application> findApplicationsDownloaded()
    {
        return serviceDownload.findApplicationsDownloadedByMember(user);
    }   

    public void setUser(Users user) {
        this.user = user;
    }
    
    public Users getUser() {
        return user;
    }

    public List<Collection> getCollections() {
        return collections;
    }

    public Collection getCollection() {
        return collection;
    }
    
    public void setApp(Application a)
    {
        this.application = a;
    }
    
    public Application getApp()
    {
        return application;
    }
}

