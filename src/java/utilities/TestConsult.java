/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import daos.Consultas;
import daos.UsuariosDAO;
import dtos.EstadoUsuarioDTO;
import dtos.ProductosAsociadosUsuariosDTO;
import dtos.UsuariosDTO;
import facade.FacadeProductosAsociadosUsuarios;
import facade.FacadeUsuarios;
import java.util.LinkedList;

/**
 *
 * @author Stefhany Alfonso
 */
public class TestConsult {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MyException {
        // TODO code application logic here
        /**UsuariosDTO user = new UsuariosDTO();
        user.setIdUsuarios(20);
        user.setNombres("consola");
        user.setApellidos("consola");
        user.setCedula(123);
        user.setTelefono(123);
        user.setDireccion("consola");
        user.setCorreo("consola");
        user.setClave("123");
        user.setNotificacion(1);
        user.setCiudad("consola");
        user.setFechaNacimiento("1890-11-23");
        
        FacadeUsuarios facade = new FacadeUsuarios();
        System.out.println(facade.ingresarRegistro(user));
        
//        System.out.println(Connection.getInstance());**/
        //System.out.println(Connection.getInstance());
        
//        LinkedList<ProductosAsociadosUsuariosDTO> p  =new LinkedList<>();
//        FacadeProductosAsociadosUsuarios a = new FacadeProductosAsociadosUsuarios();
//        p = (LinkedList<ProductosAsociadosUsuariosDTO>) a.asociarProductos(13);
//        for (ProductosAsociadosUsuariosDTO r :p) {
//            System.out.println(r.getProducto().getNombre()+" ");
//        }
//        Consultas c = new Consultas();
//        //System.out.println(c.consultarFechaActual(utilities.Connection.getInstance()));
//        
//        UsuariosDAO u = new UsuariosDAO();
        //System.out.println(u.buscarCorreoPorId(13, utilities.Connection.getInstance()));
        
        FacadeUsuarios f = new FacadeUsuarios();
        UsuariosDTO u = new UsuariosDTO();
        
        u.setNombres("Juan");
        u.setApellidos("Perez");
        u.setCedula("12344");
        u.setTelefono("17555");
        u.setDireccion("Cra x con y");
        u.setCorreo("juan@hotmail.com");
        u.setClave("123");
        u.setNotificacion(1);
        u.setCiudad("Bogota");
        u.setFechaNacimiento("1987/11/05");
//        EstadoUsuarioDTO e = new EstadoUsuarioDTO();
//        e.setIdEstadoUsuario(1);
//        u.setEstado(e);        
        u.setGenero(0);
        System.out.println(f.ingresarRegistro(u));
        
        
        
        
}
}