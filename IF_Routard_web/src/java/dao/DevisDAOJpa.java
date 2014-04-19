/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.Query;
import metier.modele.Devis;
import metier.modele.Client;

/**
 *
 * @author Antoine
 */
public class DevisDAOJpa implements DevisDAO {

    @Override
    public void creerDevis(Devis d) {
        JpaUtil.obtenirEntityManager().persist(d);
    }

    @Override
    public void modifierDevis(Devis d) {
        JpaUtil.obtenirEntityManager().merge(d);
    }

    @Override
    public void supprimerDevis(Devis d) {
        JpaUtil.obtenirEntityManager().remove(d);
    }

    @Override
    public List<Devis> rechercherDevisParClient(Client c) {
        Query q = JpaUtil.obtenirEntityManager().createQuery("Select d from  Devis d where d.client= :client")
                .setParameter("client", c);
        List<Devis> d = (List<Devis>) q.getResultList();
        return d;
    }

    @Override
    public Devis rechercherDevisParId(int id) {
        Query q = JpaUtil.obtenirEntityManager().createQuery("Select d from  Devis d where d.id = :id")
                .setParameter("id", id);
        Devis d = (Devis) q.getSingleResult();
        return d;
    }
}
