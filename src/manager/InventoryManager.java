package manager;

import model.Product;
import java.util.*;
import java.io.*;

/**
 * Manages inventory using ArrayList for O(1) random access
 * From your Grocery assignment
 */
public class InventoryManager {
    private ArrayList<Product> inventory;
    private static final String FILE_NAME = "database/inventory.txt";
    
    public InventoryManager() {
        inventory = new ArrayList<>();
    }
    
    /**
     * Loads inventory from text file
     * Format: ID,Name,Price,Stock
     */
    public void loadFromFile() throws IOException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            createSampleInventory();
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            inventory.clear();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    double price = Double.parseDouble(parts[2].trim());
                    int stock = Integer.parseInt(parts[3].trim());
                    inventory.add(new Product(id, name, price, stock));
                }
            }
        }
        System.out.println("Inventory loaded from file. " + inventory.size() + " products found.");
    }
    
    private void createSampleInventory() throws IOException {
        inventory.add(new Product(101, "Margherita Pizza", 12.99, 10));
        inventory.add(new Product(102, "Pepperoni Pizza", 14.99, 8));
        inventory.add(new Product(103, "Caesar Salad", 8.99, 15));
        inventory.add(new Product(104, "Garlic Bread", 4.99, 20));
        inventory.add(new Product(105, "Chocolate Cake", 6.99, 12));
        inventory.add(new Product(106, "Coke", 2.99, 50));
        inventory.add(new Product(107, "Sprite", 2.99, 45));
        inventory.add(new Product(108, "French Fries", 5.99, 30));
        saveToFile();
    }
    
    /**
     * Saves inventory back to file
     */
    public void saveToFile() throws IOException {
        File dir = new File("database");
        if (!dir.exists()) {
            dir.mkdir();
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Product p : inventory) {
                writer.write(p.getId() + "," + p.getName() + "," + 
                           p.getPrice() + "," + p.getStockQuantity());
                writer.newLine();
            }
        }
        System.out.println("Inventory saved to file.");
    }
    
    /**
     * Adds new product (no duplicate IDs)
     */
    public boolean addProduct(Product product) {
        if (searchById(product.getId()) != null) {
            System.out.println("Error: Product ID " + product.getId() + " already exists.");
            return false;
        }
        inventory.add(product);
        System.out.println("Product added successfully.");
        return true;
    }
    
    /**
     * Removes product by ID
     */
    public boolean removeProduct(int id) {
        Product product = searchById(id);
        if (product != null) {
            inventory.remove(product);
            System.out.println("Product removed successfully.");
            return true;
        }
        System.out.println("Product not found.");
        return false;
    }
    
    /**
     * Searches product by ID - O(1) with ArrayList
     */
    public Product searchById(int id) {
        for (Product p : inventory) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    
    /**
     * Searches products by name (case-insensitive, partial match)
     */
    public ArrayList<Product> searchByName(String name) {
        ArrayList<Product> results = new ArrayList<>();
        for (Product p : inventory) {
            if (p.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(p);
            }
        }
        return results;
    }
    
    /**
     * Updates stock quantity
     */
    public boolean updateStock(int id, int newStock) {
        Product product = searchById(id);
        if (product != null && newStock >= 0) {
            product.setStockQuantity(newStock);
            System.out.println("Stock updated.");
            return true;
        }
        System.out.println("Invalid product ID or stock quantity.");
        return false;
    }
    
    /**
     * Checks if product is available in requested quantity
     */
    public boolean isAvailable(int id, int requestedQty) {
        Product product = searchById(id);
        return product != null && product.getStockQuantity() >= requestedQty;
    }
    
    /**
     * Reduces stock temporarily (when adding to cart)
     */
    public boolean reduceStockTemporarily(int id, int quantity) {
        Product product = searchById(id);
        if (product != null && product.isAvailable(quantity)) {
            product.reduceStock(quantity);
            return true;
        }
        return false;
    }
    
    /**
     * Restores stock (when removing from cart or undo)
     */
    public void restoreStock(int id, int quantity) {
        Product product = searchById(id);
        if (product != null) {
            product.restoreStock(quantity);
        }
    }
    
    /**
     * Displays all products in table format
     */
    public void displayAll() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.printf("%-5s %-25s %-10s %-10s\n", 
                         "ID", "Name", "Price", "Stock");
        System.out.println("-".repeat(60));
        for (Product p : inventory) {
            System.out.printf("%-5d %-25s $%-9.2f %-10d\n",
                             p.getId(), p.getName(), p.getPrice(), p.getStockQuantity());
        }
        System.out.println("=".repeat(60));
    }
    
    public ArrayList<Product> getAllProducts() {
        return inventory;
    }
}