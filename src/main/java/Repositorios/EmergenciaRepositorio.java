/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositorios;

import Modelos.Emergencia;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lau
 */
public class EmergenciaRepositorio {
    private final Connection conexion;

    public EmergenciaRepositorio(Connection connection) throws SQLException {
        this.conexion = connection;
        var consulta = connection.createStatement();
        consulta.execute("CREATE TABLE IF NOT EXISTS emergencia (identificador INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, descripcion TEXT, fecha TEXT, estado BOOLEAN, idguardia INTEGER, idusuario INTEGER, FOREIGN KEY(idguardia) REFERENCES guardia(identificador), FOREIGN KEY(idusuario) REFERENCES usuario(identificador))");
        consulta.close();
    }

    public List<Emergencia> listar() throws SQLException {
        var emergencia = new ArrayList<Emergencia>();
        var consulta = conexion.createStatement();
        var resultado = consulta.executeQuery("SELECT identificador, titulo, descripcion, fecha, estado, idguardia, idusuario FROM emergencia");
        while (resultado.next()) {
            emergencia.add(
                new Emergencia(
                    resultado.getInt("identificador"),
                    resultado.getString("titulo"),
                    resultado.getString("descripcion"),
                    resultado.getDate("fecha"),
                    resultado.getInt("estado"),
                    resultado.getInt("idguardia"),
                    resultado.getInt("idusuario")
                )
            );
        }
        resultado.close();
        consulta.close();
        return emergencia;
    }

    public Emergencia obtener(int identificador) throws SQLException, EmergenciaNoEncontradaExcepcion {
        var consulta = conexion.prepareStatement("SELECT identificador, titulo, descripcion, fecha, estado, idguardia, idusuario FROM emergencia WHERE identificador = ?");
        consulta.setInt(1, identificador);
        var resultado = consulta.executeQuery();
        try {
            if (resultado.next()) {
                return new Emergencia(
                    resultado.getInt("identificador"),
                    resultado.getString("titulo"),
                    resultado.getString("descripcion"),
                    resultado.getDate("fecha"),
                    resultado.getInt("estado"),
                    resultado.getInt("idguardia"),
                    resultado.getInt("idusuario")
                );
            } else {
                throw new EmergenciaNoEncontradaExcepcion();
            }
        }
        finally {
            consulta.close();
            resultado.close();
        }
    }

    public void crear(String titulo, String descripcion, Date fecha, int estado, int idguardia, int idusuario) throws SQLException {
        var consulta = conexion.prepareStatement("INSERT INTO emergencia (titulo, descripcion, fecha, estado, idguardia, idusuario) VALUES (?, ?, ?, ?, ?, ?)");
        consulta.setString(1, titulo);
        consulta.setString(2, descripcion);
        consulta.setDate(3, fecha);
        consulta.setInt(4, estado);
        consulta.setInt(5, idguardia);
        consulta.setInt(6, idusuario);
        consulta.executeUpdate();        
        consulta.close();
    }

    
    public void borrar(Emergencia emergencia) throws SQLException, EmergenciaNoEncontradaExcepcion {
        var consulta = conexion.prepareStatement("UPDATE emergencia SET estado = 0 WHERE identificador = ?");
        consulta.setInt(1, emergencia.getIdentificador());
        consulta.setInt(2, emergencia.getEstado());
        try {
            if (consulta.executeUpdate() == 0) throw new EmergenciaNoEncontradaExcepcion();
        }
        finally {
            consulta.close();
        }
    }
}
