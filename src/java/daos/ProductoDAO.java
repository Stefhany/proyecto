/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.CategoriaDTO;
import dtos.ProductoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import utilities.MyException;

/**
 * @author krito
 */
public class ProductoDAO {

    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private Connection cnn = null;

    public String insertarProducto(ProductoDTO nuevoProducto, Connection cnn) {
        this.cnn = cnn;
        int resultado = 0;
        String msgSalida = "";
        try {
            pstmt = cnn.prepareStatement("INSERT INTO productos VALUES (null, ?,?,?,1,?);");
            pstmt.setString(1, nuevoProducto.getNombre());
            pstmt.setString(2, nuevoProducto.getUnidad());
            pstmt.setInt(3, nuevoProducto.getCategoriaId().getIdCategoria());
            pstmt.setInt(4, nuevoProducto.getPrecioProducto());  
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

    public String modificarProducto(ProductoDTO modProducto, Connection cnn) {
        this.cnn = cnn;
        int rtdo = 0;
        String msgSalida = "";
        try {
            pstmt = cnn.prepareStatement("UPDATE productos SET precioProducto = ? WHERE  idProductos =  ?;");
            pstmt.setInt(1, modProducto.getPrecioProducto());
            pstmt.setInt(2, modProducto.getIdProductos());
            rtdo = pstmt.executeUpdate();
            if (rtdo != 0) {
                msgSalida = "ok";
            } else {
                msgSalida = "no";
            }
        } catch (SQLException sqle) {
            msgSalida = "Ha ocurrido lo siguiente... " + sqle.getMessage();
        }
        return msgSalida;
    }

    public String deshabilitarProducto(int id, Connection cnn) {
        this.cnn = cnn;
        int rtdo = 0;
        String msgSalida = "";
        try {
            pstmt = cnn.prepareStatement("update productos set estado = 2 WHERE idProductos = ?;");
            pstmt.setInt(1, id);
            rtdo = pstmt.executeUpdate();
            if (rtdo != 0) {
                msgSalida = "ok";
            }else {
                msgSalida = "no";
            }
        } catch (SQLException sqle) {
            msgSalida = "Ocurrio esta excepción " + sqle.getMessage();
        }
        return msgSalida;
    }

    public List<ProductoDTO> listarAllProductos(Connection cnn) throws MyException {
        this.cnn = cnn;
        LinkedList<ProductoDTO> products = new LinkedList();
        try {
            String queryAllPro = " select idproductos, nombreProducto, unidad, idcategorias, nombreCategoria, precioProducto "
                    + " from productos p inner join categorias c on p.categoriasid=c.idcategorias"
                    + " where estado = 1"
                    + " order by nombreProducto asc";
            pstmt = cnn.prepareStatement(queryAllPro);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CategoriaDTO cdto = new CategoriaDTO(rs.getInt("idcategorias"), rs.getString("nombreCategoria"));
                ProductoDTO prodto = new ProductoDTO(rs.getInt("idproductos"), rs.getString("nombreProducto"), rs.getString("unidad"), cdto);
                prodto.setPrecioProducto(rs.getInt("precioProducto"));
                products.add(prodto);
            }
        } catch (SQLException sqle) {
            throw new MyException("Ocurrio este error al listar los elementos: " + sqle.getSQLState() + " - " + sqle.getMessage());
        }
        return products;
    }

    public ProductoDTO consultarByIdProduct(int id, Connection cnn) throws MyException {
        this.cnn = cnn;
        ProductoDTO onlyProduct = null;
        try {
            String querryByIdProduct = "select idProductos as id,nombreProducto, unidad, "
                    + " categoriasId, nombreCategoria, precioProducto "
                    + " from productos p "
                    + " inner join categorias c on p.categoriasId = c.idCategorias "
                    + " WHERE idProductos = ?;";
            pstmt = cnn.prepareStatement(querryByIdProduct);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    CategoriaDTO cto = new CategoriaDTO();
                    cto.setIdCategoria(rs.getInt("categoriasId"));
                    cto.setNombre(rs.getString("nombreCategoria"));
                    onlyProduct = new ProductoDTO(cto);
                    onlyProduct.setIdProductos(rs.getInt("id"));
                    onlyProduct.setNombre(rs.getString("nombreProducto"));
                    onlyProduct.setUnidad(rs.getString("unidad"));
                    onlyProduct.setPrecioProducto(rs.getInt("precioProducto"));
                }
            } else {
                throw new MyException("No hay registros, por esa busqueda... ");
            }
        } catch (SQLException sqle) {
            throw new MyException("Ups! Mira lo ocurrido... " + sqle.getMessage() + " y " + sqle.getSQLState());
        }
        return onlyProduct;
    }

    public List<ProductoDTO> consultarPrecios(int idProducto, Connection cn){
        this.cnn = cn;
        List<ProductoDTO> precios = new LinkedList();
        try{
            String buscarPrecio = "select idProductos as id, precioProducto from productos where idProductos = ?;";
            pstmt = cnn.prepareStatement(buscarPrecio);
            pstmt.setInt(1, idProducto);
            rs = pstmt.executeQuery();
            
            if (rs != null) {
                while(rs.next()){
                    ProductoDTO producto = new ProductoDTO();
                    producto.setIdProductos(rs.getInt("id"));
                    producto.setPrecioProducto(rs.getInt("precioProducto"));
                    precios.add(producto);
                }
            }
        }catch(SQLException sqle){
            System.out.println("Ha ocurrido lo siguiente: "+sqle.getMessage());
        }
        
        return precios;
    }
    
    public String buscarProducto(int idProducto, Connection cn){
        this.cnn = cn;
        String res = "";
        try{
            String buscarPrecio = "select nombreProducto from productos where idProductos = ?;";
            pstmt = cnn.prepareStatement(buscarPrecio);
            pstmt.setInt(1, idProducto);
            rs = pstmt.executeQuery();
            
            if (rs != null) {
                while(rs.next()){
                    res = rs.getString("nombreProducto");
                }
            }
        }catch(SQLException sqle){
            res = "Ha ocurrido lo siguiente: "+sqle.getMessage();
        }
        
        return res;
    }
    
}
