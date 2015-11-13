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
import java.util.List;

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
            pstmt = cnn.prepareStatement("INSERT INTO solicituddistribuidor VALUES (null,?,current_date(),?,null,1,?,?,?,?);");
            pstmt.setInt(1, solicitud.getCantidadSolicitada());
            pstmt.setString(2, solicitud.getFechaSolicitud());
            //pstmt.setString(3, solicitud.getFechaEntregaInterna());
            pstmt.setInt(3, solicitud.getProductoId());
            pstmt.setInt(4, solicitud.getDistribuidorId());
            pstmt.setInt(5, solicitud.getCantidadSolicitudFinal());
            pstmt.setString(6, solicitud.getObservacion());
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
            String querrySolicitudesDistribuidor = "select idSolicitudDistribuidor, idUsuarios, concat(nombres,' ',apellidos) as Distribuidor, "
                    + " idProductos, nombreProducto, cantidadSolicitada, fechaSolicitud "
                    + " from solicituddistribuidor s "
                    + " inner join productos p on p.idProductos = s.productosId "
                    + " inner join usuarios u on u.idUsuarios = s.distribuidorId "
                    + " where estadoSolicitudDistribuidorId = 1;";
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

    public List<SolicitudDistribuidorDTO> listarMisPedidosDeUnaAsociacion(int idUsuario, Connection cnn) {
        List<SolicitudDistribuidorDTO> solicitudes = new LinkedList();
        try {
            String querrySolicitudesDistribuidor = "select idUsuarios, nombreProducto, cantidadSolicitada,idSolicitudDistribuidor, fechaSolicitud, idProductos "
                    + " from usuarios "
                    + " inner join solicituddistribuidor on solicituddistribuidor.distribuidorId = usuarios.idUsuarios "
                    + " inner join productos on solicituddistribuidor.productosId = productos.idProductos "
                    + " where idusuarios = ?;";
            pstmt = cnn.prepareStatement(querrySolicitudesDistribuidor);
            pstmt.setInt(1, idUsuario);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UsuariosDTO user = new UsuariosDTO();
                    user.setIdUsuarios(rs.getInt("idUsuarios"));
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
    
    public int modificarCantidadSolicitud(int cantidadFinal, int idSolicitud, Connection cn) throws SQLException {
        this.cnn = cn;
        int res = 0;
        int rtdo;
        pstmt = cnn.prepareStatement("update solicituddistribuidor set cantidadSolicitada = ? where idSolicitudDistribuidor = ?;");
        pstmt.setInt(1, cantidadFinal);
        pstmt.setInt(2, idSolicitud);
        rtdo = pstmt.executeUpdate();
        if (rtdo > 0) {
            return res = 1;
        } else {
            return res = 0;
        }
    }
}
