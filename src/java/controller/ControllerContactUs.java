/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dtos.ContactUsDTO;
import facade.FacadeContactUs;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mail.Mail;

/**
 *
 * @author Mona
 */
public class ControllerContactUs extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String salida = "";
        try {
            if (request.getParameter("btnEnviar") != null && request.getParameter("btnEnviar").equals("enviar")) {
                out.print("ok");
                String correo = request.getParameter("txtCorreo").trim();
                String nombres = request.getParameter("txtNombre").trim();
                int telefono = Integer.parseInt(request.getParameter("txtTelefono").trim());
                String mensaje = request.getParameter("txtMensaje");
                FacadeContactUs facadeContact = new FacadeContactUs();
                ContactUsDTO contactDto = new ContactUsDTO();
                contactDto.setNombrePersona(request.getParameter("txtNombre").trim());
                contactDto.setCorreo(request.getParameter("txtCorreo").trim());
                contactDto.setTelefono(Integer.parseInt(request.getParameter("txtTelefono").trim()));

                //out.print(nombres+" - "+telefono+" - "+correo+" - "+mensaje);
                String salidaDos = facadeContact.insertarMensaje(contactDto);

                if (salidaDos.equals("ok")) {
                    Mail.sendMail("Contacto", mensaje, "contactosigaa@gmail.com");
                    Mail.sendMail("Confirmación", "El mensaje ha sido enviado al Sistema SIGAA", correo);
                    response.sendRedirect("pages/contactenos.jsp?msgSalida= <strong>Su mensaje fue enviado al Sistema SIGAA.</Strong>");
                } else {
                    response.sendRedirect("pages/contactenos.jsp?msgSalida= <strong>No se pudo enviar el correo al Sistema SIGAA</Strong>");
                }
            } else {
                out.print("tenemos problemas...");
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
        } catch (SQLException ex) {
            Logger.getLogger(ControllerContactUs.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(ControllerContactUs.class.getName()).log(Level.SEVERE, null, ex);
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
