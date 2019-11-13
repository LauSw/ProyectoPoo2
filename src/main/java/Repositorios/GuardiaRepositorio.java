/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositorios;

import Modelos.Guardia;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lau
 */
public class GuardiaRepositorio {
    private final Connection conexion;

    public GuardiaRepositorio(Connection connection) throws SQLException {
        this.conexion = connection;
        var consulta = connection.createStatement();
        consulta.execute("CREATE TABLE IF NOT EXISTS guardia (identificador INTEGER PRIMARY KEY AUTOINCREMENT, fecha_inicio DATE, fecha_fin DATE, idveterinario INTEGER, idciudad INTEGER, estado INTEGER, FOREIGN KEY(idveterinario) REFERENCES veterinario(identificador), FOREIGN KEY(idciudad) REFERENCES ciudad(identificador))");
        consulta.close();
    }

    public List<Guardia> listar() throws SQLException {
        var personas = new ArrayList<Guardia>();
        var consulta = conexion.createStatement();
        var resultado = consulta.executeQuery("SELECT identificador, fecha_inicio, fecha_fin, estado, idveterinario, idciudad FROM guardia");
        while (resultado.next()) {
            personas.add(
                new Guardia(
                    resultado.getInt("identificador"),
                    resultado.getDate("fecha_inicio"),
                    resultado.getDate("fecha_fin"),
                    resultado.getBoolean("estado"),
                    resultado.getInt("idveterinario"),
                    resultado.getInt("idciudad")
                    
                )
            );
        }
        resultado.close();
        consulta.close();
        return personas;
    }

    public Guardia obtener(int identificador) throws SQLException, GuardiaNoEncontradaExcepcion {
        var consulta = conexion.prepareStatement("SELECT identificador, fecha_inicio, fecha_fin, estado, idveterinario, idciudad FROM guardia WHERE identificador = ?");
        consulta.setInt(1, identificador);
        var resultado = consulta.executeQuery();
        try {
            if (resultado.next()) {
                return new Guardia(
                    resultado.getInt("identificador"),
                    resultado.getDate("fecha_inicio"),
                    resultado.getDate("fecha_fin"),
                    resultado.getBoolean("estado"),
                    resultado.getInt("idveterinario"),
                    resultado.getInt("idciudad")
                );
            } else {
                throw new GuardiaNoEncontradaExcepcion();
            }
        }
        finally {
            consulta.close();
            resultado.close();
        }
    }

    public void crear(Date fecha_inicio, Date fecha_fin, boolean estado, int idveterinario, int idciudad) throws SQLException {
        var consulta = conexion.prepareStatement("INSERT INTO guardia (fecha_inicio, fecha_fin, estado, idveterinario, idciudad) VALUES (?, ?, ?, ?, ?)");
        consulta.setDate(1, fecha_inicio);
        consulta.setDate(2, fecha_fin);
        consulta.setBoolean(3, estado);
        consulta.setInt(4, idveterinario);
        consulta.setInt(5, idciudad);
        //consulta.set(6, 1);
        consulta.executeUpdate();        
        consulta.close();
    }

    public void modificar(Guardia guardia) throws SQLException, GuardiaNoEncontradaExcepcion {
        var consulta = conexion.prepareStatement("UPDATE guardia SET fecha_inicio = ?, fecha_fin = ?, idveterinario = ?, idciudad = ? WHERE identificador = ?");
        consulta.setDate(1, guardia.getFecha_inicio());
        consulta.setDate(2, guardia.getFecha_fin());
        consulta.setInt(4, guardia.getIdveterinario());
        consulta.setInt(5, guardia.getIdciudad());
        try {
            if (consulta.executeUpdate() == 0) throw new GuardiaNoEncontradaExcepcion();
        }
        finally {
            consulta.close();
        }
    }

    public void borrar(Guardia guardia) throws SQLException, GuardiaNoEncontradaExcepcion {
        var consulta = conexion.prepareStatement("UPDATE guardia SET estado = 0 WHERE identificador = ?");
        consulta.setInt(1, guardia.getIdentificador());
        consulta.setBoolean(2, guardia.getEstado());
        try {
            if (consulta.executeUpdate() == 0) throw new GuardiaNoEncontradaExcepcion();
        }
        finally {
            consulta.close();
        }
    }
}
