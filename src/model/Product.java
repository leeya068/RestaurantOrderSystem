package model;

/**
 * Product class for inventory management
 * Similar to Product class in your Grocery assignment
 */
public class Product {
    private int id;
    private String name;
    private double price;
    private int stockQuantity;
    
    public Product(int id, String name, double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
    
    public void reduceStock(int quantity) {
        if (quantity <= stockQuantity) {
            stockQuantity -= quantity;
        }
    }
    
    public void restoreStock(int quantity) {
        stockQuantity += quantity;
    }
    
    public boolean isAvailable(int requestedQuantity) {
        return stockQuantity >= requestedQuantity;
    }
    
    @Override
    public String toString() {
        return String.format("%-5d %-25s $%-8.2f %d units", 
                            id, name, price, stockQuantity);
    }
}