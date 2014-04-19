/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import metier.modele.Conseiller;
import metier.modele.Pays;

/**
 *
 * @author Antoine
 */public interface ConseillerDAO {

     /**
      * Persiste un conseiller dans la base de données
      * @param c 
      */
    public void creerConseiller(Conseiller c);
    
    /**
     * Modifie un conseiller persisté dans la base
     * @param c 
     */
    public void modifierConseiller(Conseiller c);
    
    /**
     * Supprime un conseiller persisté dans la base
     * @param c 
     */
    public void supprimerConseiller(Conseiller c); 
    
    /**
     * Retourne les conseillers dont le nom est nom
     * @param nom
     * @return 
     */
    public List<Conseiller> rechercherConseillerParNom(String nom);   
    
    /**
     * Retourne le conseiller dont l'id est id
     * @param id
     * @return 
     */
    public Conseiller rechercherConseillerParId(int id);
    
    /**
     * Retourne le conseiller dont l'e-mail est mail
     * @param mail
     * @return 
     */
    public Conseiller rechercherConseillerParMail(String mail);
    
    /**
     * Retourne le conseiller qui dispose du moins de clients pour le pays
     * passé en paramètre
     * @param p
     * @return 
     */
    public Conseiller rechercherConseillerDispoParPays(Pays p);

    /**
     * Retourne le conseiller qui dispose du moins de clients
     * @return 
     */
    public Conseiller rechercherConseillerDispo();
 
}

