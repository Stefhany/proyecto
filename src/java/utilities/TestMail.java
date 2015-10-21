/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import mail.Mail;

/**
 *
 * @author Stefhany Alfonso
 */
public class TestMail {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        if (Mail.sendMail("Hola","Correo de prueba" , "contactosigaa@gmail.com")) {
            System.out.println("Super");
        }else{
            System.out.println("I'm sorry");
        }

    }
    
}
