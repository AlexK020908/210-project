package model;

import org.json.JSONObject;

//class for revenue
public class Revenue {
    private double revenue;

    //EFFECT: construct a revenue object
    public Revenue(double revenue) {
        this.revenue = revenue;
    }

    //EFFECT: get the revenue.
    public double getRevenue() {
        return this.revenue;
    }

    //EFFECT: RETURN the revenue as a JSON object
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("revenue", revenue);
        return jsonObject;
    }
}
