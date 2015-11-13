<%-- 
    Document   : asociarproducto
    Created on : 19-oct-2015, 19:56:18
    Author     : Mona
--%>

<%@page import="dtos.ProductosAsociadosUsuariosDTO"%>
<%@page import="facade.FacadeProductosAsociadosUsuarios"%>
<%@page import="dtos.RolesUsuariosDTO"%>
<%@page import="dtos.RolesUsuariosDTO"%>
<%@page import="facade.FacadeRolesUsuarios"%>
<%@page import="facade.FacadeConsultas"%>
<%@page import="dtos.CategoriaDTO"%>
<%@page import="dtos.ProductoDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="facade.FacadeCategorias"%>
<%@page import="java.util.LinkedList"%>
<%@page import="facade.FacadeProductos"%>
<%@page import="dtos.UsuariosDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Asociar Producto - SIGAA</title>
        <!-- Bootstrap Core CSS -->
        <link href="../bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

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

        <script type="text/javascript">
            $(document).ready(function () {

                //Checkbox
                $("input[name=checktodos]").change(function () {
                    $('input[type=checkbox]').each(function () {
                        if ($("input[name=checktodos]:checked").length == 1) {
                            this.checked = true;
                        } else {
                            this.checked = false;
                        }
                    });
                });

            });
        </script>
        <%
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Cache-Control", "no-store");
            response.setDateHeader("Expires", 0);
        %>
        <script>
            function confirmar() {
                if (confirm('¿Esta seguro de asociar este producto?')) {
                    return true;
                } else {
                    return false;
                }
            }
        </script>

        <script type="text/javascript" src="../js/paging.js"></script>

        <%
            HttpSession miSesion = request.getSession(false);
            if (miSesion.getAttribute("usr") != null) {
                UsuariosDTO userdto = (UsuariosDTO) miSesion.getAttribute("usr");
                String menu = (String) miSesion.getAttribute("mp");

                FacadeProductosAsociadosUsuarios facadeProducts = new FacadeProductosAsociadosUsuarios();
                LinkedList<ProductoDTO> productos = new LinkedList();
                productos = (LinkedList<ProductoDTO>) facadeProducts.asociarProductos(userdto.getIdUsuarios());
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
                                    int idUsuario = userdto.getIdUsuarios();
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
                                            if (userdto.getGenero() == 1) {%>
                                                                                              <i style="width:50px; height:50px; margin-left: 5%;"><img src="../img/iconos/mujer.png" alt="Usuario: <%if (userdto != null) {
                                                out.print(userdto.getNombres() + " " + userdto.getApellidos());
                                            }%>" 
                                                                                              title="Eres: <%if (userdto != null) {
                                                                                                          out.print(userdto.getNombres() + " " + userdto.getApellidos());
                                                                                                      }%>"></i>
                                            <% } else {%>
                                                                                 <i style="width:50px; height:50px;"><img src="../img/iconos/hombre.png" alt="Usuario: <%if (userdto != null) {
                                                out.print(userdto.getNombres() + " " + userdto.getApellidos());
                                            }%>" 
                                                                                 title="Eres: <%if (userdto != null) {
                                                                                         out.print(userdto.getNombres() + " " + userdto.getApellidos());
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
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Productos</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row" >
                    <div class="col-lg-12">
                        <div class="panel panel-success">
                            <div class="panel-heading">
                                <i class="fa fa-shopping-cart fa-fw"></i> Asociar Productos
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <div class="dataTable_wrapper">
                                    <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                        <input name="txtIdUser" id="txtIdUser" type="hidden" value="<%if (userdto != null) {
                                                out.print(userdto.getIdUsuarios());
                                            }
                                               %>">

                                        <thead>
                                            <tr>
                                                <th>Categoría</th>
                                                <th>Nombre Producto</th>
                                                <th>Unidad</th>
                                                <th>Asociar</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for (ProductoDTO p : productos) {
                                            %>
                                            <tr>

                                                <td><%=p.getCategoriaId().getNombre()%></td>
                                                <td><%=p.getNombre()%></td>
                                                <td><%=p.getUnidad()%></td>
                                                <!--<td class="squaredFour">
                                                    <input type="checkbox" value="None" id="squaredFour" name="idProAsociado" />
                                                    <label for="squaredFour"></label>
                                                </td>    
                                                <!--<input type="checkbox" name="idProAsociado"/>                                         
                                            </td>-->

                                                <td class="center">
                                                    <a href="../caso?idProduct=<%=p.getIdProductos()%>&idUsu=<%=userdto.getIdUsuarios()%>"><span class="glyphicon glyphicon-star-empty" style="font-size:140%; color:green; margin-left:25%;" onclick="return confirmar();" alt="Asociar: <%=p.getNombre()%>"
                                                                                                                                                 title="Asociar: <%=p.getNombre()%>"></span></a>
                                                </td>
                                            </tr>
                                            <%
                                                }%>

                                        </tbody>
                                    </table>

                                </div>
                                <div style="cursor: pointer; text-align:center;" id="pageNavPosition"></div>
                            </div>        
                            </table>
                        </div>

                        <!-- /.list-group -->
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-4 -->
        </div>
    </div>
    <%

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

    <script type="text/javascript"> Cufon.now();</script>

    <script type="text/javascript">
        <!--
        var pager = new Pager('dataTables-example', 5);
        pager.init();
        pager.showPageNav('pager', 'pageNavPosition');
        pager.showPage(1);
        //--></script>
</body>

</html>
