package database;

import resources.Constantes;
import modelo.Eventos;
import modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    // Método para iniciar sesión (verificar credenciales)
    public static boolean loginUser(String nombre, String contrasenya) {
        String SQL = "SELECT * FROM usuarios WHERE nombre = ? AND contrasenya = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            // Establecer los parámetros para la consulta
            pstmt.setString(1, nombre);
            pstmt.setString(2, contrasenya);

            // Ejecutar la consulta y obtener el resultado
            ResultSet rs = pstmt.executeQuery();

            // Si hay un resultado, las credenciales son correctas
            if (rs.next()) {
                System.out.println("Inicio de sesión exitoso");
                return true;
            } else {
                System.out.println("Nombre de usuario o contraseña incorrectos");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
            return false;
        }
    }

    public static void obtenerEventos() {
        String SQL = "SELECT nombre, fecha, edad FROM eventos";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL);
             ResultSet rs = pstmt.executeQuery()) {

            // Recorremos los resultados y creamos objetos Evento
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String f = rs.getString("fecha");
                int edad = rs.getInt("edad");
                Date fecha=Constantes.dateFormat.parse(f);


                // Creamos un nuevo objeto Evento y lo añadimos a la lista
                Eventos evento = new Eventos(nombre, fecha, edad);
                System.out.println(evento.toString());
                Constantes.EVENTOS.add(evento);
            }

        } catch (SQLException e) {
            System.out.println("Error al leer los eventos: " + e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
