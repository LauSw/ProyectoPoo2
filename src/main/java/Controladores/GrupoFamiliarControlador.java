/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;
//import Repositorios.GrupoFamiliarNoEncontradoExcepcion;
import Repositorios.GrupoFamiliarNoEncontradoExcepcion;
import Repositorios.GrupoFamiliarRepositorio;
import io.javalin.http.Context;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Lau
 */
public class GrupoFamiliarControlador {
    private final GrupoFamiliarRepositorio repositorio;

    public GrupoFamiliarControlador(GrupoFamiliarRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void listar(Context ctx) throws SQLException {
        ctx.json(repositorio.listar());
    }

    public void crear(Context ctx) throws SQLException {
        // Usando un formulario
        repositorio.crear(ctx.formParam("nombre", String.class).get(), 
                ctx.formParam("contraseña", String.class).get(), 
                ctx.formParam("estado", Boolean.class).get());        
        // Usando JSON        
        /*
        var p = ctx.bodyAsClass(Persona.class);            
        personasRepositorio.crear(p.getNombres(), p.getApellidos());
        */
       ctx.status(201);
    }

    public void borrar(Context ctx) throws SQLException, GrupoFamiliarNoEncontradoExcepcion {
        repositorio.borrar(repositorio.obtener(ctx.pathParam("identificador", Integer.class).get()));
        ctx.status(204);
    }

    public void modificar(Context ctx) throws SQLException, GrupoFamiliarNoEncontradoExcepcion {
        var grupofamiliar = repositorio.obtener(ctx.pathParam("identificador", Integer.class).get());
        // usando un formulario
        grupofamiliar.setNombre(ctx.formParam("nombre", String.class).get());
        grupofamiliar.setContraseña(ctx.formParam("contraseña", String.class).get());
        grupofamiliar.setEstado(ctx.formParam("estado", Boolean.class).get());
        repositorio.modificar(grupofamiliar);
        ctx.status(204);
    }

}
