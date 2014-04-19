/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Antoine
 */
@Entity
public class Sejour extends Voyage {

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;
        
    private String residence;

    public Sejour(Pays pays, String codeVoyage, String nom, String description, int duree,String residence) {
        super(pays, codeVoyage, nom, description, duree);
        this.residence = residence;
    }

    public Sejour() {
    }
    
    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        String s="Résidence :";
        s+= residence;
        return s;
    }
    
    
    
}
