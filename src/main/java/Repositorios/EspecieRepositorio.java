/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositorios;

import Modelos.Especie;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lau
 */
public class EspecieRepositorio {
    private final Connection conexion;

    public EspecieRepositorio(Connection connection) throws SQLException {
        this.conexion = connection;
        var consulta = connection.createStatement();
        consulta.execute("CREATE TABLE IF NOT EXISTS especie (identificador INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, estado BOOLEAN)");
        consulta.close();
    }

    public List<Especie> listar() throws SQLException {
        var especies = new ArrayList<Especie>();
        var consulta = conexion.createStatement();
        var resultado = consulta.executeQuery("SELECT identificador, nombre FROM especie");
        while (resultado.next()) {
            especies.add(
                new Especie(
                    resultado.getInt("identificador"),
                    resultado.getString("nombre"),
                    resultado.getInt("estado")
                )
            );
        }
        resultado.close();
        consulta.close();
        return especies;
    }

    public Especie obtener(int identificador) throws SQLException, EspecieNoEncontradaExcepcion {
        var consulta = conexion.prepareStatement("SELECT identificador, nombre, estado FROM especie WHERE identificador = ?");
        consulta.setInt(1, identificador);
        var resultado = consulta.executeQuery();
        try {
            if (resultado.next()) {
                return new Especie(
                    resultado.getInt("identificador"),
                    resultado.getString("nombre"),
                    resultado.getInt("estado")
                );
            } else {
                throw new EspecieNoEncontradaExcepcion();
            }
        }
        finally {
            consulta.close();
            resultado.close();
        }
    }

    public void crear(String nombre, boolean estado) throws SQLException {
        var consulta = conexion.prepareStatement("INSERT INTO especie (nombre, estado) VALUES (?, ?)");
        consulta.setString(1, nombre);
        consulta.setBoolean(2, estado);
        consulta.executeUpdate();        
        consulta.close();
    }

    public void modificar(Especie especie) throws SQLException, EspecieNoEncontradaExcepcion {
        var consulta = conexion.prepareStatement("UPDATE especie SET nombre = ? WHERE identificador = ?");
        consulta.setString(1, especie.getNombre());
        consulta.setInt(3, especie.getIdentificador());
        try {
            if (consulta.executeUpdate() == 0) throw new EspecieNoEncontradaExcepcion();
        }
        finally {
            consulta.close();
        }
    }

    public void borrar(Especie especie) throws SQLException, EspecieNoEncontradaExcepcion {
        var consulta = conexion.prepareStatement("UPDATE especie SET estado = 0 WHERE identificador = ?");
        consulta.setInt(1, especie.getIdentificador());
        consulta.setInt(2, especie.getEstado());
        try {
            if (consulta.executeUpdate() == 0) throw new EspecieNoEncontradaExcepcion();
        }
        finally {
            consulta.close();
        }
    }
}
