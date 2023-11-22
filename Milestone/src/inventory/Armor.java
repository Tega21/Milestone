package inventory;
/**
 * Represents a type of armor, extending the SalableProduct class. Armor includes an additional
 * defense attribute, indicating its defensive strength.
 */
public class Armor extends SalableProduct {
    private int defense;

    /**
     * Constructs an Armor object with specified attributes.
     *
     * @param name        The name of the armor.
     * @param description The description of the armor.
     * @param price       The price of the armor.
     * @param quantity    The available quantity of the armor.
     * @param defense     The defense value of the armor.
     */
    public Armor(String name, String description, double price, int quantity, int defense) {
        super(name, description, price, quantity);
        this.defense = defense;
    }

    /**
     * Gets the defense value of the armor.
     *
     * @return The defense value.
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Sets the defense value of the armor.
     *
     * @param defense The defense value to set.
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

	
}
