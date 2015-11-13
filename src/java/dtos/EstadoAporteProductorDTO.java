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
public class EstadoAporteProductorDTO {
    private int idEstadoAporteProductor = 0;
    private String nombreEstadoAporteProductor = "";

    /**
     * @return the idEstadoAporteProductor
     */
    public int getIdEstadoAporteProductor() {
        return idEstadoAporteProductor;
    }

    /**
     * @param idEstadoAporteProductor the idEstadoAporteProductor to set
     */
    public void setIdEstadoAporteProductor(int idEstadoAporteProductor) {
        this.idEstadoAporteProductor = idEstadoAporteProductor;
    }

    /**
     * @return the nombreEstadoAporteProductor
     */
    public String getNombreEstadoAporteProductor() {
        return nombreEstadoAporteProductor;
    }

    /**
     * @param nombreEstadoAporteProductor the nombreEstadoAporteProductor to set
     */
    public void setNombreEstadoAporteProductor(String nombreEstadoAporteProductor) {
        this.nombreEstadoAporteProductor = nombreEstadoAporteProductor;
    }

    @Override
    public String toString() {
        return "EstadoAporteProductorDTO{" + "idEstadoAporteProductor=" + idEstadoAporteProductor + ", nombreEstadoAporteProductor=" + nombreEstadoAporteProductor + '}';
    }
    
    
}
