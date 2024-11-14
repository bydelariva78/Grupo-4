package ui;

import Client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class JRegistro extends JFrame implements ActionListener {

    JButton botonRegistrar;
    JTextField nombre;
    JPasswordField contrasenya;
    JPasswordField contrasenya2;
    JLabel etiqueta_nombre;
    JLabel etiqueta_contrasenya;
    JLabel etiqueta_contrasenya2;
    JLabel comprobarContrasenya;
    JPanel panel;

    // Componentes adicionales para registro de discoteca
    private JCheckBox checkBoxRegistrarDiscoteca;
    private JTextField campoEdadMinima;
    private JTextField campoDiasApertura;
    private JTextField campoPrecioMedio;
    private JComboBox<String> comboTipoMusica;
    private JLabel labelEdadMinima, labelDiasApertura, labelPrecioMedio, labelTipoMusica;

    public static final int ANCHO = 1100;
    public static final int ALTO = 900;

    public JRegistro() {
        setSize(ANCHO, ALTO);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        init();
    }

    public void init() {
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

        // Checkbox para "Registrar discoteca"
        checkBoxRegistrarDiscoteca = new JCheckBox("Registrar discoteca");
        checkBoxRegistrarDiscoteca.setForeground(Color.WHITE);
        checkBoxRegistrarDiscoteca.setBackground(new Color(40, 40, 40));
        checkBoxRegistrarDiscoteca.setFont(new Font("SansSerif", Font.PLAIN, 12));
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(checkBoxRegistrarDiscoteca, gbc);

        // Componentes adicionales para registro de discoteca
        labelEdadMinima = new JLabel("Edad mínima:");
        labelEdadMinima.setForeground(Color.WHITE);
        campoEdadMinima = new JTextField(15);
        campoEdadMinima.setBackground(new Color(60, 63, 65));
        campoEdadMinima.setForeground(Color.WHITE);

        labelDiasApertura = new JLabel("Días de apertura:");
        labelDiasApertura.setForeground(Color.WHITE);
        campoDiasApertura = new JTextField(15);
        campoDiasApertura.setBackground(new Color(60, 63, 65));
        campoDiasApertura.setForeground(Color.WHITE);

        labelPrecioMedio = new JLabel("Precio medio:");
        labelPrecioMedio.setForeground(Color.WHITE);
        campoPrecioMedio = new JTextField(15);
        campoPrecioMedio.setBackground(new Color(60, 63, 65));
        campoPrecioMedio.setForeground(Color.WHITE);

        labelTipoMusica = new JLabel("Tipo de música:");
        labelTipoMusica.setForeground(Color.WHITE);
        comboTipoMusica = new JComboBox<>(new String[]{"Electrónica", "Reguetón", "Rock", "Pop", "Jazz"});
        comboTipoMusica.setBackground(new Color(60, 63, 65));
        comboTipoMusica.setForeground(Color.WHITE);

        // Mostrar/ocultar campos adicionales basados en el estado del checkbox
        checkBoxRegistrarDiscoteca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean mostrar = checkBoxRegistrarDiscoteca.isSelected();
                labelEdadMinima.setVisible(mostrar);
                campoEdadMinima.setVisible(mostrar);
                labelDiasApertura.setVisible(mostrar);
                campoDiasApertura.setVisible(mostrar);
                labelPrecioMedio.setVisible(mostrar);
                campoPrecioMedio.setVisible(mostrar);
                labelTipoMusica.setVisible(mostrar);
                comboTipoMusica.setVisible(mostrar);
            }
        });

        // Posicionar los componentes adicionales (inicialmente ocultos)
        gbc.gridy = 8;
        panel.add(labelEdadMinima, gbc);
        gbc.gridy = 9;
        panel.add(campoEdadMinima, gbc);
        gbc.gridy = 10;
        panel.add(labelDiasApertura, gbc);
        gbc.gridy = 11;
        panel.add(campoDiasApertura, gbc);
        gbc.gridy = 12;
        panel.add(labelPrecioMedio, gbc);
        gbc.gridy = 13;
        panel.add(campoPrecioMedio, gbc);
        gbc.gridy = 14;
        panel.add(labelTipoMusica, gbc);
        gbc.gridy = 15;
        panel.add(comboTipoMusica, gbc);

        // Ocultar los campos inicialmente
        labelEdadMinima.setVisible(false);
        campoEdadMinima.setVisible(false);
        labelDiasApertura.setVisible(false);
        campoDiasApertura.setVisible(false);
        labelPrecioMedio.setVisible(false);
        campoPrecioMedio.setVisible(false);
        labelTipoMusica.setVisible(false);
        comboTipoMusica.setVisible(false);

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
        gbc.gridy = 16;
        panel.add(botonRegistrar, gbc);

        // Añadir el panel al JFrame
        add(panel);
        setVisible(true);
    }

    public boolean comprobarContrasenya(String contrasenya1, String contrasenya2) {
        return contrasenya1.equals(contrasenya2);
    }

    public boolean comprobarCampos() {
        String nom = nombre.getText();
        String con = new String(contrasenya.getPassword());
        String con2 = new String(contrasenya2.getPassword());
        boolean correcto = false;
        if(checkBoxRegistrarDiscoteca.isSelected()){
            if (nom.equals("")) {
                comprobarContrasenya.setText("Debe rellenar el campo de nombre");
            } else if (con.length() < 5) {
                comprobarContrasenya.setText("La contraseña debe tener un mínimo de 5 caracteres");
            }
            else if(campoEdadMinima.getText().equals("")) {
                comprobarContrasenya.setText("Debe rellenar el campo de Edad Minima");
            }
            else if(campoDiasApertura.getText().equals("")){
                comprobarContrasenya.setText("Debe rellenar el campo de Dias de apertura");
            }
            else if(campoPrecioMedio.getText().equals("")){
                comprobarContrasenya.setText("Debe rellenar el campo de Precio Medio");
            }else if (comprobarContrasenya(con, con2)) {
                comprobarContrasenya.setForeground(Color.GREEN);
                comprobarContrasenya.setText("Ya existe un usuario con ese nombre, prueve con otro");
                correcto = true;
            } else {
                comprobarContrasenya.setText("Las contraseñas no coinciden");
            }
        }
        else{
            if (nom.equals("")) {
                comprobarContrasenya.setText("Debe rellenar el campo de nombre");
            } else if (con.length() < 5) {
                comprobarContrasenya.setText("La contraseña debe tener un mínimo de 5 caracteres");
            }
            else if (comprobarContrasenya(con, con2)) {
                comprobarContrasenya.setForeground(Color.GREEN);
                comprobarContrasenya.setText("Ya existe un usuario con ese nombre, prueve con otro");
                correcto = true;
            } else {
                comprobarContrasenya.setText("Las contraseñas no coinciden");
            }
        }

        return correcto;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(botonRegistrar)) {
            if (comprobarCampos()) {
                registro();
            }
        }
    }

    public String registro() {
        Client cliente = new Client();
        HashMap<String, Object> session = new HashMap<>();
        String context = "/registrar";
        session.put("nombre", nombre.getText());
        session.put("contrasena", contrasenya.getText().toString());


        // Solo añadimos los datos de discoteca si está seleccionado el checkbox
        if (checkBoxRegistrarDiscoteca.isSelected()) {
            session.put("edadMinima", campoEdadMinima.getText());
            session.put("diasApertura", campoDiasApertura.getText());
            session.put("precioMedio", campoPrecioMedio.getText());
            session.put("tipoMusica", comboTipoMusica.getSelectedItem());
            context = "/registrarDiscoteca";
        }

        session = cliente.sentMessage(context, session);
        System.out.println("sesion en ventana"+session);
        if (session.get("registrado").equals(true)) {
            new InicioSesion();
            dispose();
            return "Usuario registrado con éxito";
        } else {
            return "Error al registrar usuario";
        }
    }
}