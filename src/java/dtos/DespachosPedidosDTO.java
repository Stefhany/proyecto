/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dtos;

import java.sql.Date;

/**
 *
 * @author krito
 */
public class DespachosPedidosDTO {
    
    private int idDespachos = 0;
    private String direccionDespacho = " ";
    private String fechaDespacho = " ";
    private String observaciones = " ";
    private int usuariosId = 0;
    private int solicitudId = 0;
    private SolicitudDistribuidorDTO idSolicitud = null;
    private UsuariosDTO user = null;
    private int estado = 0;

    public DespachosPedidosDTO() {
    }

    public DespachosPedidosDTO(SolicitudDistribuidorDTO sol) {
        this.idSolicitud = sol;
    }

    
    /**
     * @return the idDespachos
     */
    public int getIdDespachos() {
        return idDespachos;
    }

    /**
     * @param idDespachos the idDespachos to set
     */
    public void setIdDespachos(int idDespachos) {
        this.idDespachos = idDespachos;
    }

    /**
     * @return the direccionDespacho
     */
    public String getDireccionDespacho() {
        return direccionDespacho;
    }

    /**
     * @param direccionDespacho the direccionDespacho to set
     */
    public void setDireccionDespacho(String direccionDespacho) {
        this.direccionDespacho = direccionDespacho;
    }

    /**
     * @return the fechaDespacho
     */
    public String getFechaDespacho() {
        return fechaDespacho;
    }

    /**
     * @param fechaDespacho the fechaDespacho to set
     */
    public void setFechaDespacho(String fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
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

    /**
     * @return the solicitudId
     */
    public int getSolicitudId() {
        return solicitudId;
    }

    /**
     * @param solicitudId the solicitudId to set
     */
    public void setSolicitudId(int solicitudId) {
        this.solicitudId = solicitudId;
    }

    /**
     * @return the idSolicitud
     */
    public SolicitudDistribuidorDTO getIdSolicitud() {
        return idSolicitud;
    }

    /**
     * @param idSolicitud the idSolicitud to set
     */
    public void setIdSolicitud(SolicitudDistribuidorDTO idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    /**
     * @return the user
     */
    public UsuariosDTO getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UsuariosDTO user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "DespachosPedidosDTO " + " idDespachos = " + idDespachos
                + ", direccionDespacho = " + direccionDespacho
                + ", fechaDespacho=" + fechaDespacho
                + ", observaciones=" + observaciones
                + ", usuariosId=" + usuariosId
                + ", solicitudId=" + solicitudId
                + ", idSolicitud=" + idSolicitud
                + ", user=" + user;
    }

    
}
