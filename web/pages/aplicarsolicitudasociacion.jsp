<%-- 
    Document   : aplicarsolicitudasociacion
    Created on : 20-oct-2015, 13:00:56
    Author     : Mona
--%>

<%@page import="facade.FacadeConsultas"%>
<%@page import="facade.FacadeRolesUsuarios"%>
<%@page import="dtos.RolesUsuariosDTO"%>
<%@page import="java.util.LinkedList"%>
<%@page import="facade.FacadeAportesProductores"%>
<%@page import="dtos.SolicitudDistribuidorDTO"%>
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

        <title>Solicitudes - SIGAA</title>

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

                if (request.getParameter("idSolicitud") != null) {
                    int id = Integer.parseInt(request.getParameter("idSolicitud"));
                    SolicitudDistribuidorDTO solDto = new SolicitudDistribuidorDTO();

                    FacadeAportesProductores facadeAportes = new FacadeAportesProductores();

                    solDto = facadeAportes.buscarIdAsociacion(id);
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
                                        <i style="width:50px; height:50px;">Aqui va la imagen del usuario</i>
                                    </button>
                                    <button class="btn btn-default" type="button">
                                        <i style="width:50px; height:50px; text-align: center;"><% if (uregistrado != null) {
                                                out.print(uregistrado.getNombres() + " " + uregistrado.getApellidos());
                                            }
                                            %></i>
                                    </button>
                                    </span>
                                </div>
                                <!-- /input-group -->
                            </li>
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
                        <h1 class="page-header">Participar en pedido</h1>
                    </div>

                    <form action="../cor" method="post" name="aplicarPedidoAsociacion" role="form" id='formAportar'>
                        <input name="txtIdSolicitud" id="txtIdSolicitud" value="<%if (solDto != null) {
                                out.print(solDto.getIdSolicitud());
                            }%>" type="hidden">
                        <input name="txtIdAso" id="txtIdAso" value="<%if (solDto != null) {
                                out.print(solDto.getUser().getIdUsuarios());
                            }%>" type="hidden"> 

                        <div class="form-group">
                            <label for="txtNombreProducto">Nombre del Producto:</label>
                            <input class="form-control" name="txtNombreProducto" id="txtNombreProducto" value="<%if (solDto != null) {
                                    out.print(solDto.getProduct().getNombre());
                                }%>" type="text" readonly="true">
                        </div>

                        <div class="form-group">
                            <label for="txtFechaSolicitada">Fecha solicitada por la asociación:</label>
                            <input name="txtFechaSolicitada" id="txtFechaSolicitada" value="<%if (solDto != null) {
                                    out.print(solDto.getFechaEntregaInterna());
                                }%>" type="date" class="form-control" readonly="true">
                        </div>

                        <div class="form-group">
                            <label for="txtCantidadSolicitada">Cantidad solicitada:</label>
                            <input name="txtCantidadSolicitada" id="txtCantidadSolicitada" value="<%if (solDto != null) {
                                    out.print(solDto.getCantidadSolicitada());
                                }%>" type="text" class="form-control" readonly="true">
                        </div>

                        <div class="form-group">
                            <label for="txtCantidadAportar">Cantidad aportar:</label>
                            <input name="txtCantidadAportar" class="form-control" id="txtCantidadAportar" value="" type="text">
                        </div>

                        <div class="form-group">
                            <label for="txtFechaEntrega">Fecha entrega:</label>
                            <input name="txtFechaEntrega" class="form-control" id="txtFechaEntrega" value="" type="date">
                        </div>

                        <input name="aplicarSolicitud" id="aplicarSolicitud" type="hidden">

                        <div>
                            <button name="btnAplicarSolicitud" type="submit" class="btn btn-success btn-lg btn-block">Aplicar Solicitud</button>
                        </div>
                    </form>
                    <%} else {
                            out.print("No ha llegado ningún dato");
                        } %>
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

            <script>
                $().ready(function () {
                    $("#formAportar").validate({
                        rules: {
                            txtCantidadSolicitada: {
                                required: true,
                                maxlength: 5,
                                //caracteres:true,
                                //digits:false
                                digits: true
                            }
                        },
                        messages: {
                            txtCantidadSolicitada: {
                                required: "Campo requerido",
                                maxlength: "Minimo {0} caracteres",
                                //caracteres:"Solo letras",
                                digits: "Solo números"
                                        //lettersonly: "Solo letras"
                            }
                        }
                    });
                });
            </script>
    </body>

</html>