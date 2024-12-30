package ui;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import Client.Client;
import modelo.Eventos;

public class Menu_Evento extends JFrame {

    private Eventos evento;
    private JTextField descripcionField;
    private JTextField tipoMusicaField;
    private JTextField edadMinimaField;
    private JTextField precioMedioField;

    public Menu_Evento(Eventos evento) {
        this.evento=evento;
        setTitle("Detalles del Evento: " + evento.nombre);
        setSize(1100, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        init(evento.nombre, evento.descripcion, evento.tipoMusica, evento.edadMinima, evento.precioMedio);
        setVisible(true);
    }

    public Menu_Evento() {}

    private void init(String nombreEvento, String descripcion, String tipoMusica, String edadMinima, String precioMedio) {
        Color backgroundColor = new Color(20, 20, 20);
        Color textColor = Color.WHITE;
        Font fontTitulo = new Font("Segoe UI", Font.BOLD, 20);
        Font fontSubtitulo = new Font("Segoe UI", Font.BOLD, 16);
        Font fontTexto = new Font("Segoe UI", Font.PLAIN, 16);
        Font fontUsuario = new Font("Segoe UI", Font.BOLD, 14);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Panel de información editable
        JPanel infoEditablePanel = new JPanel();
        infoEditablePanel.setBackground(backgroundColor);
        infoEditablePanel.setLayout(new GridLayout(4, 2, 15, 15)); // Espaciado entre filas
        infoEditablePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel descripcionLabel = new JLabel("Descripción:");
        descripcionLabel.setFont(fontTexto);
        descripcionLabel.setForeground(textColor);
        descripcionField = new JTextField(descripcion);
        descripcionField.setFont(fontTexto);
        descripcionField.setForeground(textColor);
        descripcionField.setBackground(new Color(50, 50, 50));
        descripcionField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        descripcionField.setPreferredSize(new Dimension(400, 40)); // Más alto

        JLabel tipoMusicaLabel = new JLabel("Tipo de Música:");
        tipoMusicaLabel.setFont(fontTexto);
        tipoMusicaLabel.setForeground(textColor);
        tipoMusicaField = new JTextField(tipoMusica);
        tipoMusicaField.setFont(fontTexto);
        tipoMusicaField.setForeground(textColor);
        tipoMusicaField.setBackground(new Color(50, 50, 50));
        tipoMusicaField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        tipoMusicaField.setPreferredSize(new Dimension(400, 40)); // Más alto

        JLabel edadMinimaLabel = new JLabel("Edad Mínima:");
        edadMinimaLabel.setFont(fontTexto);
        edadMinimaLabel.setForeground(textColor);
        edadMinimaField = new JTextField(String.valueOf(edadMinima));
        edadMinimaField.setFont(fontTexto);
        edadMinimaField.setForeground(textColor);
        edadMinimaField.setBackground(new Color(50, 50, 50));
        edadMinimaField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        edadMinimaField.setPreferredSize(new Dimension(400, 40)); // Más alto

        JLabel precioMedioLabel = new JLabel("Precio Medio:");
        precioMedioLabel.setFont(fontTexto);
        precioMedioLabel.setForeground(textColor);
        precioMedioField = new JTextField(String.valueOf(precioMedio));
        precioMedioField.setFont(fontTexto);
        precioMedioField.setForeground(textColor);
        precioMedioField.setBackground(new Color(50, 50, 50));
        precioMedioField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        precioMedioField.setPreferredSize(new Dimension(400, 40)); // Más alto

        infoEditablePanel.add(descripcionLabel);
        infoEditablePanel.add(descripcionField);
        infoEditablePanel.add(tipoMusicaLabel);
        infoEditablePanel.add(tipoMusicaField);
        infoEditablePanel.add(edadMinimaLabel);
        infoEditablePanel.add(edadMinimaField);
        infoEditablePanel.add(precioMedioLabel);
        infoEditablePanel.add(precioMedioField);

        // Panel central: Comentarios y Valoraciones
        JPanel comentariosValoracionesPanel = new JPanel();
        comentariosValoracionesPanel.setLayout(new GridLayout(1, 2, 10, 0));
        comentariosValoracionesPanel.setBackground(backgroundColor);
        comentariosValoracionesPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel comentariosPanel = new JPanel();
        comentariosPanel.setLayout(new BoxLayout(comentariosPanel, BoxLayout.Y_AXIS));
        comentariosPanel.setBackground(backgroundColor);

        JScrollPane comentariosScrollPane = new JScrollPane(comentariosPanel);
        comentariosScrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Comentarios", 0, 0, fontSubtitulo, textColor));

        JPanel asistentesPanel = new JPanel();
        asistentesPanel.setLayout(new BoxLayout(asistentesPanel, BoxLayout.Y_AXIS));
        asistentesPanel.setBackground(backgroundColor);

        JScrollPane asistentesScrollPane = new JScrollPane(asistentesPanel);
        asistentesScrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Asitentes", 0, 0, fontSubtitulo, textColor));

        comentariosValoracionesPanel.add(comentariosScrollPane);
        comentariosValoracionesPanel.add(asistentesScrollPane);

        ArrayList<String[]> comentarios =obtenerComentarios(this.evento);
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

        ArrayList<String> asistentes = obtenerAsistentes(this.evento);
        for (String asistente : asistentes) {
            JPanel asistentePanel = new JPanel();
            asistentePanel.setLayout(new BorderLayout());
            asistentePanel.setBackground(new Color(30, 30, 30));
            asistentePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

            JLabel usuarioLabel = new JLabel(asistente);
            usuarioLabel.setFont(fontUsuario);
            usuarioLabel.setForeground(textColor);



            asistentePanel.add(usuarioLabel, BorderLayout.NORTH);

            asistentesPanel.add(asistentePanel);
            asistentesPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        // Botón de confirmación
        JPanel confirmButtonPanel = new JPanel();
        confirmButtonPanel.setBackground(backgroundColor);
        JButton confirmarButton = new JButton("Confirmar Cambios");
        confirmarButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        confirmarButton.setForeground(textColor);
        confirmarButton.setBackground(new Color(70, 130, 180));
        confirmarButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        confirmarButton.setPreferredSize(new Dimension(300, 50));
        confirmarButton.addActionListener(e -> {
            evento.descripcion = descripcionField.getText();
            evento.tipoMusica = tipoMusicaField.getText();
            evento.edadMinima = edadMinimaField.getText();
            evento.precioMedio = precioMedioField.getText();
            Client cliente = new Client();
            HashMap<String, Object> session = new HashMap<>();
            String context = "/modificarEvento";
            session.put("evento", evento);
            session = cliente.sentMessage(context, session);
            if (session.get("modificado").equals(true)){
                System.out.println("modificado");
            }
            else{ System.out.println("no");}
        });
        confirmButtonPanel.add(confirmarButton);

        // Ajustar panel principal
        mainPanel.add(infoEditablePanel, BorderLayout.NORTH);
        mainPanel.add(comentariosValoracionesPanel, BorderLayout.CENTER);
        mainPanel.add(confirmButtonPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
    }
    private  ArrayList<String[]> obtenerComentarios(Eventos evento){
        Client cliente = new Client();
        HashMap<String, Object> session = new HashMap<>();
        String context = "/obtenerComentariosEvento";
        session.put("evento", evento);
        session = cliente.sentMessage(context, session);
        ArrayList<String[]> comentarios = (ArrayList<String[]>) session.get("comentarios");
        return comentarios;
    }
    private ArrayList<String> obtenerAsistentes(Eventos evento){
        Client cliente = new Client();
        HashMap<String, Object> session = new HashMap<>();
        String context = "/obtenerAsistentesEvento";
        session.put("evento", evento);
        session = cliente.sentMessage(context, session);
        ArrayList<String> asistentes = (ArrayList<String>) session.get("asistentes");
        return asistentes;
    }
    public static void main(String[] args) {
        new Menu_Evento(new Eventos("Evento Prueba", "Descripción de prueba", "Rock", "18", "50"));
    }
}
