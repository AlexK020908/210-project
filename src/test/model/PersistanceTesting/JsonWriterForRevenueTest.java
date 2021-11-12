package model.PersistanceTesting;

import model.AmountException;
import model.Revenue;
import model.RevenueList;
import org.junit.jupiter.api.Test;
import persistance.JsonReaderForRevenueList;
import persistance.JsonWriterForRevenueList;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//json writer test for revenue list
public class JsonWriterForRevenueTest {
    RevenueList revenueList;
    JsonWriterForRevenueList jsonWriterForRevenueList;
    JsonReaderForRevenueList jsonReaderForRevenueList;



    @Test
    public void testNoFileExist() {

        try {
            revenueList = new RevenueList();
            jsonWriterForRevenueList = new JsonWriterForRevenueList("./data/my\0illegal:fileName.json");
            jsonWriterForRevenueList.open();
            fail("the file does not exist");
        } catch (FileNotFoundException e) {
            //good
        }
    }

    @Test
    public void testEmptyFileWriter() {
        try {
            revenueList = new RevenueList();
            jsonWriterForRevenueList = new JsonWriterForRevenueList("./data/emptyRevenueTest.json");
            jsonWriterForRevenueList.open();
            jsonWriterForRevenueList.write(revenueList);
            jsonWriterForRevenueList.close();

            jsonReaderForRevenueList = new JsonReaderForRevenueList("./data/emptyRevenueTest.json");
            revenueList = jsonReaderForRevenueList.read();
            assertEquals(revenueList.getLength(), 0);

        } catch (FileNotFoundException e) {
            fail("exception should not have been thrown");
        } catch (IOException e) {
            fail("exception IO should not have been thrown");
        } catch (AmountException e) {
            fail("no exception is to be thrown");
        }
    }

    @Test
    public void testWriterGeneralRoom() {
        try {
            Revenue r1 = new Revenue(100);
            Revenue r2 = new Revenue(200);
            revenueList = new RevenueList();
            revenueList.addNewRevenue(r1);
            revenueList.addNewRevenue(r2);
            jsonWriterForRevenueList = new JsonWriterForRevenueList("./data/generalRevenueTest.json");
            jsonWriterForRevenueList.open();
            jsonWriterForRevenueList.write(revenueList);
            jsonWriterForRevenueList.close();

            //now read the entries from the list
            jsonReaderForRevenueList = new JsonReaderForRevenueList("./data/generalRevenueTest.json");
            revenueList = jsonReaderForRevenueList.read();
            assertEquals(revenueList.getLength(), 2);
            assertEquals(300, revenueList.calculateTotalRevenue());


        } catch (FileNotFoundException e) {
            fail("file was a valid file name");
        } catch (IOException e) {
            fail("file should be read");
        } catch (AmountException e) {
            fail("no exception is to be thrown");
        }

    }

    @Test
    public void testWriterGeneralRoomInvalidRevenue() {
        try {
            Revenue r1 = new Revenue(100);
            Revenue r2 = new Revenue(-20);
            revenueList = new RevenueList();
            revenueList.addNewRevenue(r1);
            revenueList.addNewRevenue(r2);
            jsonWriterForRevenueList = new JsonWriterForRevenueList("./data/generalRevenueTest2.json");
            jsonWriterForRevenueList.open();
            jsonWriterForRevenueList.write(revenueList);
            jsonWriterForRevenueList.close();

            //now read the entries from the list
            jsonReaderForRevenueList = new JsonReaderForRevenueList("./data/generalRevenueTest2.json");
            revenueList = jsonReaderForRevenueList.read();
            assertEquals(revenueList.getLength(), 2);
            assertEquals(300, revenueList.calculateTotalRevenue());
            fail("invalid revenue in file");

        } catch (FileNotFoundException e) {
            fail("file was a valid file name");
        } catch (IOException e) {
            fail("file should be read");
        } catch (AmountException e) {
        }

    }
}
