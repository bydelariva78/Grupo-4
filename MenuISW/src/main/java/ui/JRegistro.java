package ui;

import database.DatabaseOperations;
import modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JRegistro extends JFrame implements ActionListener{

    JButton botonRegistrar;
    JTextField nombre;
    JPasswordField contrasenya;
    JPasswordField contrasenya2;
    JLabel etiqueta_nombre;
    JLabel etiqueta_contrasenya;
    JLabel etiqueta_contrasenya2;
    JLabel comprobarContrasenya;
    JPanel panel;
    public static final int ANCHO = 1100;
    public static final int ALTO = 900;

    public JRegistro(){
        setSize(ANCHO, ALTO);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        init();
    }

    public static void main(String[] args) {
        new JRegistro();
    }

    public void init(){
        // Configuramos el panel con GridBagLayout para centrar los componentes
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(40, 40, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margen entre componentes
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        // Etiqueta Nombre
        this.etiqueta_nombre = new JLabel("Nombre:");
        etiqueta_nombre.setForeground(Color.WHITE);
        etiqueta_nombre.setFont(new Font("SansSerif", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(etiqueta_nombre, gbc);

        // Campo Nombre
        this.nombre = new JTextField(20);
        nombre.setBackground(new Color(60, 63, 65));
        nombre.setForeground(Color.WHITE);
        nombre.setCaretColor(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nombre, gbc);

        // Etiqueta Contraseña
        this.etiqueta_contrasenya = new JLabel("Contraseña:");
        etiqueta_contrasenya.setForeground(Color.WHITE);
        etiqueta_contrasenya.setFont(new Font("SansSerif", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(etiqueta_contrasenya, gbc);

        // Campo Contraseña
        this.contrasenya = new JPasswordField(20);
        contrasenya.setBackground(new Color(60, 63, 65));
        contrasenya.setForeground(Color.WHITE);
        contrasenya.setCaretColor(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(contrasenya, gbc);

        // Etiqueta Repetir Contraseña
        this.etiqueta_contrasenya2 = new JLabel("Repita la contraseña");
        etiqueta_contrasenya2.setForeground(Color.WHITE);
        etiqueta_contrasenya2.setFont(new Font("SansSerif", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(etiqueta_contrasenya2, gbc);

        // Campo Repetir Contraseña
        this.contrasenya2 = new JPasswordField(20);
        contrasenya2.setBackground(new Color(60, 63, 65));
        contrasenya2.setForeground(Color.WHITE);
        contrasenya2.setCaretColor(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(contrasenya2, gbc);

        // Etiqueta para mensajes de comprobación
        comprobarContrasenya = new JLabel();
        comprobarContrasenya.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(comprobarContrasenya, gbc);

        // Botón Registrar
        this.botonRegistrar = new JButton("REGISTRAR");
        botonRegistrar.setBackground(new Color(77, 150, 255));
        botonRegistrar.setForeground(Color.WHITE);
        botonRegistrar.setFocusPainted(false);
        botonRegistrar.setFont(new Font("SansSerif", Font.BOLD, 16));

        // Efecto hover para el botón Registrar
        botonRegistrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botonRegistrar.setBackground(new Color(50, 120, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonRegistrar.setBackground(new Color(77, 150, 255));
            }
        });

        botonRegistrar.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(botonRegistrar, gbc);

        // Añadir el panel al JFrame
        add(panel);
        setVisible(true);
    }

    public boolean comprobarContrasenya(String contrasenya1, String contrasenya2){
        return contrasenya1.equals(contrasenya2);
    }

    public void crearUsuario() throws InterruptedException {
        String nom = nombre.getText();
        String con = new String(contrasenya.getPassword());
        String con2 = new String(contrasenya2.getPassword());

        if(nom.equals("")){
            comprobarContrasenya.setText("Debe rellenar el campo de nombre");
        } else if(con.length() < 5){
            comprobarContrasenya.setText("La contraseña debe tener un mínimo de 5 caracteres");
        } else if(comprobarContrasenya(con, con2)){
            comprobarContrasenya.setForeground(Color.GREEN);
            comprobarContrasenya.setText("Usuario registrado con ÉXITO");
            Usuario user = new Usuario(nom, con);
            DatabaseOperations.insertUser(user);
            Thread.sleep(3000);
        } else {
            comprobarContrasenya.setText("Las contraseñas no coinciden");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(botonRegistrar)){
            try {
                crearUsuario();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
