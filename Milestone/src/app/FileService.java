package app;


import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import inventory.SalableProduct;

/**
 * FileService is responsible for handling file operations related to inventory.
 * This includes reading and writing SalableProduct data to and from a file.
 */
public class FileService {
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String filePath;
    
    /**
    * Constructs a new FileService object with a specified file path.
    *
    * @param filePath The path to the file where inventory data is stored.
    */
    public FileService(String filePath) {
        this.filePath = filePath;
    }
    
    /**
     * Reads the inventory data from a file and returns a list of SalableProduct.
     *
     * @return A list of SalableProduct read from the file.
     * @throws IOException If there is an issue reading the file.
     */
    public List<SalableProduct> readInventoryFromFile() throws IOException {
        return objectMapper.readValue(Paths.get(filePath).toFile(), new TypeReference<List<SalableProduct>>(){});
    }
    
    /**
     * Writes a list of SalableProduct to a file.
     *
     * @param products The list of SalableProduct to be written to the file.
     * @throws IOException If there is an issue writing to the file.
     */
    public void writeInventoryToFile(List<SalableProduct> products) throws IOException {
        objectMapper.writeValue(Paths.get(filePath).toFile(), products);
    }
}
