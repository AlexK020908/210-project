package model.investment;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

//This class focuses on a list of sneaker entries, each having a name, price and quantity.
//This class does not extend supportEntryList because this is a sneaker investment, not a support investment
//it implements writable to implement the toJson method to turn a sneaker purchase list into a JsonObject
public class SneakerPurchaseList implements Writable {
    private List<SneakerEntry> sneakerPurchaseList;
    private EntryType type;


    //EFFECT: Initialize an empty sneaker purchase linked list
    public SneakerPurchaseList() {
        sneakerPurchaseList = new LinkedList<>();
        type = EntryType.Sneaker;

    }


    //EFFECT: get the position of the specified sneaker entry in the list, the first element has position 0,
    // if the entry does not exist, simply return -1
    public int indexOf(SneakerEntry entry) {
        if (sneakerPurchaseList.contains(entry)) {
            return sneakerPurchaseList.indexOf(entry);
        }
        return -1;
    }

    //EFFECT: return the size of the sneakerPurchase List.
    public int getLength() {
        return sneakerPurchaseList.size();
    }

    //Effect: return the type of the entry
    public EntryType getType() {
        return EntryType.Sneaker;
    }


    //get the sneaker entry at given index
    public SneakerEntry get(int i) {
        return sneakerPurchaseList.get(i);
    }



    //MODIFIES: this
    //EFFECT: remove the specified sneaker entry in the list
    public boolean removeEntry(SneakerEntry sneakerEntry) {
        if (sneakerPurchaseList.contains(sneakerEntry)) {
            sneakerPurchaseList.remove(sneakerEntry);
            EventLog.getInstance().logEvent(new Event("a sneaker entry named" +  sneakerEntry.getName()
                    + " has been removed from its sneaker entry list"));
            return true;
        } else {
            return false;
        }
    }
    //
    //MODIFIES: this
    /*
     EFFECT: Add the given sneaker to the end of the sneaker entry list and return true ,
     if the sneaker name is already in the list, update the quantity of the specific sneaker purchase and
     return false to indicate that no new entries have been added, the overlapping entry is simply updated in terms
     of its quantity.
     */


    public boolean addEntry(SneakerEntry entry) {
        if (sneakerPurchaseList.isEmpty()) {
            sneakerPurchaseList.add(entry);
            EventLog.getInstance().logEvent(new Event("a new sneaker entry named " + entry.getName()
                    + "has been added to an empty sneaker entry list"));
            return true;
        } else if (sneakerPurchaseList.contains(entry)) {
            updateQuantityOfExistingSneakerEntry(entry);
            EventLog.getInstance().logEvent(new Event(entry.getName() + "'s quantity has been updated"
                    + " by" + entry.getQuantityBought()));
            return false;
        } else {
            sneakerPurchaseList.add(entry);
            EventLog.getInstance().logEvent(new Event("a new entry named" + " " + entry.getName()
                    + "has been added to the sneaker entry list"));
            return true;
        }
    }

    //Effect: return the total money spent on the sneakers
    public double getTotalMoneySpent() {
        double sum = 0;
        if (sneakerPurchaseList.isEmpty()) {
            return 0.0;
        } else {
            for (SneakerEntry next : sneakerPurchaseList) {
                int quantity = next.getQuantityBought();
                double price = next.getRetailPrice();
                sum = sum + (quantity * price);
            }
        }
        return sum;
    }



    //helper function
    //MODIFIES: this
    //EFFECT: (for the sneaker entry that the user intends to add in) If this sneaker entry's name is already in the
    // list, add the quantity of the sneaker entry to the existing sneaker entry's quantity.
    //aka. UPDATING the quantity
    private void updateQuantityOfExistingSneakerEntry(SneakerEntry sneakerEntry) {
        for (SneakerEntry next : sneakerPurchaseList) {
            if (next.equals(sneakerEntry)) {
                next.increaseQuantityBought(sneakerEntry.getQuantityBought());
            }
        }
    }


    @Override
    //EFFECT: LIST all the sneaker entries as a string
    //THIS method references code from GeekForGeeks
    //[https://www.geeksforgeeks.org/stream-map-java-examples/]
    public String toString() {
        String listOfSneakerEntries = sneakerPurchaseList.stream() //making it into a stream of string
                .map(sneakerEntry -> sneakerEntry.toString()) //for every sneaker entry, using toString method
                .reduce("", String::concat); //using reduce to join everything using concat
        //identifier is the initial value or the RESULT if there is no sneaker entries inside the list.
        return listOfSneakerEntries;
    }


    //EFFECT: returns the sneaker Purchase list as a Json object
    // This [method] references code from GitHub
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", EntryType.Sneaker);
        jsonObject.put("sneakers",sneakerToJsonArray());
        return jsonObject;

    }

    //EFFECT: return the sneaker purchase list as a Json Array
    // This [method] references code from GitHub
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    public JSONArray sneakerToJsonArray() {
        JSONArray array = new JSONArray();
        for (SneakerEntry next : sneakerPurchaseList) {
            array.put(next.toJson());
        }
        return array;
    }

    public List<SneakerEntry> getEntries() {
        return sneakerPurchaseList;
    }
}
