package model;

import org.json.JSONObject;

import java.util.Objects;

//class for revenue
public class Revenue {
    private double revenue;

    //EFFECT: construct a revenue object
    //        if the revenue made is an invalid amount, throw new Amount exception
    public Revenue(double revenue) throws AmountException {
        if (revenue <= 0) {
            throw new AmountException();
        }
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

    //EFFECT: compare the two objects by overriding equals, two objects are equal if they have the same revenue
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Revenue revenue1 = (Revenue) o;
        return Double.compare(revenue1.revenue, revenue) == 0;
    }

    //EFFECT: hascode method
    @Override
    public int hashCode() {
        return Objects.hash(revenue);
    }
}
