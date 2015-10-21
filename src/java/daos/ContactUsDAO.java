/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.CategoriaDTO;
import dtos.ContactUsDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Mona
 */
public class ContactUsDAO {
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;   
    private Connection cnn = null;

    public String insertarContact(ContactUsDTO conctat, Connection cnn) throws SQLException {
        this.cnn = cnn;
        String msgSalida; 
        int rtdo;  
        pstmt = cnn.prepareStatement("INSERT INTO mensajes VALUES (NULL, ?,?,?);");
        pstmt.setString(1, conctat.getNombrePersona());
        pstmt.setString(2, conctat.getCorreo());
        pstmt.setInt(3, conctat.getTelefono());
        rtdo = pstmt.executeUpdate();
        if (rtdo > 0) {
            msgSalida = "ok";
        } else {
            msgSalida = "no";
        }
        return msgSalida;
    }
}
