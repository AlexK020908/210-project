package model.investment;

import model.ThirdPartySolverEntry;

import java.util.LinkedList;
import java.util.List;

public class ThirdPartyCaptchaSolversPurchaseList {
    private List<ThirdPartySolverEntry> solversPurchaseList;

    public ThirdPartyCaptchaSolversPurchaseList() {
        solversPurchaseList = new LinkedList<>();
    }

    public List<ThirdPartySolverEntry> getSolversPurchaseList() {
        return solversPurchaseList;
    }

    //EFFECT: return the size of the solver purchase list
    public int getLength() {
        return solversPurchaseList.size();
    }

    //EFFECT: give the index of the specified solver in the solver purchase list
    public int indexOf(ThirdPartySolverEntry entry) {
        return solversPurchaseList.indexOf(entry);
    }


    //MODIFIES: this
    //EFFECT: Add the given sneaker to the end of the third party solver list, if the sneaker name is already in the
    // list simply add the quantity to the existing quantity of that sneaker
    public boolean addEntry(ThirdPartySolverEntry entry) {
        String name = entry.getName();
        double pricePaid = entry.getPricePaidSoFar();
        for (ThirdPartySolverEntry next: solversPurchaseList) {
            if (next.getName() == name) {
                next.addToPricePaidSoFar(pricePaid);
                return false;
            }
        }
        solversPurchaseList.add(entry);
        return true;

    }

    //REQUIRES: the entry is already in the list
    //MODIFIES:THIS
    //EFFECT: remove the specified entry from the given list
    public void removeEntry(ThirdPartySolverEntry entry) {
        solversPurchaseList.remove(entry);
    }


    //EFFECT: return total money spend on third part solvers
    public double getTotalMoneySpent() {
        double sum = 0;
        for (ThirdPartySolverEntry next : solversPurchaseList) {
            double price = next.getPricePaidSoFar();
            sum =  (sum + price);
        }
        return sum;
    }
}
