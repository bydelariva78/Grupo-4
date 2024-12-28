package modelo;

import java.io.Serializable;

public class Usuario implements Serializable {
    String nombre;
    String contrasenya;
    Integer puntos;

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", contrasenya='" + contrasenya + '\'' +
                ", puntos=" + puntos +
                '}';
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public Usuario(String nombre, String contrasenya){
        this.nombre = nombre;
        this.contrasenya = contrasenya;
        this.puntos=100;

    }
    public Usuario()
    {

    }
}
