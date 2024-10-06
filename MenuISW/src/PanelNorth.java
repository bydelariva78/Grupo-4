import javax.swing.*;
import java.awt.*;

public class PanelNorth extends JPanel {

    public JButton Map;
    public JButton Discos;

    public PanelNorth()
    {
        init();
        setSize(800,100);
        this.setLayout(new GridLayout(1,2));
        this.add(Map);
        this.add(Discos);
    }

    private void init()
    {
        Map=new JButton();
        Map.setText("MAPA");
        Map.setPreferredSize(new Dimension(200,100));
        Discos=new JButton();
        Discos.setPreferredSize(new Dimension(200,100));
        Discos.setText("DISCOTECAS");

    }


}
