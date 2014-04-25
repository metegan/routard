<%-- 
    Document   : selectionVoyage
    Created on : 11 avr. 2014, 18:16:37
    Author     : Administdateur
--%>

<%@page import="metier.modele.Voyage"%>
<%@page import="metier.modele.Pays"%>
<%@page import="java.util.List" %>
<%@page import="java.lang.String" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="metier.modele.Client" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/Style-pages.css">
        <title>Inscription</title>
    </head>
    <body>
        <%! public String choix="pasDeChoix"; %>
            <div align="left"><a class="text" href="selectionVoyage.jsp"><img  src="images/home.png" height="30px" width="30px">Home</a></div>
            <div align="right">
        <%
        Client c = (Client)request.getSession().getAttribute("client");
        if(c!=null)
        {
            out.println( c.getPrenom() + " " + c.getNom() + " - <a class=\"text\" href=\"./logout.jsp\"><i>Déconnexion</i></a>");
        } 
        if(c==null)
        {
            out.println("<div class=\"text\">Vous n'êtes pas connecté <a href=\"./login.jsp\"><i>Login</i></a></div>");
        }
        %> </div>
        
        
        
        <div align="center">
        <form class="gris" action="./ActionServlet?todo=selectionerVoyageT" method="POST" >
            <table>
                <tr>
                  
                  <th>Durée</th>
                  <th>Prix</th>
                  <th></th>
                  <th></th>
                  <th></th>
                  <th></th>
                  
                  
                  
                </tr>
                <tr>
                  <td align="center">  min    <select  name="min" > 
                       <% for(int i = 1; i<=20; i++)
                           out.println("<option value="+i+">"+i+"</option>");
                              
                       %></select>
                  </td>
                  <td align="center">min<input type="number" name="PrixMin" ></td>
                  <td align="center"><input type="checkbox" name="type" value="Sejour">Sejour</td>
                  <td align="center">Continent
                     <select name="continent">
                        <option value="indiférent">indiferent</option>
                        <option value="Afrique">Afrique</option>
                        <option value="Amerique">Amerique</option>
                        <option value="Europe">Europe</option>
            </select> 
                     
                  </td>
                  <td align="center"><input type="submit" value="Rechercher toutes les voyages" /></td>
                 
                  
                </tr>
               <tr>
                  
                  <td align="center"> max   <select  name="max" > 
                         <% for(int i = 3; i<=40; i++)
                           out.println("<option value="+i+">"+i+"</option>");
                              
                       %></select></td>
                  <td align="center"> max<input type="number" name="PrixMax" ></td>
                  <td align="center"><input type="checkbox" name="type" value="Circuit">Circuit</br> </td>
                  <td align="center">Pays
            <select name="pays">
             <%
            List<Pays> listPays = (List<Pays>) request.getAttribute("pays");
            if(!listPays.isEmpty()){
                for (Pays p : listPays)
                out.println("<option value=\""+p.getCodePays()+"\">"+p.getNom()+"</option> \n");
            }
            else out.println("<option value=\"none\" >aucune pays</option>");
            %>
            <option value="none">aucune pays</option>
            </select> 
                      
                      <td align="center"> <img src ="images/routard.jpg" width="120px" height="70px" align="left"/></td>
                  
           
                </tr>
                <tr>
                    <td><a class="choix" href="?todo=selectionerVoyageP">Rechercher voyages par prix</td>
                    <td></td>
                    <td></td>
                    <td><a class="choix" href="?todo=selectionerVoyageD">Rechercher voyages par duree</td>
                    <td></td>
                </tr>
                
              </table>
           </form>  
            </div>
            
            </br>
            <div class="gris">
            <h1 align="center">Nom: Sejour</h1></br>
            <div align="left">
                <pre class="voyage">    
                Residence:   Hotel Ibis    Pays: France    Type: Sejour
                Description:
                 <span class="description"> dsavdbsafchfsdfsdfdusfbdsadsadasddsajksdn </span> </pre>
           
                <% String typeRecherche = (String) request.getAttribute("typeRech"); 
                    out.println(typeRecherche);%>
           <%         
            List<Voyage> voyages = (List<Voyage>) request.getAttribute("voyages");
                       
            if(voyages != null){
                for (Voyage v : voyages){
           
                    out.println("<a class=\"ref\" href=\"?todo=voyage&id=" 
                            + v.getId()+"\">" +
                            v.getNom() + "</a><br/>" + 
                            "<p class='voyages'>   -> *Pays : </p> <p>" 
                            + (v.getPays()).getNom() + "</p> <p class='voyages'> -> *Jours : </p> <p>" 
                            + v.getDuree() + "</p> <br/><p class='voyages'>   -> *Description : </p> <p>" 
                            + v.getDescription() + "</p> <br/><br/>");
                }
                
                request.removeAttribute("voyages");
                
            }       %>
            </div></div>            
        
    </body>
</html>