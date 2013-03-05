/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local;

import fr.iut.javaee.appshop.commons.Rate;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Audrey
 */
@Local
public interface RateServiceLocal 
{    
    public Double findApplicationRateAverage(int id);  
    
    public void persist(Rate r);
}
