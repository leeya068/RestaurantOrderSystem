import model.*;
import datastructures.*;
import manager.*;
import java.util.*;

/**
 * Restaurant Order System - Complete Working Version
 * Combines Grocery Store + University Course Management assignments
 */
public class Main {
    private static InventoryManager inventoryManager;
    private static CartList shoppingCart;
    private static UndoStack undoStack;  // CHANGED: Stack -> UndoStack
    private static MenuManager<MenuItem> menuManager;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        shoppingCart = new CartList();
        undoStack = new UndoStack();  // CHANGED: Using UndoStack
        inventoryManager = new InventoryManager();
        menuManager = new MenuManager<>();
        
        try {
            // Load inventory from file (Grocery assignment)
            inventoryManager.loadFromFile();
            
            // Load menu items (University assignment)
            loadMenuItems();
            
            // Main program loop
            while (true) {
                displayMainMenu();
                int choice = getIntInput("Enter choice: ");
                
                switch (choice) {
                    case 1:
                        displayInventory();
                        break;
                    case 2:
                        displayMenu();
                        break;
                    case 3:
                        addToCart();
                        break;
                    case 4:
                        viewCart();
                        break;
                    case 5:
                        removeFromCart();
                        break;
                    case 6:
                        updateCartQuantity();
                        break;
                    case 7:
                        undoLastAddition();
                        break;
                    case 8:
                        clearCart();
                        break;
                    case 9:
                        checkout();
                        break;
                    case 10:
                        searchProduct();
                        break;
                    case 11:
                        addNewProduct();
                        break;
                    case 12:
                        System.out.println("Thank you for visiting!");
                        inventoryManager.saveToFile();
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void loadMenuItems() {
        // Adding items from University assignment pattern
        menuManager.addItem(new Appetizer("A001", "Spring Rolls", 5.99, false, 8));
        menuManager.addItem(new Appetizer("A002", "Spicy Wings", 7.99, true, 10));
        menuManager.addItem(new MainCourse("M001", "Grilled Chicken", 16.99, "Chicken", 20));
        menuManager.addItem(new MainCourse("M002", "Steak", 24.99, "Beef", 25));
        menuManager.addItem(new Dessert("D001", "Cheesecake", 6.99, false, 5));
        menuManager.addItem(new Dessert("D002", "Brownie", 5.99, true, 4));
        menuManager.addItem(new Beverage("B001", "Coffee", 3.99, false, 3));
        menuManager.addItem(new Beverage("B002", "Fresh Juice", 4.99, true, 4));
        
        System.out.println("Menu loaded with " + menuManager.getAllItems().size() + " items.");
    }
    
    private static void displayMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("     RESTAURANT ORDER SYSTEM");
        System.out.println("=".repeat(50));
        System.out.println("1.  View Inventory");
        System.out.println("2.  View Menu (with prep times)");
        System.out.println("3.  Add Item to Cart");
        System.out.println("4.  View Cart");
        System.out.println("5.  Remove Item from Cart");
        System.out.println("6.  Update Item Quantity");
        System.out.println("7.  Undo Last Addition");
        System.out.println("8.  Clear Cart");
        System.out.println("9.  Checkout & Generate Bill");
        System.out.println("10. Search Products");
        System.out.println("11. Add New Product (Staff)");
        System.out.println("12. Save & Exit");
        System.out.println("-".repeat(50));
    }
    
    private static void displayInventory() {
        System.out.println("\n--- CURRENT INVENTORY ---");
        inventoryManager.displayAll();
    }
    
    private static void displayMenu() {
        System.out.println("\n--- FULL MENU WITH PREPARATION TIMES ---");
        System.out.println("Legend: A=Appetizer, M=MainCourse, D=Dessert, B=Beverage");
        menuManager.printAllItems();
        
        // Demonstrate generic manager functionality (from University assignment)
        System.out.println("\n--- ANALYSIS (From University Assignment Pattern) ---");
        MenuItem longestPrep = menuManager.getItemWithLongestPrepTime();
        if (longestPrep != null) {
            System.out.println("Longest preparation time: " + longestPrep.getName() + 
                             " (" + longestPrep.calculatePreparationTime() + " min)");
        }
        
        menuManager.sortByPreparationTime();
        System.out.println("\nItems sorted by preparation time (fastest first):");
        menuManager.printAllItems();
    }
    
    private static void addToCart() {
        System.out.print("Enter Product ID to add: ");
        int id = getIntInput("");
        
        Product product = inventoryManager.searchById(id);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }
        
        System.out.print("Enter quantity: ");
        int quantity = getIntInput("");
        
        if (quantity <= 0) {
            System.out.println("Invalid quantity.");
            return;
        }
        
        if (inventoryManager.isAvailable(id, quantity)) {
            // Reduce stock temporarily
            inventoryManager.reduceStockTemporarily(id, quantity);
            
            // Add to cart (singly linked list)
            shoppingCart.addItem(product, quantity);
            
            // Push to undo stack (for undo feature)
            undoStack.push(product, quantity);  // CHANGED: Now using UndoStack
            
            System.out.printf("Added %d x %s to cart. Stock temporarily reduced.\n", 
                            quantity, product.getName());
        } else {
            System.out.println("Insufficient stock. Available: " + 
                             product.getStockQuantity());
        }
    }
    
    private static void viewCart() {
        shoppingCart.displayCart();
        if (!shoppingCart.isEmpty()) {
            double total = shoppingCart.calculateTotal();
            System.out.printf("\nCart Total: $%.2f\n", total);
            System.out.println("\nTip: Use option 7 to undo last addition");
        }
    }
    
    private static void removeFromCart() {
        if (shoppingCart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        
        viewCart();
        System.out.print("Enter Product ID to remove: ");
        int id = getIntInput("");
        
        CartNode node = shoppingCart.findItem(id);
        if (node != null) {
            // Restore stock to inventory
            inventoryManager.restoreStock(id, node.getQuantity());
            // Remove from cart
            shoppingCart.removeItem(id);
            System.out.println("Item removed from cart. Stock restored.");
        } else {
            System.out.println("Item not found in cart.");
        }
    }
    
    private static void updateCartQuantity() {
        if (shoppingCart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        
        viewCart();
        System.out.print("Enter Product ID to update: ");
        int id = getIntInput("");
        
        CartNode node = shoppingCart.findItem(id);
        if (node == null) {
            System.out.println("Item not found in cart.");
            return;
        }
        
        System.out.print("Enter new quantity: ");
        int newQuantity = getIntInput("");
        
        if (newQuantity <= 0) {
            System.out.println("Use remove option to delete item.");
            return;
        }
        
        int oldQuantity = node.getQuantity();
        int difference = newQuantity - oldQuantity;
        
        if (difference > 0) {
            // Need more stock
            if (inventoryManager.isAvailable(id, difference)) {
                inventoryManager.reduceStockTemporarily(id, difference);
                shoppingCart.updateQuantity(id, newQuantity);
                System.out.println("Quantity updated.");
            } else {
                System.out.println("Insufficient stock.");
            }
        } else if (difference < 0) {
            // Returning items to stock
            inventoryManager.restoreStock(id, -difference);
            shoppingCart.updateQuantity(id, newQuantity);
            System.out.println("Quantity updated.");
        } else {
            System.out.println("Quantity unchanged.");
        }
    }
    
    private static void undoLastAddition() {
        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo.");
            return;
        }
        
        UndoStack.StackNode lastAction = undoStack.pop();  // CHANGED: Using UndoStack.StackNode
        if (lastAction != null) {
            Product product = lastAction.getProduct();
            int quantity = lastAction.getQuantity();
            
            // Remove from cart
            shoppingCart.removeItem(product.getId());
            
            // Restore stock
            inventoryManager.restoreStock(product.getId(), quantity);
            
            System.out.printf("Undid: Removed %d x %s from cart. Stock restored.\n", 
                            quantity, product.getName());
        }
    }
    
    private static void clearCart() {
        if (shoppingCart.isEmpty()) {
            System.out.println("Cart already empty.");
            return;
        }
        
        // Restore all stock
        ArrayList<CartNode> items = shoppingCart.getAllItems();
        for (CartNode item : items) {
            inventoryManager.restoreStock(item.getProduct().getId(), item.getQuantity());
        }
        
        shoppingCart.clear();
        undoStack.clear();
        System.out.println("Cart cleared. All stock restored to inventory.");
    }
    
    private static void checkout() {
        if (shoppingCart.isEmpty()) {
            System.out.println("Cannot checkout with empty cart.");
            return;
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                  BILL");
        System.out.println("=".repeat(60));
        
        double subtotal = shoppingCart.calculateTotal();
        double tax = subtotal * 0.08; // 8% tax
        double total = subtotal + tax;
        
        shoppingCart.displayCart();
        System.out.printf("\nSubtotal: $%.2f\n", subtotal);
        System.out.printf("Tax (8%%): $%.2f\n", tax);
        System.out.printf("TOTAL: $%.2f\n", total);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Thank you for your order!");
        
        // Stock is already reduced temporarily, so we just save
        // Clear cart and undo stack
        shoppingCart.clear();
        undoStack.clear();
        
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    private static void searchProduct() {
        System.out.println("\n1. Search by ID");
        System.out.println("2. Search by Name");
        int choice = getIntInput("Choice: ");
        
        if (choice == 1) {
            System.out.print("Enter ID: ");
            int id = getIntInput("");
            Product product = inventoryManager.searchById(id);
            if (product != null) {
                System.out.println("Found: " + product);
            } else {
                System.out.println("Product not found.");
            }
        } else if (choice == 2) {
            System.out.print("Enter name (partial): ");
            String name = scanner.next();
            ArrayList<Product> results = inventoryManager.searchByName(name);
            if (results.isEmpty()) {
                System.out.println("No products found.");
            } else {
                System.out.println("\nMatching products:");
                for (Product p : results) {
                    System.out.println("  " + p);
                }
            }
        }
    }
    
    private static void addNewProduct() {
        System.out.println("\n--- Add New Product ---");
        System.out.print("Enter ID: ");
        int id = getIntInput("");
        
        if (inventoryManager.searchById(id) != null) {
            System.out.println("Product ID already exists!");
            return;
        }
        
        System.out.print("Enter Name: ");
        scanner.nextLine(); // consume newline
        String name = scanner.nextLine();
        
        System.out.print("Enter Price: ");
        double price = getDoubleInput("");
        
        System.out.print("Enter Stock Quantity: ");
        int stock = getIntInput("");
        
        Product newProduct = new Product(id, name, price, stock);
        inventoryManager.addProduct(newProduct);
    }
    
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a number: ");
            scanner.next();
        }
        int input = scanner.nextInt();
        return input;
    }
    
    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Enter a number: ");
            scanner.next();
        }
        double input = scanner.nextDouble();
        return input;
    }
}