<%-- 
    Document   : contactenosç
    Created on : 20-nov-2015, 17:34:14
    Author     : Stefhany Alfonso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contáctenos - SIGAA</title>

        <!-- Bootstrap CSS -->
        <link href="../css/bootstrap.min.css" rel="stylesheet">

        <!-- Links CSS -->
        <link href="../css/agency.css" rel="stylesheet">

        <!-- Links Fonts -->
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
        <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>

        <!-- Favicon de SIGAA -->
        <link rel="icon" href="../img/portfolio/favicon.ico" type="image/x-ico"/>
    </head>
    <body>
        <!-- Contacto con SIGAA -->
        <section id="contact">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h2 class="section-heading">Contáctenos</h2>
                        <h3 class="section-subheading text-muted">Siempre estamos de la mano con nuestro cliente</h3>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <form name="sentMessage" id="contactForm" action="../ccu" role="form">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <input type="text" class="form-control" name="txtNombre" placeholder="Nombre" id="txtNombre" required data-validation-required-message="Por favor ingresar el nombre.">
                                        <p class="help-block text-danger"></p>
                                    </div>
                                    <div class="form-group">
                                        <input type="email" class="form-control" name="txtCorreo" placeholder="Correo electrónico" id="txtCorreo" required data-validation-required-message="Por favor ingresar su correo electrónico.">
                                        <p class="help-block text-danger"></p>
                                    </div>
                                    <div class="form-group">
                                        <input type="tel" class="form-control" name="txtTelefono" placeholder="Teléfono" id="txtTelefono" required data-validation-required-message="Por favor ingrese el teléfono.">
                                        <p class="help-block text-danger"></p>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <textarea class="form-control" style="resize: none;" name="txtMensaje" placeholder="Mensaje" id="txtMensaje" required data-validation-required-message="Por favor ingrese el mensaje."></textarea>
                                        <p class="help-block text-danger" ></p>
                                    </div>
                                </div>
                                <div class="clearfix"></div>
                                <div class="col-lg-12 text-center">
                                    <div id="success"></div>
                                    <submit type="hidden" name="enviarHidden"/>
                                    <button type="submit" name="enviar" class="btn btn-xl">Enviar</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
