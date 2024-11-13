package dao;

import database.DatabaseOperations;

import modelo.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerDAOTest {

    @Mock
    private DatabaseOperations customerDAO;  // Simulación de CustomerDAO


    @BeforeEach
    public void setUp() {
        // Inicializar los mocks
        MockitoAnnotations.openMocks(this);
        // Inicializar manualmente el customerDAO
        customerDAO = mock(DatabaseOperations.class);
    }

    @Test
    public void testGetCustomerById() {
        Usuario expectedCustomer = new Usuario("jaime", "jaime");

        // Simular el comportamiento del método getCliente
        when(customerDAO.getUser("jaime")).thenReturn(expectedCustomer);

        // Llamar al método
        Usuario actualCustomer = customerDAO.getUser("jaime");

        // Verificar que el resultado es correcto
        assertNotNull(actualCustomer);
        assertEquals("jaime", actualCustomer.getNombre());
        assertEquals("jaime", actualCustomer.getContrasenya());

        // Verificar que el método getCliente fue llamado
        verify(customerDAO, times(1)).getUser("jaime");
    }
}