/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import connection.Conectar;
import daos.UsuariosDAO;
import dtos.RolesDTO;
import dtos.UsuariosDTO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import utilities.MyException;

/**
 *
 * @author Mona
 */
public class FacadeUsuarios {
    private Connection cnn = null;
    private UsuariosDAO userDao = null;
    private UsuariosDTO userDto = null;
    
    public FacadeUsuarios(){
        userDao = new UsuariosDAO();
        userDto = new UsuariosDTO();
        cnn = utilities.Connection.getInstance();
    }
    
    public String ingresarRegistro(UsuariosDTO usuario){
        return userDao.ingresarRegistro(usuario, cnn);
    }
    
    public String modificarUsuario(UsuariosDTO usuario) throws MyException{
        return userDao.modificarUsuario(usuario, cnn);
    }
    
    public String eliminarUsuario(int id){
        return userDao.eliminarUsuario(id, cnn);
    }
    
    public ArrayList<UsuariosDTO> consultarRegistros(){
        return userDao.consultarRegistros(cnn);
    }
    
    public UsuariosDTO consultarUnRegistro(int id){
        return userDao.consultarUnRegistro(id, cnn);
    }
    
    public UsuariosDTO validarUsuario(String correo, String pass){
        return userDao.validarUsuario(correo, pass, cnn);
    }
    
    public ArrayList<UsuariosDTO> mostrarPrueba(){
        return userDao.mostrarPrueba(cnn);
    }
    
    public HashMap<UsuariosDTO, String> validarUsuarioV2(String correo, String pss){
        return userDao.validarUsuarioV2(correo, pss, cnn);
    }
    
    public String deshabilitarUser(int idUser){
        return userDao.deshabilitarUsuario(idUser, cnn);
    }
    
    public ArrayList<UsuariosDTO> consultarTodosUsuarios(){
        return userDao.consultarAllRegistros(cnn);
    }
    
    public ArrayList<UsuariosDTO> consultarRegistrosHabilitados(){
        return userDao.consultarRegistrosEstadoDos(cnn);
    }
    
    public ArrayList<UsuariosDTO> consultarRegistrosDeshabilitados(){
        return userDao.consultarRegistrosEstadoTres(cnn);
    }
    
    public ArrayList<RolesDTO> consultarAsociacionesHabilitadas(){
        return userDao.consultarRegistrosEstadoUnoDeAsociacion(cnn);
    }
    
    public ArrayList<RolesDTO> consultarAsociacionesDeshabilitadas(){
        return userDao.consultarRegistrosEstadoTresDeAsociacion(cnn);
    }
    
    public String habilitarUser(int idUsuario){
        return userDao.habilitarUsuario(idUsuario, cnn);
    }
    
    public String habilitarAsociacion(int idUser){
        return userDao.habilitarAsociacion(idUser, cnn);
    }
    
    public String registrarAsociacion(String nombres, String apellidos, int cedula, int tel, 
            String direccion, String correo, 
            String clave, int notificacion, String ciudad, String fechaNacimiento){
        return userDao.insertarAsociacion(nombres, apellidos, cedula, tel, direccion, correo, clave, notificacion, ciudad, fechaNacimiento, cnn);
    } 
    
    public StringBuilder validarCorreo (String correo) throws MyException{
        return userDao.validarCorreo(correo, cnn);
    }
    
    public StringBuilder validarCorreo2 (String email) throws MyException{
        return userDao.validarCorreo2(email, cnn);
    }
    
    public StringBuilder validarCedula (int cc) throws MyException{
        return userDao.validarCedula(cc, cnn);
    }
    
    public String obtenerCorreoPorId (int idUser, int rolId){
        return userDao.obtenerCorreoPorId(idUser, rolId, cnn);
    }
    
    public String modificarClave (UsuariosDTO userDto) throws MyException{
        return userDao.modificarClave(userDto, cnn);
    }
    
    public StringBuilder validarClaveUsuario(String clave, int idUser) throws MyException{
        return userDao.validarClaveUser(clave, idUser, cnn);
    }
    
    public int buscarIdUser(String correo){
        return userDao.buscarIdUsuarioCorreoRestablecerClave(correo, cnn);
    }
    
    public String recuperarClave (String clave, int code) throws MyException{
        return userDao.recuperarClave(clave, code, cnn);
    }
    
    public String confirmarRecuperacionClaveCorreo(int iduser){
        return userDao.confirmarRecupecionClaveCorreo(iduser, cnn);
    }
    
    public String confirmarRecuperacionClaveNameUser(int iduser){
        return userDao.confirmarRecupecionClaveNombreUser(iduser, cnn);
    }
    
    public String buscarCorreoPorId(int iduser){
        return userDao.buscarCorreoPorId(iduser, cnn);
    }
}

