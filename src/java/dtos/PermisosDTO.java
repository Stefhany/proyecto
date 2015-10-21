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
public class PermisosDTO {
    private int idPermisos = 0;
    private String nombre = "";
    private String url = "";
    private int padre = 0;

    /**
     * @return the idPermisos
     */
    public int getIdPermisos() {
        return idPermisos;
    }

    /**
     * @param idPermisos the idPermisos to set
     */
    public void setIdPermisos(int idPermisos) {
        this.idPermisos = idPermisos;
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
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the padre
     */
    public int getPadre() {
        return padre;
    }

    /**
     * @param padre the padre to set
     */
    public void setPadre(int padre) {
        this.padre = padre;
    }

    @Override
    public String toString() {
        return "PermisosDTO{" + "idPermisos=" + idPermisos
                + ", nombre=" + nombre
                + ", url=" + url
                + ", padre=" + padre + '}';
    }   
    
}
