package database;

import modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseOperations {


    public static void insertUser(Usuario usuario) {
        String SQL = "INSERT INTO usuarios(nombre, contrasenya) VALUES(?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getContrasenya());

            pstmt.executeUpdate();
            System.out.println("Usuario insertado con éxito");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
