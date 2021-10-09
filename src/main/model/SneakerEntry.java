package model;

import model.Sneaker;

public class SneakerEntry extends Sneaker {
    private int quantityBought;

    public SneakerEntry(String name, double price,int quantityBought) {
        super(name, price);
        this.quantityBought = quantityBought;
    }

    public int getQuantityBought() {
        return quantityBought;
    }

    public void increaseQuantityBought(int amount) {
        quantityBought += amount;
    }

    public void decreaseQuantityBought(int amount) {
        quantityBought -= amount;
    }
}
