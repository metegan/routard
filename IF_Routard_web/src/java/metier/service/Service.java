/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;

import dao.ClientDAO;
import dao.ClientDAOJpa;
import dao.ConseillerDAO;
import dao.ConseillerDAOJpa;
import dao.DevisDAO;
import dao.DevisDAOJpa;
import dao.JpaUtil;
import dao.PaysDAO;
import dao.PaysDAOJpa;
import dao.VolDAO;
import dao.VolDAOJpa;
import dao.VoyageDAO;
import dao.VoyageDAOJpa;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import metier.modele.Client;
import metier.modele.Conseiller;
import metier.modele.Devis;
import metier.modele.Pays;
import metier.modele.Vol;
import metier.modele.Voyage;

/**
 *
 * @author Antoine
 */
public class Service {

    private static ClientDAO clientDAO = new ClientDAOJpa();
    private static ConseillerDAO conseillerDAO = new ConseillerDAOJpa();
    private static DevisDAO devisDAO = new DevisDAOJpa();
    private static PaysDAO paysDAO = new PaysDAOJpa();
    private static VoyageDAO voyageDAO = new VoyageDAOJpa();
    private static VolDAO volDAO = new VolDAOJpa();

    /**
     *
     * Crée un devis dans la bdd à partir des attributs en parametre. Sert
     * uniquement pour rentrer les données csv
     *
     * @param nbPersonne =1 en général
     * @param client Client depuis le quel le service à été généré
     * @param codeVoy Code associé au client dans les données csv.
     */
    public static Devis creerDeviscsv(int nbPersonne, Client client, String codeVoy) {
        Voyage voyage = rechercherVoyageParCodecsv(codeVoy);
        //On attribue un conseiller disponible au client dans ce nouveau devis
        Conseiller conseiller = rechercherConseillerDispoParPayscsv(voyage.getPays());
        if (conseiller == null)//Si aucun conseiller n'est spécialiste de ce pays, on attribue le conseiller qui
        //travaille le moins à ce nouveau pays
        {
            conseiller = rechercherConseillerDispocsv();
        }
        //Selection du premier vol de la liste des vols du voyage associé (par defaut)
        Vol vol = rechercherUnVolParVoyagecsv(codeVoy);
        Devis d = new Devis(client, conseiller, voyage, vol, nbPersonne);
        client.addDevis(d);
        conseiller.addDevis(d);
        conseiller.ajouterClient();
        voyage.addDevis(d);
        modifierClientcsv(client);
        modifierConseillercsv(conseiller);
        modifierVoyagecsv(voyage);
//        JpaUtil.creerEntityManager();        
//        JpaUtil.ouvrirTransaction();
        devisDAO.creerDevis(d);
//        JpaUtil.validerTransaction();
//        JpaUtil.fermerEntityManager();
        return d;
    }

    /**
     *
     * Génére un devis pour un client comportant un vol et un voyage, et
     * attribue un conseiller au client
     *
     * @param nbPersonne renseigné par le client dans l'ihm Web
     * @param client Client ayant généré le devis depuis l'ihm Web
     * @param voyage Voyage concerné par la génération du devis
     * @param vol Vol sélectionné par le client lors de la génération du devis
     */
    public static Devis creerDevis(int nbPersonne, Client client, Voyage voyage, Vol vol) {
        //On attribue un conseiller au client dans ce nouveau devis
        Conseiller conseiller = rechercherConseillerDispoParPays(voyage.getPays());
        //Conseiller conseiller = ListeConseiller.get(util.Aleatoire.random(ListeConseiller.size()));
        //Ajout d pays du voyage dans la liste des pays du conseiller choisi
        conseiller.ajouterClient();
        modifierClientcsv(client);
        modifierConseillercsv(conseiller);
        modifierVoyagecsv(voyage);
        //Creation du devis
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        Devis d = new Devis(client, conseiller, voyage, vol, nbPersonne);
        devisDAO.creerDevis(d);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return d;
    }

    /**
     * Utilisée uniquement pour la génération des clients depuis les fichiers
     * csv Création d'un client puis génération d'un devis avec le codeVoyage et
     * choix du premier départ
     *
     * @param c : ligne du fichier csv
     * @param codeVoy : Réorgannisation des données : codeVoy n'est plus un
     * attribut de client mais génére un devis
     */
    public static void creerClientcsv(Client c, String codeVoy) {

        clientDAO.creerClient(c);
        Devis d = creerDeviscsv(1, c, codeVoy);
    }

    /**
     * Création d'un client après son eneregistrement sur l'ihm web
     *
     * @param c : Client
     */
    public static void creerClient(Client c) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        clientDAO.creerClient(c);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }

    /**
     * Modifie le client passé en paramètre dans la base de données
     *
     * @param c Client devant être modifié
     */
    public static void modifierClient(Client c) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        clientDAO.modifierClient(c);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }

    /**
     * Modifie le client passé en paramètre en utilisant l'entity manager actuel
     *
     * @param c Client devant être modifié
     */
    public static void modifierClientcsv(Client c) {
        clientDAO.modifierClient(c);
    }

    /**
     * Supprime le client passé en paramètre de la base de donnée
     *
     * @param c Client devant être supprimé
     */
    public static void supprimerClient(Client c) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        clientDAO.supprimerClient(c);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }

    /**
     * Crée un pays dans la base de données
     *
     * @param p Pays devant être créé
     */
    public static void creerPays(Pays p) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        paysDAO.creerPays(p);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }

    /**
     * Crée un pays dans la base de données en utilisant l'entity manager actuel
     *
     * @param p
     */
    public static void creerPayscsv(Pays p) {
        paysDAO.creerPays(p);
    }

    /**
     * Modifie un pays dans la base de données
     *
     * @param p
     */
    public static void modifierPays(Pays p) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        paysDAO.modifierPays(p);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }

    /**
     * Modifie un pays dans la base de données en utilisant l'entity manager
     * actuel
     *
     * @param p
     */
    public static void modifierPayscsv(Pays p) {
        paysDAO.modifierPays(p);
    }

    /**
     * Supprime un pays de la base de données
     *
     * @param p
     */
    public static void supprimerPays(Pays p) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        paysDAO.supprimerPays(p);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }

    /**
     * Création d'un conseiller depuis la base Csv
     * @param c
     * @param codePays 
     */
    public static void creerConseillercsv(Conseiller c, List<String> codePays) {

        Iterator itr = codePays.iterator();
        conseillerDAO.creerConseiller(c);
        while (itr.hasNext()) {
            Pays pays = rechercherPaysParCodePayscsv((String) itr.next());
            c.AjouterPays(pays);
            pays.ajouterConseiller(c);
            modifierPayscsv(pays);
        }
    }

    /**
     * Modifie le conseiller passé en paramètre dans la base de données
     * @param c 
     */
    public static void modifierConseiller(Conseiller c) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        conseillerDAO.modifierConseiller(c);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }

    /**
     * Modifie le conseiller passé en paramètre en utilisant l'entity manager actuel
     * @param c 
     */
    public static void modifierConseillercsv(Conseiller c) {
        conseillerDAO.modifierConseiller(c);
    }

    /**
     * Supprime le conseiller de la base de données
     * @param c 
     */
    public static void supprimerConseiller(Conseiller c) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        conseillerDAO.supprimerConseiller(c);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
    /**
     * Crée un vol et l'associe avec le voyage dont le code est égal à voyage
     * @param v Vol
     * @param voyage Code du voyage auquel le vol doit être associé
     */
    public static void creerVol(Vol v, String voyage) {

        Voyage voy = rechercherVoyageParCode(voyage);
        v.setVoyage(voy);
        modifierVoyage(voy);
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        volDAO.creerVol(v);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }

    /**
     * Crée un vol et l'associe avec le voyage dont le code est égal à voyage
     * en utilisant l'entity manager actuel
     * @param v Vol
     * @param voyage Code du voyage auquel le vol doit être associé
     */
    public static void creerVolcsv(Vol v, String voyage) {
        Voyage voy = rechercherVoyageParCodecsv(voyage);
        v.setVoyage(voy);
        modifierVoyagecsv(voy);
        volDAO.creerVol(v);
    }

    /**
     * Crée un vol dans la base de données
     * @param v 
     */
    /*public void creerVol(Vol v) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        volDAO.creerVol(v);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }*/

    /**
     * Modifie le vol passé en paramètre dans la base de données
     * @param v 
     */
    public static void modifierVol(Vol v) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        volDAO.modifierVol(v);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }

    /**
     * Supprime le vol de la base de données
     * @param v 
     */
    public static void supprimerVol(Vol v) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        volDAO.supprimerVol(v);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }

    /**
     * Crée un voyage et l'associe au pays dont le code est égal à codePays
     * @param v
     * @param codePays 
     */
    public static void creerVoyage(Voyage v, String codePays) {
        Pays pays = rechercherPaysParCodePays(codePays);
        pays.addVoyage(v);
        modifierPays(pays);
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        voyageDAO.creerVoyage(v);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }

    /**
     * Crée un voyage et l'associe au pays dont le code est égal à codePays
     * en utilisant l'entity manager actuel
     * @param v
     * @param codePays 
     */
    public static void creerVoyagecsv(Voyage v, String codePays) {
        Pays pays = rechercherPaysParCodePayscsv(codePays);
        pays.addVoyage(v);
        v.setPays(pays);
        modifierPayscsv(pays);
        
        //voyageDAO.creerVoyage(v);
    }
    
    
    public static boolean connexionClient(String email, String mdp)
    {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        boolean connexion = clientDAO.rechercherClientConnexion(email, mdp);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return connexion;
    }

    /**
     * Modifie la voyage dans la base de données
     * @param v 
     */
    public static void modifierVoyage(Voyage v) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        voyageDAO.modifierVoyage(v);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }

    /**
     * Modifie la voyage dans la base de données en utilisant l'entity manager actuel
     * @param v 
     */
    public static void modifierVoyagecsv(Voyage v) {
        voyageDAO.modifierVoyage(v);
    }

    /**
     * Supprime le voyage de la base de données
     * @param v 
     */
    public static void supprimerVoyage(Voyage v) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        voyageDAO.supprimerVoyage(v);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }

    //------------Services de recherche------------------------
    /**
     * Retourne tous les clients de la base de données
     * @return tous les clients de la base
     */
    public static List<Client> rechercherClient() {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        List<Client> client = clientDAO.rechercherClient();
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return client;
    }

    /**
     * Retourne tous les clients dont le nom = nom
     * @param nom
     * @return 
     */
    public static List<Client> rechercherClientParNom(String nom) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        List<Client> client = clientDAO.rechercherClientParNom(nom);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return client;
    }

    /**
     * Retourne le client dont l'id est id
     * @param id
     * @return 
     */
    public static Client rechercherClientParId(int id) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        Client client = clientDAO.rechercherClientParId(id);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return client;
    }

    /**
     * Retourne le client dont l' e-mail est mail
     * @param mail
     * @return 
     */
    public static Client rechercherClientParMail(String mail) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        Client client = clientDAO.rechercherClientParMail(mail);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return client;
    }

    /**
     * Retourne la liste des conseillers dont le nom est nom
     * @param nom
     * @return 
     */
    public static List<Conseiller> rechercherConseillerParNom(String nom) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        List<Conseiller> conseiller = conseillerDAO.rechercherConseillerParNom(nom);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return conseiller;
    }

    /**
     * Retourne le conseiller dont l'id est id
     * @param id
     * @return 
     */
    public static Conseiller rechercherConseillerParId(int id) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        Conseiller conseiller = conseillerDAO.rechercherConseillerParId(id);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return conseiller;
    }

    /**
     * Retourne le conseiller dont l'e-mail est mail
     * @param mail
     * @return 
     */
    public static Conseiller rechercherConseillerParMail(String mail) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        Conseiller conseiller = conseillerDAO.rechercherConseillerParMail(mail);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return conseiller;
    }

    /**
     * Retourne le conseiller disposant du moins de clients pour le pays passé en paramètre
     * @param p
     * @return 
     */
    public static Conseiller rechercherConseillerDispoParPays(Pays p) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        Conseiller conseiller = conseillerDAO.rechercherConseillerDispoParPays(p);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return conseiller;
    }

    /**
     * Retourne le conseiller disposant du moins de clients pour le pays passé en paramètre
     * en utilisant l'entity manager actuel
     * @param p
     * @return 
     */
    public static Conseiller rechercherConseillerDispoParPayscsv(Pays p) {
        Conseiller conseiller = conseillerDAO.rechercherConseillerDispoParPays(p);
        return conseiller;
    }

    /**
     * Retourne le conseiller disposant du moins de clients
     * @return 
     */
    public static Conseiller rechercherConseillerDispo() {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        Conseiller conseiller = conseillerDAO.rechercherConseillerDispo();
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return conseiller;
    }

    /**
     * Retourne le conseiller disposant du moins de clients en utilisant l'entity manager actuel
     * @return 
     */
    public static Conseiller rechercherConseillerDispocsv() {
        Conseiller conseiller = conseillerDAO.rechercherConseillerDispo();
        return conseiller;
    }

    /**
     * Retourne la liste des devis du client passé en paramètre
     * @param c
     * @return 
     */
    public static List<Devis> rechercherDevisParClient(Client c) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        List<Devis> devis = devisDAO.rechercherDevisParClient(c);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return devis;
    }

    /**
     * Retourne le pays dont l'id est id
     * @param id
     * @return 
     */
    public static Pays rechercherPaysParId(int id) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        Pays pays = paysDAO.rechercherPaysParId(id);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return pays;
    }

    /**
     * Retourne le pays dont le nom est nom
     * @param nom
     * @return 
     */
    public static Pays rechercherPaysParNom(String nom) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        Pays pays = paysDAO.rechercherPaysParNom(nom);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return pays;
    }

    /**
     * Retourne une liste de pays sur le continent passé en paramètre
     * @param continent
     * @return 
     */
    public static List<Pays> rechercherPaysParContinent(String continent) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        List<Pays> pays = paysDAO.rechercherPaysParContinent(continent);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return pays;
    }

    /**
     * Retourne tous les pays de la base
     * @return 
     */
    public static List<Pays> rechercherPays() {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        List<Pays> pays = paysDAO.rechercherPays();
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return pays;
    }

    /**
     * Retourne le pays dont le code est codePays
     * @param codePays
     * @return 
     */
    public static Pays rechercherPaysParCodePays(String codePays) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        Pays pays = paysDAO.rechercherPaysParCodePays(codePays);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return pays;
    }

    /**
     * Retourne le pays dont le code est codePays en utilisant l'entity manager actuel
     * @param codePays
     * @return 
     */
    public static Pays rechercherPaysParCodePayscsv(String codePays) {
        Pays pays = paysDAO.rechercherPaysParCodePays(codePays);
        return pays;
    }

    /**
     * Retourne tous les Vols de la base
     * @return 
     */
    public static List<Vol> rechercherVol() {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        List<Vol> vols = volDAO.rechercherVol();
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return vols;
    }

    /**
     * Retourne le vol dont l'id est id
     * @param id
     * @return 
     */
    public static Vol rechercherVolParId(int id) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        Vol vol = volDAO.rechercherVolParId(id);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return vol;
    }

    /**
     * Retourne les vols qui partent à la date passée en paramètre
     * @param date
     * @return 
     */
    public static List<Vol> rechercherVolParDate(Date date) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        List<Vol> vols = volDAO.rechercherVolParDate(date);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return vols;
    }

    /**
     * Retourne les vols qui partent de la ville indiquée en paramètre
     * @param ville
     * @return 
     */
    public static List<Vol> rechercherVolParVilleDep(String ville) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        List<Vol> vols = volDAO.rechercherVolParVilleDep(ville);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return vols;
    }

    /**
     * Retourne les vols dont le prix est compris entre min et max
     * @param min
     * @param max
     * @return 
     */
    public static List<Vol> rechercherVolParPrix(int min, int max) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        List<Vol> vols = volDAO.rechercherVolParPrix(min, max);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return vols;
    }

    //TODO
    /**
     * 
     * @param codeVoy
     * @return 
     */
    public static Vol rechercherUnVolParVoyage(String codeVoy) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        Vol vol = volDAO.rechercherUnVolParVoyage(codeVoy);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return vol;
    }

    public static Vol rechercherUnVolParVoyagecsv(String codeVoy) {
        Vol vol = volDAO.rechercherUnVolParVoyage(codeVoy);
        return vol;
    }

    /**
     * Retourne tous les voyages de la base
     * @return 
     */
    public static List<Voyage> rechercherVoyage() {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        List<Voyage> voyages = voyageDAO.rechercherVoyage();
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return voyages;
    }

    /**
     * Retourne le voyage dont le nom est nom
     * @param nom
     * @return 
     */
    public static Voyage rechercherVoyageParNom(String nom) {
        //TODO
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        Voyage voyage = voyageDAO.rechercherVoyageParNom(nom);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return voyage;
    }

    /**
     * Retourne le voyage dont l'id est id
     * @param id
     * @return 
     */
    public static Voyage rechercherVoyageParId(int id) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        Voyage voyage = voyageDAO.rechercherVoyageParId(id);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return voyage;
    }

    /**
     * Retourne le voyage dont le code est codeVoy
     * @param codeVoy
     * @return 
     */
    public static Voyage rechercherVoyageParCode(String codeVoy) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        Voyage voyage = voyageDAO.rechercherVoyageParCode(codeVoy);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return voyage;
    }

    /**
     * Retourne le voyage dont le code est codeVoy en utilisant l'entity manager actuel
     * @param codeVoy
     * @return 
     */
    public static Voyage rechercherVoyageParCodecsv(String codeVoy) {
        Voyage voyage = voyageDAO.rechercherVoyageParCode(codeVoy);
        return voyage;
    }

    /**
     * Retourne les voyages qui commencent à la date passée en paramètre
     * @param date
     * @return 
     */
    public static List<Voyage> rechercherVoyageParDate(Date date) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        List<Voyage> voyages = voyageDAO.rechercherVoyageParDate(date);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return voyages;
    }

    /**
     * Retourne les voyages dont la durée est comprise entre min et max
     * @param min
     * @param max
     * @return 
     */
    public static List<Voyage> rechercherVoyageParDuree(int min, int max) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        List<Voyage> voyages = voyageDAO.rechercherVoyageParDuree(min, max);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return voyages;
    }

    /**
     * Retourne les voyages qui se déroulent dans le pays passé en paramère
     * @param pays
     * @return 
     */
    public static List<Voyage> rechercherVoyageParPays(Pays pays) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        List<Voyage> voyages = voyageDAO.rechercherVoyageParPays(pays);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return voyages;
    }

    /**
     * Retourne le devis dont l'id est id
     * @param id
     * @return 
     */
    public static Devis rechercherDevisParId(int id) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        Devis d = devisDAO.rechercherDevisParId(id);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return d;
    }
    
    public static List<Vol> rechercherVolsParVoyage(String codeVoy)
    {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        List<Vol> v = volDAO.rechercherVolsParVoyage(codeVoy);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return v;
    }
}