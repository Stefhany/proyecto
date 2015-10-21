<%-- 
    Document   : listarproductoscategorias
    Created on : 24/01/2015, 07:38:43 PM
    Author     : krito
--%>

<%@page import="facade.FacadeCategorias"%>
<%@page import="java.util.LinkedList"%>
<%@page import="dtos.ProductoDTO"%>
<%@page import="daos.CategoriaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
    </head>
    <body>
        <%  
            FacadeCategorias facadeCategoria = new FacadeCategorias();
            LinkedList<ProductoDTO> listaproductos = new LinkedList<ProductoDTO>();
            if (request.getParameter("id") != null) {                
                listaproductos = facadeCategoria.listarSubCategorias(Integer.parseInt(request.getParameter("id")));
                if (listaproductos.size() > 0) {
                    for (ProductoDTO prodto : listaproductos) {
                        out.write("<option value = " + prodto.getIdProductos() + ">" + prodto.getNombre() + "</option>");
                    }
                }
            }
        %>
    </body>
</html>
