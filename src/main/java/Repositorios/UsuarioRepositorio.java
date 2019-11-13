/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Repositorios;

import Modelos.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lau
 */
public class UsuarioRepositorio {
    private final Connection conexion;

    public UsuarioRepositorio(Connection connection) throws SQLException {
        this.conexion = connection;
        var consulta = connection.createStatement();
        consulta.execute("CREATE TABLE IF NOT EXISTS usuario (identificador INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT, dni TEXT, correo TEXT, password TEXT, estado INTEGER, idrol INTEGER, FOREIGN KEY(idrol) REFERENCES rol(identificador))");
        consulta.close();
    }

    public List<Usuario> listar() throws SQLException {
        var personas = new ArrayList<Usuario>();
        var consulta = conexion.createStatement();
        var resultado = consulta.executeQuery("SELECT identificador, nombre, apellido, dni, correo, password, estado, idrol FROM usuario");
        while (resultado.next()) {
            personas.add(
                new Usuario(
                    resultado.getInt("identificador"),
                    resultado.getString("nombre"),
                    resultado.getString("apellido"),
                    resultado.getString("dni"),
                    resultado.getString("correo"),
                    resultado.getString("password"),
                    resultado.getBoolean("estado"),
                    resultado.getInt("idrol")
                )
            );
        }
        resultado.close();
        consulta.close();
        return personas;
    }

    public Usuario obtener(int identificador) throws SQLException, UsuarioNoEncontradoExcepcion {
        var consulta = conexion.prepareStatement("SELECT identificador, nombre, apellido, dni, correo, password, estado, idrol FROM usuario WHERE identificador = ?");
        consulta.setInt(1, identificador);
        var resultado = consulta.executeQuery();
        try {
            if (resultado.next()) {
                return new Usuario(
                        resultado.getInt("identificador"),
                        resultado.getString("nombre"),
                        resultado.getString("apellido"),
                        resultado.getString("dni"),
                        resultado.getString("correo"),
                        resultado.getString("password"),
                        resultado.getBoolean("estado"),
                        resultado.getInt("idrol")
                );
            } else {
                throw new UsuarioNoEncontradoExcepcion();
            }
        }
        finally {
            consulta.close();
            resultado.close();
        }
    }

    public void crear(String nombre, String apellido, String dni, String correo, String contraseña, Integer idrol) throws SQLException {
        var consulta = conexion.prepareStatement("INSERT INTO usuario (nombre, apellido, dni, correo, contraseña, estado, idrol) VALUES (?, ?, ?, ?, ?, ?, ?)");
        consulta.setString(1, nombre);
        consulta.setString(2, apellido);
        consulta.setString(3, dni);
        consulta.setString(4, correo);
        consulta.setString(5, contraseña);
        consulta.setInt(6, idrol);
        consulta.executeUpdate();        
        consulta.close();
    }

    public void modificar(Usuario usuario) throws SQLException, UsuarioNoEncontradoExcepcion {
        var consulta = conexion.prepareStatement("UPDATE usuario SET nombre = ?, apellido = ? WHERE identificador = ?");
        consulta.setString(1, usuario.getNombre());
        consulta.setString(2, usuario.getApellido());
        consulta.setInt(3, usuario.getIdentificador());
        try {
            if (consulta.executeUpdate() == 0) throw new UsuarioNoEncontradoExcepcion();
        }
        finally {
            consulta.close();
        }
    }

    public void borrar(Usuario usuario) throws SQLException, UsuarioNoEncontradoExcepcion {
        var consulta = conexion.prepareStatement("UPDATE usuario SET estado = ? WHERE identificador = ?");
        consulta.setInt(1, usuario.getIdentificador());
        consulta.setBoolean(2, usuario.getEstado());
        try {
            if (consulta.executeUpdate() == 0) throw new UsuarioNoEncontradoExcepcion();
        }
        finally {
            consulta.close();
        }
    }
}
