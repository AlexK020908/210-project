package model.PersistanceTesting;

import model.EntryType;
import model.investment.CookGroupPurchaseList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistance.JsonReaderForCookGroupList;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//json reader test for cook hroup test
public class JsonReaderForCookGroupTest extends supportEntryTestJson{
    CookGroupPurchaseList cookGroupPurchaseList;


    @BeforeEach
    public void SetUp(){
        cookGroupPurchaseList = new CookGroupPurchaseList();
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReaderForCookGroupList jsonReaderForCookGroup = new JsonReaderForCookGroupList
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
        JsonReaderForCookGroupList jsonReaderForCookGroup = new JsonReaderForCookGroupList
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
        JsonReaderForCookGroupList jsonReaderForCookGroup = new JsonReaderForCookGroupList
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
