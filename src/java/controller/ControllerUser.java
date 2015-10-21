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
                udto.setCedula(Integer.parseInt(request.getParameter("txtcedula").trim()));
                udto.setTelefono(Integer.parseInt(request.getParameter("txttelefono").trim()));
                udto.setDireccion(request.getParameter("txtdireccion").trim());
                udto.setCorreo(request.getParameter("txtcorreo").trim());
                udto.setClave(request.getParameter("txtConfirmarClave").trim());
                udto.setNotificacion(Integer.parseInt(request.getParameter("txtnotificacion").trim()));
                udto.setCiudad(request.getParameter("txtciudad").trim());
                udto.setFechaNacimiento(sdf.format(Date.valueOf(request.getParameter("txtfechanacimiento"))).toString());
                FacadeConsultas facadeConsult = new FacadeConsultas();
                Date fechaActual = (Date) facadeConsult.consultarFechaActual();
                int año = facadeConsult.diferenciasDeFechas(fechaNac, fechaActual);
                if (año > 365 || año == 365) {
                    salida = facadeUser.ingresarRegistro(udto);
                    if (salida.equals("ok")) {

                        Mail.sendMail("Bienvenid@", "Ahora eres parte del Sistema de Información para"
                                + " la Gestión de Alimentos Agrícolas."
                                + " En donde encontraras un facil acceso para generar cada uno de "
                                + " tus negocios.", correo);
                        response.sendRedirect("index.jsp?msgSalida=<strong>Registro éxitoso</Strong>");
                    } else {
                        response.sendRedirect("index.jsp?msgSalida=<strong>Hay problemas!</Strong>");
                    }
                }else{
                    response.sendRedirect("index.jsp?msgSalida=<strong>No se aceptan usuarios menores de edad.</Strong>");
                }

            } else if (request.getParameter("btnReestablecer") != null && request.getParameter("reestablecer") != null) {
                userDto.setClave(request.getParameter("txtConfirmarClave").trim());
                userDto.setIdUsuarios(Integer.parseInt(request.getParameter("txtIdUsuario").trim()));
                String sal = facadeUser.modificarClave(userDto);
                response.sendRedirect("pages/cambiarclave.jsp?msgSalida= <strong>La contraseña ha sido actualizada.</Strong>");

            } else if (request.getParameter("btnModificarUsuario") != null && request.getParameter("modificarUsuario") != null) {
                //out.print("ok");
                userDto.setTelefono(Integer.parseInt(request.getParameter("txtTelefono").trim()));
                userDto.setDireccion(request.getParameter("txtDireccion").trim());
                userDto.setCorreo(request.getParameter("txtCorreo").trim());
                userDto.setCiudad(request.getParameter("txtCiudad").trim());
                userDto.setIdUsuarios(Integer.parseInt(request.getParameter("txtId").trim()));
                salida = facadeUser.modificarUsuario(userDto);

                response.sendRedirect("pages/modificarusuario.jsp?msgSalida = <strong>Los datos han sido actualizados.</Strong>");
            } else if (request.getParameter("btnEnviarCorreoReestablecerClave") != null && request.getParameter("enviarCorreoReestablecerClave") != null) {
                String correo = request.getParameter("txtCorreo").trim();

                int idUser = facadeUser.buscarIdUser(correo);

                if (idUser > 0) {
                    response.sendRedirect("pages/login.jsp?msgSalida=<strong>Revisa tu correo electrónico</Strong>");
                    Mail.sendMail("Reestablecer contraseña de SIGAA ", " Gracias por permitirnos ayudarle en la "
                            + " recuperación de su contraseña. Para ello debe digitar el siguiente código: <a href='#'>00000" + idUser + "</a> "
                            + " dandole click al siguiente enlace: http://localhost:8080/FarmersMarket-cambios/paginas/recuperarclave.jsp"
                            + " Gracias por pertenecer a SIGAA <br>"
                            + " Persona encargada: Stefhany Alfonso Rincón <br>"
                            + " Líneas de atención: 3213018539", correo);
                } else {
                    response.sendRedirect("pages/login.jsp?msgSalida=<strong>No se pudo hacer la operación.</Strong>");
                }
            }else if (request.getParameter("idUser") != null) {

                String salidaDos = facadeUser.deshabilitarUser(Integer.parseInt(request.getParameter("idUser")));
                response.sendRedirect("paginas/tablagestionarrol.jsp?msgSalida= <strong>El usuario ha sido deshabilitado.</Strong>");

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
