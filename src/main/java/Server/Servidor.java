/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Controladores.RolControlador;
import Controladores.UsuarioControlador;
import Repositorios.RolRepositorio;
import Repositorios.UsuarioNoEncontradoExcepcion;
import Repositorios.UsuarioRepositorio;
import io.javalin.Javalin;
import java.sql.DriverManager;
import java.sql.SQLException;

import static io.javalin.apibuilder.ApiBuilder.*;
import io.javalin.core.event.EventListener;

/**
 *
 * @author Lau
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
       var conexion = DriverManager.getConnection("jdbc:sqlite:poo2.db");
        var personasRepositorio = new UsuarioRepositorio(conexion);
        var personasControlador = new UsuarioControlador(personasRepositorio);
        var rolRepositorio = new RolRepositorio(conexion);
        var rolControlador = new RolControlador(rolRepositorio);

        Javalin.create()
        .events((EventListener event) -> {
            event.serverStopped(() -> { conexion.close(); });
        })
        .routes(() -> {
            path("rol", () -> {
                get(rolControlador::listar);
                post(rolControlador::crear);
                path(":identificador", () -> {
                    delete(rolControlador::borrar);
                    put(rolControlador::modificar);
                });
            });
            path("usuarios", () -> {
                get(personasControlador::listar);
                post(personasControlador::crear);
                path(":identificador", () -> {
                    delete(personasControlador::borrar);
                    put(personasControlador::modificar);
                });
            });
        })
        .exception(UsuarioNoEncontradoExcepcion.class, (e, ctx) -> { ctx.status(404); })
        .start(7000);
    }
    
    
}
