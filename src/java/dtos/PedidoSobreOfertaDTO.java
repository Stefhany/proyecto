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
public class PedidoSobreOfertaDTO {
    private int idPedidosOfertas = 0;
    private int cantidadSolicitada = 0;
    private String fechaSolicitud = " ";
    private int ofertaId = 0;
    private OfertasDTO offer = null;
    private UsuariosDTO user = null;
    private int idUser = 0;
    private EstadoPedidoOfertaDTO estadoOferta = null;
    
    public PedidoSobreOfertaDTO() {
    }
    
    public PedidoSobreOfertaDTO(OfertasDTO offer, EstadoPedidoOfertaDTO state, UsuariosDTO user) {
        this.offer = offer;
        this.estadoOferta = state;
        this.user = user;
    }
    
    public PedidoSobreOfertaDTO(OfertasDTO oferta, int cantidad, String fecha, int usuarioId) {
        this.offer = oferta;
        this.cantidadSolicitada = cantidad;
        this.fechaSolicitud = fecha;
        this.idUser = usuarioId;
    }
    /**
     * @return the idPedidosOfertas
     */
    public int getIdPedidosOfertas() {
        return idPedidosOfertas;
    }

    /**
     * @param idPedidosOfertas the idPedidosOfertas to set
     */
    public void setIdPedidosOfertas(int idPedidosOfertas) {
        this.idPedidosOfertas = idPedidosOfertas;
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
     * @return the ofertaId
     */
    public int getOfertaId() {
        return ofertaId;
    }

    /**
     * @param ofertaId the ofertaId to set
     */
    public void setOfertaId(int ofertaId) {
        this.ofertaId = ofertaId;
    }

    /**
     * @return the offer
     */
    public OfertasDTO getOffer() {
        return offer;
    }

    /**
     * @param offer the offer to set
     */
    public void setOffer(OfertasDTO offer) {
        this.offer = offer;
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
     * @return the idUser
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     * @param idUser the idUser to set
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    /**
     * @return the estadoOferta
     */
    public EstadoPedidoOfertaDTO getEstadoOferta() {
        return estadoOferta;
    }

    /**
     * @param estadoOferta the estadoOferta to set
     */
    public void setEstadoOferta(EstadoPedidoOfertaDTO estadoOferta) {
        this.estadoOferta = estadoOferta;
    }

    @Override
    public String toString() {
        return "PedidoSobreOfertaDTO{" + "idPedidosOfertas=" + idPedidosOfertas + ", cantidadSolicitada=" + cantidadSolicitada + ", fechaSolicitud=" + fechaSolicitud + ", ofertaId=" + ofertaId + ", offer=" + offer + ", user=" + user + ", idUser=" + idUser + ", estadoOferta=" + estadoOferta + '}';
    }
    
    
    
}
