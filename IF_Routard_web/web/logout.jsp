<%-- 
    Document   : logout
    Created on : 24 avr. 2014, 02:06:32
    Author     : Maria-PC
--%>


<%@page import="metier.modele.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout</title>
    </head>
    <body>
    <%
        Client c = (Client)session.getAttribute("client");
        if(c != null){
            session.removeAttribute("client");
            session.invalidate();
            response.sendRedirect("./");
            }
     %>
        
</html>
