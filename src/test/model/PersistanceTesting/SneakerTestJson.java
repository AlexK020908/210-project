package model.PersistanceTesting;

import model.SneakerEntry;

import static org.junit.jupiter.api.Assertions.assertEquals;

//This class checks if the given sneaker entry has the identical info given
public class SneakerTestJson {
    //EFFECT: checks if the given sneaker entry has the same given name, price and quantity.
    protected void checkSneakerEntry(String name, double pricePaid, int quantity, SneakerEntry s) {
        assertEquals(name, s.getName());
        assertEquals(pricePaid, s.getRetailPrice());
        assertEquals(quantity, s.getQuantityBought());

    }
}
