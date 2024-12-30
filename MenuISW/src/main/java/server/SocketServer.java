package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import Properties.PropertiesISW;
import controlador.UsuarioController;
import modelo.Eventos;
import modelo.Usuario;
import message.Message;

public class SocketServer extends Thread {
    public static int port = Integer.parseInt(PropertiesISW.getInstance().getProperty("port"));

    protected Socket socket;

    private SocketServer(Socket socket) {
        this.socket = socket;
        //Configure connections
        System.out.println("New client connected from " + socket.getInetAddress().getHostAddress());
        start();
    }

    public void run() {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = socket.getInputStream();
            out = socket.getOutputStream();

            //first read the object that has been sent
            ObjectInputStream objectInputStream = new ObjectInputStream(in);
            Message mensajeIn= (Message)objectInputStream.readObject();
            //Object to return informations
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            Message mensajeOut=new Message();
            HashMap<String,Object> session=mensajeIn.getSession();
            UsuarioController customerControler;
            String nombre;
            String contrasena;
            HashMap<String, Object> res;
            switch (mensajeIn.getContext()) {
                case "/inicioSesion":
                    nombre = (String) session.get("nombre");
                    contrasena = (String) session.get("contrasena");
                    customerControler=new UsuarioController();
                    res =customerControler.inicioSesion(nombre,contrasena);
                    mensajeOut.setContext("/inicioSesion");
                    session = res;
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                    break;
                case "/buscarUsuario":
                    nombre = (String) session.get("nombre");
                    customerControler=new UsuarioController();
                    res =customerControler.buscarUsuario(nombre);
                    mensajeOut.setContext("/buscarUsuario");
                    session = res;
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                case "/inicioSesionDiscoteca":
                    nombre = (String) session.get("nombre");
                    contrasena = (String) session.get("contrasena");
                    customerControler = new UsuarioController();
                    res = customerControler.inicioSesionDiscoteca(nombre,contrasena);
                    mensajeOut.setContext("/inicioSesionDiscoteca");
                    session = res;
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                    break;
                case "/registrar":
                    nombre = (String) session.get("nombre");
                    contrasena = (String) session.get("contrasena");
                    customerControler = new UsuarioController();
                    res = customerControler.registrar(nombre,contrasena);
                    session = res;
                    mensajeOut.setContext("/registrado");
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                    break;
                case "/registrarDiscoteca":
                    customerControler = new UsuarioController();
                    res = customerControler.registrarDiscoteca(session);
                    session = res;
                    System.out.println("session en socketserver"+ session);
                    mensajeOut.setContext("discotecaRegistrada");
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                    break;
                case "/obtenerEventos":
                    customerControler = new UsuarioController();
                    res = customerControler.obtenerEventos();
                    session=res;
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                    break;
                case "/modificarEvento":
                    Eventos evento= (Eventos) session.get("evento");
                    customerControler=new UsuarioController();
                    res =customerControler.modificarEvento(evento);
                    session=res;
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                    break;
                default:
                    System.out.println("\nParámetro no encontrado");
                    break;
            }
        } catch (IOException ex) {
            System.out.println("Unable to get streams from client");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("SocketServer Example - Listening port "+port);
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            while (true) {
                /**
                 * create a new {@link SocketServer} object for each connection
                 * this will allow multiple client connections
                 */
                new SocketServer(server.accept());
            }
        } catch (IOException ex) {
            System.out.println("Unable to start server.");
        } finally {
            try {
                if (server != null)
                    server.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
