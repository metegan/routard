/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import metier.modele.Consultant;

/**
 *
 * @author Antoine
 */
public interface ConsultantDAO{

    /**
     * Persiste un consultant dans la base de données
     * @param c 
     */
    public void creerConsultant(Consultant c) ;
    
    /**
     * Modifie un consultant persisté dans la base
     * @param c 
     */
    public void modifierConsultant(Consultant c);
    
    /**
     * Supprime un consultant persisté de la base
     * @param c 
     */
    public void supprimerConsultant(Consultant c);
}
