package model;

public class MainCourse implements MenuItem {
    private String itemCode;
    private String name;
    private double price;
    private String proteinType; // Chicken, Beef, Fish, Vegetarian
    private int basePreparationTime;
    
    public MainCourse(String itemCode, String name, double price, 
                      String proteinType, int basePreparationTime) {
        this.itemCode = itemCode;
        this.name = name;
        this.price = price;
        this.proteinType = proteinType;
        this.basePreparationTime = basePreparationTime;
    }
    
    @Override
    public String getItemCode() { return itemCode; }
    
    @Override
    public String getName() { return name; }
    
    @Override
    public double getPrice() { return price; }
    
    @Override
    public int calculatePreparationTime() {
        // Main courses take longer (multiply by 1.5)
        return (int)(basePreparationTime * 1.5);
    }
    
    @Override
    public String getDietaryInfo() {
        return proteinType;
    }
    
    @Override
    public void printDetails() {
        System.out.printf("M%-8s %-20s $%-8.2f %-12s %d min\n",
            itemCode, name, price, proteinType, calculatePreparationTime());
    }
}