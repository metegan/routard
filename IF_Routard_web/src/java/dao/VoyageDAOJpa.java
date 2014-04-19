/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import metier.modele.Pays;
import metier.modele.Voyage;

/**
 *
 * @author Antoine
 */
public class VoyageDAOJpa implements VoyageDAO {

    @Override
    public void creerVoyage(Voyage v) {
        //TODO Ajouter try catch
        JpaUtil.obtenirEntityManager().persist(v);
    }

    @Override
    public void modifierVoyage(Voyage v) {
        JpaUtil.obtenirEntityManager().merge(v);

    }

    @Override
    public void supprimerVoyage(Voyage v) {
        JpaUtil.obtenirEntityManager().remove(v);
    }

    @Override
    public List<Voyage> rechercherVoyage() {
        Query q = dao.JpaUtil.obtenirEntityManager().createQuery("Select v from Voyage v");
        List<Voyage> v = (List<Voyage>) q.getResultList();
        return v;
    }

    @Override
    public Voyage rechercherVoyageParNom(String nom) {
        Query q = dao.JpaUtil.obtenirEntityManager().createQuery("Select v from Voyage v where v.nom= :nom")
                .setParameter("nom", nom);
        Voyage v = (Voyage) q.getResultList();
        return v;
    }

    @Override
    public Voyage rechercherVoyageParId(int id) {
        Query q = dao.JpaUtil.obtenirEntityManager().createQuery("Select v from Voyage v where v.id= :id")
                .setParameter("id", id);
        Voyage v = (Voyage) q.getSingleResult();
        return v;
    }

    @Override
    public Voyage rechercherVoyageParCode(String codeVoy) {

        Query q = dao.JpaUtil.obtenirEntityManager().createQuery("Select v from Voyage v where v.codeVoyage = :codeVoy")
                .setParameter("codeVoy", codeVoy);
        Voyage v = (Voyage) q.getSingleResult();
        return v;
    }

    @Override
    public List<Voyage> rechercherVoyageParDate(Date date) {
        Query q = dao.JpaUtil.obtenirEntityManager().createQuery("Select v from Voyage v where v.dateDep= :date")
                .setParameter("date", date);
        List<Voyage> v = (List<Voyage>) q;
        return v;
    }

    @Override
    public List<Voyage> rechercherVoyageParDuree(int min, int max) {
        Query q = dao.JpaUtil.obtenirEntityManager().createQuery("Select v from Voyage v where v.duree>:min and v.duree<:max")
                .setParameter("min", min).setParameter("max", max);
        List<Voyage> v = (List<Voyage>) q.getSingleResult();
        return v;
    }

    @Override
    public List<Voyage> rechercherVoyageParPays(Pays pays) {
        Query q = dao.JpaUtil.obtenirEntityManager().createQuery("Select v from Voyage v where v.pays = :pays")
                .setParameter("pays", pays);
        List<Voyage> v = (List<Voyage>) q.getResultList();
        return v;
    }

    @Override
    public List<Voyage> rechercheFiltre(String codeVoyage, String nom, String pays) {
        Boolean where = false;
        String req = "Select v from Voyage v";

        if (!pays.equals("")) {
            req += ", Pays p where v.codePays=p.code and p.nom=:pays";
            where = true;
        }
        if (!nom.equals("")) {
            if (!where) {
                req += " where";
                where = true;
            } else {
                req += " and";
            }
            req += " v.nom like '%:nom%'";
        }
        if (!codeVoyage.equals("")) {
            if (!where) {
                req += " where";
            } else {
                req += " and";
            }
            req += " v.codePays like '%:code%'";
        }
        Query q = JpaUtil.obtenirEntityManager().createQuery(req);
        if (!pays.equals("")) {
            q.setParameter("pays", pays);
        }
        if (!nom.equals("")) {
            q.setParameter("nom", nom);
        }
        if (!codeVoyage.equals("")) {
            q.setParameter("code", codeVoyage);
        }
        List<Voyage> v = (List<Voyage>) q.getSingleResult();
        return v;
    }
}