/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author Mona
 */
public class EstadoUsuarioDTO {
    private int idEstadoUsuario = 0;
    private String nombreEstadoUsuario = "";

    /**
     * @return the idEstadoUsuario
     */
    public int getIdEstadoUsuario() {
        return idEstadoUsuario;
    }

    /**
     * @param idEstadoUsuario the idEstadoUsuario to set
     */
    public void setIdEstadoUsuario(int idEstadoUsuario) {
        this.idEstadoUsuario = idEstadoUsuario;
    }

    /**
     * @return the nombreEstadoUsuario
     */
    public String getNombreEstadoUsuario() {
        return nombreEstadoUsuario;
    }

    /**
     * @param nombreEstadoUsuario the nombreEstadoUsuario to set
     */
    public void setNombreEstadoUsuario(String nombreEstadoUsuario) {
        this.nombreEstadoUsuario = nombreEstadoUsuario;
    }

    @Override
    public String toString() {
        return "EstadoUsuarioDTO" 
                + "idEstadoUsuario=" + idEstadoUsuario
                + ", nombreEstadoUsuario=" + nombreEstadoUsuario;
    }
    
    
    
}
