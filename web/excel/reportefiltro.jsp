<%-- 
    Document   : reportefiltro
    Created on : 17-nov-2015, 21:05:18
    Author     : Stefhany Alfonso
--%>

<%@page import="java.sql.Date"%>
<%@page import="facade.FacadeConsultas"%>
<%@page import="dtos.OfertasDTO"%>
<%@page import="java.util.LinkedList"%>
<%@page import="daos.Consultas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="application/vnd.ms-excel; charset=UTF-8">
        <title>SIGAA | Reporte Ofertas</title>
        <%
            FacadeConsultas consulta = new FacadeConsultas();
            String filtro = request.getParameter("filtro");
            LinkedList<OfertasDTO> filtrar = new LinkedList();
            filtrar = (LinkedList<OfertasDTO>) consulta.consultarFiltro(filtro);
        %>
    </head>
    <body>
        <table border="1">
            <thead>
                <tr style="background-color: #FE9A2E; color: #FFF; border: #BC8D4B">
                    <th>Tipo de producto</th>
                    <th>Nombre productor</th>
                    <th>Nombre producto</th>
                    <th>Cantidad</th>
                    <th>Unidad</th>
                    <th>Precio</th>
                </tr>
            </thead>
            <tbody>
                <%
                    String reporteOfertas = "Ofertas de Sigaa.xls";
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + reporteOfertas + "\"");
                    <%    if (filtrar.size() != 0) {
                        for (int i = 0; i < filtrar.size(); i++) {%>
                <tr>
                    <td> <%filtrar.get(i).getIdAso().getUsuario().getNombres();%></td>
                    <td><%filtrar.get(i).getIdAso().getProducto().getNombre();%></td>
                    <td><%filtrar.get(i).getFechaFin();%></td>
                </tr>
                }
                } else {
                out.println("<tr>");
                    out.println("<td>No se encontraron registros.</td>");
                    out.println("<td>No se encontraron registros.</td>");
                    out.println("<td>No se encontraron registros.</td>");
                    out.println("</tr>");
                }   
            </tbody>
        </table> 
    </body>
</html>