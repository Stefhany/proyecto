<%-- 
    Document   : actualizarprecioproducto
    Created on : 20-nov-2015, 9:28:23
    Author     : Stefhany Alfonso
--%>

<%@page import="facade.FacadeConsultas"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="facade.FacadeCategorias"%>
<%@page import="dtos.CategoriaDTO"%>
<%@page import="dtos.RolesUsuariosDTO"%>
<%@page import="java.util.LinkedList"%>
<%@page import="facade.FacadeRolesUsuarios"%>
<%@page import="dtos.ProductoDTO"%>
<%@page import="facade.FacadeProductos"%>
<%@page import="dtos.UsuariosDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="../error404.jsp"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Actualizar Producto - SIGAA</title>
        <!-- Bootstrap Core CSS -->
        <link href="../bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Estilos para mensajes -->
        <link href="../css/estilos.css" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href="../bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

        <!-- Timeline CSS -->
        <link href="../dist/css/timeline.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="../dist/css/sb-admin-2.css" rel="stylesheet">

        <!-- Morris Charts CSS -->
        <link href="../bower_components/morrisjs/morris.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="../bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

        <!-- Favicon de SIGAA -->
        <link rel="icon" href="../img/portfolio/favicon.ico" type="image/x-ico"/>
        <%
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Cache-Control", "no-store");
            response.setDateHeader("Expires", 0);
        %>
        <%
            HttpSession miSesion = request.getSession(false);
            if (miSesion.getAttribute("usr") != null) {
                UsuariosDTO uregistrado = (UsuariosDTO) miSesion.getAttribute("usr");
                String menu = (String) miSesion.getAttribute("mp");
                if (request.getParameter("idProduct") != null) {

                    int id = Integer.parseInt(request.getParameter("idProduct"));
                    FacadeProductos facadeProducto = new FacadeProductos();
                    ProductoDTO productoDto = new ProductoDTO();

                    productoDto = facadeProducto.consultarByIdProduct(id);

                    if (productoDto != null) {
        %>
    </head>

    <body>

        <div id="wrapper">

            <!-- Navegación -->
            <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand page-scroll" href="profile.jsp" style="color:#16700C;"><i>SIGAA</i></a>
                </div>
                <!-- /.navbar-header -->

                <ul class="nav navbar-top-links navbar-right">
                    <!-- /.dropdown -->
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#" style="color:#16700C;">
                            <p>
                                <%
                                    int idUsuario = uregistrado.getIdUsuarios();
                                    FacadeRolesUsuarios facadeRoles = new FacadeRolesUsuarios();
                                    LinkedList<RolesUsuariosDTO> u = new LinkedList();
                                    u = facadeRoles.mostrarRol(idUsuario);
                                    for (RolesUsuariosDTO roles : u) {
                                        out.print(roles.getRolId().getNombre());
                                    }
                                %> 
                                <i class="fa fa-user fa-fw" ></i> <i class="fa fa-caret-down"></i></p>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="modificarusuario.jsp"><i class="fa fa-user fa-fw"></i> Datos Personales</a>
                            </li>
                            <li><a href="cambiarclave.jsp"><i class="fa fa-gear fa-fw"></i> Cambiar contraseña</a>
                            </li>
                            <li class="divider"></li>
                            <li><a href="logout.jsp"><i class="fa fa-sign-out fa-fw"></i> Cerrar Sesión</a>
                            </li>
                        </ul>
                        <!-- /.dropdown-user -->
                    </li>
                    <!-- /.dropdown -->
                </ul>
                <!-- /.navbar-top-links -->

                <div class="navbar-default sidebar" role="navigation">
                    <div class="sidebar-nav navbar-collapse">
                        <ul class="nav" id="side-menu">
                            <li class="sidebar-search">
                                <div class="input-group custom-search-form">
                                    <button class="btn btn-default" type="button">
                                        <%
                                                                                                  if (uregistrado.getGenero() == 1) {%>
                                        <i style="width:50px; height:50px; margin-left: 5%;"><img src="../img/iconos/mujer.png" alt="Usuario: <%if (uregistrado != null) {
                                                                                                      out.print(uregistrado.getNombres() + " " + uregistrado.getApellidos());
                                                                                                  }%>" 
                                                                                                  title="Eres: <%if (uregistrado != null) {
                                                                                                      out.print(uregistrado.getNombres() + " " + uregistrado.getApellidos());
                                                                                                  }%>"></i>
                                            <% } else {%>
                                        <i style="width:50px; height:50px;"><img src="../img/iconos/hombre.png" alt="Usuario: <%if (uregistrado != null) {
                                                                                         out.print(uregistrado.getNombres() + " " + uregistrado.getApellidos());
                                                                                     }%>" 
                                                                                 title="Eres: <%if (uregistrado != null) {
                                                                                         out.print(uregistrado.getNombres() + " " + uregistrado.getApellidos());
                                                                                     }%>"></i>
                                            <%}
                                            %>
                                    </button>
                                    </span>
                                </div>
                                <!-- /input-group -->
                            </li>
                        </ul>
                        <ul style="margin-left: 1,5%;">
                            <li>
                                <%
                                    out.print(menu);
                                %>
                            </li>
                        </ul>
                    </div>
                    <!-- /.sidebar-collapse -->
                </div>
                <!-- /.navbar-static-side -->
            </nav>

            <div id="page-wrapper">
                <div class="row" style="width:50%;">
                    <div class="col-lg-12">
                        <h1 class="page-header">Actualizar datos del producto</h1>
                    </div>
                    
                    <div class="alert alert-warning" style="margin-top: 20%;">
                        <img src="../img/mensajes/warning.png">
                        <span>¡Información!</span>
                        <p>Recuerde tener presente que el precio registrado es por kilogramo.
                        </p>
                    </div>

                    <div style="margin-top: 0%;">
                        <%
                            String tipo = "";
                            String mensaje = "";
                            if (request.getParameter("msg") != null && request.getParameter("tipo") != null) {
                                tipo = request.getParameter("tipo");
                                mensaje = request.getParameter("msg");
                        %>
                        <jsp:include page="msg.jsp" flush="true">
                            <jsp:param name="tipo" value="<%=tipo%>" /> 
                            <jsp:param name="sal" value="<%=mensaje%>" /> 
                        </jsp:include>

                        <%}%>
                    </div>

                    <form action="../cp" name="modProducto" method="post" role="form">
                        <div class="form-group">
                            <%
                                FacadeCategorias facadeCategories = new FacadeCategorias();
                                ArrayList<CategoriaDTO> categorias = new ArrayList();
                                categorias = (ArrayList<CategoriaDTO>) facadeCategories.listarCategorias();
                            %>
                            <label for ="txtCategoria">Categoría:</label>

                            <select name="txtCategoria" readonly="true" class="form-control">
                                <% for (CategoriaDTO categoria : categorias) {%>
                                <option value="<% if (categoria != null) {
                                        out.print(categoria.getIdCategoria());
                                    }%>" ><%if (categoria != null) {
                                            out.print(categoria.getNombre());
                                        }%></option>
                                    <%
                                        }
                                    %> 
                            </select>
                        </div>
                            <input name="txtIdProducto" id="txtIdProducto" type="text" value="<%if (productoDto != null) {
                                        out.print(productoDto.getIdProductos());
                                    }%>" class="form-control" readonly="true"/>
                            
                        <div class="form-group">
                            <label for ="txtNombre">Nombre del producto:</label>

                            <input name="txtNombre" id="txtNombre" type="text" value="<%if (productoDto != null) {
                                        out.print(productoDto.getNombre());
                                    }%>" class="form-control" readonly="true"/>

                        </div>
                                    
                        <div class="form-group">
                            <label for ="txtUnidad">Unidad:</label>
                            <input name="txtUnidad" id="txtUnidad" type="text" value="<%if (productoDto != null) {
                                    out.print(productoDto.getUnidad());
                                }
                                   %>" readonly="true" class="form-control"/>
                        </div>
                        
                        <div class="form-group">
                            <label for ="txtPrecio">Precio:</label>
                            <input name="txtPrecio" id="txtPrecio" type="text" value="<%if (productoDto != null) {
                                    out.print(productoDto.getPrecioProducto());
                                }
                                   %>" class="form-control"/>
                        </div>

                        <div class="form-group">
                            <div>
                                <input type="hidden" name="actualizarProducto" id="actualizarProducto" value=""/>
                                <button type="submit" value="Actualizar" name="btnActualizarProducto" class="btn btn-success btn-lg btn-block">Actualizar</button>
                            </div>
                        </div>
                    </form>
                </div>   

            </div>
            <%
                        } else {
                            out.print("No hay ningún dato");
                        }
                    } else {
                        out.print("No hay ningún dato");
                    }
                } else {
                    response.sendRedirect("../index.jsp");
                }
            %>


            <div class="panel-footer">
                <p style="color: green"><a style="color: green" id="sena" href="http://www.sena.edu.co/">Sena</a>          |   Stefhany Alfonso Rincón                |
                    Última actualización: 
                    <%
                        FacadeConsultas facadeConsults = new FacadeConsultas();
                        out.println(facadeConsults.consultarFecha());%>         |  <a href="http://www.minagricultura.gov.co/" style="color:green;">MinAgricultura</a></p>
            </div>

            <!-- /#page-wrapper -->

            <!-- jQuery -->
            <script src="../bower_components/jquery/dist/jquery.min.js"></script>    
            <script src="../bower_components/jquery/dist/jquery.js"></script>
            <script src="../bower_components/jquery/dist/jquery.validate.js"></script>
            <script src="../bower_components/jquery/dist/additional-methods.js"></script>
            <script src="../bower_components/jquery/dist/paging.js" type="text/javascript"></script>

            <!-- Bootstrap Core JavaScript -->
            <script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

            <!-- Metis Menu Plugin JavaScript -->
            <script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>

            <!-- Morris Charts JavaScript -->
            <script src="../bower_components/raphael/raphael-min.js"></script>
            <script src="../bower_components/morrisjs/morris.min.js"></script>
            <script src="../js/morris-data.js"></script>

            <!-- Custom Theme JavaScript -->
            <script src="../dist/js/sb-admin-2.js"></script>

    </body>

</html>