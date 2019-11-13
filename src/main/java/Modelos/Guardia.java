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
public class Guardia {
    private int identificador;
    private Date fecha_inicio;
    private Date fecha_fin;
    private boolean estado;
    private int idveterinario;
    private int idciudad;

    
public Guardia (Date fecha_inicio, Date fecha_fin, boolean estado, int idveterinario, int idciudad) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.estado = estado;
        this.idveterinario = idveterinario;
        this.idciudad = idciudad;
    }
    public Guardia(int identificador, Date fecha_inicio, Date fecha_fin, boolean estado, int idveterinario, int idciudad) {
        this.identificador = identificador;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.estado = estado;
        this.idveterinario = idveterinario;
        this.idciudad = idciudad;
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
     * @return the fecha_inicio
     */
    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    /**
     * @param fecha_inicio the fecha_inicio to set
     */
    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    /**
     * @return the fecha_fin
     */
    public Date getFecha_fin() {
        return fecha_fin;
    }

    /**
     * @param fecha_fin the fecha_fin to set
     */
    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    /**
     * @return the idveterinario
     */
    public int getIdveterinario() {
        return idveterinario;
    }

    /**
     * @param idveterinario the idveterinario to set
     */
    public void setIdveterinario(int idveterinario) {
        this.idveterinario = idveterinario;
    }

    /**
     * @return the idciudad
     */
    public int getIdciudad() {
        return idciudad;
    }

    /**
     * @param idciudad the idciudad to set
     */
    public void setIdciudad(int idciudad) {
        this.idciudad = idciudad;
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
