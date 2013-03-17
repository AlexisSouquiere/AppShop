/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.web.validator;

import fr.iut.javaee.appshop.web.controller.PurchaseController;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Audrey
 */

@FacesValidator(value="cardValidator")
public class CardValidator implements  Validator
{    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {            
        PurchaseController purchaseController = (PurchaseController)component.getValueExpression("purchaseController").getValue(context.getELContext());
        boolean res = purchaseController.validation(value.toString());
        
        if(!res)
        {
            final String message = "Number of card must haves less than 16 characters";
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
            
            throw new ValidatorException(facesMsg);            
        }
    }
}
