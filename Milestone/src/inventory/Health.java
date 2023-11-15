package inventory;

/**
 * Represents a health item, extending the SalableProduct class. This class includes an additional
 * attribute for the heal amount, representing the health recovery value of the item.
 */
public class Health extends SalableProduct {
    private int healAmount;

    /**
     * Constructs a Health object with specified attributes.
     *
     * @param name        The name of the health item.
     * @param description The description of the health item.
     * @param price       The price of the health item.
     * @param quantity    The available quantity of the health item.
     * @param healAmount  The amount of health recovery provided by the item.
     */
    public Health(String name, String description, double price, int quantity, int healAmount) {
        super(name, description, price, quantity);
        this.healAmount = healAmount;
    }

    /**
     * Gets the heal amount of the health item.
     *
     * @return The heal amount.
     */
    public int getHealAmount() {
        return healAmount;
    }

    /**
     * Sets the heal amount of the health item.
     *
     * @param healAmount The heal amount to set.
     */
    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }
}