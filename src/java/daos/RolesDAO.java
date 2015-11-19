/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.RolesDTO;
import dtos.UsuariosDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            } else {
                System.out.println("No hay registros...");
            }
        } catch (SQLException sqle) {
            System.out.println("Se ha producido la sig excepción: " + sqle.getMessage());
        }
        return roles;
    }

    public Date consultarFechaActual2(Connection cnn) {
        this.cnn = cnn;
        Date date = null;
        try {
            String querryFecha = " select Date_format(now(),'%Y/%m/%d') as fechaActual; ";
            pstmt = cnn.prepareStatement(querryFecha);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    date = rs.getDate("fechaActual");
                }
            }
        } catch (SQLException sqle) {
            String salida = "Mira lo que ocurrio! " + sqle.getMessage() + " y " + sqle.getSQLState();
        }
        return date;
    }

    public List<RolesDTO> enviarCorreosProgramados(Connection cnn) {
        this.cnn = cnn;
        ArrayList<RolesDTO> roles = new ArrayList();
        try {
            String querryRol = " select idUsuarios, concat(nombres,' ',apellidos) as Usuario, "
                    + " r.nombre, r.idRoles, correo "
                    + " from usuarios u "
                    + " inner join rolesusuarios ru on u.idUsuarios = ru.usuariosId "
                    + " inner join roles r on ru.rolesId = r.idRoles;";
            pstmt = cnn.prepareStatement(querryRol);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    
                    UsuariosDTO usuario = new UsuariosDTO();
                    usuario.setIdUsuarios(rs.getInt("idUsuarios"));
                    usuario.setNombres(rs.getString("Usuario"));
                    usuario.setCorreo(rs.getString("correo"));
                    
                    RolesDTO rol = new RolesDTO(usuario);
                    rol.setIdRol(rs.getInt("r.idRoles"));
                    rol.setNombre(rs.getString("r.nombre"));
                    roles.add(rol);
                }
            } else {
                System.out.println("No hay registros...");
            }
        } catch (SQLException sqle) {
            System.out.println("Se ha producido la sig excepción: " + sqle.getMessage());
        }
        return roles;
    }
}
