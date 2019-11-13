/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Repositorios.UsuarioNoEncontradoExcepcion;
import Repositorios.UsuarioRepositorio;
import io.javalin.http.Context;
import java.sql.SQLException;

/**
 *
 * @author Lau
 */
public class UsuarioControlador {
    private final UsuarioRepositorio personasRepositorio;

    public UsuarioControlador(UsuarioRepositorio personasRepositorio) {
        this.personasRepositorio = personasRepositorio;
    }

    public void listar(Context ctx) throws SQLException {
        ctx.json(personasRepositorio.listar());
    }

    public void crear(Context ctx) throws SQLException {
        // Usando un formulario
        personasRepositorio.crear(ctx.formParam("nombre", String.class).get(), ctx.formParam("apellido", String.class).get(), ctx.formParam("dni", String.class).get(), ctx.formParam("correo", String.class).get(), ctx.formParam("contraseña", String.class).get(), ctx.formParam("idrol", Integer.class).get());        
        // Usando JSON        
        /*
        var p = ctx.bodyAsClass(Persona.class);            
        personasRepositorio.crear(p.getNombres(), p.getApellidos());
        */
        ctx.status(201);
    }

    public void borrar(Context ctx) throws SQLException, UsuarioNoEncontradoExcepcion {
        personasRepositorio.borrar(personasRepositorio.obtener(ctx.pathParam("identificador", Integer.class).get()));
        ctx.status(204);
    }

    public void modificar(Context ctx) throws SQLException, UsuarioNoEncontradoExcepcion {
        var persona = personasRepositorio.obtener(ctx.pathParam("identificador", Integer.class).get());
        // usando un formulario
        persona.setNombre(ctx.formParam("nombre", String.class).get());
        persona.setApellido(ctx.formParam("apellido", String.class).get());
        persona.setDni(ctx.formParam("dni", String.class).get());
        persona.setCorreo(ctx.formParam("correo", String.class).get());
        persona.setPassword(ctx.formParam("contraseña", String.class).get());
        persona.setIdrol(ctx.formParam("idrol", Integer.class).get());
        personasRepositorio.modificar(persona);
        ctx.status(204);
    }
}
