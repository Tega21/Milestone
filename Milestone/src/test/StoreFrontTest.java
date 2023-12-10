package test;

import app.StoreFront;
import app.InventoryManager;
import app.ShoppingCart;
import app.FileService;
import inventory.SalableProduct;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

/**
 * This class tests the functionality of the StoreFront class.
 * It covers adding and removing products from the shopping cart, and the checkout process.
 */
public class StoreFrontTest {

    private StoreFront storeFront;
    private InventoryManager inventoryManager;
    private ShoppingCart shoppingCart;

    /**
     * Sets up the testing environment before each test.
     * Initializes the StoreFront, InventoryManager, and ShoppingCart with a mock FileService.
     */
    @Before
    public void setUp() throws IOException {
        // Mock the FileService and initialize InventoryManager and ShoppingCart
        FileService fileService = new FileService("test_inventory.json"); // Use a test file or mock the FileService
        inventoryManager = new InventoryManager(fileService);
        shoppingCart = new ShoppingCart();

        // Create a test environment for StoreFront
        storeFront = new StoreFront(inventoryManager, shoppingCart);
    }

    /**
     * Tests the ability to add a product to the shopping cart in StoreFront.
     * Ensures that the product is successfully added to the cart.
     */
    @Test
    public void testAddProductToCart() {    	
    	    // Add a test product to InventoryManager for testing
    	    String productName = "TestProduct";
    	    int quantity = 5;
    	    inventoryManager.addProduct(productName, quantity);

    	    // Assuming the product is added to the first position in the inventory
    	    boolean result = storeFront.addProductToCart(1, 2); // Trying to add 2 quantities of the product to the cart

    	    // Check if the product was added successfully
    	    Assert.assertTrue("Product should be added successfully", result);
    }

    /**
     * Tests the ability to remove a product from the shopping cart in StoreFront.
     * Ensures that the product is successfully removed from the cart.
     */
    @Test
    public void testRemoveProductFromCart() {
        // Add a test product to the cart
        String productName = "TestProduct";
        double price = 10.0;
        int quantity = 5;
        SalableProduct testProduct = new SalableProduct(productName, "Description", price, quantity);
        shoppingCart.addProduct(testProduct, 3);

        // Try removing the product from the cart
        boolean result = storeFront.removeProductFromCart(1); // Assuming the product is at position 1

        // Check if the product was removed successfully
        Assert.assertTrue("Product should be removed successfully", result);

        // Additional assertions can be added to check the cart's state
    }

    // Additional helper methods or setup for more comprehensive testing can be added


    /**
     * Tests the checkout process in StoreFront.
     * Ensures that the product quantity in inventory is reduced after checkout.
     */
    @Test
    public void testCheckout() {
        // Add a product to the inventory
        String productName = "TestProduct";
        double price = 10.0;
        int initialQuantity = 10;
        SalableProduct testProduct = new SalableProduct(productName, "Description", price, initialQuantity);
        inventoryManager.addProduct(productName, initialQuantity);

        // Add the product to the shopping cart
        int quantityToAddToCart = 3;
        shoppingCart.addProduct(testProduct, quantityToAddToCart);

        // Perform checkout
        storeFront.checkout();

        // Get the updated product from inventory
        SalableProduct updatedProduct = inventoryManager.getProductByName(productName);

        // Assert that the product quantity in inventory is reduced
        int expectedQuantityAfterCheckout = initialQuantity - quantityToAddToCart;
        assertEquals("Product quantity should be reduced in inventory after checkout", 
                     expectedQuantityAfterCheckout, 
                     updatedProduct.getQuantity());
    }



    /**
     * A testable version of StoreFront for more controlled testing scenarios.
     */
class TestableStoreFront extends StoreFront {
    public TestableStoreFront(InventoryManager inventoryManager, ShoppingCart shoppingCart) throws IOException {
        super(inventoryManager, shoppingCart);
    	}
	}
}
