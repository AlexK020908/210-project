package model.investment;

import model.EntryType;
import model.SupportEntry;
import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.LinkedList;
import java.util.List;


//This is a generic class that extends support Entry, which is extended by proxy entry
// Third part solver entry, and cookGroup entry as subtypes.
//This class implements writable in order to implement the toJson method to turn the support entry list into
// a JSON object
public abstract class SupportEntryList<T extends SupportEntry> implements Writable {
    protected List<T> typePurchaseList;
    private EntryType type;


    //EFFECT: initiate the purchaseList to a linkedList
    public SupportEntryList(EntryType type) {
        typePurchaseList = new LinkedList<>();
        this.type = type;
    }

    //EFFECT: RETURN the actual type of this list of entries
    public EntryType getType() {
        return this.type;
    }

    //EFFECT: get the size of the purchaseList
    public int getLength() {
        return typePurchaseList.size();
    }

    //EFFECT: Return the entry at given index
    public T get(int i) {
        return typePurchaseList.get(i);
    }

    public List<T> getEntries() {
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

    //REQUIRES: parameter to be of type SupportEntry or a subtype of SupportEntry
    //MODIFIES: this
    //EFFECT: Add the given entry to the end of the Entry list and return TRUE,
    // if the given list already has an entry with the same name, simply add the price paid for the entry
    // to the overlapping entry and return FALSE to indicate that no new entries are added, an entry in the purchase
    // list is simply updated
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
    //EFFECT: remove the specified entry from the given list and return true, if the entry does not exist, return FALSE
    public Boolean removeEntry(T entry) {
        if (typePurchaseList.contains(entry)) {
            typePurchaseList.remove(entry);
            return true;
        }
        return false;


    }


    //EFFECT: return the total amount of money spent on the entry type in the purchase list
    public double getTotalMoneySpent() {
        double sum = 0;
        for (T next : typePurchaseList) {
            double price = next.getPricePaid();
            sum = sum + price;
        }
        return sum;
    }


    @Override
    //EFFECT: list all the entries as a string
    //THIS method references code from GeekForGeeks
    //[https://www.geeksforgeeks.org/stream-map-java-examples/]
    public String toString() {
        String supportEntryList = typePurchaseList.stream()
                .map(supportEntry -> supportEntry.toString()).reduce("", String::concat);
        return supportEntryList;
    }


    @Override
    //EFFECT: turn the Support entry List into a Json Object
    // This [method] references code from GitHub
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    public abstract JSONObject toJson();


}
