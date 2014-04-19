package util;

import au.com.bytecode.opencsv.CSVReader;
import dao.JpaUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import metier.modele.Circuit;
import metier.modele.Client;
import metier.modele.Conseiller;
import metier.modele.Pays;
import metier.modele.Sejour;
import metier.modele.Vol;
import metier.modele.Voyage;
import metier.service.Service;

/**
 * La classe LectureDonneesCsv permet (comme son nom l'indique) la lecture de
 * données CSV dans des fichiers. Elle doit être complétée et personnalisée pour
 * interagir avec VOTRE couche service pour la création effective des entités.
 * Elle comprend initialement la lecture d'un fichier Clients et d'un fichier
 * Pays. Une méthode {@link main()} permet de tester cette classe avant de
 * l'intégrer dans le reste de votre code.
 *
 * @author Équipe DASI - 2013/2014
 */
public class LectureDonneesCsv {

    Service service = new Service();
    /**
     * Format de date pour la lecture des dates dans les fichiers CSV fournis.
     */
    protected static DateFormat CSV_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * Format de date pour l'affichage à l'écran.
     */
    protected static DateFormat USER_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    /**
     * Le lecteur de fichier CSV. Il doit être initialisé avant l'appel aux
     * méthodes de la classe.
     */
    protected CSVReader lecteurFichier;

    /**
     * Unique constructeur de la classe. Le fichier CSV donné en paramètre doit
     * avoir le point-virgule ';' comme séparateur et être encodé en UTF-8. Le
     * fichier est immédiatement ouvert (en lecture) par ce constructeur.
     *
     * @param cheminVersFichier Chemin vers le fichier CSV.
     * @throws FileNotFoundException Si le chemin vers le fichier n'est pas
     * valide ou le fichier non-lisible.
     */
    public LectureDonneesCsv(String cheminVersFichier) throws FileNotFoundException, UnsupportedEncodingException {

        this.lecteurFichier = new CSVReader(new InputStreamReader(new FileInputStream(cheminVersFichier), "UTF-8"), ';');
    }

    /**
     * Ferme le fichier CSV. Les autres méthodes ne doivent plus être appelées
     * après cela.
     *
     * @throws IOException
     */
    public void fermer() throws IOException {

        this.lecteurFichier.close();
    }

    /**
     * Méthode statique pour lire une date à partir d'une chaîne de caractère.
     * Adapté au format de date des fichiers CSV fournis, par exemple:
     * 2014-02-01.
     *
     * @param date Chaîne de caractère représentant la date.
     * @return La date interpétée ou la date actuelle en cas mauvais format en
     * entrée.
     */
    protected static Date parseDate(String date) {
        try {
            return CSV_DATE_FORMAT.parse(date);
        } catch (ParseException ex) {
            return new Date();
        }
    }

    /**
     * Méthode statique pour formater une date pour l'affichage. Par exemple:
     * 01/02/2014.
     *
     * @param date Date à formater.
     * @return Chaîne de caractère représentant la date.
     */
    protected static String formatDate(Date date) {
        return USER_DATE_FORMAT.format(date);
    }

    /**
     * Méthode statique pour afficher l'en-tête d'un fichier CSV lu par le
     * lecteur. L'affichage se fait sur la "sortie d'erreur" (en rouge dans la
     * console sous Netbeans). Le nom des colonnes est précédé de leur index
     * dans le tableau (commençant à 0).
     *
     * @param colonnes le tableau des noms de colonnes.
     */
    protected static void afficherEnTeteCsv(String[] colonnes) {

        for (int i = 0; i < colonnes.length; i++) {
            if (i > 0) {
                System.err.print("; ");
            }
            System.err.print("#" + Integer.toString(i) + " => " + colonnes[i]);
        }
        System.err.println();
    }

    /**
     * Lit le fichier CSV, affiche son en-tête, puis appelle la création de
     * Client pour chaque ligne.
     *
     * @param limite Nombre maximum de lignes à lire ou -1 pour ne pas limiter
     * @throws IOException
     */
    public void lireClients(int limite) throws IOException {

        String[] nextLine;

        // En-tete du fichier CSV
        nextLine = this.lecteurFichier.readNext();
        afficherEnTeteCsv(nextLine);


        // Lecture des lignes
        while ((nextLine = this.lecteurFichier.readNext()) != null) {

            creerClient(nextLine);

            // Limite (ou -1 si pas de limite)
            if (!(limite < 0) && (--limite < 1)) {
                break;
            }
        }

    }

    /**
     * Créée un Client à partir de sa description. La date de naissance est
     * notamment interpétée comme un objet Date.
     *
     * @param descriptionClient Ligne du fichier CSV de Clients.
     */
    public void creerClient(String[] descriptionClient) {

        /*String civilite = descriptionClient[0];
         String nom = descriptionClient[1];
         String prenom = descriptionClient[2];
         Date dateNaissance = parseDate(descriptionClient[3]);
         String adresse = descriptionClient[4];
         String telephone = descriptionClient[5];
         String email = descriptionClient[6];
         String codeVoy = descriptionClient[7];*/

//        System.out.println("CodeVoyCLient : " + codeVoy +" Client: "+  civilite + " " + nom + " " + prenom + ", né le " + formatDate(dateNaissance) + ", habitant à " + adresse + ", téléphone: " + telephone + ", e-mail: " + email);

        Client client = new Client(descriptionClient[0], descriptionClient[1], descriptionClient[2], parseDate(descriptionClient[3]), descriptionClient[4], descriptionClient[5], descriptionClient[6], false);
        Service.creerClientcsv(client, descriptionClient[7]);

    }

    /**
     * Créée un Pays à partir de sa description. La superficie et la population
     * sont notamment interpétées comme des nombres.
     *
     * @param descriptionPays Ligne du fichier CSV de Pays.
     */
    public void creerPays(String[] descriptionPays) {

//        String nom = descriptionPays[0];
//        String code = descriptionPays[1];
//        String region = descriptionPays[2];
//        String capitale = descriptionPays[3];
//        String langues = descriptionPays[4];
//        float superficie = Float.parseFloat(descriptionPays[5]);
//        float population = Float.parseFloat(descriptionPays[6]);
//        String regime = descriptionPays[7];

        Pays pays = new Pays(descriptionPays[0], descriptionPays[1], descriptionPays[2], descriptionPays[3], descriptionPays[4], Float.parseFloat(descriptionPays[5]), Float.parseFloat(descriptionPays[6]), descriptionPays[7]);

//        System.out.println("Pays: "+  nom + " [" + code + "] (" + regime + "), Capitale: " + capitale + ", Région: " + region + ", Langues: " + langues + ", " + superficie + " km², " + population + " millions d'hbitants");

        Service.creerPayscsv(pays);

    }

    /**
     * Lit le fichier CSV, affiche son en-tête, puis appelle la création de Pays
     * pour chaque ligne.
     *
     * @param limite Nombre maximum de lignes à lire ou -1 pour ne pas limiter
     * @throws IOException
     */
    public void lirePays(int limite) throws IOException {

        String[] nextLine;

        // En-tete du fichier CSV
        nextLine = this.lecteurFichier.readNext();
        afficherEnTeteCsv(nextLine);


        // Lecture des lignes
        while ((nextLine = this.lecteurFichier.readNext()) != null) {

            creerPays(nextLine);

            // Limite (ou -1 si pas de limite)
            if (!(limite < 0) && (--limite < 1)) {
                break;
            }
        }

    }

    /**
     * Lit le fichier CSV, affiche son en-tête, puis appelle la création de
     * Conseiller pour chaque ligne.
     *
     * @param limite Nombre maximum de lignes à lire ou -1 pour ne pas limiter
     * @throws IOException
     */
    public void lireVol(int limite) throws IOException {

        String[] nextLine;

        // En-tete du fichier CSV
        nextLine = this.lecteurFichier.readNext();
        afficherEnTeteCsv(nextLine);


        // Lecture des lignes
        while ((nextLine = this.lecteurFichier.readNext()) != null) {

            creerVol(nextLine);

            // Limite (ou -1 si pas de limite)
            if (!(limite < 0) && (--limite < 1)) {
                break;
            }
        }

    }

    /**
     * Créée un Vol à partir de sa description. La superficie et la population
     * sont notamment interpétées comme des nombres.
     *
     * @param descriptionVol Ligne du fichier CSV de Pays.
     */
    public void creerVol(String[] descriptionVol) {

//        String codeVoy = descriptionVol[0];
//        Date dateDep = parseDate(descriptionVol[1]);
//        String villeDep = descriptionVol[2];
//        int prix = Integer.parseInt(descriptionVol[3]);
//        String transport = descriptionVol[4];

//        System.out.println("Vol: "+ codeVoy +  " Tarif :" + prix + " Ville depart : " + villeDep + " transport :  " + transport );
        Vol vol = new Vol(parseDate(descriptionVol[1]), Integer.parseInt(descriptionVol[3]), descriptionVol[2], descriptionVol[4]);
        Service.creerVolcsv(vol, descriptionVol[0]);

    }

    /**
     * Lit le fichier CSV, affiche son en-tête, puis appelle la création de
     * Conseiller pour chaque ligne.
     *
     * @param limite Nombre maximum de lignes à lire ou -1 pour ne pas limiter
     * @throws IOException
     */
    public void lireConseiller(int limite) throws IOException {

        String[] nextLine;

        // En-tete du fichier CSV
        nextLine = this.lecteurFichier.readNext();
        afficherEnTeteCsv(nextLine);


        // Lecture des lignes
        while ((nextLine = this.lecteurFichier.readNext()) != null) {

            creerConseiller(nextLine);

            // Limite (ou -1 si pas de limite)
            if (!(limite < 0) && (--limite < 1)) {
                break;
            }
        }

    }

    /**
     * Créée un Conseiller à partir de sa description. La superficie et la
     * population sont notamment interpétées comme des nombres.
     *
     * @param descriptionConseiller Ligne du fichier CSV de Pays.
     */
    public void creerConseiller(String[] descriptionConseiller) {

//        String civilite = descriptionConseiller[0];
//        String nom = descriptionConseiller[1];
//        String prenom = descriptionConseiller[2];
//        Date dateNaissance = parseDate(descriptionConseiller[3]);
//        String adresse = descriptionConseiller[4];
//        String telephone = descriptionConseiller[5];
//        String email = descriptionConseiller[6];
        List<String> pays = new ArrayList<String>();
        for (int i = 7; i < descriptionConseiller.length; i++) {
            pays.add(descriptionConseiller[i]);
        }

//        System.out.println("Conseiller: "+  civilite + " " + nom + " " + prenom + ", né le " + formatDate(dateNaissance) + ", habitant à " + adresse + ", téléphone: " + telephone + ", e-mail: " + email);

        Conseiller conseiller = new Conseiller(descriptionConseiller[0], descriptionConseiller[1], descriptionConseiller[2], parseDate(descriptionConseiller[3]), descriptionConseiller[4], descriptionConseiller[5], descriptionConseiller[6]);
        Service.creerConseillercsv(conseiller, pays);

    }

    /**
     * Lit le fichier CSV, affiche son en-tête, puis appelle la création de
     * Sejours pour chaque ligne.
     *
     * @param limite Nombre maximum de lignes à lire ou -1 pour ne pas limiter
     * @throws IOException
     */
    public void lireSejours(int limite) throws IOException {

        String[] nextLine;

        // En-tete du fichier CSV
        nextLine = this.lecteurFichier.readNext();
        afficherEnTeteCsv(nextLine);


        // Lecture des lignes
        while ((nextLine = this.lecteurFichier.readNext()) != null) {

            creerSejour(nextLine);

            // Limite (ou -1 si pas de limite)
            if (!(limite < 0) && (--limite < 1)) {
                break;
            }
        }

    }

    /**
     * Créée un Conseiller à partir de sa description. La superficie et la
     * population sont notamment interpétées comme des nombres.
     *
     * @param descriptionSejour Ligne du fichier CSV de Sejours.
     */
    public void creerSejour(String[] descriptionSejour) {

//        String codePays = descriptionSejour[0];
//        String codeVoy = descriptionSejour[1];
//        String nom = descriptionSejour[2];
//        int duree = Integer.parseInt(descriptionSejour[3]);
//        String description = descriptionSejour[4];
//        String residence = descriptionSejour[5];


//        System.out.println("Sejour: "+  nom + " Code : " + codeVoy + "  Description : " + description +  " Duree : " + duree + "Residence : " + residence );

        Pays pays = Service.rechercherPaysParCodePayscsv(descriptionSejour[0]);
        Voyage sejour = new Sejour(pays, descriptionSejour[1], descriptionSejour[2], descriptionSejour[4], Integer.parseInt(descriptionSejour[3]), descriptionSejour[5]);
        Service.creerVoyagecsv(sejour, descriptionSejour[0]);

    }

    /**
     * Lit le fichier CSV, affiche son en-tête, puis appelle la création de
     * Sejours pour chaque ligne.
     *
     * @param limite Nombre maximum de lignes à lire ou -1 pour ne pas limiter
     * @throws IOException
     */
    public void lireCircuit(int limite) throws IOException {

        String[] nextLine;

        // En-tete du fichier CSV
        nextLine = this.lecteurFichier.readNext();
        afficherEnTeteCsv(nextLine);


        // Lecture des lignes
        while ((nextLine = this.lecteurFichier.readNext()) != null) {

            creerCircuit(nextLine);

            // Limite (ou -1 si pas de limite)
            if (!(limite < 0) && (--limite < 1)) {
                break;
            }
        }

    }

    /**
     * Créée un Circuit à partir de sa description. La superficie et la
     * population sont notamment interpétées comme des nombres.
     *
     * @param descriptionCircuit Ligne du fichier CSV de Sejours.
     */
    public void creerCircuit(String[] descriptionCircuit) {

//        System.out.println("Circuit: "+  nom + " Code : " + codeVoy + "  Description : " + description +  " Duree : " + duree + " nbkm : " + nbkm + " Transport : " + transport  );
        Pays pays = (Pays) Service.rechercherPaysParCodePayscsv(descriptionCircuit[0]);
        Circuit circuit = new Circuit(pays, descriptionCircuit[1], descriptionCircuit[2], descriptionCircuit[4], Integer.parseInt(descriptionCircuit[3]), Integer.parseInt(descriptionCircuit[6]), descriptionCircuit[5]);
        Service.creerVoyagecsv(circuit, descriptionCircuit[0]);

    }

    /**
     * Cette méthode main() permet de tester cette classe avant de l'intégrer
     * dans votre code. Elle exploite initialement un fichier de Client et un
     * fichier de Pays, en limitant la lecture aux 10 premiers éléments de
     * chaque fichier.
     *
     * @param args non utilisé ici
     */
    public static void main(String[] args) {
        try {

            String fichierClients = "data/IFRoutard-Clients.csv";
            String fichierPays = "data/IFRoutard-Pays.csv";
            String fichierConseillers = "data/IFRoutard-Conseillers.csv";
            String fichierDeparts = "data/IFRoutard-Departs.csv";
            String fichierCircuits = "data/IFRoutard-Voyages-Circuits.csv";
            String fichierSejours = "data/IFRoutard-Voyages-Sejours.csv";

            LectureDonneesCsv lectureDonneesCsv_Clients = new LectureDonneesCsv(fichierClients);
            LectureDonneesCsv lectureDonneesCsv_Pays = new LectureDonneesCsv(fichierPays);
            LectureDonneesCsv lectureDonneesCsv_Conseillers = new LectureDonneesCsv(fichierConseillers);
            LectureDonneesCsv lectureDonneesCsv_Departs = new LectureDonneesCsv(fichierDeparts);
            LectureDonneesCsv lectureDonneesCsv_Circuits = new LectureDonneesCsv(fichierCircuits);
            LectureDonneesCsv lectureDonneesCsv_Sejours = new LectureDonneesCsv(fichierSejours);

            //Pour tester: limite à 10
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            lectureDonneesCsv_Pays.lirePays(-1);
            lectureDonneesCsv_Circuits.lireCircuit(-1);
            lectureDonneesCsv_Sejours.lireSejours(-1);
            lectureDonneesCsv_Departs.lireVol(-1);
            lectureDonneesCsv_Conseillers.lireConseiller(-1);
            lectureDonneesCsv_Clients.lireClients(100);
            JpaUtil.validerTransaction();
            JpaUtil.fermerEntityManager();
            // Puis, quand tout est au point!
            //lectureDonneesCsv_Clients.lireClients(-1);



            lectureDonneesCsv_Clients.fermer();
            lectureDonneesCsv_Pays.fermer();
            lectureDonneesCsv_Departs.fermer();
            lectureDonneesCsv_Circuits.fermer();
            lectureDonneesCsv_Sejours.fermer();
            lectureDonneesCsv_Conseillers.fermer();

        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }

    }
}
