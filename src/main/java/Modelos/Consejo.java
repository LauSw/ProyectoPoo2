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
public class Consejo {
    private int identificador;
    private String titulo;
    private String contenido;
    private String desde;
    private String hasta;
    private boolean estado;
    private int idraza;

    public Consejo (String titulo, String contenido, String desde, String hasta, boolean estado, int idraza) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.desde = desde;
        this.hasta = hasta;
        this.estado = estado;
        this.idraza = idraza;
    }

    public Consejo(int identificador, String titulo, String contenido, String desde, String hasta, boolean estado, int idraza) {
        this.identificador = identificador;
        this.contenido = contenido;
        this.desde = desde;
        this.hasta = hasta;
        this.estado = estado;
        this.idraza = idraza;
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
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the contenido
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }


    /**
     * @return the idraza
     */
    public int getIdraza() {
        return idraza;
    }

    /**
     * @param idraza the idraza to set
     */
    public void setIdraza(int idraza) {
        this.idraza = idraza;
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
     * @return the desde
     */
    public String getDesde() {
        return desde;
    }

    /**
     * @param desde the desde to set
     */
    public void setDesde(String desde) {
        this.desde = desde;
    }

    /**
     * @return the hasta
     */
    public String getHasta() {
        return hasta;
    }

    /**
     * @param hasta the hasta to set
     */
    public void setHasta(String hasta) {
        this.hasta = hasta;
    }
}
