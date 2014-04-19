/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import metier.modele.Pays;
import metier.modele.Voyage;

/**
 *
 * @author Antoine
 */
public interface VoyageDAO{

    /**
     * Persiste un voyage dans la base de données
     * @param v 
     */
    public void creerVoyage(Voyage v);
    
    /**
     * Modifie un voyage persisté dans la base de données
     * @param v 
     */
    public void modifierVoyage(Voyage v);
    
    /**
     * Supprime le voyage persisté de la base de données
     * @param v 
     */
    public void supprimerVoyage(Voyage v);
    
    /**
     * Retourne tous les voyages de la base
     * @return 
     */
    public List<Voyage> rechercherVoyage();
    
    /**
     * Retourne le voyage dont le nom est nom
     * @param nom
     * @return 
     */
    public Voyage rechercherVoyageParNom(String nom);
    
    /**
     * Retourne le voyage dont l'id est id
     * @param id
     * @return 
     */
    public Voyage rechercherVoyageParId(int id);
    
    /**
     * Retourne le voyage dont le code est codeVoy
     * @param codeVoy
     * @return 
     */
    public Voyage rechercherVoyageParCode(String codeVoy);
    
    /**
     * Retourne les voyages qui débutent à la date passée en paramètre
     * @param date
     * @return 
     */
    public List<Voyage> rechercherVoyageParDate(Date date);
    
    /**
     * Retourne les voyages dont la durée est comprise entre min et max
     * @param min
     * @param max
     * @return 
     */
    public List<Voyage> rechercherVoyageParDuree(int min,int max);
    
    /**
     * Retourne les voyages qui se déroulent dans le pays passé en paramètre
     * @param pays
     * @return 
     */
    public List<Voyage> rechercherVoyageParPays(Pays pays);
    
    /**
     * Retourne les voyages qui correspondent aux filtres passés en paramètre
     * @param codeVoyage
     * @param nom
     * @param pays
     * @return 
     */
    public List<Voyage> rechercheFiltre (String codeVoyage, String nom, String pays);
}

