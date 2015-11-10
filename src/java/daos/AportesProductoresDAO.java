/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.AportesProductoresDTO;
import dtos.CategoriaDTO;
import dtos.ProductoDTO;
import dtos.ProductosAsociadosUsuariosDTO;
import dtos.SolicitudDistribuidorDTO;
import dtos.UsuariosDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Mona
 */
public class AportesProductoresDAO {

    private static PreparedStatement pstmt = null;
    private static CallableStatement cllstmt = null;
    private static Connection cnn = null;
    private static ResultSet rs = null;

    public LinkedList<SolicitudDistribuidorDTO> listarSolicitudesDeAsociacion(Connection cnn) {
        LinkedList<SolicitudDistribuidorDTO> solicitudes = new LinkedList();
        this.cnn = cnn;
        try {
            String querrySolicitudesDistribuidor = " select idSolicitudDistribuidor, idUsuarios, "
                    + " concat(nombres,' ',apellidos) as Distribuidor, idProductos, "
                    + " nombreProducto, cantidadSolicitada, fechaEntregaInterna "
                    + " from solicituddistribuidor  s "
                    + " inner join productos p on "
                    + " p.idProductos = s.productosId "
                    + " inner join usuarios u on  "
                    + " u.idUsuarios = s.distribuidorId "
                    + " WHERE fechaEntregaInterna IS NOT NULL "
                    + " AND estadosolicituddistribuidorid = 5;";
            pstmt = cnn.prepareStatement(querrySolicitudesDistribuidor);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UsuariosDTO user = new UsuariosDTO(rs.getInt("idUsuarios"), rs.getString("Distribuidor"));
                    ProductoDTO pro = new ProductoDTO(rs.getInt("idProductos"), rs.getString("nombreProducto"));
                    SolicitudDistribuidorDTO solicitud = new SolicitudDistribuidorDTO(user, pro);
                    solicitud.setIdSolicitud(rs.getInt("idSolicitudDistribuidor"));
                    solicitud.setCantidadSolicitada(rs.getInt("cantidadSolicitada"));
                    solicitud.setFechaEntregaInterna(rs.getString("fechaEntregaInterna"));
                    solicitudes.add(solicitud);
                }
            } else {
                System.out.println("No se encontraron registros...");
            }
        } catch (SQLException sqle) {
            System.out.println("Mira lo que ocurrio! " + sqle.getMessage() + " y " + sqle.getSQLState());
        }
        return solicitudes;
    }

    public SolicitudDistribuidorDTO byIdForAssociation(int id, Connection cnn) {
        SolicitudDistribuidorDTO solicitud = null;
        this.cnn = cnn;
        try {
            String querrySolicitudesDistribuidor = " SELECT idSolicitudDistribuidor, idUsuarios,"
                    + " concat(nombres, ' ', apellidos) AS Distribuidor, idProductos, nombreProducto,"
                    + " cantidadSolicitada, fechaEntregaInterna "
                    + " FROM solicituddistribuidor s"
                    + " INNER JOIN productos p ON (s.productosId = p.idProductos)"
                    + " INNER JOIN usuarios u ON (s.distribuidorId = u.idUsuarios)"
                    + " WHERE  idSolicitudDistribuidor = ?;";
            pstmt = cnn.prepareStatement(querrySolicitudesDistribuidor);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UsuariosDTO user = new UsuariosDTO();
                    user.setIdUsuarios(rs.getInt("idUsuarios"));
                    user.setNombres(rs.getString("Distribuidor"));
                    ProductoDTO pro = new ProductoDTO();
                    pro.setIdProductos(rs.getInt("idProductos"));
                    pro.setNombre(rs.getString("nombreProducto"));
                    solicitud = new SolicitudDistribuidorDTO(user, pro);
                    solicitud.setIdSolicitud(rs.getInt("idSolicitudDistribuidor"));
                    solicitud.setCantidadSolicitada(rs.getInt("cantidadSolicitada"));
                    solicitud.setFechaEntregaInterna(rs.getString("fechaEntregaInterna"));
                }
            } else {
                System.out.println("No se encontraron registros...");
            }

        } catch (SQLException sqle) {
            System.out.println("Mira lo que ocurrio! " + sqle.getMessage() + " y " + sqle.getSQLState());
        }
        return solicitud;
    }

    public String aplicarSolicitudAsociacion(String fechaEntrega, int cantidadAportar, int proAsoId,
            int idSolicitud, Connection cnn) {
        this.cnn = cnn;
        int sal = 0;
        String msgSalida = "";
        try {
            String procedureOrder = "{call ps_aplicarSolicitudV5(?,?,?,?,?)}";
            cllstmt = cnn.prepareCall(procedureOrder);
            cllstmt.setString(1, fechaEntrega);
            cllstmt.setInt(2, cantidadAportar);
            cllstmt.setInt(3, proAsoId);
            cllstmt.setInt(4, idSolicitud);
            cllstmt.registerOutParameter(5, java.sql.Types.INTEGER);
            cllstmt.execute();
            sal = cllstmt.getInt(5);

            if (sal == 1) {
                msgSalida = "Bien. Revisa la base de datos. Ocurrio el primer procedimiento";
            } else if (sal == 2) {
                msgSalida = "Bien. Ocurrio el segundo procedimiento";
            } else if (sal == -1) {
                msgSalida = "No ha tenido modificación la base de datos";
            } else {
                msgSalida = "something was wrong!!!";
            }
        } catch (SQLException sqle) {
            msgSalida = "Pilas! Ocurrio la siguiente excepción " + sqle.getMessage();
        }
        return msgSalida;
    }

    public int calcularCantidad(int idSolicitud, Connection con) {
        this.cnn = con;

        int res = 0;
        try {
            pstmt = cnn.prepareStatement("select cantidadSolicitada from solicituddistribuidor where idSolicitudDistribuidor = ?;");
            pstmt.setInt(1, idSolicitud);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    res = rs.getInt("cantidadSolicitada");
                }
            }

        } catch (SQLException sql) {
            System.out.println("Mire: " + sql.getMessage());
        }
        return res;
    }

    public String registrarAporte(AportesProductoresDTO aporte, Connection cnn) throws SQLException {
        this.cnn = cnn;
        String msgSalida;
        int rtdo;
        pstmt = cnn.prepareStatement("insert into aportesproductores values (null, ?, ?, ?, ?, date(now()),1);");
        pstmt.setString(1, aporte.getFechaEntrega());
        pstmt.setInt(2, aporte.getCantidad());
        pstmt.setInt(3, aporte.getIdAso());
        pstmt.setInt(4, aporte.getSolicitudId());
        rtdo = pstmt.executeUpdate();
        if (rtdo > 0) {
            msgSalida = "ok";
        } else {
            msgSalida = "no";
        }
        return msgSalida;
    }

    public List<AportesProductoresDTO> consultarMisParticipaciones(int id, Connection con) {
        this.cnn = con;
        List<AportesProductoresDTO> misAportes = new LinkedList();
        try {
            String querryMisAportes = " select usuariosId, idCategorias, nombrecategoria, productosasociadosusuarios.productosId, nombreProducto, cantidad, unidad, fechaEntrega, "
                    + " idAportesProductores, cantidadSolicitada, fechaSolicitud, idSolicitudDistribuidor "
                    + " from categorias "
                    + " inner join productos on categorias.idCategorias =  productos.categoriasId "
                    + " inner join productosasociadosusuarios on productos.idProductos = productosasociadosusuarios.productosId "
                    + " inner join usuarios on usuarios.idUsuarios = productosasociadosusuarios.usuariosId "
                    + " inner join aportesproductores on productosasociadosusuarios.idProductosAsociadosUsuarios = aportesproductores.idAportesProductores "
                    + " inner join solicituddistribuidor on aportesproductores.solicitudDistribuidorId = solicituddistribuidor.idSolicitudDistribuidor "
                    + " where usuariosId = ?;";
            pstmt = cnn.prepareStatement(querryMisAportes);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UsuariosDTO user = new UsuariosDTO();
                    user.setIdUsuarios(rs.getInt("usuariosId"));
                    CategoriaDTO categoria = new CategoriaDTO(rs.getInt("idCategorias"), rs.getString("nombrecategoria"));
                    ProductoDTO pro = new ProductoDTO(categoria);
                    pro.setIdProductos(rs.getInt("productosId"));
                    pro.setNombre(rs.getString("nombreProducto"));
                    pro.setUnidad(rs.getString("unidad"));
                    ProductosAsociadosUsuariosDTO proAso = new ProductosAsociadosUsuariosDTO(user, pro);
                    SolicitudDistribuidorDTO solicitud = new SolicitudDistribuidorDTO();
                    solicitud.setIdSolicitud(rs.getInt("idSolicitudDistribuidor"));
                    solicitud.setCantidadSolicitada(rs.getInt("cantidadSolicitada"));
                    solicitud.setFechaSolicitud(rs.getString("fechaSolicitud"));
                    AportesProductoresDTO aport = new AportesProductoresDTO(proAso,solicitud);
                    aport.setIdAporteProductor(rs.getInt("idAportesProductores"));
                    aport.setCantidad(rs.getInt("cantidad"));
                    aport.setFechaEntrega(rs.getString("fechaEntrega"));
                    misAportes.add(aport);
                }
            } else {
                System.out.println("No se encontraron registros...");
            }

        } catch (SQLException sqle) {
            System.out.println("Mira lo que ocurrio! " + sqle.getMessage() + " y " + sqle.getSQLState());
        }
        return misAportes;
    }
    
    public String modificarEstadoACancelado(int idAporte, AportesProductoresDTO aporte, Connection cn) throws SQLException {
        this.cnn = cn;
        String msgSalida;
        int rtdo;
        pstmt = cnn.prepareStatement("update aportesproductores set estadoAporteProductorId = 2, "
                + " novedad = ? where idAportesProductores = ?;");
        pstmt.setString(1, aporte.getNovedad());
        pstmt.setInt(2, idAporte);
        rtdo = pstmt.executeUpdate();
        if (rtdo > 0) {
            msgSalida = "ok";
        } else {
            msgSalida = "no";
        }
        return msgSalida;
    }
    
    public int consultarCantidadSolicitada(int idSolicitud, Connection cn) {
        this.cnn = cn;
        int res = 0;
        try {
            pstmt = cnn.prepareStatement("select cantidadSolicitada from solicituddistribuidor where idSolicitudDistribuidor = ?;");
            pstmt.setInt(1, idSolicitud);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    res = rs.getInt("cantidadSolicitada");
                }
            }

        } catch (SQLException sql) {
            System.out.println("Mire: " + sql.getMessage());
        }
        return res;
    }
    
    public int consultarCantidadAportada(int idAporte, Connection cn) {
        this.cnn = cn;
        int res = 0;
        try {
            pstmt = cnn.prepareStatement("select cantidad from aportesproductores where idAportesProductores = ?;");
            pstmt.setInt(1, idAporte);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    res = rs.getInt("cantidad");
                }
            }

        } catch (SQLException sql) {
            System.out.println("Mire: " + sql.getMessage());
        }
        return res;
    }
}
