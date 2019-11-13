/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.sql.Date;

/**
 *
 * @author Lau
 */
public class Emergencia {
    private int identificador;
    private String titulo;
    private String descripcion;
    private Date fecha;
    private int estado;
    private int idusuario;
    private int idguardia;

    
    public Emergencia (String titulo, String descripcion, Date fecha, int estado, int idusuario, int idguardia) {
        this.titulo = titulo;
        this.descripcion = descripcion;
         this.fecha = fecha;
        this.estado = estado;
        this.idusuario = idusuario;
        this.idguardia = idguardia;
    }

    public Emergencia(int identificador, String titulo, String descripcion, Date fecha, int estado, int idusuario, int idguardia) {
        this.identificador = identificador;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
        this.idusuario = idusuario;
        this.idguardia = idguardia;
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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    /**
     * @return the idguardia
     */
    public int getIdguardia() {
        return idguardia;
    }

    /**
     * @param idguardia the idguardia to set
     */
    public void setIdguardia(int idguardia) {
        this.idguardia = idguardia;
    }
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
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
