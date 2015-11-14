/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.FacadeSolicitudDistribuidor;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Stefhany Alfonso
 */
public class ControllerDistributorOrders extends HttpServlet {

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
        FacadeSolicitudDistribuidor facadeSolicitud = new FacadeSolicitudDistribuidor();
        try {
            if (request.getParameter("idSolicitud") != null) {
                //out.print("ok");
                int idSolicit = Integer.parseInt(request.getParameter("idSolicitud").trim());
                String salida = facadeSolicitud.cancelarPedido(idSolicit);
                if (salida.equals("ok")) {
                    String msg = "Su pedido a sido cancelado. Esperamos que pronto vuelva a solicitar otro pedido";
                    response.sendRedirect("pages/listarmissolicitudesaunaasociacion.jsp?tipo=1&msg=" + msg);
                }else {
                    String msg = "No se pudo cancelar el pedido";
                    response.sendRedirect("pages/listarmissolicitudesaunaasociacion.jsp?tipo=0&msg=" + msg);
                }
            }
        }finally{
        
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
