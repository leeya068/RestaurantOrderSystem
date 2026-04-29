package model;

public class Beverage implements MenuItem {
    private String itemCode;
    private String name;
    private double price;
    private boolean isCold;
    private int preparationTime;
    
    public Beverage(String itemCode, String name, double price, 
                    boolean isCold, int preparationTime) {
        this.itemCode = itemCode;
        this.name = name;
        this.price = price;
        this.isCold = isCold;
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
        return preparationTime; // Beverages are quick
    }
    
    @Override
    public String getDietaryInfo() {
        return isCold ? "Cold Drink" : "Hot Drink";
    }
    
    @Override
    public void printDetails() {
        System.out.printf("B%-8s %-20s $%-8.2f %-12s %d min\n",
            itemCode, name, price, getDietaryInfo(), calculatePreparationTime());
    }
}