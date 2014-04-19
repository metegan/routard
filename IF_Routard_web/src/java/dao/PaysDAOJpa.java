/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.Query;
import metier.modele.Pays;

/**
 *
 * @author Antoine
 */
public class PaysDAOJpa implements PaysDAO {

    
    @Override
     public void creerPays(Pays p) {
        //TODO Ajouter try catch
        JpaUtil.obtenirEntityManager().persist(p);
    }
    
    @Override
    public void modifierPays(Pays p) {
        JpaUtil.obtenirEntityManager().merge(p);
    }
    
    @Override
    public void supprimerPays(Pays p) {
        JpaUtil.obtenirEntityManager().remove(p);
    }
    
    @Override
    public List<Pays> rechercherPays()
    {
        Query q = dao.JpaUtil.obtenirEntityManager().createQuery("Select p from Pays p");
        List<Pays> p = (List<Pays>)q.getResultList();
        return p;
    }
     
    @Override
    public Pays rechercherPaysParId(int id)
    {
        Query q = JpaUtil.obtenirEntityManager().createQuery("Select p from Pays p where p.id= :id")
        .setParameter("id", id);
        Pays p = (Pays)q.getSingleResult();
        return p;
    }

    @Override
    public Pays rechercherPaysParNom(String nom)
    {
        Query q = JpaUtil.obtenirEntityManager().createQuery("Select p from Pays p where p.Nom= :nom")
        .setParameter("nom", nom);
        Pays p = (Pays)q.getSingleResult();
        return p;
    }
        
    
    @Override
    public List<Pays> rechercherPaysParContinent(String continent)
    {
        Query q = JpaUtil.obtenirEntityManager().createQuery("Select p from Pays p where p.region=:region")
        .setParameter("region", continent);
        List<Pays> p = (List<Pays>)q.getResultList();
        return p;
    }
    

    @Override
    public Pays rechercherPaysParCodePays(String codePays)
    {
        Query q = JpaUtil.obtenirEntityManager().createQuery("Select p from Pays p where p.codePays=:codePays")
        .setParameter("codePays", codePays);
        Pays p = (Pays)q.getSingleResult();
        return p;
    }

}

