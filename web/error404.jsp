<%-- 
    Document   : error404
    Created on : 16-oct-2015, 14:03:47
    Author     : Stefhany Alfonso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
    <!DOCTYPE html>
    <html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Error 404 - SIGAA</title>

        <!-- Bootstrap Core CSS -->
        <link href=<%out.print(getServletContext().getContextPath()+"/bower_components/bootstrap/dist/css/bootstrap.min.css");%>"" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href=<%out.print(getServletContext().getContextPath()+"/bower_components/metisMenu/dist/metisMenu.min.css");%>"" rel="stylesheet">

        <!-- Timeline CSS -->
        <link href=<%out.print(getServletContext().getContextPath()+"/dist/css/timeline.css");%>"" rel="stylesheet">

        <!-- Custom CSS -->
        <link href=<%out.print(getServletContext().getContextPath()+"/dist/css/sb-admin-2.css");%>"" rel="stylesheet">

        <!-- Morris Charts CSS -->
        <link href=<%out.print(getServletContext().getContextPath()+"/bower_components/morrisjs/morris.css");%>"" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href=<%out.print(getServletContext().getContextPath()+"/bower_components/font-awesome/css/font-awesome.min.css");%>"" rel="stylesheet" type="text/css">

        <!-- Favicon de SIGAA -->
        <link rel="icon" href=<%out.print(getServletContext().getContextPath()+"/img/portfolio/favicon.ico");%>"" type="image/x-ico"/>
    
    </head>

    <body>

        <div id="wrapper">
            

            <div id="page-wrapper" style="margin-left:-1%; margin-top:-3%;">
                <div id="advertencia">
                <img id="imgAdvertencia" src="img/error/advertencia.png">
            </div>
            <div id="error">
                <img src="img/error/error404.png">
                <h1>¡PÁGINA NO ENCONTRADA!</h1>
                <h4>Hubo un error con la página que estas buscando.</h4>
                <h5>Es posible que la dirección que escribiste no exista o haya sido eliminada.</h5>
                <button class="btn btn-warning"><a style="color:#fff" href="javascript:window.history.back();">&laquo; Volver atrás</a></button>
            </div>
            </div>
        </div>   

        <!-- jQuery -->
        <script src="bower_components/jquery/dist/jquery.min.js"></script>
        <script src="bower_components/jquery/dist/jquery.js"></script>
        <script src="bower_components/jquery/dist/jquery.validate.js"></script>
        <script src="bower_components/jquery/dist/additional-methods.js"></script>
        <script src="bower_components/jquery/dist/paging.js" type="text/javascript"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

        <!-- Metis Menu Plugin JavaScript -->
        <script src="bower_components/metisMenu/dist/metisMenu.min.js"></script>

        <!-- Morris Charts JavaScript -->
        <script src="bower_components/raphael/raphael-min.js"></script>
        <script src="bower_components/morrisjs/morris.min.js"></script>
        <script src="js/morris-data.js"></script>

        <!-- Custom Theme JavaScript -->
        <script src="dist/js/sb-admin-2.js"></script>

        <script type="text/javascript"> Cufon.now();</script>

    </body>

    </html>

