/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import metier.modele.Voyage;
import metier.service.Service;


/**
 *
 * @author Maria-PC
 */
public class definirVoyage extends Action {
    
    /**
     *
     * @param request
     */
    @Override
    public boolean execute(HttpServletRequest request) {
        // [Récupérer les paramètres de request]
        // Déléguer l'éxécution de la tâche en déclenchement
        // une-des méthode(s) de l'instance de service métier  
        
        String code = (String) request.getAttribute("voyageId");
        
        Voyage voyage = Service.rechercherVoyageParCode(code);

        request.setAttribute("voyage", voyage );
        return true;
    }
}


