/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import connection.Conectar;
import daos.ProductosAsociadosUsuariosDAO;
import dtos.ProductoDTO;
import dtos.ProductosAsociadosUsuariosDTO;
import dtos.UsuariosDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utilities.MyException;

/**
 *
 * @author Mona
 */
public class FacadeProductosAsociadosUsuarios {
    private Connection cnn = null;
    private ProductosAsociadosUsuariosDAO proAsoDao = null;
    private ProductosAsociadosUsuariosDTO proAsoDto = null;
    
    public FacadeProductosAsociadosUsuarios(){
//        cnn = Conection.getConnection2();
        proAsoDao = new ProductosAsociadosUsuariosDAO();
        proAsoDto = new ProductosAsociadosUsuariosDTO();
        cnn = utilities.Connection.getInstance();
    }
    
    public ArrayList<ProductosAsociadosUsuariosDTO> listarProductosByUser(int idUser){
        return proAsoDao.listarProductosByUser(idUser, cnn);
    }
    
    public ArrayList<ProductosAsociadosUsuariosDTO> consultarMisProductos(int idUser){
        return proAsoDao.consultarMisProductos(idUser, cnn);
    }
    
    public String eliminarProAso(int id){
        return proAsoDao.eliminarProAso(id, cnn);
    }
    
    public String insertarProductoAsociado(String nomProducto, int categoria,int idUsuario){
        return proAsoDao.insertarProductoAsociado(nomProducto, categoria, idUsuario, cnn);
    }
    
    public String seleccionarProducto(int idUser, int idProduct) throws SQLException{
        return proAsoDao.registrarProAso(idUser,idProduct, cnn);
    }
    
    public ProductosAsociadosUsuariosDTO listarProductoAsociadoParaOfertar(int idProAso){
        return proAsoDao.listarProductoAsociadoParaOfertar(idProAso, cnn);
    }
    
    public String cambiarEstadoProAso (int idProAso){
        return proAsoDao.cambiarEstadoProAso(idProAso, cnn);
    }
    
    public String cambiarEstadoProAsoDdeshabilitar (int idProductoAso){
        return proAsoDao.cambiarEstadoProAsoDdeshabilitar(idProductoAso, cnn);
    }
    
    public List<ProductoDTO> asociarProductos(int idUsuario) throws MyException{
        return proAsoDao.asociarProductos(cnn, idUsuario);
    }
    
    public List<UsuariosDTO> enviarCorreoAProductores(int idProduct){
        return proAsoDao.enviarCorreoAProductores(idProduct, cnn);
    }
    
    public List<ProductosAsociadosUsuariosDTO> consultarAsociaciones(){
        return proAsoDao.consultarAsociaciones(cnn);
    }
}
