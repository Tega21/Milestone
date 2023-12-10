package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import inventory.SalableProduct;

/**
 * Class represents the inventory in our store. USing a list to manage
 * SalableProduct objects.
 */
public class InventoryManager {
    private List<SalableProduct> products;
    private final FileService fileService;

    /**
     * Inventory manager class is the manger of all products in the store.
     * We can add new items in our store in this class.
     *
     * UPDATED by using the FileService class and also giving exception handling
     * to help if errors arise.
     */
    public InventoryManager(FileService fileService) {
        this.fileService = fileService;
        try {
            this.products = fileService.readInventoryFromFile();
        } catch (IOException e) {
            e.printStackTrace();
            this.products = new ArrayList<>(); // Fallback to empty list
        }
    }

    /**
     * Method to update the inventory
     */
    public void updateInventory() {
        try {
            fileService.writeInventoryToFile(products);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions, maybe log them or notify the user
        }
    }

    /**
     * Returns the list of all products in the inventory.
     *
     * @return A list containing all the products available in the inventory.
     */
    
    public List<SalableProduct> getProducts(){
        return products;
    }

    /**
     * Retrieves a product by its name.
     *
     * @param name the name of the product to retrieve
     * @return the product if found, null otherwise
     */
    
    public SalableProduct getProductByName(String name) {
        for (SalableProduct product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    /**
     * Removes a specified quantity of a product from the inventory. If the product exists and the
     * available quantity is sufficient, it reduces the quantity of the product in stock. If the product
     * does not exist or there isn't enough quantity, it outputs an appropriate message.
     *
     * @param name The name of the product to be removed.
     * @param quantity The quantity of the product to be removed.
     */
    public void removeProduct(String name, int quantityToRemove) {
        SalableProduct product = getProductByName(name);
        if (product == null) {
            System.out.println("Product not found: " + name);
            return;
        }
        if (product.getQuantity() < quantityToRemove) {
            System.out.println("Not enough quantity in stock for: " + name);
            return;
        }
        product.setQuantity(product.getQuantity() - quantityToRemove); 
        System.out.println("Reduced quantity for " + name + " to " + product.getQuantity());
        updateInventory(); 
    }


    /**
     * Adds a specified quantity of a product to the inventory. If the product exists, it increases the
     * quantity of the product in stock. If the product does not exist, no action is taken.
     *
     * @param name The name of the product to be added.
     * @param quantity The quantity of the product to be added.
     */
    public void addProduct(String name, int quantity) {
        SalableProduct product = getProductByName(name);
        if (product != null) {
            // Product exists, update its quantity
            product.setQuantity(product.getQuantity() + quantity);
        } else {
            // Product does not exist, create a new product and add it to the inventory
            product = new SalableProduct(name, "Default Description", 0.0, quantity); // Adjust as needed
            products.add(product);
        }

        // Update the inventory file
        updateInventory();
    }

    /**
     * Sorts the list of products by name in ascending order.
     * It uses the natural ordering of the products, which is defined by the compareTo method
     * implemented in the SalableProduct class.
     */
    public void sortProductsByNameAscending() {
        Collections.sort(products);
    }

    /**
     * Sorts the list of products by name in descending order.
     * This is achieved by using the reverseOrder comparator along with the natural ordering.
     */
    public void sortProductsByNameDescending() {
        products.sort(Collections.reverseOrder());
    }

    /**
     * Sorts the list of products by price in ascending order.
     * It uses a comparator that compares products based on their price attribute.
     */
    public void sortProductsByPriceAscending() {
        products.sort(Comparator.comparing(SalableProduct::getPrice));
    }

    /**
     * Sorts the list of products by price in descending order.
     * It uses a comparator that compares products based on their price attribute in reverse order.
     */
    public void sortProductsByPriceDescending() {
        products.sort(Comparator.comparing(SalableProduct::getPrice).reversed());
    }

    public String getInventoryAsJson() throws JsonProcessingException{
        return new ObjectMapper().writeValueAsString(products);
    }

    public void updateInventoryFromJson(String json) throws IOException {
        SalableProduct newProduct = new ObjectMapper().readValue(json, SalableProduct.class);
        products.add(newProduct);
        updateInventory();
    }
    
    public void addProductFromJson(String json) throws IOException {
        SalableProduct newProduct = new ObjectMapper().readValue(json, SalableProduct.class);
        // Add newProduct to the products list
        products.add(newProduct);
        fileService.writeInventoryToFile(products); // Update JSON file
    }
}
