/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Personne;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mkass
 */
@Local
public interface PersonneFacadeLocal {

    void create(Personne personne);

    void edit(Personne personne);

    void remove(Personne personne);

    Personne find(Object id);

    List<Personne> findAll();

    List<Personne> findRange(int[] range);

    int count();
     public Personne findByMailAndPass(String mail, String pass);
     public List<Personne> search(Personne P,Integer offset, String nom, String prenom, Integer nbre, Boolean premierappel);
    
}
