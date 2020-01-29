/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositorios;

import Modelos.Usuario;
import Modelos.Veterinario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lau
 */
public class VeterinarioRepositorio {
    private final Connection conexion;

    public VeterinarioRepositorio(Connection connection) throws SQLException {
        this.conexion = connection;
        var consulta = connection.createStatement();
        consulta.execute("CREATE TABLE IF NOT EXISTS veterinario (identificador INTEGER PRIMARY KEY AUTOINCREMENT, matricula TEXT, idusuario INTEGER, boolean estado, FOREIGN KEY(idusuario) REFERENCES usuario(identificador))");
        consulta.close();
    }

    public List<Veterinario> listar() throws SQLException {
        var veterinarios = new ArrayList<Veterinario>();
        var consulta = conexion.createStatement();
        var resultado = consulta.executeQuery("SELECT identificador, matricula, telefono, idusuario, estado FROM veterinario");
        while (resultado.next()) {
            veterinarios.add(
                new Veterinario(
                    resultado.getInt("identificador"),
                    resultado.getString("matricula"),
                    resultado.getString("telefono"),
                    resultado.getInt("idusuario"),
                    resultado.getBoolean("estado")
                    
                )
            );
        }
        resultado.close();
        consulta.close();
        return veterinarios;
    }

    public Veterinario obtener(int identificador) throws SQLException, VeterinarioNoEncontradoExcepcion {
        var consulta = conexion.prepareStatement("SELECT identificador, matricula, telefono, idusuario, estado FROM veterinario WHERE identificador = ?");
        consulta.setInt(1, identificador);
        var resultado = consulta.executeQuery();
        try {
            if (resultado.next()) {
                return new Veterinario(
                        resultado.getInt("identificador"),
                        resultado.getString("matricula"),
                        resultado.getString("telefono"),
                        resultado.getInt("idusuario"),
                        resultado.getBoolean("estado")
                        
                );
            } else {
                throw new VeterinarioNoEncontradoExcepcion();
            }
        }
        finally {
            consulta.close();
            resultado.close();
        }
    }

    public void crear(String matricula, String telefono, int idusuario, boolean estado) throws SQLException {
        var consulta = conexion.prepareStatement("INSERT INTO veterinario (identificador, matricula, telefono, idusuario, estado) VALUES (?, ?, ?, ?, ?)");
        consulta.setString(1, matricula);
        consulta.setString(2, telefono);
        consulta.setInt(3, idusuario);
        consulta.setBoolean(4, estado);
        consulta.executeUpdate();        
        consulta.close();
    }

    public void modificar(Veterinario veterinario) throws SQLException, VeterinarioNoEncontradoExcepcion {
        var consulta = conexion.prepareStatement("UPDATE veterinario SET matricula = ? WHERE identificador = ?");
        consulta.setString(1, veterinario.getMatricula());
        //consulta.setString(2, veterinario.getApellido());
        consulta.setInt(3, veterinario.getIdentificador());
        try {
            if (consulta.executeUpdate() == 0) throw new VeterinarioNoEncontradoExcepcion();
        }
        finally {
            consulta.close();
        }
    }

    public void borrar(Veterinario veterinario) throws SQLException, VeterinarioNoEncontradoExcepcion {
        var consulta = conexion.prepareStatement("UPDATE veterinario SET estado = 0 WHERE identificador = ?");
        consulta.setInt(1, veterinario.getIdentificador());
        try {
            if (consulta.executeUpdate() == 0) throw new VeterinarioNoEncontradoExcepcion();
        }
        finally {
            consulta.close();
        }
    }
}
