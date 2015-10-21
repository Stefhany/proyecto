<%-- 
    Document   : logout
    Created on : 19-oct-2015, 17:44:18
    Author     : Mona
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cerrar Sesión - SIGAA</title>
    </head>
    <body>
        <h1>Salir</h1>
        
        <%         
            HttpSession miSesion = request.getSession(false);
            
            if (miSesion.getAttribute("usr") == null) {
                response.sendRedirect("login.jsp?msg= redireccion desde logout");

            } else {
                miSesion.removeAttribute("usr");
                miSesion.invalidate();
                response.sendRedirect("login.jsp?msg= Sesion cerrada");
            }
        %>  
    </body>
</html>