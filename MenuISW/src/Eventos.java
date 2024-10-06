import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Eventos extends JPanel{
    public String nombre;
    public Date fecha;
    public Integer edad;
    public ImageIcon logo;


    public Eventos(String nombre, Date fecha, Integer edad) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.edad = edad;
        this.add(new BotonEvento(this));
    }

    public Eventos(String nombre)
    {
        this.nombre=nombre;
    }
    public Eventos()
    {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
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
                ", fecha=" + fecha +
                ", edad=" + edad +
                ", logo=" + logo +
                '}';
    }
}
