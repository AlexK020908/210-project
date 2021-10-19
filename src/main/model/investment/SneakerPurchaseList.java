package model.investment;

import model.EntryType;
import model.SneakerEntry;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

//This class focuses on a list of sneaker entries, each having a name, price and quantity.
//This class does not extend supportEntryList because this is a sneaker investment, not a support investment
public class SneakerPurchaseList {
    private List<SneakerEntry> sneakerPurchaseList;


    //EFFECT: Initialize an empty sneaker purchase linked list
    public SneakerPurchaseList() {
        sneakerPurchaseList = new LinkedList<>();

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


    //
    //MODIFIES: this
    /*
     EFFECT: Add the given sneaker to the end of the sneaker entry list and return true ,
     if the sneaker name is already in the list, update the quantity of the specific sneaker purchase and
     return false to indicate that no new entries have been added, the overlapping entry is simply updated in terms
     of its quantity.
     */

    // NOTE that we do not have to compare the price as the sneaker with the same name SHOULD have
    // the same price
    public boolean addEntry(SneakerEntry entry) {
        List<String> sneakerNames = getSneakerNames();
        String nameOfSneaker = (entry.getName());
        if (sneakerPurchaseList.isEmpty()) {
            sneakerPurchaseList.add(entry);
            return true;
        } else if (sneakerNames.contains(nameOfSneaker)) {
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
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", EntryType.Sneaker);
        jsonObject.put("sneakers",sneakerToJsonArray());
        return jsonObject;

    }

    public JSONArray sneakerToJsonArray() {
        JSONArray array = new JSONArray();
        for (SneakerEntry next : sneakerPurchaseList) {
            next.toJson();
        }
        return array;
    }

}
