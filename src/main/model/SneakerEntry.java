package model;

import org.json.JSONObject;
import persistance.Writable;

import java.util.Objects;

//SneakerEntry class, each sneaker entry has a name, retail price and a quantity
//Implements writable method so it can implement the toJson method to turn a sneakerEntry into a JSON object
public class SneakerEntry implements Writable {
    private String name;
    private double retailPrice;
    private int quantityBought;

    //REQUIRES: retail price and quantity bought to be positive
    //EFFECT: initialize the sneaker entry with a name, retailPrice and quantityBought
    //        if the sneaker entry has an empty name, throw name exception
    //        if the sneaker entry has an invalid retail price, throw amount exception
    //        if the sneaker entry has an invalid quantity , throw quantity exception
    public SneakerEntry(String name, double retailPrice,int quantityBought)
            throws NameException, QuantityException, AmountException {
        if (name.isEmpty()) {
            throw new NameException();
        } else {
            this.name = name;
        }
        if (retailPrice == 0 || retailPrice < 0) {
            throw new AmountException();
        } else {
            this.retailPrice = retailPrice;
        }
        if (quantityBought <= 0) {
            throw new QuantityException();
        } else {
            this.quantityBought = quantityBought;
        }
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


    //MODIFIES: this
    //EFFECT: increase the quantities bought of that sneaker by the specified amount
    public void increaseQuantityBought(int amount) {
        quantityBought += amount;
    }

    @Override
    //EFFECT: PRINT out the sneaker entry by override the toString method
    public String toString() {
        return  " " + name + " " + retailPrice + " " + quantityBought + ", ";
    }



    @Override
    //EFFECT: return the sneaker entry as a JSON object
    // This [method] references code from GitHub
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("retailPrice", retailPrice);
        json.put("quantity", quantityBought);
        return json;

    }


    //EFFECT: check if two sneaker entries are equal, if two entries are the same object, return true, otherwise false
    //        the same sneaker should have the same name and price;
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || !(o.getClass() == this.getClass())) {
            return false;
        }
        SneakerEntry s = (SneakerEntry) o;

        return (s.getName().equals(this.getName()) && s.getRetailPrice() == (this.getRetailPrice())) ? true : false;
    }

    @Override
    //hashcode method for overriden equals
    public int hashCode() {
        return Objects.hash(name, retailPrice);
    }
}
