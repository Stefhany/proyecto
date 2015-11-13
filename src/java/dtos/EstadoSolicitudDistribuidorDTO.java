/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author Stefhany Alfonso
 */
public class EstadoSolicitudDistribuidorDTO {
    private int idEstadosSolicitudDistribuidor = 0;
    private String nombreEstadosSolicitudDistribuidor = "";

    /**
     * @return the idEstadosSolicitudDistribuidor
     */
    public int getIdEstadosSolicitudDistribuidor() {
        return idEstadosSolicitudDistribuidor;
    }

    /**
     * @param idEstadosSolicitudDistribuidor the idEstadosSolicitudDistribuidor to set
     */
    public void setIdEstadosSolicitudDistribuidor(int idEstadosSolicitudDistribuidor) {
        this.idEstadosSolicitudDistribuidor = idEstadosSolicitudDistribuidor;
    }

    /**
     * @return the nombreEstadosSolicitudDistribuidor
     */
    public String getNombreEstadosSolicitudDistribuidor() {
        return nombreEstadosSolicitudDistribuidor;
    }

    /**
     * @param nombreEstadosSolicitudDistribuidor the nombreEstadosSolicitudDistribuidor to set
     */
    public void setNombreEstadosSolicitudDistribuidor(String nombreEstadosSolicitudDistribuidor) {
        this.nombreEstadosSolicitudDistribuidor = nombreEstadosSolicitudDistribuidor;
    }

    @Override
    public String toString() {
        return "estadossolicitudesdistribuidores{" + "idEstadosSolicitudDistribuidor=" + idEstadosSolicitudDistribuidor + ", nombreEstadosSolicitudDistribuidor=" + nombreEstadosSolicitudDistribuidor + '}';
    }
    
    
    
}
