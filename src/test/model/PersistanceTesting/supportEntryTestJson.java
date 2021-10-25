package model.PersistanceTesting;

import model.SupportEntry;

import static org.junit.jupiter.api.Assertions.assertEquals;

//This class checks if the given support entry(either of proxy, cook group, or solver) has the identical info given
public class supportEntryTestJson {
    //EFFECT: checks if the support entry has the same given name and price paid
    protected void checkSupportEntry(String name, double pricePaid, SupportEntry p ) {
         assertEquals(name, p.getName());
         assertEquals(pricePaid, p.getPricePaid());
    }
}
