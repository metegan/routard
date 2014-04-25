package action;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Client;
import metier.modele.Pays;
import metier.modele.Voyage;
import metier.service.Service;

/**
 *
 * @author Administrateur
 */
//@WebServlet(name = "inscription", urlPatterns = {"/inscription"})
public class InscriptionAction extends Action {
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public boolean execute (HttpServletRequest request  )
    {
       List<Pays> pays = Service.rechercherPays();
       List <Voyage> voyages = Service.rechercherVoyage();
       request.setAttribute("voyages", voyages);
       if(!pays.isEmpty())request.setAttribute("pays", pays);
       else{
           Pays p=new Pays();
           request.setAttribute("pays", p);
       }
       
        String civilite = (String)request.getParameter("civilite");
        
        String nom = (String)request.getParameter("nom");
        
        String prenom = (String)request.getParameter("prenom");
        
        String mail =(String) request.getParameter("mail");
        
        String dateNaissance =(String) request.getParameter("dateNaiss");
        DateFormat CSV_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
        Date dateNaiss = new Date();
        try {
          dateNaiss = CSV_DATE_FORMAT.parse(dateNaissance);
        } catch (ParseException ex) {      
        }
        String tel = (String)request.getParameter("tel");    
        
        String adresse = (String) request.getParameter("adresse");   
        
        String password =  (String)request.getParameter("password");  
        
        String confirmation = (String)request.getParameter("confirmation");  
        String enregistre;
        if(confirmation.equals(password)) {
        
            Client c = new Client(civilite, nom,  prenom, dateNaiss, adresse, tel, mail, password, true);

            Service.creerClient(c); 


            if (dateNaiss != null && nom != null && !nom.equals("")) {
                enregistre = "ok";
                request.getSession().setAttribute("client", c);

            } else {
                enregistre = "fail"; //le client n'a pas pu s'enregister
            }
       } else {
            enregistre = "fail";
       }
        
        request.getSession().setAttribute("enregistre", enregistre);
        
        return true;
       
    }
}
