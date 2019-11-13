/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositorios;

import Modelos.Raza;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lau
 */
public class RazaRepositorio {
    private final Connection conexion;

    public RazaRepositorio(Connection connection) throws SQLException {
        this.conexion = connection;
        var consulta = connection.createStatement();
        consulta.execute("CREATE TABLE IF NOT EXISTS raza (identificador INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, estado INTEGER, vida_aproximada INTEGER, idespecie INTEGER, FOREIGN KEY(idespecie) REFERENCES especie(identificador))");
        consulta.close();
    }

    public List<Raza> listar() throws SQLException {
        var razas = new ArrayList<Raza>();
        var consulta = conexion.createStatement();
        var resultado = consulta.executeQuery("SELECT identificador, nombre, estado, idespecie FROM raza");
        while (resultado.next()) {
            razas.add(
                new Raza(
                    resultado.getInt("identificador"),
                    resultado.getString("nombre"),
                    resultado.getBoolean("estado"),
                    resultado.getInt("idespecie")
                    
                )
            );
        }
        resultado.close();
        consulta.close();
        return razas;
    }

    public Raza obtener(int identificador) throws SQLException, RazaNoEncontradaExcepcion {
        var consulta = conexion.prepareStatement("SELECT identificador, nombre, idespecie FROM raza WHERE identificador = ?");
        consulta.setInt(1, identificador);
        var resultado = consulta.executeQuery();
        try {
            if (resultado.next()) {
                return new Raza(
                    resultado.getInt("identificador"),
                    resultado.getString("nombre"),
                    resultado.getBoolean("estado"),
                    resultado.getInt("idespecie")
                    
                
                );
            } else {
                throw new RazaNoEncontradaExcepcion();
            }
        }
        finally {
            consulta.close();
            resultado.close();
        }
    }

    public void crear(String nombre, int idespecie) throws SQLException {
        var consulta = conexion.prepareStatement("INSERT INTO raza (identificador, nombre, estado, idespecie) VALUES (?, ?, ?, ?)");
        consulta.setString(1, nombre);
        consulta.setInt(2, idespecie);
        consulta.executeUpdate();        
        consulta.close();
    }

    public void modificar(Raza raza) throws SQLException, RazaNoEncontradaExcepcion {
        var consulta = conexion.prepareStatement("UPDATE raza SET nombre = ?, idespecie = ? WHERE identificador = ?");
        consulta.setString(1, raza.getNombre());
        consulta.setInt(2, raza.getIdespecie());
        consulta.setInt(3, raza.getIdentificador());
        try {
            if (consulta.executeUpdate() == 0) throw new RazaNoEncontradaExcepcion();
        }
        finally {
            consulta.close();
        }
    }

    public void borrar(Raza raza) throws SQLException, RazaNoEncontradaExcepcion {
        var consulta = conexion.prepareStatement("UPDATE raza SET estado = 0 WHERE identificador = ?");
        consulta.setInt(1, raza.getIdentificador());
        consulta.setBoolean(2, raza.getEstado());
        try {
            if (consulta.executeUpdate() == 0) throw new RazaNoEncontradaExcepcion();
        }
        finally {
            consulta.close();
        }
    }
}
