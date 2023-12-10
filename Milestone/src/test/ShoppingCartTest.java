package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import app.ShoppingCart;
import inventory.SalableProduct;

public class ShoppingCartTest {

    private ShoppingCart cart;

    @Before
    public void setUp() {
        cart = new ShoppingCart();
    }

    @Test
    public void testAddProduct() {
        SalableProduct product = new SalableProduct("TestProduct", "Description", 10.0, 5);
        cart.addProduct(product, 1);
        assertEquals(1, (int) cart.getItems().get(product));
    }

    @Test
    public void testRemoveProduct() {
        SalableProduct product = new SalableProduct("TestProduct", "Description", 10.0, 5);
        cart.addProduct(product, 2);
        cart.removeProduct(product);
        assertEquals(1, (int) cart.getItems().get(product));
    }

    @Test
    public void testGetTotal() {
        SalableProduct product1 = new SalableProduct("Product1", "Description", 5.0, 5);
        SalableProduct product2 = new SalableProduct("Product2", "Description", 10.0, 5);
        cart.addProduct(product1, 1);
        cart.addProduct(product2, 1);
        assertEquals(15.0, cart.getTotal(), 0.001);
    }

    @Test
    public void testGetProductByName() {
        SalableProduct product = new SalableProduct("UniqueName", "Description", 10.0, 5);
        cart.addProduct(product, 1);
        assertNotNull(cart.getProductByName("UniqueName"));
    }
    
    @Test
    public void testClear() {
        SalableProduct product = new SalableProduct("TestProduct", "Description", 10.0, 5);
        cart.addProduct(product, 1);
        cart.clear();
        assertTrue(cart.getItems().isEmpty());
    }
}