package app;

import java.util.Map;
import java.util.HashMap;

import inventory.SalableProduct;


/**
 * This class represents the user's shopping cart. It will add and
 * remove products from their cart.
 * 
 * UPDATED, now using a map to help with the new way to remove items from your cart 
 * one at a time instead of just clearing everything in your cart.
 */
public class ShoppingCart {
	// Map to hold products and their respective quantities
    private Map<SalableProduct, Integer> items;

    
    /**
     * Constructor for the ShoppingCart class. It initializes the internal map
     * to track the products and quantities.
     */
    public ShoppingCart(){   	 
    	this.items = new HashMap<>();
    }

    /**
     * Adds a product to the cart with the entered quantity.
     * If product already exists, it will update the quantity in the cart
     *
     * @param product The product to add
     * @param quantity The quantity of the product
     */
    public void addProduct(SalableProduct product, int quantity){
    	// Going to use getOrDefault here to handle cases where the product isn't already in the cart
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    /**
     * Removes a product from the shopping cart or decreases its quantity.
     *
     * @param product The product to remove from the cart
     */
    public void removeProduct(SalableProduct product) {
        // Checks to see if the product exists in the cart
        if (items.containsKey(product)) {
            int newQuantity = items.get(product) - 1;
            if (newQuantity > 0) {
                // If more than one quantity, reduce the quantity
                items.put(product, newQuantity);
            } else {
                // If only one quantity, remove the product from the cart
                items.remove(product);
            }
        }
    }

    /**
     * Returns a copy of the items in the shopping cart.
     * 
     * UPDATED! Now using a map
     *
     * @return A map of items in the cart with their quantities
     */
    public Map<SalableProduct, Integer> getItems() {
        return new HashMap<>(items);
    }

    /**
     * This calculates the total price of all the items in the cart
     *
     * @return the total price
     */
    public double getTotal() {
        // Stream over the entrySet of the map to calculate the total price
        return items.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    /**
     * Retrieves a product by its name from the cart.
     *
     * @param name the name of the product to retrieve
     * @return the product if found in the cart, null otherwise
     */
    public SalableProduct getProductByName(String name) {
        // Stream over the keys (products) to find a match by name
        return items.keySet().stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Displays the contents of the cart, showing each unique product
     * along with the total quantity in the cart.
     */
    public void displayCartContents() {
        if (items.isEmpty()) {
            System.out.println("\nYour cart is empty.");
        } else {
            System.out.println("\nItems in your cart:");
            // Iterate over the map entries to display each product and its quantity
            items.forEach((product, quantity) -> 
                System.out.printf("Name: %s - Description: %s - Price: $%.2f - Quantity in Cart: %d\n",
                    product.getName(), product.getDescription(), product.getPrice(), quantity));
            // Display the total price of the cart
            System.out.println("Total Price: " + getTotal());
        }
    }
    
    /**
     *  Method to clear all items in your cart
     */
    public void clear() {
    	items.clear();
    }
}