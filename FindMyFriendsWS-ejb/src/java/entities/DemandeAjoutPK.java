/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ilias
 */
@Embeddable
public class DemandeAjoutPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "emetteur")
    private int emetteur;
    @Basic(optional = false)
    @NotNull
    @Column(name = "recepteur")
    private int recepteur;

    public DemandeAjoutPK() {
    }

    public DemandeAjoutPK(int emetteur, int recepteur) {
        this.emetteur = emetteur;
        this.recepteur = recepteur;
    }

    public int getEmetteur() {
        return emetteur;
    }

    public void setEmetteur(int emetteur) {
        this.emetteur = emetteur;
    }

    public int getRecepteur() {
        return recepteur;
    }

    public void setRecepteur(int recepteur) {
        this.recepteur = recepteur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) emetteur;
        hash += (int) recepteur;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DemandeAjoutPK)) {
            return false;
        }
        DemandeAjoutPK other = (DemandeAjoutPK) object;
        if (this.emetteur != other.emetteur) {
            return false;
        }
        if (this.recepteur != other.recepteur) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DemandeAjoutPK[ emetteur=" + emetteur + ", recepteur=" + recepteur + " ]";
    }
    
}
