package ui;

import Client.Client;
import modelo.Comentario;
import modelo.Eventos;
import modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class EventDetailsWindow extends JPanel {

    private JTextArea commentArea;
    private JButton favButton;
    private JButton assistButton;

    private Usuario user;
    private Eventos event;

    public String eventName;
    private String description;
    private String edad;
    private String precio;
    private String musica;
    private String dia;


    private final String fav= "Quitar de favoritos";
    private final String notfav= "Hacer favorito";
    private final String asist="Asistir al evento";
    private final String no_asist="Cancelar asistencia";




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
        System.out.println(evento.getNombre());
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


        favButton= favButton();
        favButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (favButton.getLabel()==fav)
                {
                    makeFavorito(true);
                    favButton.setLabel(notfav);
                    addPuntos(-100);
                } else if (favButton.getLabel()==notfav){
                    makeFavorito(false);
                    favButton.setLabel(fav);
                    addPuntos(100);
                }
            }
        });
        favButton.setFont(new Font("Arial", Font.BOLD, 16));
        favButton.setBackground(new Color(70, 130, 180));
        favButton.setForeground(Color.WHITE);
        favButton.setFocusPainted(false);
        favButton.setHorizontalAlignment(SwingConstants.CENTER);

        JButton assistButton = getAssistButton();
        assistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (assistButton.getLabel()==asist)
                {
                    Asistir(false);
                    assistButton.setLabel(no_asist);
                    addPuntos(500);
                }else {
                    Asistir(true);
                    assistButton.setLabel(asist);
                    addPuntos(-500);
                }
            }
        });
        assistButton.setBackground(new Color(34, 139, 34));
        assistButton.setForeground(Color.WHITE);
        assistButton.setFont(new Font("Arial", Font.BOLD, 16));

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

        actionPanel.add(favButton);
        actionPanel.add(assistButton);
        actionPanel.add(commentLabel);
        actionPanel.add(commentPanel);

        add(actionPanel, BorderLayout.SOUTH);
        add(commentsBlockPanel, BorderLayout.EAST);
    }

    private JButton favButton()
    {
        JButton button;
        if (checkFavorito()){
            button=new JButton();
            button.setLabel(fav);
        }
        else{
            button=new JButton();
            button.setLabel(notfav);
        }
        return button;
    }


    private JButton getAssistButton()
    {
        JButton button;
        if (checkAssist()){
            button=new JButton();
            button.setLabel(no_asist);
        }
        else{
            button=new JButton();
            button.setLabel(asist);
        }
        return button;
    }

    public void addPuntos(Integer points)
    {
        Client cliente = new Client();
        HashMap<String,Object> session = new HashMap<>();
        String context="/addPuntos";
        session.put("usuario",user.getNombre());
        session.put("puntos",points);
        try{
            session = cliente.sentMessage(context, session);
            if (session.containsKey("error")) {
                System.out.println("Error al actualizar puntos: " + session.get("error"));
            } else {
                System.out.println("Éxito");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar puntos: " + e.getMessage());
        }

    }

    public boolean checkAssist()
    {
        Client cliente = new Client();
        HashMap<String, Object> session = new HashMap<>();
        String context = "/checkAssist";
        session.put("evento",event.getNombre());
        session.put("usuario",user.getNombre());
        try {
            session = cliente.sentMessage(context, session);
            if (session.containsKey("error")) {
                System.out.println("Error al checkear asistencia: " + session.get("error"));
            } else {
                System.out.println("Éxito");
            }
        } catch (Exception e) {
            System.out.println("Error al cehckear asistencia: " + e.getMessage());
        }

        Boolean res=(Boolean) session.get("result");
        return res;
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

        // Validar datos antes de enviar
        if (commentArea.getText() == null || commentArea.getText().isEmpty()) {
            System.out.println("El comentario no puede estar vacío.");
            return;
        }
        if (user == null || user.getNombre() == null || event == null || event.getNombre() == null) {
            System.out.println("Datos del usuario o evento no válidos.");
            return;
        }

        // Crear el objeto Comentario
        Comentario comentario = new Comentario(commentArea.getText(), user.getNombre(), event.getNombre());
        System.out.println(comentario.toString());

        session.put("comentario", comentario);

        // Enviar datos al servidor y manejar la respuesta
        try {
            session = cliente.sentMessage(context, session);
            if (session.containsKey("error")) {
                System.out.println("Error al guardar el comentario: " + session.get("error"));
            } else {
                System.out.println("Comentario guardado con éxito.");
            }
        } catch (Exception e) {
            System.out.println("Error al guardar el comentario: " + e.getMessage());
        }

        System.out.println("guardando comment...");
    }

    public boolean checkFavorito()
    {
        Client cliente = new Client();
        HashMap<String, Object> session = new HashMap<>();
        String context = "/checkFavorito";
        session.put("evento",event.getNombre());
        System.out.println(event.getNombre());
        session.put("usuario",user.getNombre());
        try {
            session = cliente.sentMessage(context, session);
            if (session.containsKey("error")) {
                System.out.println("Error al checkear fav: " + session.get("error"));
            } else {
                System.out.println("Éxito");
            }
        } catch (Exception e) {
            System.out.println("Error al cehckear fav: " + e.getMessage());
        }

        Boolean res=(Boolean) session.get("result");
        System.out.println(res);
        return res;
    }

    public void makeFavorito(Boolean b)
    {
        Client cliente = new Client();
        HashMap<String, Object> session = new HashMap<>();
        String context = "/makeFavorito";
        session.put("make",b);
        session.put("evento",event.getNombre());
        session.put("usuario",user.getNombre());
        try {
            session = cliente.sentMessage(context, session);
            if (session.containsKey("error")) {
                System.out.println("Error al amrcar fav: " + session.get("error"));
            } else {
                System.out.println("Éxito");
            }
        } catch (Exception e) {
            System.out.println("Error al marcar fav: " + e.getMessage());
        }

    }

    public void Asistir(Boolean b)
    {
        Client cliente = new Client();
        HashMap<String, Object> session = new HashMap<>();
        String context = "/Asistir";
        session.put("make",b);
        session.put("evento",event.getNombre());
        session.put("usuario",user.getNombre());
        try {
            session = cliente.sentMessage(context, session);
            if (session.containsKey("error")) {
                System.out.println("Error al declarar asistencia: " + session.get("error"));
            } else {
                System.out.println("Éxito");
            }
        } catch (Exception e) {
            System.out.println("Error al declarar asistencia: " + e.getMessage());
        }

    }

    public ArrayList<Comentario> getComent() {
        Client cliente = new Client();
        HashMap<String, Object> session = new HashMap<>();
        String context = "/getComentario";
        session.put("evento", eventName);
        session = cliente.sentMessage(context, session);

        ArrayList<Comentario> comentarios = new ArrayList<>();
        if (session.containsKey("comentarios")) {
            Object comentariosObj = session.get("comentarios");
            if (comentariosObj instanceof ArrayList) {
                comentarios = (ArrayList<Comentario>) comentariosObj;
            }
        }

        // Verifica si la lista está vacía
        if (comentarios.isEmpty()) {
            System.out.println("No hay comentarios para este evento.");
        }

        return comentarios;
    }


    public void actualizarCommentListModel() {
        ArrayList<Comentario> comentarios = getComent();
        commentListModel.clear();

        if (comentarios.isEmpty()) {
            commentListModel.addElement("No hay comentarios disponibles para este evento.");
        } else {
            for (int i=0; i<comentarios.size();i++) {
                commentListModel.addElement(comentarios.get(i).toString());
            }
        }
    }


    public static void main(String[] args) {
    }
}
