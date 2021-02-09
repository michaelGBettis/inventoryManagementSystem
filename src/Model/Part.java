/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Myque
 */
public abstract  class Part {
    
    private int ID;
    private String Name;
    private double Price;
    private int Stock;
    private int Min;
    private int Max;
    private boolean isInHouse;

    public Part(int ID, String Name, double Price, int Stock, int Min, int Max, boolean isInHouse) {
        this.ID = ID;
        this.Name = Name;
        this.Price = Price;
        this.Stock = Stock;
        this.Min = Min;
        this.Max = Max;
        this.isInHouse = isInHouse;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public int getMin() {
        return Min;
    }

    public void setMin(int Min) {
        this.Min = Min;
    }

    public int getMax() {
        return Max;
    }

    public void setMax(int Max) {
        this.Max = Max;
    }

    public boolean isIsInHouse() {
        return isInHouse;
    }

    public void setIsInHouse(boolean isInHouse) {
        this.isInHouse = isInHouse;
    }
    
}
