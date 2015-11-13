<%-- 
    Document   : cambiarclave
    Created on : 19-oct-2015, 17:06:51
    Author     : Mona
--%>

<%@page import="dtos.RolesUsuariosDTO"%>
<%@page import="java.util.LinkedList"%>
<%@page import="facade.FacadeRolesUsuarios"%>
<%@page import="facade.FacadeConsultas"%>
<%@page import="dtos.UsuariosDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Modificar Clave - SIGAA</title>

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
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Cambiar Contraseña</a>
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
                    <h1 class="page-header">Actualizar contraseña</h1>
                </div>

                <form action="../cu" method="post" name="formcontactenos" id="formReestablcer">
                    <input type="hidden" name="txtIdUsuario" value="<%if (uregistrado != null) {
                            out.print(uregistrado.getIdUsuarios());
                        }
                           %>">

                    <div class="form-group">
                        <label for="txtNuevaClave">Digite su contraseña actual <span style="color:red; font-size:120%;">*</span>:</label>
                        <input type="password" name="txtClaveActual" id="txtClaveActual" class="form-control"
                               placeholder="Contraseña actual" onchange="validarClave(this)">
                        <div id="empResult3" style="background-color: white; font-size: 12px;color:green;">
                        </div><div id="empResult1" style="background-color: white; font-size: 12px;color:red;">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="txtNuevaClave">Digite su nueva contraseña <span style="color:red; font-size:120%;">*</span>:</label>
                        <input type="password" name="txtNuevaClave" id="txtNuevaClave" class="form-control" placeholder="Nueva contraseña">
                    </div>

                    <div class="form-group">
                        <label for="txtConfirmarClave">Confirmar contraseña nueva <span style="color:red; font-size:120%;">*</span>:</label>
                        <input type="password" name="txtConfirmarClave" id="txtConfirmarClave" placeholder="Confirmar contraseña" class="form-control">
                    </div>
                    <input type="hidden" name="reestablecer" id="txtNuevaClave">
                    <div>
                        <button name="btnReestablecer" type="submit" class="btn btn-success btn-lg btn-block" id="btnReestablecer">Cambiar Contraseña</button>
                    </div>
                </form>

            </div>                
        </div>
        <%
            } else {
                response.sendRedirect("../index.jsp");
            }
        %>


        <div class="panel-footer">
            <a id="sena" href="http://www.sena.edu.co/">Sena</a> |   Stefhany Alfonso Rincón                |
            Última actualización: 
            <%
                FacadeConsultas facadeConsults = new FacadeConsultas();
                out.println(facadeConsults.consultarFecha());%>         |  <a href="http://www.minagricultura.gov.co/">MinAgricultura</a>
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
                                       $("#formReestablcer").validate({
                                           rules: {
                                               txtNuevaClave: {
                                                   required: true,
                                                   minlength: 5, },
                                               //equalTo: "#txtclave"
                                               //caracteres:true,
                                               //digits:false 
                                               txtConfirmarClave: {
                                                   required: true,
                                                   minlength: 5,
                                                   equalTo: "#txtNuevaClave"
                                                           //caracteres:true,
                                                           //digits:false txtConfirmarClave
                                               }
                                           },
                                           messages: {
                                               txtNuevaClave: {
                                                   required: "Campo requerido",
                                                   minlength: "Minimo {0} caracteres"
                                               },
                                               txtConfirmarClave: {
                                                   required: "Campo requerido",
                                                   minlength: "Minimo {0} caracteres",
                                                   equalTo: "Debe ser el mismo valor anterior"
                                                           //caracteres:true,
                                                           //digits:false txtConfirmarClave
                                               }
                                           }
                                       });
                                   });
        </script>
</body>

</html>


