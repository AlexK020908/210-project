package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.LinkedList;
import java.util.List;

//All the revenues in a list, implements writable to implement the toJson method to turn a revenuelist into a JsonObject
public class RevenueList implements Writable {
    private List<Revenue> revenues;
    private EntryType type;

    //EFFECT: CREATE an empty Revenue list
    public RevenueList() {
        revenues = new LinkedList<>();
        type = EntryType.Revenue;
    }

    //Get the length of the revenue List
    public int getLength() {
        return revenues.size();
    }

    //MODIFIES: this
    //EFFECT: add the new money made to revenue.
    public void addNewRevenue(Revenue r) {
        revenues.add(r);
        EventLog.getInstance().logEvent(new Event("a new revenue of " + r.getRevenue() + " "
                + "has been added to the existing revenues"));
    }

    //EFFECT: calculate the total revenue by adding all the revenues together
    public double calculateTotalRevenue() {
        int totalRevenue = 0;
        for (Revenue next : revenues) {
            totalRevenue += next.getRevenue();
        }
        return totalRevenue;
    }


    //EFFECT: return the Revenue List as JSON object
    // This [method] references code from GitHub
     // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", type);
        jsonObject.put("revenues",toJsonRevenueArray());
        return jsonObject;
    }

    //EFFECT: return the revenue List as a JSON array
    // This [Method] references code from GitHub
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    public JSONArray toJsonRevenueArray() {
        JSONArray jsonArray = new JSONArray();
        for (Revenue next : revenues) {
            jsonArray.put(next.toJson());
        }
        return jsonArray;
    }

    //MODIFIES: this
    //EFFECT: REMOVE the specified revenue from the revenue list if it does contain the given revenue
    // can not use forloop as it will cause concurency error, need the iteralble interface....
    public void removeRevenue(Revenue revenue) {
        if (revenues.contains(revenue)) {
            revenues.remove(revenue);
            EventLog.getInstance().logEvent(new Event("an existing revenue of " + revenue.getRevenue() + " "
                    + "has been removed from the existing revenues"));
        }
    }

    //EFFECT: return the revenues in form of a list
    public List<Revenue> getRevenues() {
        return revenues;
    }

    public EntryType getType() {
        return type;
    }
}
