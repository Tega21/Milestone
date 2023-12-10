package test;

import inventory.Health;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class tests the Health class, ensuring that its constructor,
 * getters, and setters function correctly.
 */
public class HealthTest {
    private Health health;

    /**
     * Sets up a Health object before each test.
     */
    @Before
    public void setUp() {
        health = new Health("Health Potion", "Restores health", 15.0, 30, 50);
    }


    /**
     * Tests the constructor and getters of the Health class.
     */
    @Test
    public void testConstructorAndGetter() {
        assertEquals("Health Potion", health.getName());
        assertEquals("Restores health", health.getDescription());
        assertEquals(15.0, health.getPrice(), 0.01);
        assertEquals(30, health.getQuantity());
        assertEquals(50, health.getHealAmount());
    }

    /**
     * Tests the setHealAmount method of the Health class.
     */
    @Test
    public void testSetHealAmount() {
        health.setHealAmount(60);
        assertEquals(60, health.getHealAmount());
    }
}
