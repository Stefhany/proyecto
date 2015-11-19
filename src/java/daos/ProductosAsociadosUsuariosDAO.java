/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.CategoriaDTO;
import dtos.ProductoDTO;
import dtos.ProductosAsociadosUsuariosDTO;
import dtos.UsuariosDTO;
import java.sql.CallableStatement;
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
public class ProductosAsociadosUsuariosDAO {

    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private Connection cnn = null;
    private CallableStatement cllstmt = null;

    public ArrayList<ProductosAsociadosUsuariosDTO> listarProductosByUser(int idUser, Connection cnn) {
        this.cnn = cnn;
        ArrayList<ProductosAsociadosUsuariosDTO> asociados = new ArrayList();
        try {
            pstmt = cnn.prepareStatement("   SELECT idProductosAsociadosUsuarios as id, usuariosId as idUsu, "
                    + " productosId as idPro, pa.estado as estadoProducto, u.*, p.*, c.* "
                    + " FROM productosAsociadosUsuarios pa inner join usuarios u ON pa.usuariosid= u.idusuarios "
                    + " INNER JOIN productos p ON pa.productosId = p.idproductos "
                    + " INNER JOIN categorias c ON p.categoriasId = c.idCategorias "
                    + " WHERE usuariosId = ? "
                    + " order by id asc;");
            pstmt.setInt(1, idUser);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    UsuariosDTO nUser = new UsuariosDTO();
                    nUser.setIdUsuarios(rs.getInt("u.idUsuarios"));
                    nUser.setCedula(rs.getString("u.cedula"));
                    nUser.setNombres(rs.getString("u.nombres"));
                    CategoriaDTO c = new CategoriaDTO(rs.getInt("c.idCategorias"), rs.getString("nombreCategoria"));
                    ProductoDTO nProduct = new ProductoDTO(c);
                    nProduct.setIdProductos(rs.getInt("p.idproductos"));
                    nProduct.setNombre(rs.getString("p.nombreproducto"));
                    ProductosAsociadosUsuariosDTO psodto = new ProductosAsociadosUsuariosDTO(nUser, nProduct);
                    psodto.setIdProductosAsociadosUsuarios(rs.getInt("id"));
                    psodto.setEstado(rs.getInt("estadoProducto"));
                    asociados.add(psodto);
                }
            } else {
                System.out.println("No se encuetran registros de productores asociados.. ");
            }
        } catch (SQLException sqle) {
            System.out.println("Se ha producido esta excepción.. " + sqle.getMessage());
        }
        return asociados;
    }

    public ArrayList<ProductosAsociadosUsuariosDTO> consultarMisProductos(int idUser, Connection cnn) {
        this.cnn = cnn;
        ArrayList<ProductosAsociadosUsuariosDTO> misProductos = new ArrayList();
        try {
            pstmt = cnn.prepareStatement("SELECT idProductosAsociadosUsuarios as id, usuariosId as idUsu, "
                    + " productosId as idPro, u.*, p.*, c.* "
                    + " FROM productosAsociadosUsuarios pa inner join usuarios u ON pa.usuariosid= u.idusuarios "
                    + " INNER JOIN productos p ON pa.productosId = p.idproductos "
                    + " INNER JOIN categorias c on c.idCategorias = p.categoriasId "
                    + " WHERE usuariosId = ?;");
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
                    misProductos.add(psodto);
                }
            } else {
                System.out.println("No se encuetran registros de productores asociados.. ");
            }
        } catch (SQLException sqle) {
            System.out.println("Se ha producido esta excepción.. " + sqle.getMessage());
        }
        return misProductos;
    }

    public String eliminarProAso(int id, Connection cnn) {
        this.cnn = cnn;
        int rtdo = 0;
        String msgSalida = "";
        try {
            pstmt = cnn.prepareStatement("DELETE FROM productosasociadosusuarios WHERE idProductosAsociadosUsuarios = ?;");
            pstmt.setInt(1, id);
            rtdo = pstmt.executeUpdate();

            if (rtdo != 0) {
                msgSalida = "Registro " + rtdo + " eliminado. Exitosamente";
            } else {
                msgSalida = "No se pudo eliminar. ";
            }
        } catch (SQLException sqle) {
            msgSalida = "Ocurrio esta excepción " + sqle.getMessage();
        }
        return msgSalida;
    }

    public String insertarProductoAsociado(String nomProducto, int categoria, int idUsuario, Connection cnn) {
        this.cnn = cnn;
        int sal = 0;
        String msgSalida = "";
        try {
            cllstmt = cnn.prepareCall("{call ps_registrarProductoV5 (?,?,?,?)}");
            cllstmt.setString(1, nomProducto);
            cllstmt.setInt(2, categoria);
            cllstmt.setInt(3, idUsuario);
            cllstmt.registerOutParameter(4, java.sql.Types.INTEGER);
            cllstmt.execute();
            sal = cllstmt.getInt(4);

            if (sal == 1) {
                msgSalida = "Registro exitoso";
            } else {
                msgSalida = "No se pudo registrar el producto!!!";
            }
        } catch (SQLException sqle) {
            msgSalida = "Pilas! Ocurrio la siguiente excepción " + sqle.getMessage();
        }
        return msgSalida;
    }

    public String registrarProAso(int idUser, int idProduct, Connection cnn) throws SQLException {
        this.cnn = cnn;
        String msgSalida;
        int rtdo;
        pstmt = cnn.prepareStatement("INSERT INTO productosasociadosusuarios VALUES (NULL, ?,?,1)");
        pstmt.setInt(1, idUser);
        pstmt.setInt(2, idProduct);

        rtdo = pstmt.executeUpdate();
        if (rtdo > 0) {
            msgSalida = "ok";
        } else {
            msgSalida = "no";
        }
        return msgSalida;
    }

    public ProductosAsociadosUsuariosDTO listarProductoAsociadoParaOfertar(int idProAso, Connection cnn) {
        ProductosAsociadosUsuariosDTO proAsoDto = null;
        try {
            String consultaByOfertar = "select `productosasociadosusuarios`.`idProductosAsociadosUsuarios` as id,"
                    + " `productosasociadosusuarios`.`usuariosId` as idUsu,"
                    + " `productosasociadosusuarios`.`productosId` as idPro,"
                    + " `usuarios`.`correo`,"
                    + " `categorias`.`nombreCategoria`,"
                    + " `productos`.`nombreProducto`"
                    + " from `categorias`"
                    + " inner join `productos` on `categorias`.`idCategorias` = `productos`.`categoriasId`"
                    + " inner join `productosasociadosusuarios` on `productos`.`idProductos` = `productosasociadosusuarios`.`productosId`"
                    + " inner join `usuarios` on `productosasociadosusuarios`.`usuariosId` = `usuarios`.`idUsuarios`"
                    + " where `productosasociadosusuarios`.`idProductosAsociadosUsuarios` = ?;";

            pstmt = cnn.prepareStatement(consultaByOfertar);
            pstmt.setInt(1, idProAso);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    CategoriaDTO c = new CategoriaDTO();
                    c.setNombre(rs.getString("nombreCategoria"));
                    ProductoDTO p = new ProductoDTO(c);
                    p.setIdProductos(rs.getInt("idPro"));
                    p.setNombre(rs.getString("nombreProducto"));
                    UsuariosDTO u = new UsuariosDTO();
                    u.setIdUsuarios(rs.getInt("idUsu"));
                    u.setCorreo(rs.getString("correo"));
                    proAsoDto = new ProductosAsociadosUsuariosDTO(u, p);
                    proAsoDto.setIdProductosAsociadosUsuarios(rs.getInt("id"));
                }
            }
        } catch (SQLException sqle) {
            System.out.println("Mire este error: " + sqle.getMessage());
        }
        return proAsoDto;
    }

    public String cambiarEstadoProAso(int idProAso, Connection cnn) {
        this.cnn = cnn;
        String salida = "";
        int res = 0;
        try {
            String querryCambiarEstado = "update productosasociadosusuarios set estado = 2 where "
                    + " idProductosAsociadosUsuarios = ?";

            pstmt = cnn.prepareStatement(querryCambiarEstado);
            pstmt.setInt(1, idProAso);
            res = pstmt.executeUpdate();

            if (res != 0) {
                salida = "ok";
            } else {
                salida = "no";
            }
        } catch (SQLException sqle) {
            salida = "Ha ocurrido lo siguiente: " + sqle.getMessage();
        }

        return salida;
    }

    public String cambiarEstadoProAsoDdeshabilitar(int idProAso, Connection cnn) {
        this.cnn = cnn;
        String salida = "";
        int res = 0;
        try {
            String querryCambiarEstado = " update productosasociadosusuarios set estado = 0 where "
                    + " idProductosAsociadosUsuarios = ?";

            pstmt = cnn.prepareStatement(querryCambiarEstado);
            pstmt.setInt(1, idProAso);
            res = pstmt.executeUpdate();

            if (res != 0) {
                salida = "ok";
            } else {
                salida = "no";
            }
        } catch (SQLException sqle) {
            salida = "Ha ocurrido lo siguiente: " + sqle.getMessage();
        }

        return salida;
    }

    public List<ProductoDTO> asociarProductos(Connection cnn, int idUsuario) throws MyException {
        this.cnn = cnn;
        LinkedList<ProductoDTO> products = new LinkedList();
        try {
            String querryAsociarProductos = " select idCategorias, nombreCategoria, idProductos, nombreProducto, unidad, "
                    + "precioProducto"
                    + " from categorias "
                    + " inner join productos on categorias.idCategorias = productos.categoriasId "
                    + " where idProductos not in (select productosId from productosasociadosusuarios where usuariosId=? and estado = 1)"
                    + " order by idProductos;";
            pstmt = cnn.prepareStatement(querryAsociarProductos);
            pstmt.setInt(1, idUsuario);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CategoriaDTO cdto = new CategoriaDTO(rs.getInt("idCategorias"), rs.getString("nombreCategoria"));
                ProductoDTO prodto = new ProductoDTO(rs.getInt("idProductos"), rs.getString("nombreProducto"), rs.getString("unidad"), cdto);
                prodto.setPrecioProducto(rs.getInt("precioProducto"));
                products.add(prodto);
            }
        } catch (SQLException sqle) {
            throw new MyException("Ocurrio este error al listar los elementos: " + sqle.getSQLState() + " - " + sqle.getMessage());
        }
        return products;
    }

    public List<UsuariosDTO> enviarCorreoAProductores(int idProduct, Connection con) {
        this.cnn = con;
        List<UsuariosDTO> usuarios = new ArrayList();
        try {
            pstmt = cnn.prepareStatement("SELECT DISTINCT idUsuarios, correo FROM usuarios u INNER JOIN productosasociadosusuarios psu ON psu.usuariosId = u.idUsuarios WHERE psu.productosId=?;");
            pstmt.setInt(1, idProduct);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    UsuariosDTO user = new UsuariosDTO();
                    user.setIdUsuarios(rs.getInt("idUsuarios"));
                    user.setCorreo(rs.getString("correo"));
                    usuarios.add(user);
                }
            }
            
        } catch (SQLException sqle) {
            System.out.println("Se ha producido esta excepción.. " + sqle.getMessage());
        }
        return usuarios;
    }

    public List<ProductosAsociadosUsuariosDTO> consultarAsociaciones(Connection con) {
        this.cnn = con;
        List<ProductosAsociadosUsuariosDTO> productosAsociados = new LinkedList();
        try {
            String consultarAsociaciones = "select productosId, nombreProducto, "
                    + " usuariosId, concat_ws(' ', u.nombres, u.apellidos) as Asociacion, nombreCategoria, precioProducto "
                    + " from productosasociadosusuarios pau  "
                    + " inner join usuarios u on pau.usuariosId = u.idUsuarios "
                    + " inner join productos p on pau.productosId = p.idProductos "
                    + " inner join categorias c on p.categoriasId = c.idCategorias"
                    + " where estadoUser = 3 or estadoUser=5;";
            pstmt = con.prepareStatement(consultarAsociaciones);
            rs = pstmt.executeQuery();
            
            if (rs != null) {
                while(rs.next()){
                 
                    
                    UsuariosDTO user = new UsuariosDTO();
                    user.setNombres(rs.getString("Asociacion"));
                    
                    CategoriaDTO categoria = new CategoriaDTO();
                    categoria.setNombre(rs.getString("nombreCategoria"));
                    
                    ProductoDTO producto = new ProductoDTO(categoria);
                    producto.setNombre(rs.getString("nombreProducto"));
                    producto.setPrecioProducto(rs.getInt("precioProducto"));
                    
                    ProductosAsociadosUsuariosDTO proAso = new ProductosAsociadosUsuariosDTO(user, producto);
                    proAso.setProductoId(rs.getInt("productosId"));
                    proAso.setUsuarioId(rs.getInt("usuariosId"));
                    
                    productosAsociados.add(proAso);
                }
            }
        }catch(SQLException sqle){
            System.out.println("No se encontraron asociaciones, con sus productos respectivos.");
        }
        return productosAsociados;
    }
}
