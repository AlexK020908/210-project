package model;

import java.util.LinkedList;
import java.util.List;

public class RevenueList {
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

}
