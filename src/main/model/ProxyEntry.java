package model;

// a proxy that have a name and price plan
public class ProxyEntry {
    private String name;
    private ProxyType proxyType;
    private double pricePaidSoFar;

    public ProxyEntry(String name, ProxyType proxyType, double pricePaidSoFar) {
        this.name = name;
        this.proxyType = proxyType;
        this.pricePaidSoFar = pricePaidSoFar;
    }

    public String getName() {
        return name;
    }

    public ProxyType getProxyType() {
        return proxyType;
    }

    public double getPricePaidSoFar() {
        return pricePaidSoFar;
    }
    //one should be able to modify name and pricePaidSofar if there was a mistake

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
