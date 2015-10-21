/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.RolesUsuariosDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dtos.RolesDTO;
import dtos.UsuariosDTO;
import java.util.LinkedList;

/**
 *
 * @author Mona
 */
public class RolesUsuariosDAO {

    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private Connection cnn = null;

    public String insertarRol(RolesUsuariosDTO rol, Connection cnn) {
        this.cnn = cnn;
        int res = 0;
        String msgSalida = "";
        try {
            String querryInsertRol = " INSERT INTO rolesusuarios VALUES (?,?,1);";
            pstmt = cnn.prepareStatement(querryInsertRol);
            pstmt.setInt(1, rol.getRolesId());
            pstmt.setInt(2, rol.getUsuariosId());
            res = pstmt.executeUpdate();
            if (res != 0) {
                msgSalida = "ok";
            } else {
                msgSalida = "no";
            }
        } catch (SQLException sqle) {
            msgSalida = "Se ha producido la sig excepción: " + sqle.getMessage();
        }
        return msgSalida;
    }

    public LinkedList<RolesUsuariosDTO> rol(int idUsuario, Connection cc) {
        LinkedList<RolesUsuariosDTO> rolesUsuario = new LinkedList();
        
        this.cnn = cc;
        try {
            String querryRol = "select nombres, apellidos, idRoles, nombre "
                    + " from usuarios u "
                    + " inner join rolesusuarios ru "
                    + " on u.idUsuarios = ru.usuariosId "
                    + " inner join roles r "
                    + " on r.idRoles = ru.rolesId where idusuarios=?;";
            pstmt = cc.prepareStatement(querryRol);
            pstmt.setInt(1, idUsuario);
            rs = pstmt.executeQuery();
            
            if (rs != null) {
                while(rs.next()){
                    UsuariosDTO user = new UsuariosDTO();
                    user.setNombres(rs.getString("nombres"));
                    user.setApellidos(rs.getString("apellidos"));
                    RolesDTO rol = new RolesDTO();
                    rol.setIdRol(rs.getInt("idRoles"));
                    rol.setNombre(rs.getString("nombre"));
                    RolesUsuariosDTO rolUsuario = new RolesUsuariosDTO(rol, user);
                    rolesUsuario.add(rolUsuario);
                }
            }
        }catch(SQLException sqle){
            System.out.println("Ha ocurrido lo siguiente: "+sqle.getMessage());
        }
        return rolesUsuario;
    }
    
    public String cambiarEstadoUsuario (int idUsuario, Connection cc){
        this.cnn = cc;
        int res = 0;
        String msgSalida = "";
        try {
        String querryCambiarEstadoUser = "update usuarios set estadoUser = 2 where idusuarios = ?;";
        pstmt = cnn.prepareStatement(querryCambiarEstadoUser);
        pstmt.setInt(1, idUsuario);
        res = pstmt.executeUpdate();
            if (res != 0) {
                msgSalida = "ok";
            }else {
                msgSalida = "no";
            }        
        }catch (SQLException sqle){
            msgSalida = "Mira lo que ocurrio: "+sqle.getMessage();
        }
        
        return msgSalida;
    }
}
