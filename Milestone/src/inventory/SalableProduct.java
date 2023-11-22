package inventory;

/**
 * 
 * @author Brandon Ortega
 * This class represents a product that is available for sale in the store
 */

public class SalableProduct implements Comparable<SalableProduct> {

	
 	private String name;
    private String description;
    private double price;
    private int quantity;

    /**
     * The constructor for SalableProduct with all fields
     * @param name name of products
     * @param description product description
     * @param price how much products costs
     * @param quantity the amount of the product we have in the store
     */

    /**
     * No argument constructor
     */
    public SalableProduct() {
    	
    }
    public SalableProduct(String name, String description, double price, int quantity){
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Gets the name of the product.
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the product.
     * @return the description of the product
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the product.
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the price of the product.
     * @return the price of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the quantity of the product in stock.
     * @return the quantity of the product
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product in stock.
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }

    /**
     * Provides a string representation of a SalableProduct.
     * @return a string representation of the product details
     */
    @Override
    public String toString() {
        return String.format("Name: %s - Description: %s - Price: $%.2f - Quantity: %d",
                name, description, price, quantity);
    }

	@Override
	public int compareTo(SalableProduct other) {
		return this.getName().compareToIgnoreCase(other.getName());
	}


}


