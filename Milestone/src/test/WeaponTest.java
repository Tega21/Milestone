package test;

import inventory.Weapon;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class tests the Weapon class, focusing on its constructor,
 * getters, setters, and any additional logic that may be implemented.
 */
public class WeaponTest {
    private Weapon weapon;

    /**
     * Sets up a Weapon object before each test.
     */
    @Before
    public void setUp() {
        weapon = new Weapon("Sword", "Sharp blade", 100.0, 5, 40);
    }

    /**
     * Tests the constructor and getters of the Weapon class.
     */
    @Test
    public void testConstructorAndGetter() {
        assertEquals("Sword", weapon.getName());
        assertEquals("Sharp blade", weapon.getDescription());
        assertEquals(100.0, weapon.getPrice(), 0.01);
        assertEquals(5, weapon.getQuantity());
        assertEquals(40, weapon.getDamage());
    }

    /**
     * Tests the setDamage method of the Weapon class.
     */
    @Test
    public void testSetDamage() {
        weapon.setDamage(45);
        assertEquals(45, weapon.getDamage());
    }

}
