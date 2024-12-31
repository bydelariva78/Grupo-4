package controlador;

import java.sql.SQLException;
import java.util.HashMap;
import database.DatabaseOperations;
import modelo.Comentario;
import modelo.Eventos;
import modelo.Usuario;

public class UsuarioController {

    public HashMap<String,Object> checkFavorito(String nombre, String evento)
    {
        HashMap<String,Object> res= DatabaseOperations.checkFav(nombre,evento);
        return  res;

    }

    public HashMap<String,Object> addPuntos(String nombre, Integer puntos)
    {
        HashMap<String, Object> res = DatabaseOperations.addPoints(nombre,puntos);
        return res;
    }

    public HashMap<String,Object> Asistir(Boolean make, String nombre, String evento)
    {
        HashMap<String,Object> res= DatabaseOperations.insertAsistente(make,nombre,evento);
        return  res;
    }

    public HashMap<String,Object> checkAsistencia( String nombre, String evento)
    {
        HashMap<String,Object> res= DatabaseOperations.checkAssist(nombre,evento);
        return  res;

    }


    public HashMap<String,Object> makeFavorito(Boolean make, String nombre, String evento)
    {
        HashMap<String,Object> res= DatabaseOperations.makeFav(make,nombre,evento);
        return  res;

    }
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

    public HashMap<String,Object> guardarComentario(Comentario comentario){
        HashMap<String,Object> res = DatabaseOperations.saveComment(comentario);
        if(comentario.getEvento()!=null)
            {
                System.out.println("FUNCIONA");
            }
        return (res);
    }

    public HashMap<String,Object> getComentarios(String eventoNombre){
        HashMap<String,Object> res = DatabaseOperations.getComments(eventoNombre);
        return (res);
    }
    public HashMap<String,Object> modificarEvento(Eventos evento) {
        HashMap<String, Object> res = DatabaseOperations.modEvent(evento);
        return (res);
    }
    public HashMap<String,Object> obtenerComentariosEvento(Eventos evento) {
        HashMap<String, Object> res = DatabaseOperations.obtainComentsEvent(evento);
        return (res);
    }
    public HashMap<String,Object> obtenerAsistentesEvento(Eventos evento) {
        HashMap<String, Object> res = DatabaseOperations.obtainAsistantsEvent(evento);
        return (res);
    }
    public HashMap<String,Object> obtenerComentariosUsuario(String user) {
        HashMap<String, Object> res = DatabaseOperations.obtainComentsUser(user);
        return (res);
    }
    public HashMap<String,Object> obtenerFavoritos(String user) {
        HashMap<String, Object> res = DatabaseOperations.obtainFavs(user);
        return (res);
    }
}