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
public class Circuit extends Voyage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;
    private int nbKm;
    private String transport;

    public Circuit(Pays pays, String codeVoyage, String nom, String description, int duree, int nbKm, String transport) {
        super(pays, codeVoyage, nom, description, duree);
        this.nbKm = nbKm;
        this.transport = transport;
    }

    public Circuit() {
    }

    public int getNbKm() {
        return nbKm;
    }

    public void setNbKm(int nbKm) {
        this.nbKm = nbKm;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        String s = super.getNom();
        s += " (Code ";
        s += super.getCodeVoyage();
        s += "). ";
        s += super.getPays().getNom();
        s += "\nCircuit (";
        s += String.valueOf(duree);
        s += " jours, ";
        s += String.valueOf(nbKm);
        s += " kms, ";
        s += transport;
        return s;
    }
}
