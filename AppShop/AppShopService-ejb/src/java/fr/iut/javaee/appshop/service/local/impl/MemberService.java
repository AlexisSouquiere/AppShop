/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local.impl;

import fr.iut.javaee.appshop.commons.Member1;
import fr.iut.javaee.appshop.service.local.MemberServiceLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Audrey
 */
@Stateless
public class MemberService implements MemberServiceLocal
{
    @PersistenceContext(unitName="AppShopCommonsPU")
    private EntityManager em;

    @Override
    public Member1 findOneById(Integer id) 
    {
        return (Member1)em.createNamedQuery("Member1.findByMemberId")
                              .setParameter("memberId", id)
                              .getSingleResult();  
    }

    @Override
    public void persist(Member1 m) 
    {
        em.persist(m);
    }

    @Override
    public void remove(Member1 m) 
    {
        em.remove(m);
    }   

    @Override
    public boolean authenticate(Member1 m) 
    {
        Query q = em.createQuery("SELECT m FROM Member1 m "
                + "WHERE m.memberUsername = :paramUsername "
                + "AND m.memberPassword = : paramPassword");
        q.setParameter("paramUsername", m.getMemberUsername());
        q.setParameter("paramPassword", m.getMemberPassword());
        
        Member1 mem = (Member1) q.getSingleResult();
        
        return mem != null ? true : false;
    }
}
