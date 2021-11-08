package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.LinkedList;
import java.util.List;

//All the revenues in a list, implements writable to implement the toJson method to turn a revenuelist into a JsonObject
public class RevenueList implements Writable {
    private List<Revenue> revenues;

    //EFFECT: CREATE an empty Revenue list
    public RevenueList() {
        revenues = new LinkedList<>();
    }

    //Get the length of the revenue List
    public int getLength() {
        return revenues.size();
    }

    //MODIFIES: this
    //EFFECT: add the new money made to revenue.
    public void addNewRevenue(Revenue r) {
        revenues.add(r);
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
    public void removeRevenue(Revenue revenue) {
        if (revenues.contains(revenue)) {
            revenues.remove(revenue);
        }

    }
}
