package ui;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelNorth extends JPanel {

    public JButton Map;
    public JButton Discos;

    public PanelNorth(Ventana ventana) {
        init();
        setPreferredSize(new Dimension(800, 100));  // Ajusta el tamaño del panel
        this.setLayout(new GridLayout(1, 2, 10, 0));  // Espacio entre los botones
        this.setBackground(new Color(240, 240, 240));  // Fondo del panel
        this.add(Map);
        this.add(Discos);
    }

    private void init() {
        // Configuración del botón "MAPA"
        Map = new JButton("MAPA");
        Map.setPreferredSize(new Dimension(200, 100));
        Map.setFont(new Font("SansSerif", Font.BOLD, 16));  // Fuente más grande y negrita
        Map.setBackground(new Color(77, 150, 255));  // Color de fondo azul claro
        Map.setForeground(Color.WHITE);  // Texto blanco
        Map.setFocusPainted(false);  // Elimina el borde de selección
        Map.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Añade margen interno
        Map.setCursor(new Cursor(Cursor.HAND_CURSOR));  // Cursor de mano

        // Efecto hover para "MAPA"
        Map.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Map.setBackground(new Color(60, 130, 230));  // Color más oscuro al pasar el ratón
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Map.setBackground(new Color(77, 150, 255));  // Color original al salir
            }
        });

        // Configuración del botón "DISCOTECAS"
        Discos = new JButton("DISCOTECAS");
        Discos.setPreferredSize(new Dimension(200, 100));
        Discos.setFont(new Font("SansSerif", Font.BOLD, 16));  // Fuente más grande y negrita
        Discos.setBackground(new Color(255, 100, 100));  // Color de fondo rojo claro
        Discos.setForeground(Color.WHITE);  // Texto blanco
        Discos.setFocusPainted(false);  // Elimina el borde de selección
        Discos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Añade margen interno
        Discos.setCursor(new Cursor(Cursor.HAND_CURSOR));  // Cursor de mano

        // Efecto hover para "DISCOTECAS"
        Discos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Discos.setBackground(new Color(230, 80, 80));  // Color más oscuro al pasar el ratón
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Discos.setBackground(new Color(255, 100, 100));  // Color original al salir
            }
        });

        Discos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
