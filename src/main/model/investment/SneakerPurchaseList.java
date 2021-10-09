package model.investment;

import model.Types.CookGroupSubscriptionEntry;
import model.Types.SneakerEntry;

import java.util.LinkedList;
import java.util.List;

public class SneakerPurchaseList {
    private List<SneakerEntry> sneakerPurchaseList;

    public SneakerPurchaseList() {
        sneakerPurchaseList = new LinkedList<>();
    }

    public List<SneakerEntry> getSneakerPurchaseList() {
        return sneakerPurchaseList;
    }

    //MODIFIES: this
    //EFFECT: Add the given sneaker to the end of the sneaker entry list , if the sneaker name is already, update the
    //quantity on the existing sneaker Entry
    public void addEntry(SneakerEntry entry) {
        List<String> sneakerNames = getSneakerNames();
        String nameOfSneaker = (entry.getName());

        if (sneakerNames.contains(nameOfSneaker)) {
            updateQuantityOfExistingSneakerEntry(entry);
        } else {
            sneakerPurchaseList.add(entry);
        }
    }


    //MODIFIES: this
    //EFFECT: if addEntry of sneaker returns false, add the quantity of the attempted sneaker entry to the existing
    //sneaker entry
    private void updateQuantityOfExistingSneakerEntry(SneakerEntry sneakerEntry) {
        for (SneakerEntry next : sneakerPurchaseList) {
            if (next.getName() == sneakerEntry.getName()) {
                next.increaseQuantityBought(sneakerEntry.getQuantityBought());
            }
        }
    }

    //EFFECT: returns all the sneaker names in form of a list
    private List<String> getSneakerNames() {
        List<String> names = new LinkedList<>();
        for (SneakerEntry next : sneakerPurchaseList) {
            names.add(next.getName());
        }
        return names;
    }

    //REQUIRES: the entry is already in the list
    //MODIFIES:THIS
    //EFFECT: remove the specified entry from the given list
    public void removeEntry(SneakerEntry entry) {
        sneakerPurchaseList.remove(entry);

    }

    public int getTotalMoneySpent() {
        int sum = 0;
        for (SneakerEntry next : sneakerPurchaseList) {
            int quantity = next.getQuantityBought();
            double price = next.getPrice();
            sum = sum + (int) (quantity * price);
        }
        return sum;
    }
}
