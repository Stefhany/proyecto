/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.PermisosDTO;
import dtos.UsuariosDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import utilities.MyException;

/**
 *
 * @author Mona
 */
public class PermisosDAO {

    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private Connection cnn = null;

    public HashMap<UsuariosDTO, String> validarUsuario(String correo, String pss, Connection cnn, String url) {
        this.cnn = cnn;
        String menu = "<ul >";
        HashMap<UsuariosDTO, String> usuarioValidado = new HashMap<UsuariosDTO, String>();
        UsuariosDTO user = new UsuariosDTO();
        ResultSet rs = null;
        try {
            pstmt = cnn.prepareStatement(" SELECT u.idUsuarios, u.nombres,u.apellidos, u.cedula, u.telefono, "
                    + " u.direccion, u.correo, u.clave, u.notificaciones, u.ciudad, u.fechaNacimiento, "
                    + " r.idRoles, p.nombre, p.url, p.idPermisos "
                    + " FROM usuarios u "
                    + " INNER JOIN rolesusuarios ru on u.idUsuarios = ru.usuariosId "
                    + " INNER JOIN roles r on ru.rolesId = r.idRoles "
                    + " INNER JOIN permisosroles pr on r.idRoles = pr.rolesId "
                    + " INNER JOIN permisos p on pr.permisosId = p.idPermisos "
                    + " WHERE u.correo = ? "
                    + " AND u.clave = MD5(?)"
                    + " AND p.padre = 0;");

            pstmt.setString(1, correo);
            pstmt.setString(2, pss);

            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    user.setIdUsuarios(rs.getInt("idUsuarios"));
                    user.setNombres(rs.getString("nombres"));
                    user.setApellidos(rs.getString("apellidos"));
                    user.setCedula(rs.getString("cedula"));
                    user.setTelefono(rs.getString("telefono"));
                    user.setDireccion(rs.getString("direccion"));
                    user.setCorreo(rs.getString("correo"));
                    user.setClave(rs.getString("clave"));
                    user.setNotificacion(rs.getInt("notificaciones"));
                    user.setCiudad(rs.getString("ciudad"));
                    user.setFechaNacimiento(rs.getString("Fechanacimiento"));
                    menu += "<li>";
                    // menu+="<a href='"+rs.getString("url")+"'>"+rs.getString("descripcion")+"</a>";
                    menu += rs.getString("nombre");

                    ArrayList<PermisosDTO> permisos = new ArrayList();

                    String querryPermisos = " SELECT p.idPermisos, p.nombre, p.url FROM permisos p "
                            + " INNER JOIN permisosroles pr "
                            + " ON p.idPermisos = pr.permisosId "
                            + " WHERE p.padre = 0;";

                    pstmt = cnn.prepareStatement(querryPermisos);
                    ResultSet rsSub = pstmt.executeQuery();

                    menu += "<ul>";
                    while (rsSub.next()) {

                        menu += "<li>";
                        menu += "<a href='" + url + rsSub.getString("url") + "'>" + rsSub.getString("p.nombre") + url + rsSub.getString("url") + "</a>";
                        menu += "</li>";

                    }

                    ResultSet rsSub3 = cnn.prepareStatement(" SELECT p.idPermisos, p.nombre, p.url "
                            + " FROM permisos p INNER JOIN permisosroles pr ON p.idPermisos = pr.permisosId "
                            + " WHERE padre = " + rs.getInt("idPermisos")
                            + " AND pr.rolesId = " + rs.getInt("idRoles")).executeQuery();

                    menu += "<ul class=\"panel-body\">";
                    while (rsSub.next()) {

                        menu += "<li>";
                        menu += "<a href='" + url + rsSub.getString("url") + "'>" + rsSub.getString("p.nombre") + url + rsSub.getString("url") + "</a>";
                        menu += "</li>";

                    }

                    menu += "</ul>";
                    menu += "</li>";

                }
                menu += "</ul>";
            } else {
                menu = "";
            }
        } catch (SQLException sqle) {

            menu = " error " + sqle.getMessage();

        } finally {
        }
        usuarioValidado.put(user, menu);

        return usuarioValidado;
    }




}
