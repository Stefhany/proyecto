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

   // @Schedule(dayOfMonth = "Monday", hour = "06", minute = "30", persistent = false)
    public void enviarCorreos() {
        FacadeRoles facadeRol = new FacadeRoles();
        List<RolesDTO> roles = new ArrayList();
        roles = facadeRol.enviarCorreosProgramados();
        for (RolesDTO role : roles) {
            if (role.getIdRol() == 1) {
                Mail.sendMail("Novedades de SIGAA", "Hola Asociación: " + role.getUserDto().getNombres() + " <br>"
                        + " Te informamos las ultimás novedades de nuestro sistema, dirigete a: http://www.proyectoscsf.com:3080/proyectosigaa/pages/novedades.jsp <br><br>"
                        + " Gracias por pertenecer a SIGAA <br>"
                        + " Persona encargada: Stefhany Alfonso Rincón <br>"
                        + " Líneas de atención: 3213018539", role.getUserDto().getCorreo());
            } else if (role.getIdRol() == 2) {
                Mail.sendMail("Novedades de SIGAA", "Hola Productor: " + role.getUserDto().getNombres() + " <br>"
                        + " Te informamos las ultimás novedades de nuestro sistema, dirigete a: http://www.proyectoscsf.com:3080/proyectosigaa/pages/novedades.jsp <br><br>"
                        + " Gracias por pertenecer a SIGAA <br>"
                        + " Persona encargada: Stefhany Alfonso Rincón <br>"
                        + " Líneas de atención: 3213018539", role.getUserDto().getCorreo());
            } else if (role.getIdRol() == 3) {
                Mail.sendMail("Novedades de SIGAA", "Hola Distribuidor: " + role.getUserDto().getNombres() + " <br>"
                        + " Te informamos las ultimás novedades de nuestro sistema, dirigete a: http://www.proyectoscsf.com:3080/proyectosigaa/pages/novedades.jsp <br><br>"
                        + " Gracias por pertenecer a SIGAA <br>"
                        + " Persona encargada: Stefhany Alfonso Rincón <br>"
                        + " Líneas de atención: 3213018539", role.getUserDto().getCorreo());
            } else if (role.getIdRol() == 4) {
                Mail.sendMail("Novedades de SIGAA", "Hola Administrador: " + role.getUserDto().getNombres() + " <br>"
                        + " Te informamos las ultimás novedades de nuestro sistema, dirigete a: http://www.proyectoscsf.com:3080/proyectosigaa/pages/novedades.jsp <br><br>"
                        + " Gracias por pertenecer a SIGAA <br>"
                        + " Persona encargada: Stefhany Alfonso Rincón <br>"
                        + " Líneas de atención: 3213018539", role.getUserDto().getCorreo());
            } else {
                System.out.println("No se pudo enviar nada");
            }
        }
    }
}
