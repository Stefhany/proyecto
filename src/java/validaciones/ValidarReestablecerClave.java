/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validaciones;

import facade.FacadeUsuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utilities.MyException;

/**
 *
 * @author Stefhany Alfonso
 */
public class ValidarReestablecerClave extends HttpServlet {

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
            throws ServletException, IOException, SQLException, MyException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        FacadeUsuarios facadeUser = new FacadeUsuarios();
        
        if (request.getParameter("txtCorreo") != null || !request.getParameter("txtCorreo").equalsIgnoreCase("")) {
            StringBuilder respuesta = new StringBuilder("");
            respuesta.append(facadeUser.validarCorreo2(request.getParameter("txtCorreo").trim()));
            this.writeResponse(response, respuesta.toString());
            
        }  else {
            //mostramos el contenido por default del servlet
        
        
        try {

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BuscarCedulaAjax</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BuscarCedulaAjax at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
      }
    }

    /**
     * Este método permite enviar la respuesta de la consulta la base de datos
     * en forma de cadena pasandole al objeto response que será el que se pasa
     * al objeto ajax y finalmente se muestra en la capa de presentación. Para
     * este proceso no es necesario modificarlo
     *
     * @param resp
     * @param output
     * @throws IOException
     */
    public void writeResponse(HttpServletResponse response, String output) throws IOException {
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Content", "text/html;charset=iso-8859-1");
        response.getWriter().write(output);
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
            Logger.getLogger(ValidarReestablecerClave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MyException ex) {
            Logger.getLogger(ValidarReestablecerClave.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ValidarReestablecerClave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MyException ex) {
            Logger.getLogger(ValidarReestablecerClave.class.getName()).log(Level.SEVERE, null, ex);
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