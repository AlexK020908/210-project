package model.investment;

import model.Sneaker;
import model.SneakerEntry;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.List;

//This class focuses on a list of sneaker entries, each having a price and quantity
public class SneakerPurchaseList {
    private List<SneakerEntry> sneakerPurchaseList;


    //EFFECT: Initialize an empty sneaker purchase linked list
    public SneakerPurchaseList() {
        sneakerPurchaseList = new LinkedList<>();

    }

    public List<SneakerEntry> getPurchaseList() {
        return sneakerPurchaseList;
    }

    //EFFECT: get the position of the specified sneaker entry in the list, the first element has position 0,
    // if the entry does not exist, simply return -1
    public int indexOf(SneakerEntry entry) {
        if (sneakerPurchaseList.contains(entry)) {
            return sneakerPurchaseList.indexOf(entry);
        }
        return -1;
    }

    //MODIFIES: this
    //EFFECT: Add the given sneaker to the end of the sneaker entry list and return true ,
    // if the sneaker name is already in the list, update the quantity of the sneaker purchase and return false to
    //indicate that no new entries have been added, the overlapping entry is simply updated
    public boolean addEntry(SneakerEntry entry) {
        List<String> sneakerNames = getSneakerNames();
        String nameOfSneaker = (entry.getName());

        if (sneakerNames.contains(nameOfSneaker)) {
            updateQuantityOfExistingSneakerEntry(entry);
            return false;
        } else {
            sneakerPurchaseList.add(entry);
            return true;
        }
    }

    public double getTotalMoneySpent() {
        double sum = 0;
        if (sneakerPurchaseList.isEmpty()) {
            return 0.0;
        } else {
            for (SneakerEntry next : sneakerPurchaseList) {
                int quantity = next.getQuantityBought();
                double price = next.getPrice();
                sum = sum + (quantity * price);
            }
        }
        return sum;
    }



    //MODIFIES: this
    //EFFECT: if addEntry of sneaker returns false, add the quantity of the attempted sneaker entry to the existing
    //sneaker entry
    private void updateQuantityOfExistingSneakerEntry(SneakerEntry sneakerEntry) {
        for (SneakerEntry next : sneakerPurchaseList) {
            if (next.getName().equals(sneakerEntry.getName())) {
                next.increaseQuantityBought(sneakerEntry.getQuantityBought());
            }
        }
    }

    //EFFECT: returns all the sneaker names in form of a list of Strings
    private List<String> getSneakerNames() {
        List<String> names = new LinkedList<>();
        for (SneakerEntry next : sneakerPurchaseList) {
            names.add(next.getName());
        }
        return names;
    }


    public int getLength() {
        return sneakerPurchaseList.size();
    }
}
