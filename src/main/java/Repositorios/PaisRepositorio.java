/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositorios;

import Modelos.Pais;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lau
 */
public class PaisRepositorio {
     private final Connection conexion;

    public PaisRepositorio(Connection connection) throws SQLException {
        this.conexion = connection;
        var consulta = connection.createStatement();
        consulta.execute("CREATE TABLE IF NOT EXISTS pais (identificador INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, estado BOOLEAN)");
        consulta.close();
    }

    public List<Pais> listar() throws SQLException {
        var pais = new ArrayList<Pais>();
        var consulta = conexion.createStatement();
        var resultado = consulta.executeQuery("SELECT identificador, nombre, estado FROM pais");
        while (resultado.next()) {
            pais.add(
                new Pais(
                    resultado.getInt("identificador"),
                    resultado.getString("nombre"),
                    resultado.getBoolean("estado")
                )
            );
        }
        resultado.close();
        consulta.close();
        return pais;
    }

    public Pais obtener(int identificador) throws SQLException, PaisNoEncontradoExcepcion {
        var consulta = conexion.prepareStatement("SELECT identificador, nombre, estado FROM pais WHERE identificador = ?");
        consulta.setInt(1, identificador);
        var resultado = consulta.executeQuery();
        try {
            if (resultado.next()) {
                return new Pais(
                    resultado.getInt("identificador"),
                    resultado.getString("nombre"),
                    resultado.getBoolean("estado")
                );
            } else {
                throw new PaisNoEncontradoExcepcion();
            }
        }
        finally {
            consulta.close();
            resultado.close();
        }
    }

    public void crear(String nombre, boolean estado) throws SQLException {
        var consulta = conexion.prepareStatement("INSERT INTO ciudad (nombre, estado) VALUES (?, ?)");
        consulta.setString(1, nombre);
        consulta.setBoolean(2, estado);
        consulta.executeUpdate();        
        consulta.close();
    }

    public void modificar(Pais pais) throws SQLException, PaisNoEncontradoExcepcion {
        var consulta = conexion.prepareStatement("UPDATE pais SET nombre = ? WHERE identificador = ?");
        consulta.setString(1, pais.getNombre());
        //consulta.setInt(2, ciudad.getIdprovincia());
        consulta.setInt(3, pais.getIdentificador());
        try {
            if (consulta.executeUpdate() == 0) throw new PaisNoEncontradoExcepcion();
        }
        finally {
            consulta.close();
        }
    }

    public void borrar(Pais pais) throws SQLException, PaisNoEncontradoExcepcion {
        var consulta = conexion.prepareStatement("UPDATE pais SET estado = 0 WHERE identificador = ?");
        consulta.setInt(1, pais.getIdentificador());
        consulta.setBoolean(2, pais.getEstado());
        try {
            if (consulta.executeUpdate() == 0) throw new PaisNoEncontradoExcepcion();
        }
        finally {
            consulta.close();
        }
    }
}
