package modelo;
import java.io.Serializable;

public class Comentario implements Serializable {
    public String user;
    public String comentario;
    public String evento;

    private static final long serialVersionUID = 1L;


    public Comentario(String comentario, String nombreuser, String  nomevento) {
        this.comentario = comentario;
        this.user = nombreuser;
        this.evento=nomevento;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(user);
        sb.append(":");
        sb.append(comentario);
        return sb.toString();
    }
}
