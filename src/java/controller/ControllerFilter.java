/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dtos.OfertasDTO;
import facade.FacadeConsultas;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mona
 */
public class ControllerFilter extends HttpServlet {

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
        try {
            
            request.getSession().setAttribute("filtro", "filtro");
            
            String filtro = "";

            if (request.getParameter("txtProductor") != null && request.getParameter("txtProductor") != "") {

                filtro += " WHERE nombres like('%" + request.getParameter("txtProductor") + "%')";
            } else {
                filtro += " WHERE nombres like('%')";
            }

            if (request.getParameter("txtProducto") != null && request.getParameter("txtProducto") != "") {

                filtro += " AND nombreProducto like('%" + request.getParameter("txtProducto") + "%')";
            }

            if (request.getParameter("txtFecha") != null && request.getParameter("txtFecha") != "") {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                //sdf.format(Date.valueOf(request.getParameter("txtFecha"))).toString();
                String fecha = request.getParameter("txtFecha");
                filtro += " AND FechaFin BETWEEN CURRENT_DATE and '" + fecha + "'";
            }
            
            //response.sendRedirect("paginas/filtro.jsp");
            
            // 
            //llamar al metodo   
            out.println("<html>");
            out.println("<head>");
            out.println("<title>SIGAA Consultar </title>");
            out.println("</head>");
            out.println("<body>");

            //out.print("ksdvjsdvjj kjsnva ios"+filtro); 
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>Nombre productor</th>");
            out.println("<th>Nombre producto</th>");
            out.println("<th>Fecha de la oferta</th>");
            out.println("</tr>");
            //Consultas con = new Consultas();
            FacadeConsultas facadeConsults = new FacadeConsultas();
            LinkedList<OfertasDTO> filtrar = new LinkedList();
            filtrar = (LinkedList<OfertasDTO>) facadeConsults.consultarFiltro(filtro);

            //out.print(filtrar.size());
            if (filtrar.size() != 0) {
                for (int i = 0; i < filtrar.size(); i++) {
                    out.println("<tr>");
                    out.println("<td>" + filtrar.get(i).getIdAso().getUsuario().getNombres() + "</td>");
                    out.println("<td>" + filtrar.get(i).getIdAso().getProducto().getNombre() + "</td>");
                    out.println("<td>" + filtrar.get(i).getFechaFin() + "</td>");
                    out.println("</tr>");
                }
            } else {
                out.println("<tr>");
                out.println("<td>No se encontraron registros.</td>");
                out.println("<td>No se encontraron registros.</td>");
                out.println("<td>No se encontraron registros.</td>");
                out.println("</tr>");
            }

            out.println("</table>");
//            out.println("<h2> Consultar !</h2>");
//            out.println("<h3>EL FILTRO PARA LA CONSULTAR ES ;<br /> </h3><H1>" + con.listarSolicitudesDeAsociacion(filtro) + "</h1>");
            out.println("</body>");
            out.println("</html>");

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
