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
    public void execute (HttpServletRequest request, HttpServletResponse response  )
    {
       
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        Service.connexionClient(mail,password);
            
        
    }
}
