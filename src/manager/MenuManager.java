package manager;

import model.MenuItem;
import java.util.ArrayList;

/**
 * Generic class to manage different types of menu items
 * Similar to CourseManager<T extends Course> from your university assignment
 */
public class MenuManager<T extends MenuItem> {
    private ArrayList<T> menuItems;
    
    public MenuManager() {
        menuItems = new ArrayList<>();
    }
    
    public void addItem(T item) {
        menuItems.add(item);
        System.out.println("Added: " + item.getName());
    }
    
    public boolean removeItem(String itemCode) {
        for (int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i).getItemCode().equals(itemCode)) {
                menuItems.remove(i);
                System.out.println("Removed item: " + itemCode);
                return true;
            }
        }
        System.out.println("Item not found.");
        return false;
    }
    
    /**
     * Returns item with longest preparation time
     * Similar to getCourseWithHighestWorkload()
     */
    public T getItemWithLongestPrepTime() {
        if (menuItems.isEmpty()) return null;
        
        T longest = menuItems.get(0);
        for (T item : menuItems) {
            if (item.calculatePreparationTime() > longest.calculatePreparationTime()) {
                longest = item;
            }
        }
        return longest;
    }
    
    /**
     * Sorts items by preparation time (ascending)
     * Similar to sortCoursesByWorkload()
     */
    public void sortByPreparationTime() {
        menuItems.sort((a, b) -> Integer.compare(
            a.calculatePreparationTime(), 
            b.calculatePreparationTime()
        ));
    }
    
    public void printAllItems() {
        if (menuItems.isEmpty()) {
            System.out.println("No menu items available.");
            return;
        }
        
        System.out.println("\n" + "=".repeat(65));
        System.out.printf("%-10s %-22s %-10s %-14s %s\n", 
                         "Code", "Name", "Price", "Dietary", "Prep Time");
        System.out.println("-".repeat(65));
        for (T item : menuItems) {
            item.printDetails();
        }
        System.out.println("=".repeat(65));
    }
    
    public ArrayList<T> getAllItems() {
        return menuItems;
    }
}