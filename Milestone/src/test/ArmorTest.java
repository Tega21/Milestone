package test;

import inventory.Armor;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class tests the Armor class, ensuring that its constructor,
 * getters, and setters function correctly.
 */
public class ArmorTest {
    private Armor armor;

    /**
     * Sets up an Armor object before each test.
     */
    @Before
    public void setUp() {
        armor = new Armor("Iron Armor", "Provides strong defense", 50.0, 10, 20);
    }

    /**
     * Tests the constructor and getters of the Armor class.
     */
    @Test
    public void testConstructorAndGetter() {
        assertEquals("Iron Armor", armor.getName());
        assertEquals("Provides strong defense", armor.getDescription());
        assertEquals(50.0, armor.getPrice(), 0.01);
        assertEquals(10, armor.getQuantity());
        assertEquals(20, armor.getDefense());
    }

    /**
     * Tests the setDefense method of the Armor class.
     */
    @Test
    public void testSetDefense() {
        armor.setDefense(25);
        assertEquals(25, armor.getDefense());
    }
}