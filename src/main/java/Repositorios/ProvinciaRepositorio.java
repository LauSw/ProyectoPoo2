/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositorios;

import Modelos.Pais;
import Modelos.Provincia;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lau
 */
public class ProvinciaRepositorio {
        private final Connection conexion;

    public ProvinciaRepositorio(Connection connection) throws SQLException {
        this.conexion = connection;
        var consulta = connection.createStatement();
        consulta.execute("CREATE TABLE IF NOT EXISTS provincia (identificador INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, estado BOOLEAN, idpais INTEGER, FOREIGN KEY(idpais) REFERENCES pais(identificador))");
        consulta.close();
    }

    public List<Provincia> listar() throws SQLException {
        var provincia = new ArrayList<Provincia>();
        var consulta = conexion.createStatement();
        var resultado = consulta.executeQuery("SELECT identificador, nombre, estado, idpais FROM provincia");
        while (resultado.next()) {
            provincia.add(
                new Provincia(
                    resultado.getInt("identificador"),
                    resultado.getString("nombre"),
                    resultado.getBoolean("estado"),
                    resultado.getInt("idpais")
                )
            );
        }
        resultado.close();
        consulta.close();
        return provincia;
    }

    public Provincia obtener(int identificador) throws SQLException, ProvinciaNoEncontradaExcepcion {
        var consulta = conexion.prepareStatement("SELECT identificador, nombre, estado, idpais FROM provincia WHERE identificador = ?");
        consulta.setInt(1, identificador);
        var resultado = consulta.executeQuery();
        try {
            if (resultado.next()) {
                return new Provincia(
                    resultado.getInt("identificador"),
                    resultado.getString("nombre"),
                    resultado.getBoolean("estado"),
                    resultado.getInt("idpais")
                );
            } else {
                throw new ProvinciaNoEncontradaExcepcion();
            }
        }
        finally {
            consulta.close();
            resultado.close();
        }
    }

    public void crear(String nombre, boolean estado, int idpais) throws SQLException {
        var consulta = conexion.prepareStatement("INSERT INTO provincia (nombre, estado, idpais) VALUES (?, ?, ?)");
        consulta.setString(1, nombre);
        consulta.setBoolean(2, estado);
        consulta.setInt(3, idpais);
        consulta.executeUpdate();        
        consulta.close();
    }

    public void modificar(Provincia provincia) throws SQLException, ProvinciaNoEncontradaExcepcion {
        var consulta = conexion.prepareStatement("UPDATE provincia SET nombre = ? WHERE identificador = ?");
        consulta.setString(1, provincia.getNombre());
        //consulta.setInt(2, ciudad.getIdprovincia());
        consulta.setInt(3, provincia.getIdentificador());
        try {
            if (consulta.executeUpdate() == 0) throw new ProvinciaNoEncontradaExcepcion();
        }
        finally {
            consulta.close();
        }
    }

    public void borrar(Provincia provincia) throws SQLException, ProvinciaNoEncontradaExcepcion {
        var consulta = conexion.prepareStatement("UPDATE provincia SET estado = 0 WHERE identificador = ?");
        consulta.setInt(1, provincia.getIdentificador());
        consulta.setBoolean(2, provincia.getEstado());
        try {
            if (consulta.executeUpdate() == 0) throw new ProvinciaNoEncontradaExcepcion();
        }
        finally {
            consulta.close();
        }
    }
}
