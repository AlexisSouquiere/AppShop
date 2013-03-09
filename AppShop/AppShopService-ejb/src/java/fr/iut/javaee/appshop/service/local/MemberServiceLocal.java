/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local;

import fr.iut.javaee.appshop.commons.Member1;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Audrey
 */
@Local
public interface MemberServiceLocal 
{
    public Member1 findOneById(Integer id);
    
    public void persist(Member1 m);
    
    public void remove(Member1 m);
    
    public String authenticate(Member1 a);
}
