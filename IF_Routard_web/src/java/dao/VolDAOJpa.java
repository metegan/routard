/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import metier.modele.Vol;

/**
 *
 * @author Antoine
 */
public class VolDAOJpa implements VolDAO{
    
    @Override
     public void creerVol(Vol v) {
        try{
        JpaUtil.obtenirEntityManager().persist(v);
        }
        catch( IllegalArgumentException e){}
    }
    
    @Override
    public void modifierVol(Vol v) {
                try{
        JpaUtil.obtenirEntityManager().merge(v);
        }
        catch( IllegalArgumentException e){}
    }
    
    @Override
    public void supprimerVol(Vol v) {
        JpaUtil.obtenirEntityManager().remove(v);
    }
    
    @Override
    public List<Vol> rechercherVol()
    {
        Query q = dao.JpaUtil.obtenirEntityManager().createQuery("Select v from Vol v");
        List<Vol> v = (List<Vol>)q.getSingleResult();
        return v;
    }


    @Override
    public Vol rechercherVolParId(int id)
    {
        Query q = dao.JpaUtil.obtenirEntityManager().createQuery("Select v from Vol v where v.id= :id")
        .setParameter("id", id);
        Vol v = (Vol)q.getSingleResult();
        return v;

    }
    @Override
    public Vol rechercherUnVolParVoyage(String codeVoy){
       try
       {
           Query q = dao.JpaUtil.obtenirEntityManager().createQuery("Select v.vols from Voyage v where v.codeVoyage= :codeVoy")
           .setParameter("codeVoy", codeVoy).setMaxResults(1);
            List<Vol> Liste = (List<Vol>)q.getResultList();
            Iterator<Vol> volItr = Liste.iterator();
            return volItr.next();
       }
       catch(NoResultException e)
       {
        return null;
       }
    }
    
    @Override
    public List<Vol> rechercherVolsParVoyage(String codeVoy){
       try
       {
           Query q = dao.JpaUtil.obtenirEntityManager().createQuery("Select v.vols from Voyage v where v.codeVoyage= :codeVoy")
           .setParameter("codeVoy", codeVoy);
            List<Vol> Liste = (List<Vol>)q.getResultList();
            return Liste;
       }
       catch(NoResultException e)
       {
        return null;
       }
    }
    @Override
    public List<Vol> rechercherVolParDate(Date date)
    {
        Query q = dao.JpaUtil.obtenirEntityManager().createQuery("Select v from Vol v where v.dateVol= :date")
        .setParameter("date", date);
        List<Vol> v = (List<Vol>)q;
        return v;
    }
         
    @Override
    public List<Vol> rechercherVolParVilleDep(String Ville)
    {
        Query q = dao.JpaUtil.obtenirEntityManager().createQuery("Select v from Vol v where v.villeDep= :Ville")
        .setParameter("Ville", Ville);
        List<Vol> v = (List<Vol>)q;
        return v;
    }
   
    @Override
        public List<Vol> rechercherVolParPrix(int min,int max)
    {
        Query q = dao.JpaUtil.obtenirEntityManager().createQuery("Select v from Vol v where v.prix>:min and v.prix<:max")
        .setParameter("min", min).setParameter("max", max);
        List<Vol> v = (List<Vol>)q.getSingleResult();
        return v;
    }


    
}
