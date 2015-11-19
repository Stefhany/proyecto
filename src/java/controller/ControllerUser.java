/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dtos.UsuariosDTO;
import facade.FacadeConsultas;
import facade.FacadeUsuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mail.Mail;
import utilities.MyException;

/**
 *
 * @author Stefhany Alfonso
 */
public class ControllerUser extends HttpServlet {

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
            throws ServletException, IOException, MyException {
        response.setContentType("text/html;charset=UTF-8");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        PrintWriter out = response.getWriter();
        String salida = "";
        FacadeUsuarios facadeUser = new FacadeUsuarios();
        UsuariosDTO userDto = new UsuariosDTO();
        try {
            if (request.getParameter("btnRegistrarUsuario") != null && request.getParameter("rUsuario") != null) {
                Date fechaNac = Date.valueOf(request.getParameter("txtfechanacimiento"));
                UsuariosDTO udto = new UsuariosDTO();
                String correo = request.getParameter("txtcorreo").trim();
                udto.setNombres(request.getParameter("txtnombre").trim());
                udto.setApellidos(request.getParameter("txtpellido").trim());
                udto.setCedula(request.getParameter("txtcedula").trim());
                udto.setTelefono(request.getParameter("txttelefono").trim());
                udto.setDireccion(request.getParameter("txtdireccion").trim());
                udto.setCorreo(request.getParameter("txtcorreo").trim());
                udto.setClave(request.getParameter("txtConfirmarClave").trim());
                udto.setGenero(Integer.parseInt(request.getParameter("txtGenero").trim()));
                udto.setNotificacion(Integer.parseInt(request.getParameter("txtnotificacion").trim()));
                udto.setCiudad(request.getParameter("txtciudad").trim());
                udto.setFechaNacimiento(sdf.format(Date.valueOf(request.getParameter("txtfechanacimiento"))).toString());
                FacadeConsultas facadeConsult = new FacadeConsultas();
                Date fechaActual = (Date) facadeConsult.consultarFechaActual();
                int año = facadeConsult.diferenciasDeFechas(fechaNac, fechaActual);
                if (año > 365 || año == 365) {
                    String registrarPersona = facadeUser.ingresarRegistro(udto);
                    if (registrarPersona.equals("ok")) {

                        Mail.sendMail("Bienvenid@", "Ahora eres parte del Sistema de Información para"
                                + " la Gestión de Alimentos Agrícolas."
                                + " En donde encontraras un facil acceso para generar cada uno de "
                                + " tus negocios.<br><br>"
                                + " Gracias por pertenecer a SIGAA <br>"
                                + " Persona encargada: Stefhany Alfonso Rincón <br>"
                                + " Líneas de atención: 3213018539", correo);

                        String mensajeRegistro = "El registro ha sido exitoso.";
                        response.sendRedirect("index.jsp?tipo=1&msg=" + mensajeRegistro);

                    } else {
                        String mensajeRegistro = "No se pudo hacer el registro.";
                        response.sendRedirect("index.jsp?tipo=0&msg=" + mensajeRegistro);
                    }
                } else {
                    String mensajeEdad = "En este sistema no se permiten personas menores de edad.";
                    response.sendRedirect("index.jsp?tipo=0&msg=" + mensajeEdad);
                }

            } else if (request.getParameter("btnReestablecer") != null && request.getParameter("reestablecer") != null) {
                userDto.setClave(request.getParameter("txtConfirmarClave").trim());
                userDto.setIdUsuarios(Integer.parseInt(request.getParameter("txtIdUsuario").trim()));
                String sal = facadeUser.modificarClave(userDto);
                if (sal.equals("ok")) {
                    String msg = "La contraseña ha sido actualiazada";
                    response.sendRedirect("pages/cambiarclave.jsp?tipo=1&msg=" + msg);
                } else {
                    String msg = "La contraseña no se pudo cambiar.";
                    response.sendRedirect("pages/cambiarclave.jsp?tipo=0&msg=" + msg);
                }

            } else if (request.getParameter("btnModificarUsuario") != null && request.getParameter("modificarUsuario") != null) {
                //out.print("ok");
                userDto.setTelefono(request.getParameter("txtTelefono").trim());
                userDto.setDireccion(request.getParameter("txtDireccion").trim());
                userDto.setCorreo(request.getParameter("txtCorreo").trim());
                userDto.setCiudad(request.getParameter("txtCiudad").trim());
                userDto.setIdUsuarios(Integer.parseInt(request.getParameter("txtId").trim()));

                String modificarUsuario = facadeUser.modificarUsuario(userDto);

                if (modificarUsuario.equals("ok")) {
                    String modificar = "Sus datos han sido actualizados.";
                    response.sendRedirect("pages/modificarusuario.jsp?tipo=1&msg=" + modificar);
                } else {
                    String modificar = "Sus datos no han sido actualizados.";
                    response.sendRedirect("pages/modificarusuario.jsp?tipo=0&msg=" + modificar);
                }

            } else if (request.getParameter("btnEnviarCorreoReestablecerClave") != null && request.getParameter("enviarCorreoReestablecerClave") != null) {
                String correo = request.getParameter("txtCorreo").trim();

                int idUser = facadeUser.buscarIdUser(correo);

                if (idUser > 0) {
                    Mail.sendMail("Reestablecer contraseña de SIGAA ", " Gracias por permitirnos ayudarle en la "
                            + " recuperación de su contraseña. Para ello debe digitar el siguiente código: <a href='#'>00000" + idUser + "</a> "
                            + " dandole click al siguiente enlace: http://localhost:8080/ProyectoSigaa/pages/recuperarclave.jsp"
                            + " Gracias por pertenecer a SIGAA <br>"
                            + " Persona encargada: Stefhany Alfonso Rincón <br>"
                            + " Líneas de atención: 3213018539", correo);

                    String reestablecer = "Revise su correo electronico, en el cual se le enviara la información "
                            + " para poder reestablecer su contraseña ";
                    response.sendRedirect("pages/login.jsp?tipo=1&msg=" + reestablecer);
                } else {
                    String mensaje = "Hemos tenido problemas al enviar el correo para reestablecer su contraseña.";
                    response.sendRedirect("pages/login.jsp?tipo=0&msg=" + mensaje);
                }
            } else if (request.getParameter("idUser") != null) {

                String salidaDos = facadeUser.deshabilitarUser(Integer.parseInt(request.getParameter("idUser")));
                if (salidaDos.equals("ok")) {
                    String mensaje = "El usuario ha sido deshabilitado.";
                    response.sendRedirect("paginas/tablagestionarrol.jsp?tipo=1&msg=" + mensaje);
                } else {
                    String mensaje = "El usuario no ha sido deshabilitado.";
                    response.sendRedirect("paginas/tablagestionarrol.jsp?tipo=0&msg=" + mensaje);
                }

            } else if (request.getParameter("btnRecuperarClave") != null && request.getParameter("recuperarClave") != null) {

                int codigo = Integer.parseInt(request.getParameter("txtCode").trim());
                String clave = request.getParameter("txtConfirmarClave").trim();
                String correoUser = facadeUser.confirmarRecuperacionClaveCorreo(codigo);
                String nameUser = facadeUser.confirmarRecuperacionClaveNameUser(codigo);
                String sal = facadeUser.recuperarClave(clave, codigo);

                if (sal.equals("ok")) {

                    Mail.sendMail("Se ha modificado la contraseña", " Hola, " + nameUser + "<br>"
                            + " La contraseña de tu cuenta de SIGAA con el correo " + correoUser + " se ha modificado recientemente.<br>"
                            + " si desconoces esta acción, diríjase al siguiente enlace: http://localhost:8080/ProyectoSigaa/pages/recuperarclave.jsp"
                            + " Gracias por pertenecer a SIGAA<br>"
                            + " Persona encargada: Stefhany Alfonso Rincón <br>"
                            + " Líneas de atención: 3213018539", correoUser);
                    String mensaje = "Ya puedes ingresar nuevamente a nuestro sistema";
                    response.sendRedirect("pages/login.jsp?tipo=1&msg=" + mensaje);
                } else {
                    String mensaje = "Tenemos problemas para finalizar el cambio de la contraseña.";
                    response.sendRedirect("pages/login.jsp?tipo=0&msg=" + mensaje);
                }

            } else {
                out.println("Su ingreso no es permitido");
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
        try {
            processRequest(request, response);
        } catch (MyException ex) {
            Logger.getLogger(ControllerUser.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (MyException ex) {
            Logger.getLogger(ControllerUser.class.getName()).log(Level.SEVERE, null, ex);
        }
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
