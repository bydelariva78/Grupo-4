package ui;

import FileReader.Constantes;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    private PanelNorth north;
    private ui.Menu center;

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
