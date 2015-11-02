/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Stefhany Alfonso
 */
public class Connection {
    protected static java.sql.Connection cnn = null;

    private Connection() {
    }

    public static void conectar() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sigaa", "stefhany", "123");
        } catch (SQLException sqle) {
            System.out.println("Error Inesperado" + sqle);

        } catch (Exception ex) {
            System.out.println("Error de MySql" + ex);

        }
    }

    public static java.sql.Connection getInstance() {

        if (cnn == null) {
            conectar();
        }else{
        }
        return cnn;

    }
}
