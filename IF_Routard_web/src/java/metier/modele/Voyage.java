/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Antoine
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Voyage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;
    protected String codeVoyage;
    protected String nom;
    protected String description;
    protected int duree;
    /**
     * Liste des vols disponibles pour ce voyage
     */
    @OneToMany( mappedBy = "voyage")
    private List<Vol> vols;
    @OneToMany(mappedBy = "voyage")
    private List<Devis> devis;
    @ManyToOne
    private Pays pays;

    public Voyage() {
    }

    public Voyage(Pays pays, String codeVoyage, String nom, String description, int duree) {
        this.codeVoyage = codeVoyage;
        this.nom = nom;
        this.description = description;
        this.duree = duree;
        this.pays = pays;
        this.devis = new ArrayList();
        this.vols = new ArrayList();
    }

    public List<Vol> getVols() {
        return vols;
    }

    public void setVols(List<Vol> vols) {
        this.vols = vols;
    }

    public List<Devis> getDevis() {
        return devis;
    }

    public void setDevis(List<Devis> devis) {
        this.devis = devis;
    }

    public void addDevis(Devis d) {
        this.devis.add(d);
    }

    public int getId() {
        return id;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public String getCodeVoyage() {
        return codeVoyage;
    }

    public void setCodeVoyage(String codeVoyage) {
        this.codeVoyage = codeVoyage;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
    
}
