package model;


//third part solver with a name and price
public class ThirdPartySolverEntry {
    private String name;
    private double pricePaidSoFar;

    public ThirdPartySolverEntry(String name, double topUpAmount) {
        this.name = name;
        this.pricePaidSoFar = topUpAmount;
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
