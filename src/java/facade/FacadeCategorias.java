/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import connection.Conectar;
import daos.CategoriaDAO;
import dtos.CategoriaDTO;
import dtos.ProductoDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import utilities.MyException;

/**
 *
 * @author Mona
 */
public class FacadeCategorias {
    private Connection cnn = null;
    private CategoriaDAO categoryDao = null;
    private CategoriaDTO categoryDto = null;
    
    public FacadeCategorias(){
//        cnn = Conection.getConnection2();
        cnn = utilities.Connection.getInstance();
        categoryDto = new CategoriaDTO();
        categoryDao = new CategoriaDAO();
    }
    
    public String insertarCategoria(CategoriaDTO categoryDto) throws SQLException{
        return categoryDao.registrarCategoria(categoryDto, cnn);
    }
        
    public String actualizarCategoria(CategoriaDTO categoryDto){
        return categoryDao.modificarCategoria(categoryDto, cnn);
    }
    
    public String eliminarCategoria(int id){
        return categoryDao.eliminarCategoria(id, cnn);
    }
    
    public CategoriaDTO consultByIdCategoory(int id){
        return categoryDao.consultarById(id, cnn);
    }
    
    public ArrayList<CategoriaDTO> listarCategorias(){
        return categoryDao.listarCategorias(cnn);
    }
    
    public LinkedList<CategoriaDTO> listarTodasCategorias() throws MyException, SQLException{
        return categoryDao.listarCategorias2(cnn);        
    }
    
    public LinkedList<ProductoDTO> listarSubCategorias(int idCategoria) throws MyException, SQLException{
        return categoryDao.listarSubCategorias(idCategoria, cnn);
    }
    
}
