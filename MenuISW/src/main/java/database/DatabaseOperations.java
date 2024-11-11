package database;

import modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DatabaseOperations {

    // Método para registrar un nuevo usuario
    //Habria que añadir que no pueden existir dos usuario con el mismo nombre
    public static HashMap<String,Object> insertUser(String nombre, String contrasena) {
        String SQL = "INSERT INTO usuarios(nombre, contrasenya, puntos) VALUES(?, ?, ?)";
        HashMap<String,Object> res = new HashMap<>();
        boolean nombreExistente = comprobarNombre(nombre, false);

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, nombre);
            pstmt.setString(2, contrasena);
            pstmt.setInt(3,100);


            if(nombreExistente){
                System.out.println("El nombre de usuario ya existe");
                res.put("registrado", false);
                return(res);
            }
            else{
                pstmt.executeUpdate();
                System.out.println("Usuario insertado con éxito");
                res.put("registrado", true);
                return res;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            res.put("registrado", false);
            return res;
        }
    }


    // Método para iniciar sesión (verificar credenciales)
    public static HashMap<String,Object> loginUser(String nombre, String contrasenya) {
        String SQL = "SELECT * FROM usuarios WHERE nombre = ? AND contrasenya = ?";
        HashMap<String,Object> res = new HashMap<>();
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
                res.put("usuario",new Usuario(nombre,contrasenya));
                res.put("encontrado",true);
                return res;
            } else {
                System.out.println("Nombre de usuario o contraseña incorrectos");
                res.put("encontrado",false);
                return res;
            }

        } catch (SQLException e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
            res.put("encontrado",false);
            return res;
        }
    }
    public static HashMap<String, Object> loginDisco(String nombre, String contrasena){
        String SQL = "SELECT * FROM eventos WHERE nombre = ? AND contrasenya = ?";
        HashMap<String,Object> res = new HashMap<>();
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            // Establecer los parámetros para la consulta
            pstmt.setString(1, nombre);
            pstmt.setString(2, contrasena);

            // Ejecutar la consulta y obtener el resultado
            ResultSet rs = pstmt.executeQuery();

            // Si hay un resultado, las credenciales son correctas
            if (rs.next()) {
                System.out.println("Inicio de sesión de discoteca exitoso");
                res.put("usuario",new Usuario(nombre,contrasena));
                res.put("encontrado",true);
                return res;
            } else {
                System.out.println("Nombre de discoteca o contraseña incorrectos");
                res.put("encontrado",false);
                return res;
            }

        } catch (SQLException e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
            res.put("encontrado",false);
            return res;
        }
    }
    public static HashMap<String,Object> insertDisco(HashMap<String,Object> datos){
        String SQL = "INSERT INTO eventos(nombre, contrasenya, edadminima, tipomusica, diasapertura, preciomedio) VALUES(?, ?, ?, ?, ?, ?)";
        HashMap<String,Object> res = new HashMap<>();
        boolean nombreExistente = comprobarNombre((String)datos.get("nombre"),true);
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            // Establecer los parámetros para la consulta
            pstmt.setString(1,(String) datos.get("nombre"));
            pstmt.setString(2, (String)datos.get("contrasena"));
            pstmt.setString(3,(String)datos.get("edadMinima"));
            pstmt.setString(4,(String)datos.get("tipoMusica"));
            pstmt.setString(5,(String)datos.get("diasApertura"));
            pstmt.setString(6,(String)datos.get("precioMedio"));

            if(nombreExistente){
                System.out.println("El nombre de usuario ya existe");
                res.put("registrado", false);
                return(res);
            }
            else{
                pstmt.executeUpdate();
                System.out.println("Discoteca insertado con éxito");
                res.put("registrado", true);
                return res;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            res.put("registrado", false);
            return res;
        }
    }
    // Método para buscar a un usuario y obtener toda su información
    public static HashMap<String, Object> findUser(String nombre) {
        String SQL = "SELECT * FROM usuarios WHERE nombre = ?";
        HashMap<String, Object> userInfo = new HashMap<>();

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            // Establecer el parámetro para la consulta
            pstmt.setString(1, nombre);

            // Ejecutar la consulta
            ResultSet rs = pstmt.executeQuery();

            // Si el usuario existe, llenamos el HashMap con su información
            if (rs.next()) {
                userInfo.put("nombre", rs.getString("nombre"));
                userInfo.put("contrasenya", rs.getString("contrasenya"));
                userInfo.put("puntos", rs.getInt("puntos"));
                System.out.println("Usuario encontrado: " + userInfo);
            } else {
                System.out.println("Usuario no encontrado.");
                userInfo.put("encontrado", false);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar usuario: " + e.getMessage());
            userInfo.put("error", e.getMessage());
        }

        return userInfo;
    }

    public static boolean comprobarNombre(String nombre, boolean disco){
        String SQLusuario = "SELECT * FROM usuarios WHERE nombre = ?";
        String SQLdisco = "SELECT * FROM eventos WHERE nombre = ?";
        String SQL;
        if(disco){
            SQL = SQLdisco;
        }
        else{
            SQL = SQLusuario;
        }
        boolean nombreExistente;
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            // Establecer los parámetros para la consulta
            pstmt.setString(1, nombre);

            // Ejecutar la consulta y obtener el resultado
            ResultSet rs = pstmt.executeQuery();

            // Si hay un resultado, las credenciales son correctas
            if (rs.next()) {
                System.out.println("Ya existe un usuario con dicho nombre");
                nombreExistente = true;
                return nombreExistente;
            } else {
                System.out.println("No existe ningun usuario con dicho nombre");
                nombreExistente = false;
                return nombreExistente;
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar nombre: " + e.getMessage());
            nombreExistente = false;
            return nombreExistente;
        }
    }
    }


