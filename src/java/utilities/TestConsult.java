/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import dtos.AportesProductoresDTO;
import dtos.PedidoSobreOfertaDTO;
import dtos.ProductoDTO;
import dtos.ProductosAsociadosUsuariosDTO;
import dtos.SolicitudDistribuidorDTO;
import facade.FacadeAportesProductores;
import facade.FacadeConsultas;
import facade.FacadePedidoSobreOferta;
import facade.FacadeProductosAsociadosUsuarios;
import facade.FacadeSolicitudDistribuidor;
import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Stefhany Alfonso
 */
public class TestConsult {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MyException, SQLException {
        // TODO code application logic here
        
//        System.out.println(Connection.getInstance());**/
        //System.out.println(Connection.getInstance());
//        Consultas c = new Consultas();
//        //System.out.println(c.consultarFechaActual(utilities.Connection.getInstance()));
//        
//        UsuariosDAO u = new UsuariosDAO();
        //System.out.println(u.buscarCorreoPorId(13, utilities.Connection.getInstance()));
        
//        FacadeUsuarios f = new FacadeUsuarios();
//        UsuariosDTO u = new UsuariosDTO();
//        
//        u.setNombres("Juan");
//        u.setApellidos("Perez");
//        u.setCedula("9999999");
//        u.setTelefono("17555");
//        u.setDireccion("Cra x con y");
//        u.setCorreo("juan@hotmail.com");
//        u.setClave("123");
//        u.setNotificacion(1);
//        u.setCiudad("Bogota");
//        u.setFechaNacimiento("1987/11/05");
////        EstadoUsuarioDTO e = new EstadoUsuarioDTO();
////        e.setIdEstadoUsuario(1);
////        u.setEstado(e);        
//        u.setGenero(0);
//        System.out.println(f.ingresarRegistro(u));
//        
////        AportesProductoresDTO aport = new AportesProductoresDTO();
////        aport.setFechaEntrega("2015-11-23");
////        aport.setCantidad(650);
////        aport.setIdAso(10);
////        aport.setSolicitudId(1);
//        FacadeAportesProductores f = new FacadeAportesProductores();
//        System.out.println(f.participarASolicitudAsociacion("2015-11-14",200,10,2));
        
//        List<ProductoDTO> p  =new LinkedList<>();
//        FacadeProductosAsociadosUsuarios a = new FacadeProductosAsociadosUsuarios();
//        p = (List<ProductoDTO>) a.asociarProductos(13);
//        for (ProductoDTO r :p) {
//            System.out.println(r.getNombre()+" ");
//        }
//        System.out.println("=============");
//    
//        FacadeConsultas fconsult = new FacadeConsultas();
//        Date fechamenor = Date.valueOf("2015-11-10");
//        Date fechamayor = Date.valueOf("2015-11-13");
//        System.out.println(fconsult.diferenciasDeFechas(fechamenor, fechamayor));
        FacadeSolicitudDistribuidor f = new FacadeSolicitudDistribuidor();
        AportesProductoresDTO a = new AportesProductoresDTO();
        
        System.out.println(f.modificarCantidadSolicitud(10000, 1));
        
        
        
        
}
}