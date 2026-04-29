package model;

public class Dessert implements MenuItem {
    private String itemCode;
    private String name;
    private double price;
    private boolean hasNuts;
    private int preparationTime;
    
    public Dessert(String itemCode, String name, double price, 
                   boolean hasNuts, int preparationTime) {
        this.itemCode = itemCode;
        this.name = name;
        this.price = price;
        this.hasNuts = hasNuts;
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
        // Desserts are quick
        return preparationTime;
    }
    
    @Override
    public String getDietaryInfo() {
        return hasNuts ? "Contains Nuts" : "Nut-Free";
    }
    
    @Override
    public void printDetails() {
        System.out.printf("D%-8s %-20s $%-8.2f %-12s %d min\n",
            itemCode, name, price, getDietaryInfo(), calculatePreparationTime());
    }
}