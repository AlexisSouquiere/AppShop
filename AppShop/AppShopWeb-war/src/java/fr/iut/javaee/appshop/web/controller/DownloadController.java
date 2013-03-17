/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.web.controller;

import fr.iut.javaee.appshop.commons.Application;
import fr.iut.javaee.appshop.commons.Download;
import fr.iut.javaee.appshop.commons.Users;
import fr.iut.javaee.appshop.service.local.DownloadServiceLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Alexis
 */
@ManagedBean(name = "downloadController")
@SessionScoped
public class DownloadController implements Serializable 
{        
    @EJB
    private DownloadServiceLocal serviceDownload;
    
    @ManagedProperty(value = "#{userController.user}")
    private Users user;

    public List<Download> findCollectionsByUser()
    {
        return serviceDownload.findByMember(user);
    }
    
    public Integer findDownloadStats()
    {
        return serviceDownload.findTotalDownloads();
    }
    
    public Application findApplicationMostDownloaded()
    {
        return serviceDownload.findApplicationMostDownloaded();
    }
    
    public Users findMostActiveMember()
    {
        return serviceDownload.findMostActiveMember();
    }
    
    public void computeStats()
    {
        serviceDownload.computeStats();
    }

    public void setUser(Users user) 
    {
        this.user = user;
    }
}

