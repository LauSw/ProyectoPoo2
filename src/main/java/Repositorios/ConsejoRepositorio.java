/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositorios;

import Modelos.Consejo;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lau
 */
public class ConsejoRepositorio {
private final Connection conexion;

    public ConsejoRepositorio(Connection connection) throws SQLException {
        this.conexion = connection;
        var consulta = connection.createStatement();
        consulta.execute("CREATE TABLE IF NOT EXISTS consejo (identificador INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, contenido TEXT, estado BOOLEAN, desde TEXT, hasta TEXT, idraza INTEGER, FOREIGN KEY(idraza) REFERENCES raza(identificador))");
        consulta.close();
    }

    public List<Consejo> listar() throws SQLException {
        var consejo = new ArrayList<Consejo>();
        var consulta = conexion.createStatement();
        var resultado = consulta.executeQuery("SELECT identificador, titulo, contenido, estado, desde, hasta, idraza FROM consejo");
        while (resultado.next()) {
            consejo.add(
                new Consejo(
                    resultado.getInt("identificador"),
                    resultado.getString("titulo"),
                    resultado.getString("contenido"),
                    resultado.getString("desde"),
                    resultado.getString("hasta"),
                    resultado.getBoolean("estado"),
                    resultado.getInt("idraza")
                )
            );
        }
        resultado.close();
        consulta.close();
        return consejo;
    }

    public Consejo obtener(int identificador) throws SQLException, ConsejoNoEncontradoExcepcion {
        var consulta = conexion.prepareStatement("SELECT identificador, nombre, estado, idprovincia FROM ciudad WHERE identificador = ?");
        consulta.setInt(1, identificador);
        var resultado = consulta.executeQuery();
        try {
            if (resultado.next()) {
                return new Consejo(
                    resultado.getInt("identificador"),
                    resultado.getString("titulo"),
                    resultado.getString("contenido"),
                    resultado.getString("desde"),
                    resultado.getString("hasta"),
                    resultado.getBoolean("estado"),
                    resultado.getInt("idraza")
                );
            } else {
                throw new ConsejoNoEncontradoExcepcion();
            }
        }
        finally {
            consulta.close();
            resultado.close();
        }
    }

    public void crear(String titulo, String contenido, String desde, String hasta, boolean estado, int idraza) throws SQLException {
        var consulta = conexion.prepareStatement("INSERT INTO consejo (titulo, contenido, desde, hasta, estado, idraza) VALUES (?, ?, ?, ?, ?, ?)");
        consulta.setString(1, titulo);
        consulta.setString(2, contenido);
        consulta.setString(3, desde);
        consulta.setString(4, hasta);
        consulta.setBoolean(5, estado);
        consulta.setInt(6, idraza);
        consulta.executeUpdate();        
        consulta.close();
    }

    public void modificar(Consejo consejo) throws SQLException, ConsejoNoEncontradoExcepcion {
        var consulta = conexion.prepareStatement("UPDATE consejo SET titulo = ? WHERE identificador = ?");
        consulta.setString(1, consejo.getTitulo());
        //consulta.setInt(2, ciudad.getIdprovincia());
        consulta.setInt(3, consejo.getIdentificador());
        try {
            if (consulta.executeUpdate() == 0) throw new ConsejoNoEncontradoExcepcion();
        }
        finally {
            consulta.close();
        }
    }

    public void borrar(Consejo consejo) throws SQLException, ConsejoNoEncontradoExcepcion {
        var consulta = conexion.prepareStatement("UPDATE consejo SET estado = 0 WHERE identificador = ?");
        consulta.setInt(1, consejo.getIdentificador());
        consulta.setBoolean(2, consejo.getEstado());
        try {
            if (consulta.executeUpdate() == 0) throw new ConsejoNoEncontradoExcepcion();
        }
        finally {
            consulta.close();
        }
    }
}
