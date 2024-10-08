import FileReader.Constantes;

import javax.swing.*;
import java.awt.*;
import java.net.StandardSocketOptions;

public class Ventana extends JFrame {
    private PanelNorth north;
    private Menu center;

    public Ventana()
    {

        init();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setSize(800,1000);
        setLayout(new BorderLayout());
        this.add(north, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        System.out.println(Constantes.NOMBRES.get(1));


    }

    public void restart()
    {
        center.start();
    }

    public void init()
    {
        north=new PanelNorth(this);
        center=new Menu();

    }

    public static void main (String[] args)
    {
        new Ventana();

    }

}
