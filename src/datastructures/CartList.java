package datastructures;

import model.Product;
import java.util.ArrayList;

/**
 * Self-implemented Singly Linked List for shopping cart
 * From your Grocery assignment
 */
public class CartList {
    private CartNode head;
    private int size;
    
    public CartList() {
        head = null;
        size = 0;
    }
    
    /**
     * Adds item to cart (at the end for FIFO behavior)
     * If product already exists, updates quantity
     */
    public void addItem(Product product, int quantity) {
        // Check if product already in cart
        CartNode existing = findItem(product.getId());
        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + quantity);
        } else {
            CartNode newNode = new CartNode(product, quantity);
            if (head == null) {
                head = newNode;
            } else {
                CartNode current = head;
                while (current.getNext() != null) {
                    current = current.getNext();
                }
                current.setNext(newNode);
            }
            size++;
        }
    }
    
    /**
     * Removes item by product ID
     */
    public boolean removeItem(int productId) {
        if (head == null) return false;
        
        if (head.getProduct().getId() == productId) {
            head = head.getNext();
            size--;
            return true;
        }
        
        CartNode current = head;
        while (current.getNext() != null) {
            if (current.getNext().getProduct().getId() == productId) {
                current.setNext(current.getNext().getNext());
                size--;
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
    
    /**
     * Updates quantity of existing cart item
     */
    public boolean updateQuantity(int productId, int newQuantity) {
        CartNode node = findItem(productId);
        if (node != null && newQuantity > 0) {
            node.setQuantity(newQuantity);
            return true;
        }
        return false;
    }
    
    /**
     * Finds item by product ID (sequential search - O(n))
     */
    public CartNode findItem(int productId) {
        CartNode current = head;
        while (current != null) {
            if (current.getProduct().getId() == productId) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }
    
    /**
     * Displays all cart items
     */
    public void displayCart() {
        if (head == null) {
            System.out.println("Cart is empty.");
            return;
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.printf("%-25s %-10s %-10s %-15s\n", 
                         "Product", "Quantity", "Unit Price", "Subtotal");
        System.out.println("-".repeat(70));
        
        CartNode current = head;
        double total = 0;
        while (current != null) {
            Product p = current.getProduct();
            double subtotal = current.getSubtotal();
            System.out.printf("%-25s %-10d $%-9.2f $%-10.2f\n",
                             p.getName(), current.getQuantity(), 
                             p.getPrice(), subtotal);
            total += subtotal;
            current = current.getNext();
        }
        System.out.println("-".repeat(70));
        System.out.printf("Total: $%.2f\n", total);
        System.out.println("=".repeat(70));
    }
    
    /**
     * Calculates total bill amount
     */
    public double calculateTotal() {
        double total = 0;
        CartNode current = head;
        while (current != null) {
            total += current.getSubtotal();
            current = current.getNext();
        }
        return total;
    }
    
    /**
     * Clears entire cart
     */
    public void clear() {
        head = null;
        size = 0;
    }
    
    /**
     * Returns all items as ArrayList (for checkout processing)
     */
    public ArrayList<CartNode> getAllItems() {
        ArrayList<CartNode> items = new ArrayList<>();
        CartNode current = head;
        while (current != null) {
            items.add(current);
            current = current.getNext();
        }
        return items;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public int getSize() {
        return size;
    }
    
    public CartNode getHead() {
        return head;
    }
}