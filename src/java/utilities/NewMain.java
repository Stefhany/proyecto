/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import dtos.UsuariosDTO;
import facade.FacadeProductosAsociadosUsuarios;
import facade.FacadeUsuarios;
import java.util.ArrayList;
import java.util.List;
import mail.Mail;

/**
 *
 * @author Stefhany Alfonso
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FacadeUsuarios facadeUsuarios = new FacadeUsuarios();
        FacadeProductosAsociadosUsuarios facadeProductAsociado = new FacadeProductosAsociadosUsuarios();
        StringBuilder correos = new StringBuilder("");
        List<UsuariosDTO> users = new ArrayList();
                
        users =facadeProductAsociado.enviarCorreoAProductores(3);
        
        for (UsuariosDTO user : users) {
            correos.append(user.getCorreo());
            correos.append(", ");
        }
        
        System.out.println(correos.toString());
        
        
    }

}
