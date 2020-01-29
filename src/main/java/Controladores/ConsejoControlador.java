/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

//import Repositorios.ConsejoNoEncontradoExcepcion;
//import Repositorios.ConsejoRepositorio;
import Repositorios.ConsejoNoEncontradoExcepcion;
import Repositorios.ConsejoRepositorio;
import io.javalin.http.Context;
import java.sql.SQLException;

/**
 *
 * @author Lau
 */
public class ConsejoControlador {
    private final ConsejoRepositorio repositorio;

    public ConsejoControlador(ConsejoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void listar(Context ctx) throws SQLException {
        ctx.json(repositorio.listar());
    }

    public void crear(Context ctx) throws SQLException {
        // Usando un formulario
        repositorio.crear(ctx.formParam("nombre", String.class).get(), ctx.formParam("contenido", String.class).get(), ctx.formParam("desde", String.class).get(), ctx.formParam("hasta", String.class).get(), ctx.formParam("estado", Boolean.class).get(), ctx.formParam("idraza", Integer.class).get());        
       
        ctx.status(201);
    }

    public void borrar(Context ctx) throws SQLException, ConsejoNoEncontradoExcepcion {
        repositorio.borrar(repositorio.obtener(ctx.pathParam("identificador", Integer.class).get()));
        ctx.status(204);
    }

    public void modificar(Context ctx) throws SQLException, ConsejoNoEncontradoExcepcion {
        var consejo = repositorio.obtener(ctx.pathParam("identificador", Integer.class).get());
        // usando un formulario
        consejo.setTitulo(ctx.formParam("titulo", String.class).get());
        consejo.setContenido(ctx.formParam("contenido", String.class).get());
        consejo.setDesde(ctx.formParam("desde", String.class).get());
        consejo.setHasta(ctx.formParam("hasta", String.class).get());
        consejo.setIdraza(ctx.formParam("idraza", Integer.class).get());
        consejo.setEstado(ctx.formParam("estado", Boolean.class).get());
        repositorio.modificar(consejo);
        ctx.status(204);
    }
}
