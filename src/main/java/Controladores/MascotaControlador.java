/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;
import Repositorios.MascotaNoEncontradaExcepcion;
import Repositorios.MascotaRepositorio;
import io.javalin.http.Context;
import java.sql.Date;
import java.sql.SQLException;
/**
 *
 * @author Lau
 */
public class MascotaControlador {
    private final MascotaRepositorio repositorio;

    public MascotaControlador(MascotaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void listar(Context ctx) throws SQLException {
        ctx.json(repositorio.listar());
    }

    public void crear(Context ctx) throws SQLException {
        // Usando un formulario
        repositorio.crear(ctx.formParam("nombre", String.class).get(), 
                ctx.formParam("nacimiento", Date.class).get(), 
                ctx.formParam("estado", Boolean.class).get(), 
                ctx.formParam("idraza", Integer.class).get(), 
                ctx.formParam("idgrupofamiliar", Integer.class).get());      
        // Usando JSON        
        /*
        var p = ctx.bodyAsClass(Persona.class);            
        personasRepositorio.crear(p.getNombres(), p.getApellidos());
        */
        ctx.status(201);
    }

    public void borrar(Context ctx) throws SQLException, MascotaNoEncontradaExcepcion {
        repositorio.borrar(repositorio.obtener(ctx.pathParam("identificador", Integer.class).get()));
        ctx.status(204);
    }

    public void modificar(Context ctx) throws SQLException, MascotaNoEncontradaExcepcion {
        var mascota = repositorio.obtener(ctx.pathParam("identificador", Integer.class).get());
        // usando un formulario
        mascota.setNombre(ctx.formParam("nombre", String.class).get());
        mascota.setNacimiento(ctx.formParam("nacimiento", Date.class).get());
        mascota.setIdraza(ctx.formParam("idraza", Integer.class).get());
        mascota.setIdgrupofamiliar(ctx.formParam("idgrupofamiliar", Integer.class).get());
        mascota.setEstado(ctx.formParam("estado", Boolean.class).get());
        repositorio.modificar(mascota);
        ctx.status(204);
    }
}
