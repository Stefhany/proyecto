<%-- 
    Document   : login
    Created on : 19-oct-2015, 15:16:26
    Author     : Mona
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
        <title>SIGAA - Iniciar sesión</title>
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
    </head>

    <body style="background-image: url(../img/login2.jpg);">
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="login-panel panel-center panel panel-success">
                        <div class="panel-heading">
                            <h3 class="panel-title">Iniciar sesión</h3>
                        </div>
                        <div class="panel-body">
                            <form role="form" action="../ip" >
                                <fieldset>
                                    <div class="form-group">
                                        <!--<label class="col-lg-1 control-label">Correo:</label>
                                        <div class="col-xs-9">-->
                                        <input class="form-control" placeholder="Correo electrónico" name="txtCorreo" type="email"  required="true" ><!--<span class="help-block">Ingresar correo electrónico</span>-->
                                        <p class="help-block text-danger"></p>
                                    </div>
                                    <div class="form-group">
                                        <!--<label class="col-lg-1 control-label">Contraseña:</label>
                                        <div class="col-xs-9">-->
                                        <input class="form-control" placeholder="Contraseña" name="txtClave" type="password"  value="" required="true">
                                    </div>
                                    <div> 
                                        <p align="center"><a href="../pages/reestablecerclave.jsp" class="link">¿Olvidó su contraseña?</a></p>
                                    </div>    

                                    <!-- Change this to a button or input when using this as a form -->
                                    <button type="submit" value="Ingresar" name="btnIngresar" class="btn btn-success btn-lg btn-block">Ingresar</button>
                                </fieldset>
                            </form>
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
        </div>

        <!-- jQuery -->
        <script src="../bower_components/jquery/dist/jquery.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>

        <!-- Metis Menu Plugin JavaScript -->
        <script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>

        <!-- Custom Theme JavaScript -->
        <script src="../dist/js/sb-admin-2.js"></script>

    </body>

</html>

