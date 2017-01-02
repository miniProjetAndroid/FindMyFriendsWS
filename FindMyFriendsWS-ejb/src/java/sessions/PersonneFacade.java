/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Personne;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mkass
 */
@Stateless
public class PersonneFacade extends AbstractFacade<Personne> implements PersonneFacadeLocal {

    @PersistenceContext(unitName = "FindMyFriendsWS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonneFacade() {
        super(Personne.class);
    }

    @Override
    public Personne findByMailAndPass(String mail, String pass) {
        Personne P= null;
        Query q = em.createNamedQuery("Personne.findByMailAndPass");
        q.setParameter("mail", mail);
        q.setParameter("pass", pass);
        List L=q.getResultList();
        if(L.size()>0){
            P=(Personne) L.get(0);}
        return P;
    }
    
}
