/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;
import Repositorios.VeterinarioNoEncontradoExcepcion;
import Repositorios.VeterinarioRepositorio;
import io.javalin.http.Context;
import java.sql.SQLException;
/**
 *
 * @author Lau
 */
public class VeterinarioControlador {
    private final VeterinarioRepositorio repositorio;

    public VeterinarioControlador(VeterinarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void listar(Context ctx) throws SQLException {
        ctx.json(repositorio.listar());
    }

    public void crear(Context ctx) throws SQLException {
        // Usando un formulario
        repositorio.crear(ctx.formParam("matricula", String.class).get(), 
                ctx.formParam("telefono", String.class).get(), 
                ctx.formParam("idusuario", Integer.class).get(), 
                ctx.formParam("estado", Boolean.class).get());       
        // Usando JSON        
      
        ctx.status(201);
    }

    public void borrar(Context ctx) throws SQLException, VeterinarioNoEncontradoExcepcion {
        repositorio.borrar(repositorio.obtener(ctx.pathParam("identificador", Integer.class).get()));
        ctx.status(204);
    }

    public void modificar(Context ctx) throws SQLException, VeterinarioNoEncontradoExcepcion {
        var veterinario = repositorio.obtener(ctx.pathParam("identificador", Integer.class).get());
        // usando un formulario
        veterinario.setMatricula(ctx.formParam("matricula", String.class).get());
        veterinario.setTelefono(ctx.formParam("telefono", String.class).get());
        veterinario.setIdusuario(ctx.formParam("idusuario", Integer.class).get());
        veterinario.setEstado(ctx.formParam("estado", Boolean.class).get());
        repositorio.modificar(veterinario);
        ctx.status(204);
    }
}
