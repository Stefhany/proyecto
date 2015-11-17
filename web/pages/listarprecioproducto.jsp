<%-- 
    Document   : listarprecioproducto
    Created on : 16-nov-2015, 19:02:19
    Author     : Stefhany Alfonso
--%>

<%@page import="java.util.LinkedList"%>
<%@page import="java.util.List"%>
<%@page import="dtos.ProductoDTO"%>
<%@page import="facade.FacadeProductos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%  
            FacadeProductos facadeCategoria = new FacadeProductos();
            List<ProductoDTO> listarPrecio = new LinkedList();
            
            //LinkedList<ProductoDTO> listaproductos = new LinkedList<ProductoDTO>();
            if (request.getParameter("id") != null) {                
                listarPrecio = facadeCategoria.consultarPrecio(Integer.parseInt(request.getParameter("id")));
                if (listarPrecio.size() > 0) {
                    for (ProductoDTO prodto : listarPrecio) {
                        out.write("<option value = " + prodto.getPrecioProducto() + "> " + prodto.getPrecioProducto() + "</option>");
                    }
                }
            }
        %>
    </body>
</html>
