package domain;

import modelo.Eventos;
import modelo.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventosTest {

    private Eventos evento;

    @BeforeEach
    public void setUp() {
        evento = new Eventos("Goose", "Electrónica", "Jueves", "20", "19");
    }

    @Test
    public void testGetNombre() {
        assertEquals("Goose", evento.getNombre());
    }

    @Test
    public void testSetNombre() {
        evento.setNombre("Goose");
        assertEquals("Goose", evento.getNombre());
    }

    @Test
    public void testGetTipoMusica() {
        assertEquals("Electrónica", evento.getTipoMusica());
    }

    @Test
    public void testSetTipoMusica() {
        evento.setTipoMusica("Reguetón");
        assertEquals("Reguetón", evento.getTipoMusica());
    }

    @Test
    public void testGetEdadMinima() {
        assertEquals("20", evento.getEdadMinima());
    }

    @Test
    public void testSetEdadMinima() {
        evento.setEdadMinima("22");
        assertEquals("22", evento.getEdadMinima());
    }

    @Test
    public void testdiasApertura() {
        assertEquals("Jueves", evento.getDiasApertura());
    }

    @Test
    public void testSetDiasApertura() {
        evento.setDiasApertura("Miércoles");
        assertEquals("Miércoles", evento.getDiasApertura());
    }
}