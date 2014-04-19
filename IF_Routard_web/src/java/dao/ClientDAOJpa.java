/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.Query;
import metier.modele.Client;

/**
 *
 * @author Antoine
 */
public class ClientDAOJpa implements ClientDAO {

    @Override
    public void creerClient(Client c) {
        JpaUtil.obtenirEntityManager().persist(c);
    }

    @Override
    public void modifierClient(Client c) {
        JpaUtil.obtenirEntityManager().merge(c);
    }

    @Override
    public void supprimerClient(Client c) {
        JpaUtil.obtenirEntityManager().remove(c);
    }

    @Override
    public List<Client> rechercherClient() {
        Query q = JpaUtil.obtenirEntityManager().createQuery("Select c from Client c");
        List<Client> c = (List<Client>) q.getResultList();
        return c;
    }

    @Override
    public List<Client> rechercherClientParNom(String nom) {
        Query q = JpaUtil.obtenirEntityManager().createQuery("Select c from Client c where c.nom= :nom")
                .setParameter("nom", nom);
        List<Client> c = (List<Client>) q.getResultList();
        return c;
    }

    @Override
    public Client rechercherClientParId(int id) {
        Query q = JpaUtil.obtenirEntityManager().createQuery("Select c from Client c where c.id= :id")
                .setParameter("id", id);
        Client c = (Client) q.getSingleResult();
        return c;
    }

    @Override
    public Client rechercherClientParMail(String mail) {
        Query q = JpaUtil.obtenirEntityManager().createQuery("Select c from Client c where c.mail= :mail")
                .setParameter("mail", mail);
        Client c = (Client) q.getSingleResult();
        return c;
    }

    @Override
    public boolean rechercherClientConnexion(String mail, String mdp) {
        try
        {
            Query q = JpaUtil.obtenirEntityManager().createQuery("Select c from Client c where c.mail= :mail and c.password = :mdp")
                .setParameter("mail", mail).setParameter("mdp", mdp);
            Client c = (Client) q.getSingleResult();
        }
        catch (Exception e)
        {
            return false;
        }
        
        return true;
    }
    
    
}
