<%-- 
    Document   : listarofertasactuales
    Created on : 20-oct-2015, 10:33:45
    Author     : Mona
--%>

<%@page import="dtos.RolesUsuariosDTO"%>
<%@page import="facade.FacadeRolesUsuarios"%>
<%@page import="facade.FacadeConsultas"%>
<%@page import="dtos.OfertasDTO"%>
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

        <title>Ofertas - SIGAA</title>

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
                UsuariosDTO uregistrado = (UsuariosDTO) miSesion.getAttribute("usr");
                String menu = (String) miSesion.getAttribute("mp");

                FacadeOfertas facadeOffer = new FacadeOfertas();
                LinkedList<OfertasDTO> offers = new LinkedList();
                offers = (LinkedList<OfertasDTO>) facadeOffer.consultarOfertasHabilitadas();
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
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Ofertas</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row" >
                    <div class="col-lg-12">
                        <div class="panel panel-success">
                            <div class="panel-heading">
                                <i class="fa fa-calendar-o fa-fw"></i><a href="../paginas/reporteofertas.jsp"><img src="../img/iconos/excel.png" width="24" height="24" title="Descargar en Excel" alt="Generar excel"></a> Solicitar Oferta
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <div class="dataTable_wrapper">
                                    <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                        <thead>
                                            <tr>
                                                <th>Tipo de producto</th>                   
                                                <th>Nombre productor</th>
                                                <th>Nombre producto</th>
                                                <th>Cantidad</th>
                                                <th>Unidad</th>
                                                <th>Precio</th>
                                                <th>Fecha Fin</th>
                                                <th>Solicitar</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for (OfertasDTO ofer : offers) {
                                            %>
                                            <tr>
                                                <td><%=ofer.getUser().getNombres()%></td> 
                                                <td><%=ofer.getProducts().getCategoriaId().getNombre()%></td>
                                                <td><%=ofer.getProducts().getNombre()%></td>
                                                <td><%=ofer.getCantidad()%></td>
                                                <td><%=ofer.getProducts().getUnidad()%></td>
                                                <td><%=ofer.getPrecio()%></td>
                                                <td><%=ofer.getFechaFin()%></td>

                                        <!-- <td><a href="listarofertas.jsp?idOffer=<%=ofer.getIdOfertas()%>"><img src="../imagenes/order.png" width="32" height="32" alt="Solicitar " 
                                                                                                              title="Solicitar el producto: " onclick="window.open('solicitarpedido.jsp',
                                                                                      'Solicitar Pedido', 'width=100', 'height=200', 'menubar=No')">
                                        </a></td>
                                        <td><a href="../ofertas/modificaroferta.jsp?id=<%=ofer.getIdOfertas()%>"><img src="../../imagenes/modificar.png" width="32" height="32" alt="Modificar oferta <%=ofer.getProducts().getNombre()%>" 
                                                                                                           title="Modificar oferta: <%=ofer.getProducts().getNombre()%>">
                                            </a></td>
                                        <td><a href="../Controlador?idOfertas=<%=ofer.getIdOfertas()%>"><img src="../../imagenes/eliminar.png" width="32" height="32" alt="Eliminar la oferta: <%=ofer.getProducts().getNombre()%>" 
                                                                                                             title="Elimianr la oferta de: <%=ofer.getProducts().getNombre()%>" onclick="return confirmar();">
                                            </a></td>
                                                -->

                                                <td><a href="../pages/solicitaroferta.jsp?idOffer=<%=ofer.getIdOfertas()%>">
                                                        <span class="glyphicon glyphicon-calendar" style="font-size:140%; color:green; margin-left:35%;" alt="Solicitar <%=ofer.getProducts().getNombre()%>" title="Solicitar el producto: <%=ofer.getProducts().getNombre()%> width=100', height=200, menubar=No"></span></a>
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
