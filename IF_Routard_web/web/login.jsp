<%-- 
    Document   : login
    Created on : 11 avr. 2014, 14:56:59
    Author     : Administrateur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="CSS/style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        </br>
        </br>
        </br>
        </br>
        </br>
        </br>
        </br>
        </br>


        <div align="center">
        <form action="./ActionServlet?todo=authentification" method="POST" >
            <h2>Adresse E-mail <input type="text" name="mail" placeholder="Votre mail"></br>
         Mot de passe <input type="password" name="password" placeholder="Votre mot de passe"></br>
          <input type="submit" value="Soumettre" /> </br>
          <div class="text">Je ne suis pas inscrit -> <a class="inscription" href="./Inscription.jsp" > <i>S'inscrire </i></a></div>
            </h2>
        </form>
        </div>
    </body>
</html>
