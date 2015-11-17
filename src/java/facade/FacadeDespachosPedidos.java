/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import connection.Conectar;
import daos.DespachosPedidosDAO;
import dtos.DespachosPedidosDTO;
import dtos.SolicitudDistribuidorDTO;
import java.sql.Connection;
import java.sql.Date;
import java.util.LinkedList;
import utilities.MyException;

/**
 *
 * @author Mona
 */
public class FacadeDespachosPedidos {
    private static Connection cnn = null;
    private static DespachosPedidosDAO despachoDao = null;
    private static DespachosPedidosDTO despachoDto = null;
    
    public FacadeDespachosPedidos(){
        despachoDao = new DespachosPedidosDAO();
        despachoDto = new DespachosPedidosDTO();
//        cnn = Conection.getConnection2();
        cnn = utilities.Connection.getInstance();
    }
    
    public String insertarDespacho(DespachosPedidosDTO despachoDto){
        return despachoDao.insertarDespacho(despachoDto, cnn);
    }
    
    public LinkedList<SolicitudDistribuidorDTO> mostrarDespachosPendientes() throws MyException{
        return despachoDao.mostrarDespachosPendientes(cnn);
    }
    
    public SolicitudDistribuidorDTO consultarSolicitud(int id){
        return despachoDao.consultarSolicitud(id, cnn);
    }
    
    public boolean consultarFecha(Date fechaInicial, Date fechaFinal){
        return despachoDao.consultarFecha(fechaInicial, fechaFinal, cnn);
    }
    
    public String cambiarEstadoSolicitud(int idSolicitud){
        return despachoDao.cambiarEstadoSolicitud(idSolicitud, cnn);
    }
    
    public static String cambiarEstadoAProductores(int idSolicitud){
        return despachoDao.cambiarEstadoAProductores(idSolicitud, cnn);
    }
    
    public static int validarFecha(String fechaIngresar){
        return despachoDao.validarFecha(fechaIngresar, cnn);
    }
    
    public static int validarFechaDespacho(String fechaIngresar, int idSolicitud){
        return despachoDao.validarFechaDespacho(fechaIngresar, idSolicitud, cnn);
    }
    
    public int validarFechaAporte(String fechaIngresar, int idSolicitud){
        return despachoDao.validarFechaAporte(fechaIngresar, idSolicitud, cnn);
    }
    
    public String cambiarEstadoDespacho(int idSolicitud){
        return despachoDao.cambiarEstadoDespacho(idSolicitud, cnn);
    }
}
