package ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import Client.Client;
import modelo.*;

public class InicioSesion extends JFrame {

    // Componentes de la interfaz
    private JTextField campoUsuario;
    private JPasswordField campoContrasena;
    private JButton botonIniciarSesion;
    private JButton botonRegistrarse;
    private JLabel informacion;
    private String usuario;
    private String contrasena;

    // Nuevo JCheckBox para seleccionar entre Usuario o Discoteca
    private JCheckBox checkBoxTipoSesion;
    private JLabel labelTipoSesion;



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

        // Crear un JCheckBox para seleccionar tipo de sesión
        checkBoxTipoSesion = new JCheckBox("Iniciar sesión como Discoteca");
        checkBoxTipoSesion.setBackground(new Color(40, 40, 40)); // Fondo oscuro
        checkBoxTipoSesion.setForeground(Color.WHITE); // Texto blanco
        checkBoxTipoSesion.setFont(new Font("SansSerif", Font.PLAIN, 12));
        checkBoxTipoSesion.setSelected(false); // Por defecto está como Usuario

        // Crear JLabel para mostrar el tipo de sesión
        labelTipoSesion = new JLabel("Iniciar sesión como: Usuario");
        labelTipoSesion.setForeground(Color.WHITE);
        labelTipoSesion.setFont(new Font("SansSerif", Font.BOLD, 14));

        // Acción del JCheckBox para cambiar entre Usuario y Discoteca
        checkBoxTipoSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxTipoSesion.isSelected()) {
                    labelTipoSesion.setText("Iniciando sesión como: Discoteca");
                } else {
                    labelTipoSesion.setText("Iniciando sesión como: Usuario");
                }
            }
        });

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

        // Configurar las posiciones en el layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(labelTipoSesion, gbc); // Coloca el label para el tipo de sesión encima de etiquetaUsuario

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(etiquetaUsuario, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(campoUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(etiquetaContrasena, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(campoContrasena, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Botón ocupa dos columnas
        panel.add(botonIniciarSesion, gbc);

        // Añadir el botón de registrarse debajo del botón de iniciar sesión
        gbc.gridy = 4;
        panel.add(botonRegistrarse, gbc);

        // Añadir el JCheckBox para cambiar el tipo de sesión
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(checkBoxTipoSesion, gbc);

        informacion = new JLabel("");  // Inicializa el JLabel con un texto vacío o con un mensaje por defecto
        informacion.setForeground(Color.RED);

        gbc.gridy = 6;  // Colocar el JLabel debajo del botón de registrarse
        panel.add(informacion, gbc);

        // Añadir el panel a la ventana
        add(panel);
        this.setVisible(true);

        // Acción del botón "Iniciar Sesión"
        botonIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuario = campoUsuario.getText();
                contrasena = campoContrasena.getText();
                System.out.println("Seleccionado: "+ checkBoxTipoSesion.isSelected());
                if(checkBoxTipoSesion.isSelected()){
                    informacion.setText(inicioSesionDisco());
                }
                else{
                    informacion.setText(inicioSesion());
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

    public static void main(String[] args){
        new InicioSesion();
    }

    public String inicioSesion() {
        Client cliente = new Client(); //aqui no estas instanciando ningun host o port
        HashMap<String, Object> session = new HashMap<>();
        String context = "/inicioSesion";
        session.put("nombre", usuario); //
        session.put("contrasena", contrasena.toString());
        session = cliente.sentMessage(context, session);
        if (session.get("encontrado").equals(true)) {
            Usuario user = (Usuario) session.get("usuario");
            new Ventana(user);
            dispose();
            return ("Bienvenido " + user.getNombre());
        } else {
            return ("Usuario o contraseña incorrectos");
        }
    }
    public String inicioSesionDisco() {
        Client cliente = new Client(); //aqui no estas instanciando ningun host o port
        HashMap<String, Object> session = new HashMap<>();
        String context = "/inicioSesionDiscoteca";
        session.put("nombre", usuario); //
        session.put("contrasena", contrasena.toString());
        session = cliente.sentMessage(context, session);
        if (session.get("encontrado").equals(true)) {
            Eventos evento = (Eventos) session.get("evento");
            new VentanaMenu(evento);
            dispose();
            return ("Bienvenido " + evento.getNombre());
        } else {
            return ("Usuario o contraseña incorrectos");
        }
    }

}







