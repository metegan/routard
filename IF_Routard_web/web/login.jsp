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
            <h2>Adresse E-mail <input type="text" name="mail"></br>
         Mot de passe <input type="password" name="password" ></br>
          <input type="submit" value="Soumettre" /> </br>
            </h2>
        </form>
        </div>
    </body>
</html>
