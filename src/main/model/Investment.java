package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Investment {
    private List<ProxyEntry> proxyPurchaseList;
    private List<SneakerEntry> sneakerPurchaseList;
    private List<ThirdPartySolverEntry> solversPurchaseList;
    private List<CookGroupSubscriptionEntry> cookGroupSubscriptionsList;

    public Investment() {
        proxyPurchaseList = new LinkedList<>();
        sneakerPurchaseList = new LinkedList<>();
        solversPurchaseList = new LinkedList<>();
        cookGroupSubscriptionsList = new LinkedList<>();
    }

    //getters
    public List<ProxyEntry> getProxyPurchaseList() {
        return proxyPurchaseList;
    }

    public List<SneakerEntry> getSneakerPurchaseList() {
        return sneakerPurchaseList;
    }

    public List<ThirdPartySolverEntry> getSolversPurchaseList() {
        return solversPurchaseList;
    }

    public List<CookGroupSubscriptionEntry> getCookGroupSubscriptionsList() {
        return cookGroupSubscriptionsList;
    }

    public List<String> getSneakerNames() {
        List<String> names = new LinkedList<>();
        for (SneakerEntry next : sneakerPurchaseList) {
            names.add(next.getName());
        }
        return names;
    }

    //MODIFIES: this
    //EFFECT: Add the given sneaker to the end of the sneakerentry list , if the sneaker name is already, update the
    //quantity on the existing sneaker Entry
    public void addEntry(SneakerEntry sneakerEntry) {
        List<String> sneakerNames = getSneakerNames();
        String nameOfSneaker = (sneakerEntry.getName());

        if (sneakerNames.contains(nameOfSneaker)) {
            updateQuantityOfExistingSneakerEntry(sneakerEntry);
        } else {
            sneakerPurchaseList.add(sneakerEntry);
        }
    }

    //MODIFIES: this
    //EFFECT: if addEntry of sneaker returns false, add the quantity of the attempted sneaker entry to the existing
    //sneaker entry
    public void updateQuantityOfExistingSneakerEntry(SneakerEntry sneakerEntry) {
        for (SneakerEntry next : sneakerPurchaseList) {
            if (next.getName() == sneakerEntry.getName()) {
                next.increaseQuantityBought(sneakerEntry.getQuantityBought());

            }
        }
    }


    //MODIFIES: this
    //EFFECT: Add the given proxy entry to the end of the proxy Entry list, the given list already has the same
    //proxy and same type, simple add the amount paid to the existing entry
    public void addEntry(ProxyEntry proxyEntry) {


    }


    //MODIFIES: this
    //EFFECT: Add the given sneaker to the end of the cook group sub list, if the sneaker name is already in the list
    //simply add the quantity to the existing quantity of that sneaker
    public void addEntry(CookGroupSubscriptionEntry cookGroupSubscriptionEntry){

    }


    //MODIFIES: this
    //EFFECT: Add the given sneaker to the end of the third party solver list, if the sneaker name is already in the
    // list simply add the quantity to the existing quantity of that sneaker
    public void addEntry(ThirdPartySolverEntry thirdPartySolverEntry){

    }

}
