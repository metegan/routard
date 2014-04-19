/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import metier.modele.Client;

/**
 *
 * @author Antoine
 */
public interface ClientDAO {

    /**
     * Persiste un client dans la base de données
     * @param c 
     */
    public void creerClient(Client c);

    /**
     * Modifie un client persisté dans la base de données
     * @param c 
     */
    public void modifierClient(Client c);

    /**
     * Supprime un client persisté dans la base de données
     * @param c 
     */
    public void supprimerClient(Client c);

    /**
     * Retourne tous les clients de la base de données
     * @return 
     */
    public List<Client> rechercherClient();

    /**
     * Retourne les clients dont le nom est nom
     * @param nom
     * @return 
     */
    public List<Client> rechercherClientParNom(String nom);

    /**
     * Retourne le client dont l'id est id
     * @param id
     * @return 
     */
    public Client rechercherClientParId(int id);

    /**
     * Retourne le client dont l'e-mail est mail
     * @param mail
     * @return 
     */
    public Client rechercherClientParMail(String mail);
    
    /**
     * Retourne true si un client existe avec ce mail et ce mot de passe dans la base.
     * False sinon.
     * @param mail Email du client
     * @param mdp Mot de passe du client
     * @return 
     */
    public boolean rechercherClientConnexion(String mail, String mdp);
}
