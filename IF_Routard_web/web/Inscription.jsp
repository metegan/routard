<%-- 
    Document   : Inscription
    Created on : 4 avr. 2014, 17:56:16
    Author     : Maria ETEGAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="CSS/style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Inscription</title>
    </head>
    <body>
        <div align="center">
            
        <form action="./ActionServlet?todo=validerInscription" method="POST" >
            
            <h2>
            Civilité
            <input type="radio" name="civilite" value="M." >M.
            <input type="radio" name="civilite" value="Mme">Mme</br>
            </br>
            Nom
            <input type="text" name="nom"></br>
            </br>
            Prénom
            <input type="text" name="prenom"></br>
            </br>
            Adresse E-mail
            <input type="text" name="mail"></br>
            </br>
            Date de naissance
            <input type="text" name="dateNaiss" value="jj/mm/aaaa" ></br>
            </br>
            Numéro téléphone
            <input type="text" name="tel" ></br>
            </br>
            Adresse
            <input type="text" name="adresse" > </br>
            </br>
            Mot de passe 
            <input type="password" name="password" ></br>
            </br>
            Confirmation <input type="password" name="confirmation" ></br>
             <input type="submit" value="S'inscrire" /></br>
             
            </h2>
              <%
            java.lang.String enregistre = (java.lang.String) request.getAttribute("enregistre");
            if (enregistre == "ok") {
                out.println("<p class=\"connexion\" > Enregistrement OK </p>");
            }
            %>
        <%
        metier.modele.Client c = (metier.modele.Client)request.getSession().getAttribute("client");
        if(c!=null)
        {
            out.println(" - " + c.getPrenom() + " " + c.getNom() + " - <a id=\"logout\" href=\"./logout.jsp\"><font color=\"white\"><i>Déconnexion</i></font></a>");
        } 
    %>
          </form>
        </div>
    </body>
</html>
