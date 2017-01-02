/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Amis;
import entities.Personne;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ilias
 */
@Local
public interface AmisFacadeLocal {

    void create(Amis amis);

    void edit(Amis amis);

    void remove(Amis amis);

    Amis find(Object id);

    List<Amis> findAll();

    List<Amis> findRange(int[] range);

    int count();
      public List <Personne> findAmis(Personne P);
    
}
