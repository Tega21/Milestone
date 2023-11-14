	// Brandon Ortega// CST-239// 11.05.2023// This is my own work

	package app;

    import java.util.List;
    import java.util.Scanner;
    
    /**
     * This class is responsible for user interface and its purpose is for the user
     * to interact with the store to buy items, return items, and even just see the 
     * description of any product. They can then view their shopping cart and finalize 
     * all of their puirchases.
     * 
     * @author Brandon Ortega
     *
     */

    public class StoreFront {
        private final InventoryManager inventoryManager;
        private final ShoppingCart shoppingCart;
        
        /**
         * Constructor initializes the StoreFront with a new InventoryManager and ShoppingCart.
         */
        public StoreFront() {
            inventoryManager = new InventoryManager();
            shoppingCart = new ShoppingCart();
        }
        
        /**
         * Displays the main menu options to the console.
         */
        public void displayMenu() {
            System.out.println("\nWELCOME TO THE SMASH BROS ARENA STORE!!");
            System.out.println("1. View Products");           
            System.out.println("2. Remove Product from Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
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
         */
        private void displayProductsAndAddToCart(Scanner scanner) {
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

            // Adds logic to check if the selected quantity is available

            shoppingCart.addProduct(selectedProduct, quantity);
            System.out.println("Added " + quantity + " of " + selectedProduct.getName() + " to the cart.");
        }        

        
        /**
         * Allows a user to return a product, removing it from the shopping cart.
         * UPDATED
         * Method now allows the user to see their cart before removing items
         * from it. They will also be given a message of no items in the cart 
         * if their cart is empty.
         */
        private void removeProductFromCart(Scanner scanner) {
        	 List<SalableProduct> items = shoppingCart.getItems();
        	    
        	    if (items.isEmpty()) {
        	        System.out.println("\nYour cart is empty, nothing to remove.");
        	        return;
        	    }

        	    System.out.println("\nItems in your cart:");
        	    for (int i = 0; i < items.size(); i++) {
        	        SalableProduct item = items.get(i);
        	        System.out.printf("%d - %s - %s ($%.2f) [Quantity: %d]\n",
        	                i + 1, item.getName(), item.getDescription(), item.getPrice(), item.getQuantity());
        	    }

        	    System.out.println("Enter the number of the product you wish to remove from your cart, or 0 to cancel:");
        	    int itemNumber = scanner.nextInt();
        	    
        	    // Check if user wants to cancel the remove action
        	    if (itemNumber == 0) {
        	        return;
        	    }

        	    // Validate the product number input
        	    if (itemNumber < 1 || itemNumber > items.size()) {
        	        System.out.println("Invalid product number. Please try again.");
        	        return;
        	    }

        	    // Remove the selected item from the cart
        	    SalableProduct productToRemove = items.get(itemNumber - 1);
        	    shoppingCart.removeProduct(productToRemove);
        	    System.out.println("Removed " + productToRemove.getName() + " from the cart.");
        	}
        /**
         * Processes the user to see their cart and all the items that
         * they have added to it. 
         */
        private void displayCart() {
            List<SalableProduct> items = shoppingCart.getItems();
            if (items.isEmpty()) {
                System.out.println("\nYour cart is empty.");
            } else {
                System.out.println("\nItems in your cart:");
                for (SalableProduct item : items) {
                    System.out.println(item);
                }
                System.out.println("Total Price: " + shoppingCart.getTotal());
            }
        }
        
        /**
         * Processes the user's shopping cart and completes the purchase.
         * This might include calculating the total price and confirming the purchase.
         */
        private void checkout() {
            double total = shoppingCart.getTotal();
            if (total > 0) {
                System.out.println("\nChecking out. Total price: " + total);
                shoppingCart.clear();
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
            StoreFront storeFront = new StoreFront();
            storeFront.run();
        }
    }
