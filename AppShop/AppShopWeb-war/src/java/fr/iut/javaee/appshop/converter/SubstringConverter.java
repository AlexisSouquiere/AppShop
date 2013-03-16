/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Alexis
 */
@FacesConverter("substringConverter")
public class SubstringConverter implements Converter 
{
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) 
    {
        int length = 50;
        if(((String)value).length() > length) {
            return ((String)value).substring(0, length).concat("...");
        }
        return (String)value;
    }
    
}
