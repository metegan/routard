package action;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.service.Service;

/**
 *
 * @author Administrateur
 */

public class LoginAction extends Action {
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public boolean execute (HttpServletRequest request)
    {
       
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        
        Boolean connecte;
        if (Service.connexionClient(mail,password)) {
            connecte = true;
            
        } else {
            connecte = false; //le client n'a pas pu se connecter
        }
        request.getSession().setAttribute("connecte", connecte);
        
        return true;   
        
    }
}
