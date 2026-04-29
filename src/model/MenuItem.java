package model;

/**
 * ADT (Abstract Data Type) for all menu items
 * Similar to Course interface from your university assignment
 */
public interface MenuItem {
    
    /**
     * Returns the unique item code
     */
    String getItemCode();
    
    /**
     * Returns the item name
     */
    String getName();
    
    /**
     * Returns the price
     */
    double getPrice();
    
    /**
     * Calculates preparation time in minutes
     * Different for each item type - polymorphism in action
     */
    int calculatePreparationTime();
    
    /**
     * Returns dietary information (vegan, gluten-free, etc.)
     */
    String getDietaryInfo();
    
    /**
     * Prints all item details
     */
    void printDetails();
}