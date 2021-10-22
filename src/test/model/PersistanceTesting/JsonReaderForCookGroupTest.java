package model.PersistanceTesting;

import model.EntryType;
import model.investment.CookGroupPurchaseList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistance.JsonReaderForCookGroup;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderForCookGroupTest extends supportEntryTestJson{
    CookGroupPurchaseList cookGroupPurchaseList;


    @BeforeEach
    public void SetUp(){
        cookGroupPurchaseList = new CookGroupPurchaseList();
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReaderForCookGroup jsonReaderForCookGroup = new JsonReaderForCookGroup
                ("./data/noSuchFile.json");
        try {
            cookGroupPurchaseList = jsonReaderForCookGroup.read();
            fail();
        } catch (IOException e) {
            System.out.println("please enter a correct file directory ");
        }

    }

    @Test
    void testReaderEmptySolversFile() {
        JsonReaderForCookGroup jsonReaderForCookGroup = new JsonReaderForCookGroup
                ("./data/emptyCookGroupEntryListTest.json");
        try {
            cookGroupPurchaseList = jsonReaderForCookGroup.read();
            assertEquals(0, cookGroupPurchaseList.getLength());
            assertEquals(0, cookGroupPurchaseList.getTotalMoneySpent());
            assertEquals(EntryType.CookGroup, cookGroupPurchaseList.getType());
        } catch (IOException e) {
            fail("no exception should been caught");
        }

    }

    @Test
    void testReaderGeneralSolversFile() {
        //read from current file
        JsonReaderForCookGroup jsonReaderForCookGroup = new JsonReaderForCookGroup
                ("./data/generalCookGroupEntryListTest.json");
        try {
            cookGroupPurchaseList = jsonReaderForCookGroup.read();
            assertEquals(2, cookGroupPurchaseList.getLength());
            assertEquals(12.99+16.99 , cookGroupPurchaseList.getTotalMoneySpent());
            assertEquals(EntryType.CookGroup, cookGroupPurchaseList.getType());
            checkSupportEntry( "Forbidden", 12.99, cookGroupPurchaseList.get(0));
            checkSupportEntry("DropAlert",16.99, cookGroupPurchaseList.get(1));
        } catch (IOException e) {
            fail("no exception should been caught");
        }

    }
}
