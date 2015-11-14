/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.CategoriaDTO;
import dtos.EstadoPedidoOfertaDTO;
import dtos.OfertasDTO;
import dtos.PedidoSobreOfertaDTO;
import dtos.ProductoDTO;
import dtos.ProductosAsociadosUsuariosDTO;
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
public class PedidoSobreOfertaDAO {

    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private Connection cnn = null;
    private CallableStatement cllstmt = null;

    public String insertarPedidoSobreOferta(int cantidadPedida, int idOferta, String fechaSolicitud, int idUser, Connection cnn) {
        this.cnn = cnn;
        int salida = 0;
        String msgSalida = "";
        try {
            String procedureOrder = "{call ps_registrarPedidov1 (?,?,?,?,?)}";
            cllstmt = cnn.prepareCall(procedureOrder);
            cllstmt.setInt(1, cantidadPedida);
            cllstmt.setInt(2, idOferta);
            cllstmt.setString(3, fechaSolicitud);
            cllstmt.setInt(4, idUser);
            cllstmt.registerOutParameter(5, java.sql.Types.INTEGER);
            cllstmt.execute();
            salida = cllstmt.getInt(5);

            if (salida == -1) {
                msgSalida = "no";
            } else if (salida == 1) {
                msgSalida = "ok";
            } else {
                msgSalida = "Something was wrong!!";
            }
        } catch (SQLException sqle) {
            msgSalida = "Pilas! Ocurrio la siguiente excepción " + sqle.getMessage();
        }
        return msgSalida;
    }

    public int calcularCantidad(int idOferta, Connection con) {
        this.cnn = con;
        int res = 0;
        try {
            pstmt = cnn.prepareStatement("select cantidad from ofertas where idOfertas = ?;");
            pstmt.setInt(1, idOferta);
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

    public List<PedidoSobreOfertaDTO> listarMisPedidosSobreUnaOferta(int idUser, Connection cn) {
        this.cnn = cn;
        List<PedidoSobreOfertaDTO> pedidos = new LinkedList();
        try {
            String misPedidosSobreUnaOfertas = "select usuariosId, concat(nombres,' ',apellidos) as Productor, "
                    + " nombreProducto, cantidadSolicitada, fechaSolicitada, usuarioSolicitadoId, idProductos "
                    + " from pedidosofertas "
                    + " inner join ofertas on pedidosofertas.ofertasId = ofertas.idOfertas "
                    + " inner join productosasociadosusuarios on ofertas.productosAsociadosUsuariosId = productosasociadosusuarios.idProductosAsociadosUsuarios "
                    + " inner join usuarios on productosasociadosusuarios.usuariosId = usuarios.idUsuarios "
                    + " inner join productos on productosasociadosusuarios.productosId = productos.idProductos "
                    + " where usuarioSolicitadoId = ?"
                    + " and if(fechaSolicitada < current_date(),1,0) = 0"
                    + " order by fechaSolicitada asc;";
            pstmt = cnn.prepareStatement(misPedidosSobreUnaOfertas);
            pstmt.setInt(1, idUser);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    UsuariosDTO user = new UsuariosDTO(rs.getInt("usuariosId"), rs.getString("Productor"));
                    ProductoDTO product = new ProductoDTO(rs.getInt("idProductos"), rs.getString("nombreProducto"));
                    ProductosAsociadosUsuariosDTO proAso = new ProductosAsociadosUsuariosDTO(user, product);
                    OfertasDTO offer = new OfertasDTO(proAso);
                    PedidoSobreOfertaDTO pedido = new PedidoSobreOfertaDTO(offer, rs.getInt("cantidadSolicitada"), rs.getString("fechaSolicitada"), rs.getInt("usuarioSolicitadoId"));
                    pedidos.add(pedido);
                }
            }
        } catch (SQLException sqle) {
            System.out.println("Ha ocurrido la siguiente excepción: " + sqle.getMessage());
        }
        return pedidos;
    }

    public List<PedidoSobreOfertaDTO> consultarMisPedidosDeUnProducto(int idUser, Connection cn) {
        this.cnn = cn;
        List<PedidoSobreOfertaDTO> pedidosSobreUnaOferta = new LinkedList();
        try {
            String misPedidosSobreUnaOfertas = "select u.idusuarios, nombreCategoria, nombreProducto, "
                    + " cantidadSolicitada, fechaSolicitada, nombreestadopedidooferta, "
                    + " pau.usuariosId, concat_ws(' ',user.nombres,user.apellidos) as Productor, idofertas, "
                    + " precio "
                    + " from pedidosofertas po "
                    + " inner join estadospedidoosofertas epo on po.estadoPedidoOfertaId = epo.idEstadoPedidoOferta "
                    + " inner join usuarios u on po.usuariosolicitadoId = u.idusuarios "
                    + " inner join ofertas o on po.ofertasId = o.idOfertas "
                    + " inner join productosasociadosusuarios pau on o.productosAsociadosUsuariosId = pau.idProductosAsociadosUsuarios "
                    + " inner join usuarios user on pau.usuariosId = user.idUsuarios "
                    + " inner join productos p on pau.productosId = p.idProductos "
                    + " inner join categorias c on p.categoriasId = c.idCategorias "
                    + " where u.idUsuarios = ? "
                    + " order by fechaSolicitada asc;";
            pstmt = cnn.prepareStatement(misPedidosSobreUnaOfertas);
            pstmt.setInt(1, idUser);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    UsuariosDTO user = new UsuariosDTO();
                    user.setIdUsuarios(rs.getInt("u.idusuarios"));
                    
                    CategoriaDTO categoria = new CategoriaDTO();
                    categoria.setNombre(rs.getString("nombreCategoria"));
                    
                    ProductoDTO producto = new ProductoDTO(categoria);
                    producto.setNombre(rs.getString("nombreProducto"));
                    
                    UsuariosDTO userAso = new UsuariosDTO();
                    userAso.setIdUsuarios(rs.getInt("pau.usuariosId"));
                    userAso.setNombres(rs.getString("Productor"));
                    
                    OfertasDTO offer = new OfertasDTO(producto, userAso);
                    offer.setIdOfertas(rs.getInt("idofertas"));
                    offer.setPrecio(rs.getInt("precio"));
                    
                    EstadoPedidoOfertaDTO estadoPedidoSobreOferta = new EstadoPedidoOfertaDTO();
                    estadoPedidoSobreOferta.setNombreEstadoPedidoSobreOferta(rs.getString("nombreestadopedidooferta"));
                    
                    PedidoSobreOfertaDTO pedidoSobreOferta = new PedidoSobreOfertaDTO(offer, estadoPedidoSobreOferta, user);
                    pedidoSobreOferta.setCantidadSolicitada(rs.getInt("cantidadSolicitada"));
                    pedidoSobreOferta.setFechaSolicitud(rs.getString("fechaSolicitada"));
                    
                    pedidosSobreUnaOferta.add(pedidoSobreOferta);
                           
                }
            }
        } catch (SQLException sqle) {
            System.out.println("Ha ocurrido la siguiente excepción: " + sqle.getMessage());
        }
        return pedidosSobreUnaOferta;
    }

}
