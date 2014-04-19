/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import metier.modele.Vol;

/**
 *
 * @author Antoine
 */
public interface VolDAO{

    /**
     * Persiste un vol dans la base de données
     * @param v 
     */
    public void creerVol(Vol v);
    
    /**
     * Modifie un vol persisté dans la base de données
     * @param v 
     */
    public void modifierVol(Vol v);
    
    /**
     * Supprime le vol persisté de la base de données
     * @param v 
     */
    public void supprimerVol(Vol v);
    
    /**
     * Retourne les vols de la base de données
     * @return 
     */
    public List<Vol> rechercherVol();
    
    /**
     * Retourne le vol dont l'id est id
     * @param id
     * @return 
     */
    public Vol rechercherVolParId(int id);
    
    //TODO
    public Vol rechercherUnVolParVoyage(String codeVoy);
    
    public List<Vol> rechercherVolsParVoyage(String codeVoy);
    
    /**
     * Retourne les vols qui partent à la date passée en paramètre
     * @param date
     * @return 
     */
    public List<Vol> rechercherVolParDate(Date date);
    
    /**
     * Retourne les vols qui partent de la ville passée en paramètre
     * @param Ville
     * @return 
     */
    public List<Vol> rechercherVolParVilleDep(String Ville);
    
    /**
     * Retourne les vols dont le prix est compris entre min et max
     * @param min
     * @param max
     * @return 
     */
    public List<Vol> rechercherVolParPrix(int min,int max);

}

