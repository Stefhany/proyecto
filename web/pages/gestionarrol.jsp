<%-- 
    Document   : gestionarrol
    Created on : 20-oct-2015, 13:43:59
    Author     : Mona
--%>

<%@page import="facade.FacadeConsultas"%>
<%@page import="java.util.LinkedList"%>
<%@page import="dtos.RolesUsuariosDTO"%>
<%@page import="java.util.LinkedList"%>
<%@page import="facade.FacadeRolesUsuarios"%>
<%@page import="dtos.RolesDTO"%>
<%@page import="facade.FacadeUsuarios"%>
<%@page import="dtos.UsuariosDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="facade.FacadeRoles"%>
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

        <title>Gestionar Rol - SIGAA</title>

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

                UsuariosDTO u = (UsuariosDTO) miSesion.getAttribute("usr");
                String menu = (String) miSesion.getAttribute("mp");

                FacadeRoles facadeRol = new FacadeRoles();
                ArrayList<RolesDTO> roles = new ArrayList();
                roles = (ArrayList<RolesDTO>) facadeRol.listarRoles();

                if (request.getParameter("idUsuario") != null) {
                    int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
                    UsuariosDTO userdto = new UsuariosDTO();

                    FacadeUsuarios facadeUsers = new FacadeUsuarios();
                    userdto = facadeUsers.consultarUnRegistro(idUsuario);
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
                                    int idUser = userdto.getIdUsuarios();
                                    FacadeRolesUsuarios facadeRoles = new FacadeRolesUsuarios();
                                    LinkedList<RolesUsuariosDTO> user = new LinkedList();
                                    user = facadeRoles.mostrarRol(idUser);
                                    for (RolesUsuariosDTO rol : user) {
                                        out.print(rol.getRolId().getNombre());
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
                <div class="row" style="width:50%;">
                    <div class="col-lg-12">
                        <h1 class="page-header">Despachar pedido</h1>
                    </div>

                    <form name="modificarusuario" method="post" action="../cr">

                        <input name="txtIdUser" id="txtIdUser" type="hidden" 
                               value="<%if (userdto != null) {
                                       out.print(userdto.getIdUsuarios());
                                   }
                               %>"/>

                        <div class="form-group">
                            <label for="txtNombres">Nombres:</label>
                            <input name="txtNombres" id="txtNombres" type="text" 
                                   value="<%if (userdto != null) {
                                           out.print(userdto.getNombres());
                                       }
                                   %>" readonly='true' class="form-control"/>
                        </div>

                        <div class="form-group">
                            <label for="txtApellidos">Apellidos:</label>

                            <input name="txtApellidos" id="txtApellidos" type="text" 
                                   value="<%if (userdto != null) {
                                           out.print(userdto.getApellidos());
                                       }
                                   %>" readonly='true' class="form-control"/> 
                        </div>

                        <div class="form-group">
                            <label for="txtCedula">Cédula:</label>
                            <input name="txtCedula" id="txtCedula" type="text" 
                                   value="<%if (userdto != null) {
                                           out.print(userdto.getCedula());
                                       }
                                   %>" readonly='true' class="form-control"/> 
                        </div>

                        <div class="form-group">
                            <label for="txtTelefono">Teléfono:</label>
                            <input name="txtTelefono" id="txtTelefono" type="text" 
                                   value="<%if (userdto != null) {
                                           out.print(userdto.getTelefono());
                                       }
                                   %>" readonly="true" class="form-control"/>
                        </div>

                        <div class="form-group">
                            <label for="txtDireccion">Dirección:</label>
                            <input name="txtDireccion" id="txtDireccion" type="text" 
                                   value="<%if (userdto != null) {
                                           out.print(userdto.getDireccion());
                                       }
                                   %>" readonly="true" class="form-control"/>
                        </div>

                        <div class="form-group">
                            <label for="txtCorreo">Correo:</label>
                            <input name="txtCorreo" id="txtCorreo" type="text" 
                                   value="<%if (userdto != null) {
                                           out.print(userdto.getCorreo());
                                       }
                                   %>" readonly="true" class="form-control"/>
                        </div>

                        <div class="form-group">
                            <label for="txtCiudad">Ciudad:</label>
                            <input name="txtCiudad" id="txtCiudad" type="text" 
                                   value="<%if (userdto != null) {
                                           out.print(userdto.getCiudad());
                                       }
                                   %>" readonly="true" class="form-control"/>
                        </div>

                        <div class="form-group">
                            <label for="txtFechaNacimiento">Fecha de nacimiento:</label>
                            <input name="txtFechaNacimiento" id="txtFechaNacimiento" type="date" 
                                   value="<%if (userdto != null) {
                                           out.print(userdto.getFechaNacimiento());
                                       }
                                   %>" readonly='true' class="form-control"/> 
                        </div>

                        <div class="form-group">
                            <label for="txtRol">Rol:</label>  
                            <!--<select name="txtRol" id="txtRol" style="width: 200px;">
                                 for (RolesDTO rol : roles) {%>
                                <option name="txtRol" value=" if (rol != null) {
                                        out.print(rol.getIdRol());
                                    }%>">if (rol != null) {
                                            out.print(rol.getNombre());
                                        }%></option>
                                    
                                        }
                                    %>
                            </select>-->

                            <select name="txtRol" id="txtRol" class="form-control">
                                <option name="txtRol" value="2">Productor</option>
                                <option name="txtRol" value="3">Dsitribuidor</option>
                            </select>
                        </div>

                        <input name="cambiarRol" id="cambiarRol" type="hidden"/>

                        <button id="btnSolicitar" type="submit" name="btnCambiarRol" class="btn btn-success btn-lg btn-block">Vincular a Asociación</button>

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
