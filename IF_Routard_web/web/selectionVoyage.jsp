<%-- 
    Document   : selectionVoyage
    Created on : 11 avr. 2014, 18:16:37
    Author     : Administrateur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inscription</title>
    </head>
    <body>
        <form action="./ActionServlet?todo=selectionerVoyage" method="POST" >
            Durée </br>
            min
            <input type="number" min="1" max="100"  name="min" >
            max
            <input type="number" min="2" max="200"  name="max" >
            Prix </br>
            min
            <input type="number" name="min" >
            max
            <input type="number" name="max" >
            
            <input type="checkbox" name="vehicle" value="Bike">Sejour
            <input type="checkbox" name="vehicle" value="Car">Circuit</br>
            
            Continent
            <select>
            <option value="indiférent">indiferent</option>
            <option value="Afrique">Afrique</option>
            <option value="Amerique">Amerique</option>
            <option value="Europe">Europe</option>
            </select> 
            
            
            Pays
            <select>
            <option value="France">France</option>
            <option value="Roumanie">Roumanie</option>
            <option value="Luxembourg">Luxembourg</option>
            </select> 
             <input type="submit" value="Rechercher" /> </br>
                      
            
          </form>
    </body>
</html>