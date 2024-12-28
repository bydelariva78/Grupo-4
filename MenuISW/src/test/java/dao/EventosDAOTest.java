package dao;

import database.DatabaseOperations;

import modelo.Eventos;
import modelo.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EventosDAOTest {

    @Mock
    private DatabaseOperations EventosDAO;  // Simulación de CustomerDAO


    @BeforeEach
    public void setUp() {
        // Inicializar los mocks
        MockitoAnnotations.openMocks(this);
        // Inicializar manualmente el customerDAO
        EventosDAO = mock(DatabaseOperations.class);
    }

    @Test
    public void testGetEventoById() {
        Eventos expectedEvento = new Eventos("Banloo", "Reguetón", "Martes","15", "25");

        // Simular el comportamiento del método getCliente
        when(EventosDAO.getEvento("Banloo")).thenReturn(expectedEvento);

        // Llamar al método
        Eventos actualEvento = EventosDAO.getEvento("Banloo");

        // Verificar que el resultado es correcto
        assertNotNull(actualEvento);
        assertEquals("Banloo", actualEvento.getNombre());
        assertEquals("Reguetón", actualEvento.getTipoMusica());
        assertEquals("Martes", actualEvento.getDiasApertura());
        assertEquals("15", actualEvento.getEdadMinima());
        assertEquals("25", actualEvento.getPrecioMedio());


        // Verificar que el método getCliente fue llamado
        verify(EventosDAO, times(1)).getEvento("Banloo");
    }
}