package model.investment;

import model.ProxyEntry;
import model.ProxyType;

import java.util.LinkedList;
import java.util.List;

public class ProxyPurchaseList {
    private List<ProxyEntry> proxyPurchaseList;

    public ProxyPurchaseList() {
        proxyPurchaseList = new LinkedList<>();
    }

    public List<ProxyEntry> getProxyPurchaseList() {
        return proxyPurchaseList;
    }

    //EFFECT: return the size of the proxy purchase list
    public int getLength() {
        return proxyPurchaseList.size();
    }

    //return the index of the specified proxy entry
    public int indexOf(ProxyEntry entry) {
        return proxyPurchaseList.indexOf(entry);
    }


    //MODIFIES: this
    //EFFECT: Add the given proxy entry to the end of the proxy Entry list, the given list already has the same
    //proxy and same type, simply add the amount paid to the existing entry
    public boolean addEntry(ProxyEntry entry) {
        String nameOfProxy = entry.getName();
        ProxyType proxyType = entry.getProxyType();
        double pricePaid = entry.getPricePaidSoFar();

        for (ProxyEntry next : proxyPurchaseList) {
            if (next.getName() == nameOfProxy && next.getProxyType() == proxyType) {
                next.addToPricePaidSoFar(pricePaid);
                return false;
            }

        }
        proxyPurchaseList.add(entry);
        return true;

    }

    //REQUIRES: the entry is already in the list
    //MODIFIES:THIS
    //EFFECT: remove the specified entry from the given list
    public void removeEntry(ProxyEntry entry) {
        proxyPurchaseList.remove(entry);


    }

    //EFFECT: return total money spend on proxies
    public double getTotalMoneySpent() {
        double sum = 0;
        for (ProxyEntry next : proxyPurchaseList) {
            double price = next.getPricePaidSoFar();
            sum = sum + price;
        }
        return sum;
    }
}

