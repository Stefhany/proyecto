/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import connection.Conectar;
import daos.PedidoSobreOfertaDAO;
import dtos.PedidoSobreOfertaDTO;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Mona
 */
public class FacadePedidoSobreOferta {
    private Connection cnn = null;
    private PedidoSobreOfertaDTO pedidoDto = null;
    private PedidoSobreOfertaDAO pedidoDao = null;
    
    public FacadePedidoSobreOferta(){
//        cnn = Conection.getConnection2();
        pedidoDto = new PedidoSobreOfertaDTO();
        pedidoDao = new PedidoSobreOfertaDAO();
        cnn = utilities.Connection.getInstance();
    }
    
    public String registrarPedidoSobreOferta(int cantidadPedida, int idOferta, String fechaSolicitud, int idUsuario){
        return pedidoDao.insertarPedidoSobreOferta(cantidadPedida, idOferta, fechaSolicitud, idUsuario, cnn);
    }
    
    public int calcularCantidad(int idOferta){
        return pedidoDao.calcularCantidad(idOferta, cnn);
    }
    
    public List<PedidoSobreOfertaDTO> listarMisPedidosSobreUnaOferta(int idUser){
        return pedidoDao.listarMisPedidosSobreUnaOferta(idUser, cnn);
    } 
    
    public List<PedidoSobreOfertaDTO> consultarMisPedidosDeUnProducto(int idUser){
        return pedidoDao.consultarMisPedidosDeUnProducto(idUser, cnn);
    }
}
