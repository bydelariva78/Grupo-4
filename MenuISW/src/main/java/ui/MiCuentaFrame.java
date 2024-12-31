package ui;

import modelo.Usuario;
import Client.Client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MiCuentaFrame extends JFrame {
    private Usuario user;
    private Integer puntos;
    private JFrame ventana;

    public MiCuentaFrame(Usuario user, JFrame ventana) {
        this.user = user;
        this.puntos = getPuntos();
        this.ventana = ventana;
        Color backgroundColor = new Color(20, 20, 20);
        Font fontTitulo = new Font("Segoe UI", Font.BOLD, 20);
        Font fontSubtitulo = new Font("Segoe UI", Font.BOLD, 16);
        Font fontTexto = new Font("Segoe UI", Font.PLAIN, 16);
        Font fontUsuario = new Font("Segoe UI", Font.BOLD, 14);

        // Configuración básica del JFrame
        setTitle("Mi Cuenta");
        setSize(800, 1000); // Tamaño del frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false); // Deshabilitar cambiar el tamaño de la ventana

        // Colores personalizados
        Color backgroundDark = new Color(30, 30, 30);  // Fondo oscuro
        Color accentColor = new Color(60, 120, 200);   // Color de acento
        Color textColor = Color.WHITE;                // Texto claro para títulos

        // Panel superior: PanelNorth
        PanelNorth panelNorth = new PanelNorth(this, user); // Usa tu clase PanelNorth
        add(panelNorth, BorderLayout.NORTH); // Agregar PanelNorth como cabecera

        // Panel de eventos favoritos
        JPanel FavoritosPanel = new JPanel();
        FavoritosPanel.setLayout(new BoxLayout(FavoritosPanel, BoxLayout.Y_AXIS));
        FavoritosPanel.setBackground(backgroundColor);

        JScrollPane FavoritosScrollPane = new JScrollPane(FavoritosPanel);
        FavoritosScrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Eventos favoritos", 0, 0, fontSubtitulo, Color.BLACK));
        ArrayList<String> favoritos = obtenerFavoritos(this.user);
        for (String favorito : favoritos) {
            JPanel favoritoPanel = new JPanel();
            favoritoPanel.setLayout(new BorderLayout());
            favoritoPanel.setBackground(new Color(30, 30, 30)); // Cambiar el fondo a negro
            favoritoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

            JLabel usuarioLabel = new JLabel(favorito);
            usuarioLabel.setFont(fontUsuario);
            usuarioLabel.setForeground(Color.WHITE); // Cambiar el texto a blanco para contraste

            favoritoPanel.add(usuarioLabel, BorderLayout.NORTH);

            FavoritosPanel.add(favoritoPanel);
            FavoritosPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        FavoritosPanel.revalidate();
        FavoritosPanel.repaint();



        // Panel de comentarios
        JPanel comentariosPanel = new JPanel();
        comentariosPanel.setLayout(new BoxLayout(comentariosPanel, BoxLayout.Y_AXIS));
        comentariosPanel.setBackground(backgroundColor);

        JScrollPane comentariosScrollPane = new JScrollPane(comentariosPanel);
        comentariosScrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Comentarios", 0, 0, fontSubtitulo, Color.BLACK));
        ArrayList<String[]> comentarios =obtenerComentariosUser(this.user);
        for (String[] comentario : comentarios) {
            JPanel comentarioPanel = new JPanel();
            comentarioPanel.setLayout(new BorderLayout());
            comentarioPanel.setBackground(new Color(30, 30, 30));
            comentarioPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

            JLabel usuarioLabel = new JLabel(comentario[0]);
            usuarioLabel.setFont(fontUsuario);
            usuarioLabel.setForeground(textColor);

            JLabel comentarioLabel = new JLabel(comentario[1]);
            comentarioLabel.setFont(fontTexto);
            comentarioLabel.setForeground(textColor);

            comentarioPanel.add(usuarioLabel, BorderLayout.NORTH);
            comentarioPanel.add(comentarioLabel, BorderLayout.CENTER);

            comentariosPanel.add(comentarioPanel);
            comentariosPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        //panel favoritos


        // Panel de puntos
        JPanel puntosPanel = new JPanel();
        puntosPanel.setLayout(new BorderLayout());
        puntosPanel.setBackground(accentColor);
        puntosPanel.setBorder(BorderFactory.createTitledBorder(null, "Puntos de Usuario",
                0, 0, new Font("Arial", Font.BOLD, 14), textColor)); // Letras en negro

        JLabel puntosLabel = new JLabel("Puntos: " + puntos, SwingConstants.CENTER);
        puntosLabel.setFont(new Font("Arial", Font.BOLD, 30));
        puntosLabel.setForeground(Color.WHITE);
        puntosPanel.add(puntosLabel, BorderLayout.CENTER);

        // Dividir eventos y puntos en la parte central
        JSplitPane centralSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, FavoritosScrollPane, comentariosScrollPane);
        centralSplitPane.setResizeWeight(0.5); // Balance entre paneles
        centralSplitPane.setBackground(backgroundDark);
        centralSplitPane.setDividerSize(5);

        // Agregar componentes al JFrame
        add(centralSplitPane, BorderLayout.CENTER); // Panel central
        add(puntosPanel, BorderLayout.SOUTH); // Panel inferior

        // Hacer visible el JFrame
        setVisible(true);
    }
    private ArrayList<String[]> obtenerComentariosUser(Usuario user){
        Client cliente = new Client();
        HashMap<String, Object> session = new HashMap<>();
        String context = "/obtenerComentariosUsuario";
        session.put("usuario", user.nombre);
        session = cliente.sentMessage(context, session);
        ArrayList<String[]> comentarios = (ArrayList<String[]>) session.get("comentarios");
        return comentarios;
    }
    private ArrayList<String> obtenerFavoritos(Usuario user){
        Client cliente = new Client();
        HashMap<String, Object> session = new HashMap<>();
        String context = "/obtenerFavoritos";
        session.put("usuario", user.nombre);
        session = cliente.sentMessage(context, session);
        ArrayList<String> favoritos = (ArrayList<String>) session.get("favoritos");
        return favoritos;
    }
    public Integer getPuntos() {
        Client cliente = new Client(); // Asegúrate de configurar host y puerto en Client
        HashMap<String, Object> session = new HashMap<>();
        String context = "/buscarUsuario";
        session.put("nombre", user.getNombre());
        session = cliente.sentMessage(context, session);
        int puntos = (int) session.get("puntos");
        return puntos;
    }

}
