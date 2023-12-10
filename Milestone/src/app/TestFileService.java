package app;

import java.util.ArrayList;
import java.util.List;
import inventory.SalableProduct;

public class TestFileService extends FileService {
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
