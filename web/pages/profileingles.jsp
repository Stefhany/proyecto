<%-- 
    Document   : profile
    Created on : 19-oct-2015, 15:30:12
    Author     : Mona
--%>

<%@page import="dtos.RolesUsuariosDTO"%>
<%@page import="facade.FacadeRolesUsuarios"%>
<%@page import="dtos.OfertasDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.LinkedList"%>
<%@page import="facade.FacadeOfertas"%>
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

        <title>Profile - SIGAA</title>

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
        <%
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Cache-Control", "no-store");
            response.setDateHeader("Expires", 0);
        %>
        <%    
            HttpSession miSesion = request.getSession(false);
            if (miSesion.getAttribute("usr") != null) {
                UsuariosDTO userdto = (UsuariosDTO) miSesion.getAttribute("usr");
                String menu = (String) miSesion.getAttribute("mp");
                FacadeOfertas facadeOffer = new FacadeOfertas();
                LinkedList<OfertasDTO> ofertas = new LinkedList();
                ofertas = (LinkedList<OfertasDTO>) facadeOffer.consultarTopOfertas();

        %>
    </head>

    <body>

        <div id="wrapper" style="scroll:none;">

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
                                <%                                    int idUsuario = userdto.getIdUsuarios();
                                    FacadeRolesUsuarios facadeRoles = new FacadeRolesUsuarios();
                                    LinkedList<RolesUsuariosDTO> u = new LinkedList();
                                    u = facadeRoles.mostrarRol(idUsuario);
                                    for (RolesUsuariosDTO roles : u) {
                                        out.print(roles.getRolId().getNombre());
                                        
                                %> 
                                <i class="fa fa-user fa-fw" ></i> <i class="fa fa-caret-down"></i></p>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="modificarusuario.jsp"><i class="fa fa-user fa-fw"></i>Personal data</a>
                            </li>
                            <li><a href="cambiarclave.jsp"><i class="fa fa-gear fa-fw"></i> Change Password</a>
                            </li>
                            <li class="divider"></li>
                            <li><a href="logout.jsp"><i class="fa fa-sign-out fa-fw"></i> Close session</a>




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
                                                                                              <%                                            if (userdto.getGenero() == 1) {%>
                                                                                              <i style="width:50px; height:50px; margin-left: 5%;"><img src="../img/iconos/mujer.png" alt="Usuario: <%if (userdto != null) {
                                                out.print(userdto.getNombres() + " " + userdto.getApellidos());
                                            }%>" 
                                                                                              title="Eres: <%if (userdto != null) {
                                                                                                          out.print(userdto.getNombres() + " " + userdto.getApellidos());
                                                                                                      }%>"></i>
                                            <% } else if (userdto.getGenero() == 0) {
                                            %>
                                                                                 <i style="width:50px; height:50px;"><img src="../img/iconos/hombre.png" alt="Usuario: <%if (userdto != null) {
                                                out.print(userdto.getNombres() + " " + userdto.getApellidos());
                                            }%>" 
                                                                                 title="Eres: <%if (userdto != null) {
                                                                                         out.print(userdto.getNombres() + " " + userdto.getApellidos());
                                                                                     }%>"></i>
                                            <%} else if (roles.getRolId().getIdRol() == 1) {%>
                                                                                 <i style="width:50px; height:50px;"><img src="../img/iconos/grupo.jpg" alt="Usuario: <%if (userdto != null) {
                                                                                             out.print(userdto.getNombres() + " " + userdto.getApellidos());
                                                                                         }%>" 
                                                                                 title="Eres: <%if (userdto != null) {
                                                                                         out.print(userdto.getNombres() + " " + userdto.getApellidos());
                                                                                     }%>"></i>
                                            <%} else {
                                                        out.print("null");
                                                    }
                                                }
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
                        <h1 class="page-header">Welcome</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-3 col-md-6">
                        <div style="background-color: #fff; border: 1px; border-color: green; border-style: outset;">
                            <div class="panel-heading">
                                <div class="row">
                                    <img src="../img/portfolio/foto1.png" style="margin-left: 15%;">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6" style="width: 25%;">
                        <div style="background-color: #fff; border: 1px; border-color: green; border-style: outset;">
                            <div class="panel-heading" >
                                <div class="row">
                                    <img src="../img/portfolio/foto2.png" style="margin-left:7%; margin-top: 5%; margin-bottom: 4%;">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div style="background-color: #fff; border: 1px; border-color: green; border-style: outset;">
                            <div class="panel-heading">
                                <div class="row">
                                    <img src="../img/portfolio/foto3.png" style="margin-left:7%; margin-top: 7%; margin-bottom: 7%;">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div style="background-color: #fff; border: 1px; border-color: green; border-style: outset;">
                            <div class="panel-heading">
                                <div class="row">
                                    <img src="../img/portfolio/foto4.png" style="margin-left:5%; margin-top: -2%;">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row" style="margin-top: 3%;">
                    <div class="col-lg-12">
                        <div class="panel panel-success">
                            <div class="panel-heading">
                                <i class="fa fa-shopping-cart fa-fw"></i>  Top of more products offered
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <div class="dataTable_wrapper">

                                    <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                        <thead>
                                            <tr>
                                                <th class="odd gradeX"><i class="fa fa-user fa-fw"></i>Name producer</th>
                                                <th class="even gradeC">Category Product</th>
                                                <th class="odd gradeA">Name Product</th>
                                                <th class="odd gradeA">Price</th>
                                                <th class="odd gradeA">Expiration date</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%                
                                                for (OfertasDTO offers : ofertas) {
                                            %>
                                        <td><%=offers.getUser().getNombres()%></td> 
                                        <td><%=offers.getProducts().getCategoriaId().getNombre()%></td>
                                        <td><%=offers.getProducts().getNombre()%></td>
                                        <td><%=offers.getPrecio()%></td>
                                        <td><%=offers.getFechaFin()%></td>
                                        </tbody>
                                        <%}%>
                                    </table>
                                </div>
                                <!-- /.list-group -->
                            </div>
                            <!-- /.panel-body -->
                        </div>
                        <%
                            } else {
                                response.sendRedirect("../index.jsp");
                            }
                        %>
                        <!-- /.panel -->
                    </div>
                    <!-- /.col-lg-4 -->
                </div>
            </div></div>

        <!-- /#page-wrapper -->

        <!-- jQuery -->
        <script src="../bower_components/jquery/dist/jquery.min.js"></script>

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
