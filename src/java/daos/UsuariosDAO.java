/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.UsuariosDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import dtos.RolesDTO;
import java.sql.CallableStatement;
import utilities.MyException;

/**
 *
 * @author Sena
 */
public class UsuariosDAO {

    private Connection cnn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private CallableStatement cllstmt = null;

    public String ingresarRegistro(UsuariosDTO usuario, Connection cnn) {
        this.cnn = cnn;
        int rtdo = 0;
        String msgSalida = "";
        try {
            pstmt = cnn.prepareStatement("INSERT INTO usuarios VALUES (null,?, ?, ?, ?, ?, ?, md5(?), ?, ?, ?, 0)");
            pstmt.setString(1, usuario.getNombres());
            pstmt.setString(2, usuario.getApellidos());
            pstmt.setInt(3, usuario.getCedula());
            pstmt.setInt(4, usuario.getTelefono());
            pstmt.setString(5, usuario.getDireccion());
            pstmt.setString(6, usuario.getCorreo());
            pstmt.setString(7, usuario.getClave());
            pstmt.setInt(8, usuario.getNotificacion());
            pstmt.setString(9, usuario.getCiudad());
            pstmt.setString(10, usuario.getFechaNacimiento());
            //mensaje = pstmt.toString();
            rtdo = pstmt.executeUpdate();
            if (rtdo != 0) {
                msgSalida = "ok";
            } else {
                msgSalida = "No se ha registrado ";
            }
        } catch (SQLException sd) {
            msgSalida = " ocurrido un error en " + sd.getMessage();
        }
        return msgSalida;
    }

    public String modificarUsuario(UsuariosDTO usuario, Connection cnn) throws MyException {
        this.cnn = cnn;
        String msgSalida = "";
        int res = 0;
        try {
            String queryUpDateUser = " UPDATE usuarios SET telefono=?, "
                    + " direccion=?, correo=?, "
                    + " ciudad=? WHERE idUsuarios=?;";
            pstmt = cnn.prepareStatement(queryUpDateUser);
            pstmt.setInt(1, usuario.getTelefono());
            pstmt.setString(2, usuario.getDireccion());
            pstmt.setString(3, usuario.getCorreo());
            pstmt.setString(4, usuario.getCiudad());
            pstmt.setInt(5, usuario.getIdUsuarios());
            res = pstmt.executeUpdate();

            if (res != 0) {
                msgSalida = "El campo se ha modificado: " + res + " satisfactoriamente.";
            } else {
                msgSalida = "Error";
            }
        } catch (SQLException sqle) {
            throw new MyException("Lo siento, ocurrio lo siguiente: " + sqle.getSQLState() + " y " + sqle.getMessage());
        }
        return msgSalida;
    }

    public String eliminarUsuario(int id, Connection cnn) {
        this.cnn = cnn;
        int rtdo = 0;
        String msgSalida = "";
        try {
            pstmt = cnn.prepareStatement("delete from usuarios where idUsuarios=?");
            pstmt.setInt(1, id);
            rtdo = pstmt.executeUpdate();

            if (rtdo != 0) {
                msgSalida = "El registro se elimino corretamente";
            }
        } catch (SQLException sqlexception) {
            msgSalida = "Ocurrio un error" + sqlexception.getMessage();
        }
        return msgSalida;
    }

    public ArrayList<UsuariosDTO> consultarRegistros(Connection cnn) {
        this.cnn = cnn;
        ArrayList<UsuariosDTO> listaUsuarios = new ArrayList<UsuariosDTO>();
        try {
            pstmt = cnn.prepareStatement("SELECT idUsuarios, nombres, apellidos, cedula, telefono, direccion, "
                    + " correo, clave, notificaciones, ciudad, fechaNacimiento FROM usuarios WHERE estadoUser = 0;");
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UsuariosDTO user = new UsuariosDTO();
                    user.setIdUsuarios(rs.getInt("idUsuarios"));
                    user.setNombres(rs.getString("nombres"));
                    user.setApellidos(rs.getString("apellidos"));
                    user.setCedula(rs.getInt("cedula"));
                    user.setTelefono(rs.getInt("telefono"));
                    user.setDireccion(rs.getString("direccion"));
                    user.setCorreo(rs.getString("correo"));
                    user.setClave(rs.getString("clave"));
                    user.setNotificacion(rs.getInt("notificaciones"));
                    user.setCiudad(rs.getString("ciudad"));
                    user.setFechaNacimiento(rs.getString("fechaNacimiento"));
                    listaUsuarios.add(user);
                }
            } else {
                System.out.println("No se encontraron registros..");
            }
        } catch (SQLException sqle) {
            System.out.println("Se ha producido la sig excepción: " + sqle.getMessage());
        }
        return listaUsuarios;
    }

    public UsuariosDTO consultarUnRegistro(int id, Connection cnn) {
        this.cnn = cnn;
        UsuariosDTO user = new UsuariosDTO();
        try {
            pstmt = cnn.prepareStatement("SELECT idUsuarios, nombres, apellidos, cedula, telefono, "
                    + " direccion, correo, clave, notificaciones,ciudad, fechanacimiento "
                    + " FROM usuarios WHERE idUsuarios = ?;");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    user.setIdUsuarios(rs.getInt("idUsuarios"));
                    user.setNombres(rs.getString("nombres"));
                    user.setApellidos(rs.getString("apellidos"));
                    user.setCedula(rs.getInt("cedula"));
                    user.setTelefono(rs.getInt("telefono"));
                    user.setDireccion(rs.getString("direccion"));
                    user.setCorreo(rs.getString("correo"));
                    user.setClave(rs.getString("clave"));
                    user.setNotificacion(rs.getInt("notificaciones"));
                    user.setCiudad(rs.getString("ciudad"));
                    user.setFechaNacimiento(rs.getString("Fechanacimiento"));
                }
            } else {
                System.out.println("No se encontraron registros...");
            }
        } catch (SQLException sqle) {
            System.out.println("eyy " + sqle.getMessage());
        }
        return user;
    }

    public UsuariosDTO validarUsuario(String correo, String pass, Connection cnn) {
        this.cnn = cnn;
        UsuariosDTO udto = new UsuariosDTO();
        try {
            String querryValidation = " select idUsuarios as id, nombres, apellidos, cedula, telefono, "
                    + " direccion, correo, clave, notificaciones, ciudad, fechaNacimiento "
                    + " from usuarios where correo =? and clave=md5(?) ";
            pstmt = cnn.prepareStatement(querryValidation);
            pstmt.setString(1, correo);
            pstmt.setString(2, pass);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    udto.setIdUsuarios(rs.getInt("id"));
                    udto.setNombres(rs.getString("nombres"));
                    udto.setApellidos(rs.getString("apellidos"));
                    udto.setCedula(rs.getInt("cedula"));
                    udto.setTelefono(rs.getInt("telefono"));
                    udto.setDireccion(rs.getString("direccion"));
                    udto.setCorreo(rs.getString("correo"));
                    udto.setClave(rs.getString("clave"));
                    udto.setNotificacion(rs.getInt("notificaciones"));
                    udto.setCiudad(rs.getString("ciudad"));
                    udto.setFechaNacimiento(rs.getString("fechaNacimiento"));
                }
            } else {
                udto = null;
            }
        } catch (SQLException ex) {
            System.out.println("Datos errones.. !!reviselos!!" + ex.getSQLState() + " - " + ex.getMessage());
        }
        return udto;
    }

    public String insertarAsociacion(
            String nombres,
            String apellidos,
            int cedula,
            int telefono,
            String direccion,
            String correo,
            String clave,
            int notificacion,
            String ciudad,
            String fechaNacimiento, Connection cn) {
        this.cnn = cn;
        int salida = 0;
        String msgSalida = "";
        try {
            String procedureInsert = "{call ps_registrarAsociacionV5 (?,?,?,?,?,?,?,?,?,?,?)}";
            cllstmt = cnn.prepareCall(procedureInsert);
            cllstmt.setString(1, nombres);
            cllstmt.setString(2, apellidos);
            cllstmt.setInt(3, cedula);
            cllstmt.setInt(4, telefono);
            cllstmt.setString(5, direccion);
            cllstmt.setString(6, correo);
            cllstmt.setString(7, clave);
            cllstmt.setInt(8, notificacion);
            cllstmt.setString(9, ciudad);
            cllstmt.setString(10, fechaNacimiento);
            cllstmt.registerOutParameter(11, java.sql.Types.INTEGER);
            cllstmt.execute();
            salida = cllstmt.getInt(11);

            if (salida == 1) {
                msgSalida = "ok";
            } else {
                msgSalida = "no";
            }
        } catch (SQLException sqle) {
            msgSalida = "Pilas! Ocurrio la siguiente excepción: " + sqle.getMessage();
        }
        return msgSalida;
    }

    public ArrayList<UsuariosDTO> mostrarPrueba(Connection cnn) {
        this.cnn = cnn;
        ArrayList<UsuariosDTO> usuarios = new ArrayList();
        try {
            String querryPrueba = ("SELECT nombres, apellidos, cedula, telefono FROM usuarios;");
            pstmt = cnn.prepareStatement(querryPrueba);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UsuariosDTO user = new UsuariosDTO();
                    user.setNombres(rs.getString("nombres"));
                    user.setApellidos(rs.getString("apellidos"));
                    user.setCedula(rs.getInt("cedula"));
                    user.setTelefono(rs.getInt("telefono"));
                    usuarios.add(user);
                }
            }
        } catch (SQLException sqle) {
            System.out.println("Ha ocurrido esto ! " + sqle.getMessage());
        }
        return usuarios;
    }

    public HashMap<UsuariosDTO, String> validarUsuarioV2(String correo, String pss, Connection cnn) {
        this.cnn = cnn;
        String menu = "";
        HashMap<UsuariosDTO, String> usuarioValidado = new HashMap<UsuariosDTO, String>();
        UsuariosDTO user = new UsuariosDTO();
        ResultSet rs = null;
        try {
            pstmt = cnn.prepareStatement(" SELECT u.idUsuarios, u.nombres,u.apellidos, u.cedula, u.telefono, "
                    + " u.direccion, u.correo, u.clave, u.notificaciones, u.ciudad, u.fechaNacimiento, "
                    + " u.estadoUser, r.idRoles, p.nombre, p.url, p.idPermisos "
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
                    user.setCedula(rs.getInt("cedula"));
                    user.setTelefono(rs.getInt("telefono"));
                    user.setDireccion(rs.getString("direccion"));
                    user.setCorreo(rs.getString("correo"));
                    user.setClave(rs.getString("clave"));
                    user.setNotificacion(rs.getInt("notificaciones"));
                    user.setCiudad(rs.getString("ciudad"));
                    user.setFechaNacimiento(rs.getString("Fechanacimiento"));
                    user.setEstado(rs.getInt("estadoUser"));
                    menu += "<li>";
                    // menu+="<a href='"+rs.getString("url")+"'>"+rs.getString("descripcion")+"</a>";
                    menu += rs.getString("nombre");
                    ResultSet rsSub = cnn.prepareStatement(" SELECT p.idPermisos, p.nombre, p.url "
                            + " FROM permisos p INNER JOIN permisosroles pr ON p.idPermisos = pr.permisosId "
                            + " WHERE padre = " + rs.getInt("idPermisos")
                            + " AND pr.rolesId = " + rs.getInt("idRoles")).executeQuery();

                    menu += "<ul class=\"children\"";
                    while (rsSub.next()) {
                        menu += "<li>";
                        menu += "<a href='" + rsSub.getString("url") + "'>" + rsSub.getString("p.nombre") + "</a>";
                        menu += "</li>";
                    }

                    menu += "</ul>";
                    menu += "</li>";

                }

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

    public StringBuilder validarCorreo(String correo, Connection cn) throws MyException {
        this.cnn = cn;
        StringBuilder salida = new StringBuilder("");
        try {
            //cnn = Conectar.getInstance();
            pstmt = cnn.prepareStatement("SELECT idUsuarios, nombres, apellidos, cedula, telefono, direccion, "
                    + " correo, clave, notificaciones, ciudad, fechaNacimiento FROM usuarios"
                    + " WHERE correo=?");
            pstmt.setString(1, correo);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                salida.append("El correo ' ").append(correo).append(" ' ya se encuentra registrado!");
            } else {
                salida.append("El correo esta disponible");
            }

        } catch (SQLException sqle) {
            throw new MyException("Error de My SQL" + sqle.getErrorCode() + " " + sqle.getMessage());
        }
        return salida;
    }

    public StringBuilder validarCorreo2(String correo, Connection cnn) throws MyException {
        this.cnn = cnn;
        StringBuilder salida = new StringBuilder("");
        try {
            //cnn = Conectar.getInstance();
            pstmt = cnn.prepareStatement("SELECT idUsuarios, nombres, apellidos, cedula, telefono, direccion, "
                    + " correo, clave, notificaciones, ciudad, fechaNacimiento FROM usuarios"
                    + " WHERE correo=?");
            pstmt.setString(1, correo);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                salida.append("El correo ' ").append(correo).append(" ' se encuentra registrado!");
            } else {
                salida.append("El correo no se encuentra registrado.");
            }

        } catch (SQLException sqle) {
            throw new MyException("Error de My SQL" + sqle.getErrorCode() + " " + sqle.getMessage());
        }
        return salida;
    }

    public StringBuilder validarCedula(int cedula, Connection cn) throws MyException {
        this.cnn = cn;
        StringBuilder salida = new StringBuilder("");
        try {
            //cnn = Conectar.getInstance();
            pstmt = cnn.prepareStatement("SELECT idUsuarios, nombres, apellidos, cedula, telefono, direccion, "
                    + " correo, clave, notificaciones, ciudad, fechaNacimiento FROM usuarios"
                    + " WHERE cedula=?");
            pstmt.setInt(1, cedula);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                salida.append("La cédula ' ").append(cedula).append(" ' ya se encuentra registrada!");
            } else {
                salida.append("Cédula disponible.");
            }

        } catch (SQLException sqle) {
            throw new MyException("Error de My SQL" + sqle.getErrorCode() + " " + sqle.getMessage());
        }
        return salida;
    }

    public String obtenerCorreoPorId(int idUser, int rolId, Connection cn) {
        this.cnn = cn;
        String mensaje = "";
        String consult = " SELECT correo "
                + " FROM usuarios "
                + " inner join `rolesusuarios` on "
                + " `usuarios`.`idUsuarios` = `rolesusuarios`.`usuariosId` "
                + " WHERE `usuarios`.`idUsuarios` = ? AND `rolesusuarios`.`rolesId` = 1 ";
        try {
            pstmt = cnn.prepareStatement(consult);
            pstmt.setInt(1, idUser);
            pstmt.setInt(2, rolId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                mensaje = rs.getString("correo");
            }

        } catch (SQLException ex) {
            mensaje = "Error, detalle: " + ex.getMessage();
        }
        return mensaje;
    }

    public String modificarClave(UsuariosDTO usuario, Connection cn) throws MyException {
        this.cnn = cn;
        //cnn = Conectar.getInstance();
        String msgSalida = "";
        int res = 0;
        try {
            String queryUpDateUser = " UPDATE usuarios SET clave=md5(?) WHERE idUsuarios=?;";
            pstmt = cnn.prepareStatement(queryUpDateUser);
            pstmt.setString(1, usuario.getClave());
            pstmt.setInt(2, usuario.getIdUsuarios());
            res = pstmt.executeUpdate();

            if (res != 0) {
                msgSalida = "ok";
            } else {
                msgSalida = "no";
            }
        } catch (SQLException sqle) {
            throw new MyException("Lo siento, ocurrio lo siguiente: " + sqle.getSQLState() + " y " + sqle.getMessage());
        }
        return msgSalida;
    }

    public int buscarIdUsuarioCorreoRestablecerClave(String correo, Connection cn) {
        this.cnn = cn;

        int res = 0;

        try {
            String queryBuscarIdUsuario = "select idusuarios as id from usuarios where correo = ?;";
            pstmt = cnn.prepareStatement(queryBuscarIdUsuario);
            pstmt.setString(1, correo);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    res = rs.getInt("id");
                }
            }
        } catch (SQLException sqle) {
            res = 0;
        }
        return res;
    }

    public StringBuilder validarClaveUser(String clave, int idUsuario, Connection cn) throws MyException {
        this.cnn = cn;
        StringBuilder salida = new StringBuilder("");
        try {
            //cnn = Conectar.getInstance();
            pstmt = cnn.prepareStatement("SELECT idUsuarios, nombres, apellidos, cedula, telefono, direccion, "
                    + " correo, clave, notificaciones, ciudad, fechaNacimiento FROM usuarios"
                    + " WHERE clave=md5(?) and idUsuarios = ?");
            pstmt.setString(1, clave);
            pstmt.setInt(2, idUsuario);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                salida.append("Correcta");
            } else {
                salida.append("La contraseña no coincide con la actual.");
            }

        } catch (SQLException sqle) {
            throw new MyException("Error de My SQL" + sqle.getErrorCode() + " " + sqle.getMessage());
        }
        return salida;
    }

    public String deshabilitarUsuario(int idUsuario, Connection cc) {
        this.cnn = cc;
        int res = 0;
        String msgSalida = "";
        try {
            String querryCambiarEstadoUser = "update usuarios set estadoUser = 3 where idusuarios = ?;";
            pstmt = cnn.prepareStatement(querryCambiarEstadoUser);
            pstmt.setInt(1, idUsuario);
            res = pstmt.executeUpdate();
            if (res != 0) {
                msgSalida = "ok";
            } else {
                msgSalida = "no";
            }
        } catch (SQLException sqle) {
            msgSalida = "Mira lo que ocurrio: " + sqle.getMessage();
        }

        return msgSalida;
    }

    public ArrayList<UsuariosDTO> consultarAllRegistros(Connection cnn) {
        this.cnn = cnn;
        ArrayList<UsuariosDTO> listaUsuarios = new ArrayList<UsuariosDTO>();
        try {
            pstmt = cnn.prepareStatement("SELECT idUsuarios, nombres, apellidos, cedula, telefono, direccion, "
                    + " correo, clave, notificaciones, ciudad, fechaNacimiento FROM usuarios;");
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UsuariosDTO user = new UsuariosDTO();
                    user.setIdUsuarios(rs.getInt("idUsuarios"));
                    user.setNombres(rs.getString("nombres"));
                    user.setApellidos(rs.getString("apellidos"));
                    user.setCedula(rs.getInt("cedula"));
                    user.setTelefono(rs.getInt("telefono"));
                    user.setDireccion(rs.getString("direccion"));
                    user.setCorreo(rs.getString("correo"));
                    user.setClave(rs.getString("clave"));
                    user.setNotificacion(rs.getInt("notificaciones"));
                    user.setCiudad(rs.getString("ciudad"));
                    user.setFechaNacimiento(rs.getString("fechaNacimiento"));
                    listaUsuarios.add(user);
                }
            } else {
                System.out.println("No se encontraron registros..");
            }
        } catch (SQLException sqle) {
            System.out.println("Se ha producido la sig excepción: " + sqle.getMessage());
        }
        return listaUsuarios;
    }

    public ArrayList<UsuariosDTO> consultarRegistrosEstadoDos(Connection cnn) {
        this.cnn = cnn;
        ArrayList<UsuariosDTO> listaUsuarios = new ArrayList<UsuariosDTO>();
        try {
            pstmt = cnn.prepareStatement("SELECT idUsuarios, nombres, apellidos, cedula, telefono, direccion, "
                    + " correo, clave, notificaciones, ciudad, fechaNacimiento, estadoUser FROM usuarios WHERE estadoUser = 2;");
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UsuariosDTO user = new UsuariosDTO();
                    user.setIdUsuarios(rs.getInt("idUsuarios"));
                    user.setNombres(rs.getString("nombres"));
                    user.setApellidos(rs.getString("apellidos"));
                    user.setCedula(rs.getInt("cedula"));
                    user.setTelefono(rs.getInt("telefono"));
                    user.setDireccion(rs.getString("direccion"));
                    user.setCorreo(rs.getString("correo"));
                    user.setClave(rs.getString("clave"));
                    user.setNotificacion(rs.getInt("notificaciones"));
                    user.setCiudad(rs.getString("ciudad"));
                    user.setFechaNacimiento(rs.getString("fechaNacimiento"));
                    user.setEstado(rs.getInt("estadoUser"));
                    listaUsuarios.add(user);
                }
            } else {
                System.out.println("No se encontraron registros..");
            }
        } catch (SQLException sqle) {
            System.out.println("Se ha producido la sig excepción: " + sqle.getMessage());
        }
        return listaUsuarios;
    }

    public ArrayList<UsuariosDTO> consultarRegistrosEstadoTres(Connection cnn) {
        this.cnn = cnn;
        ArrayList<UsuariosDTO> listaUsuarios = new ArrayList<UsuariosDTO>();
        try {
            pstmt = cnn.prepareStatement("SELECT idUsuarios, nombres, apellidos, cedula, telefono, direccion, "
                    + " correo, clave, notificaciones, ciudad, fechaNacimiento FROM usuarios WHERE estadoUser = 3;");
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UsuariosDTO user = new UsuariosDTO();
                    user.setIdUsuarios(rs.getInt("idUsuarios"));
                    user.setNombres(rs.getString("nombres"));
                    user.setApellidos(rs.getString("apellidos"));
                    user.setCedula(rs.getInt("cedula"));
                    user.setTelefono(rs.getInt("telefono"));
                    user.setDireccion(rs.getString("direccion"));
                    user.setCorreo(rs.getString("correo"));
                    user.setClave(rs.getString("clave"));
                    user.setNotificacion(rs.getInt("notificaciones"));
                    user.setCiudad(rs.getString("ciudad"));
                    user.setFechaNacimiento(rs.getString("fechaNacimiento"));
                    listaUsuarios.add(user);
                }
            } else {
                System.out.println("No se encontraron registros..");
            }
        } catch (SQLException sqle) {
            System.out.println("Se ha producido la sig excepción: " + sqle.getMessage());
        }
        return listaUsuarios;
    }

    public ArrayList<RolesDTO> consultarRegistrosEstadoUnoDeAsociacion(Connection cnn) {
        this.cnn = cnn;
        ArrayList<RolesDTO> listaAsociaciones = new ArrayList<RolesDTO>();
        try {
            String querryDeshabilitarAsociaciones = "SELECT idUsuarios, nombres, apellidos, cedula, telefono, "
                    + " direccion, correo, clave, notificaciones, ciudad, fechaNacimiento, idroles,estadoUser"
                    + " FROM usuarios "
                    + " INNER JOIN `rolesusuarios` on `rolesusuarios`.`usuariosId` = `usuarios`.`idUsuarios`"
                    + " INNER JOIN `roles` on `rolesusuarios`.`rolesId` = `roles`.`idRoles`"
                    + " WHERE estadoUser = 1"
                    + " AND idRoles = 1";
            pstmt = cnn.prepareStatement(querryDeshabilitarAsociaciones);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UsuariosDTO user = new UsuariosDTO();
                    user.setIdUsuarios(rs.getInt("idUsuarios"));
                    user.setNombres(rs.getString("nombres"));
                    user.setApellidos(rs.getString("apellidos"));
                    user.setCedula(rs.getInt("cedula"));
                    user.setTelefono(rs.getInt("telefono"));
                    user.setDireccion(rs.getString("direccion"));
                    user.setCorreo(rs.getString("correo"));
                    user.setClave(rs.getString("clave"));
                    user.setNotificacion(rs.getInt("notificaciones"));
                    user.setCiudad(rs.getString("ciudad"));
                    user.setFechaNacimiento(rs.getString("fechaNacimiento"));
                    user.setEstado(rs.getInt("estadoUser"));
                    RolesDTO rol = new RolesDTO(user);
                    listaAsociaciones.add(rol);
                }
            } else {
                System.out.println("No se encontraron registros..");
            }
        } catch (SQLException sqle) {
            System.out.println("Se ha producido la sig excepción: " + sqle.getMessage());
        }
        return listaAsociaciones;
    }

    public ArrayList<RolesDTO> consultarRegistrosEstadoTresDeAsociacion(Connection cnn) {
        this.cnn = cnn;
        ArrayList<RolesDTO> listaAsociaciones = new ArrayList<RolesDTO>();
        try {
            String querryDeshabilitarAsociaciones = "SELECT idUsuarios, nombres, apellidos, cedula, telefono, "
                    + " direccion, correo, clave, notificaciones, ciudad, fechaNacimiento, idroles,estadoUser"
                    + " FROM usuarios "
                    + " INNER JOIN `rolesusuarios` on `rolesusuarios`.`usuariosId` = `usuarios`.`idUsuarios`"
                    + " INNER JOIN `roles` on `rolesusuarios`.`rolesId` = `roles`.`idRoles`"
                    + " WHERE estadoUser = 3"
                    + " AND idRoles = 1";
            pstmt = cnn.prepareStatement(querryDeshabilitarAsociaciones);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UsuariosDTO user = new UsuariosDTO();
                    user.setIdUsuarios(rs.getInt("idUsuarios"));
                    user.setNombres(rs.getString("nombres"));
                    user.setApellidos(rs.getString("apellidos"));
                    user.setCedula(rs.getInt("cedula"));
                    user.setTelefono(rs.getInt("telefono"));
                    user.setDireccion(rs.getString("direccion"));
                    user.setCorreo(rs.getString("correo"));
                    user.setClave(rs.getString("clave"));
                    user.setNotificacion(rs.getInt("notificaciones"));
                    user.setCiudad(rs.getString("ciudad"));
                    user.setFechaNacimiento(rs.getString("fechaNacimiento"));
                    user.setEstado(rs.getInt("estadoUser"));
                    RolesDTO rol = new RolesDTO(user);
                    listaAsociaciones.add(rol);
                }
            } else {
                System.out.println("No se encontraron registros..");
            }
        } catch (SQLException sqle) {
            System.out.println("Se ha producido la sig excepción: " + sqle.getMessage());
        }
        return listaAsociaciones;
    }

    public String habilitarUsuario(int idUsuario, Connection cc) {
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
            } else {
                msgSalida = "no";
            }
        } catch (SQLException sqle) {
            msgSalida = "Mira lo que ocurrio: " + sqle.getMessage();
        }

        return msgSalida;
    }

    public String habilitarAsociacion(int idUsuario, Connection cc) {
        this.cnn = cc;
        int res = 0;
        String msgSalida = "";
        try {
            String querryCambiarEstadoUser = "update usuarios set estadoUser = 1 where idusuarios = ?;";
            pstmt = cnn.prepareStatement(querryCambiarEstadoUser);
            pstmt.setInt(1, idUsuario);
            res = pstmt.executeUpdate();
            if (res != 0) {
                msgSalida = "ok";
            } else {
                msgSalida = "no";
            }
        } catch (SQLException sqle) {
            msgSalida = "Mira lo que ocurrio: " + sqle.getMessage();
        }

        return msgSalida;
    }

    public String recuperarClave(String clave, int codigo, Connection cn) throws MyException {
        this.cnn = cn;
        //cnn = Conectar.getInstance();
        String msgSalida = "";
        int res = 0;
        try {
            String queryUpDateUser = " UPDATE usuarios SET clave=md5(?) WHERE idUsuarios=?;";
            pstmt = cnn.prepareStatement(queryUpDateUser);
            pstmt.setString(1, clave);
            pstmt.setInt(2, codigo);
            res = pstmt.executeUpdate();

            if (res != 0) {
                msgSalida = "ok";
            } else {
                msgSalida = "no";
            }
        } catch (SQLException sqle) {
            throw new MyException("Lo siento, ocurrio lo siguiente: " + sqle.getSQLState() + " y " + sqle.getMessage());
        }
        return msgSalida;
    }

    public String confirmarRecupecionClaveCorreo(int iduser, Connection cn) {
        this.cnn = cn;
        String res = "";
        try {
            String queryConsultConfirmation = "select correo from usuarios where idusuarios = ? + 2;";
        pstmt = cnn.prepareStatement(queryConsultConfirmation);
        pstmt.setInt(1, iduser);
        rs = pstmt.executeQuery();

        if (rs != null) {
            while (rs.next()) {
                res = rs.getString("correo");
            }
        }
        }catch(SQLException sqle){
            res = "Ocurrio la siguiente exepción: "+sqle.getMessage();
        }
        return res;
    }
    
    public String confirmarRecupecionClaveNombreUser(int iduser, Connection cn) {
        this.cnn = cn;
        String res = "";
        try {
            String queryConsultConfirmation = "select nombres from usuarios where idusuarios = ? + 2;";
        pstmt = cnn.prepareStatement(queryConsultConfirmation);
        pstmt.setInt(1, iduser);
        rs = pstmt.executeQuery();

        if (rs != null) {
            while (rs.next()) {
                res = rs.getString("nombres");
            }
        }
        }catch(SQLException sqle){
            res = "Ocurrio la siguiente exepción: "+sqle.getMessage();
        }
        return res;
    }
    
    public String buscarCorreoPorId(int iduser, Connection cn) {
        this.cnn = cn;
        String res = "";
        try {
            String queryConsultConfirmation = "select correo from usuarios where idusuarios = ?;";
        pstmt = cnn.prepareStatement(queryConsultConfirmation);
        pstmt.setInt(1, iduser);
        rs = pstmt.executeQuery();

        if (rs != null) {
            while (rs.next()) {
                res = rs.getString("correo");
            }
        }
        }catch(SQLException sqle){
            res = "Ocurrio la siguiente exepción: "+sqle.getMessage();
        }
        return res;
    }
}
