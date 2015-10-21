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
public class CategoriaDTO {
    
    private int idCategoria = 0;
    private String nombre = " ";

    public CategoriaDTO (){
        
    }
    
    public CategoriaDTO (int idc, String nom){
        this.idCategoria = idc;
        this.nombre = nom;
    }
    /**
     * @return the idCategoria
     */
    public int getIdCategoria() {
        return idCategoria;
    }

    /**
     * @param idCategoria the idCategoria to set
     */
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
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

    @Override
    public String toString() {
        return "CategoriaDTO " + " idCategoria = "  + idCategoria + ", nombre = " + nombre;
    }
}
