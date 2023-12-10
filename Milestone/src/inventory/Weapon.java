package inventory;

/**
 * Represents a weapon, extending the SalableProduct class and implementing the Comparable interface
 * for sorting. Weapons have an additional attribute for damage, indicating their offensive capability.
 */
public class Weapon extends SalableProduct {
    private int damage; // Additional attribute specific to weapons

    /**
     * Constructs a Weapon object with specified attributes.
     *
     * @param name        The name of the weapon.
     * @param description The description of the weapon.
     * @param price       The price of the weapon.
     * @param quantity    The available quantity of the weapon.
     * @param damage      The damage value of the weapon.
     */
    public Weapon(String name, String description, double price, int quantity, int damage) {
        super(name, description, price, quantity);
        this.damage = damage;
    }

    /**
     * Gets the damage value of the weapon.
     *
     * @return The damage value.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Sets the damage value of the weapon.
     *
     * @param damage The damage value to set.
     */
    public void setDamage(int damage) {
        this.damage = damage;    }

    
}
