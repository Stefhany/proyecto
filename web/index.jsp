<%-- 
    Document   : inde
    Created on : 16-oct-2015, 13:59:21
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

        <title>Inicio - SIGAA</title>

        <!-- Bootstrap CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Links CSS -->
        <link href="css/agency.css" rel="stylesheet">

        <!-- Links Fonts -->
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
        <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>

        <!-- Favicon de SIGAA -->
        <link rel="icon" href="img/portfolio/favicon.ico" type="image/x-ico"/>

    </head>

    <body id="page-top" class="index">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header page-scroll">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand page-scroll" href="#page-top">SIGAA</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="hidden">
                            <a href="#page-top"></a>
                        </li>
                        <li>
                            <a class="page-scroll" href="#services">Servicios</a>
                        </li>
                        <li>
                            <a class="page-scroll" href="#portfolio">Portafolio</a>
                        </li>
                        <li>
                            <a class="page-scroll" href="#about">Regístrate</a>
                        </li>
                        <li>
                            <a class="page-scroll" href="#team">Búsqueda</a>
                        </li>
                        <li>
                            <a class="page-scroll" href="#contact">Contáctenos</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container-fluid -->
        </nav>

        <!-- Header -->
        <header>
            <div class="container">
                <div class="intro-text">
                    <div class="intro-lead-in">Sistema de Información para la Gestión de Alimentos Agrícolas</div>
                    <div class="intro-heading">¡Bienvenidos!</div>
                    <a href="pages/login.jsp" class="page-scroll btn btn-xl">Iniciar sesión</a>
                </div>
            </div>
        </header>

        <!-- Sección de servicios -->
        <section id="services">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h2 class="section-heading">Servicios</h2>
                    </div>
                </div><br><br><br>
                <div class="row text-center">
                    <div class="col-md-3">
                        <span class="fa-stack fa-4x">
                            <i class="fa fa-circle fa-stack-2x text-primary"></i>
                            <i class="fa fa-group fa-stack-1x fa-inverse"></i>
                        </span>
                        <h4 class="service-heading">Comercialización</h4>
                        <p class="text-muted">Este sistema contribuye a la mejora de la comunicación entre la cadena de comercialización agrícola.</p>
                    </div>
                    <div class="col-md-3">
                        <span class="fa-stack fa-4x">
                            <i class="fa fa-circle fa-stack-2x text-primary"></i>
                            <i class="fa fa-laptop fa-stack-1x fa-inverse"></i>
                        </span>
                        <h4 class="service-heading">Diseño Responsive</h4>
                        <p class="text-muted">Se adaptará a cualquier dispositivo que desee utilizar el usuario para realizar alguna tarea específica o sencillamente consultar.</p>
                    </div>
                    <div class="col-md-3">
                        <span class="fa-stack fa-4x">
                            <i class="fa fa-circle fa-stack-2x text-primary"></i>
                            <i class="fa fa-lock fa-stack-1x fa-inverse"></i>
                        </span>
                        <h4 class="service-heading">Seguridad</h4>
                        <p class="text-muted">Este sistema tendrá la mayor seguridad posible en la información suministrada por el usuario, para gestionar corectamente sus solicitudes.</p>
                    </div>
                    <div class="col-md-3">
                        <span class="fa-stack fa-4x">
                            <i class="fa fa-circle fa-stack-2x text-primary"></i>
                            <i class="fa fa-shopping-cart fa-stack-1x fa-inverse"></i>
                        </span>
                        <h4 class="service-heading">Venta Online</h4>
                        <p class="text-muted">El sistema no cuenta con ventas online de los productos registrados.</p>
                    </div>
                </div>
            </div>
        </section>

        <!-- Portafolio de SIGAA -->
        <section id="portfolio" class="bg-light-gray">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h2 class="section-heading">Portafolio</h2>
                        <h3 class="section-subheading text-muted">Encontraras los productos más frescos del mercado.</h3>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4 col-sm-6 portfolio-item">
                        <a href="#portfolioModal1" class="portfolio-link" data-toggle="modal">
                            <div class="portfolio-hover">
                                <div class="portfolio-hover-content">
                                    <i class="fa fa-plus fa-3x"></i>
                                </div>
                            </div>
                            <img src="img/portfolio/mercado.jpg" class="img-responsive" title="Mercado">
                        </a>
                        <div class="portfolio-caption">
                            <h4>Mercado</h4>
                            <p class="text-muted">Diversidad en productos</p>
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-6 portfolio-item">
                        <a href="#portfolioModal2" class="portfolio-link" data-toggle="modal">
                            <div class="portfolio-hover">
                                <div class="portfolio-hover-content">
                                    <i class="fa fa-plus fa-3x"></i>
                                </div>
                            </div>
                            <img src="img/portfolio/frutas.jpg" class="img-responsive" title="Mercado" alt="Frutas frescas">
                        </a>
                        <div class="portfolio-caption">
                            <h4>Frutas</h4>
                            <p class="text-muted">Las frutas más frescas del mercado.</p>
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-6 portfolio-item">
                        <a href="#portfolioModal3" class="portfolio-link" data-toggle="modal">
                            <div class="portfolio-hover">
                                <div class="portfolio-hover-content">
                                    <i class="fa fa-plus fa-3x"></i>
                                </div>
                            </div>
                            <img src="img/portfolio/7.jpg" class="img-responsive" alt="">
                        </a>
                        <div class="portfolio-caption">
                            <h4>Vegetales</h4>
                            <p class="text-muted">Los nutrientes de los vegetales son mejor frescos</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- Registro SIGAA -->
        <section id="about">
            <div class="container" style="width:70%;">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h2 class="section-heading">Regístrate</h2>
                        <h3 class="section-subheading text-muted">No dejes pasar la oportunidad de ser parte de SIGAA</h3>
                    </div>
                </div>
                <div class="row" style="margin-top:-5%;">
                    <form name="registrarUsuario" role="form" action="cu" method="get" id="formRegistro">
                        <div class="form-group">
                            <label  for="txtnombre">Nombres:</label>
                            <input class="form-control" type="text" id="txtnombre" required name="txtnombre"  placeholder="Ingrese sus nombres"/>
                            <!--<p class="help-block">Example block-level help text here.</p>-->
                        </div>

                        <div class="form-group">
                            <label for="txtapellido">Apellidos:</label>
                            <input class="form-control" type="text" id="txtapellido" name="txtpellido"  placeholder="Ingrese sus apellidos"/>
                        </div>

                        <div class="form-group">
                            <label for="txtcedula">Cédula:</label>
                            <input  type="text" name="txtcedula" id="txtcedula" placeholder="Ingrese su cédula" 
                                    onchange="validarCedula(this);" class="form-control"/>
                            <div id="empResult" style="background-color: white; font-size: 12px;color:red;">
                            </div><div id="empResult2" style="background-color: white; font-size: 12px;color:green;">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="txttelefono">Telefono:</label>
                            <input class="form-control" type="text" name="txttelefono" id="txttelefono" placeholder="Ingrese su telefono" />
                        </div>

                        <div class="form-group">
                            <label for="txtdireccion">Dirección:</label>
                            <input class="form-control" type="text" name="txtdireccion" id="txtdireccion" placeholder="Ingrese su dirección"/>
                        </div>

                        <div class="form-group">
                            <label for="txtcorreo">Correo:</label>
                            <input type="email" name="txtcorreo" id="txtcorreo"  placeholder="Ingrese el correo electrónico" 
                                   onchange="validarCorreo(this);" class="form-control"/>
                            <div id="empResult1" style="background-color: white; font-size: 12px;color:red;">
                            </div><div id="empResult3" style="background-color: white; font-size: 12px;color:green;">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="txtConfirmarCorreo">Confirmar correo:</label>
                            <input class="form-control" type="email" placeholder="Ingrese nuevamente el correo electrónico" name="txtConfirmarCorreo"/>
                        </div>

                        <div class="form-group">   
                            <label for="txtclave">Contraseña:</label>
                            <input class="form-control" type="password" id="txtclave" name="txtclave" placeholder="Contraseña"/>
                        </div> 

                        <div class="form-group">
                            <label for="txtconfirmar">Confirmar Contraseña:</label>
                            <input class="form-control" type="password" id="txtconfirmar" name="txtConfirmarClave"  placeholder="Repita su contraseña"/>
                        </div> 

                        <div class="form-group">
                            <label for="txtciudad">Ciudad:</label>
                            <input class="form-control" type="text" name="txtciudad" id="txtciudad" placeholder="Ingrese su ciudad"/>
                        </div>

                        <div class="form-group">
                            <label for="txtfechanacimiento">Fecha de Nacimiento:</label>
                            <input  type="date" id="txtfechanacimiento" name="txtfechanacimiento" 
                                    onblur="javascript:validarFechaNacimiento();"
                                    required="true" class="form-control"/>
                            <div id="result" class="mensajegError"></div>
                        </div>

                        <div class="form-group">
                            <label for="txtnotificacion">Desea recibir notificaciones:</label>
                            <select class="form-control" name="txtnotificacion">
                                <option value="0">No</option>
                                <option value="1">Si</option>                                        
                            </select>
                        </div>

                        <div class="form-group" align="center">
                            <div class="checkbox">
                                <label id="txtTerminos" for="txtTerminos"></label>
                                <input id="textTerminos" name="textTerminos" type="checkbox"/>
                                <a href="terminos.jsp" style="color:blue;"> Leer términos y condiciones</a>
                            </div>
                        </div>


                        <input type="hidden" name="rUsuario" id="rUsuario">

                        <div>
                            <button id="btn" type="submit" id="btnRegistrarUsuario" name="btnRegistrarUsuario" class="btn btn-success btn-lg btn-block">Registrar</button>
                        </div>

                    </form>
                </div>
            </div>
        </section>

        <!-- Filtro en SIGAA -->
        <section id="team" class="bg-light-gray">
            <div class="container" style="width:70%;">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h2 class="section-heading">Búsqueda</h2>
                        <h3 class="section-subheading text-muted">La manera más sencilla para informarte de SIGAA</h3>
                    </div>
                </div>
                <div class="row">
                    <form name="filtro" action="cf" role="form">
                        <table name="tableFiltro">
                            <th><label for="txtProductor">Nombre Productor:</label></th>
                            <th><div class="col-sm-11" >
                                <input name="txtProductor"  id="txtProductor" type="text" class="form-control">
                            </div></th>

                            <th><label for="txtProducto" >Nombre Producto:</label></th>
                            <th><div class="col-sm-11">
                                <input name="txtProducto" id="txtProducto" type="text" class="form-control">
                            </div></th>
                            <th><label for="txtFecha" class="col-sm-2 control-label">Fecha ofertas:</label></th>
                            <th><div class="col-sm-11">
                                <input name="txtFecha" id="txtFecha" type="date" class="form-control"></div></th>

                            <th><button type="submit" value="Consultar" name="consultar" class="btn btn-success">Consultar</button></th>
                        </table>
                    </form>
                </div>
            </div>
        </section>

        <!-- Contacto con SIGAA -->
        <section id="contact">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h2 class="section-heading">Contáctanos</h2>
                        <h3 class="section-subheading text-muted">Siempre estamos de la mano con nuestro cliente</h3>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <form name="sentMessage" id="contactForm" novalidate action="ccu">
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
                                    <button type="submit" class="btn btn-xl" name="enviar">Enviar</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>

        <footer>
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <span class="copyright">Copyright &copy; 2015</span>
                    </div>
                    <div class="col-md-4">
                        <ul class="list-inline social-buttons">
                            <li><a href="https://www.twitter.com"><i class="fa fa-twitter"></i></a>
                            </li>
                            <li><a href="https://www.facebook.com/"><i class="fa fa-facebook"></i></a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-md-4">
                        <ul class="list-inline quicklinks">
                            <li><a href="#">Derechos reservamos</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </footer>

        <!-- Portafolio de modales -->

        <!-- Portafolio Modal 1 -->
        <div class="portfolio-modal modal fade" id="portfolioModal1" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-content">
                <div class="close-modal" data-dismiss="modal">
                    <div class="lr">
                        <div class="rl">
                        </div>
                    </div>
                </div>
                <div class="container">
                    <div class="row">
                        <div class="col-lg-8 col-lg-offset-2">
                            <div class="modal-body">
                                <h2>Mercado</h2>
                                <p class="item-intro text-muted">Diversidad en productos</p>
                                <img class="img-responsive img-centered" src="img/portfolio/mercado.jpg" alt="">
                                <p><strong>Con SIGAA lograrás tener productos de diferentes categorías, desde hortalizas hasta tubérculos.</strong><br>
                                    Teniendo la mayor diversidad en cada uno de sus características como: precio, cantidad, calidad y proveedor.</p>
                                <button type="button" class="btn btn-primary btn-lg active" data-dismiss="modal"><i class="fa fa-times"></i> Cerrar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Portafolio Modal 2 -->
        <div class="portfolio-modal modal fade" id="portfolioModal2" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-content">
                <div class="close-modal" data-dismiss="modal">
                    <div class="lr">
                        <div class="rl">
                        </div>
                    </div>
                </div>
                <div class="container">
                    <div class="row">
                        <div class="col-lg-8 col-lg-offset-2">
                            <div class="modal-body">
                                <h2>Frutas</h2>
                                <p class="item-intro text-muted">Las frutas más frescas del mercado</p>
                                <img class="img-responsive img-centered" src="img/portfolio/frutas.jpg" alt="">
                                <p><strong>Te ofrecemos la mayor calidad en frutas</strong><br>
                                    No solo te damos los mejores medios de comunicación sino también te garantizamos productos de calidad.</p>
                                <button type="button" class="btn btn-primary btn-lg active" data-dismiss="modal"><i class="fa fa-times"></i> Cerrar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Portafolio Modal 3 -->
        <div class="portfolio-modal modal fade" id="portfolioModal3" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-content">
                <div class="close-modal" data-dismiss="modal">
                    <div class="lr">
                        <div class="rl">
                        </div>
                    </div>
                </div>
                <div class="container">
                    <div class="row">
                        <div class="col-lg-8 col-lg-offset-2">
                            <div class="modal-body">
                                <h2>Vegetales</h2>
                                <p class="item-intro text-muted">Los nutrientes de los vegetales son mejor frescos</p>
                                <img class="img-responsive img-centered" src="img/portfolio/frutas.jpg" alt="">
                                <p><strong>Tambíen te ofrecemos vegetales</strong><br>
                                    Nuestra nutrición no solo se basa en frutas, también necesitamos vegetales para que nos den vitaminas A, B, C, K, B12, B6 entre otras.</p>
                                <button type="button" class="btn btn-primary btn-lg active" data-dismiss="modal"><i class="fa fa-times"></i> Cerrar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!-- jQuery -->
        <script src="js/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>

        <!-- Plugin JavaScript -->
        <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
        <script src="js/classie.js"></script>
        <script src="js/cbpAnimatedHeader.js"></script>

        <!-- Contact Form JavaScript -->
        <script src="js/jqBootstrapValidation.js"></script>
        <script src="js/contact_me.js"></script>

        <!-- Custom Theme JavaScript -->
        <script src="js/agency.js"></script>

    </body>

</html>