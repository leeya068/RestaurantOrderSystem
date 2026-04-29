package model;

public class Appetizer implements MenuItem {
    private String itemCode;
    private String name;
    private double price;
    private boolean isSpicy;
    private int preparationTime; // base time
    
    public Appetizer(String itemCode, String name, double price, 
                     boolean isSpicy, int preparationTime) {
        this.itemCode = itemCode;
        this.name = name;
        this.price = price;
        this.isSpicy = isSpicy;
        this.preparationTime = preparationTime;
    }
    
    @Override
    public String getItemCode() { return itemCode; }
    
    @Override
    public String getName() { return name; }
    
    @Override
    public double getPrice() { return price; }
    
    @Override
    public int calculatePreparationTime() {
        // Appetizers are faster (multiply by 1)
        return preparationTime;
    }
    
    @Override
    public String getDietaryInfo() {
        return isSpicy ? "Spicy" : "Non-Spicy";
    }
    
    @Override
    public void printDetails() {
        System.out.printf("A%-8s %-20s $%-8.2f %-12s %d min\n",
            itemCode, name, price, getDietaryInfo(), calculatePreparationTime());
    }
}