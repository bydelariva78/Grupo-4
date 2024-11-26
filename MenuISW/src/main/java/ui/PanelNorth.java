package ui;

import modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelNorth extends JPanel {
    public JButton Perfil;
    public JButton Discos;
    public JButton CerrarSesion;
    public static Boolean start_discotecas = false;
    public static Boolean start_perfil = false;
    private JFrame ventana;
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

        // Panel para los botones (Perfil, Discotecas, Cerrar Sesión)
        JPanel botonesPanel = new JPanel(new GridLayout(1, 3, 10, 0)); // Organiza botones en tres columnas
        botonesPanel.setBackground(new Color(240, 240, 240)); // Fondo del panel de botones
        botonesPanel.add(Perfil);
        botonesPanel.add(Discos);
        botonesPanel.add(CerrarSesion);

        this.add(botonesPanel, BorderLayout.SOUTH); // Colocar los botones en la parte inferior del panel
    }

    private void init() {
        // Configuración del botón "MI PERFIL"
        Perfil = new JButton("MI PERFIL");
        configurarBoton(Perfil, new Color(77, 150, 255), new Color(60, 130, 230));
        Perfil.addActionListener(e -> {
            ventana.dispose();
            new MiCuentaFrame(user, ventana); // Navegar al frame "Mi Cuenta"
        });

        // Configuración del botón "DISCOTECAS"
        Discos = new JButton("DISCOTECAS");
        configurarBoton(Discos, new Color(255, 100, 100), new Color(230, 80, 80));
        Discos.addActionListener(e -> {
            ventana.dispose();
            new Ventana(user);
        });

        // Configuración del botón "CERRAR SESIÓN"
        CerrarSesion = new JButton("CERRAR SESIÓN");
        configurarBoton(CerrarSesion, new Color(173, 216, 230), new Color(100, 149, 237)); // Azul clarito y azul oscuro
        CerrarSesion.addActionListener(e -> {
            ventana.dispose();
            new InicioSesion(); // Vuelve a la ventana de inicio de sesión
        });
    }

    private void configurarBoton(JButton boton, Color colorNormal, Color colorHover) {
        boton.setPreferredSize(new Dimension(200, 50));
        boton.setFont(new Font("SansSerif", Font.BOLD, 16)); // Fuente más grande y negrita
        boton.setBackground(colorNormal); // Color de fondo
        boton.setForeground(Color.WHITE); // Texto blanco
        boton.setFocusPainted(false); // Elimina el borde de selección
        boton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añade margen interno
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor de mano

        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(colorHover); // Cambia al color más oscuro al pasar el ratón
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(colorNormal); // Vuelve al color original al salir
            }
        });
    }
}
