/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositorios;


import Modelos.Mascota;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lau
 */
public class MascotaRepositorio {
    private final Connection conexion;

    public MascotaRepositorio(Connection connection) throws SQLException {
        this.conexion = connection;
       /* var consulta = connection.createStatement();
        consulta.execute("CREATE TABLE IF NOT EXISTS mascota (identificador INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " nombre TEXT, nacimiento TEXT, estado INTEGER, idraza INTEGER, idgrupo INTEGER,"
                + " FOREIGN KEY(idraza) REFERENCES raza(identificador),"
                + " FOREIGN KEY(idgrupo) REFERENCES grupofamiliar(identificador))");
        consulta.close();*/
    }

    public List<Mascota> listar() throws SQLException {
        var personas = new ArrayList<Mascota>();
        var consulta = conexion.createStatement();
        var resultado = consulta.executeQuery("SELECT identificador, nombre, nacimiento, estado, idraza, idgrupo FROM mascota");
        while (resultado.next()) {
            personas.add(
                new Mascota(
                    resultado.getInt("identificador"),
                    resultado.getString("nombre"),
                    resultado.getDate("nacimiento"),
                    resultado.getBoolean("estado"),
                    resultado.getInt("idraza"),
                    resultado.getInt("idgrupo")
                    
                )
            );
        }
        resultado.close();
        consulta.close();
        return personas;
    }

    public Mascota obtener(int identificador) throws SQLException, MascotaNoEncontradaExcepcion {
        var consulta = conexion.prepareStatement("SELECT identificador, nombre, nacimiento, estado, idraza, idgrupo FROM mascota WHERE identificador = ?");
        consulta.setInt(1, identificador);
        var resultado = consulta.executeQuery();
        try {
            if (resultado.next()) {
                return new Mascota(
                    resultado.getInt("identificador"),
                    resultado.getString("nombre"),
                    resultado.getDate("nacimiento"),
                    resultado.getBoolean("estado"),
                    resultado.getInt("idraza"),
                    resultado.getInt("idgrupo")
                    
                
                );
            } else {
                throw new MascotaNoEncontradaExcepcion();
            }
        }
        finally {
            consulta.close();
            resultado.close();
        }
    }

    public void crear(String nombre, Date nacimiento, Boolean estado, int idraza, int idgrupo) throws SQLException {
        var consulta = conexion.prepareStatement("INSERT INTO mascota (identificador, nombre, nacimiento, estado, idraza, idgrupo) VALUES (?, ?, ?, ?, ?)");
        consulta.setString(1, nombre);
        consulta.setDate(2, nacimiento);
        consulta.setBoolean(3, estado);
        consulta.setInt(4, idraza);
        consulta.setInt(5, idgrupo);
        consulta.executeUpdate();        
        consulta.close();
    }

    public void modificar(Mascota mascota) throws SQLException, MascotaNoEncontradaExcepcion {
        var consulta = conexion.prepareStatement("UPDATE mascota SET fecha_inicio = ?, fecha_fin = ?, idveterinario = ?, idciudad = ? WHERE identificador = ?");
        consulta.setString(1, mascota.getNombre());
        consulta.setDate(2, mascota.getNacimiento());
        consulta.setInt(3, mascota.getIdentificador());
        try {
            if (consulta.executeUpdate() == 0) throw new MascotaNoEncontradaExcepcion();
        }
        finally {
            consulta.close();
        }
    }

    public void borrar(Mascota mascota) throws SQLException, MascotaNoEncontradaExcepcion {
        var consulta = conexion.prepareStatement("UPDATE mascota SET estado = 0 WHERE identificador = ?");
        consulta.setInt(1, mascota.getIdentificador());
        consulta.setBoolean(2, mascota.getEstado());
        try {
            if (consulta.executeUpdate() == 0) throw new MascotaNoEncontradaExcepcion();
        }
        finally {
            consulta.close();
        }
    }
}
