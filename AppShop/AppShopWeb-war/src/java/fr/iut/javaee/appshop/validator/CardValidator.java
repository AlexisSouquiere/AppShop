/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.web.validator;

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

@FacesValidator(value="cardValidator")
public class CardValidator implements  Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        int cardSize = value.toString().length();
        
        if(cardSize < 16)
        {
            final String message = "Number of card must haves less than 16 characters";
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
            
            throw new ValidatorException(facesMsg);
            
        }
    }
    
}
