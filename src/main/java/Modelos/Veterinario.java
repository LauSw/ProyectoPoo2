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
public class Veterinario {
    private int identificador;
    private String matricula;
    private String telefono;
    private int idusuario;
    private boolean estado;

    public Veterinario (String matricula, String telefono, int idusuario, boolean estado) {
        this.matricula = matricula;
        this.telefono = telefono;
        this.idusuario = idusuario;
        this.estado = estado;
        
    }

    public Veterinario(int identificador, String matricula, String telefono, int idusuario, boolean estado) {
        this.identificador = identificador;
       this.matricula = matricula;
        this.telefono = telefono;
        this.idusuario = idusuario;
        this.estado = estado;
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
     * @return the matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
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
     * @return the idusuario
     */
    public int getIdusuario() {
        return idusuario;
    }

    /**
     * @param idusuario the idusuario to set
     */
    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
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
  
}
