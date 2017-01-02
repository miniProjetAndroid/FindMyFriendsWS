/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Lieu;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mkass
 */
@Stateless
public class LieuFacade extends AbstractFacade<Lieu> implements LieuFacadeLocal {

    @PersistenceContext(unitName = "FindMyFriendsWS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LieuFacade() {
        super(Lieu.class);
    }
    
}
