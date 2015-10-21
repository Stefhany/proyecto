/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import connection.Conectar;
import daos.OfertasDAO;
import dtos.OfertasDTO;
import java.sql.Connection;
import dtos.ProductoDTO;
import java.util.ArrayList;
import java.util.List;
import utilities.MyException;

/**
 *
 * @author Mona
 */
public class FacadeOfertas {

    private Connection cnn = null;
    private OfertasDTO offerDto = null;
    private OfertasDAO offerDao = null;

    public FacadeOfertas(){
//        cnn = Conection.getConnection2();
        cnn = utilities.Connection.getInstance();
        offerDto = new OfertasDTO();
        offerDao = new OfertasDAO();
    }
    
    public String registrarOferta(OfertasDTO offerDto) {
        return offerDao.insertarOferta(offerDto, cnn);        
    }
    
    public String actualizarOferta(OfertasDTO offerDto){
        return offerDao.modificarOferta(offerDto, cnn);
    }
    
    public String eliminarOferta(int id){
        return offerDao.eliminarOferta(id, cnn);
    }
    
    public List<ProductoDTO> listarProductoAsociado(int id){
        return offerDao.listarProductosAsociado(id, cnn);
    }
    
    public List<OfertasDTO> consultarOfertas(){
        return offerDao.consultarOfertas(cnn);
    }
    
    public OfertasDTO consultByOffer(int id) throws MyException{
        return offerDao.consultByOffer(id, cnn);
    }
    
    public ArrayList<OfertasDTO> consultarMisOfertas(int idUser){
        return offerDao.consultarMisOfertas(idUser, cnn);
    }
    
    public OfertasDTO consultMyOffer(int id) throws MyException{
        return offerDao.consultMyOffer(id, cnn);
    }
    
    public String actualizarMiOferta(OfertasDTO offerDto){
        return offerDao.modificarMyOffer(offerDto, cnn);
    }
    
    public List<OfertasDTO> consultarTopOfertas(){
        return offerDao.consultarTopOfertas(cnn);
    }
    
    public List<OfertasDTO> consultarOfertasHabilitadas(){
        return offerDao.consultarOfertasHabilitadas(cnn);
    }
}
