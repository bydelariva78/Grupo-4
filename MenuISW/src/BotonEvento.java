import FileReader.Constantes;

import javax.swing.*;
import java.awt.*;

public class BotonEvento extends JButton {
    Eventos eventos;
    public BotonEvento(Eventos eventos)
    {
        this.eventos=eventos;
        this.setForeground(Color.WHITE);
        this.setPreferredSize(new Dimension(800,500));
        init();
    }

    public void init()
    {
        this.add(Panelinfo(eventos));
    }

    public JPanel Panelinfo(Eventos eventos)
    {
        JPanel panel= new JPanel();
        panel.setLayout(new GridLayout(2,2));

        Font font1 = new Font("SansSerif", Font.BOLD, 15);
        JLabel label1=new JLabel();
        label1.setFont(font1);
        label1.setForeground(Color.BLACK);
        label1.setText(eventos.nombre);
        panel.add(label1);

        Font font2 = new Font("SansSerif", Font.PLAIN, 12);
        JLabel label2=new JLabel();
        label2.setFont(font2);
        label2.setForeground(Color.GRAY);
        label2.setText(Constantes.dateFormat.format(eventos.fecha));
        panel.add(label2);


        Font font3 = new Font("SansSerif", Font.PLAIN, 12);
        JLabel label3=new JLabel();
        label3.setFont(font3);
        label3.setForeground(Color.BLACK);
        label3.setText("EDAD:"+Integer.toString(eventos.edad));
        panel.add(label3);
        return panel;

    }



}
