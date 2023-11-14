package app;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents the inventory in our store. Using a list to manage 
 * SalableProduct objects.
 */
public class InventoryManager {
    private List<SalableProduct> products;
    
    /**
     * Inventory manager class is the manger of all products in the store.
     * We can add new items in our store in this class.
     */
    public InventoryManager(){
        this.products = new ArrayList<>();

        products.add(new SalableProduct("King's Sword", "Long, Sharpe Blade originally welded for the King", 35.0, 35));
        products.add(new SalableProduct("Majestic Bow and Arrow", "Curved wooden bow and a feather arrow " +
                "with a sharp point", 40.0, 15));
        products.add(new SalableProduct("Stun rifle", "Rifle that was brought from the future that launches " + 
                "electric shocked bullet projectiles", 200.0, 5));
        products.add(new SalableProduct("Armoured Shield", "Reinforced steel shield ", 25.0, 75));
        products.add(new SalableProduct("Dragon Skin Body Amour", "Body suit armour made from the skin " + 
        		"of a slayed dragon", 85.0, 100));
        products.add(new SalableProduct("Magic Juice", "Delicious drink that replenishes you to" +
                "full health", 20.0, 100));
        products.add(new SalableProduct("Senzu Beans", "Mystical beans that replensish your energy", 12.0, 500));
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
        return null; // if no product found with the given name
    }

}
