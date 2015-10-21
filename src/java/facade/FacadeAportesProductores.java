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
import java.util.LinkedList;

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
}
