package model.PersistanceTesting;

import model.*;
import model.investment.SneakerPurchaseList;
import org.junit.jupiter.api.Test;
import persistance.JsonReaderForSneaker;
import persistance.JsonWriteForSneakers;

import java.io.FileNotFoundException;
import java.io.IOException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//Json writer test for the sneaker entries
public class jsonWriterForSneakerTest extends SneakerTestJson{
    SneakerPurchaseList sneakerPurchaseList;
    JsonWriteForSneakers jsonWriteForSneakers;
    JsonReaderForSneaker jsonReaderForSneaker;

    //Fields



    @Test
    public void testNoFileExist() {

        try {
            sneakerPurchaseList = new SneakerPurchaseList();
            jsonWriteForSneakers = new JsonWriteForSneakers("./data/my\0illegal:fileName.json");
            jsonWriteForSneakers.open();
            fail("the file does not exist");
        } catch (FileNotFoundException e) {
            //good
        }
    }

    @Test
    public void testEmptyFileWriter() {
        try {
            sneakerPurchaseList = new SneakerPurchaseList();
            jsonWriteForSneakers = new JsonWriteForSneakers("./data/emptySneakerTest.json");
            jsonWriteForSneakers.open();
            jsonWriteForSneakers.write(sneakerPurchaseList);
            jsonWriteForSneakers.close();

            jsonReaderForSneaker = new JsonReaderForSneaker("./data/emptySneakerTest.json");
            sneakerPurchaseList = jsonReaderForSneaker.read();
            assertEquals(sneakerPurchaseList.getType(), EntryType.Sneaker);
            assertEquals(sneakerPurchaseList.getLength(), 0);

        } catch (FileNotFoundException e) {
            fail("exception should not have been thrown");
        } catch (IOException e) {
            fail("exception IO should not have been thrown");
        } catch (NameException e) {
            fail();
        } catch (AmountException e) {
            fail();
        } catch (QuantityException e) {
            fail();
        }
    }

    @Test
    public void testWriterGeneralRoom() {
        try {
            SneakerEntry s1 = new SneakerEntry("Nike dunk low", 13.99, 10);
            SneakerEntry s2 = new SneakerEntry("Yeezy", 20.00, 10);
            SneakerPurchaseList sneakerPurchaseList = new SneakerPurchaseList();
            sneakerPurchaseList.addEntry(s1);
            sneakerPurchaseList.addEntry(s2);
            jsonWriteForSneakers = new JsonWriteForSneakers("./data/generalSneakerTest.json");
            jsonWriteForSneakers.open();
            jsonWriteForSneakers.write(sneakerPurchaseList);
            jsonWriteForSneakers.close();

            //now read the entries from the list
            jsonReaderForSneaker = new JsonReaderForSneaker("./data/generalSneakerTest.json");
            sneakerPurchaseList = jsonReaderForSneaker.read();
            assertEquals(sneakerPurchaseList.getType(), EntryType.Sneaker);
            assertEquals(sneakerPurchaseList.getLength(), 2);
            checkSneakerEntry(s1.getName(), s1.getRetailPrice(), s1.getQuantityBought(), sneakerPurchaseList.get(0));
            checkSneakerEntry(s2.getName(), s2.getRetailPrice(), s2.getQuantityBought(), sneakerPurchaseList.get(1));


        } catch (FileNotFoundException e) {
            fail("file was a valid file name");
        } catch (IOException e) {
            fail("file should be read");
        } catch (NameException | AmountException | QuantityException e) {
            fail();
        }

    }


    @Test
    public void testWriterGeneralRoomInvalidSneakerName() {
        try {
            SneakerEntry s1 = new SneakerEntry("Nike dunk low", 13.99, 10);
            SneakerEntry s2 = new SneakerEntry("Yeezy", 20.00, 10);
            SneakerEntry s3 = new SneakerEntry("", 20, 10);
            SneakerPurchaseList sneakerPurchaseList = new SneakerPurchaseList();
            sneakerPurchaseList.addEntry(s1);
            sneakerPurchaseList.addEntry(s2);
            sneakerPurchaseList.addEntry(s3);
            jsonWriteForSneakers = new JsonWriteForSneakers("./data/generalSneakerTest.json");
            jsonWriteForSneakers.open();
            jsonWriteForSneakers.write(sneakerPurchaseList);
            jsonWriteForSneakers.close();

            //now read the entries from the list
            jsonReaderForSneaker = new JsonReaderForSneaker("./data/generalSneakerTest.json");
            sneakerPurchaseList = jsonReaderForSneaker.read();
            assertEquals(sneakerPurchaseList.getType(), EntryType.Sneaker);
            assertEquals(sneakerPurchaseList.getLength(), 2);
            checkSneakerEntry(s1.getName(), s1.getRetailPrice(), s1.getQuantityBought(), sneakerPurchaseList.get(0));
            checkSneakerEntry(s2.getName(), s2.getRetailPrice(), s2.getQuantityBought(), sneakerPurchaseList.get(1));
            fail();
        } catch (FileNotFoundException e) {
            fail("file was a valid file name");
        } catch (IOException e) {
            fail("file should be read");
        } catch (NameException e) {
            //good
        } catch (AmountException e) {
            fail();
        } catch (QuantityException e) {
            fail();
        }

    }

    @Test
    public void testWriterGeneralRoomInvalidSneakerPrice() {
        try {
            SneakerEntry s1 = new SneakerEntry("Nike dunk low", 13.99, 10);
            SneakerEntry s2 = new SneakerEntry("Yeezy", 20.00, 10);
            SneakerEntry s3 = new SneakerEntry("sneaker", -20, 20);
            SneakerPurchaseList sneakerPurchaseList = new SneakerPurchaseList();
            sneakerPurchaseList.addEntry(s1);
            sneakerPurchaseList.addEntry(s2);
            sneakerPurchaseList.addEntry(s3);
            jsonWriteForSneakers = new JsonWriteForSneakers("./data/generalSneakerTest.json");
            jsonWriteForSneakers.open();
            jsonWriteForSneakers.write(sneakerPurchaseList);
            jsonWriteForSneakers.close();

            //now read the entries from the list
            jsonReaderForSneaker = new JsonReaderForSneaker("./data/generalSneakerTest.json");
            sneakerPurchaseList = jsonReaderForSneaker.read();
            assertEquals(sneakerPurchaseList.getType(), EntryType.Sneaker);
            assertEquals(sneakerPurchaseList.getLength(), 2);
            checkSneakerEntry(s1.getName(), s1.getRetailPrice(), s1.getQuantityBought(), sneakerPurchaseList.get(0));
            checkSneakerEntry(s2.getName(), s2.getRetailPrice(), s2.getQuantityBought(), sneakerPurchaseList.get(1));


        } catch (FileNotFoundException e) {
            fail("file was a valid file name");
        } catch (IOException e) {
            fail("file should be read");
        } catch (NameException e) {
            fail();
        } catch (AmountException e) {
            //good
        } catch (QuantityException e) {
            fail();
        }

    }

    @Test
    public void testWriterGeneralRoomInvalidSneakerQuantity() {
        try {
            SneakerEntry s1 = new SneakerEntry("Nike dunk low", 13.99, 10);
            SneakerEntry s2 = new SneakerEntry("Yeezy", 20.00, 10);
            SneakerEntry s3 = new SneakerEntry("sneaker", 20, -2);
            SneakerPurchaseList sneakerPurchaseList = new SneakerPurchaseList();
            sneakerPurchaseList.addEntry(s1);
            sneakerPurchaseList.addEntry(s2);
            sneakerPurchaseList.addEntry(s3);
            jsonWriteForSneakers = new JsonWriteForSneakers("./data/generalSneakerTest.json");
            jsonWriteForSneakers.open();
            jsonWriteForSneakers.write(sneakerPurchaseList);
            jsonWriteForSneakers.close();

            //now read the entries from the list
            jsonReaderForSneaker = new JsonReaderForSneaker("./data/generalSneakerTest.json");
            sneakerPurchaseList = jsonReaderForSneaker.read();
            assertEquals(sneakerPurchaseList.getType(), EntryType.Sneaker);
            assertEquals(sneakerPurchaseList.getLength(), 2);
            checkSneakerEntry(s1.getName(), s1.getRetailPrice(), s1.getQuantityBought(), sneakerPurchaseList.get(0));
            checkSneakerEntry(s2.getName(), s2.getRetailPrice(), s2.getQuantityBought(), sneakerPurchaseList.get(1));
            fail();

        } catch (FileNotFoundException e) {
            fail("file was a valid file name");
        } catch (IOException e) {
            fail("file should be read");
        } catch (NameException e) {
            fail();
        } catch (AmountException e) {
            fail();
        } catch (QuantityException e) {
            //good
        }

    }
}
