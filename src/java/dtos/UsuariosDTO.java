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
public class UsuariosDTO {

    private int idUsuarios = 0;
    private String nombres = "";
    private String apellidos = "";
    private String cedula = "";
    private String telefono = "";
    private String direccion  = "";
    private String correo = "";
    private String clave = "";
    private int notificacion = 0;
    private String ciudad = "";
    private String fechaNacimiento = "";
    private EstadoUsuarioDTO estadoInt = null;
    private int estado = 0;
    private int genero = 0;
    
    public UsuariosDTO(){}
//    public UsuariosDTO(EstadoUsuarioDTO estadoUser){
//        this.estadoInt = estadoUser;
//    }
    
    /*
    * Este constructor me facilita listar los pedidos que se van a despachar.
    */
    public UsuariosDTO(int idUsuarios, String nombres, String telefono, String direccion) {
        this.idUsuarios = idUsuarios;
        this.nombres = nombres;
        this.telefono = telefono;
        this.direccion = direccion;
    }
    
    public UsuariosDTO(String nom, String ape){
        this.nombres = nom;
        this.apellidos = ape;
    }
    /*
    * Se utiliza este constructor para la elabaroacion de la
    * lista de todas las ofertas
    */
    public UsuariosDTO(String nom){
        this.nombres = nom;
    }
    
    public UsuariosDTO(int id, String nom){
        this.idUsuarios = id;
        this.nombres = nom;
    }
    
    public UsuariosDTO(int id, String nom, String dir){
        this.idUsuarios = id;
        this.nombres = nom;
        this.direccion = dir;
    }
     
    /**
     * @return the idUsuarios
     */
    public int getIdUsuarios() {
        return idUsuarios;
    }

    /**
     * @param idUsuarios the idUsuarios to set
     */
    public void setIdUsuarios(int idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * @return the notificacion
     */
    public int getNotificacion() {
        return notificacion;
    }

    /**
     * @param notificacion the notificacion to set
     */
    public void setNotificacion(int notificacion) {
        this.notificacion = notificacion;
    }

    /**
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the fechaNacimiento
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    /**
     * @return the genero
     */
    public int getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(int genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "UsuariosDTO{" + "idUsuarios=" + idUsuarios
                + ", nombres=" + nombres
                + ", apellidos=" + apellidos
                + ", cedula=" + cedula
                + ", telefono=" + telefono
                + ", direccion=" + direccion
                + ", correo=" + correo
                + ", clave=" + clave
                + ", notificacion=" + notificacion
                + ", ciudad=" + ciudad
                + ", fechaNacimiento=" + fechaNacimiento
                + ", estado=" + getEstadoInt()
                +", genero="+ genero;
    }   

    /**
     * @return the estadoInt
     */
    public EstadoUsuarioDTO getEstadoInt() {
        return estadoInt;
    }

    /**
     * @param estadoInt the estadoInt to set
     */
    public void setEstadoInt(EstadoUsuarioDTO estadoInt) {
        this.estadoInt = estadoInt;
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

}
