/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.ConsultasDTO;
import dtos.OfertasDTO;
import dtos.ProductoDTO;
import dtos.ProductosAsociadosUsuariosDTO;
import dtos.UsuariosDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Mona
 */
public class Consultas {

    private ResultSet rs = null;
    private PreparedStatement pstmt = null;
    private static Connection cnn = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    public LinkedList<OfertasDTO> consultarFiltro(String filtro, Connection cnn) {
        this.cnn = cnn;
        String salida = "";
        LinkedList<OfertasDTO> listaFiltro = new LinkedList();
        try {
            String querryFiltro = " select idProductosAsociadosUsuarios, usuariosId, "
                    + " concat(nombres,' ',apellidos) as Productor, productosId,"
                    + " nombreProducto, FechaFin "
                    + " from `usuarios` u inner join "
                    + " `productosasociadosusuarios` s on u.`idUsuarios` = s.`usuariosId` "
                    + " inner join `productos` p on s.`productosId` = p.`idProductos` "
                    + " inner join `ofertas` o on s.`idProductosAsociadosUsuarios` = o.`productosAsociadosUsuariosId` "+filtro ;
            pstmt = cnn.prepareStatement(querryFiltro);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    
                    UsuariosDTO user = new UsuariosDTO(rs.getInt("usuariosId"), rs.getString("Productor"));
                    ProductoDTO pro = new ProductoDTO(rs.getInt("productosId"), rs.getString("nombreProducto"));
                    ProductosAsociadosUsuariosDTO proAso = new ProductosAsociadosUsuariosDTO(user, pro);
                    proAso.setIdProductosAsociadosUsuarios(rs.getInt("idProductosAsociadosUsuarios"));
                    OfertasDTO ofer = new OfertasDTO(proAso);
                    ofer.setFechaFin(rs.getString("FechaFin"));
                    listaFiltro.add(ofer);
                }
            }
        } catch (SQLException sqle) {
            salida = "Mira lo que ocurrio! " + sqle.getMessage() + " y " + sqle.getSQLState();
        }
        return listaFiltro;
    }
    
    public ConsultasDTO consultarFecha(Connection cnn) {
        this.cnn = cnn;
        ConsultasDTO c = null;
        String salida = "";
        try {
            String querryFecha = " select concat( DAYNAME(now()),', ',DAYOFMONTH(now()),' de ', MONTHNAME(now()),' de ',year(now())) as fecha; ";
            pstmt = cnn.prepareStatement(querryFecha);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    c = new ConsultasDTO();
                    c.setFecha(rs.getString("fecha"));
                }
            }
        } catch (SQLException sqle) {
            salida = "Mira lo que ocurrio! " + sqle.getMessage() + " y " + sqle.getSQLState();
        }
        return c;
    }
    
    public int diferenciasDeFechas(Date fechaInicial, Date fechaFinal, Connection cn) {
        this.cnn = cn;
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String fechaInicioString = df.format(fechaInicial);
        try {fechaInicial = df.parse(fechaInicioString);} 
        catch (ParseException ex) {}

        String fechaFinalString = df.format(fechaFinal);
        try {fechaFinal = df.parse(fechaFinalString);}
        catch (ParseException ex) {}

        long fechaInicialMs = fechaInicial.getTime();
        long fechaFinalMs = fechaFinal.getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
        return ((int) dias);
    }
    
    public Date consultarFechaActual(Connection cnn) {
        this.cnn = cnn;
        Date date = null;
        ConsultasDTO c = new ConsultasDTO();
        String salida = "";
        try {
            String querryFecha = " select Date_format(now(),'%Y/%m/%d') as fechaActual; ";
            pstmt = cnn.prepareStatement(querryFecha);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    date = rs.getDate("fechaActual");
                }
            }
        } catch (SQLException sqle) {
            salida = "Mira lo que ocurrio! " + sqle.getMessage() + " y " + sqle.getSQLState();
        }
        return date;
    }
}
