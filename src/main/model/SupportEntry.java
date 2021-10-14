package model;

public class SupportEntry {
    protected String name;
    protected double pricePaid;

    public SupportEntry(String name, double pricePaidSoFar) {
        this.name = name;
        this.pricePaid = pricePaidSoFar;

    }


    public String getName() {
        return name;
    }

    public double getPricePaid() {
        return pricePaid;
    }

    public void setName(String name) {
        this.name = name;
    }

    //MODIFIES: this
    //EFFECT: add the specified amount to  the current subscription amount
    public void addToPricePaidSoFar(double amount) {
        pricePaid += amount;
    }

    //modifies This
    //EFFECT: subtract the specified amount from current subscription amount, only use if you made a mistake
    public void removeFromPricePaidSoFar(double amount) {
        pricePaid -= amount;
    }

}
