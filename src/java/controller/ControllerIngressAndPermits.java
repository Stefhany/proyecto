/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dtos.RolesDTO;
import dtos.UsuariosDTO;
import facade.FacadeUsuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mona
 */
public class ControllerIngressAndPermits extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        FacadeUsuarios menu = new FacadeUsuarios();
        
        if (request.getParameter("btnIngresar") != null && request.getParameter("btnIngresar").equals("Ingresar")) {
            
                String usr = request.getParameter("txtCorreo");
                String psw = request.getParameter("txtClave");
                UsuariosDTO datosUsuario = new UsuariosDTO();
                RolesDTO rol = new RolesDTO(datosUsuario);
                            
                String menuAPintar = "";
                HashMap<UsuariosDTO, String> hs = new HashMap<UsuariosDTO, String>();
                String url = request.getContextPath();
                hs = menu.validarUsuarioV2(usr, psw);
                for (Map.Entry<UsuariosDTO, String> registro : hs.entrySet()) {
                    datosUsuario = registro.getKey();
                    menuAPintar = registro.getValue();
                }
                // out.print("documento " + datosUsuario.getDocumento());
                
                if (datosUsuario.getCedula().equals("") && 
                        datosUsuario.getEstado() == 2 || 
                        datosUsuario.getEstado() == 3 || 
                        datosUsuario.getEstado() == 6 || 
                        datosUsuario.getEstado() == 5) {
                    HttpSession miSesion = request.getSession(true);
                    miSesion.setAttribute("usr", datosUsuario);
                    miSesion.setAttribute("mp", menuAPintar);
                    miSesion.setAttribute("rol", rol);
                    response.sendRedirect("pages/profile.jsp");

                } else if (datosUsuario.getCedula().equals("") && datosUsuario.getEstado() == 1) {
                    String mensaje = "Usuario no validado (Solicitar permisos)";
                    response.sendRedirect("pages/login.jsp?tipo=2&msg="+mensaje);
                    
                }else if (datosUsuario.getCedula().equals("") && datosUsuario.getEstado() == 4) {
                    String mensaje = "Usuario deshabilitado, contáctese con nosotros para conocer su inquietud.";
                    response.sendRedirect("pages/login.jsp?tipo=2&msg="+mensaje);
                    
                } else if (datosUsuario.getCedula().equals("")) {
                    String mensaje = "Datos incorrectos, revise e ingrese nuevamente.";
                    response.sendRedirect("pages/login.jsp?tipo=0&msg="+mensaje);
                }else {
                    String prueba = "No eres parte del sistema, te invitamos a que te registres.";
                    response.sendRedirect("pages/login.jsp?tipo=0&msg="+prueba);
                }
            } else {  
                out.print("Esta ingresando de forma fraudalente");                
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
