package datastructures;

import model.Product;

/**
 * Custom Stack implementation using singly linked list
 * Renamed from "Stack" to avoid conflict with java.util.Stack
 */
public class UndoStack {
    
    public static class StackNode {
        private Product product;
        private int quantity;
        private StackNode next;
        
        public StackNode(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
            this.next = null;
        }
        
        public Product getProduct() { return product; }
        public int getQuantity() { return quantity; }
        public StackNode getNext() { return next; }
        public void setNext(StackNode next) { this.next = next; }
    }
    
    private StackNode top;
    private int size;
    
    public UndoStack() {
        top = null;
        size = 0;
    }
    
    /**
     * Push item onto stack (add to top/head - O(1))
     */
    public void push(Product product, int quantity) {
        StackNode newNode = new StackNode(product, quantity);
        newNode.next = top;
        top = newNode;
        size++;
    }
    
    /**
     * Pop item from stack (remove from top/head - O(1))
     */
    public StackNode pop() {
        if (isEmpty()) {
            return null;
        }
        StackNode popped = top;
        top = top.next;
        size--;
        return popped;
    }
    
    public StackNode peek() {
        return top;
    }
    
    public boolean isEmpty() {
        return top == null;
    }
    
    public int getSize() {
        return size;
    }
    
    public void clear() {
        top = null;
        size = 0;
    }
}