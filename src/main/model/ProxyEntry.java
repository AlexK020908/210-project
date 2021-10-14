package model;

// a proxy that have a name and price plan
public class ProxyEntry extends SupportEntry {
    private ProxyType proxyType;

    public ProxyEntry(String name, double pricePaidSoFar) {
        super(name, pricePaidSoFar);
    }

    public ProxyType getProxyType() {
        return proxyType;
    }



}
