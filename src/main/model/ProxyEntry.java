package model;

import model.investment.EntryTypes;

// a proxy that have a name and price plan
public class ProxyEntry extends SupportEntry {
    private ProxyType proxyType;

    public ProxyEntry(String name, ProxyType proxyType, double pricePaidSoFar) {
        super(name, pricePaidSoFar);
        this.proxyType = proxyType;
    }

    public ProxyType getProxyType() {
        return proxyType;
    }



}
