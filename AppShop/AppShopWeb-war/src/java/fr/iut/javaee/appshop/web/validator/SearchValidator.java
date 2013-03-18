/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.web.validator;

import fr.iut.javaee.appshop.web.controller.ApplicationController;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Audrey
 */

@FacesValidator(value="searchValidator")
public class SearchValidator implements  Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        ApplicationController appCtrl = (ApplicationController)component.getValueExpression("applicationController").getValue(context.getELContext());
               
        //set the new valeur at choice
        appCtrl.setSearchValue(value.toString());       
        
        String res = appCtrl.searchApp();  
        
        if(res == null)
        {
            final String message = "There are no results for this search";
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
            
            throw new ValidatorException(facesMsg);
        }
    }
    
}
