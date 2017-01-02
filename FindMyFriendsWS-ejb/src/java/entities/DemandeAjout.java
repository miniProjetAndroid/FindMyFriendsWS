/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ilias
 */
@Entity
@Table(name = "demande_ajout")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DemandeAjout.findAll", query = "SELECT d FROM DemandeAjout d"),
    @NamedQuery(name = "DemandeAjout.findByEmetteur", query = "SELECT d FROM DemandeAjout d WHERE d.demandeAjoutPK.emetteur = :emetteur"),
    @NamedQuery(name = "DemandeAjout.findByRecepteur", query = "SELECT d FROM DemandeAjout d WHERE d.demandeAjoutPK.recepteur = :recepteur"),
    @NamedQuery(name = "DemandeAjout.findByDate", query = "SELECT d FROM DemandeAjout d WHERE d.date = :date"),
    @NamedQuery(name = "DemandeAjout.findAmisByPersonne", query = "SELECT a.personne FROM DemandeAjout a where a.personne1 = :personne"),
    @NamedQuery(name = "DemandeAjout.findPersonneByAmis", query = "SELECT a.personne1 FROM DemandeAjout a where a.personne = :personne"),
    @NamedQuery(name = "DemandeAjout.delete", query = "DELETE FROM DemandeAjout a where a.personne1 = :recepteur and a.personne = :emetteur")})
public class DemandeAjout implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DemandeAjoutPK demandeAjoutPK;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "emetteur", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Personne personne;
    @JoinColumn(name = "recepteur", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Personne personne1;

    public DemandeAjout() {
    }

    public DemandeAjout(DemandeAjoutPK demandeAjoutPK) {
        this.demandeAjoutPK = demandeAjoutPK;
    }

    public DemandeAjout(int emetteur, int recepteur) {
        this.demandeAjoutPK = new DemandeAjoutPK(emetteur, recepteur);
    }

    public DemandeAjoutPK getDemandeAjoutPK() {
        return demandeAjoutPK;
    }

    public void setDemandeAjoutPK(DemandeAjoutPK demandeAjoutPK) {
        this.demandeAjoutPK = demandeAjoutPK;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Personne getPersonne1() {
        return personne1;
    }

    public void setPersonne1(Personne personne1) {
        this.personne1 = personne1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (demandeAjoutPK != null ? demandeAjoutPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DemandeAjout)) {
            return false;
        }
        DemandeAjout other = (DemandeAjout) object;
        if ((this.demandeAjoutPK == null && other.demandeAjoutPK != null) || (this.demandeAjoutPK != null && !this.demandeAjoutPK.equals(other.demandeAjoutPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DemandeAjout[ demandeAjoutPK=" + demandeAjoutPK + " ]";
    }
    
}
