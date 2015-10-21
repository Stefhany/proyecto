/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daos;

import dtos.EstadosPedidosDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author krito
 */
public class EstadosPedidosDAO {
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private Connection cnn = null;
    
    public String insertarEstadoPedido(EstadosPedidosDTO nuevoEstado, Connection cnn) {
        this.cnn = cnn;
        int rtdo = 0;
        String sal = "";
        try {
            pstmt = cnn.prepareStatement("INSERT INTO estadospedidos VALUES (null, ?, ?);");
            pstmt.setString(1, nuevoEstado.getNombre());
            pstmt.setString(2, nuevoEstado.getObservaciones());
            rtdo = pstmt.executeUpdate();

            if (rtdo != 0) {
                sal = "El registro del estado del pedido " + rtdo + " ha sido exitoso";
            } else {
                sal = "No se pudo realizar el registro";
            }
        } catch (SQLException sqle) {
            sal = "Ha ocurrido la siguiente exepción.. " + sqle.getMessage();

        }
        return sal;
    }

    public String modificarEstadoPedido(EstadosPedidosDTO modEstado, Connection cnn) {
        this.cnn = cnn;
        int rtdo = 0;
        String msgSalida = "";
        try{
        pstmt = cnn.prepareStatement("UPDATE estadospedidos SET nombre = ?, observaciones = ? WHERE idEstadosPedidos = ?;");
        pstmt.setString(1, modEstado.getNombre());
        pstmt.setString(2, modEstado.getObservaciones());
        rtdo =  pstmt.executeUpdate();
        
        if (rtdo != 0) {
            msgSalida = "La modificación "+ rtdo +  " se pudo realizar exitosamente";
        }else{
            msgSalida = "No se pudo realizar la modificación";
        }
        }catch(SQLException sqle){
            msgSalida = "Ha ocurrido lo siguiente... "+sqle.getMessage();
        }       
        return msgSalida;
    }
    
    public ArrayList<EstadosPedidosDTO> listarEstadosPedidos(Connection cnn){
        ArrayList<EstadosPedidosDTO> estados = new ArrayList();
        this.cnn = cnn;
        try{
        pstmt = cnn.prepareStatement("SELECT idEstadosPedidos as id, nombre, observaciones FROM estadospedidos;");
        rs = pstmt.executeQuery();
        
        if (rs != null) {
            while (rs.next()){
                EstadosPedidosDTO espdto = new EstadosPedidosDTO();
                espdto.setIdEstadoPedidos(rs.getInt("id"));
                espdto.setNombre(rs.getString("nombre"));
                espdto.setObservaciones(rs.getString("observaciones"));
                estados.add(espdto);
            }
        }else{
            System.out.println("No se encuetran estados de pedidos");
        }
        }catch(SQLException sqle){
            System.out.println("Se ha producido esta excepción.. "+sqle.getMessage());
        }
        return estados;
    }
    
    public String eliminarEstadoPedido(int id, Connection cnn){
        this.cnn = cnn;
        int resultado = 0;
        String msgSalida = "";
        try{
            pstmt = cnn.prepareStatement("DELETE FROM estadospedidos WHERE idEstadosPedidos = ?;");
            pstmt.setInt(1, id);
            resultado = pstmt.executeUpdate();
            
            if (resultado != 0) {
                msgSalida = "El registro " + resultado + " ha sido eliminado. Exitosamente";
            }
        }catch (SQLException sqle){
            msgSalida = "Ocurrio esta excepción "+sqle.getMessage();
        }
        return msgSalida;
    }
    
    public EstadosPedidosDTO consultarByIdEstadoPedido(int id, Connection cnn){
        this.cnn = cnn;
        EstadosPedidosDTO epdto = null;
        try{
            pstmt = cnn.prepareStatement("SELECT idEstadosPedidos as id, nombre, observaciones FROM estadospedidos WHERE idEstadosPedidos = ?;");
            rs = pstmt.executeQuery();
            if (rs != null) {
                while(rs.next()){
                    epdto = new EstadosPedidosDTO();
                    epdto.setIdEstadoPedidos(rs.getInt("id"));
                    epdto.setNombre(rs.getString("nombre"));
                    epdto.setObservaciones(rs.getString("observaciones"));
                }
            }else{
                System.out.println("No hay registros... ");
            }
        }catch (SQLException sqle){
            System.out.println("Ups! Mira lo ocurrido... "+sqle.getMessage());
        }
        return epdto;
    }
}
