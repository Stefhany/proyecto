/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Mona
 */
public class PedidoSobreOfertaDAO {

    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private Connection cnn = null;
    private CallableStatement cllstmt = null;

    public String insertarPedidoSobreOferta(int cantidadPedida, int idOferta, String fechaSolicitud, Connection cnn) {
        this.cnn = cnn;
        int salida = 0;
        String msgSalida = "";
        try {
            String procedureOrder = "{call ps_registrarPedidov1 (?,?,?,?)}";
            cllstmt = cnn.prepareCall(procedureOrder);
            cllstmt.setInt(1, cantidadPedida);
            cllstmt.setInt(2, idOferta);
            cllstmt.setString(3, fechaSolicitud);
            cllstmt.registerOutParameter(4, java.sql.Types.INTEGER);
            cllstmt.execute();
            salida = cllstmt.getInt(4);

            if (salida == -1) {
                msgSalida = "no";
            } else if (salida == 1) {
                msgSalida = "ok";
            } else {
                msgSalida = "Something was wrong!!";
            }
        } catch (SQLException sqle) {
            msgSalida = "Pilas! Ocurrio la siguiente excepción " + sqle.getMessage();
        }
        return msgSalida;
    }

    public int calcularCantidad(int idOferta, Connection con) {
        this.cnn = con;        
        int res = 0;
        try {
            pstmt = cnn.prepareStatement("select cantidad from ofertas where idOfertas = ?;");
            pstmt.setInt(1, idOferta);     
            rs = pstmt.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {
                   res = rs.getInt("cantidad");                    
                }
            }

        } catch (SQLException sql) {
            System.out.println("Mire: " + sql.getMessage());
        }
        return res;
    }
    
    
}
