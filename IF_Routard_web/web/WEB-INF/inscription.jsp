<%-- 
    Document   : inscription
    Created on : 9 avr. 2014, 19:28:20
    Author     : hh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST" ACTION="<% out.print( request.getContextPath() ); %>/inscription?todo=validate_inscription">
            Civilité
            <input type="radio" name="civilite" value="M.">M
            <input type="radio" name="civilite" value="Mme">F</br>
            
            Nom
            <input type="text" name="nom"></br>
            
            Prénom
            <input type="text" name="prenom"></br>
            
            Adresse E-mail
            <input type="text" name="mail"></br>
            
            Date de naissance
            <input type="text" name="dateNaiss" value="jj/mm/aaaa" ></br>
            
            Numéro téléphone
            <input type="text" name="tel" ></br>
            
            Adresse
            <input type="text" name="adresse" ></br>
            Mot de passe <input type="password" name="password" ></br>
            
            Confirmation <input type="password" name="confirmation" ></br>
            <input type="submit" value="S'inscrire">
            </form>
    </body>
</html>
