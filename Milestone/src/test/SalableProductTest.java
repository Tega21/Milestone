package test;

import static org.junit.Assert.*;
import org.junit.Test;
import inventory.SalableProduct;

/**
 * This class tests the functionality of the SalableProduct class.
 * It includes tests for setting and getting the name, description,
 * price, and quantity of a product.
 */
public class SalableProductTest {

    /**
     * Test the setName and getName methods of SalableProduct.
     * Ensures that the name set using setName is correctly
     * retrieved using getName.
     */
    @Test
    public void testSetNameAndGetName() {
        SalableProduct product = new SalableProduct();
        String expectedName = "Test Product";
        product.setName(expectedName);
        assertEquals(expectedName, product.getName());
    }

    /**
     * Test the setDescription and getDescription methods of SalableProduct.
     * Ensures that the description set using setDescription is correctly
     * retrieved using getDescription.
     */
    @Test
    public void testSetDescriptionAndGetDescription() {
        SalableProduct product = new SalableProduct();
        String expectedDescription = "Test Description";
        product.setDescription(expectedDescription);
        assertEquals(expectedDescription, product.getDescription());
    }

    /**
     * Test the setPrice and getPrice methods of SalableProduct.
     * Ensures that the price set using setPrice is correctly
     * retrieved using getPrice.
     */
    @Test
    public void testSetPriceAndGetPrice() {
        SalableProduct product = new SalableProduct();
        double expectedPrice = 99.99;
        product.setPrice(expectedPrice);
        assertEquals(expectedPrice, product.getPrice(), 0.0);
    }

    /**
     * Test the setQuantity and getQuantity methods of SalableProduct.
     * Ensures that the quantity set using setQuantity is correctly
     * retrieved using getQuantity.
     */
    @Test
    public void testSetQuantityAndGetQuantity() {
        SalableProduct product = new SalableProduct();
        int expectedQuantity = 10;
        product.setQuantity(expectedQuantity);
        assertEquals(expectedQuantity, product.getQuantity());
    }
}
