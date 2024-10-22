package ui;

import FileReader.Constantes;
import database.DatabaseOperations;
import modelo.Eventos;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Menu extends JPanel {
    private ArrayList<Eventos> eventos;  // Lista local de eventos
    private JPanel panelDeBotones;

    public Menu() {
        // Configuramos el layout del panel
        this.setLayout(new BorderLayout());

        // Inicializamos la lista de eventos
        this.eventos = new ArrayList<>();

        // Creamos un panel para los botones y lo añadimos al JScrollPane
        panelDeBotones = new JPanel();
        panelDeBotones.setLayout(new BoxLayout(panelDeBotones, BoxLayout.Y_AXIS));  // Apilar botones verticalmente

        // Creamos el scroll para el panel de botones
        JScrollPane scrollPane = new JScrollPane(panelDeBotones);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);  // Aumentar velocidad del scroll

        // Agregamos los eventos
        start();

        // Añadimos el JScrollPane al panel principal
        this.add(scrollPane, BorderLayout.CENTER);
    }

    // Método para cargar los eventos desde la base de datos
    public void start() {
        // Obtenemos los eventos de la base de datos
        DatabaseOperations.obtenerEventos();

        // Llenamos la lista local con los eventos de Constantes.EVENTOS
        eventos.addAll(Constantes.EVENTOS);

        // Inicializamos los botones con los eventos
        init(panelDeBotones);
    }

    // Método para inicializar los botones con los eventos
    public void init(JPanel panelDeBotones) {
        // Iteramos sobre los eventos y creamos un botón para cada uno
        for (Eventos evento : eventos) {
            System.out.println(evento);  // Imprimimos el evento para depuración

            // Creamos un botón personalizado para cada evento
            BotonEvento botonEvento = new BotonEvento(evento);

            // Ajustamos el tamaño máximo y alineación del botón
            botonEvento.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
            botonEvento.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Añadimos el botón al panel de botones
            panelDeBotones.add(botonEvento);
            panelDeBotones.add(Box.createRigidArea(new Dimension(0, 10)));  // Espacio entre los botones
        }

        // Ajustamos el tamaño preferido del panel de botones en función del número de eventos
        panelDeBotones.setPreferredSize(new Dimension(panelDeBotones.getWidth(), eventos.size() * 110));

        // Actualizamos el panel
        panelDeBotones.revalidate();
        panelDeBotones.repaint();
    }
}
