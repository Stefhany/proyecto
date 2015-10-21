/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.RolesDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mona
 */
public class RolesDAO {
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private Connection cnn = null;
    
    public ArrayList<RolesDTO> consultarRoles(Connection cnn) {
        this.cnn = cnn;
        ArrayList<RolesDTO> roles = new ArrayList();
        try {
            String querryRol = " SELECT idRoles, nombre, estado FROM roles ";
            pstmt = cnn.prepareStatement(querryRol);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    RolesDTO rol = new RolesDTO();
                    rol.setIdRol(rs.getInt("idRoles"));
                    rol.setNombre(rs.getString("nombre"));
                    rol.setEstado(rs.getString("estado"));
                    roles.add(rol);
                }
            }else{
                System.out.println("No hay registros...");
            }
        } catch (SQLException sqle) {
            System.out.println("Se ha producido la sig excepción: " + sqle.getMessage());
        }
        return roles;
    }   
}
