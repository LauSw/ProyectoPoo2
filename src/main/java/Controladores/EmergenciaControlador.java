/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

//import Repositorios.EmergenciaNoEncontradaExcepcion;
//import Repositorios.EmergenciaRepositorio;
import Repositorios.EmergenciaNoEncontradaExcepcion;
import Repositorios.EmergenciaRepositorio;
import io.javalin.http.Context;
import java.sql.SQLException;
import java.sql.Date;

/**
 *
 * @author Lau
 */
public class EmergenciaControlador {
    private final EmergenciaRepositorio repositorio;

    public EmergenciaControlador(EmergenciaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void listar(Context ctx) throws SQLException {
        ctx.json(repositorio.listar());
    }

    public void crear(Context ctx) throws SQLException {
        // Usando un formulario
        repositorio.crear(ctx.formParam("titulo", String.class).get(), 
                ctx.formParam("descripcion", String.class).get(), 
                ctx.formParam("fecha", Date.class).get(), 
                ctx.formParam("estado", Integer.class).get(), 
                ctx.formParam("idusuario", Integer.class).get(), 
                ctx.formParam("idguardia", Integer.class).get());        
       
        ctx.status(201);
    }

    public void borrar(Context ctx) throws SQLException, EmergenciaNoEncontradaExcepcion {
        repositorio.borrar(repositorio.obtener(ctx.pathParam("identificador", Integer.class).get()));
        ctx.status(204);
    }

    public void modificar(Context ctx) throws SQLException, EmergenciaNoEncontradaExcepcion {
        /*var emergencia = repositorio.obtener(ctx.pathParam("identificador", Integer.class).get());
        // usando un formulario
        emergencia.setTitulo(ctx.formParam("titulo", String.class).get());
        emergencia.setDescripcion(ctx.formParam("descripcion", String.class).get());
        emergencia.setIdusuario(ctx.formParam("idusuario", Integer.class).get());
        emergencia.setIdguardia(ctx.formParam("idguardia", Integer.class).get());
        emergencia.setFecha(ctx.formParam("fecha", Date.class).get());
        emergencia.setEstado(ctx.formParam("estado", Integer.class).get());
        repositorio.modificar(emergencia);
        ctx.status(204);*/
    }
}
