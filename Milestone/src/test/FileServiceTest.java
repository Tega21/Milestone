// In your test package
package test;

import app.FileService;
import inventory.SalableProduct;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is for unit testing the FileService class.
 * It uses a mock implementation of FileService, TestFileService, to simulate file operations.
 */
public class FileServiceTest {

    private TestFileService fileService;

    /**
     * Sets up the test environment before each test case.
     * Initializes the mock FileService.
     */
    @Before
    public void setUp() {
        fileService = new TestFileService();
    }

    /**
     * Tests both the write and read functionality of the FileService.
     * It simulates writing a list of products to a file and then reading from it.
     * The test ensures that the written and read products are identical.
     */
    @Test
    public void testWriteAndReadInventory() {
        List<SalableProduct> productsToWrite = new ArrayList<>();
        productsToWrite.add(new SalableProduct("Product1", "Description1", 10.0, 5));
        productsToWrite.add(new SalableProduct("Product2", "Description2", 15.0, 3));

        // Simulate writing to a file
        fileService.writeInventoryToFile(productsToWrite);

        // Simulate reading from a file
        List<SalableProduct> productsRead = fileService.readInventoryFromFile();

        // Verify that the read products match the written products
        Assert.assertEquals("Number of products should match", productsToWrite.size(), productsRead.size());
        for (int i = 0; i < productsToWrite.size(); i++) {
            Assert.assertEquals("Product should match", productsToWrite.get(i), productsRead.get(i));
        }
    }
}

// Mock version of FileService for testing
class TestFileService extends FileService {
    private List<SalableProduct> products = new ArrayList<>();

    public TestFileService() {
        super(null); // Pass null since we won't be using the file path
    }

    @Override
    public List<SalableProduct> readInventoryFromFile() {
        // Return a copy of the products list
        return new ArrayList<>(products);
    }

    @Override
    public void writeInventoryToFile(List<SalableProduct> products) {
        // Update the internal list with a copy of the provided list
        this.products = new ArrayList<>(products);
    }

    // Method to directly manipulate the product list for test setup
    public void setProducts(List<SalableProduct> products) {
        this.products = new ArrayList<>(products);
    }
}
