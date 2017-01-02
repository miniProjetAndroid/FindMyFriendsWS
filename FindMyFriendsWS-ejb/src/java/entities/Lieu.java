/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mkass
 */
@Entity
@Table(name = "lieu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lieu.findAll", query = "SELECT l FROM Lieu l"),
    @NamedQuery(name = "Lieu.findById", query = "SELECT l FROM Lieu l WHERE l.id = :id"),
    @NamedQuery(name = "Lieu.findByNom", query = "SELECT l FROM Lieu l WHERE l.nom = :nom"),
    @NamedQuery(name = "Lieu.findByLongitude", query = "SELECT l FROM Lieu l WHERE l.longitude = :longitude"),
    @NamedQuery(name = "Lieu.findByLatitude", query = "SELECT l FROM Lieu l WHERE l.latitude = :latitude"),
    @NamedQuery(name = "Lieu.findByPlaceID", query = "SELECT l FROM Lieu l WHERE l.placeID = :placeID")})
public class Lieu implements Serializable {

    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 250)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "longitude")
    private double longitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "latitude")
    private double latitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "placeID")
    private int placeID;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "adresse")
    private String adresse;

    public Lieu() {
    }

    public Lieu(Integer id) {
        this.id = id;
    }

    public Lieu(Integer id, double longitude, double latitude, int placeID, String adresse) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.placeID = placeID;
        this.adresse = adresse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getPlaceID() {
        return placeID;
    }

    public void setPlaceID(int placeID) {
        this.placeID = placeID;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lieu)) {
            return false;
        }
        Lieu other = (Lieu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Lieu[ id=" + id + " ]";
    }


    
}
