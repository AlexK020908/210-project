package model.investment;

import model.CookGroupSubscriptionEntry;

import java.util.LinkedList;
import java.util.List;

public class CookGroupPurchaseList {
    private List<CookGroupSubscriptionEntry> cookGroupPurchaseList;

    public CookGroupPurchaseList() {
        cookGroupPurchaseList = new LinkedList<>();
    }

    public List<CookGroupSubscriptionEntry> getCookGroupPurchaseList() {
        return cookGroupPurchaseList;
    }

    //EFFECT: get the size of the given list
    public int getLength() {
        return cookGroupPurchaseList.size();
    }

    //EFFECT: get index of the first occurance of the specified entry
    public int indexOf(CookGroupSubscriptionEntry entry) {
        return cookGroupPurchaseList.indexOf(entry);
    }

    //MODIFIES: this
    //EFFECT: Add the given sneaker to the end of the cook group sub list and return true,
    // if the cookgroup name is already in the list simply add the cost to the existing cost and return false to show
    // that no new entries have been added
    public boolean addEntry(CookGroupSubscriptionEntry entry) {
        String name = entry.getName();
        double pricePaid = entry.getPricePaidSoFar();

        for (CookGroupSubscriptionEntry next: cookGroupPurchaseList) {
            if (next.getName() == name) {
                next.addToPricePaidSoFar(pricePaid);
                return false; //can I return a string instead? like updated exsiting subsrcition?
            }
        }
        cookGroupPurchaseList.add(entry);
        return true;
    }

     //REQUIRES: the entry is already in the list
    //MODIFIES:THIS
    //EFFECT: remove the specified entry from the given list and return true, otherwise return false
    public boolean removeEntry(CookGroupSubscriptionEntry entry) {
        if (cookGroupPurchaseList.contains(entry)) {
            cookGroupPurchaseList.remove(entry);
            return true;
        }
        return false;
    }

    //EFFECT: return total money spend on cook Groups
    public double getTotalMoneySpent() {
        double sum = 0.0;
        for (CookGroupSubscriptionEntry next : cookGroupPurchaseList) {
            double price = next.getPricePaidSoFar();
            sum =  sum + price;
        }
        return sum;
    }





}
