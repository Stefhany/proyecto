/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import connection.Conectar;
import daos.ProductoDAO;
import dtos.ProductoDTO;
import java.sql.Connection;
import java.util.List;
import utilities.MyException;
/**
 *
 * @author Mona
 */
public class FacadeProductos {
    private Connection cnn = null;
    private ProductoDTO productDto = null;
    private ProductoDAO productDao = null;
    
    public FacadeProductos(){
//        cnn = Conection.getConnection2();
        productDto = new ProductoDTO();
        productDao = new ProductoDAO();
        cnn = utilities.Connection.getInstance();
    }
    
    public String registrarProducto(ProductoDTO productDto){
        return productDao.insertarProducto(productDto, cnn);
    }
    
    public String actualizarProducto(ProductoDTO productDto){
        return productDao.modificarProducto(productDto, cnn);
    }
    
    public String deshabilitarProducto(int id){
        return productDao.deshabilitarProducto(id, cnn);
    }
    
    public List<ProductoDTO> listarAllProducts() throws MyException{
        return productDao.listarAllProductos(cnn);
    }
    
    public ProductoDTO consultarByIdProduct(int id) throws MyException{
        return productDao.consultarByIdProduct(id, cnn);
    }
    
    public List<ProductoDTO> consultarPrecio(int idProduct){
        return productDao.consultarPrecios(idProduct, cnn);
    }
    
    public String buscarProducto(int idProducto){
        return productDao.buscarProducto(idProducto, cnn);
    }
}
