/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositorios;

import Modelos.Ciudad;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lau
 */
public class CiudadRepositorio {
    private final Connection conexion;

    public CiudadRepositorio(Connection connection) throws SQLException {
        this.conexion = connection;
        var consulta = connection.createStatement();
        consulta.execute("CREATE TABLE IF NOT EXISTS ciudad (identificador INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, estado BOOLEAN, idprovincia INTEGER, FOREIGN KEY(idprovincia) REFERENCES provincia(identificador))");
        consulta.close();
    }

    public List<Ciudad> listar() throws SQLException {
        var ciudad = new ArrayList<Ciudad>();
        var consulta = conexion.createStatement();
        var resultado = consulta.executeQuery("SELECT identificador, nombre, estado, idprovincia FROM ciudad");
        while (resultado.next()) {
            ciudad.add(
                new Ciudad(
                    resultado.getInt("identificador"),
                    resultado.getString("nombre"),
                    resultado.getBoolean("estado"),
                    resultado.getInt("idprovincia")
                )
            );
        }
        resultado.close();
        consulta.close();
        return ciudad;
    }

    public Ciudad obtener(int identificador) throws SQLException, CiudadNoEncontradaExcepcion {
        var consulta = conexion.prepareStatement("SELECT identificador, nombre, estado, idprovincia FROM ciudad WHERE identificador = ?");
        consulta.setInt(1, identificador);
        var resultado = consulta.executeQuery();
        try {
            if (resultado.next()) {
                return new Ciudad(
                    resultado.getInt("identificador"),
                    resultado.getString("nombre"),
                    resultado.getBoolean("estado"),
                    resultado.getInt("idprovincia")
                );
            } else {
                throw new CiudadNoEncontradaExcepcion();
            }
        }
        finally {
            consulta.close();
            resultado.close();
        }
    }

    public void crear(String nombre, boolean estado, int idprovincia) throws SQLException {
        var consulta = conexion.prepareStatement("INSERT INTO ciudad (nombre, estado, idprovincia) VALUES (?, ?, ?)");
        consulta.setString(1, nombre);
        consulta.setBoolean(2, estado);
        consulta.setInt(3, idprovincia);
        consulta.executeUpdate();        
        consulta.close();
    }

    public void modificar(Ciudad ciudad) throws SQLException, CiudadNoEncontradaExcepcion {
        var consulta = conexion.prepareStatement("UPDATE ciudad SET nombre = ? WHERE identificador = ?");
        consulta.setString(1, ciudad.getNombre());
        //consulta.setInt(2, ciudad.getIdprovincia());
        consulta.setInt(3, ciudad.getIdentificador());
        try {
            if (consulta.executeUpdate() == 0) throw new CiudadNoEncontradaExcepcion();
        }
        finally {
            consulta.close();
        }
    }

    public void borrar(Ciudad ciudad) throws SQLException, CiudadNoEncontradaExcepcion {
        var consulta = conexion.prepareStatement("UPDATE ciudad SET estado = 0 WHERE identificador = ?");
        consulta.setInt(1, ciudad.getIdentificador());
        consulta.setBoolean(2, ciudad.getEstado());
        try {
            if (consulta.executeUpdate() == 0) throw new CiudadNoEncontradaExcepcion();
        }
        finally {
            consulta.close();
        }
    }
}
