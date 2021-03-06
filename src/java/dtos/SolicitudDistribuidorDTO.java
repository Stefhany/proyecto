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
public class SolicitudDistribuidorDTO {
    private int idSolicitud = 0;
    private int cantidadSolicitada = 0;
    private String fechaSolicitud = "";
    private String fechaEntregaInterna = "";
    private int estadoId = 0; 
    private int productoId = 0;
    private int distribuidorId = 0;
    private ProductoDTO product;
    private UsuariosDTO user;
    private ProductosAsociadosUsuariosDTO proAso = null;
    private int cantidadSolicitudFinal = 0;
    private EstadoSolicitudDistribuidorDTO estadoSolicitud = null;
    private String observacion = "";
    private float precioSolicitud = 0f;
        
    public SolicitudDistribuidorDTO(){}
    
    
    public SolicitudDistribuidorDTO(UsuariosDTO u, ProductoDTO pro){
        this.user = u;
        this.product = pro;
    }
    
     public SolicitudDistribuidorDTO(UsuariosDTO u, ProductoDTO pro, EstadoSolicitudDistribuidorDTO estadoNew){
        this.user = u;
        this.product = pro;
        this.estadoSolicitud = estadoNew;
    }
    
    public SolicitudDistribuidorDTO(UsuariosDTO u, EstadoSolicitudDistribuidorDTO estado){
        this.user = u;
        this.estadoSolicitud = estado;
    }
    
    public SolicitudDistribuidorDTO(ProductoDTO pro){
        this.product = pro;
    }

    /**
     * @return the idSolicitud
     */
    public int getIdSolicitud() {
        return idSolicitud;
    }

    /**
     * @param idSolicitud the idSolicitud to set
     */
    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    /**
     * @return the cantidadSolicitada
     */
    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    /**
     * @param cantidadSolicitada the cantidadSolicitada to set
     */
    public void setCantidadSolicitada(int cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    /**
     * @return the fechaSolicitud
     */
    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    /**
     * @param fechaSolicitud the fechaSolicitud to set
     */
    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    /**
     * @return the fechaEntregaInterna
     */
    public String getFechaEntregaInterna() {
        return fechaEntregaInterna;
    }

    /**
     * @param fechaEntregaInterna the fechaEntregaInterna to set
     */
    public void setFechaEntregaInterna(String fechaEntregaInterna) {
        this.fechaEntregaInterna = fechaEntregaInterna;
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
     * @return the product
     */
    public ProductoDTO getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(ProductoDTO product) {
        this.product = product;
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

    /**
     * @return the productoId
     */
    public int getProductoId() {
        return productoId;
    }

    /**
     * @param productoId the productoId to set
     */
    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    /**
     * @return the distribuidorId
     */
    public int getDistribuidorId() {
        return distribuidorId;
    }

    /**
     * @param distribuidorId the distribuidorId to set
     */
    public void setDistribuidorId(int distribuidorId) {
        this.distribuidorId = distribuidorId;
    }

    /**
     * @return the proAso
     */
    public ProductosAsociadosUsuariosDTO getProAso() {
        return proAso;
    }

    /**
     * @param proAso the proAso to set
     */
    public void setProAso(ProductosAsociadosUsuariosDTO proAso) {
        this.proAso = proAso;
    }

    @Override
    public String toString() {
        return "SolicitudDistribuidorDTO{" + "idSolicitud=" + idSolicitud + ", cantidadSolicitada=" + cantidadSolicitada + ", fechaSolicitud=" + fechaSolicitud + ", fechaEntregaInterna=" + fechaEntregaInterna + ", estadoId=" + estadoId + ", productoId=" + productoId + ", distribuidorId=" + distribuidorId + ", product=" + product + ", user=" + user + ", proAso=" + proAso + ", cantidadSolicitudFinal=" + cantidadSolicitudFinal + ", estadoSolicitud=" + estadoSolicitud + ", observacion=" + observacion + '}';
    }

    /**
     * @return the cantidadSolicitudFinal
     */
    public int getCantidadSolicitudFinal() {
        return cantidadSolicitudFinal;
    }

    /**
     * @param cantidadSolicitudFinal the cantidadSolicitudFinal to set
     */
    public void setCantidadSolicitudFinal(int cantidadSolicitudFinal) {
        this.cantidadSolicitudFinal = cantidadSolicitudFinal;
    }

    /**
     * @return the estadoSolicitud
     */
    public EstadoSolicitudDistribuidorDTO getEstadoSolicitud() {
        return estadoSolicitud;
    }

    /**
     * @param estadoSolicitud the estadoSolicitud to set
     */
    public void setEstadoSolicitud(EstadoSolicitudDistribuidorDTO estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * @return the precioSolicitud
     */
    public float getPrecioSolicitud() {
        return precioSolicitud;
    }

    /**
     * @param precioSolicitud the precioSolicitud to set
     */
    public void setPrecioSolicitud(float precioSolicitud) {
        this.precioSolicitud = precioSolicitud;
    }
    
    
}
