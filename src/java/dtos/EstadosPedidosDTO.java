/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dtos;

/**
 *
 * @author krito
 */
public class EstadosPedidosDTO {
    
    private int idEstadoPedidos = 0;
    private String nombre = " ";
    private String observaciones = " ";

    /**
     * @return the idEstadoPedidos
     */
    public int getIdEstadoPedidos() {
        return idEstadoPedidos;
    }

    /**
     * @param idEstadoPedidos the idEstadoPedidos to set
     */
    public void setIdEstadoPedidos(int idEstadoPedidos) {
        this.idEstadoPedidos = idEstadoPedidos;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    @Override
    public String toString() {
        return "EstadosPedidosDTO " + " idEstadoPedidos = " + idEstadoPedidos + ", nombre = " + nombre + ", observaciones = " + observaciones;
    }
}
