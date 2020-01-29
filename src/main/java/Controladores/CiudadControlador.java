/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Repositorios.CiudadNoEncontradaExcepcion;
import Repositorios.CiudadRepositorio;
import io.javalin.http.Context;
import java.sql.SQLException;

/**
 *
 * @author Lau
 */
public class CiudadControlador {
    private final CiudadRepositorio repositorio;

    public CiudadControlador(CiudadRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void listar(Context ctx) throws SQLException {
        ctx.json(repositorio.listar());
    }

    public void crear(Context ctx) throws SQLException {
        // Usando un formulario
        repositorio.crear(ctx.formParam("nombre", String.class).get(), ctx.formParam("estado", Boolean.class).get(), ctx.formParam("idprovincia", Integer.class).get());        
        // Usando JSON        
        /*
        var p = ctx.bodyAsClass(Persona.class);            
        personasRepositorio.crear(p.getNombres(), p.getApellidos());
        */
        ctx.status(201);
    }

    public void borrar(Context ctx) throws SQLException, CiudadNoEncontradaExcepcion {
        repositorio.borrar(repositorio.obtener(ctx.pathParam("identificador", Integer.class).get()));
        ctx.status(204);
    }

    public void modificar(Context ctx) throws SQLException, CiudadNoEncontradaExcepcion {
        var ciudad = repositorio.obtener(ctx.pathParam("identificador", Integer.class).get());
        // usando un formulario
        ciudad.setNombre(ctx.formParam("nombre", String.class).get());
        ciudad.setEstado(ctx.formParam("estado", Boolean.class).get());
        ciudad.setIdprovincia(ctx.formParam("idprovincia", Integer.class).get());
        repositorio.modificar(ciudad);
        ctx.status(204);
    }
}
