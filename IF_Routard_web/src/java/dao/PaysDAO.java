/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import metier.modele.Pays;

/**
 *
 * @author Antoine
 */
public interface PaysDAO {
    
    /**
     * Persiste un pays dans la base de données
     * @param p 
     */
    public void creerPays(Pays p);
    
    /**
     * Modifie un pays persisté dans la base de données
     * @param p 
     */
    public void modifierPays(Pays p);
    
    /**
     * Supprime le pays persisté de la base de données
     * @param p 
     */
    public void supprimerPays(Pays p);
    
    /**
     * Retourne tous les pays de la base de données
     * @return 
     */
    public List<Pays> rechercherPays();
    
    /**
     * Retourne le pays dont l'id est id
     * @param id
     * @return 
     */
    public Pays rechercherPaysParId(int id);
    
    /**
     * Retourne le pays dont le nom est nom
     * @param nom
     * @return 
     */
    public  Pays rechercherPaysParNom(String nom);
        
    /**
     * Retourne les pays présents sur le continent dont le nom est passé en paramètre
     * @param continent
     * @return 
     */
    public List<Pays> rechercherPaysParContinent(String continent);
    
    /**
     * Retourne le pays dont le code est codePays
     * @param codePays
     * @return 
     */
    public Pays rechercherPaysParCodePays(String codePays);

    
}
