/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Repositorios.RolNoEncontradoExcepcion;
import Repositorios.RolRepositorio;
import io.javalin.http.Context;
import java.sql.SQLException;

/**
 *
 * @author Lau
 */
public class RolControlador {
        private final RolRepositorio personasRepositorio;

    public RolControlador(RolRepositorio personasRepositorio) {
        this.personasRepositorio = personasRepositorio;
    }

    public void listar(Context ctx) throws SQLException {
        ctx.json(personasRepositorio.listar());
    }

    public void crear(Context ctx) throws SQLException {
        // Usando un formulario
        personasRepositorio.crear(ctx.formParam("nombre", String.class).get(), ctx.formParam("estado", Boolean.class).get());        
        // Usando JSON        
        /*
        var p = ctx.bodyAsClass(Persona.class);            
        personasRepositorio.crear(p.getNombres(), p.getApellidos());
        */
        ctx.status(201);
    }

    public void borrar(Context ctx) throws SQLException, RolNoEncontradoExcepcion {
        personasRepositorio.borrar(personasRepositorio.obtener(ctx.pathParam("identificador", Integer.class).get()));
        ctx.status(204);
    }

    public void modificar(Context ctx) throws SQLException, RolNoEncontradoExcepcion {
        var persona = personasRepositorio.obtener(ctx.pathParam("identificador", Integer.class).get());
        // usando un formulario
        persona.setNombre(ctx.formParam("nombre", String.class).get());
        persona.setEstado(ctx.formParam("estado", Boolean.class).get());
        personasRepositorio.modificar(persona);
        ctx.status(204);
    }
}
