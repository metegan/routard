/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Amaury
 */
@Entity
public class Client implements Serializable {

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
    private Short spam;
    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST)
    private List<Devis> devis = new ArrayList();

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getNom() {
        return nom;
    }

    public boolean isSpam() {
        return (spam==1);
    }

    public void setSpam(boolean spam) {
        this.spam = (short)(spam?1:0);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(Date date) {
        this.dateNaiss = date;
    }

    public List<Devis> getDevis() {
        return devis;
    }

    public void setDevis(List<Devis> devis) {
        this.devis = devis;
    }

    public void addDevis(Devis devis) {
        this.devis.add(devis);
    }

    /**
     * Constructeur par défaut de client
     */
    public Client() {
    }

    public Client(String civilite, String nom, String prenom, Date dateNaiss, String adresse, String tel, String mail, String password, boolean spam) {
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.adresse = adresse;
        this.tel = tel;
        this.mail = mail;
        this.password = password;
        this.spam = (short)(spam?1:0);
    }

    public Client(String civilite, String nom, String prenom, Date dateNaiss, String adresse, String tel, String mail, boolean spam) {
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.adresse = adresse;
        this.tel = tel;
        this.mail = mail;
        this.password = "IF-ROUTARD";
        this.spam = (short)(spam?1:0);
    }

    @Override
    public String toString() {
        return nom + " " + prenom + "\n" + adresse + "\n" + tel;
    }
}
