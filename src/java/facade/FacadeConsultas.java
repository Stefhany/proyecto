/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import connection.Conectar;
import daos.Consultas;
import dtos.ConsultasDTO;
import dtos.OfertasDTO;
import java.sql.Connection;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author Mona
 */
public class FacadeConsultas {
    private Connection cnn = null;
    private ConsultasDTO consulDto = null;
    private Consultas consulDao = null;
    
    public FacadeConsultas(){
//        cnn = Conection.getConnection2();
        consulDto = new ConsultasDTO();
        consulDao = new Consultas();
        cnn = utilities.Connection.getInstance();
    }
    
    public LinkedList<OfertasDTO> consultarFiltro(String filtro){
        return consulDao.consultarFiltro(filtro, cnn);
    }
    
    public ConsultasDTO consultarFecha(){
        return consulDao.consultarFecha(cnn);
    }
    
    public int diferenciasDeFechas(Date fechaInicial, Date fechaFinal){
        return consulDao.diferenciasDeFechas(fechaInicial, fechaFinal, cnn);
    }
    
    public Date consultarFechaActual() {
        return consulDao.consultarFechaActual(cnn);
    }
}
