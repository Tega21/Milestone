package app;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The AdminService class represents a server-side component that listens for
 * administrative commands and processes them accordingly.
 */
public class AdminService implements Runnable {
    private ServerSocket serverSocket;
    private boolean running;
    private final InventoryManager inventoryManager;

    /**
     * Constructs a new AdminService instance associated with an InventoryManager and listening on a specific port.
     *
     * @param inventoryManager The InventoryManager instance to interact with.
     * @param port             The port number on which to listen for incoming connections.
     * @throws IOException If an I/O error occurs when opening the server socket.
     */
    public AdminService(InventoryManager inventoryManager, int port) throws IOException {
        this.inventoryManager = inventoryManager;
        serverSocket = new ServerSocket(port);
        running = true;
    }

    /**
     * The run method of the runnable interface. It listens for client connections and processes incoming commands.
     * U to update the JSON file and R to retrieve it
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try (Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                String inputLine;
                while ((inputLine = in.readLine()) != null) { // Listen for commands
                    if ("U".equalsIgnoreCase(inputLine)) {
                        StringBuilder jsonBuilder = new StringBuilder();
                        String line;
                        while (!(line = in.readLine()).equals("END")) {
                            jsonBuilder.append(line);
                        }
                        String productJson = jsonBuilder.toString();

                        // Process the received JSON to update inventory
                        try {
                            inventoryManager.updateInventoryFromJson(productJson); 
                            out.println("Product added successfully");
                        } catch (Exception e) {
                            out.println("Error in updating product: " + e.getMessage());
                        }

                    } else if ("R".equalsIgnoreCase(inputLine)) {
                        // Handle the "R" command
                        try {
                            String inventoryJson = inventoryManager.getInventoryAsJson();
                            out.println(inventoryJson);
                        } catch (Exception e) {
                            out.println("Error in retrieving inventory: " + e.getMessage());
                        }
                    }
                }
            } catch (IOException e) {
                if (!running) break;
                System.err.println("Error handling client connection: " + e.getMessage());
            }
        }
    }




    /**
     * Stops the admin service by closing the server socket and setting the running flag to false.
     *
     * @throws IOException If an I/O error occurs when closing the server socket.
     */
    
    public void stop() throws IOException {
        running = false;
        serverSocket.close();
    }

}