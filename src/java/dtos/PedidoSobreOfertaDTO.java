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

    @Override
    public String toString() {
        return "PedidoSobreOfertaDTO " + " idPedidosOfertas=" + idPedidosOfertas + ", cantidadSolicitada=" + cantidadSolicitada + ", fechaSolicitud=" + fechaSolicitud + ", ofertaId=" + ofertaId + ", offer=" + offer.getIdOfertas();
    }
    
    
}
