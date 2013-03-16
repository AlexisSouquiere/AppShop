/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.web.controller;

import fr.iut.javaee.appshop.commons.Editor;
import fr.iut.javaee.appshop.service.local.EditorServiceLocal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Alexis
 */
@ManagedBean(name = "editorController")
@RequestScoped
public class EditorController 
{
    @EJB
    private EditorServiceLocal service;
    
    private Editor editor = new Editor();
        
    public List<Editor> findAll()
    {
       return service.findAll();
    }
    
    public String editOneById(int id)
    {
        editor = service.findOneById(id);
        return "/admin/editors/editor.xhtml";
    }
    
    public Editor findOneById(int id)
    {
        return service.findOneById(id);
    }
    
    public String add()
    {
        service.persist(editor);
        return "/admin/editors/list.xhtml?faces-config=true";
    }
    
    public String update()
    {
        service.persist(editor);
        return "/admin/editors/list.xhtml?faces-config=true";
    }
    
    public String remove(Editor e)
    {
        service.remove(e);
        return "/admin/editors/list.xhtml?faces-config=true";
    }

    public Editor getEditor() 
    {
        return editor;
    }
}
