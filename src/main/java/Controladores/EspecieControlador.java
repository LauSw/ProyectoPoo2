/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Repositorios.EspecieNoEncontradaExcepcion;
import Repositorios.EspecieRepositorio;
import io.javalin.http.Context;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Lau
 */
public class EspecieControlador {
   private final EspecieRepositorio repositorio;

    public EspecieControlador(EspecieRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void listar(Context ctx) throws SQLException {
        ctx.json(repositorio.listar());
    }

    public void crear(Context ctx) throws SQLException {
        // Usando un formulario
        repositorio.crear(ctx.formParam("nombre", String.class).get(), ctx.formParam("estado", Integer.class).get());        
        // Usando JSON        
        ctx.status(201);
    }

    public void borrar(Context ctx) throws SQLException, EspecieNoEncontradaExcepcion {
        repositorio.borrar(repositorio.obtener(ctx.pathParam("identificador", Integer.class).get()));
        ctx.status(204);
    }

    public void modificar(Context ctx) throws SQLException, EspecieNoEncontradaExcepcion {
        var especie = repositorio.obtener(ctx.pathParam("identificador", Integer.class).get());
        // usando un formulario
        especie.setNombre(ctx.formParam("nombre", String.class).get());
        especie.setEstado(ctx.formParam("estado", Integer.class).get());
        repositorio.modificar(especie);
        ctx.status(204);
    }
}
