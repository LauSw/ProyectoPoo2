/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositorios;

import Modelos.Rol;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lau
 */
public class RolRepositorio {
    private final Connection conexion;

    public RolRepositorio(Connection connection) throws SQLException {
        this.conexion = connection;
        var consulta = connection.createStatement();
        consulta.execute("CREATE TABLE IF NOT EXISTS rol (identificador INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, estado BOOLEAN)");
        consulta.close();
    }

    public List<Rol> listar() throws SQLException {
        var roles = new ArrayList<Rol>();
        var consulta = conexion.createStatement();
        var resultado = consulta.executeQuery("SELECT identificador, nombre FROM rol");
        while (resultado.next()) {
            roles.add(
                new Rol(
                    resultado.getInt("identificador"),
                    resultado.getString("nombre"),
                    resultado.getBoolean("estado")
                )
            );
        }
        resultado.close();
        consulta.close();
        return roles;
    }

    public Rol obtener(int identificador) throws SQLException, RolNoEncontradoExcepcion {
        var consulta = conexion.prepareStatement("SELECT identificador, nombre, estado FROM rol WHERE identificador = ?");
        consulta.setInt(1, identificador);
        var resultado = consulta.executeQuery();
        try {
            if (resultado.next()) {
                return new Rol(
                    resultado.getInt("identificador"),
                    resultado.getString("nombre"),
                    resultado.getBoolean("estado")
                );
            } else {
                throw new RolNoEncontradoExcepcion();
            }
        }
        finally {
            consulta.close();
            resultado.close();
        }
    }

    public void crear(String nombre, boolean estado) throws SQLException {
        var consulta = conexion.prepareStatement("INSERT INTO rol (nombre, estado) VALUES (?, ?)");
        consulta.setString(1, nombre);
        consulta.setBoolean(2, estado);
        consulta.executeUpdate();        
        consulta.close();
    }

    public void modificar(Rol rol) throws SQLException, RolNoEncontradoExcepcion {
        var consulta = conexion.prepareStatement("UPDATE rol SET nombre = ? WHERE identificador = ?");
        consulta.setString(1, rol.getNombre());
        consulta.setInt(3, rol.getIdentificador());
        try {
            if (consulta.executeUpdate() == 0) throw new RolNoEncontradoExcepcion();
        }
        finally {
            consulta.close();
        }
    }

    public void borrar(Rol rol) throws SQLException, RolNoEncontradoExcepcion {
        var consulta = conexion.prepareStatement("UPDATE rol SET estado = 0 WHERE identificador = ?");
        consulta.setInt(1, rol.getIdentificador());
        consulta.setBoolean(2, rol.getEstado());
        try {
            if (consulta.executeUpdate() == 0) throw new RolNoEncontradoExcepcion();
        }
        finally {
            consulta.close();
        }
    }
}
