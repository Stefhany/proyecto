<%-- 
    Document   : recuperarclave
    Created on : 18-nov-2015, 17:04:21
    Author     : Stefhany Alfonso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>SIGAA - Reestablecer clave</title>
        <!-- Bootstrap Core CSS -->
        <link href="../bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Estilos para mensajes -->
        <link href="../css/estilos.css" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href="../bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="../dist/css/sb-admin-2.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="../bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

        <!-- Favicon de SIGAA -->
        <link rel="icon" href="../img/portfolio/favicon.ico" type="image/x-ico"/>
        <!-- jQuery -->
        <script src="../bower_components/jquery/dist/jquery.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>

        <!-- Metis Menu Plugin JavaScript -->
        <script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>

        <!-- Custom Theme JavaScript -->
        <script src="../dist/js/sb-admin-2.js"></script>
    </head>

    <body style="background-image: url(../img/recuperar.jpg);">
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="login-panel panel-center panel panel-success">
                        <div class="panel-heading">
                            <h3 class="panel-title">Reestablecer Contraseña</h3>
                        </div>
                        <div class="panel-body">
                            <form action="../cu" method="post" name="formrecuperarclave" id="formrecuperarclave">
                                <fieldset>
                                    <div class="form-group">
                                        <label for="txtCode">Código (*):</label>
                                        <input type="text" class="form-control" name="txtCode" id="txtCode" placeholder="Código">
                                    </div>

                                    <div class="form-group">
                                        <label for="txtNuevaClave">Nueva contraseña (*):</label>
                                        <input type="password" class="form-control" name="txtNuevaClave" id="txtNuevaClave" placeholder="Nueva contraseña">
                                    </div>

                                    <div class="form-group">
                                        <label for="txtConfirmarClave">Confirmar contraseña (*):</label>
                                        <input type="password" class="form-control" name="txtConfirmarClave" id="txtConfirmarClave" placeholder="Confirmar contraseña">
                                    </div>


                                    <input type="hidden" name="recuperarClave" id="recuperarClave">
                                    <button id="btnRecuperarClave" type="submit" name="btnRecuperarClave" class="btn btn-success boton" style="alignment-adjust: auto;">Recuperar contraseña</button>
                                </fieldset>                    
                            </form>
                        </div>
                    </div>
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
            </div>


        </div>

        <script>
            $().ready(function () {
                $("#formrecuperarclave").validate({
                    rules: {
                        txtCode: {required: true, caracteres: false, number: true},
                        txtNuevaClave: {required: true, minlength: 6},
                        txtConfirmarClave: {required: true, minlength: 6, equalTo: "#txtNuevaClave"}
                    },
                    messages: {
                        txtCode: {
                            required: "Este campo es requerido",
                            caracteres: "No se admiten caracteres en este campo",
                            number: '<div class="alert alert-danger " role="alert"  style="height: 33px;  width: 191px;   margin-left: 300px;  margin-top: -34px;  font-size: 14px;   padding-top: 9px; ">Campo numérico </div>'
                        },
                        txtNuevaClave: {
                            required: "Campo requerido",
                            minlength: "La contraseña debe tener mínimo {0} caracteres"
                        },
                        txtConfirmarClave: {
                            required: "Campo requerido",
                            minlength: "La contraseña debe tener mínimo {0} caracteres",
                            equalTo: '<div class="alert alert-danger " role="alert"  style="height: 33px;  width: 260px;   margin-left: 300px;  margin-top: -34px;  font-size: 14px;   padding-top: 9px; ">Debe ser el mismo valor anterior</div>'
                        }
                    }
                });
            });
        </script>


    </body>

</html>

