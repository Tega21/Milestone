	package app;

    import java.util.List;
    import java.util.Scanner;

    public class StoreFront {
        private final InventoryManager inventoryManager;
        private final ShoppingCart shoppingCart;

        public StoreFront() {
            inventoryManager = new InventoryManager();
            shoppingCart = new ShoppingCart();
        }

        public void displayMenu() {
            System.out.println("\nWelcome to the Arena Store!");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. Remove Product from Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Checkout");
            System.out.println("0. Exit");
        }

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
                        displayProducts();
                        break;
                    case 2:
                        addProductToCart(scnr);
                        break;
                    case 3:
                        removeProductFromCart(scnr);
                        break;
                    case 4:
                        displayCart();
                        break;
                    case 5:
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

        private void displayProducts() {
            List<SalableProduct> products = inventoryManager.getProducts();
            System.out.println("\nAvailable Products:");
            for (SalableProduct product : products) {
                System.out.println(product);
            }
        }

        private void addProductToCart(Scanner scanner) {
            displayProducts();
            System.out.print("\nEnter the name of the product you want to add to the cart: ");
            String name = scanner.nextLine();
            SalableProduct product = inventoryManager.getProductByName(name);
            if (product != null) {
                System.out.print("Enter the quantity: ");
                int quantity = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                shoppingCart.addProduct(product, quantity);
                System.out.println("Added " + quantity + " " + name + "(s) to the cart.");
            } else {
                System.out.println("Product not found!");
            }
        }

        private void removeProductFromCart(Scanner scanner) {
            System.out.print("\nEnter the name of the product you want to remove from the cart: ");
            String name = scanner.nextLine();
            SalableProduct product = shoppingCart.getProductByName(name);
            if (product != null) {
                shoppingCart.removeProduct(product);
                System.out.println("Removed " + name + " from the cart.");
            } else {
                System.out.println("Product not found in the cart!");
            }
        }

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

        // Main method to run the StoreFront application.
        public static void main(String[] args) {
            StoreFront storeFront = new StoreFront();
            storeFront.run();
        }
    }
