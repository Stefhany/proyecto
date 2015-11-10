/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dtos.AportesProductoresDTO;
import facade.FacadeAportesProductores;
import facade.FacadeConsultas;
import facade.FacadeSolicitudDistribuidor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Stefhany Alfonso
 */
public class ControllerAportProducer extends HttpServlet {

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
        FacadeAportesProductores facadeAport = new FacadeAportesProductores();
        FacadeSolicitudDistribuidor facadeSolicitud = new FacadeSolicitudDistribuidor();
        AportesProductoresDTO aport = new AportesProductoresDTO();
        try{
            if (request.getParameter("idAport") != null) {
                FacadeConsultas facadeConsult = new FacadeConsultas();
                Date fechaEntrega = Date.valueOf("txtFechaEntrega");
                Date fechaSolicitud = Date.valueOf("txtFechaSolicitud");
                int diasFaltantes = facadeConsult.diferenciasDeFechas(fechaEntrega,fechaSolicitud);
                if (diasFaltantes <= 3) {
                    aport.setNovedad(request.getParameter("txtNovedad"));
                    facadeAport.modificarEstadoACancelado(Integer.parseInt(request.getParameter("txtIdAporteProductor").trim()), aport);
                    int cantidadAportada = facadeAport.consultarCantidadAportada(Integer.parseInt(request.getParameter("txtIdAporteProductor").trim()));
                    int cantidadSolicitada = facadeAport.consultarCantidadSolicitada(Integer.parseInt(request.getParameter("txtIdSolicitud").trim()));
                    int cantidadFinal = cantidadSolicitada + cantidadAportada;
                    String cambioCantidad = facadeSolicitud.modificarCantidadSolicitud(cantidadFinal, Integer.parseInt(request.getParameter("txtIdSolicitud").trim()));
                    if (cambioCantidad.equals("ok")) {
                        String salida = "Ha sido cancelada la participación que realizo para el pedido de la asociación";
                        response.sendRedirect("pages/misparticipaciones.jsp?msgSalida = <strong>"+salida+"</Strong>");
                    }else{
                        String salida = "Tuvimos problemas al hacer la cancelación del pedido";
                        response.sendRedirect("pages/misparticipaciones.jsp?msgSalida = <strong>"+salida+"</Strong>");
                    }
                }else{
                    String msj = "No puede cancelar el pedido porque ya ha adquirido un compromiso con "
                            + " la asociación y restan menos de tres (3) para generar el despacho del pedido.";
                    response.sendRedirect("pages/misparticipaciones.jsp?msgSalida = <strong>"+msj+"</Strong>");
                }
            }else{
                out.print("Esta ingresando de forma inadecuada.");
            }
        }finally{
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
            Logger.getLogger(ControllerAportProducer.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ControllerAportProducer.class.getName()).log(Level.SEVERE, null, ex);
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
