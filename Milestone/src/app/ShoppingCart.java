package app;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<SalableProduct> items;

    public ShoppingCart(){
        this.items = new ArrayList<>();
    }

    /**
     * Adds a product to the cart with the desired quantity.
     * If prodcut already exists, it will update the quantity in the cart
     *
     * @param product The product to add
     * @param quantity The quantity of the product
     */
    public void addProduct(SalableProduct product, int quantity){
        for (int i = 0; i < quantity; i++){
            items.add(product);
        }
    }

    /**
     * Removes all instances of a product from the shopping cart
     *
     * @param product The product to remove
     */
    public void removeProduct(SalableProduct product){
        items.removeIf(p -> p.getName().equals(product.getName()));
    }

    /**
     * Gets the desired items in the cart
     *
     * @return the items in the cart
     */
    public List<SalableProduct> getItems(){
        return new ArrayList<>(items); // Returns a copy to prevent external modification
    }

    /**
     * This calculates the total price of all the items in the cart
     *
     * @return the total price
     */
    public double getTotal(){
        double totalPrice = 0.0;

        //iterate through all items in the shopping cart
        for (int i = 0; i < items.size(); i++){
            // Access the item at the current index
            SalableProduct item = items.get(i);
            // Finally, add the item's price to the totalPrice
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

    /**
     * Retrieves a product by its name from the cart.
     *
     * @param name the name of the product to retrieve
     * @return the product if found in the cart, null otherwise
     */
    public SalableProduct getProductByName(String name) {
        for (SalableProduct product : items) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null; // if no product found with the given name in the cart
    }

    public void clear() {
        items.clear();
    }
}
