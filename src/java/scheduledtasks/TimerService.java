/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduledtasks;

import dtos.RolesDTO;
import facade.FacadeRoles;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import mail.Mail;

/**
 *
 * @author Stefhany Alfonso
 */
@Singleton
public class TimerService {
    
    @Schedule(second="50", minute="05",hour="*", persistent=false)
    public void enviarCorreos(){
        FacadeRoles facadeRol = new FacadeRoles();
        List<RolesDTO> roles = new ArrayList();
        roles = facadeRol.enviarCorreosProgramados();
        for (RolesDTO role : roles) {
            if (role.getIdRol() == 1) {
                Mail.sendMail("prueba", "prueb de asociacios", role.getUserDto().getCorreo());
            }else if (role.getIdRol() == 2) {
                Mail.sendMail("prueba", "prueba de productor", role.getUserDto().getCorreo());
            }else if (role.getIdRol() == 3) {
                Mail.sendMail("prueba", "prueba de distribuidor", role.getUserDto().getCorreo());
            }else if (role.getIdRol() == 4) {
                Mail.sendMail("prueba", "prueba de admin", role.getUserDto().getCorreo());
            }else{
                System.out.println("No se pudo enviar nada");
            }
        }
    }
}
