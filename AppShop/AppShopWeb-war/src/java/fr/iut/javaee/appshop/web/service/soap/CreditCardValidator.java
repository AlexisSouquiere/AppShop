/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.web.service.soap;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author Alexis
 */
@WebService(serviceName = "CreditCardValidator")
@Stateless()
public class CreditCardValidator 
{
    @WebMethod(operationName = "validation")
    public boolean calcul(@WebParam(name = "card") String cardNumber) 
    {
        if(cardNumber.length() >= 16)
        {
            return true;
        }

        return false;
    }
}
