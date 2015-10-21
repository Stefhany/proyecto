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
    
    public SolicitudDistribuidorDTO(){}
    
    
    public SolicitudDistribuidorDTO(UsuariosDTO u, ProductoDTO pro){
        this.user = u;
        this.product = pro;
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
        return "SolicitudDistribuidorDTO " 
                + ", idSolicitud=" + idSolicitud 
                + ", cantidadSolicitada=" + cantidadSolicitada 
                + ", fechaSolicitud=" + fechaSolicitud
                + ", fechaEntregaInterna=" + fechaEntregaInterna
                + ", estadoId=" + estadoId 
                + ", productoId=" + productoId
                + ", distribuidorId=" + distribuidorId 
                + ", product = " + product.getIdProductos()
                + ", user = " + user.getIdUsuarios();
//                + ", proAso = " + getProAso().getIdProductosAsociadosUsuarios();
    }
    
    
}
