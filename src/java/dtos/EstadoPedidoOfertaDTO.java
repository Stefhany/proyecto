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
public class EstadoPedidoOfertaDTO {
    private int idEstadoPedidoSobreOferta = 0;
    private String nombreEstadoPedidoSobreOferta = "";

    /**
     * @return the idEstadoPedidoSobreOferta
     */
    public int getIdEstadoPedidoSobreOferta() {
        return idEstadoPedidoSobreOferta;
    }

    /**
     * @param idEstadoPedidoSobreOferta the idEstadoPedidoSobreOferta to set
     */
    public void setIdEstadoPedidoSobreOferta(int idEstadoPedidoSobreOferta) {
        this.idEstadoPedidoSobreOferta = idEstadoPedidoSobreOferta;
    }

    /**
     * @return the nombreEstadoPedidoSobreOferta
     */
    public String getNombreEstadoPedidoSobreOferta() {
        return nombreEstadoPedidoSobreOferta;
    }

    /**
     * @param nombreEstadoPedidoSobreOferta the nombreEstadoPedidoSobreOferta to set
     */
    public void setNombreEstadoPedidoSobreOferta(String nombreEstadoPedidoSobreOferta) {
        this.nombreEstadoPedidoSobreOferta = nombreEstadoPedidoSobreOferta;
    }

    @Override
    public String toString() {
        return "EstadoPedidoOferta" + "idEstadoPedidoSobreOferta=" + idEstadoPedidoSobreOferta + ", nombreEstadoPedidoSobreOferta=" + nombreEstadoPedidoSobreOferta;
    }
    
    
}
