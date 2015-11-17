/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.CategoriaDTO;
import dtos.DespachosPedidosDTO;
import dtos.ProductoDTO;
import dtos.SolicitudDistribuidorDTO;
import dtos.UsuariosDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.CallableStatement;
import java.sql.Date;
import utilities.MyException;

/**
 *
 * @author krito
 */
public class DespachosPedidosDAO {

    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;
    private static Connection cnn = null;
    private static CallableStatement cllstmt = null;

    public String insertarDespacho(DespachosPedidosDTO nuevoDespacho, Connection cnn) {
        this.cnn = cnn;
        String msgSalida = "";
        int resultado = 0;
        try {
            pstmt = cnn.prepareStatement("INSERT INTO despachospedidos VALUES (null, ?,?,?,?,?,8);");
            pstmt.setString(1, nuevoDespacho.getDireccionDespacho());
            pstmt.setString(2, nuevoDespacho.getFechaDespacho());
            pstmt.setString(3, nuevoDespacho.getObservaciones());
            pstmt.setInt(4, nuevoDespacho.getUsuariosId());
            pstmt.setInt(5, nuevoDespacho.getSolicitudId());
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

    public LinkedList<SolicitudDistribuidorDTO> mostrarDespachosPendientes(Connection cnn) throws MyException {
        LinkedList<SolicitudDistribuidorDTO> solicitudes = new LinkedList();
        try {
            String querryAllDespachos = " select idSolicitudDistribuidor, nombreCategoria, "
                    + " nombreProducto, cantidadSolicitada, unidad, precioSolicitud, "
                    + " observacion, fechaSolicitud, fechaEntregaInterna, "
                    + " concat_ws(' ',u.nombres, u.apellidos) as Distribuidor, "
                    + " u.telefono, u.direccion "
                    + " from solicituddistribuidor s "
                    + " inner join usuarios u on s.distribuidorId = u.idUsuarios "
                    + " inner join productos p on s.productosId = p.idProductos "
                    + " inner join categorias c on p.categoriasId = c.idCategorias "
                    + " inner join estadossolicitudesdistribuidores esd "
                    + " on s.estadoSolicitudDistribuidorId = esd.idEstadoSolicitudDistribuidor "
                    + " where idEstadoSolicitudDistribuidor = 3 "
                    + " or (fechaEntregaInterna <= current_date() "
                    + " AND current_date() <= fechaSolicitud) "
                    + " order by fechaSolicitud asc;";
            pstmt = cnn.prepareStatement(querryAllDespachos);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    CategoriaDTO categoria = new CategoriaDTO();
                    categoria.setNombre(rs.getString("nombreCategoria"));
                    
                    ProductoDTO producto = new ProductoDTO(categoria);
                    producto.setNombre(rs.getString("nombreProducto"));
                    producto.setUnidad(rs.getString("unidad"));
                    
                    UsuariosDTO usuario = new UsuariosDTO();
                    usuario.setNombres(rs.getString("Distribuidor"));
                    usuario.setTelefono(rs.getString("u.telefono"));
                    usuario.setDireccion(rs.getString("u.direccion"));
                    
                    SolicitudDistribuidorDTO solicitud = new SolicitudDistribuidorDTO(usuario, producto);
                    solicitud.setIdSolicitud(rs.getInt("idSolicitudDistribuidor"));
                    solicitud.setCantidadSolicitada(rs.getInt("cantidadSolicitada"));
                    solicitud.setPrecioSolicitud(rs.getInt("precioSolicitud"));
                    solicitud.setObservacion(rs.getString("observacion"));
                    solicitud.setFechaSolicitud(rs.getString("fechaSolicitud"));
                    solicitud.setFechaEntregaInterna(rs.getString("fechaEntregaInterna"));
                    solicitudes.add(solicitud);
                }
            } else {
                throw new MyException("No hay registros en la base de datos");
            }
        } catch (SQLException sqle) {
            throw new MyException("Ha pasado esta excepción!! " + sqle.getMessage());
        } finally {
            try {
                pstmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DespachosPedidosDAO.class.getName()).log(Level.SEVERE, null, ex); // se deja por defecto en este caso
            }
        }
        return solicitudes;
    }

    public SolicitudDistribuidorDTO consultarSolicitud(int id, Connection cnn) {
        SolicitudDistribuidorDTO solicitud = null;
        try {
            String querrySolicitudesDistribuidor = " select idSolicitudDistribuidor, idUsuarios, "
                    + " CONCAT(u.nombres,' ',u.apellidos) as Solicitante, "
                    + " direccion,idProductos, nombreProducto, correo,"
                    + " fechaSolicitud from usuarios u inner join "
                    + " solicituddistribuidor s on u.idUsuarios = s.distribuidorId "
                    + " inner join productos p on s.productosId = p.idProductos "
                    + " WHERE  idSolicitudDistribuidor = ?;";
            pstmt = cnn.prepareStatement(querrySolicitudesDistribuidor);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UsuariosDTO user = new UsuariosDTO(rs.getInt("idUsuarios"), rs.getString("Solicitante"), rs.getString("direccion"));
                    user.setCorreo(rs.getString("correo"));
                    ProductoDTO pro = new ProductoDTO(rs.getInt("idProductos"), rs.getString("nombreProducto"));
                    solicitud = new SolicitudDistribuidorDTO(user, pro);
                    solicitud.setIdSolicitud(rs.getInt("idSolicitudDistribuidor"));
                    solicitud.setFechaSolicitud(rs.getString("fechaSolicitud"));
                }
            } else {
                System.out.println("No hay registros...");
            }

        } catch (SQLException sqle) {
            System.out.println("Mira lo que ocurrio! " + sqle.getMessage() + " y " + sqle.getSQLState());
        }
        return solicitud;
    }

    public boolean consultarFecha(Date fechaInicial, Date fechaFinal, Connection con) {
        this.cnn = con;
        boolean res = false;
        String fec = "";
        try {
            String fechaActual = "select CURRENT_DATE() as fecha;";
            pstmt = cnn.prepareStatement(fechaActual);

//            String fechaSolicitud = "select fechaSolicitada "
//                    + " from pedidosofertas "
//                    + " where idPedidosOfertas = ?;";
            //pstmt.setString(1, fechaSolicitud);
            //pstmt.setString(parameterIndex, fec);
            fec = rs.getString("fecha");

            if (fechaActual.equals(fec)) {
                res = true;
            } else {
                res = false;
            }

        } catch (SQLException sql) {
            System.out.println("Mire: " + sql.getMessage());
        }
        return res;
    }

    public String cambiarEstadoSolicitud(int idSolicitud, Connection con) {
        this.cnn = con;
        int rtdo = 0;
        String mensaje = "";
        try {
            String sqlInsert = "update solicituddistribuidor set estadoSolicitudDistribuidorId = 4 where idSolicitudDistribuidor = ?;";
            pstmt = cnn.prepareStatement(sqlInsert);

            pstmt.setInt(1, idSolicitud);
            rtdo = pstmt.executeUpdate();
            if (rtdo != 0) {
                mensaje = "ok";
            } else {
                mensaje = "no";
            }
        } catch (SQLException sqle) {
            mensaje = "Error, detalle " + sqle.getMessage();
        }
        return mensaje;
    }

    public String cambiarEstadoAProductores(int idSolicitud, Connection con) {
        this.cnn = con;
        int rtdo = 0;
        String mensaje = "";
        try {
            String sqlInsert = "update solicituddistribuidor set estadoSolicitudDistribuidorId = 5 where idSolicitudDistribuidor=?;";
            pstmt = cnn.prepareStatement(sqlInsert);

            pstmt.setInt(1, idSolicitud);
            rtdo = pstmt.executeUpdate();
            if (rtdo != 0) {
                mensaje = "ok";
            } else {
                mensaje = "no";
            }
        } catch (SQLException sqle) {
            mensaje = "Error, detalle " + sqle.getMessage();
        }
        return mensaje;
    }

    public int validarFecha(String fechaIngresar, Connection con) {
        this.cnn = con;
        int salida = 0;
        int msgSalida = 0;
        try {
            String procedureOrder = "{call ps_validarFecha(?,?)}";
            cllstmt = cnn.prepareCall(procedureOrder);
            cllstmt.setString(1, fechaIngresar);
            cllstmt.registerOutParameter(2, java.sql.Types.INTEGER);
            cllstmt.execute();
            salida = cllstmt.getInt(2);

            if (salida == 1) {
                msgSalida = 1;
            } else if (salida == 0) {
                msgSalida = 0;
            } else {
                msgSalida = 2;
            }
        } catch (SQLException sqle) {
            System.out.println("Pilas! Ocurrio la siguiente excepción " + sqle.getMessage());
        }
        return msgSalida;
    }

    public int validarFechaDespacho(String fechaIngresar, int idSolicitud, Connection con) {
        this.cnn = con;
        int salida = 0;
        int msgSalida = 0;
        try {
            String procedureOrder = "{call ps_validarFechaV1(?,?,?)}";
            cllstmt = cnn.prepareCall(procedureOrder);
            cllstmt.setString(1, fechaIngresar);
            cllstmt.setInt(2, idSolicitud);
            cllstmt.registerOutParameter(3, java.sql.Types.INTEGER);
            cllstmt.execute();
            salida = cllstmt.getInt(3);

            if (salida == 1) {
                msgSalida = 1;
            } else if (salida == 0) {
                msgSalida = 0;
            } else {
                msgSalida = 2;
            }
        } catch (SQLException sqle) {
            System.out.println("Pilas! Ocurrio la siguiente excepción " + sqle.getMessage());
        }
        return msgSalida;
    }

    public int validarFechaAporte(String fechaIngresar, int idSolicitud, Connection con) {
        this.cnn = con;
        int salida = 0;
        int msgSalida = 0;
        try {
            String procedureOrder = "{call ps_validarFechaV2(?,?,?)}";
            cllstmt = cnn.prepareCall(procedureOrder);
            cllstmt.setString(1, fechaIngresar);
            cllstmt.setInt(2, idSolicitud);
            cllstmt.registerOutParameter(3, java.sql.Types.INTEGER);
            cllstmt.execute();
            salida = cllstmt.getInt(3);

            if (salida == 1) {
                msgSalida = 1;
            } else if (salida == 0) {
                msgSalida = 0;
            } else {
                msgSalida = 2;
            }
        } catch (SQLException sqle) {
            System.out.println("Pilas! Ocurrio la siguiente excepción " + sqle.getMessage());
        }
        return msgSalida;
    }

}
