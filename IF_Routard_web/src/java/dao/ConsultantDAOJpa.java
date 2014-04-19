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
public class ConsultantDAOJpa implements ConsultantDAO {
    
    @Override
        public void creerConsultant(Consultant c) {
        //TODO Ajouter try catch
        JpaUtil.obtenirEntityManager().persist(c);
    }
    
    @Override
    public void modifierConsultant(Consultant c) {
        JpaUtil.obtenirEntityManager().merge(c);
    }
    @Override
    public void supprimerConsultant(Consultant c) {
        JpaUtil.obtenirEntityManager().remove(c);
    }
}
