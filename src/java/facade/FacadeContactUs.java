/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import daos.ContactUsDAO;
import dtos.ContactUsDTO;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Mona
 */
public class FacadeContactUs {
    private Connection cnn = null;
    private ContactUsDAO contactDao = null;
    private ContactUsDTO contactDto = null;
    
    public FacadeContactUs(){
        cnn = utilities.Connection.getInstance();
        contactDao = new ContactUsDAO();
        contactDto = new ContactUsDTO();
    }
    
    public String insertarMensaje(ContactUsDTO contactDto) throws SQLException{
        return contactDao.insertarContact(contactDto, cnn);
    }
}
