package controlador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import database.DatabaseOperations;
import modelo.Usuario;

import javax.xml.crypto.Data;

public class UsuarioController {



    public HashMap<String,Object> inicioSesion(String nombre, String contrasena){
        HashMap<String,Object> res = DatabaseOperations.loginUser(nombre,contrasena);
        return res;
    }
    public HashMap<String,Object> registrar(String nombre, String contrasena) throws SQLException {
        HashMap<String, Object> registrado = DatabaseOperations.insertUser(nombre,contrasena);

        return(registrado);
    }
    public HashMap<String,Object> inicioSesionDiscoteca(String nombre, String contrasena){
        HashMap<String,Object> res = DatabaseOperations.loginDisco(nombre,contrasena);
        return (res);
    }
    public HashMap<String,Object> registrarDiscoteca(HashMap<String,Object> datos){
        HashMap<String,Object> res = DatabaseOperations.insertDisco(datos);
        return(res);
    }
    public HashMap<String,Object> buscarUsuario(String nombre){
        HashMap<String,Object> res = DatabaseOperations.findUser(nombre);
        return(res);
    }

    public HashMap<String,Object> obtenerEventos(){
        HashMap<String,Object> res = DatabaseOperations.obtainEvents();
        return (res);
    }

    public HashMap<String,Object> guardarComentario(String evento, String comentario){
        HashMap<String,Object> res = DatabaseOperations.saveComment(evento, comentario);
        return (res);
    }

    public HashMap<String,Object> getComentarios(String eventoNombre){
        HashMap<String,Object> res = DatabaseOperations.getComments(eventoNombre);
        return (res);
    }



}