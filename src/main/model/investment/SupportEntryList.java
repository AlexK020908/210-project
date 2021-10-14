package model.investment;

import model.SneakerEntry;
import model.SupportEntry;

import java.util.LinkedList;
import java.util.List;


//This is a generic class that extends supportEntry, which has proxies, ThirdPartySolvers, and cookGroups as subtypes
public class SupportEntryList<T extends SupportEntry> {
    private List<T> typePurchaseList;
    private EntryType type;

    //EFFECT: initiate the purchaseList to a linkedList
    public SupportEntryList(EntryType type) {
        typePurchaseList = new LinkedList<>();
        this.type = type;
    }

    public EntryType getType() {
        return this.type;
    }

    //EFFECT: get the size of the purchaseList
    public int getLength() {
        return typePurchaseList.size();
    }

    public List<T> getPurchaseList() {
        return typePurchaseList;
    }

    //EFFECT: get the position of the specified entry in the list, the first element has position 0, if the entry does
    //not exist, simply return -1
    public int indexOf(T entry) {
        if (typePurchaseList.contains(entry)) {
            return typePurchaseList.indexOf(entry);
        }
        return -1;
    }

    //REQUIRES: the parameter to be supportEntry type of a subtype of supportEntry type
    //MODIFIES: this
    //EFFECT: Add the given proxy entry to the end of the proxy Entry list and return true,
    // if the given list already has the same proxy and same type, simply add the amount paid to the existing entry
    // and return false to indicate that no new entries are added, an entry is simply updated
    public boolean addEntry(T entry) {
        String name = entry.getName();
        double pricePaid = entry.getPricePaid();

        for (T next : typePurchaseList) {
            if (next.getName().equals(name)) {
                next.addToPricePaidSoFar(pricePaid);
                return false;
            }

        }
        typePurchaseList.add(entry);
        return true;

    }


    //REQUIRES: the entry is already in the list
    //MODIFIES:THIS
    //EFFECT: remove the specified entry from the given list and return true, if the entry does not exist, return true
    public Boolean removeEntry(T entry) {
        if (typePurchaseList.contains(entry)) {
            typePurchaseList.remove(entry);
            return true;
        }
        return false;


    }


    //EFFECT: return the total amount of money spent on the spcificied entry in the purchase list
    public double getTotalMoneySpent() {
        double sum = 0;
        for (T next : typePurchaseList) {
            double price = next.getPricePaid();
            sum = sum + price;
        }
        return sum;
    }









}
