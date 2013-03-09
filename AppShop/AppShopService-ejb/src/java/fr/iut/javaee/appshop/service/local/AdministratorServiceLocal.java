/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local;

import fr.iut.javaee.appshop.commons.Administrator;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Audrey
 */
@Local
public interface AdministratorServiceLocal 
{    
    public Administrator findOneById(Integer id);
    
    public void persist(Administrator a);
    
    public String authenticate(Administrator a);
}
