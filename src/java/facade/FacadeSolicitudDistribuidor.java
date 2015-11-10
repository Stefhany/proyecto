/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import connection.Conectar;
import daos.SolicitudDistribuidorDAO;
import dtos.SolicitudDistribuidorDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Mona
 */
public class FacadeSolicitudDistribuidor {
    private Connection cnn = null;
    private SolicitudDistribuidorDAO solicitudDao = null;
    private SolicitudDistribuidorDTO solicitudDto = null;
    
    public FacadeSolicitudDistribuidor(){
//        cnn = Conection.getConnection2();
        solicitudDao = new SolicitudDistribuidorDAO();
        solicitudDto = new SolicitudDistribuidorDTO();
        cnn = utilities.Connection.getInstance();
    }
    
    public String insertarSolicitudDistribuidor(SolicitudDistribuidorDTO solicitud){
        return solicitudDao.insertarSolicitudDistribuidor(solicitud, cnn);
    }
    
    public LinkedList<SolicitudDistribuidorDTO> listarSolicitudesDistribuidor(){
        return solicitudDao.listarSolicitudesDistribuidor(cnn);
    }
    
    public SolicitudDistribuidorDTO byIdRequest(int id){
        return solicitudDao.byIdRequest(id, cnn);
    }
    
    public String eliminarSolicitud(int id){
        return solicitudDao.eliminarSolicitud(id, cnn);
    }
    
    public String modificarSolicitudDistribuidor(SolicitudDistribuidorDTO solicitud){
        return solicitudDao.modificarSolicitudDistribuidor(solicitud, cnn);
    }
    
    public List listarMisPedidosALaAsociacion(int idUser){
        return solicitudDao.listarMisPedidosDeUnaAsociacion(idUser, cnn);
    }
    
    public String modificarCantidadSolicitud(int cantidadFinal, int idSolicitud) throws SQLException {
        return solicitudDao.modificarCantidadSolicitud(cantidadFinal, idSolicitud, cnn);
    }
}
