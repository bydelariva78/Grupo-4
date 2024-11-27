package ui;



import javax.swing.*;
import java.awt.*;
import modelo.Usuario;

public class Ventana extends JFrame {
    private PanelNorth north;
    private JPanel center;
    private Usuario user;
    private JPanel panel;

    public Ventana(Usuario user, JPanel panel)
    {
        this.user=user;
        this.panel=panel;
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

    public void init()
    {
        north=new PanelNorth(this,user);
        center=panel;
        if (panel instanceof Menu)
        {
            ((Menu) panel).setVentana(this);
        }
    }

    public static void main (String[] args)
    {
        new Ventana();

    }

    public Usuario getUser() {
        return user;
    }
}
