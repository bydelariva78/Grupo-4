package ui;

import modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelNorth extends JPanel {
    public JButton Perfil;
    public JButton Discos;
    public JButton CerrarSesion;
    public static Boolean start_discotecas = false;
    public static Boolean start_perfil = false;
    private JFrame ventana;
    private Window frame;
    private Usuario user;

    public PanelNorth(JFrame ventana, Usuario user) {
        this.user = user;
        this.ventana = ventana;
        init();
        setPreferredSize(new Dimension(800, 100)); // Ajusta el tamaño del panel
        this.setLayout(new BorderLayout()); // Usamos BorderLayout para organizar mejor los elementos
        this.setBackground(new Color(240, 240, 240)); // Fondo del panel

        // Agregar el nombre del usuario en el centro
        JLabel nombreUsuario = new JLabel("Usuario: " + user.getNombre(), SwingConstants.CENTER);
        nombreUsuario.setFont(new Font("SansSerif", Font.BOLD, 18));
        nombreUsuario.setForeground(Color.BLACK);
        this.add(nombreUsuario, BorderLayout.CENTER);

        // Panel para los botones (Perfil, Discotecas y Cerrar Sesión)
        JPanel botonesPanel = new JPanel(new GridLayout(1, 3, 10, 0)); // Organiza botones en tres columnas
        botonesPanel.setBackground(new Color(240, 240, 240)); // Fondo del panel de botones
        botonesPanel.add(Perfil);
        botonesPanel.add(Discos);
        botonesPanel.add(CerrarSesion);

        this.add(botonesPanel, BorderLayout.SOUTH); // Colocar los botones en la parte inferior del panel
    }

    private void init() {
        // Configuración del botón "MI PERFIL"
        frame = SwingUtilities.getWindowAncestor(this);
        Perfil = new JButton("MI PERFIL");
        Perfil.setPreferredSize(new Dimension(200, 50));
        Perfil.setFont(new Font("SansSerif", Font.BOLD, 16)); // Fuente más grande y negrita
        Perfil.setBackground(new Color(77, 150, 255)); // Color de fondo azul claro
        Perfil.setForeground(Color.WHITE); // Texto blanco
        Perfil.setFocusPainted(false); // Elimina el borde de selección
        Perfil.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añade margen interno
        Perfil.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor de mano

        Perfil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Perfil.setBackground(new Color(60, 130, 230)); // Color más oscuro al pasar el ratón

            }

            @Override
            public void mouseExited(MouseEvent e) {
                Perfil.setBackground(new Color(77, 150, 255)); // Color original al salir
            }
        });

        Perfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.dispose();
                new MiCuentaFrame(user, ventana); // Navegar al frame "Mi Cuenta"
            }
        });

        // Configuración del botón "DISCOTECAS"
        Discos = new JButton("DISCOTECAS");
        Discos.setPreferredSize(new Dimension(200, 50));
        Discos.setFont(new Font("SansSerif", Font.BOLD, 16)); // Fuente más grande y negrita
        Discos.setBackground(new Color(255, 100, 100)); // Color de fondo rojo claro
        Discos.setForeground(Color.WHITE); // Texto blanco
        Discos.setFocusPainted(false); // Elimina el borde de selección
        Discos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añade margen interno
        Discos.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor de mano

        Discos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Discos.setBackground(new Color(230, 80, 80)); // Color más oscuro al pasar el ratón
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Discos.setBackground(new Color(255, 100, 100)); // Color original al salir
            }
        });

        Discos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.dispose();
                new Ventana(user, new Menu(user));
            }
        });

        // Configuración del botón "CERRAR SESIÓN"
        CerrarSesion = new JButton("CERRAR SESIÓN");
        CerrarSesion.setPreferredSize(new Dimension(200, 50));
        CerrarSesion.setFont(new Font("SansSerif", Font.BOLD, 16));
        CerrarSesion.setBackground(new Color(100, 100, 255)); // Color de fondo púrpura claro
        CerrarSesion.setForeground(Color.WHITE); // Texto blanco
        CerrarSesion.setFocusPainted(false);
        CerrarSesion.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        CerrarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR));

        CerrarSesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                CerrarSesion.setBackground(new Color(80, 80, 230)); // Color más oscuro al pasar el ratón
            }

            @Override
            public void mouseExited(MouseEvent e) {
                CerrarSesion.setBackground(new Color(100, 100, 255)); // Color original al salir
            }
        });

        CerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.dispose();
                new InicioSesion(); // Navegar a una ventana genérica
            }
        });
    }
}

