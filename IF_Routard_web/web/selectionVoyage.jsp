<%-- 
    Document   : selectionVoyage
    Created on : 11 avr. 2014, 18:16:37
    Author     : Administdateur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/Style-pages.css">
        <title>Inscription</title>
    </head>
    <body>
        <div align="center">
        <form action="./ActionServlet?todo=selectionerVoyage" method="POST" >
            <table>
                <tr>
                  <th>Durée</th>
                  <th>Prix</th>
                  <th></th>
                  <th></th>
                  <th></th>
                  
                  
                  
                </tr>
                <tr>
                  <td>min<input type="number" min="1" max="100"  name="min" > </td>
                  <td>min<input type="number" name="PrixMin" ></td>
                  <td><input type="checkbox" name="type" value="Sejour">Sejour</td>
                  <td>Continent
                     <select>
                        <option value="indiférent">indiferent</option>
                        <option value="Afrique">Afrique</option>
                        <option value="Amerique">Amerique</option>
                        <option value="Europe">Europe</option>
            </select> </td>
                  <td><input type="submit" value="Rechercher" /> </td>
                  
                </tr>
               <tr>
                  <td> max<input type="number" min="2" max="200"  name="max" ></td>
                  <td> max<input type="number" name="max" ></td>
                  <td><input type="checkbox" name="type" value="Circuit">Circuit</br> </td>
                  <td>Pays
            <select>
            <option value="France">France</option>
            <option value="Roumanie">Roumanie</option>
            <option value="Luxembourg">Luxembourg</option>
            </select> 
                      
                      <td> <img src ="images/routard.jpg" width="80px" height="70px" align="left"/></td>
                  
           
                </tr>
                
              </table>
           </form>
            
            </div>
    </body>
</html>