package modelo;

//Hay que crearse en la base de datos una nueva tabla que se llame comentario y que tenga de valores, la discoteca el usuario y el propio comentario en string.
public class Comentario {
    public Eventos evento;
    public Usuario user;
    public String comentario;
    public Comentario(Eventos evento, Usuario user){
        this.evento = evento;
        this.user = user;
    }
    public Comentario(){
    }
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
