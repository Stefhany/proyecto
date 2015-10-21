/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import connection.Conectar;
import daos.RolesUsuariosDAO;
import dtos.RolesUsuariosDTO;
import java.sql.Connection;
import java.util.LinkedList;

/**
 *
 * @author Mona
 */
public class FacadeRolesUsuarios {
    private Connection cnn = null;
    private RolesUsuariosDAO rolUserDao = null;
    private RolesUsuariosDTO rolUserDto = null;
    
    public FacadeRolesUsuarios(){
        rolUserDao = new RolesUsuariosDAO();
        rolUserDto = new RolesUsuariosDTO();
//        cnn = Conection.getConnection2();
        //cnn = Conectar.conectar();
        cnn = utilities.Connection.getInstance();
    }
    
    public String registrarRol(RolesUsuariosDTO rol){
        return rolUserDao.insertarRol(rol, cnn);
    }
    
    public  LinkedList<RolesUsuariosDTO> mostrarRol(int idUser){
        return rolUserDao.rol(idUser, cnn);
    }
    
    public String cambiarEstadoUser (int idUser){
        return rolUserDao.cambiarEstadoUsuario(idUser, cnn);
    }
          
}
