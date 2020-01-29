/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;
import Repositorios.RazaNoEncontradaExcepcion;
import Repositorios.RazaRepositorio;
import io.javalin.http.Context;
import java.sql.SQLException;
/**
 *
 * @author Lau
 */
public class RazaControlador {
    private final RazaRepositorio repositorio;

    public RazaControlador(RazaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void listar(Context ctx) throws SQLException {
        ctx.json(repositorio.listar());
    }

    public void crear(Context ctx) throws SQLException {
        // Usando un formulario
        repositorio.crear(ctx.formParam("nombre", String.class).get(), 
                ctx.formParam("estado", Boolean.class).get(), 
                ctx.formParam("vida_aproximada", Integer.class).get(), 
                ctx.formParam("idespecie", Integer.class).get());       
        // Usando JSON        
       
        ctx.status(201);
    }

    public void borrar(Context ctx) throws SQLException, RazaNoEncontradaExcepcion {
        repositorio.borrar(repositorio.obtener(ctx.pathParam("identificador", Integer.class).get()));
        ctx.status(204);
    }

    public void modificar(Context ctx) throws SQLException, RazaNoEncontradaExcepcion {
        var raza = repositorio.obtener(ctx.pathParam("identificador", Integer.class).get());
        // usando un formulario
        raza.setNombre(ctx.formParam("nombre", String.class).get());
        raza.setIdespecie(ctx.formParam("idespecie", Integer.class).get());
        raza.setEstado(ctx.formParam("estado", Boolean.class).get());
        raza.setVida_aproximada(ctx.formParam("vida_aproximada", Integer.class).get());
        repositorio.modificar(raza);
        ctx.status(204);
    }
}
