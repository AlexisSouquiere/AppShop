/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.web.converter;

import fr.iut.javaee.appshop.commons.Application;
import fr.iut.javaee.appshop.commons.Editor;
import fr.iut.javaee.appshop.service.local.ApplicationServiceLocal;
import fr.iut.javaee.appshop.web.controller.ApplicationController;
import fr.iut.javaee.appshop.web.controller.EditorController;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Alexis
 */
@ManagedBean(name="applicationConverter")
@RequestScoped
public class ApplicationConverter implements Converter 
{    
    @EJB
    private ApplicationServiceLocal service;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) 
    {
        int id = Integer.parseInt(value);
        Application a = service.findOneById(id);
        return a;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) 
    {
        return ((Application)value).getApplicationId().toString();      
    }
    
    public void setService(ApplicationServiceLocal service)
    {
        this.service = service;
    }
}
