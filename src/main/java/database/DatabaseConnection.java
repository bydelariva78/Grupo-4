package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String url = "jdbc:postgresql://127.0.0.1:5432/grupo4"; // Cambia <IP_VM> y <nombre_base_datos>
    private static final String user = "postgres";  // Cambia esto por tu usuario de PostgreSQL
    private static final String password = "postgres";  // Cambia esto por tu contraseña de PostgreSQL

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa a PostgreSQL");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // Método main para probar la conexión
    public static void main(String[] args) {
        DatabaseConnection app = new DatabaseConnection();
        app.connect();
    }
}
