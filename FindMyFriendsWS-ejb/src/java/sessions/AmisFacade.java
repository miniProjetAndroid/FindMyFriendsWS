/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Amis;
import entities.Personne;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ilias
 */
@Stateless
public class AmisFacade extends AbstractFacade<Amis> implements AmisFacadeLocal {

    @PersistenceContext(unitName = "FindMyFriendsWS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AmisFacade() {
        super(Amis.class);
    }
    
    @Override
    public List <Personne> findAmis(Personne P){
        
   
    Query q = em.createNamedQuery("Amis.findAmisByPersonne");
    q.setParameter("personne", P);
    
    return (List <Personne>) q.getResultList();
    
    }
    
}
