package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.LinkedList;
import java.util.List;

//All the revenues in a list, implements writable to implement the toJson method to turn a revenuelist into a JsonObject
public class RevenueList implements Writable {
    private List<Revenue> revenues;

    public RevenueList() {
        revenues = new LinkedList<>();
    }


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
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("revenues",toJsonRevenueArray());
        return jsonObject;
    }

    //EFFECT: return the revenue List as a JSON array
    public JSONArray toJsonRevenueArray() {
        JSONArray jsonArray = new JSONArray();
        for (Revenue next : revenues) {
            jsonArray.put(next.toJson());
        }
        return jsonArray;
    }

}
