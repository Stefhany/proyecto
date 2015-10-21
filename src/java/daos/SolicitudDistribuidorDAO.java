/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.ProductoDTO;
import dtos.SolicitudDistribuidorDTO;
import dtos.UsuariosDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author krito
 */
public class SolicitudDistribuidorDAO {

    PreparedStatement pstmt = null;
    Connection cnn = null;
    ResultSet rs = null;
    int resultado = 0;
    String msgSalida = "";

    public String insertarSolicitudDistribuidor(SolicitudDistribuidorDTO solicitud, Connection cnn) {
        try {
            pstmt = cnn.prepareStatement("INSERT INTO solicituddistribuidor VALUES (null,?,current_date(),?,null,3,?,?);");
            pstmt.setInt(1, solicitud.getCantidadSolicitada());
            pstmt.setString(2, solicitud.getFechaSolicitud());
            //pstmt.setString(3, solicitud.getFechaEntregaInterna());
            pstmt.setInt(3, solicitud.getProductoId());
            pstmt.setInt(4, solicitud.getDistribuidorId());
            resultado = pstmt.executeUpdate();
            if (resultado != 0) {
                msgSalida = "ok";
            } else {
                msgSalida = "no";
            }
        } catch (SQLException sqle) {
            msgSalida = "Ha ocurrido la siguiente exepción.. " + sqle.getMessage();
        }
        return msgSalida;
    }

    public LinkedList<SolicitudDistribuidorDTO> listarSolicitudesDistribuidor(Connection cnn) {
        LinkedList<SolicitudDistribuidorDTO> solicitudes = new LinkedList();
        try {
            String querrySolicitudesDistribuidor = " select idSolicitudDistribuidor, idUsuarios, "
                    + " concat(nombres,' ',apellidos) as Distribuidor, idProductos, "
                    + " nombreProducto, cantidadSolicitada, fechaSolicitud "
                    + " from solicituddistribuidor  s "
                    + " inner join productos p on "
                    + " p.idProductos = s.productosId "
                    + " inner join usuarios u on "
                    + " u.idUsuarios = s.distribuidorId"
                    + " where estadosPedidosId = 3;";
            pstmt = cnn.prepareStatement(querrySolicitudesDistribuidor);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UsuariosDTO user = new UsuariosDTO(rs.getInt("idUsuarios"), rs.getString("Distribuidor"));
                    ProductoDTO pro = new ProductoDTO(rs.getInt("idProductos"), rs.getString("nombreProducto"));
                    SolicitudDistribuidorDTO solicitud = new SolicitudDistribuidorDTO(user, pro);
                    solicitud.setIdSolicitud(rs.getInt("idSolicitudDistribuidor"));
                    solicitud.setCantidadSolicitada(rs.getInt("cantidadSolicitada"));
                    solicitud.setFechaSolicitud(rs.getString("fechaSolicitud"));
                    solicitudes.add(solicitud);
                }
            }
        } catch (SQLException sqle) {
            msgSalida = "Mira lo que ocurrio! " + sqle.getMessage() + " y " + sqle.getSQLState();
        }
        return solicitudes;
    }

    public SolicitudDistribuidorDTO byIdRequest(int id, Connection cnn) {
        SolicitudDistribuidorDTO solicitud = null;
        try {
            String querrySolicitudesDistribuidor = " SELECT idSolicitudDistribuidor, idUsuarios,"
                    + " concat(nombres, ' ', apellidos) AS Distribuidor, idProductos, nombreProducto,"
                    + " cantidadSolicitada, fechaSolicitud FROM solicituddistribuidor s"
                    + " INNER JOIN productos p ON (s.productosId = p.idProductos)"
                    + " INNER JOIN usuarios u ON (s.distribuidorId = u.idUsuarios)"
                    + " WHERE  idSolicitudDistribuidor = ?;";
            pstmt = cnn.prepareStatement(querrySolicitudesDistribuidor);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UsuariosDTO user = new UsuariosDTO(rs.getInt("idUsuarios"), rs.getString("Distribuidor"));
                    ProductoDTO pro = new ProductoDTO(rs.getInt("idProductos"), rs.getString("nombreProducto"));
                    solicitud = new SolicitudDistribuidorDTO(user, pro);
                    solicitud.setIdSolicitud(rs.getInt("idSolicitudDistribuidor"));
                    solicitud.setCantidadSolicitada(rs.getInt("cantidadSolicitada"));
                    solicitud.setFechaSolicitud(rs.getString("fechaSolicitud"));
                }
            }

        } catch (SQLException sqle) {
            msgSalida = "Mira lo que ocurrio! " + sqle.getMessage() + " y " + sqle.getSQLState();
        }
        return solicitud;
    }

    public String eliminarSolicitud(int id, Connection cnn) {
        try {
            pstmt = cnn.prepareStatement("DELETE FROM solicituddistribuidor WHERE idSolicitudDistribuidor = ?;");
            pstmt.setInt(1, id);
            resultado = pstmt.executeUpdate();

            if (resultado != 0) {
                msgSalida = "Registro " + resultado + " eliminado. Exitosamente";
            }
        } catch (SQLException sqle) {
            msgSalida = "Ocurrio esta excepción " + sqle.getMessage();
        }
        return msgSalida;
    }

    public String modificarSolicitudDistribuidor(SolicitudDistribuidorDTO solicitud, Connection cnn) {
        try {
            String querryUpdateSolicitud = "UPDATE `solicituddistribuidor` SET fechaEntregaInterna = ? "
                    + " WHERE `idSolicitudDistribuidor` = ?;";
            pstmt = cnn.prepareStatement(querryUpdateSolicitud);
            pstmt.setString(1, solicitud.getFechaEntregaInterna());
            pstmt.setInt(2, solicitud.getIdSolicitud());
            resultado = pstmt.executeUpdate();

            if (resultado != 0) {
                msgSalida = "ok";
            } else {
                msgSalida = "no";
            }
        } catch (SQLException sqle) {
            msgSalida = "Ha ocurrido lo siguiente... " + sqle.getMessage();
        }
        return msgSalida;
    }
}
