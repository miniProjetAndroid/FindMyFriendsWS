/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Amis;
import entities.AmisPK;
import entities.DemandeAjout;
import entities.Personne;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ilias
 */
@Stateless
public class DemandeAjoutFacade extends AbstractFacade<DemandeAjout> implements DemandeAjoutFacadeLocal {

    @EJB
    private AmisFacadeLocal amisFacade;

    @PersistenceContext(unitName = "FindMyFriendsWS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandeAjoutFacade() {
        super(DemandeAjout.class);
    }
    
        @Override
    public List <Personne> findDemandes(Personne P){
        
   
    Query q = em.createNamedQuery("DemandeAjout.findAmisByPersonne");
    q.setParameter("personne", P);
    
    return (List <Personne>) q.getResultList();
    
    }

    @Override
    public void decision(Personne P, Personne P2, Boolean decision) {
        if(decision){
            AmisPK A = new AmisPK();
            A.setAmi(P.getId());
            A.setPersonne(P2.getId()); 
            Amis amis=new Amis();
            amis.setAmisPK(A);
            amis.setPersonne1(P);
            amis.setPersonne2(P2);
            amisFacade.create(amis);
            A = new AmisPK();
            amis=new Amis();
             A.setAmi(P2.getId());
            A.setPersonne(P.getId());
            amis.setAmisPK(A);
            amis.setPersonne1(P2);
            amis.setPersonne2(P);
            amisFacade.create(amis);
         }
        deleteDemande(P,P2);
        
    }

    private void deleteDemande(Personne P, Personne P2) {
        Query q = em.createNamedQuery("DemandeAjout.delete");
    q.setParameter("recepteur", P);
    q.setParameter("emetteur", P2);
    q.executeUpdate();
        
    }

    @Override
    public List<Personne> findToutesDemandes(Personne P) {
       Query q = em.createNamedQuery("DemandeAjout.findAmisByPersonne");
    q.setParameter("personne", P);
    List <Personne> LP =(List <Personne>) q.getResultList();
    Query q2 = em.createNamedQuery("DemandeAjout.findPersonneByAmis");
    q2.setParameter("personne", P);
    LP.addAll((List <Personne>) q2.getResultList());
    return LP;}
    
}
