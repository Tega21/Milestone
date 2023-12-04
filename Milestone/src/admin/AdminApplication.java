package admin;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

import inventory.SalableProduct;

/**
 * The AdminApplication class represents a client-side console application for sending
 * administrative commands to the StoreFront application.
 */
public class AdminApplication {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    /**
     * Constructs a new AdminApplication instance that connects to a specified host and port.
     *
     * @param host The hostname or IP address of the server.
     * @param port The port number of the server.
     * @throws IOException If an I/O error occurs when creating the socket.
     */
    public AdminApplication(String host, int port) throws IOException {
        socket = new Socket(host, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    /**
     * Sends a command to the server.
     *
     * @param command The command to be sent.
     */
    public void sendCommand(String command) {
        out.println(command);
    }

    /**
     * Receives a response from the server.
     *
     * @return The response received from the server.
     * @throws IOException If an I/O error occurs when reading from the input stream.
     */
    public String receiveResponse() throws IOException {
        return in.readLine();
    }

    /**
     * Closes the client socket and associated streams.
     *
     * @throws IOException If an I/O error occurs when closing the socket or streams.
     */
    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

    /**
     * The main method that starts the AdminApplication.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
    	 try (Scanner scanner = new Scanner(System.in)) {
    	        AdminApplication adminApp = new AdminApplication("localhost", 9999);

    	        System.out.println("Enter command (U for update, R for retrieve): ");
    	        String command = scanner.nextLine();

    	        if ("U".equalsIgnoreCase(command)) {
    	            System.out.println("Enter product details (Name, Description, Price, Quantity):");
    	            String name = scanner.nextLine();
    	            String description = scanner.nextLine();
    	            double price = Double.parseDouble(scanner.nextLine());
    	            int quantity = Integer.parseInt(scanner.nextLine());

    	            SalableProduct newProduct = new SalableProduct(name, description, price, quantity);
    	            String jsonPayload = new ObjectMapper().writeValueAsString(newProduct);

    	            adminApp.sendCommand("U");
    	            adminApp.sendCommand(jsonPayload); // Send JSON payload
    	        } else if ("R".equalsIgnoreCase(command)) {
    	            adminApp.sendCommand(command);
    	        }

    	        String response = adminApp.receiveResponse();
    	        System.out.println("Response from store: " + response);

    	        adminApp.close();
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    	}
}
