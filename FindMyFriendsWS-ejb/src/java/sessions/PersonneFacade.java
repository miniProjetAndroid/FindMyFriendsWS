/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Personne;
import java.util.List;

import javax.ejb.EJB;

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

    @EJB
    private DemandeAjoutFacadeLocal demandeAjoutFacade;

    @EJB
    private AmisFacadeLocal amisFacade;

    @PersistenceContext(unitName = "FindMyFriendsWS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonneFacade() {
        super(Personne.class);
    }

    public Personne findByMailAndPass(String mail, String pass){
        
    Personne P= null;
    Query q = em.createNamedQuery("Personne.findByMailAndPass");
    q.setParameter("mail", mail);
    q.setParameter("pass", pass);
    List L=q.getResultList();
    if(L.size()>0){
    P=(Personne) L.get(0);}
    return P;
    }
    @Override
    public List<Personne> search(Personne P,Integer offset, String nom, String prenom, Integer nbre, Boolean premierappel) {
         List<Personne> E= null;
         List <Personne> Amis= amisFacade.findAmis(P);
         Amis.add(P);
         Amis.addAll(demandeAjoutFacade.findToutesDemandes(P));
         System.out.println(Amis.size());
        if(premierappel){
            
    Query q = em.createNamedQuery("Personne.findPeopleFirstCall");
     q.setParameter("Nom", "%"+nom+"%");
      q.setParameter("Prenom","%"+prenom+"%" );
    q.setParameter("list", Amis);
    E=(List<Personne>)q.setMaxResults(nbre).getResultList();}
        else{
            Personne p = find(offset);
           
            System.out.println(p.getNom());
    Query q = em.createNamedQuery("Personne.findPeople");
      q.setParameter("Nom", "%"+nom+"%");
      q.setParameter("Prenom","%"+prenom+"%" );
      q.setParameter("persnom", p.getNom());
      q.setParameter("persprenom", p.getPrenom());
      q.setParameter("pesid", p.getId());
      q.setParameter("list", Amis);
    E=(List<Personne>)q.setMaxResults(nbre).getResultList();
    
        }
        if(Amis!=null){
   E.removeAll(Amis);}
    return E;
    } 

    
}
