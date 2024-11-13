package modelo;

import ui.BotonEvento;

import javax.swing.*;
import java.util.Date;

public class Eventos extends JPanel{
    public String nombre;
    public String tipoMusica;
    public String diasApertura;
    public String edadMinima;
    public String precioMedio;
    public ImageIcon logo;


    public Eventos(String nombre, String tipoMusica, String diasApertura, String edadMinima, String precioMedio) {
        this.nombre = nombre;
        this.tipoMusica = tipoMusica;
        this.diasApertura = diasApertura;
        this.edadMinima = edadMinima;
        this.precioMedio = precioMedio;
        this.add(new BotonEvento(this));

    }
    public ImageIcon getLogo() {
        return logo;
    }

    public void setLogo(ImageIcon logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "Eventos{" +
                "nombre='" + nombre + '\'' +
                ", tipoMusica='" + tipoMusica + '\'' +
                ", diasApertura='" + diasApertura + '\'' +
                ", edadMinima='" + edadMinima + '\'' +
                ", precioMedio='" + precioMedio + '\'' +
                ", logo=" + logo +
                '}';

    }
}
