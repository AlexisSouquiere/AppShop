/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.web.controller;

import fr.iut.javaee.appshop.commons.Collection;
import fr.iut.javaee.appshop.commons.Users;
import fr.iut.javaee.appshop.service.local.CollectionServiceLocal;
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
    
    @ManagedProperty(value = "#{userController.user}")
    private Users user;

    private List<Collection> collections = new ArrayList<Collection>();
    private Collection collection = new Collection();
    
    public List<Collection> findCollectionsByUserId()
    {
        return service.findCollectionsByMemberId(user.getUserId());
    }
    
    public String editOneById(int id)
    {
        collection = service.findOneById(id);
        return "/AppShopWeb-war/protected/new.xhtml?faces-redirect=true";
    }
    
    public String add()
    {
        collection.setCollectionUsers(user);
        service.persist(collection);
        return "/AppShopWeb-war/protected/collections/new.xhtml?faces-redirect=true";
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
}

