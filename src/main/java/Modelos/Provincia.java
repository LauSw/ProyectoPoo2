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
public class Provincia {
    private int identificador;
    private String nombre;
    private boolean estado;
    private int idpais;

    public Provincia (String nombre, boolean estado, int idpais) {
        this.nombre = nombre;
        this.estado = estado;
        this.idpais = idpais;
    }

    public Provincia(int identificador, String nombre, boolean estado, int idpais) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.estado = estado;
        this.idpais = idpais;
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
     * @return the estado
     */
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
     * @return the idpais
     */
    public int getIdpais() {
        return idpais;
    }

    /**
     * @param idpais the idpais to set
     */
    public void setIdpais(int idpais) {
        this.idpais = idpais;
    }


}
