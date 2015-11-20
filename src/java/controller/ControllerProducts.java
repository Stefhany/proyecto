/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dtos.CategoriaDTO;
import dtos.ProductoDTO;
import facade.FacadeProductos;
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
public class ControllerProducts extends HttpServlet {

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
            if (request.getParameter("idProducto") != null) {

                FacadeProductos facadeProducto = new FacadeProductos();
                String salida = facadeProducto.deshabilitarProducto(Integer.parseInt(request.getParameter("idProducto")));
                
                if (salida.equals("ok")) {
                    String mensaje = "El producto ha sido deshabilitado.";
                    response.sendRedirect("pages/listarproductos.jsp?tipo=1&msg=" + mensaje);                    
                }else{
                    String mensaje = "El producto no ha sido deshabilitado.";
                    response.sendRedirect("pages/listarproductos.jsp?tipo=0&msg=" + mensaje);                    
                }
            }else if (request.getParameter("btnRegistrar") != null && request.getParameter("registrar") != null) {
                FacadeProductos facadeProducts = new FacadeProductos();
                CategoriaDTO cdto = new CategoriaDTO();
                ProductoDTO pdto = new ProductoDTO();
                pdto.setNombre(request.getParameter("txtNombre").trim());
                pdto.setUnidad(request.getParameter("txtUnidad").trim());
                pdto.setPrecioProducto(Integer.parseInt(request.getParameter("txtPrecio")));
                cdto.setIdCategoria(Integer.parseInt(request.getParameter("txtCategoria").trim()));
                pdto.setCategoriaId(cdto);
                
                String registroProducto = facadeProducts.registrarProducto(pdto);
                if (registroProducto.equals("ok")) {
                    String mensajeRegistro = "El producto ha sido registrado satisfactoriamente.";
                    response.sendRedirect("pages/listarproductos.jsp?tipo=1&msg=" + mensajeRegistro); 
                }else{
                    String mensajeRegistro = "El producto no se ha registrado.";
                    response.sendRedirect("pages/listarproductos.jsp?tipo=0&msg=" + mensajeRegistro);
                }                               
            }else if (request.getParameter("btnActualizarProducto") != null && request.getParameter("actualizarProducto") != null) {
                ProductoDTO prdto = new ProductoDTO();
                prdto.setIdProductos(Integer.parseInt(request.getParameter("txtIdProducto").trim()));                
                prdto.setPrecioProducto(Integer.parseInt(request.getParameter("txtPrecio").trim()));
                
                FacadeProductos facadeProducto = new FacadeProductos();
                String salida = facadeProducto.actualizarProducto(prdto);
                
                if (salida.equals("ok")) {
                    String mensaje = "Los datos del producto han sido actualizados.";
                    response.sendRedirect("pages/listarproductos.jsp?tipo=1&msg=" + mensaje);
                }else{
                    String mensaje = "Los datos del producto no han sido actualizados.";
                    response.sendRedirect("pages/listarproductos.jsp?tipo=0&msg=" + mensaje);
                }                            
            }
        }finally{
            out.print("Esta ingresando de mala manera");
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
