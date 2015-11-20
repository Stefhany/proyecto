<%-- 
    Document   : newjsp
    Created on : 20-nov-2015, 19:14:08
    Author     : Stefhany Alfonso
--%>

<%@page import="javax.naming.Context"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%out.print(getServletContext().getContextPath()+"/");%>
    </body>
</html>
