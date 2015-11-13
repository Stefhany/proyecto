<%-- 
    Document   : msg
    Created on : 10/11/2015, 12:24:11 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
       
        <!--
        En esta parte se pintan los mensajes dependiendo del tipo.
        El estilo se puede cambiar manteniendo la estructura de los tipos
        se valida la existencia de las variables y se muestra el mensaje.
        Esta será la salida que se incluye en la pagina en donde se vincula.
        para el ejemplo, en las páginas en index y newjsp
        -->
        
        <%
            if (request.getParameter("sal") != null && (request.getParameter("tipo") != null && request.getParameter("tipo").equals("0"))) {
        %>
        <div class="error"><%out.print(request.getParameter("sal"));%></div>
        <%
        } else if (request.getParameter("sal") != null && (request.getParameter("tipo") != null && request.getParameter("tipo").equals("2"))) {
        %>
        <div class="info"><%out.print(request.getParameter("sal"));%></div>
        <%
        } else if (request.getParameter("sal") != null && (request.getParameter("tipo") != null && request.getParameter("tipo").equals("1"))) {
        %>
        <div class="success"><%out.print(request.getParameter("sal"));%></div>
        <%
        } else {
        %>
        <div class="warning"><%out.print(request.getParameter("sal"));%></div>
        <%
            }
        %>

    </body>
</html>
