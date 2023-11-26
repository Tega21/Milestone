package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
    @SuppressWarnings("exports")
	public List<SalableProduct> getProducts(){
        return products;
    }

    /**
     * Retrieves a product by its name.
     *
     * @param name the name of the product to retrieve
     * @return the product if found, null otherwise
     */
    @SuppressWarnings("exports")
	public SalableProduct getProductByName(String name) {
        for (SalableProduct product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null; // if no product found with the given name
    }

    /**
     * Removes a specified quantity of a product from the inventory. If the product exists and the
     * available quantity is sufficient, it reduces the quantity of the product in stock. If the product
     * does not exist or there isn't enough quantity, it outputs an appropriate message.
     *
     * @param name The name of the product to be removed.
     * @param quantity The quantity of the product to be removed.
     */
    public void removeProduct(String name, int quantity) {
        SalableProduct product = getProductByName(name);
        if (product != null && product.getQuantity() >= quantity) {
            product.setQuantity(product.getQuantity() - quantity);
        } else {
            System.out.println("Not enough quantity in stock or product not found.");
        }
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
            product.setQuantity(product.getQuantity() + quantity);
        }
    }
    
    public void sortProductsByNameAscending() {
        Collections.sort(products);
    }

    public void sortProductsByNameDescending() {
        products.sort(Collections.reverseOrder());
    }

    public void sortProductsByPriceAscending() {
        products.sort(Comparator.comparing(SalableProduct::getPrice));
    }

    public void sortProductsByPriceDescending() {
        products.sort(Comparator.comparing(SalableProduct::getPrice).reversed());
    }
}
