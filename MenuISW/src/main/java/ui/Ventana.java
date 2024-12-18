package ui;



import javax.swing.*;
import java.awt.*;
import modelo.Usuario;

public class Ventana extends JFrame {
    private PanelNorth north;
    private Menu center;
    private Usuario user;

    public Ventana(Usuario user)
    {
        this.user=user;
        init();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setSize(800,1000);
        setLayout(new BorderLayout());
        this.add(north, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);



    }
    public Ventana(){

    }

    public void restart()
    {
        center.obtenerEventos();
    }

    public void init()
    {
        north=new PanelNorth(this,user);
        center=new Menu();

    }

    public static void main (String[] args)
    {
        new Ventana();

    }

}
