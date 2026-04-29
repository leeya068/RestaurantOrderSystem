package datastructures;

import model.Product;

/**
 * Node for custom singly linked list
 * Similar to CartNode from your Grocery assignment
 */
public class CartNode {
    private Product product;
    private int quantity;
    private CartNode next;
    
    public CartNode(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.next = null;
    }
    
    // Getters and Setters
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public CartNode getNext() { return next; }
    public void setNext(CartNode next) { this.next = next; }
    
    public double getSubtotal() {
        return product.getPrice() * quantity;
    }
}