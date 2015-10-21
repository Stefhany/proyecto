/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.sql.Date;

/**
 *
 * @author Mona
 */
public class ConsultasDTO {
    private String fecha = "";
    private Date fechaNew = null;

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return fecha;
    }

    /**
     * @return the fechaNew
     */
    public Date getFechaNew() {
        return fechaNew;
    }

    /**
     * @param fechaNew the fechaNew to set
     */
    public void setFechaNew(Date fechaNew) {
        this.fechaNew = fechaNew;
    }
       
}
