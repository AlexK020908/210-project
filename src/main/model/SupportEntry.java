package model;

import org.json.JSONObject;
import persistance.Writable;

//this class represents a super class, it represent support entries such as proxies, Cook groups and Third part
//captcha solvers, it implements Writable as it is needed for it to implement the toJSON method
public class SupportEntry implements Writable {
    protected String name;
    protected double pricePaid;

    //REQUIRES: price paid so far to be positive
    //EFFECT: create a support Investment Entry with a name and a price paid for that entry
    public SupportEntry(String name, double pricePaidSoFar) {
        this.name = name;
        this.pricePaid = pricePaidSoFar;


    }

    //EFFECT: return the name of the entry
    public String getName() {
        return name;
    }

    //EFFECT: return the price paid for that entry
    public double getPricePaid() {
        return pricePaid;
    }


    //REQUIRES amount > 0
    //MODIFIES: this
    //EFFECT: add the specified amount to the current amount of price paid.
    public void addToPricePaidSoFar(double amount) {
        if (amount > 0) {
            pricePaid += amount;
        }

    }

    @Override
    //EFFECT: print out the sneaker entry
    public String toString() {
        return name + " " + pricePaid + " ,";
    }

    @Override
    //EFFECT: Return the support Entry as a JSON object
    // This [method] references code from GitHub
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("pricePaid", pricePaid);
        return json;
    }
}
