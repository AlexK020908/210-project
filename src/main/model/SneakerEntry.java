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

    //EFFECT: Decrease the amount of quantity bought, if larger than the amount there already, return 0
    public void decreaseQuantityBought(int amount) {
        if (amount >= quantityBought) {
            quantityBought = 0;
        } else {
            quantityBought -= amount;
        }
    }
}