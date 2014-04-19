/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import metier.modele.Client;
import metier.modele.Devis;

/**
 *
 * @author Antoine
 */
public interface DevisDAO {

    /**
     * Persiste un devis dans la base de données
     * @param d 
     */
    public void creerDevis(Devis d);

    /**
     * Modifie un devis persisté dans la base de données
     * @param d 
     */
    public void modifierDevis(Devis d);

    /**
     * Supprime un devis persisté de la base de données
     * @param d 
     */
    public void supprimerDevis(Devis d);

    /**
     * Retourne les devis qui impliquent le client passé en paramètre
     * @param c
     * @return 
     */
    public List<Devis> rechercherDevisParClient(Client c);

    /**
     * Retourne le devis dont l'id est id
     * @param id
     * @return 
     */
    public Devis rechercherDevisParId(int id);
}
