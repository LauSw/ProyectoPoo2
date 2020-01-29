/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Controladores.*;
import Controladores.UsuarioControlador;
import Repositorios.*;
import Repositorios.RolNoEncontradoExcepcion;
import Repositorios.RolRepositorio;
import Repositorios.UsuarioNoEncontradoExcepcion;
import Repositorios.UsuarioRepositorio;
import io.javalin.Javalin;
import java.sql.DriverManager;
import java.sql.SQLException;

import static io.javalin.apibuilder.ApiBuilder.*;
import io.javalin.core.event.EventListener;
import java.sql.*;

/**
 *
 * @author Lau
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /*var conexion = DriverManager.getConnection("jdbc:sqlite:poo2.db");
        var personasRepositorio = new UsuarioRepositorio(conexion);
        var personasControlador = new UsuarioControlador(personasRepositorio);
        var rolRepositorio = new RolRepositorio(conexion);
        var rolControlador = new RolControlador(rolRepositorio);*/
        String driver = "org.postgresql.Driver";
        String connectString = "jdbc:postgresql://localhost:5432/PochocloBD";
        String user = "postgres";
        String password = "postgres";
        try {
            Class.forName(driver);
            //Hacemos la coneccion.

            Connection conexion = DriverManager.getConnection(connectString, user, password);
            var usuariosRepositorio = new UsuarioRepositorio(conexion);
            var usuariosControlador = new UsuarioControlador(usuariosRepositorio);

            var rolRepositorio = new RolRepositorio(conexion);
            var rolControlador = new RolControlador(rolRepositorio);
            
            var paisRepositorio = new PaisRepositorio(conexion);
            var paisControlador = new PaisControlador(paisRepositorio);
            
            var provinciaRepositorio = new ProvinciaRepositorio(conexion);
            var provinciaControlador = new ProvinciaControlador(provinciaRepositorio);
            
            var ciudadRepositorio = new CiudadRepositorio(conexion);
            var ciudadControlador = new CiudadControlador(ciudadRepositorio);
            
            var especieRepositorio = new EspecieRepositorio(conexion);
            var especieControlador = new EspecieControlador(especieRepositorio);
          
            var razaRepositorio = new RazaRepositorio(conexion);
            var razaControlador = new RazaControlador(razaRepositorio);
            
            var consejoRepositorio = new ConsejoRepositorio(conexion);
            var consejoControlador = new ConsejoControlador(consejoRepositorio);
            
            var mascotaRepositorio = new MascotaRepositorio(conexion);
            var mascotaControlador = new MascotaControlador(mascotaRepositorio);
            
            var grupoRepositorio = new GrupoFamiliarRepositorio(conexion);
            var grupoControlador = new GrupoFamiliarControlador(grupoRepositorio);
            
            var veterinarioRepositorio = new VeterinarioRepositorio(conexion);
            var veterinarioControlador = new VeterinarioControlador(veterinarioRepositorio);
            
            var guardiaRepositorio = new GuardiaRepositorio(conexion);
            var guardiaControlador = new GuardiaControlador(guardiaRepositorio);
            
            var emergenciaRepositorio = new EmergenciaRepositorio(conexion);
            var emergenciaControlador = new EmergenciaControlador(emergenciaRepositorio);
            
            Javalin.create(config -> {
                //config.defaultContentType = "application/json";
                config.addStaticFiles("/publico");
                //config.enableCorsForAllOrigins();
                //config.addSinglePageRoot("/", "/public/index.html");
            })
                    .events((EventListener event) -> {
                        event.serverStopped(() -> {
                            conexion.close();
                        });
                    })
                    .routes(() -> {
                        path("usuarios", () -> {
                            get(usuariosControlador::listar);
                            post(usuariosControlador::crear);
                            path(":idUsuario", () -> {
                                delete(usuariosControlador::borrar);
                                put(usuariosControlador::modificar);
                            });
                        });
                    })
                    .exception(UsuarioNoEncontradoExcepcion.class, (e, ctx) -> {
                        ctx.status(404);
                    })
                    .routes(() -> {
                        path("rol", () -> {
                            get(rolControlador::listar);
                            post(rolControlador::crear);
                            path(":idRol", () -> {
                                delete(rolControlador::borrar);
                                put(rolControlador::modificar);
                            });
                        });
                    })
                    .exception(RolNoEncontradoExcepcion.class, (e, ctx) -> {
                        ctx.status(404);
                    })
                    .routes(() -> {
                        path("pais", () -> {
                            get(paisControlador::listar);
                            post(paisControlador::crear);
                            path(":idPais", () -> {
                                delete(paisControlador::borrar);
                                put(paisControlador::modificar);
                                //get(paisControlador::obtener);
                            });
                        });
                    })
                    .routes(() -> {
                        path("provincia", () -> {
                            get(provinciaControlador::listar);
                            post(provinciaControlador::crear);
                            path(":idProvincia", () -> {
                                //get(provinciaControlador::obtener);
                                delete(provinciaControlador::borrar);
                                put(provinciaControlador::modificar);
                            });
                        });
                    })
                    .exception(ProvinciaNoEncontradaExcepcion.class, (e, ctx) -> {
                        ctx.status(404);
                    })
                    .routes(() -> {
                        path("ciudad", () -> {
                            get(ciudadControlador::listar);
                            post(ciudadControlador::crear);
                            path(":idPelicula", () -> {
                                //get(ciudadControlador::obtener);
                                delete(ciudadControlador::borrar);
                                put(ciudadControlador::modificar);
                            });
                        });
                    })
                    .exception(CiudadNoEncontradaExcepcion.class, (e, ctx) -> {
                        ctx.status(404);
                    })
                    .routes(() -> {
                        path("veterinario", () -> {
                            get(veterinarioControlador::listar);
                            post(veterinarioControlador::crear);
                            path(":idVeterinario", () -> {
                                //get(veterinarioControlador::obtener);
                                delete(veterinarioControlador::borrar);
                                put(veterinarioControlador::modificar);
                            });
                        });
                    })
                    .exception(VeterinarioNoEncontradoExcepcion.class, (e, ctx) -> {
                        ctx.status(404);
                    })
                    .routes(() -> {
                        path("mascota", () -> {
                            get(mascotaControlador::listar);
                            post(mascotaControlador::crear);
                            path(":idMascota", () -> {
                                //get(mascotaControlador::obtener);
                                delete(mascotaControlador::borrar);
                                put(mascotaControlador::modificar);
                            });
                        });
                    })
                    .exception(MascotaNoEncontradaExcepcion.class, (e, ctx) -> {
                        ctx.status(404);
                    })
                    .routes(() -> {
                        path("especie", () -> {
                            get(especieControlador::listar);
                            post(especieControlador::crear);
                            path(":idEspecie", () -> {
                                delete(especieControlador::borrar);
                                put(especieControlador::modificar);
                            });
                        });
                    })
                    .exception(EspecieNoEncontradaExcepcion.class, (e, ctx) -> {
                        ctx.status(404);
                    })
                    .routes(() -> {
                        path("raza", () -> {
                            get(razaControlador::listar);
                            post(razaControlador::crear);
                            path(":idRaza", () -> {
                                delete(razaControlador::borrar);
                                put(razaControlador::modificar);
                            });
                        });
                    })
                    .exception(RazaNoEncontradaExcepcion.class, (e, ctx) -> {
                        ctx.status(404);
                    })
                    .routes(() -> {
                        path("guardia", () -> {
                            get(guardiaControlador::listar);
                            post(guardiaControlador::crear);
                            path(":idGuardia", () -> {
                                delete(guardiaControlador::borrar);
                                put(guardiaControlador::modificar);
                            });
                        });
                    })
                    .exception(GuardiaNoEncontradaExcepcion.class, (e, ctx) -> {
                        ctx.status(404);
                    })
                    .routes(() -> {
                        path("emergencia", () -> {
                            get(emergenciaControlador::listar);
                            post(emergenciaControlador::crear);
                            path(":idEmergencia", () -> {
                                delete(emergenciaControlador::borrar);
                                put(emergenciaControlador::modificar);
                            });
                        });
                    })
                    .exception(EmergenciaNoEncontradaExcepcion.class, (e, ctx) -> {
                        ctx.status(404);
                    })
                    .routes(() -> {
                        path("grupo", () -> {
                            get(grupoControlador::listar);
                            post(grupoControlador::crear);
                            path(":idGrupo", () -> {
                                delete(grupoControlador::borrar);
                                put(grupoControlador::modificar);
                            });
                        });
                    })
                    .exception(GrupoFamiliarNoEncontradoExcepcion.class, (e, ctx) -> {
                        ctx.status(404);
                    })
                    .routes(() -> {
                        path("consejo", () -> {
                            get(consejoControlador::listar);
                            post(consejoControlador::crear);
                            path(":idConsejo", () -> {
                                delete(consejoControlador::borrar);
                                put(consejoControlador::modificar);
                            });
                        });
                    })
                    .exception(ConsejoNoEncontradoExcepcion.class, (e, ctx) -> {
                        ctx.status(404);
                    })
                    .start(7000);

            //Si la conexion fue realizada con exito, muestra el sgte mensaje.
            System.out.println("Conexion exitosa!");
        } //Si se produce una Excepcion y no nos podemos conectar, muestra el sgte. mensaje.
        catch (SQLException e) {
            System.out.println("Problema con la conexion: " + e);
        }

        /*Javalin.create()
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
        .start(7000);*/
    }

}
