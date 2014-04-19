/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Antoine
 */
@Entity
public class Conseiller implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String civilite;
    private String nom;
    private String prenom;
    @Temporal(TemporalType.DATE) //DATE,TIME OU TIMESTAMP
    private Date dateNaiss;
    private String adresse;
    private String tel;
    private String mail;
    private String password;
    private int nbreClient;
    @ManyToMany(mappedBy = "conseillers")
    private List<Pays> pays;
    @OneToMany(mappedBy = "conseiller")
    private List<Devis> devis;

    public Conseiller() {
    }

    public Conseiller(String civilite, String nom, String prenom, Date dateNaiss, String adresse, String tel, String mail, String password, int nbreClient, List<Pays> pays) {
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.adresse = adresse;
        this.tel = tel;
        this.mail = mail;
        this.password = password;
        this.nbreClient = nbreClient;
        this.pays = pays;
        this.devis = new ArrayList();
    }

    public Conseiller(String civilite, String nom, String prenom, Date dateNaiss, String adresse, String tel, String mail, List<Pays> pays) {
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.adresse = adresse;
        this.tel = tel;
        this.mail = mail;
        this.password = "IF-ROUTARD";
        this.nbreClient = 0;
        this.pays = pays;
        this.devis = new ArrayList();
    }

    public Conseiller(String civilite, String nom, String prenom, Date dateNaiss, String adresse, String tel, String mail) {
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.adresse = adresse;
        this.tel = tel;
        this.mail = mail;
        this.nbreClient = 0;
        this.pays = new ArrayList();
        this.devis = new ArrayList();
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void AjouterPays(Pays pays) {
        this.pays.add(pays);
    }

    public void ajouterClient() {
        nbreClient++;
    }

    public void enleverClient() {
        nbreClient--;
    }

    public int getNbreClient() {
        return nbreClient;
    }

    public void setNbreClient(int nbreClient) {
        this.nbreClient = nbreClient;
    }

    public List<Pays> getPays() {
        return pays;
    }

    public void setPays(List<Pays> pays) {
        this.pays = pays;
    }

    public List<Devis> getDevis() {
        return devis;
    }

    public void setDevis(List<Devis> devis) {
        this.devis = devis;
    }

    public void addDevis(Devis d) {
        this.devis.add(d);
    }

    @Override
    public String toString() {
        return prenom + " " + nom + " " + "(" + mail + ")";
    }
}
