package model.PersistanceTesting;

import model.SneakerEntry;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SneakerTestJson {
    protected void checkSneakerEntry(String name, double pricePaid, int quantity, SneakerEntry s) {
        assertEquals(name, s.getName());
        assertEquals(pricePaid, s.getRetailPrice());
        assertEquals(quantity, s.getQuantityBought());

    }
}
