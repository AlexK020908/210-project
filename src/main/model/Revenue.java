package model;

import org.json.JSONObject;

//class for revenue
public class Revenue {
    private double revenue;

    public Revenue(double revenue) {
        this.revenue = revenue;
    }

    public double getRevenue() {
        return this.revenue;
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("revenue", revenue);
        return jsonObject;
    }
}
