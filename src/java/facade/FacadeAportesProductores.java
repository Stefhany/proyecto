/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import connection.Conectar;
import daos.AportesProductoresDAO;
import dtos.AportesProductoresDTO;
import dtos.SolicitudDistribuidorDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Mona
 */
public class FacadeAportesProductores {
    private static Connection cnn = null;
    private static AportesProductoresDAO aporteDao = null;
    private static AportesProductoresDTO aporteDto = null;
    
    public FacadeAportesProductores(){
//        cnn = Conection.getConnection2();
        aporteDao = new AportesProductoresDAO();
        aporteDto = new AportesProductoresDTO();
        cnn = utilities.Connection.getInstance();
    }
    
    public LinkedList<SolicitudDistribuidorDTO> listarSolicitudesDeAsociacion(){
        return aporteDao.listarSolicitudesDeAsociacion(cnn);
    }
    
    public SolicitudDistribuidorDTO buscarIdAsociacion(int id){
        return aporteDao.byIdForAssociation(id, cnn);
    }
    
    public String participarASolicitudAsociacion(String fechaEntrega, int cantidadAportar, 
            int proAsoId, int idSolicitud){
        return aporteDao.aplicarSolicitudAsociacion(fechaEntrega, cantidadAportar, proAsoId, idSolicitud, cnn);
    }
    
    public static int calcularCantidad (int idSolicitud){
        return aporteDao.calcularCantidad(idSolicitud, cnn);
    }
    
    public String registrarAporte (AportesProductoresDTO aporte) throws SQLException{
        return aporteDao.registrarAporte(aporte, cnn);
    }
    
    public List<AportesProductoresDTO> consultarMisParticipaciones(int idUser){
        return aporteDao.consultarMisParticipaciones(idUser, cnn);
    }
    
    public String modificarEstadoACancelado (int idAporte, AportesProductoresDTO aport) throws SQLException{
        return aporteDao.modificarEstadoACancelado(idAporte, aport, cnn);
    }
    
    public int consultarCantidadAportada(int idAporte){
        return aporteDao.consultarCantidadAportada(idAporte, cnn);
    }
   
    public int consultarCantidadSolicitada(int idSolicitud){
        return  aporteDao.consultarCantidadSolicitada(idSolicitud, cnn);
    }
    
    public int buscarMisIdAsociados(int idProduct, int idUser){
        return aporteDao.buscarMisIdAsociados(idProduct, idUser, cnn);
    }
    
}
