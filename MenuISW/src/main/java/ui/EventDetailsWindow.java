package ui;

import Client.Client;
import modelo.Comentario;
import modelo.Eventos;
import modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class EventDetailsWindow extends JPanel {

    private JTextArea commentArea;

    private Usuario user;
    private Eventos event;

    private String eventName;
    private String description;
    private String edad;
    private String precio;
    private String musica;
    private String dia;

    private DefaultListModel<String> commentListModel;



    private static final String texto_null="El creador de este evento todavía no ha introducido una descripción del mismo. ¡Pronto encontrarás la descripción del evento!";

    public EventDetailsWindow(Eventos evento, Usuario user) {
        this.user=user;
        this.event=evento;
        this.eventName=evento.getNombre();
        this.description=evento.getDescripcion();
        this.dia= evento.getDiasApertura();
        this.edad=evento.getEdadMinima();
        this.precio=evento.getEdadMinima();
        this.musica=evento.getTipoMusica();
        init();
        actualizarCommentListModel();
    }

    private void init() {
        setSize(1100, 900);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(eventName, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(50, 50, 50));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setPreferredSize(new Dimension(1100, 70));
        add(titleLabel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1, 10, 10));
        mainPanel.setBackground(new Color(40, 40, 40));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.setBackground(new Color(40, 40, 40));
        JLabel descriptionLabel = new JLabel("Descripción:", SwingConstants.LEFT);
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        descriptionLabel.setForeground(Color.WHITE);
        JTextArea descriptionArea;
        if (description==null)
        {
            descriptionLabel.setForeground(Color.RED);
            descriptionArea = new JTextArea(texto_null);
        }else {
            descriptionLabel.setForeground(Color.RED);
            descriptionArea = new JTextArea(description);
        }
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setBackground(new Color(60, 60, 60));
        descriptionArea.setForeground(Color.WHITE);
        descriptionArea.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));
        descriptionArea.setPreferredSize(new Dimension(800, 50));

        descriptionPanel.add(descriptionLabel, BorderLayout.NORTH);
        descriptionPanel.add(new JScrollPane(descriptionArea), BorderLayout.CENTER);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(4, 2, 10, 10));
        detailsPanel.setBackground(new Color(40, 40, 40));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        detailsPanel.add(createDetailLabel("Precio:"));
        detailsPanel.add(createDetailValue(precio));
        detailsPanel.add(createDetailLabel("Edad mínima:"));
        detailsPanel.add(createDetailValue(edad));
        detailsPanel.add(createDetailLabel("Días de apertura:"));
        detailsPanel.add(createDetailValue(dia));
        detailsPanel.add(createDetailLabel("Tipo de música:"));
        detailsPanel.add(createDetailValue(musica));

        mainPanel.add(descriptionPanel);
        mainPanel.add(detailsPanel);
        add(mainPanel, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(4, 1, 10, 10));
        actionPanel.setBackground(new Color(30, 30, 30));
        actionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JCheckBox favoriteCheckbox = new JCheckBox("Marcar como Favorito");
        favoriteCheckbox.setFont(new Font("Arial", Font.BOLD, 16));
        favoriteCheckbox.setBackground(new Color(70, 130, 180));
        favoriteCheckbox.setForeground(Color.WHITE);
        favoriteCheckbox.setFocusPainted(false);
        favoriteCheckbox.setHorizontalAlignment(SwingConstants.CENTER);

        JButton attendButton = new JButton("Voy a asistir");
        attendButton.setBackground(new Color(34, 139, 34));
        attendButton.setForeground(Color.WHITE);
        attendButton.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel commentLabel = new JLabel("Tu comentario:", SwingConstants.LEFT);
        commentLabel.setFont(new Font("Arial", Font.BOLD, 18));
        commentLabel.setForeground(Color.WHITE);

        JPanel commentPanel = new JPanel(new BorderLayout());
        commentPanel.setBackground(new Color(30, 30, 30));
        commentArea = new JTextArea();
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);
        commentArea.setBackground(new Color(60, 60, 60));
        commentArea.setForeground(Color.WHITE);
        commentArea.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));

        JButton uploadCommentButton = new JButton("Subir comentario");
        uploadCommentButton.setBackground(new Color(70, 130, 180));
        uploadCommentButton.setForeground(Color.WHITE);
        uploadCommentButton.setFont(new Font("Arial", Font.BOLD, 14));

        commentPanel.add(new JScrollPane(commentArea), BorderLayout.CENTER);
        commentPanel.add(uploadCommentButton, BorderLayout.SOUTH);

        JPanel commentsBlockPanel = new JPanel(new BorderLayout());
        commentsBlockPanel.setBackground(new Color(30, 30, 30));
        JLabel commentsLabel = new JLabel("Comentarios de otros usuarios:", SwingConstants.LEFT);
        commentsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        commentsLabel.setForeground(Color.WHITE);

        commentListModel = new DefaultListModel<>();
        JList<String> commentList = new JList<>(commentListModel);
        commentList.setBackground(new Color(60, 60, 60));
        commentList.setForeground(Color.WHITE);
        commentList.setFont(new Font("Arial", Font.PLAIN, 14));
        commentList.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));

        commentsBlockPanel.add(commentsLabel, BorderLayout.NORTH);
        commentsBlockPanel.add(new JScrollPane(commentList), BorderLayout.CENTER);

        uploadCommentButton.addActionListener(e -> {
            String newComment = commentArea.getText().trim();
            if (!newComment.isEmpty()) {
                guardarComent();
                user.setPuntos(user.getPuntos()+5);
                commentArea.setText("");
                actualizarCommentListModel();
            }
        });

        actionPanel.add(favoriteCheckbox);
        actionPanel.add(attendButton);
        actionPanel.add(commentLabel);
        actionPanel.add(commentPanel);

        add(actionPanel, BorderLayout.SOUTH);
        add(commentsBlockPanel, BorderLayout.EAST);
    }

    private JLabel createDetailLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        return label;
    }

    private JLabel createDetailValue(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setForeground(Color.LIGHT_GRAY);
        return label;
    }

    public void guardarComent() {
        Client cliente = new Client();
        HashMap<String, Object> session = new HashMap<>();
        String context = "/guardarComentario";
        Comentario comentario= new Comentario(commentArea.getText(),user, event);
        session.put("comentario", comentario);
        session=cliente.sentMessage(context,session);

    }

    public ArrayList<String> getComent() {
        Client cliente = new Client();
        HashMap<String, Object> session = new HashMap<>();
        String context = "/getComentario";
        session.put("evento", eventName);
        session = cliente.sentMessage(context, session);

        ArrayList<String> comentarios = new ArrayList<>();
        if (session.containsKey("comentarios")) {
            Object comentariosObj = session.get("comentarios");
            if (comentariosObj instanceof ArrayList) {
                comentarios = (ArrayList<String>) comentariosObj;
            }
        }

        // Verifica si la lista está vacía
        if (comentarios.isEmpty()) {
            System.out.println("No hay comentarios para este evento.");
        }

        return comentarios;
    }


    public void actualizarCommentListModel() {
        ArrayList<String> comentarios = getComent();
        commentListModel.clear();

        if (comentarios.isEmpty()) {
            commentListModel.addElement("No hay comentarios disponibles para este evento.");
        } else {
            for (String comentario : comentarios) {
                commentListModel.addElement(comentario);
            }
        }
    }


    public static void main(String[] args) {
    }
}
