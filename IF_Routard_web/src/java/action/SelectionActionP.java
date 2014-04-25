package action;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Pays;
import metier.modele.Voyage;
import metier.service.Service;


/**
 *
 * @author Administrateur
 */
//@WebServlet(name = "inscription", urlPatterns = {"/inscription"})
public class SelectionActionP extends Action {
    /**
     *
     * @param request
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public boolean execute (HttpServletRequest request)
    {
        String nomPays = request.getParameter("pays");
        Pays pays = Service.rechercherPaysParNom(nomPays);
        List <Voyage> voyages = Service.rechercherVoyageParPays(pays);
        request.setAttribute("voyages", voyages);
        request.setAttribute("typeRech", "pays");
        return true;

    }
}