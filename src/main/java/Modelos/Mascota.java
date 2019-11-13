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
public class Mascota {
    private int identificador;
    private String nombre;
    private Date nacimiento;
    private boolean estado;
    private int idraza;
    private int idgrupofamiliar;

    public Mascota (String nombre, Date nacimiento, boolean estado, int idraza, int idgrupofamiliar) {
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.estado = estado;
        this.idraza = idraza;
        this.idgrupofamiliar = idgrupofamiliar;
    }

    public Mascota(int identificador, String nombre, Date nacimiento, boolean estado, int idraza, int idgrupofamiliar) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.estado = estado;
        this.idraza = idraza;
        this.idgrupofamiliar = idgrupofamiliar;
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
     * @return the nacimiento
     */
    public Date getNacimiento() {
        return nacimiento;
    }

    /**
     * @param nacimiento the nacimiento to set
     */
    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
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

    /**
     * @return the idgrupofamiliar
     */
    public int getIdgrupofamiliar() {
        return idgrupofamiliar;
    }

    /**
     * @param idgrupofamiliar the idgrupofamiliar to set
     */
    public void setIdgrupofamiliar(int idgrupofamiliar) {
        this.idgrupofamiliar = idgrupofamiliar;
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
