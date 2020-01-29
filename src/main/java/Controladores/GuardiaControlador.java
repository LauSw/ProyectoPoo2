/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;
import Repositorios.GuardiaNoEncontradaExcepcion;
import Repositorios.GuardiaRepositorio;
import io.javalin.http.Context;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author Lau
 */
public class GuardiaControlador {
    private final GuardiaRepositorio repositorio;

    public GuardiaControlador(GuardiaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void listar(Context ctx) throws SQLException {
        ctx.json(repositorio.listar());
    }

    public void crear(Context ctx) throws SQLException {
        // Usando un formulario
        repositorio.crear(ctx.formParam("fecha_inicio", Date.class).get(), 
                ctx.formParam("fecha_fin", Date.class).get(), 
                ctx.formParam("estado", Boolean.class).get(), 
                ctx.formParam("idveterinario", Integer.class).get(), 
                ctx.formParam("idciudad", Integer.class).get());        
        // Usando JSON        
        /*
        var p = ctx.bodyAsClass(Persona.class);            
        personasRepositorio.crear(p.getNombres(), p.getApellidos());
        */
        ctx.status(201);
    }

    public void borrar(Context ctx) throws SQLException, GuardiaNoEncontradaExcepcion {
        repositorio.borrar(repositorio.obtener(ctx.pathParam("identificador", Integer.class).get()));
        ctx.status(204);
    }

    public void modificar(Context ctx) throws SQLException, GuardiaNoEncontradaExcepcion {
        var guardia = repositorio.obtener(ctx.pathParam("identificador", Integer.class).get());
        // usando un formulario
        guardia.setFecha_inicio(ctx.formParam("fecha_inicio", Date.class).get());
        guardia.setFecha_fin(ctx.formParam("fecha_fin", Date.class).get());
        guardia.setIdveterinario(ctx.formParam("idveterinario", Integer.class).get());
        guardia.setIdciudad(ctx.formParam("idciudad", Integer.class).get());
        guardia.setEstado(ctx.formParam("estado", Boolean.class).get());
        repositorio.modificar(guardia);
        ctx.status(204);
    }
}
