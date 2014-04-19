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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author Antoine
 */
@Entity
public class Pays implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nom;
    private String codePays;
    private String region;
    private String capitale;
    private String langues;
    private float superficie;
    private float population;
    private String regime;
    
    @OneToMany(mappedBy="pays")
    private List<Voyage> voyage;
    
    @ManyToMany
    private List<Conseiller> conseillers;
    
    public Pays() {
    }

    public Pays( String nom, String code, String region, String capitale, String langues, float superficie, float population, String regime) {
        this.nom = nom;
        this.codePays = code;
        this.region = region;
        this.capitale = capitale;
        this.langues = langues;
        this.superficie = superficie;
        this.population = population;
        this.regime = regime;
        this.voyage = new ArrayList();
        this.conseillers = new ArrayList();
    }

    public void addVoyage(Voyage v) {
        this.voyage.add(v);
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodePays() {
        return codePays;
    }

    public void setCodePays(String code) {
        this.codePays = code;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCapitale() {
        return capitale;
    }

    public void setCapitale(String capitale) {
        this.capitale = capitale;
    }

    public String getLangues() {
        return langues;
    }

    public void setLangues(String langues) {
        this.langues = langues;
    }

    public float getSuperficie() {
        return superficie;
    }

    public void setSuperficie(float superficie) {
        this.superficie = superficie;
    }

    public float getPopulation() {
        return population;
    }

    public void setPopulation(float population) {
        this.population = population;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public int getId() {
        return id;
    }

    public void ajouterConseiller(Conseiller conseiller) {
        this.conseillers.add(conseiller);
    }

}
