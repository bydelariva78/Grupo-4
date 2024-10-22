package ui;

import database.DatabaseOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InicioSesion extends JFrame {

    // Componentes de la interfaz
    private JTextField campoUsuario;
    private JPasswordField campoContrasena;
    private JButton botonIniciarSesion;
    private JButton botonRegistrarse;
    private JLabel comprobarContrasenya;

    public InicioSesion() {
        // Configuración básica de la ventana
        setTitle("Iniciar Sesión");
        setSize(1100, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setResizable(false);

        // Crear el panel principal con GridBagLayout y fondo oscuro
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(40, 40, 40));  // Fondo oscuro

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes

        // Crear los componentes con la nueva estética
        JLabel etiquetaUsuario = new JLabel("Usuario:");
        etiquetaUsuario.setForeground(Color.WHITE);  // Texto en blanco
        etiquetaUsuario.setFont(new Font("SansSerif", Font.BOLD, 16));

        campoUsuario = new JTextField(20);  // Campo de texto estilizado
        campoUsuario.setBackground(new Color(60, 63, 65));
        campoUsuario.setForeground(Color.WHITE);
        campoUsuario.setCaretColor(Color.WHITE);

        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        etiquetaContrasena.setForeground(Color.WHITE);
        etiquetaContrasena.setFont(new Font("SansSerif", Font.BOLD, 16));

        campoContrasena = new JPasswordField(20);  // Campo de contraseña estilizado
        campoContrasena.setBackground(new Color(60, 63, 65));
        campoContrasena.setForeground(Color.WHITE);
        campoContrasena.setCaretColor(Color.WHITE);

        botonIniciarSesion = new JButton("Iniciar Sesión");
        botonIniciarSesion.setBackground(new Color(77, 150, 255));
        botonIniciarSesion.setForeground(Color.WHITE);
        botonIniciarSesion.setFocusPainted(false);
        botonIniciarSesion.setFont(new Font("SansSerif", Font.BOLD, 16));

        botonRegistrarse = new JButton("No tengo cuenta, registrarme");
        botonRegistrarse.setBackground(new Color(77, 150, 255));
        botonRegistrarse.setForeground(Color.WHITE);
        botonRegistrarse.setFocusPainted(false);
        botonRegistrarse.setFont(new Font("SansSerif", Font.BOLD, 16));

        // Efecto hover para los botones
        botonIniciarSesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botonIniciarSesion.setBackground(new Color(50, 120, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonIniciarSesion.setBackground(new Color(77, 150, 255));
            }
        });

        botonRegistrarse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botonRegistrarse.setBackground(new Color(50, 120, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonRegistrarse.setBackground(new Color(77, 150, 255));
            }
        });

        comprobarContrasenya = new JLabel();
        comprobarContrasenya.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(comprobarContrasenya, gbc);

        // Configurar las posiciones en el layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(etiquetaUsuario, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(campoUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(etiquetaContrasena, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(campoContrasena, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Botón ocupa dos columnas
        panel.add(botonIniciarSesion, gbc);

        // Añadir el botón de registrarse debajo del botón de iniciar sesión
        gbc.gridy = 3;
        panel.add(botonRegistrarse, gbc);

        // Añadir el panel a la ventana
        add(panel);
        this.setVisible(true);

        // Acción del botón "Iniciar Sesión"
        botonIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean a=DatabaseOperations.loginUser(campoUsuario.getText(),campoContrasena.getText());
                if (a)
                {
                    dispose();
                    new Ventana();
                }else {
                    comprobarContrasenya.setText("Usuario o contraseña incorrecta");
                }
            }
        });

        // Acción del botón "Registrarse"
        botonRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new JRegistro();
            }
        });
    }

    private void mostrarPantallaRegistro() {
        JOptionPane.showMessageDialog(this, "Abrir pantalla de registro aquí");
    }

    public static void main(String[] args){
        new InicioSesion();
    }
}



