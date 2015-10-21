/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import connection.Conectar;
import daos.RolesDAO;
import dtos.RolesDTO;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Mona
 */
public class FacadeRoles {
    private Connection cnn = null;
    private RolesDAO rolDao = null;
    private RolesDTO rolDto = null;
    
    public FacadeRoles(){
//        cnn = Conection.getConnection2();
        rolDao = new RolesDAO();
        rolDto = new RolesDTO();
        cnn = utilities.Connection.getInstance();
    }
    
    public ArrayList<RolesDTO> listarRoles(){
        return rolDao.consultarRoles(cnn);
    }
}
