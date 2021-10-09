package model.investment;

import model.Types.CookGroupSubscriptionEntry;
import model.Types.ProxyEntry;

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

    //MODIFIES: this
    //EFFECT: Add the given sneaker to the end of the cook group sub list, if the cookgroup name is already in the list
    //simply add the cost to the existing cost.
    public void addEntry(CookGroupSubscriptionEntry entry) {
        String name = entry.getName();
        double pricePaid = entry.getPricePaidSoFar();

        for (CookGroupSubscriptionEntry next: cookGroupPurchaseList) {
            if (next.getName() == name) {
                next.addToPricePaidSoFar(pricePaid);
            }
            cookGroupPurchaseList.add(entry);

        }

    }

     //REQUIRES: the entry is already in the list
    //MODIFIES:THIS
    //EFFECT: remove the specified entry from the given list
    public void removeEntry(CookGroupSubscriptionEntry entry) {
        cookGroupPurchaseList.remove(entry);


    }

    //EFFECT: return total money spend on cook Groups
    public int getTotalMoneySpent() {
        int sum = 0;
        for (CookGroupSubscriptionEntry next : cookGroupPurchaseList) {
            double price = next.getPricePaidSoFar();
            sum = (int) (sum + price);
        }
        return sum;
    }





}
