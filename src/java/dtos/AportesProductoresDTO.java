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
public class AportesProductoresDTO {
    private int idAporteProductor = 0;
    private String fechaEntrega = " ";
    private int cantidad = 0;
    private int idAso = 0;
    private int solicitudId = 0;
    private ProductosAsociadosUsuariosDTO proAsoId = null;
    private SolicitudDistribuidorDTO solId = null;
    private int estadoId = 0;
    private String novedad = "";
    private EstadoAporteProductorDTO estadoAport = null;
    
    public AportesProductoresDTO(){}
    
    public AportesProductoresDTO(SolicitudDistribuidorDTO solicitud) {this.solId = solicitud;}

    public AportesProductoresDTO(ProductosAsociadosUsuariosDTO proAso, SolicitudDistribuidorDTO sol) {
        this.proAsoId = proAso;
        this.solId = sol;
    }
    
    public AportesProductoresDTO(ProductosAsociadosUsuariosDTO proAso, SolicitudDistribuidorDTO sol, EstadoAporteProductorDTO estado) {
        this.proAsoId = proAso;
        this.solId = sol;
        this.estadoAport = estado;
        
    }
    
    /**
     * @return the idAporteProductor
     */
    public int getIdAporteProductor() {
        return idAporteProductor;
    }

    /**
     * @param idAporteProductor the idAporteProductor to set
     */
    public void setIdAporteProductor(int idAporteProductor) {
        this.idAporteProductor = idAporteProductor;
    }

    /**
     * @return the fechaEntrega
     */
    public String getFechaEntrega() {
        return fechaEntrega;
    }

    /**
     * @param fechaEntrega the fechaEntrega to set
     */
    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the idAso
     */
    public int getIdAso() {
        return idAso;
    }

    /**
     * @param idAso the idAso to set
     */
    public void setIdAso(int idAso) {
        this.idAso = idAso;
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
     * @return the proAsoId
     */
    public ProductosAsociadosUsuariosDTO getProAsoId() {
        return proAsoId;
    }

    /**
     * @param proAsoId the proAsoId to set
     */
    public void setProAsoId(ProductosAsociadosUsuariosDTO proAsoId) {
        this.proAsoId = proAsoId;
    }

    /**
     * @return the solId
     */
    public SolicitudDistribuidorDTO getSolId() {
        return solId;
    }

    /**
     * @param solId the solId to set
     */
    public void setSolId(SolicitudDistribuidorDTO solId) {
        this.solId = solId;
    }

    /**
     * @return the estadoId
     */
    public int getEstadoId() {
        return estadoId;
    }

    /**
     * @param estadoId the estadoId to set
     */
    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    /**
     * @return the novedad
     */
    public String getNovedad() {
        return novedad;
    }

    /**
     * @param novedad the novedad to set
     */
    public void setNovedad(String novedad) {
        this.novedad = novedad;
    }

    @Override
    public String toString() {
        return "AportesProductoresDTO{" + "idAporteProductor=" + idAporteProductor + ", fechaEntrega=" + fechaEntrega + ", cantidad=" + cantidad + ", idAso=" + idAso + ", solicitudId=" + solicitudId + ", proAsoId=" + proAsoId + ", solId=" + solId + ", estadoId=" + estadoId + ", novedad=" + novedad + '}';
    }

    /**
     * @return the estadoAport
     */
    public EstadoAporteProductorDTO getEstadoAport() {
        return estadoAport;
    }

    /**
     * @param estadoAport the estadoAport to set
     */
    public void setEstadoAport(EstadoAporteProductorDTO estadoAport) {
        this.estadoAport = estadoAport;
    }
    
    
}
