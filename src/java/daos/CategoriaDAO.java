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
import java.util.ArrayList;
import java.util.LinkedList;
import utilities.MyException;

/**
 *
 * @author krito
 */
public class CategoriaDAO {
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;   
    private Connection cnn = null;

    public String registrarCategoria(CategoriaDTO p, Connection cnn) throws SQLException {
        this.cnn = cnn;
        String msgSalida; 
        int rtdo;  
        pstmt = cnn.prepareStatement("INSERT INTO categorias VALUES (NULL, ?) ");
        pstmt.setString(1, p.getNombre());
        rtdo = pstmt.executeUpdate();
        if (rtdo > 0) {
            msgSalida = "Se logro insertar el registro (" + rtdo + ")";
        } else {
            msgSalida = "No se pudo ingresar el registro";
        }
        return msgSalida;
    }
    
    public String modificarCategoria(CategoriaDTO modCategoria, Connection cnn) {
        this.cnn = cnn;
        int resultado = 0;
        String salida = "";
        try{
        pstmt = cnn.prepareStatement("UPDATE categorias SET nombre = ? WHERE idcategorias = ?;");
        pstmt.setString(1, modCategoria.getNombre());
        resultado =  pstmt.executeUpdate();
        
        if (resultado != 0) {
            salida = "La modificación se pudo realizar "+ resultado + "exitosamente";
        }else{
            salida = "No se pudo realizar la modificación";
        }
        }catch(SQLException sqle){
            salida = "Ha ocurrido lo siguiente... "+sqle.getMessage();
        }       
        return salida;
    }
    
    public ArrayList<CategoriaDTO> listarCategorias(Connection cnn){
        ArrayList<CategoriaDTO> categorias = new ArrayList();
        this.cnn = cnn;
        try{
        pstmt = cnn.prepareStatement("SELECT idCategorias as id, nombreCategoria FROM categorias;");
        rs = pstmt.executeQuery();
        
        if (rs != null) {
            while (rs.next()){
                CategoriaDTO cadto =new CategoriaDTO();
                cadto.setIdCategoria(rs.getInt("id"));
                cadto.setNombre(rs.getString("nombreCategoria"));
                categorias.add(cadto);
            }
        }else{
            System.out.println("No se encuetran registros de categorias");
        }
        }catch(SQLException sqle){
            System.out.println("Se ha producido esta excepción.. "+sqle.getMessage());
        }
        return categorias;
    }
    
    public String eliminarCategoria(int id, Connection cnn){
        this.cnn = cnn;
        int rto = 0;
        String msgSalida = "";
        try{
            pstmt = cnn.prepareStatement("DELETE FROM categorias WHERE idCategorias = ?;");
            pstmt.setInt(1, id);
            rto = pstmt.executeUpdate();
            
            if (rto != 0) {
                msgSalida = "Registro " + rto + " eliminado. Exitosamente";
            }else{
                msgSalida = "No se pudo realizar la eliminación";
            }
        }catch (SQLException sqle){
            msgSalida = "Ocurrio esta excepción "+sqle.getMessage();
        }
        return msgSalida;
    }
    
    public CategoriaDTO consultarById(int id, Connection cnn){
        this.cnn = cnn;
        CategoriaDTO cdto = null;
        try{
            pstmt = cnn.prepareStatement("SELECT idCategorias as id, nombre FROM categorias WHERE idCategorias = ?;");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while(rs.next()){
                    cdto = new CategoriaDTO(rs.getInt("id"), rs.getString("nombre")); 
                }
            }else{
                System.out.println("No hay registros... ");
            }
        }catch (SQLException sqle){
            System.out.println("Ups! Mira lo ocurrido... "+sqle.getMessage());
        }
        return cdto;
    }
    
    /*
    * Listar las categorías que se encuentran en el sistema.
    */
    public LinkedList<CategoriaDTO> listarCategorias2(Connection cnn) throws MyException, SQLException {
        this.cnn = cnn;
        LinkedList<CategoriaDTO> listaCategory = new LinkedList<>();
        try {
            String query = "SELECT  idcategorias as id, nombreCategoria "                    
                    + " FROM categorias ";
            pstmt = cnn.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                CategoriaDTO newCategory = new CategoriaDTO();
                newCategory.setIdCategoria(rs.getInt("id"));
                newCategory.setNombre(rs.getString("nombreCategoria"));                
                listaCategory.add(newCategory);
            }
            }else{
                throw new MyException("No se encuentran registros...");
            }
            
        } catch (SQLException ex) {
            throw new MyException("Error al listar los elementos " + ex.getSQLState() + " - " + ex.getMessage());
        } finally {
            pstmt.close();
        }
        return listaCategory;
    }
    /**
     * 
     * Este metodo se hace para realizar la lista dependiente
     * de acuerdo a la categoria que escojio y de inmediato se cargan 
     * los productos encontrados en la misma.
     * */
    public LinkedList<ProductoDTO> listarSubCategorias(int idCategoria, Connection cnn) throws MyException, SQLException{
        this.cnn = cnn;
        LinkedList<ProductoDTO> productos = new LinkedList();
        try {
            pstmt = cnn.prepareStatement("SELECT idProductos as id, nombreproducto, categoriasId "
                    + " FROM productos "
                    + " WHERE categoriasId = ?;");
            pstmt.setInt(1, idCategoria);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    ProductoDTO onlyProduct = new ProductoDTO();
                    onlyProduct.setIdProductos(rs.getInt("id"));
                    onlyProduct.setNombre(rs.getString("nombreproducto"));
                    onlyProduct.setCategoriaId2(rs.getInt("categoriasId"));
                    productos.add(onlyProduct);                    
                }
            }
        } catch (SQLException sqle) {
            throw new MyException("Ha ocurrido lo siguiente. Revise! "+ sqle.getMessage()+"o mira esto: " + sqle.getSQLState());
        }finally{
            pstmt.close();
        }
        return productos;
    }
}
