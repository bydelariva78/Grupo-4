package domain;

import modelo.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

    private Usuario customer;

    @BeforeEach
    public void setUp() {
        customer = new Usuario("Beltran", "Beltran");
    }

    @Test
    public void testGetId() {
        assertEquals("Beltran", customer.getNombre());
    }

    @Test
    public void testSetId() {
        customer.setNombre("Beltran");
        assertEquals("Beltran", customer.getNombre());
    }

    @Test
    public void testGetName() {
        assertEquals("Beltran", customer.getContrasenya());
    }

    @Test
    public void testSetLastName() {
        customer.setContrasenya("Smith");
        assertEquals("Smith", customer.getContrasenya());
    }
}