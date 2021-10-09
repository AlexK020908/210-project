package model.investment;

import model.Types.CookGroupSubscriptionEntry;
import model.Types.ProxyEntry;
import model.Types.ProxyType;
import model.Types.SneakerEntry;

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


    //MODIFIES: this
    //EFFECT: Add the given proxy entry to the end of the proxy Entry list, the given list already has the same
    //proxy and same type, simply add the amount paid to the existing entry
    public void addEntry(ProxyEntry entry) {
        String nameOfProxy = entry.getName();
        ProxyType proxyType = entry.getProxyType();
        double pricePaid = entry.getPricePaidSoFar();

        for (ProxyEntry next : proxyPurchaseList) {
            if (next.getName() == nameOfProxy && next.getProxyType() == proxyType) {
                next.addToPricePaidSoFar(pricePaid);
            }
            proxyPurchaseList.add(entry);
        }

    }

    //REQUIRES: the entry is already in the list
    //MODIFIES:THIS
    //EFFECT: remove the specified entry from the given list
    public void removeEntry(ProxyEntry entry) {
        proxyPurchaseList.remove(entry);


    }

    //EFFECT: return total money spend on proxies
    public int getTotalMoneySpent() {
        int sum = 0;
        for (ProxyEntry next : proxyPurchaseList) {
            double price = next.getPricePaidSoFar();
            sum = (int) (sum + price);
        }
        return sum;
    }
}

