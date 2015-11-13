<%-- 
    Document   : misparticipaciones
    Created on : 10-nov-2015, 6:25:12
    Author     : Stefhany Alfonso
--%>

<%@page import="facade.FacadeConsultas"%>
<%@page import="dtos.RolesUsuariosDTO"%>
<%@page import="dtos.RolesUsuariosDTO"%>
<%@page import="facade.FacadeRolesUsuarios"%>
<%@page import="java.util.List"%>
<%@page import="java.util.LinkedList"%>
<%@page import="dtos.AportesProductoresDTO"%>
<%@page import="facade.FacadeAportesProductores"%>
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
                int idUser = uregistrado.getIdUsuarios();
                List<AportesProductoresDTO> misAportes = new LinkedList();
                FacadeAportesProductores facadeAport = new FacadeAportesProductores();
                misAportes = facadeAport.consultarMisParticipaciones(idUser);
        %>

        <script>
            function confirmar() {
                if (confirm('¿Esta seguro de borrar este pedido?')) {
                    return true;
                } else {
                    return false;
                }
            }
        </script>
    </head>

    <body>
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
                    <h1 class="page-header">Participaciones en los pedidos por la asociación</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row" >
                <div class="col-lg-12">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <i class="fa fa-calendar-o fa-fw"></i>Mis participaciones
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead style="text-align: center;">
                                        <tr>                    
                                            <th>Distribuidor</th>
                                            <th>Nombre producto</th>
                                            <th>Cantidad solicitada</th>
                                            <th>Unidad</th>
                                            <th>Fecha de Entrega</th>
                                            <th>Cancelar</th>
                                        </tr>                
                                    </thead>
                                    <tbody id="bodyTabla">
                                        <%
                                            for (AportesProductoresDTO aport : misAportes) {
                                        %>
                                        <tr>
                                            <td><%=aport.getProAsoId().getProducto().getCategoriaId().getNombre()%></td>
                                            <td><%=aport.getProAsoId().getProducto().getNombre()%></td>
                                            <td><%=aport.getCantidad()%></td>  
                                            <td><%=aport.getProAsoId().getProducto().getUnidad()%></td>
                                            <td><%=aport.getFechaEntrega()%></td>
                                            <!--<td><button data-toggle="modal" data-target="#miventana" value="<%=//aport.getIdAporteProductor()%>"
                                                        <span class="glyphicon glyphicon-remove-circle" 
                                                      style="font-size:140%; color:green; margin-left:35%;" 
                                                      alt="Cancelar pedido de: <%=//aport.getProAsoId().getProducto().getNombre()%> " 
                                                      title="Cancelar pedido de: <%=//aport.getProAsoId().getProducto().getNombre()%>"></span>
                                                </button>
                                            </td>-->
                                            <td><a href="newjspgreg.jsp" rel="prueba"> Ir</a>
                                            </td>
                                        </tr>

                                    <div class="modal fade" id="miventana" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                        <form action="../cap">
                                        <input type="hidden" value="<%=aport.getSolId().getCantidadSolicitada()%>" name="txtCantidadSolicitada">
                                        <input type="hidden" value="<%=aport.getSolId().getFechaEntregaInterna()%>" name="txtFechaEntregaInterna">
                                        <input type="hidden" value="<%=aport.getCantidad()%>" name="txtCantidadAportada">
                                        <input type="hidden" value="<%=aport.getSolId().getIdSolicitud()%>" name="txtIdSolicitud">
                                        <input type="hidden" value="<%=aport.getIdAporteProductor()%>" name="idAporte">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times</button>
                                                    <center><h4>Registrar Novedad</h4></center>
                                                </div>
                                                <div class="modal-body">
                                                    <!--<form action="asociarproducto.jsp">-->
                                                    <div class="form-group">
                                                        <label>Novedad:</label>
                                                        <textarea style="resize:none;" class="form-control" rows="3" name="txtNovedad" placeholder="Escribir aquí..."></textarea>
                                                    </div>
                                                    <div><span style="color:red; margin-top:45%;"> Maximo 100 letras.</span></div>
                                                    <div style="margin-left: 40%; margin-top: 3%;"><input type="hidden" name="cancelar" id="solicitar" value="cancelar" />
                                                        <a href="../cap?idAport=<%=aport.getIdAporteProductor()%>&solicitud=<%=aport.getSolId().getIdSolicitud()%>">
                                                            <button type="submit" name="btnCancelar" class="btn btn-success" style="text-align: center;">Cancelar Aporte</button></a></div>
                                                        
                                                    
                                                </div>
                                            </div>
                                        </div>
                                        </form>
                                    </div>
                                    <%}%>
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
