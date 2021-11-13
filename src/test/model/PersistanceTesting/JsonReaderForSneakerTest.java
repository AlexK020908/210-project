package model.PersistanceTesting;

import model.AmountException;
import model.EntryType;
import model.NameException;
import model.QuantityException;
import model.investment.SneakerPurchaseList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistance.JsonReaderForSneaker;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//json reader test for sneaker purchase list
public class JsonReaderForSneakerTest extends SneakerTestJson {
    SneakerPurchaseList sneakerPurchaseList;

    @BeforeEach
    public void setUp() {
        sneakerPurchaseList = new SneakerPurchaseList();
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReaderForSneaker jsonReaderForSneakerTest = new JsonReaderForSneaker
                ("./data/noSuchFile.json");
        try {
            sneakerPurchaseList = jsonReaderForSneakerTest.read();
            fail();
        } catch (IOException e) {
            System.out.println("please enter a correct file directory ");
        } catch (NameException e) {
            fail();
        } catch (AmountException e) {
            fail();
        } catch (QuantityException e) {
           fail();
        }

    }

    @Test
    void testReaderEmptySneakerFile() {
        JsonReaderForSneaker jsonReaderForSneakerTest = new JsonReaderForSneaker("./data/emptySneakerTest.json");
        try {
            sneakerPurchaseList = jsonReaderForSneakerTest.read();
            assertEquals(0, sneakerPurchaseList.getLength());
            assertEquals(0, sneakerPurchaseList.getTotalMoneySpent());
            assertEquals(EntryType.Sneaker, sneakerPurchaseList.getType());
        } catch (IOException | NameException | AmountException | QuantityException e) {
            fail("no exception should been caught");
        }

    }

    @Test
    void testReaderInvalidSneakerNameFile() {
        JsonReaderForSneaker jsonReaderForSneakerTest = new JsonReaderForSneaker("./data/sneakerEntryInvalidNameTest.json");
        try {
            sneakerPurchaseList = jsonReaderForSneakerTest.read();
            assertEquals(2, sneakerPurchaseList.getLength());
            assertEquals(EntryType.Sneaker, sneakerPurchaseList.getType());
            fail("empty name in file found");
        } catch (IOException | AmountException | QuantityException e) {
            fail("no exception should been caught");
        } catch (NameException e) {
            //good
        }

    }

    @Test
    public void testReaderInvalidSneakerPriceFile() {
        JsonReaderForSneaker jsonReaderForSneakerTest = new JsonReaderForSneaker("./data/sneakerEntryInvalidPriceTest.json");
        try {
            sneakerPurchaseList = jsonReaderForSneakerTest.read();
            assertEquals(2, sneakerPurchaseList.getLength());
            assertEquals(EntryType.Sneaker, sneakerPurchaseList.getType());
            fail("invalid price found in file found");
        } catch (IOException | NameException | QuantityException e) {
            fail("no exception should been caught");
        } catch (AmountException e) {
            //good
        }
    }


    @Test
    public void testReaderInvalidSneakerQuantityFile() {
        JsonReaderForSneaker jsonReaderForSneakerTest = new JsonReaderForSneaker("./data/sneakerEntryInvalidQuantityTest.json");
        try {
            sneakerPurchaseList = jsonReaderForSneakerTest.read();
            assertEquals(2, sneakerPurchaseList.getLength());
            assertEquals(EntryType.Sneaker, sneakerPurchaseList.getType());
            fail("invalid price found in file found");
        } catch (IOException | NameException | AmountException e) {
            fail("no exception should been caught");
        } catch (QuantityException e) {
            //good
        }
    }

    @Test
    void testReaderGeneralSneakerFile() {
        //read from current file
        JsonReaderForSneaker jsonReaderForSneakerTest = new JsonReaderForSneaker(
                "./data/generalSneakerTest.json");
        try {
            sneakerPurchaseList = jsonReaderForSneakerTest.read();
            assertEquals(2, sneakerPurchaseList.getLength());
            assertEquals(339.9, sneakerPurchaseList.getTotalMoneySpent());
            assertEquals(EntryType.Sneaker, sneakerPurchaseList.getType());
            checkSneakerEntry("Nike dunk low", 13.99, 10, sneakerPurchaseList.get(0));
            checkSneakerEntry("Yeezy", 20, 10, sneakerPurchaseList.get(1));
        } catch (IOException | NameException | AmountException | QuantityException e) {
            fail("no exception should been caught");

        }
    }
}


