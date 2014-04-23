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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Client;
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
        
        Client c = new Client(civilite, nom,  prenom, dateNaiss, adresse, tel, mail, password, true);
       
        Service.creerClient(c);
        Boolean enregistre;
        if (dateNaiss != null && nom != null && !nom.equals("")) {
            enregistre = true;
            request.getSession().setAttribute("client", c);

        } else {
            enregistre = false; //le client n'a pas pu s'enregister
        }
        request.getSession().setAttribute("register", enregistre);
        
        return true;
       
    }
}
