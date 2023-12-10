package test;

import app.InventoryManager;
import app.TestFileService;
import inventory.SalableProduct;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * This class tests the functionality of the InventoryManager class.
 * It includes tests for adding and removing products from the inventory.
 */
public class InventoryManagerTest {
    private InventoryManager inventoryManager;
    private TestFileService testFileService;

    /**
     * Sets up the testing environment before each test.
     * Initializes the TestFileService and InventoryManager.
     */
    @Before
    public void setUp() {
        testFileService = new TestFileService();
        inventoryManager = new InventoryManager(testFileService);
    }

    /**
     * Tests the addProduct method of InventoryManager.
     * Ensures that a product added to the inventory is not null and has the correct quantity.
     */
    @Test
    public void testAddProduct() {
        String testName = "TestProduct";
        int testQuantity = 5;

        inventoryManager.addProduct(testName, testQuantity);
        SalableProduct addedProduct = inventoryManager.getProductByName(testName);

        assertNotNull("Added product should not be null", addedProduct);
        assertEquals("Quantity of added product should match", testQuantity, addedProduct.getQuantity());
    }

    /**
     * Tests the removeProduct method of InventoryManager.
     * Ensures that after removing a certain quantity of a product, the remaining quantity is updated correctly.
     */
    @Test
    public void testRemoveProduct() {
        String testName = "ExistingProduct";
        int startingQuantity = 10;
        int quantityToRemove = 5;

        // Manually add a product to the inventory
        SalableProduct testProduct = new SalableProduct(testName, "Description", 10.0, startingQuantity);
        List<SalableProduct> productList = new ArrayList<>();
        productList.add(testProduct);
        

        // Remove the product
        inventoryManager.removeProduct(testName, quantityToRemove);

        // Retrieve the product and check its quantity
        SalableProduct updatedProduct = inventoryManager.getProductByName(testName);
        assertNotNull("Product should exist after removal", updatedProduct);
        assertEquals("Quantity should be updated correctly", startingQuantity - quantityToRemove, updatedProduct.getQuantity());
    }
}
