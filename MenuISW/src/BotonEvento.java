import FileReader.Constantes;

import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BotonEvento extends JButton {
    Eventos eventos;

    public BotonEvento(Eventos eventos) {
        this.eventos = eventos;
        this.setForeground(Color.WHITE);
        this.setPreferredSize(new Dimension(800, 100));  // Tamaño más ajustado para una tarjeta
        this.setBackground(new Color(60, 63, 65));  // Fondo oscuro para el botón
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Márgenes dentro del botón
        this.setFocusPainted(false);  // Elimina el borde de foco al seleccionar
        init();

        // Efecto de hover para resaltar la tarjeta
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(77, 150, 255));  // Cambio de color al pasar el ratón
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(60, 63, 65));  // Color original al salir
            }
        });
    }

    public void init() {
        this.setLayout(new BorderLayout());  // Cambia a BorderLayout para organizar mejor los elementos
        this.add(Panelinfo(eventos), BorderLayout.CENTER);  // Panel de información en el centro
    }

    public JPanel Panelinfo(Eventos eventos) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.setOpaque(false);  // Hacer que el panel sea transparente para mantener el color del botón

        // Nombre del evento (más grande y negrita)
        Font font1 = new Font("SansSerif", Font.BOLD, 20);
        JLabel label1 = new JLabel();
        label1.setFont(font1);
        label1.setForeground(Color.WHITE);  // Texto blanco
        label1.setText(eventos.nombre);
        panel.add(label1);

        // Fecha del evento
        Font font2 = new Font("SansSerif", Font.PLAIN, 14);
        JLabel label2 = new JLabel();
        label2.setFont(font2);
        label2.setForeground(Color.LIGHT_GRAY);
        label2.setText(Constantes.dateFormat.format(eventos.fecha));
        panel.add(label2);

        // Edad mínima
        Font font3 = new Font("SansSerif", Font.PLAIN, 14);
        JLabel label3 = new JLabel();
        label3.setFont(font3);
        label3.setForeground(Color.LIGHT_GRAY);
        label3.setText("EDAD: " + eventos.edad);
        panel.add(label3);

        return panel;
    }
}

