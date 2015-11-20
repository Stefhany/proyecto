/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import dtos.UsuariosDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Stefhany Alfonso
 */
public class Ofertas extends HttpServlet {

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
            throws ServletException, IOException, JRException {
        response.setContentType("text/html;charset=UTF-8");
        //int dato1 = Integer.parseInt(request.getParameter("txtIdUsuario")) != 0 ? Integer.parseInt(request.getParameter("txtIdUsuario")) : 0;
           //se debe crear un map para pasar los parámetros
        HttpSession miSesion = request.getSession(false);
        if (miSesion.getAttribute("usr") != null) {
            UsuariosDTO uregistrado = (UsuariosDTO) miSesion.getAttribute("usr");
            String menu = (String) miSesion.getAttribute("mp");
            int idUser = uregistrado.getIdUsuarios();
            Map parametros = new HashMap();
            parametros.put("idUsuario", idUser);
            parametros.put("agricola", getServletContext().getRealPath("reports/foto1.jpg"));
            parametros.put("firma", getServletContext().getRealPath("jasper/logoFinal.png"));
            //Se intancia la conexión a la base de datos
            Connection conn = utilities.Connection.getInstance();

            //En la variable reporte se carga el reporte compilado es decir el jasper
            // la instrucción  getServletContext().getRealPath("/jasper/reporteVentas.jasper")  devuelve la ruta completa desde el directorio local. para el caso es
            //C:\Users\admin\Desktop\Programacion\pruebasgithub\ExamplePdf\build\web\jasper\reporteVentas.jasper
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getServletContext().getRealPath("reports/historialmisofertas.jasper"));
            //JasperReport reporte = (JasperReport) JRLoader.loadObject((request.getContextPath()+"/jasper/reporteVentas.jasper"));

            //La variable bytes toma el reporte jasper le pasa los parámetros y va a la base de datos a traer
            // la consulta que se convierte en pdf.
            @SuppressWarnings("unchecked")
            byte[] bytes = JasperRunManager.runReportToPdf(reporte, parametros, conn);

            // el atributo attachment hace que el archivo se lance con el nombre reporteventasfechahora.pdf
            SimpleDateFormat formateador = new SimpleDateFormat("yyyyMMdd-Hmmss");

            String fecha = formateador.format(new Date());
            response.setHeader("Content-Disposition", "attachment; filename=\"Historial de mis ofertas " + fecha + ".pdf\"");

            response.setHeader("Cache-Control", "max-age=30");
            response.setHeader("Pragma", "No-cache");
            response.setDateHeader("Expires", 0);
            response.setContentLength(bytes.length);
            ServletOutputStream ouputStream = response.getOutputStream();
            ouputStream.write(bytes, 0, bytes.length);
            ouputStream.flush();
            ouputStream.close();
        } else {
            response.sendRedirect("../index.jsp");
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
        } catch (JRException ex) {
            Logger.getLogger(Ofertas.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (JRException ex) {
            Logger.getLogger(Ofertas.class.getName()).log(Level.SEVERE, null, ex);
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
