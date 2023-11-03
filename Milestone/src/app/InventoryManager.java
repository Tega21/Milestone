package app;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private List<SalableProduct> products;

    public InventoryManager(){
        this.products = new ArrayList<>();

        products.add(new SalableProduct("Sword", "Long, Sharpe Blade", 50.0, 35));
        products.add(new SalableProduct("Bow and Arrow", "Curved wooden bow and a feather arrow " +
                "with a sharp point", 150.0, 15));
        products.add(new SalableProduct("Shield", "Reinforced steel shield ", 30.0, 75));
        products.add(new SalableProduct("Magic Juice", "Delicious drink that replenishes you to" +
                "full health", 20.0, 100));
    }

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
