/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dtos.RolesUsuariosDTO;
import facade.FacadeRolesUsuarios;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mona
 */
public class ControllerRole extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            if (request.getParameter("btnCambiarRol") != null && request.getParameter("cambiarRol") != null) {
                RolesUsuariosDTO u = new RolesUsuariosDTO();
                u.setRolesId(Integer.parseInt(request.getParameter("txtRol").trim()));
                u.setUsuariosId(Integer.parseInt(request.getParameter("txtIdUser").trim()));
                int idUsuario = Integer.parseInt(request.getParameter("txtIdUser").trim());
                String nombreUsuario = request.getParameter("txtNombres").trim();
                String apellidoUsuario = request.getParameter("txtApellidos").trim();
                
                FacadeRolesUsuarios facadeRolUser = new FacadeRolesUsuarios();
                String salida = facadeRolUser.registrarRol(u);
                
                if (salida.equals("ok")) {
                    String salidaDos = facadeRolUser.cambiarEstadoUser(idUsuario);
                    if (salidaDos.equals("ok")) {
                        response.sendRedirect("pages/tablagestionarrol.jsp?msgSalida= <strong>Los cambios han sido satisfactorios.</Strong>");
                    }else {
                        response.sendRedirect("pages/tablagestionarrol.jsp?msgSalida= <strong>El estado del usuario no se pudo cambiar.</Strong>");
                    }
                }else {
                    response.sendRedirect("pages/tablagestionarrol.jsp?msgSalida= <strong>No se le han dado permisos al usuario: " + nombreUsuario +" "+apellidoUsuario +".</Strong>");
                }              
                                
            }
        }finally {
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
