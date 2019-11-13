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
public class Ciudad {
    private int identificador;
    private String nombre;
    private boolean estado;
    private int idprovincia;

    public Ciudad (String nombre, boolean estado, int idprovincia) {
        this.nombre = nombre;
        this.estado = estado;
        this.idprovincia = idprovincia;
    }

    public Ciudad(int identificador, String nombre, boolean estado, int idprovincia) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.estado = estado;
        this.idprovincia = idprovincia;
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
     * @return the idprovincia
     */
    public int getIdprovincia() {
        return idprovincia;
    }

    /**
     * @param idprovincia the idprovincia to set
     */
    public void setIdprovincia(int idprovincia) {
        this.idprovincia = idprovincia;
    }
}
