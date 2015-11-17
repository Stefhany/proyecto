/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.DespachosPedidosDAO;
import daos.PedidoSobreOfertaDAO;
import dtos.DespachosPedidosDTO;
import dtos.SolicitudDistribuidorDTO;
import dtos.UsuariosDTO;
import facade.FacadeAportesProductores;
import facade.FacadeConsultas;
import facade.FacadeDespachosPedidos;
import facade.FacadePedidoSobreOferta;
import facade.FacadeProductos;
import facade.FacadeProductosAsociadosUsuarios;
import facade.FacadeSolicitudDistribuidor;
import facade.FacadeUsuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mail.Mail;

/**
 *
 * @author Mona
 */
public class ControllerOrders extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String salida = "";
        FacadeSolicitudDistribuidor facadeRequestDistributor = new FacadeSolicitudDistribuidor();
        FacadePedidoSobreOferta facadePedidoSobreOferta = new FacadePedidoSobreOferta();
        SolicitudDistribuidorDTO solicitud = new SolicitudDistribuidorDTO();
        FacadeDespachosPedidos facadeDespachoPedidos = new FacadeDespachosPedidos();
        FacadeAportesProductores facadeAporte = new FacadeAportesProductores();
        try {
            if (request.getParameter("btnSolicitar") != null && request.getParameter("solicitar").equals("solicitar")) {
                String nombreDistribuidor = request.getParameter("txtNombreDistribuidor").trim();
                int telefonoDistribuidor = Integer.parseInt(request.getParameter("txtTelefonoDistribuidor").trim());
                String direccionDistribuidor = request.getParameter("txtDireccionDistribuidor").trim();
                String nombreProducto = request.getParameter("txtNombreProducto").trim();
                int cantidadSolicitada = Integer.parseInt(request.getParameter("txtCantidadSolicitada").trim());
                String fechaSolicitud = request.getParameter("txtFechaSolicitud").trim();
                String correoProductor = request.getParameter("txtCorreoProductor").trim();
                String correoDistribuidor = request.getParameter("txtCorreoDistribuidor").trim();
                String nombreProductor = request.getParameter("txtNombreProductor").trim();
                int telefonoProductor = Integer.parseInt(request.getParameter("txtTelefonoProductor").trim());

                PedidoSobreOfertaDAO p = new PedidoSobreOfertaDAO();
                int contar = facadePedidoSobreOferta.calcularCantidad(Integer.parseInt(request.getParameter("txtIdOferta")));
                int canasta = contar - cantidadSolicitada;

                Date fechaSolicitada = Date.valueOf(request.getParameter("txtFechaSolicitud").trim());
                Date fechaRegistrada = Date.valueOf(request.getParameter("txtFecha").trim());

                FacadeConsultas facadeConsults = new FacadeConsultas();
                int fechasHabiles = facadeConsults.diferenciasDeFechas(fechaSolicitada, fechaRegistrada);
                if (fechasHabiles > 0) {
                    if (contar >= cantidadSolicitada) {
                        FacadePedidoSobreOferta facadeOrderOffer = new FacadePedidoSobreOferta();
                        String salidaDos = facadeOrderOffer.registrarPedidoSobreOferta(Integer.parseInt(request.getParameter("txtCantidadSolicitada").trim()),
                                Integer.parseInt(request.getParameter("txtIdOferta").trim()), request.getParameter("txtFechaSolicitud").trim(),
                                Integer.parseInt(request.getParameter("txtIdDistribuidor").trim()));
                        if (salidaDos.equals("ok")) {
                            Mail.sendMail("Noticia de oferta", "Te informamos que el distribuidor " + nombreDistribuidor
                                    + ", le solicito " + cantidadSolicitada + " kilogramos de " + nombreProducto
                                    + ", para la fecha " + fechaSolicitud + ". <br><br>"
                                    + " Si desea comunicarse con el cliente lo puede hacer directamente"
                                    + " a su número teléfonico " + telefonoDistribuidor + " o a su "
                                    + " correo electronico " + correoDistribuidor + ".<br><br>"
                                    + " Recuerde que la mercancía se debe entregar en la " + direccionDistribuidor + "y su "
                                    + " máxima entrega es para el " + fechaSolicitud + "<br><br>"
                                    + " Información: Aún tiene publicado " + canasta + " kilogramos de " + nombreProducto + ".<br><br><br>"
                                    + " Gracias por pertenecer a SIGAA <br>"
                                    + " Persona encargada: Stefhany Alfonso Rincón <br>"
                                    + " Líneas de atención: 3213018539", correoProductor);

                            Mail.sendMail("Confirmación de pedido", "Recuerde que ha solicitado " + cantidadSolicitada + ""
                                    + " kilogramos de " + nombreProducto + ", del usuario " + nombreProductor + ".<br>"
                                    + " Si desea concretar las condiciones del pedido se puede comunicar a su teléfono "
                                    + telefonoProductor + " o al correo electronico " + correoProductor + ".<br><br>"
                                    + "Información: Recuerce tener presentes los términos y condiciones del pedido.<br><br>"
                                    + "Gracias por pertenecer a SIGAA <br>"
                                    + " Persona encargada: Stefhany Alfonso Rincón <br>"
                                    + "Líneas de atención: 3213018539", correoDistribuidor);
                            String mensaje = "Su pedido ha sido enviado sactisfactoriamente";
                            String advertencia = "Recuerde que el precio que se va a tener en cuenta para el envio de este pedido "
                                    + " es de acuerdo a la postulación que hizo el productor. Se le enviara un correo de confirmación "
                                    + " para más detalles del pedido";
                            response.sendRedirect("pages/listarofertasactuales.jsp?tipo=1&msg=" + mensaje + "&tipo=2&msg=" + advertencia);
                        }
                    } else {
                        String msj = "No puede ingresar una cantidad mayor a la solicitada";
                        response.sendRedirect("pages/listarofertasactuales.jsp?msgSalida = <strong>" + msj + "</Strong>");
                    }
                } else {
                    String mensaje = "La fecha que selecciono no esta disponible para el termino de esta oferta.";
                    response.sendRedirect("pages/listarofertasactuales.jsp?msgSalida = <strong>" + mensaje + "</Strong>");
                }

            } else if (request.getParameter("btnSolicitarAsociacion") != null && request.getParameter("solicitarAsociacion") != null) {
                //out.print("ok");
                solicitud.setCantidadSolicitada(Integer.parseInt(request.getParameter("txtCantidad").trim()));
                solicitud.setFechaSolicitud(request.getParameter("txtFechaSolicitud").trim());
                solicitud.setCantidadSolicitudFinal(Integer.parseInt(request.getParameter("txtCantidad").trim()));
                solicitud.setProductoId(Integer.parseInt(request.getParameter("subcategoria").trim()));
                solicitud.setDistribuidorId(Integer.parseInt(request.getParameter("txtId").trim()));
                solicitud.setObservacion(request.getParameter("txtObservacion").trim());
                solicitud.setPrecioSolicitud(Integer.parseInt(request.getParameter("subcategoria2").trim()));
                
                FacadeProductos facadeProducto = new FacadeProductos();
                String nombreProducto = facadeProducto.buscarProducto(Integer.parseInt(request.getParameter("subcategoria").trim()));
                int cantidadSolicitda = Integer.parseInt(request.getParameter("txtCantidad").trim());
                String fechaSolicitud = request.getParameter("txtFechaSolicitud").trim();
                
                String correo = request.getParameter("txtCorreo");
                String nombre = request.getParameter("txtUser");

                salida = facadeRequestDistributor.insertarSolicitudDistribuidor(solicitud);

                if (salida.equals("ok")) {
                    String mensaje = "El pedido a la asociación a sido enviado sactisfactoriamente. Se le enviara un correo de confirmacion.";
                    response.sendRedirect("pages/realizarpedidoasociacion.jsp?tipo=1&msg=" + mensaje);
                    Mail.sendMail("Confirmación de pedido a la asociación", ""
                            + " <h3>Confirmación</h3>"
                            + " Señor, ra: " + nombre + "<br><br>"
                            + " Su pedido a sido solicitado satisfactoriamente, con las siguientes caracteristicas: <br>"
                            + " Ha solicitado: "+cantidadSolicitda+" kilogramos de "+nombreProducto+" para la "
                            + " fecha: "+fechaSolicitud+", siendo esta la fecha maxíma para cumplir con su pedido "
                            + " por la asociación.<br><br>"
                            + " Información: Recuerde que al realizar un pedido no lo puede cancelar si la "
                            + " asociación ya lo aprobo, dado que se definio en lo terminos y condiciones que "
                            + " cuando la asociación acepta un pedido, no lo puede cancelar dado que ya "
                            + " tiene a unos productos comprometidos para aportar en su pedido. <br><br><br>"
                            + " Gracias por pertenecer a SIGAA <br>"
                            + " Persona encargada: Stefhany Alfonso Rincón <br>"
                            + " Líneas de atención: 3213018539", correo);
                } else {
                    String msg = "Hemos tenido problemas al registrar su pedido";
                    response.sendRedirect("pages/realizarpedidoasociacion.jsp?tipo=0&msg=" + msg);
                }
            } else if (request.getParameter("btnGenerarPedido") != null && request.getParameter("generarPedido") != null) {

                String nombreProducto = request.getParameter("txtNombreProducto").trim();
                String nombreDistribuidor = request.getParameter("Distribuidor").trim();
                int cantidadSolicitada = Integer.parseInt(request.getParameter("txtCantidadSolicitada").trim());
                String fechaEntregaPorElProductor = request.getParameter("txtFechaEntrega").trim();

                String fechaEntrega = request.getParameter("txtFechaEntrega").trim();
                int idSolicitud = Integer.parseInt(request.getParameter("txtIdSolicitud"));
                solicitud.setIdSolicitud(Integer.parseInt(request.getParameter("txtIdSolicitud").trim()));
                solicitud.setFechaEntregaInterna(request.getParameter("txtFechaEntrega").trim());

                int fechaEntregaBien = facadeDespachoPedidos.validarFecha(fechaEntrega);

                if (fechaEntregaBien == 1) {
                    int fechaAnticipacion = facadeDespachoPedidos.validarFechaDespacho(fechaEntrega, idSolicitud);
                    if (fechaAnticipacion == 1) {
                        salida = facadeRequestDistributor.modificarSolicitudDistribuidor(solicitud);
                        if (salida.equals("ok")) {
                            FacadeUsuarios facadeUsuarios = new FacadeUsuarios();
                            FacadeProductosAsociadosUsuarios facadeProductAsociado = new FacadeProductosAsociadosUsuarios();

                            List<UsuariosDTO> users = new ArrayList();
                            StringBuilder correos = new StringBuilder();
                            users = facadeProductAsociado.enviarCorreoAProductores(3);

                            for (UsuariosDTO user : users) {
                                correos.append(user.getCorreo());
                                correos.append(", ");
                            }

                            Mail.sendMail("Solicitud de pedido", "Señor(a): "
                                    + " La asociación requiere para la fecha: " + fechaEntregaPorElProductor + ", " + cantidadSolicitada + " "
                                    + " de " + nombreProducto + ", para el distribuidor " + nombreDistribuidor + " <br><br>"
                                    + " Información: Tener presente que al participar en este pedido no lo puede cancelar tres dias antes "
                                    + " del " + fechaEntregaPorElProductor + ". <br><br>"
                                    + " Gracias por pertenecer a SIGAA <br>"
                                    + " Persona encargada: Stefhany Alfonso Rincón <br>"
                                    + " Líneas de atención: 3213018539", correos.toString());

                            response.sendRedirect("pages/listarsolicitudesasociaciones.jsp?msgSalida=<strong>El pedido ha sido enviado a los productores.</Strong>");
                            facadeDespachoPedidos.cambiarEstadoAProductores(idSolicitud);

                        } else {
                            response.sendRedirect("pages/listarsolicitudesasociaciones.jsp?msgSalida=<strong>No se pudo realizar el cambio del estado.</Strong>");
                        }
                    } else {
                        response.sendRedirect("pages/listarsolicitudesasociaciones.jsp?msgSalida=<strong>La fecha de envío a los productores debe ser anterior a la de solicitud.</Strong>");
                    }
                } else {
                    response.sendRedirect("pages/listarsolicitudesasociaciones.jsp?msgSalida=<strong>Esta seleccionando una fecha anterior de la actual.</Strong>");
                }

            } else if (request.getParameter("btnAplicarSolicitud") != null && request.getParameter("aplicarSolicitud") != null) {

                int cantidadPermitida = facadeAporte.calcularCantidad(Integer.parseInt(request.getParameter("txtIdSolicitud").trim()));
                int cantidadAportar = Integer.parseInt(request.getParameter("txtCantidadAportar").trim());
                int idSolicitud = Integer.parseInt(request.getParameter("txtIdSolicitud").trim());

                String nombreProducto = request.getParameter("txtNombreProducto").trim();
                int idProducto = Integer.parseInt(request.getParameter("txtIdProducto").trim());
                int idUsuario = Integer.parseInt(request.getParameter("txtIdUser").trim());

                int idAsociado = facadeAporte.buscarMisIdAsociados(idProducto, idUsuario);

                if (idAsociado > 0) {

                    if (cantidadPermitida >= cantidadAportar || cantidadPermitida > cantidadAportar) {
                        String fechaEntrega = request.getParameter("txtFechaEntrega").trim();
                        int fechaEntregaBien = facadeDespachoPedidos.validarFecha(fechaEntrega);

                        if (fechaEntregaBien == 1) {
                            int fechaAnticipacion = facadeDespachoPedidos.validarFechaAporte(fechaEntrega, idSolicitud);
                            if (fechaAnticipacion == 1) {

                                FacadeAportesProductores facadeContributeProducer = new FacadeAportesProductores();
                                salida = facadeContributeProducer.participarASolicitudAsociacion(request.getParameter("txtFechaEntrega").trim(),
                                        Integer.parseInt(request.getParameter("txtCantidadAportar").trim()),
                                        idAsociado,
                                        Integer.parseInt(request.getParameter("txtIdSolicitud").trim()));

                                response.sendRedirect("pages/listarsolicitudesproductores.jsp?msgSalida= <strong>Su aporte ha sido registrado. Gracias por participar</Strong> ");
                            } else {
                                response.sendRedirect("pages/listarsolicitudesproductores.jsp?msgSalida=<strong>La fecha para el envío del producto debe ser anterior a la fecha estipulada por la asociación.</Strong>");
                            }
                        } else {
                            response.sendRedirect("pages/listarsolicitudesproductores.jsp?msgSalida=<strong>Esta seleccionando una fecha anterior de la actual.</Strong>");
                        }
                    } else {
                        String msj = "No puede ingresar una cantidad mayor a la solicitada";
                        response.sendRedirect("pages/listarsolicitudesproductores.jsp?msgSalida = <strong>" + msj + " </Strong>");
                    }
                } else {
                    String mensaje = "No tiene el producto: " + nombreProducto + " registrado. Por ende no puede participar en este pedido";
                    response.sendRedirect("pages/listarsolicitudesproductores.jsp?msgSalida = <strong>" + mensaje + " </Strong>");
                }
            } else if (request.getParameter("btnDespacharPedido") != null && request.getParameter("despacharPedido") != null) {

                DespachosPedidosDTO dto = new DespachosPedidosDTO();
                String fechaEnvio = request.getParameter("txtFechaEnvio").trim();
                dto.setDireccionDespacho(request.getParameter("txtDireccion").trim());
                dto.setFechaDespacho(request.getParameter("txtFechaEnvio").trim());
                dto.setObservaciones(request.getParameter("txtObservacion").trim());
                dto.setSolicitudId(Integer.parseInt(request.getParameter("txtSolicitud").trim()));
                dto.setUsuariosId(Integer.parseInt(request.getParameter("txtUser").trim()));
                String correoDistribuidor = request.getParameter("txtCorreoDistribuidor").trim();

                int idSolicitud = Integer.parseInt(request.getParameter("txtSolicitud").trim());

                int fechaEntregaBien = facadeDespachoPedidos.validarFecha(fechaEnvio);

                if (fechaEntregaBien == 1) {
                    int fechaAnticipacion = facadeDespachoPedidos.validarFechaDespacho(fechaEnvio, idSolicitud);
                    if (fechaAnticipacion == 1) {
                        FacadeDespachosPedidos facadeDispatchOrder = new FacadeDespachosPedidos();
                        salida = facadeDispatchOrder.insertarDespacho(dto);

                        if (salida.equals("ok")) {
                            facadeDespachoPedidos.cambiarEstadoSolicitud(idSolicitud);
                            Mail.sendMail("Su pedido le ha sido enviado", "Su pedido ha sido enviado satisfactoriamente.", correoDistribuidor);
                            response.sendRedirect("pages/listardespachos.jsp?msgSalida=<strong>El despacho ha sido realizado.</Strong>");
                        } else {
                            response.sendRedirect("pages/listardespachos.jsp?msgSalida=<strong>No se pudo realizar el cambio del estado.</Strong>");
                        }
                    } else {
                        response.sendRedirect("pages/listardespachos.jsp?msgSalida=<strong>La fecha de despacho debe ser anterior a la de solicitud.</Strong>");
                    }
                } else {
                    response.sendRedirect("pages/listardespachos.jsp?msgSalida=<strong>Esta seleccionando una fecha anterior de la actual.</Strong>");
                }

            }
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
