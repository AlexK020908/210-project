package model.Types;

public class SupportEntry {
    protected String name;
    protected double pricePaidSoFar;

    public SupportEntry(String name, double pricePaidSoFar) {
        this.name = name;
        this.pricePaidSoFar = pricePaidSoFar;
    }

    public String getName() {
        return name;
    }

    public double getPricePaidSoFar() {
        return pricePaidSoFar;
    }

    public void setName(String name) {
        this.name = name;
    }

    //MODIFIES: this
    //EFFECT: add the specified amount to  the current subscription amount
    public void addToPricePaidSoFar(double amount) {
        pricePaidSoFar += amount;
    }

    //modifies This
    //EFFECT: subtract the specified amount from current subscription amount, only use if you made a mistake
    public void removeFromPricePaidSoFar(double amount) {
        pricePaidSoFar -= amount;
    }
}
