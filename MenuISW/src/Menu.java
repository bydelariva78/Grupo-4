import FileReader.Constantes;
import FileReader.Reader;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Menu extends JPanel {
    private ArrayList<Eventos> eventos = new ArrayList<Eventos>();
    private JPanel panelDeBotones;

    public Menu() {
        this.setLayout(new BorderLayout());

        // Creamos un panel para los botones y lo añadimos al JScrollPane
        panelDeBotones = new JPanel();
        panelDeBotones.setLayout(new BoxLayout(panelDeBotones, BoxLayout.Y_AXIS));  // Apilar botones verticalmente

        JScrollPane scrollPane = new JScrollPane(panelDeBotones);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        // Agregamos los eventos
        start();

        // Añadimos el JScrollPane al panel principal
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void start() {
        Reader.leerOpciones();
        for (int i = 0; i < Constantes.NUMERO_DE_EVENTOS; i++) {
            Eventos e = new Eventos(Constantes.NOMBRES.get(i), Constantes.FECHAS.get(i), Constantes.EDADES.get(i));
            eventos.add(e);
        }
        init(panelDeBotones);

    }


    public void init(JPanel panelDeBotones) {
        for (Eventos evento : eventos) {
            BotonEvento botonEvento = new BotonEvento(evento);

            botonEvento.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));  // Ancho máximo y altura fija de 100px
            botonEvento.setAlignmentX(Component.CENTER_ALIGNMENT);


            panelDeBotones.add(botonEvento);
            panelDeBotones.add(Box.createRigidArea(new Dimension(0, 10)));

        }

        panelDeBotones.setPreferredSize(new Dimension(panelDeBotones.getWidth(), eventos.size() * 110));
        panelDeBotones.revalidate();
        panelDeBotones.repaint();


    }
}
