/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositorios;

import Modelos.GrupoFamiliar;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lau
 */
public class GrupoFamiliarRepositorio {
    private final Connection conexion;

    public GrupoFamiliarRepositorio(Connection connection) throws SQLException {
        this.conexion = connection;
        var consulta = connection.createStatement();
        consulta.execute("CREATE TABLE IF NOT EXISTS grupofamiliar (identificador INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, contraseña TEXT, idveterinario INTEGER, idciudad INTEGER, estado INTEGER, FOREIGN KEY(idveterinario) REFERENCES veterinario(identificador), FOREIGN KEY(idciudad) REFERENCES ciudad(identificador))");
        consulta.close();
    }

    public List<GrupoFamiliar> listar() throws SQLException {
        var personas = new ArrayList<GrupoFamiliar>();
        var consulta = conexion.createStatement();
        var resultado = consulta.executeQuery("SELECT identificador, nombre, contraseña, estado FROM grupofamiliar");
        while (resultado.next()) {
            personas.add(
                new GrupoFamiliar(
                    resultado.getInt("identificador"),
                    resultado.getString("nombre"),
                    resultado.getString("contraseña"),
                    resultado.getBoolean("estado")
                    
                )
            );
        }
        resultado.close();
        consulta.close();
        return personas;
    }

    public GrupoFamiliar obtener(int identificador) throws SQLException, GrupoFamiliarNoEncontradoExcepcion {
        var consulta = conexion.prepareStatement("SELECT identificador, nombre, contraseña, estado FROM grupofamiliar WHERE identificador = ?");
        consulta.setInt(1, identificador);
        var resultado = consulta.executeQuery();
        try {
            if (resultado.next()) {
                return new GrupoFamiliar(
                    resultado.getInt("identificador"),
                    resultado.getString("nombre"),
                    resultado.getString("contraseña"),
                    resultado.getBoolean("estado")
                );
            } else {
                throw new GrupoFamiliarNoEncontradoExcepcion();
            }
        }
        finally {
            consulta.close();
            resultado.close();
        }
    }

    public void crear(String nombre, String contraseña, boolean estado) throws SQLException {
        var consulta = conexion.prepareStatement("INSERT INTO grupofamiliar (nombre, contraseña, estado) VALUES (?, ?, ?)");
        consulta.setString(1, nombre);
        consulta.setString(2, contraseña);
        consulta.setBoolean(3, estado);
        consulta.executeUpdate();        
        consulta.close();
    }

    public void modificar(GrupoFamiliar grupo) throws SQLException, GrupoFamiliarNoEncontradoExcepcion {
        var consulta = conexion.prepareStatement("UPDATE grupofamiliar SET nombre = ?, contraseña = ? WHERE identificador = ?");
        consulta.setString(1, grupo.getNombre());
        consulta.setString(2, grupo.getContraseña());
        try {
            if (consulta.executeUpdate() == 0) throw new GrupoFamiliarNoEncontradoExcepcion();
        }
        finally {
            consulta.close();
        }
    }

    public void borrar(GrupoFamiliar grupo) throws SQLException, GrupoFamiliarNoEncontradoExcepcion {
        var consulta = conexion.prepareStatement("UPDATE grupofamiliar SET estado = 0 WHERE identificador = ?");
        consulta.setInt(1, grupo.getIdentificador());
        consulta.setBoolean(2, grupo.getEstado());
        try {
            if (consulta.executeUpdate() == 0) throw new GrupoFamiliarNoEncontradoExcepcion();
        }
        finally {
            consulta.close();
        }
    }
}
