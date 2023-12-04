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

                String inputLine = in.readLine();
                if ("U".equalsIgnoreCase(inputLine)) {
                    String productJson = in.readLine(); // Read product JSON
                    inventoryManager.updateInventoryFromJson(productJson); // Update inventory
                    out.println("Product added successfully");
                } else if ("R".equalsIgnoreCase(inputLine)) {
                    String inventoryJson = inventoryManager.getInventoryAsJson();
                    out.println(inventoryJson);
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