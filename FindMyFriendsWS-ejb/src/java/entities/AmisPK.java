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
public class AmisPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "personne")
    private int personne;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ami")
    private int ami;

    public AmisPK() {
    }

    public AmisPK(int personne, int ami) {
        this.personne = personne;
        this.ami = ami;
    }

    public int getPersonne() {
        return personne;
    }

    public void setPersonne(int personne) {
        this.personne = personne;
    }

    public int getAmi() {
        return ami;
    }

    public void setAmi(int ami) {
        this.ami = ami;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) personne;
        hash += (int) ami;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AmisPK)) {
            return false;
        }
        AmisPK other = (AmisPK) object;
        if (this.personne != other.personne) {
            return false;
        }
        if (this.ami != other.ami) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AmisPK[ personne=" + personne + ", ami=" + ami + " ]";
    }
    
}
