package model;


public class SupportEntry {
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

}
