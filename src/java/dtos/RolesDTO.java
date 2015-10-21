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
public class RolesDTO {

    private int idRol = 0;
    private String nombre = "";
    private String estado = "";
    private UsuariosDTO userDto;

    public RolesDTO(UsuariosDTO user) {
        this.userDto = user;
    }

    public RolesDTO() {
    }

        
    
    /**
     * @return the idRol
     */
    public int getIdRol() {
        return idRol;
    }

    /**
     * @param idRol the idRol to set
     */
    public void setIdRol(int idRol) {
        this.idRol = idRol;
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
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the userDto
     */
    public UsuariosDTO getUserDto() {
        return userDto;
    }

    /**
     * @param userDto the userDto to set
     */
    public void setUserDto(UsuariosDTO userDto) {
        this.userDto = userDto;
    }

    @Override
    public String toString() {
        return "RolesDTO{" + "idRol=" + idRol + ", nombre=" + nombre + ", estado=" + estado + ", userDto=" + userDto + '}';
    }
    
    
}
