package PersistanceTesting;

import model.ProxyEntry;
import model.SupportEntry;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class supportEntryTestJson {
    protected void checkSupportEntry(String name, double pricePaid, SupportEntry p ) {
         assertEquals(name, p.getName());
         assertEquals(pricePaid, p.getPricePaid());
    }
}
