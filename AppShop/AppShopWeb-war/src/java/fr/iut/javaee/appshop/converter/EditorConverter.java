/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.converter;

import fr.iut.javaee.appshop.commons.Editor;
import fr.iut.javaee.appshop.web.controller.EditorController;
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
@ManagedBean(name="editorConverter")
@RequestScoped
public class EditorConverter implements Converter 
{    
    @ManagedProperty(value = "#{editorController}")
    private EditorController controller;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) 
    {
        int id = Integer.parseInt(value);
        Editor e = controller.findOneById(id);
        return e;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) 
    {
        return ((Editor)value).getEditorId().toString();      
    }
    
    public void setController(EditorController controller)
    {
        this.controller = controller;
    }
}
