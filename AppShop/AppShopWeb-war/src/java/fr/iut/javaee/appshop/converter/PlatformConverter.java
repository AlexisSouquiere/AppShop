/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.converter;

import fr.iut.javaee.appshop.commons.Platform;
import fr.iut.javaee.appshop.service.local.PlatformServiceLocal;
import fr.iut.javaee.appshop.web.controller.PlatformController;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Alexis
 */
@ManagedBean(name="platformConverter")
@RequestScoped
public class PlatformConverter implements Converter 
{    
    @ManagedProperty(value = "#{platformController}")
    private PlatformController controller;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) 
    {
        int id = Integer.parseInt(value);
        Platform p = controller.findOneById(id);
        return p;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) 
    {
        return ((Platform)value).getPlatformId().toString();      
    }
    
    public void setController(PlatformController controller)
    {
        this.controller = controller;
    }
}
