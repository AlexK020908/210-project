package model;

//cook group that should have a cook group name and a price
public class CookGroupSubscriptionEntry {
    private String name;
    private double pricePaidSoFar;

    public CookGroupSubscriptionEntry(String name, double pricePaidSoFar) {
        this.name = name;
        this.pricePaidSoFar = pricePaidSoFar;
    }

    public String getName() {
        return name;
    }

    public double getPricePaidSoFar() {
        return pricePaidSoFar;
    }

    public void setName() {
        this.name = name;
    }

    public void addToPricePaidSoFar(double amount) {
        pricePaidSoFar += amount;
    }

    public void removeFromPricePaidSoFar(double amount) {
        pricePaidSoFar -= amount;
    }


}
