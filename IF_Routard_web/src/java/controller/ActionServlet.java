/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import action.Action;
import action.InscriptionAction;
import action.LoginAction;
import action.SelectionAction;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.service.Service;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            String tache = request.getParameter("todo");
            System.out.println(tache);
            Action action = this.getAction(tache);
            if(action!=null) 
            {
                action.execute(request,response);
            }
            String vue = this.setVue(tache);
            request.getRequestDispatcher(vue).forward(request, response);
           
    }
       

    private Action getAction(String todo)
    {
        Action action = null;
       
       if( "validerInscription".equals(todo) )
       {
           action = new InscriptionAction();
       }
        if( "authentification".equals(todo))
        {
            action = new LoginAction();
        }
        if( "selectionerVoyage".equals(todo))
        {
            action = new SelectionAction();
        }
        return action;
    }
    private String setVue(String todo)
    {
        String vue = null;
        if( "inscription".equals(todo) ) {
            vue = "Inscription.jsp" ;
        }
        if( "login".equals(todo) ||"authentification".equals(todo) )
        {
            vue = "login.jsp";
        }
        if( "selectionerVoyage".equals(todo)||  "validerInscription".equals(todo))
        {
            vue = "selectionVoyage.jsp";
        }
        
        return vue;
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
