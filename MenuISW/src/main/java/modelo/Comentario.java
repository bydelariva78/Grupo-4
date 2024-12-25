package modelo;

public class Comentario {
    public Usuario user;
    public String comentario;
    public Eventos evento;

    public Comentario(String comentario, Usuario user, Eventos evento) {
        this.comentario = comentario;
        this.user = user;
        this.evento=evento;
    }

    public Eventos getEvento() {
        return evento;
    }

    public void setEvento(Eventos evento) {
        this.evento = evento;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
