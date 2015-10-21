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
public class ProductoDTO {

    private int idProductos = 0;
    private String nombre = ""; 
    private String unidad = "";
    private CategoriaDTO categoriaId = null;  
    private int categoriaId2 = 0;
    private UsuariosDTO user = null;

    public ProductoDTO() {
    }
    
    /*
    *Este constructor se hace para listar byIdProduct en el método de 
    *modificar un producto
    */
    public ProductoDTO(CategoriaDTO cto) {
        this.categoriaId = cto;
    }
    
    public ProductoDTO(int idp, String nomPro, String unidad, CategoriaDTO cdto) {
        this.idProductos = idp;
        this.nombre = nomPro;
        this.unidad = unidad;
        this.categoriaId = cdto;
    }
    
    public ProductoDTO(UsuariosDTO user) {
        this.user = user;
    }
    
    /*
    * Este constructor es el encargado de 
    * de permitirme realizar la consulta de todos para 
    * las solicitudes de los distribuidores
    */
    public ProductoDTO(int id, String nom) {
        this.idProductos = id;
        this.nombre = nom;
    }

    /**
     * @return the idProductos
     */
    public int getIdProductos() {
        return idProductos;
    }

    /**
     * @param idProductos the idProductos to set
     */
    public void setIdProductos(int idProductos) {
        this.idProductos = idProductos;
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
     * @return the unidad
     */
    public String getUnidad() {
        return unidad;
    }

    /**
     * @param unidad the unidad to set
     */
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    /**
     * @return the categoriaId
     */
    public CategoriaDTO getCategoriaId() {
        return categoriaId;
    }

    /**
     * @param categoriaId the categoriaId to set
     */
    public void setCategoriaId(CategoriaDTO categoriaId) {
        this.categoriaId = categoriaId;
    }

    @Override
    public String toString() {
        return "ProductoDTO " + " idProductos = " + idProductos + ", nombre = " + nombre + ", unidad = " + unidad + ", categoriaId=  " + categoriaId;
    }

    /**
     * @return the categoriaId2
     */
    public int getCategoriaId2() {
        return categoriaId2;
    }

    /**
     * @param categoriaId2 the categoriaId2 to set
     */
    public void setCategoriaId2(int categoriaId2) {
        this.categoriaId2 = categoriaId2;
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

}
