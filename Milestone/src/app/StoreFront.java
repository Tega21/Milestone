// Brandon Ortega// CST-239// 11.05.2023// This is my own work

package app;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Map;

import inventory.SalableProduct;

/**
 * This class is responsible for user interface and its purpose is for the user
 * to interact with the store to buy items, return items, and even just see the
 * description of any product. They can then view their shopping cart and finalize
 * all of their purchases.
 *
 * @author Brandon Ortega
 *
 */

public class StoreFront {
    private final InventoryManager inventoryManager;
    private final ShoppingCart shoppingCart;
    private final AdminService adminService;
    private final Thread adminThread;

    /**
     * Constructor initializes the StoreFront with a new InventoryManager and ShoppingCart.
     *
     * UPDATED to use the File Service and also have the name of the JSON file that we
     * will be using for our inventory manager.
     */
    public StoreFront() throws IOException {
        FileService fileService = new FileService("inventory.json");
        inventoryManager = new InventoryManager(fileService);
        shoppingCart = new ShoppingCart();
        adminService = new AdminService(inventoryManager, 9999);

        adminThread = new Thread(adminService);
        adminThread.start();
    }
    
    /*
     * New constructor for testing
     */
    public StoreFront(InventoryManager inventoryManager, ShoppingCart shoppingCart) {
        this.inventoryManager = inventoryManager;
        this.shoppingCart = shoppingCart;
		this.adminService = null;
		this.adminThread = new Thread();
    }

    /**
     * Displays the main menu options to the console.
     */
    public void displayMenu() {
        System.out.println("\nWELCOME TO THE SMASH BROS ARENA STORE!!");
        System.out.println("1. View and Add Products");
        System.out.println("2. Remove Product from Cart");
        System.out.println("3. View Cart");
        System.out.println("4. Checkout");
        System.out.println("5. Return All Items in Cart");
        System.out.println("0. Exit");
    }

    /**
     * Main loop for store front operations. Handles user input and
     * calls the appropriate methods based on user choice.
     */
    public void run() {
        Scanner scnr = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scnr.nextInt();
            scnr.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    displayProductsAndAddToCart(scnr);
                    break;
                case 2:
                    removeProductFromCart(scnr);
                    break;
                case 3:
                    displayCart();
                    break;
                case 4:
                    checkout();
                    break;
                case 5:
                    clearCart(scnr);
                    break;
                case 0:
                    System.out.println("Exiting the Arena Store. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);
        scnr.close();
    }

    /**
     * Displays a list of all salable products to the user.
     * UPDATED
     * Method is now responsible for the displaying of all store's products, but now
     * asks the user the question of whether they want to purchase any of these items.
     * There is an option for the user to go back to the main menu if they decide not
     * to purchase anything.
     * ALSO, changes the interface by adding a number input rather than having the
     * user type out the name of the item.
     *
     * Updated to give the user the ability to filter through our products by name
     * or by price ascending or descending.
     */
    private void displayProductsAndAddToCart(Scanner scanner) {
        // Adding sorting options
        System.out.println("Sort products by: 1) Name Ascending 2) Name Descending 3) Price Ascending 4) Price Descending");
        int sortChoice = scanner.nextInt();
        switch(sortChoice) {
            case 1: inventoryManager.sortProductsByNameAscending(); break;
            case 2: inventoryManager.sortProductsByNameDescending(); break;
            case 3: inventoryManager.sortProductsByPriceAscending(); break;
            case 4: inventoryManager.sortProductsByPriceDescending(); break;
        }


        List<SalableProduct> products = inventoryManager.getProducts();
        if (products.isEmpty()) {
            System.out.println("There are no products available for purchase.");
            return;
        }

        System.out.println("\nAvailable Products:");
        for (int i = 0; i < products.size(); i++) {
            SalableProduct product = products.get(i);
            System.out.printf("%d - %s - %s ($%.2f) [Quantity: %d]\n",
                    i + 1, product.getName(), product.getDescription(), product.getPrice(), product.getQuantity());
        }

        System.out.println("Enter the number of the product you wish to add to your cart, or 0 to return:");
        int productNumber = scanner.nextInt();

        // Check if user wants to return to the previous menu
        if (productNumber == 0) {
            return;
        }

        // Validate the product number input
        if (productNumber < 1 || productNumber > products.size()) {
            System.out.println("Invalid product number. Please try again.");
            return;
        }

        SalableProduct selectedProduct = products.get(productNumber - 1);

        // Ask for the quantity to add to the cart
        System.out.print("Enter the quantity to add to your cart: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Here, add logic to check if the selected quantity is available

        shoppingCart.addProduct(selectedProduct, quantity);
        System.out.println("Added " + quantity + " of " + selectedProduct.getName() + " to the cart.");

    }
    
    /**
     * Logic for adding a product to the cart.
     *
     * @param productNumber The product number to add.
     * @param quantity The quantity to add.
     * @return true if added successfully, false otherwise.
     */
    public boolean addProductToCart(int productNumber, int quantity) {
        List<SalableProduct> products = inventoryManager.getProducts();
        if (productNumber < 1 || productNumber > products.size()) {
            return false;
        }
        SalableProduct selectedProduct = products.get(productNumber - 1);
        shoppingCart.addProduct(selectedProduct, quantity);
        return true;
    }


    /**
     * Allows a user to return a product, removing it from the shopping cart.
     * UPDATED
     * Method now allows the user to see their cart before removing items
     * from it. They will also be given a message of no items in the cart
     * if their cart is empty.
     */
    private void removeProductFromCart(Scanner scanner) {
        Map<SalableProduct, Integer> cartItems = shoppingCart.getItems();
        if (cartItems.isEmpty()) {
            System.out.println("\nYour cart is empty, nothing to remove.");
            return;
        }

        System.out.println("\nItems in your cart:");
        SalableProduct[] products = cartItems.keySet().toArray(new SalableProduct[0]);
        for (int i = 0; i < products.length; i++) {
            System.out.printf("%d - %s - Price: $%.2f - Quantity in Cart: %d\n",
                    i + 1, products[i].getName(), products[i].getPrice(), cartItems.get(products[i]));
        }

        System.out.println("Enter the number of the product you wish to remove from your cart, or 0 to cancel:");
        int itemNumber = scanner.nextInt();

        if (itemNumber == 0) {
            return;
        }

        if (itemNumber < 1 || itemNumber > products.length) {
            System.out.println("Invalid product number. Please try again.");
            return;
        }

        // Remove the selected item from the cart
        SalableProduct productToRemove = products[itemNumber - 1];
        shoppingCart.removeProduct(productToRemove);
        System.out.println("Removed " + productToRemove.getName() + " from the cart.");
    }
    
    /**
     * Logic for removing a product from the cart.
     *
     * @param itemNumber The item number to remove.
     * @return true if removed successfully, false otherwise.
     */
    public boolean removeProductFromCart(int itemNumber) {
        Map<SalableProduct, Integer> cartItems = shoppingCart.getItems();
        if (cartItems.isEmpty() || itemNumber < 1 || itemNumber > cartItems.size()) {
            return false;
        }
        SalableProduct[] products = cartItems.keySet().toArray(new SalableProduct[0]);
        SalableProduct productToRemove = products[itemNumber - 1];
        shoppingCart.removeProduct(productToRemove);
        return true;
    }

    /**
     * Processes the user to see their cart and all the items that
     * they have added to it. Will use the displayCartContents()
     * method from the ShoppingCart class
     */
    // Inside StoreFront class

    private void displayCart() {
        shoppingCart.displayCartContents();
    }

    private void clearCart(Scanner scnr) {
        System.out.println("Are you sure you want to empty your cart? (y/n)");
        String confirmation = scnr.nextLine().trim().toLowerCase();

        if (confirmation.equals("y")) {
            shoppingCart.clear();
            System.out.println("The cart has been emptied!");
        } else {
            System.out.println("Cart clearing cancelled.");
        }
    }

    /**
     * Processes the user's shopping cart and completes the purchase.
     * This might include calculating the total price and confirming the purchase.
     * UPDATED now inventory will update to the correct amount after 
     */
    public void checkout() {
        double total = shoppingCart.getTotal();
        if (total > 0) {
            System.out.println("\nChecking out. Total price: " + total);

            // Reduce the quantity in inventory
            for (Map.Entry<SalableProduct, Integer> entry : shoppingCart.getItems().entrySet()) {
                SalableProduct product = entry.getKey();
                int quantity = entry.getValue();
                inventoryManager.removeProduct(product.getName(), quantity);
            }

            shoppingCart.clear(); // Clear the cart after checkout
            System.out.println("Thank you for your purchase!");
        } else {
            System.out.println("\nYour cart is empty. Add some products before checkout.");
        }
    }

    /**
     * Main method runs the store application
     * @param args not used
     */
    public static void main(String[] args) {
        try {
            StoreFront storeFront = new StoreFront();
            storeFront.run();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
