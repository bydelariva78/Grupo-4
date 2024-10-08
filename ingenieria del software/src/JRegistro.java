import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JRegistro extends JFrame implements ActionListener{

    JButton botonRegistrar;
    JTextField nombre;
    JTextField contrasenya;
    JTextField contrasenya2;
    JLabel etiqueta_nombre;
    JLabel etiqueta_contrasenya;
    JLabel etiqueta_contrasenya2;
    JLabel comprobarContrasenya;
    JPanel panel;
    public static final int ANCHO = 1100;
    public static final int ALTO = 900;
    public static final int posy = 200;
    public JRegistro(){
        setSize(ANCHO, ALTO);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
        setResizable(false);
        init();
    }
    public static void main(String[] args) {
        new JRegistro();
    }
    public void init(){

        this.etiqueta_nombre = new JLabel("Nombre:");
        etiqueta_nombre.setBounds(500, posy, 100, 25);
        etiqueta_nombre.setOpaque(true);

        this.nombre = new JTextField("");
        nombre.setBounds(500, posy+50, 100, 25);
        nombre.setOpaque(true);

        this.etiqueta_contrasenya = new JLabel("Contraseña:");
        etiqueta_contrasenya.setBounds(500,posy+100,100, 25);
        etiqueta_contrasenya.setOpaque(true);

        this.contrasenya = new JTextField();
        contrasenya.setBounds(500, posy + 150, 100, 25);
        contrasenya.setOpaque(true);

        this.etiqueta_contrasenya2 = new JLabel("Repita la contraseña");
        etiqueta_contrasenya2.setBounds(500,posy+200,150, 25);
        etiqueta_contrasenya2.setOpaque(true);

        this.contrasenya2 = new JTextField();
        contrasenya2.setBounds(500, posy+250, 100, 25);
        contrasenya2.setOpaque(true);

        this.panel = new JPanel(null);

        comprobarContrasenya = new JLabel();
        comprobarContrasenya.setBounds(500,posy+300,1000, 25);

        this.botonRegistrar = new JButton("REGISTRAR");
        botonRegistrar.setBounds(500, posy+350, 150, 25);
        botonRegistrar.setOpaque(true);
        botonRegistrar.addActionListener((ActionListener) this);

        panel.add(nombre);
        panel.add(contrasenya);
        panel.add(contrasenya2);
        panel.add(comprobarContrasenya);
        panel.add(etiqueta_contrasenya);
        panel.add(etiqueta_contrasenya2);
        panel.add(botonRegistrar);
        panel.add(etiqueta_nombre);
        panel.setVisible(true);
        add(panel);
        setVisible(true);
        setLayout(null);
    }
    public boolean comprobarContrasenya(String contrasenya1, String contrasenya2){
        boolean iguales;
        if(contrasenya1.equals(contrasenya2)){
            iguales = true;
        }
        else{
            iguales = false;
        }
        return(iguales);
    }
    public void crearUsuario(){
        String  nom = nombre.getText();
        String con = contrasenya.getText();
        String con2 = contrasenya2.getText();
        if(nom.equals("")){
            comprobarContrasenya.setText("Debe rellenar el campo de nombre");
        }
        else if(con.length()<5){
            comprobarContrasenya.setText("La contraseña debe tener un mínimo de 5 caracteres");
        }
        else if(comprobarContrasenya(con, con2)){
            comprobarContrasenya.setText("Usuario registrado con ÉXITO");
            Usuario user = new Usuario(nom, con);
            //Aqui habra que codificar el proceso de enviar el objeto usuario a la base de datos y crearlo
        }
        else{
            comprobarContrasenya.setText("Las contraseñas no coinciden");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(botonRegistrar)){
            crearUsuario();
        }
    }
}
