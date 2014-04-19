/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 *
 * @author Antoine
 */
@Entity
public class Vol implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Temporal(TemporalType.DATE) //DATE,TIME OU TIMESTAMP
    private Date dateVol;
    
    private int prix;
    
    private String villeDep;
    
    @ManyToOne
    private Voyage voyage;
    
    private String transport;
    
    @OneToMany(mappedBy="vol")
    private List<Devis> devis;

    public Vol() {
    }

    public Date getDateVol() {
        return dateVol;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public void setDateVol(Date dateVol) {
        this.dateVol = dateVol;
    }

    public Voyage getVoyage() {
        return voyage;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }

    public List<Devis> getReservation() {
        return devis;
    }

    public void setReservation(List<Devis> reservation) {
        this.devis = reservation;
    }

    public void addDevis ( Devis d) {
        this.devis.add(d);
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getVilleDep() {
        return villeDep;
    }

    public void setVilleDep(String villeDep) {
        this.villeDep = villeDep;
    }

    public Vol(Date dateVol, int prix, String villeDep, String transport) {
        this.dateVol = dateVol;
        this.prix = prix;
        this.villeDep = villeDep;
        this.transport = transport;
    }

    public Vol(Date dateVol, int prix, String villeDep, Voyage voyage, String transport) {
        this.dateVol = dateVol;
        this.prix = prix;
        this.villeDep = villeDep;
        this.voyage = voyage;
        this.transport = transport;
    }

    

   
    public int getId() {
        return id;
    }

}
