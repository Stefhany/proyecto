/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.CategoriaDTO;
import dtos.OfertasDTO;
import dtos.ProductoDTO;
import dtos.ProductosAsociadosUsuariosDTO;
import dtos.UsuariosDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import utilities.MyException;

/**
 *
 * @author krito
 */
public class OfertasDAO {

    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private Connection cnn = null;

    /**
     * Insertar oferta nueva version
     *
     * @param nuevaOferta
     * @return
     */
    public String insertarOferta(OfertasDTO nuevaOferta, Connection cnn) {
        this.cnn = cnn;
        int rtdo = 0;
        String msgSalida = "";
        try {
            pstmt = cnn.prepareStatement("INSERT INTO ofertas VALUES (null, ?,?,?,current_date(), date_add(curdate(), interval 15 day),1,?);");
            pstmt.setInt(1, nuevaOferta.getProductosAsociadosUsuariosId());
            pstmt.setFloat(2, nuevaOferta.getPrecio());
            pstmt.setInt(3, nuevaOferta.getCantidad());
            pstmt.setInt(4, nuevaOferta.getCantidadFinal());
            rtdo = pstmt.executeUpdate();

            if (rtdo != 0) {
                msgSalida = "ok";
            } else {
                msgSalida = "no";
            }
        } catch (SQLException sqle) {
            msgSalida = "Ha ocurrido la siguiente exepción.. " + sqle.getMessage();

        }
        return msgSalida;
    }

    public String modificarOferta(OfertasDTO modOferta, Connection cnn) {
        this.cnn = cnn;
        int rtdo = 0;
        String msgSalida = "";
        try {
            pstmt = cnn.prepareStatement("UPDATE ofertas SET cantidad = ? WHERE idOfertas = ?;");
            pstmt.setInt(1, modOferta.getCantidad());
            pstmt.setInt(2, modOferta.getIdOfertas());
            rtdo = pstmt.executeUpdate();

            if (rtdo != 0) {
                msgSalida = "La modificación " + rtdo + " se pudo realizar, exitosamente";
            } else {
                msgSalida = "No se pudo realizar la modificación";
            }
        } catch (SQLException sqle) {
            msgSalida = "Ha ocurrido lo siguiente... " + sqle.getMessage();
        }
        return msgSalida;
    }

    public String eliminarOferta(int id, Connection cnn) {
        this.cnn = cnn;
        int rtdo = 0;
        String msgSalida = "";
        try {
            pstmt = cnn.prepareStatement("DELETE FROM ofertas WHERE idOfertas = ?;");
            pstmt.setInt(1, id);
            rtdo = pstmt.executeUpdate();

            if (rtdo != 0) {
                msgSalida = "Registro " + rtdo + " eliminado. Exitosamente";
            } else {
                msgSalida = "No se pudo eliminar";
            }
        } catch (SQLException sqle) {
            msgSalida = "Ocurrio esta excepción " + sqle.getMessage();
        }
        return msgSalida;
    }

    public List<ProductoDTO> listarProductosAsociado(int id, Connection cnn) {
        this.cnn = cnn;
        ArrayList<ProductoDTO> productosAso = new ArrayList();
        try {
            String queryAllProductsAso = " SELECT idUsuarios as 'Id_productor', paso.idProductosAsociadosUsuarios as 'Id_productoAsociado', p.nombreProducto as 'Nombre' "
                    + " FROM usuarios u "
                    + " INNER JOIN productosasociadosusuarios paso on u.idUsuarios = paso.usuariosId "
                    + " INNER JOIN productos p on paso.productosId = p.idProductos "
                    + " WHERE u.idUsuarios = ?;";
            pstmt = cnn.prepareStatement(queryAllProductsAso);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    ProductoDTO pdto = new ProductoDTO();
                    pdto.setIdProductos(rs.getInt("Id_productoAsociado"));
                    pdto.setNombre(rs.getString("Nombre"));
                    productosAso.add(pdto);
                }
            }

        } catch (SQLException sqle) {
            System.out.println("Ha ocurrido esto" + sqle.getSQLState() + " - " + sqle.getMessage());
        }
        return productosAso;
    }

    public List<OfertasDTO> consultarOfertas(Connection cnn) {
        this.cnn = cnn;
        LinkedList<OfertasDTO> ofer = new LinkedList();

        try {
            String queryAllOfertas = " select idOfertas,idCategorias, nombreCategoria, concat(u.nombres,' ',u.apellidos) as NombreProductor, idProductos"
                    + " as idPro, nombreProducto, unidad, cantidad, precio "
                    + " from ofertas o"
                    + " inner join productosasociadosusuarios pro on"
                    + " o.productosAsociadosUsuariosId = pro.idProductosAsociadosUsuarios"
                    + " inner join productos p on pro.productosId = p.idProductos"
                    + " inner join usuarios u on pro.usuariosId = u.idUsuarios"
                    + " inner join categorias c on p.categoriasId = c.idCategorias"
                    + " where o.Estado = 1"
                    + " order by idOfertas asc;";
            pstmt = cnn.prepareStatement(queryAllOfertas);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CategoriaDTO cdto = new CategoriaDTO(rs.getInt("idCategorias"), rs.getString("nombreCategoria"));
                UsuariosDTO udto = new UsuariosDTO(rs.getString("NombreProductor"));
                ProductoDTO pdto = new ProductoDTO(rs.getInt("idPro"), rs.getString("nombreProducto"), rs.getString("unidad"), cdto);
                OfertasDTO ofdto = new OfertasDTO(pdto, udto);
                ofdto.setIdOfertas(rs.getInt("idOfertas"));
                ofdto.setCantidad(rs.getInt("cantidad"));
                ofdto.setPrecio(rs.getFloat("precio"));
                ofer.add(ofdto);
            }

        } catch (SQLException ex) {
            System.out.println("Ha ocurrido esto" + ex.getSQLState() + " - " + ex.getMessage());
        }
        return ofer;
    }

    public OfertasDTO consultByOffer(int id, Connection cnn) throws MyException {
        this.cnn = cnn;
        OfertasDTO onlyoffer = null;

        try {
            String querryByIdOffer = " select idCategorias, nombreCategoria, idOfertas,idProductosAsociadosUsuarios, "
                    + " idUsuarios, concat(u.nombres,' ',u.apellidos) as Productor, correo as correoProductor,"
                    + " telefono as telefonoProductor, "
                    + " idProductos, nombreProducto, cantidad, precio, fechafin"
                    + " from ofertas as o "
                    + " inner join productosasociadosusuarios as apor on "
                    + " o.productosAsociadosUsuariosId = apor.idProductosAsociadosUsuarios "
                    + " inner join usuarios as u on apor.usuariosId = u.idUsuarios "
                    + " inner join productos as p on apor.productosId = p.idProductos "
                    + " inner join categorias as c on p.categoriasId = c.idCategorias "
                    + " where idOfertas = ?";
            pstmt = cnn.prepareStatement(querryByIdOffer);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UsuariosDTO udto = new UsuariosDTO();
                    udto.setIdUsuarios(rs.getInt("idUsuarios"));
                    udto.setNombres(rs.getString("Productor"));
                    udto.setCorreo(rs.getString("correoProductor"));
                    udto.setTelefono(rs.getString("telefonoProductor"));
                    CategoriaDTO cdto = new CategoriaDTO();
                    cdto.setIdCategoria(rs.getInt("idCategorias"));
                    cdto.setNombre(rs.getString("nombreCategoria"));
                    ProductoDTO pdto = new ProductoDTO(cdto);
                    pdto.setIdProductos(rs.getInt("idProductos"));
                    pdto.setNombre(rs.getString("nombreProducto"));
                    ProductosAsociadosUsuariosDTO proAso = new ProductosAsociadosUsuariosDTO(udto, pdto);
                    proAso.setIdProductosAsociadosUsuarios(rs.getInt("idProductosAsociadosUsuarios"));
                    onlyoffer = new OfertasDTO(proAso);
                    onlyoffer.setIdOfertas(rs.getInt("idOfertas"));
                    onlyoffer.setCantidad(rs.getInt("cantidad"));
                    onlyoffer.setPrecio(rs.getInt("precio"));
                    onlyoffer.setFechaFin(rs.getString("fechafin"));
                }
            } else {
                throw new MyException("No se encuentran registros por este id.");
            }
        } catch (SQLException sqle) {
            throw new MyException("Ups! Mira lo ocurrido... " + sqle.getMessage());
        }
        return onlyoffer;
    }

    public ArrayList<OfertasDTO> consultarMisOfertas(int idUser, Connection cnn) {
        this.cnn = cnn;
        ArrayList<OfertasDTO> ofertas = new ArrayList();
        try {
            pstmt = cnn.prepareStatement(" SELECT pro.idProductosAsociadosUsuarios as id, "
                    + " pro.usuariosId as idUsu,"
                    + " pro.productosId as idPro, u.*, p.*, c.*, o.*"
                    + " from categorias c"
                    + " inner join productos p on c.idCategorias = p.categoriasId"
                    + " inner join  productosasociadosusuarios  pro on"
                    + " pro.productosId = p.idProductos"
                    + " inner join usuarios u on pro.usuariosId = u.idUsuarios"
                    + " inner join ofertas  o on"
                    + " o.productosAsociadosUsuariosId = pro.idProductosAsociadosUsuarios"
                    + " where pro.usuariosId = ? and if(FechaFin > CURRENT_DATE(), 1, 0) = 1"
                    + " order by o.FechaFin asc;");
            pstmt.setInt(1, idUser);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    UsuariosDTO nUser = new UsuariosDTO();
                    nUser.setCedula(rs.getString("u.cedula"));
                    nUser.setNombres(rs.getString("u.nombres"));
                    CategoriaDTO c = new CategoriaDTO();
                    c.setIdCategoria(rs.getInt("c.idCategorias"));
                    c.setNombre(rs.getString("c.nombreCategoria"));
                    ProductoDTO nProduct = new ProductoDTO(c);
                    nProduct.setIdProductos(rs.getInt("p.idproductos"));
                    nProduct.setNombre(rs.getString("p.nombreproducto"));
                    ProductosAsociadosUsuariosDTO psodto = new ProductosAsociadosUsuariosDTO(nUser, nProduct);
                    psodto.setIdProductosAsociadosUsuarios(rs.getInt("id"));
                    OfertasDTO of = new OfertasDTO(psodto);
                    of.setCantidad(rs.getInt("o.cantidad"));
                    of.setPrecio(rs.getInt("o.precio"));
                    of.setFechaFin(rs.getString("o.fechafin"));
                    ofertas.add(of);
                }
            } else {
                System.out.println("No se encuetran registros de productores asociados.. ");
            }
        } catch (SQLException sqle) {
            System.out.println("Se ha producido esta excepción.. " + sqle.getMessage());
        }
        return ofertas;
    }

    public OfertasDTO consultMyOffer(int id, Connection cnn) throws MyException {
        this.cnn = cnn;
        OfertasDTO onlyoffer = null;
        try {
            String querryMyOffer = " SELECT pro.idProductosAsociadosUsuarios as id, pro.usuariosId as idUsu, "
                    + " pro.productosId as idPro, u.*, p.*, c.*, o.* "
                    + " from categorias c "
                    + " inner join productos p on c.idCategorias = p.categoriasId "
                    + " inner join  productosasociadosusuarios  pro on "
                    + " pro.productosId = p.idProductos "
                    + " inner join usuarios u on pro.usuariosId = u.idUsuarios "
                    + " inner join ofertas  o on "
                    + " o.productosAsociadosUsuariosId = pro.idProductosAsociadosUsuarios "
                    + " where pro.idProductosAsociadosUsuarios = ?;";
            pstmt = cnn.prepareStatement(querryMyOffer);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UsuariosDTO udto = new UsuariosDTO();
                    udto.setIdUsuarios(rs.getInt("idUsu"));
                    CategoriaDTO cdto = new CategoriaDTO(rs.getInt("idCategorias"), rs.getString("nombreCategoria"));
                    ProductoDTO pdto = new ProductoDTO(rs.getInt("idProductos"), rs.getString("nombreProducto"), rs.getString("unidad"), cdto);
                    ProductosAsociadosUsuariosDTO proAso = new ProductosAsociadosUsuariosDTO(udto, pdto);
                    proAso.setIdProductosAsociadosUsuarios(rs.getInt("id"));
                    onlyoffer = new OfertasDTO(proAso);
                    onlyoffer.setIdOfertas(rs.getInt("idOfertas"));
                    onlyoffer.setCantidad(rs.getInt("cantidad"));
                    onlyoffer.setPrecio(rs.getInt("precio"));
                }
            } else {
                throw new MyException("No se encuentran registros por este id.");
            }
        } catch (SQLException sqle) {
            throw new MyException("Ups! Mira lo ocurrido... " + sqle.getMessage());
        }
        return onlyoffer;
    }

    public String modificarMyOffer(OfertasDTO modOferta, Connection cnn) {
        this.cnn = cnn;
        int rtdo = 0;
        String msgSalida = "";
        try {
            pstmt = cnn.prepareStatement("UPDATE ofertas SET cantidad = ? WHERE idOfertas = ?;");
            pstmt.setInt(1, modOferta.getCantidad());
            pstmt.setInt(2, modOferta.getIdOfertas());
            rtdo = pstmt.executeUpdate();

            if (rtdo != 0) {
                msgSalida = "La modificación " + rtdo + " se pudo realizar, exitosamente";
            } else {
                msgSalida = "No se pudo realizar la modificación";
            }
        } catch (SQLException sqle) {
            msgSalida = "Ha ocurrido lo siguiente... " + sqle.getMessage();
        }
        return msgSalida;
    }

    public List<OfertasDTO> consultarTopOfertas(Connection cnn) {
        this.cnn = cnn;
        LinkedList<OfertasDTO> ofer = new LinkedList();

        try {
            String queryAllOfertas = " select idOfertas,idCategorias, concat(u.nombres,' ',u.apellidos) "
                    + " as NombreProductor, nombreCategoria, idProductos as idPro, nombreProducto,unidad, precio, fechafin "
                    + " from ofertas o inner join productosasociadosusuarios pro on"
                    + " o.productosAsociadosUsuariosId = pro.idProductosAsociadosUsuarios"
                    + " inner join productos p on pro.productosId = p.idProductos"
                    + " inner join usuarios u on pro.usuariosId = u.idUsuarios"
                    + " inner join categorias c on p.categoriasId = c.idCategorias"
                    + " where if (fechafin > CURRENT_DATE(),1,0) = 1"
                    + " order by precio asc;";
            pstmt = cnn.prepareStatement(queryAllOfertas);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CategoriaDTO cdto = new CategoriaDTO(rs.getInt("idCategorias"), rs.getString("nombreCategoria"));
                UsuariosDTO udto = new UsuariosDTO(rs.getString("NombreProductor"));
                ProductoDTO pdto = new ProductoDTO(rs.getInt("idPro"), rs.getString("nombreProducto"), rs.getString("unidad"), cdto);
                OfertasDTO ofdto = new OfertasDTO(pdto, udto);
                ofdto.setIdOfertas(rs.getInt("idOfertas"));
                ofdto.setPrecio(rs.getFloat("precio"));
                ofdto.setFechaFin(rs.getString("fechafin"));
                ofer.add(ofdto);
            }

        } catch (SQLException ex) {
            System.out.println("Ha ocurrido esto" + ex.getSQLState() + " - " + ex.getMessage());
        }
        return ofer;
    }

    public List<OfertasDTO> consultarOfertasHabilitadas(Connection cnn) {
        this.cnn = cnn;
        LinkedList<OfertasDTO> ofer = new LinkedList();

        try {
            String queryAllOfertas = " select idOfertas,idCategorias, nombreCategoria, "
                    + " concat(u.nombres,' ',u.apellidos) as NombreProductor, idProductos "
                    + " as idPro, nombreProducto, unidad, cantidad, precio, fechafin "
                    + " from ofertas o "
                    + " inner join productosasociadosusuarios pro on "
                    + " o.productosAsociadosUsuariosId = pro.idProductosAsociadosUsuarios "
                    + " inner join productos p on pro.productosId = p.idProductos "
                    + " inner join usuarios u on pro.usuariosId = u.idUsuarios "
                    + " inner join categorias c on p.categoriasId = c.idCategorias "
                    + " where o.Estado = 1 and if(fechafin > CURDATE(),1,0) = 1 "
                    + " order by idOfertas asc;";
            pstmt = cnn.prepareStatement(queryAllOfertas);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CategoriaDTO cdto = new CategoriaDTO(rs.getInt("idCategorias"), rs.getString("nombreCategoria"));
                UsuariosDTO udto = new UsuariosDTO(rs.getString("NombreProductor"));
                ProductoDTO pdto = new ProductoDTO(rs.getInt("idPro"), rs.getString("nombreProducto"), rs.getString("unidad"), cdto);
                OfertasDTO ofdto = new OfertasDTO(pdto, udto);
                ofdto.setIdOfertas(rs.getInt("idOfertas"));
                ofdto.setCantidad(rs.getInt("cantidad"));
                ofdto.setPrecio(rs.getFloat("precio"));
                ofdto.setFechaFin(rs.getString("fechafin"));
                ofer.add(ofdto);
            }

        } catch (SQLException ex) {
            System.out.println("Ha ocurrido esto" + ex.getSQLState() + " - " + ex.getMessage());
        }
        return ofer;
    }
}
