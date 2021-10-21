package model;

import org.json.JSONObject;
import persistance.Writable;

//SneakerEntry class, each sneaker entry has a name, retail price and a quantity
//Implements writable method so it can implement the toJson method to turn a sneakerEntry into a JSON object
public class SneakerEntry implements Writable {
    private String name;
    private double retailPrice;
    private int quantityBought;

    //REQUIRES: retail price and quantity bought to be positive
    //EFFECT: initialize the sneaker entry with a name, retailPrice and quantityBought
    public SneakerEntry(String name, double retailPrice,int quantityBought) {
        this.name = name;
        this.retailPrice = retailPrice;
        this.quantityBought = quantityBought;
    }

    //EFFECT: RETURN the name of the sneaker
    public String getName() {
        return name;
    }

    //EFFECT: return the retail price of the sneaker
    public double getRetailPrice() {
        return retailPrice;
    }

    //EFFECT: RETURN the quantity bought for the sneaker
    public int getQuantityBought() {
        return quantityBought;
    }


    //REQUIRES: AMOUNT > 0
    //MODIFIES: this
    //EFFECT: increase the quantities bought of that sneaker by the specified amount
    public void increaseQuantityBought(int amount) {
        if (amount > 0) {
            quantityBought += amount;
        }
    }

    @Override
    //EFFECT: PRINT out the sneaker entry by override the toString method
    public String toString() {
        return  " " + name + " " + retailPrice + " " + quantityBought + ", ";
    }



    @Override
    //EFFECT: return the sneaker entry as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("retailPrice", retailPrice);
        json.put("quantity", quantityBought);
        return json;

    }

}