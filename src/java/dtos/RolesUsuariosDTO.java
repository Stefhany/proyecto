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
public class RolesUsuariosDTO {
    private int rolesId = 0;
    private int usuariosId = 0;
    private RolesDTO rolId = null;
    private UsuariosDTO usuarioId = null;

    public RolesUsuariosDTO() {
    }

    public RolesUsuariosDTO(RolesDTO rol, UsuariosDTO u) {
        this.rolId = rol;
        this.usuarioId = u;
    }
    
    

    /**
     * @return the rolesId
     */
    public int getRolesId() {
        return rolesId;
    }

    /**
     * @param rolesId the rolesId to set
     */
    public void setRolesId(int rolesId) {
        this.rolesId = rolesId;
    }

    /**
     * @return the usuariosId
     */
    public int getUsuariosId() {
        return usuariosId;
    }

    /**
     * @param usuariosId the usuariosId to set
     */
    public void setUsuariosId(int usuariosId) {
        this.usuariosId = usuariosId;
    }

    @Override
    public String toString() {
        return "RolesUsuariosDTO{" + "rolesId=" + rolesId + ", usuariosId=" + usuariosId + ", rolId=" + rolId + ", usuarioId=" + usuarioId + '}';
    }

    

    /**
     * @return the rolId
     */
    public RolesDTO getRolId() {
        return rolId;
    }

    /**
     * @param rolId the rolId to set
     */
    public void setRolId(RolesDTO rolId) {
        this.rolId = rolId;
    }

    /**
     * @return the usuarioId
     */
    public UsuariosDTO getUsuarioId() {
        return usuarioId;
    }

    /**
     * @param usuarioId the usuarioId to set
     */
    public void setUsuarioId(UsuariosDTO usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    
}
