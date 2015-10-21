/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.EvaluacionDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Mona
 */
public class EvaluacionDAO {
    PreparedStatement pstmt = null;
    Connection cnn = null;
    ResultSet rs =  null;
    
    public LinkedList<EvaluacionDTO> contarRegistrosDao( Connection con){
        this.cnn = con;
        LinkedList<EvaluacionDTO> registros = new LinkedList();
        try{
        String count = "select count(idUsuarios) as registros from usuarios;";
        
        pstmt = cnn.prepareStatement(count);
        rs = pstmt.executeQuery();
        
            if (rs != null) {
                while(rs.next()){
                    EvaluacionDTO e = new EvaluacionDTO();
                    e.setRegistros(rs.getInt("registros"));
                    registros.add(e);
                }
            }else{
                System.out.println("No hay registros en esa tabla.");
            }
        }catch(SQLException sql){
            System.out.println("Mira lo que ocurrio: "+sql.getMessage());
        }
        return registros;
    }
    
}
