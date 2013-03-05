/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local;

import fr.iut.javaee.appshop.commons.Purchase;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Audrey
 */
@Local
public interface PurchaseServiceLocal 
{
    public List<Purchase> findAll();
    
    public Purchase findOneById(Integer id);
    
     public void persist(Purchase p);
}
