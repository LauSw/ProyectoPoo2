/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelos;

/**
 *
 * @author Lau
 */
public class Usuario {
    private int identificador;
    private String nombre;
    private String apellido;
    private String dni;
    private String correo;
    private String password;
    private boolean estado;
    private int idrol;
    
    public Usuario() {
        
    }

    
    public Usuario (String nombres, String apellidos, String dni, String correo, String password, boolean estado, int idrol) {
        this.nombre = nombres;
        this.apellido = apellidos;
        this.dni = dni;
        this.correo = correo;
        this.password = password;
        this.estado = estado;
        this.idrol = idrol;
    }

    public Usuario(int identificador, String nombres, String apellidos, String dni, String correo, String password, boolean estado, int idrol) {
        this.identificador = identificador;
        this.nombre = nombres;
        this.apellido = apellidos;
        this.dni = dni;
        this.correo = correo;
        this.password = password;
        this.idrol = idrol;
    }
    
    @Override
    public String toString() {
        return this.getNombre() + " " + this.getApellido();
    }

    /**
     * @return the identificador
     */
    public int getIdentificador() {
        return identificador;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(int identificador) {
        this.identificador = identificador;
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
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
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
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * @return the idrol
     */
    public int getIdrol() {
        return idrol;
    }

    /**
     * @param idrol the idrol to set
     */
    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }
}
