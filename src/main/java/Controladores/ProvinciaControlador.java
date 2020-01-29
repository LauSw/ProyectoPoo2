/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;
import Repositorios.ProvinciaNoEncontradaExcepcion;
import Repositorios.ProvinciaRepositorio;
import io.javalin.http.Context;
import java.sql.SQLException;
/**
 *
 * @author Lau
 */
public class ProvinciaControlador {
    private final ProvinciaRepositorio repositorio;

    public ProvinciaControlador(ProvinciaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void listar(Context ctx) throws SQLException {
        ctx.json(repositorio.listar());
    }

    public void crear(Context ctx) throws SQLException {
        // Usando un formulario
        repositorio.crear(ctx.formParam("nombre", String.class).get(), 
                ctx.formParam("estado", Boolean.class).get(), 
                ctx.formParam("idpais", Integer.class).get());        
        // Usando JSON        
        /*
        var p = ctx.bodyAsClass(Persona.class);            
        personasRepositorio.crear(p.getNombres(), p.getApellidos());
        */
        ctx.status(201);
    }

    public void borrar(Context ctx) throws SQLException, ProvinciaNoEncontradaExcepcion {
        repositorio.borrar(repositorio.obtener(ctx.pathParam("identificador", Integer.class).get()));
        ctx.status(204);
    }

    public void modificar(Context ctx) throws SQLException, ProvinciaNoEncontradaExcepcion {
        var provincia = repositorio.obtener(ctx.pathParam("identificador", Integer.class).get());
        // usando un formulario
        provincia.setNombre(ctx.formParam("nombre", String.class).get());
        provincia.setEstado(ctx.formParam("estado", Boolean.class).get());
        provincia.setIdpais(ctx.formParam("idpais", Integer.class).get());
        repositorio.modificar(provincia);
        ctx.status(204);
    }
}
