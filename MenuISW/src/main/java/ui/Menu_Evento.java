package ui;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Menu_Evento extends JFrame {

    private JTextField descripcionField;
    private JTextField tipoMusicaField;
    private JTextField edadMinimaField;
    private JTextField precioMedioField;

    public Menu_Evento(String nombreEvento, String descripcion, String tipoMusica, int edadMinima, double precioMedio) {
        setTitle("Detalles del Evento: " + nombreEvento);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        init(nombreEvento, descripcion, tipoMusica, edadMinima, precioMedio);
        setVisible(true);
    }

    private void init(String nombreEvento, String descripcion, String tipoMusica, int edadMinima, double precioMedio) {
        Color backgroundColor = new Color(20, 20, 20);
        Color textColor = Color.WHITE;
        Font fontTitulo = new Font("Segoe UI", Font.BOLD, 20);
        Font fontSubtitulo = new Font("Segoe UI", Font.BOLD, 16);
        Font fontTexto = new Font("Segoe UI", Font.PLAIN, 14);
        Font fontUsuario = new Font("Segoe UI", Font.BOLD, 14);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));

        JPanel infoEditablePanel = new JPanel();
        infoEditablePanel.setBackground(backgroundColor);
        infoEditablePanel.setLayout(new GridLayout(4, 2, 10, 10));
        infoEditablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel descripcionLabel = new JLabel("Descripción:");
        descripcionLabel.setFont(fontTexto);
        descripcionLabel.setForeground(textColor);
        descripcionField = new JTextField(descripcion);
        descripcionField.setFont(fontTexto);
        descripcionField.setForeground(textColor);
        descripcionField.setBackground(new Color(50, 50, 50));
        descripcionField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        JLabel tipoMusicaLabel = new JLabel("Tipo de Música:");
        tipoMusicaLabel.setFont(fontTexto);
        tipoMusicaLabel.setForeground(textColor);
        tipoMusicaField = new JTextField(tipoMusica);
        tipoMusicaField.setFont(fontTexto);
        tipoMusicaField.setForeground(textColor);
        tipoMusicaField.setBackground(new Color(50, 50, 50));
        tipoMusicaField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        JLabel edadMinimaLabel = new JLabel("Edad Mínima:");
        edadMinimaLabel.setFont(fontTexto);
        edadMinimaLabel.setForeground(textColor);
        edadMinimaField = new JTextField(String.valueOf(edadMinima));
        edadMinimaField.setFont(fontTexto);
        edadMinimaField.setForeground(textColor);
        edadMinimaField.setBackground(new Color(50, 50, 50));
        edadMinimaField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        JLabel precioMedioLabel = new JLabel("Precio Medio:");
        precioMedioLabel.setFont(fontTexto);
        precioMedioLabel.setForeground(textColor);
        precioMedioField = new JTextField(String.valueOf(precioMedio));
        precioMedioField.setFont(fontTexto);
        precioMedioField.setForeground(textColor);
        precioMedioField.setBackground(new Color(50, 50, 50));
        precioMedioField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        infoEditablePanel.add(descripcionLabel);
        infoEditablePanel.add(descripcionField);
        infoEditablePanel.add(tipoMusicaLabel);
        infoEditablePanel.add(tipoMusicaField);
        infoEditablePanel.add(edadMinimaLabel);
        infoEditablePanel.add(edadMinimaField);
        infoEditablePanel.add(precioMedioLabel);
        infoEditablePanel.add(precioMedioField);

        JPanel comentariosValoracionesPanel = new JPanel();
        comentariosValoracionesPanel.setLayout(new GridLayout(1, 2, 10, 0));
        comentariosValoracionesPanel.setBackground(backgroundColor);
        comentariosValoracionesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel comentariosPanel = new JPanel();
        comentariosPanel.setLayout(new BoxLayout(comentariosPanel, BoxLayout.Y_AXIS));
        comentariosPanel.setBackground(backgroundColor);

        JScrollPane comentariosScrollPane = new JScrollPane(comentariosPanel);
        comentariosScrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Comentarios", 0, 0, fontSubtitulo, textColor));

        JPanel valoracionesPanel = new JPanel();
        valoracionesPanel.setLayout(new BoxLayout(valoracionesPanel, BoxLayout.Y_AXIS));
        valoracionesPanel.setBackground(backgroundColor);

        JScrollPane valoracionesScrollPane = new JScrollPane(valoracionesPanel);
        valoracionesScrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Valoraciones", 0, 0, fontSubtitulo, textColor));

        ArrayList<String[]> comentarios = new ArrayList<>();
        comentarios.add(new String[]{"Usuario1", "Comentario de ejemplo 1"});
        comentarios.add(new String[]{"Usuario2", "Comentario de ejemplo 2"});
        comentarios.add(new String[]{"Usuario3", "Comentario de ejemplo 3"});

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

        ArrayList<String[]> valoraciones = new ArrayList<>();
        valoraciones.add(new String[]{"Usuario1", "Valoración de ejemplo 1"});
        valoraciones.add(new String[]{"Usuario2", "Valoración de ejemplo 2"});
        valoraciones.add(new String[]{"Usuario3", "Valoración de ejemplo 3"});

        for (String[] valoracion : valoraciones) {
            JPanel valoracionPanel = new JPanel();
            valoracionPanel.setLayout(new BorderLayout());
            valoracionPanel.setBackground(new Color(30, 30, 30));
            valoracionPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

            JLabel usuarioLabel = new JLabel(valoracion[0]);
            usuarioLabel.setFont(fontUsuario);
            usuarioLabel.setForeground(textColor);

            JLabel valoracionLabel = new JLabel(valoracion[1]);
            valoracionLabel.setFont(fontTexto);
            valoracionLabel.setForeground(textColor);

            valoracionPanel.add(usuarioLabel, BorderLayout.NORTH);
            valoracionPanel.add(valoracionLabel, BorderLayout.CENTER);

            valoracionesPanel.add(valoracionPanel);
            valoracionesPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        comentariosValoracionesPanel.add(comentariosScrollPane);
        comentariosValoracionesPanel.add(valoracionesScrollPane);

        mainPanel.add(infoEditablePanel);
        mainPanel.add(comentariosValoracionesPanel);

        add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Menu_Evento(
                "Fiesta Electrónica",
                "Una noche inolvidable con música electrónica.",
                "Electrónica",
                18,
                25.50
        ));
    }
}
