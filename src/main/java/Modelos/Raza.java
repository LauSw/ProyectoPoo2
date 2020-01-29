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
public class Raza {
    private int identificador;
    private String nombre;
    private boolean estado;
    private int vida_aproximada;
    private int idespecie;

    public Raza (String nombre, boolean estado, int vida_aproximada, int idespecie) {
        this.nombre = nombre;
        this.estado = estado;
        this.vida_aproximada = vida_aproximada;
        this.idespecie = idespecie;
    }

    public Raza(int identificador, String nombre, boolean estado, int vida_aproximada, int idespecie) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.estado = estado;
        this.vida_aproximada = vida_aproximada;
        this.idespecie = idespecie;
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
     * @return the idespecie
     */
    public int getIdespecie() {
        return idespecie;
    }

    /**
     * @param idespecie the idespecie to set
     */
    public void setIdespecie(int idespecie) {
        this.idespecie = idespecie;
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
     * @return the vida_aproximada
     */
    public int getVida_aproximada() {
        return vida_aproximada;
    }

    /**
     * @param vida_aproximada the vida_aproximada to set
     */
    public void setVida_aproximada(int vida_aproximada) {
        this.vida_aproximada = vida_aproximada;
    }
}
