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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoMusica() {
        return tipoMusica;
    }

    public void setTipoMusica(String tipoMusica) {
        this.tipoMusica = tipoMusica;
    }

    public String getDiasApertura() {
        return diasApertura;
    }

    public void setDiasApertura(String diasApertura) {
        this.diasApertura = diasApertura;
    }

    public String getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(String edadMinima) {
        this.edadMinima = edadMinima;
    }

    public String getPrecioMedio() {
        return precioMedio;
    }

    public void setPrecioMedio(String precioMedio) {
        this.precioMedio = precioMedio;
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
