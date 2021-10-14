package model;



//represents a sneaker class that should have a name and a retail price and also the quantity bought
public class Sneaker  {
    private String name;
    private double price;

    public Sneaker(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }



}
