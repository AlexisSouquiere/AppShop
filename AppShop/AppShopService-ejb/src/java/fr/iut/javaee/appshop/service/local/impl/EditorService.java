/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.service.local.impl;

import fr.iut.javaee.appshop.commons.Editor;
import fr.iut.javaee.appshop.service.local.EditorServiceLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Audrey
 */
@Stateless
public class EditorService implements EditorServiceLocal
{
    @PersistenceContext(unitName="AppShopCommonsPU")
    private EntityManager em;

    @Override
    public List<Editor> findAll() 
    {
       return em.createNamedQuery("Editor.findAll").getResultList();
    }

    @Override
    public Editor findOneById(Integer id) 
    {
        return (Editor)em.createNamedQuery("Editor.findByEditorId")
                              .setParameter("editorId", id)
                              .getSingleResult();   
    }

    @Override
    public void persist(Editor e) 
    {
        em.persist(em.merge(e));
    }

    @Override
    public void remove(Editor e) 
    {
        em.remove(em.merge(e));
    }
    
    public void setEM(EntityManager em)
    {
        this.em = em;
    }
}
