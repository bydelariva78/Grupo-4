package ui;

import modelo.Usuario;
import Client.Client;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class MiCuentaFrame extends JFrame {
    private Usuario user;
    private Integer puntos;
    private JFrame ventana;

    public MiCuentaFrame(Usuario user, JFrame ventana) {
        this.user = user;
        this.puntos = getPuntos();
        this.ventana = ventana;

        // Configuración básica del JFrame
        setTitle("Mi Cuenta");
        setSize(800, 1000); // Tamaño del frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false); // Deshabilitar cambiar el tamaño de la ventana

        // Colores personalizados
        Color backgroundDark = new Color(30, 30, 30);  // Fondo oscuro
        Color accentColor = new Color(60, 120, 200);   // Color de acento
        Color textColor = Color.BLACK;                // Texto claro para títulos

        // Panel superior: PanelNorth
        PanelNorth panelNorth = new PanelNorth(this, user); // Usa tu clase PanelNorth
        add(panelNorth, BorderLayout.NORTH); // Agregar PanelNorth como cabecera

        // Panel de eventos visitados
        JPanel eventosVisitadosPanel = new JPanel();
        eventosVisitadosPanel.setLayout(new BoxLayout(eventosVisitadosPanel, BoxLayout.Y_AXIS));
        eventosVisitadosPanel.setBackground(backgroundDark);

        JScrollPane scrollPaneVisitados = new JScrollPane(eventosVisitadosPanel);
        scrollPaneVisitados.setBorder(BorderFactory.createTitledBorder(null, "Eventos Visitados",
                0, 0, new Font("Arial", Font.BOLD, 14), textColor)); // Letras en negro
        scrollPaneVisitados.getViewport().setBackground(backgroundDark);

        // Panel de eventos asistidos
        JPanel eventosAsistidosPanel = new JPanel();
        eventosAsistidosPanel.setLayout(new BoxLayout(eventosAsistidosPanel, BoxLayout.Y_AXIS));
        eventosAsistidosPanel.setBackground(backgroundDark);

        JScrollPane scrollPaneAsistidos = new JScrollPane(eventosAsistidosPanel);
        scrollPaneAsistidos.setBorder(BorderFactory.createTitledBorder(null, "Eventos Asistidos",
                0, 0, new Font("Arial", Font.BOLD, 14), textColor)); // Letras en negro
        scrollPaneAsistidos.getViewport().setBackground(backgroundDark);

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
        JSplitPane centralSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPaneVisitados, scrollPaneAsistidos);
        centralSplitPane.setResizeWeight(0.5); // Balance entre paneles
        centralSplitPane.setBackground(backgroundDark);
        centralSplitPane.setDividerSize(5);

        // Agregar componentes al JFrame
        add(centralSplitPane, BorderLayout.CENTER); // Panel central
        add(puntosPanel, BorderLayout.SOUTH); // Panel inferior

        // Hacer visible el JFrame
        setVisible(true);
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
