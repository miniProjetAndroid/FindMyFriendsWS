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
@Table(name = "amis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Amis.findAll", query = "SELECT a FROM Amis a"),
    @NamedQuery(name = "Amis.findByPersonne", query = "SELECT a FROM Amis a WHERE a.amisPK.personne = :personne"),
    @NamedQuery(name = "Amis.findByAmi", query = "SELECT a FROM Amis a WHERE a.amisPK.ami = :ami"),
    @NamedQuery(name = "Amis.findByDate", query = "SELECT a FROM Amis a WHERE a.date = :date"),
    @NamedQuery(name = "Amis.findAmisByPersonne", query = "SELECT a.personne1 FROM Amis a where a.personne2 = :personne")})
public class Amis implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AmisPK amisPK;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "ami", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Personne personne1;
    @JoinColumn(name = "personne", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Personne personne2;

    public Amis() {
    }

    public Amis(AmisPK amisPK) {
        this.amisPK = amisPK;
    }

    public Amis(int personne, int ami) {
        this.amisPK = new AmisPK(personne, ami);
    }

    public AmisPK getAmisPK() {
        return amisPK;
    }

    public void setAmisPK(AmisPK amisPK) {
        this.amisPK = amisPK;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Personne getPersonne1() {
        return personne1;
    }

    public void setPersonne1(Personne personne1) {
        this.personne1 = personne1;
    }

    public Personne getPersonne2() {
        return personne2;
    }

    public void setPersonne2(Personne personne2) {
        this.personne2 = personne2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (amisPK != null ? amisPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Amis)) {
            return false;
        }
        Amis other = (Amis) object;
        if ((this.amisPK == null && other.amisPK != null) || (this.amisPK != null && !this.amisPK.equals(other.amisPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Amis[ amisPK=" + amisPK + " ]";
    }
    
}
