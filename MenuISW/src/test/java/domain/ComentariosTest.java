package domain;

import modelo.Comentario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComentariosTest {

    private Comentario comment;

    @BeforeEach
    public void setUp() {
        comment = new Comentario("Me gusta","jaime","Goose");
    }

    @Test
    public void testGetComment() {
        assertEquals("Me gusta", comment.getComentario());
    }

    @Test
    public void testSetComment() {
        comment.setComentario("Me encanta");
        assertEquals("Me encanta", comment.getComentario());
    }

    @Test
    public void testGetUser() {
        assertEquals("jaime", comment.getUser());
    }

    @Test
    public void testSetLastName() {
        comment.setUser("Pedro");
        assertEquals("Pedro", comment.getUser());
    }

    @Test
    public void testGetEvento() {
        assertEquals("Goose", comment.getEvento());
    }

    @Test
    public void testSetEvento() {
        comment.setEvento("Marusha");
        assertEquals("Marusha", comment.getEvento());
    }



}
