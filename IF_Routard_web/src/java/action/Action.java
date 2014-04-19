/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.service.Service;

/**
 *
 * @author Administrateur
 */
public abstract class Action {
    protected Service service;
    public void setServiceMetier(Service service)
    {
        this.service=service;
    }
    
    public abstract void execute (HttpServletRequest request, HttpServletResponse response  );
}
