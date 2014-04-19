/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Iterator;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import metier.modele.Conseiller;
import metier.modele.Pays;

/**
 *
 * @author Antoine
 */
public class ConseillerDAOJpa implements ConseillerDAO {

    @Override
    public void creerConseiller(Conseiller c) {
        JpaUtil.obtenirEntityManager().persist(c);
    }

    @Override
    public void modifierConseiller(Conseiller c) {
        JpaUtil.obtenirEntityManager().merge(c);
    }

    @Override
    public void supprimerConseiller(Conseiller c) {
        JpaUtil.obtenirEntityManager().remove(c);
    }

    @Override
    public List<Conseiller> rechercherConseillerParNom(String nom) {
        Query q = dao.JpaUtil.obtenirEntityManager().createQuery("Select c from Conseiller c where c.nom= :nom")
                .setParameter("nom", nom);
        List<Conseiller> c = (List<Conseiller>) q.getResultList();
        return c;
    }

    @Override
    public Conseiller rechercherConseillerParId(int id) {
        Query q = dao.JpaUtil.obtenirEntityManager().createQuery("Select c from Conseiller c where c.id= :id")
                .setParameter("id", id);
        Conseiller c = (Conseiller) q.getSingleResult();
        return c;
    }

    @Override
    public Conseiller rechercherConseillerParMail(String mail) {
        Query q = JpaUtil.obtenirEntityManager().createQuery("Select c from Conseiller c where c.mail= :mail")
                .setParameter("mail", mail);
        Conseiller c = (Conseiller) q.getSingleResult();
        return c;
    }

    @Override
    public Conseiller rechercherConseillerDispoParPays(Pays pays) {
        try {
            Query q = JpaUtil.obtenirEntityManager().createQuery("Select c "
                    + "FROM Conseiller c join c.pays p "
                    + "where p.id = :pays "
                    + "order by c.nbreClient ASC");
            q.setParameter("pays", pays.getId());
            q.setMaxResults(1);
            Conseiller conseiller = (Conseiller) q.getSingleResult();
            return conseiller;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Conseiller rechercherConseillerDispo() {
        try {
            Query query = JpaUtil.obtenirEntityManager().createQuery("SELECT c FROM Conseiller c "
                    + "ORDER BY c.nbreClient ASC");
            query.setMaxResults(1);
            Conseiller conseiller = (Conseiller) query.getSingleResult();
            return conseiller;
        } catch (NoResultException e) {
            return null;
        }
    }
}