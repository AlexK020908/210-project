package model.PersistanceTesting;

import model.*;
import model.investment.*;
import org.junit.jupiter.api.Test;
import persistance.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterForSupportEntryTest extends supportEntryTestJson {

    ProxyPurchaseList proxyPurchaseList;
    CookGroupPurchaseList cookGroupPurchaseList;
    ThirdPartyCaptchaSolversPurchaseList thirdPartyCaptchaSolversPurchaseList;
    JsonWriterForSupportEntries jsonWriterForSupportEntries;
    JsonReaderForProxy jsonReaderForProxy;
    JsonReaderForCookGroupList jsonReaderForCookGroup;
    JsonReaderForThirdPartySolvers jsonReaderForThirdPartySolvers;


    //fields:
    ProxyEntry p1 = new ProxyEntry("Oculus", 10.99);
    ProxyEntry p2 = new ProxyEntry("Leaf Proxies", 15.99);
    CookGroupSubscriptionEntry c1 = new CookGroupSubscriptionEntry("Forbidden", 12.99);
    CookGroupSubscriptionEntry c2 = new CookGroupSubscriptionEntry("DropAlert", 16.99);
    ThirdPartyCaptchaSolverEntry t1 = new ThirdPartyCaptchaSolverEntry("CapMonster", 13.99);
    ThirdPartyCaptchaSolverEntry t2 = new ThirdPartyCaptchaSolverEntry("AntiCaptcha", 13.99);

    @Test
    public void testNoFileExistForAllSupportEntries() {
        try {
            proxyPurchaseList = new ProxyPurchaseList();
            jsonWriterForSupportEntries = new JsonWriterForSupportEntries("./data/my\0illegal:fileName.json");
            jsonWriterForSupportEntries.open();
            fail("the file does not exist");
        } catch (FileNotFoundException e) {
            //good
        }
        try {
            cookGroupPurchaseList = new CookGroupPurchaseList();
            jsonWriterForSupportEntries = new JsonWriterForSupportEntries
                    ("./data/my\0illegal:fileName.json1");
            jsonWriterForSupportEntries.open();
            fail("the file does not exist");
        } catch (FileNotFoundException e) {
            //good
        }
        try {
            thirdPartyCaptchaSolversPurchaseList = new ThirdPartyCaptchaSolversPurchaseList();
            jsonWriterForSupportEntries = new JsonWriterForSupportEntries
                    ("./data/my\0illegal:fileName.json2");
            jsonWriterForSupportEntries.open();
            fail("the file does not exist");
        } catch (FileNotFoundException e) {
            //good
        }
    }



    @Test
    public void testEmptyFileWriter() {
        try {
            proxyPurchaseList = new ProxyPurchaseList();
            //for proxy
            jsonWriterForSupportEntries = new JsonWriterForSupportEntries("./data/emptyProxyTest.json");
            openWriteAndCloseWriter(proxyPurchaseList);

            jsonReaderForProxy = new JsonReaderForProxy("./data/emptyProxyTest.json");
            proxyPurchaseList = jsonReaderForProxy.read();
            assertEquals(proxyPurchaseList.getType(), EntryType.PROXY);
            assertEquals(proxyPurchaseList.getLength(), 0);

            } catch (FileNotFoundException e) {
                fail("exception should not have been thrown");
            } catch (IOException e) {
                fail("exception IO should not have been thrown");
            }
        try
        {
            cookGroupPurchaseList = new CookGroupPurchaseList();
            jsonWriterForSupportEntries = new JsonWriterForSupportEntries
                    ("./data/emptyCookGroupEntryListTest.json");
            openWriteAndCloseWriter(cookGroupPurchaseList);

            jsonReaderForCookGroup = new JsonReaderForCookGroupList("./data/emptyCookGroupEntryListTest.json");
            jsonReaderForCookGroup.read();
            assertEquals(cookGroupPurchaseList.getType(), EntryType.CookGroup);
            assertEquals(cookGroupPurchaseList.getLength(), 0);

        } catch (FileNotFoundException e) {
             fail("file should have been found");
        } catch (IOException e) {
            fail("exception IO should not have been thrown");
        }

        try
        {
            thirdPartyCaptchaSolversPurchaseList = new ThirdPartyCaptchaSolversPurchaseList();
            jsonWriterForSupportEntries = new JsonWriterForSupportEntries
                    ("./data/emptyThirdPartySolverListTest.json");
            openWriteAndCloseWriter(thirdPartyCaptchaSolversPurchaseList);

            jsonReaderForThirdPartySolvers = new JsonReaderForThirdPartySolvers
                    ("./data/emptyThirdPartySolverListTest.json");
            jsonReaderForThirdPartySolvers.read();
            assertEquals(thirdPartyCaptchaSolversPurchaseList.getType(), EntryType.ThirdPartSolver);
            assertEquals(thirdPartyCaptchaSolversPurchaseList.getLength(), 0);

        } catch (FileNotFoundException e) {
            fail("file should have been found");
        } catch (IOException e) {
            fail("exception IO should not have been thrown");
        }

    }

    private void openWriteAndCloseWriter(SupportEntryList<? extends SupportEntry> entry) throws FileNotFoundException {
        jsonWriterForSupportEntries.open();
        jsonWriterForSupportEntries.write(entry);
        jsonWriterForSupportEntries.close();
    }

    @Test
    public void testGeneralFileWriter() {
        try {
            proxyPurchaseList = new ProxyPurchaseList();
            proxyPurchaseList.addEntry(p1);
            proxyPurchaseList.addEntry(p2);
            //for proxy
            jsonWriterForSupportEntries = new JsonWriterForSupportEntries("./data/GeneralProxyTest.json");
            openWriteAndCloseWriter(proxyPurchaseList);

            jsonReaderForProxy = new JsonReaderForProxy("./data/GeneralProxyTest.json");
            proxyPurchaseList = jsonReaderForProxy.read();
            assertEquals(proxyPurchaseList.getType(), EntryType.PROXY);
            assertEquals(2, proxyPurchaseList.getLength());
            checkSupportEntry(p1.getName(), p1.getPricePaid(), proxyPurchaseList.get(0));
            checkSupportEntry(p2.getName(), p2.getPricePaid(), proxyPurchaseList.get(1));

        } catch (FileNotFoundException e) {
            fail("exception should not have been thrown");
        } catch (IOException e) {
            fail("exception IO should not have been thrown");
        }
        try
        {
            cookGroupPurchaseList = new CookGroupPurchaseList();
            cookGroupPurchaseList.addEntry(c1);
            cookGroupPurchaseList.addEntry(c2);
            jsonWriterForSupportEntries = new JsonWriterForSupportEntries
                    ("./data/generalCookGroupEntryListTest.json");
            openWriteAndCloseWriter(cookGroupPurchaseList);

            jsonReaderForCookGroup = new JsonReaderForCookGroupList("./data/generalCookGroupEntryListTest.json");
            jsonReaderForCookGroup.read();
            assertEquals(cookGroupPurchaseList.getType(), EntryType.CookGroup);
            assertEquals(cookGroupPurchaseList.getLength(), 2);
            checkSupportEntry(c1.getName(), c1.getPricePaid(), cookGroupPurchaseList.get(0));
            checkSupportEntry(c2.getName(), c2.getPricePaid(), cookGroupPurchaseList.get(1));

        } catch (FileNotFoundException e) {
            fail("file should have been found");
        } catch (IOException e) {
            fail("exception IO should not have been thrown");
        }

        try
        {
            thirdPartyCaptchaSolversPurchaseList = new ThirdPartyCaptchaSolversPurchaseList();
            thirdPartyCaptchaSolversPurchaseList.addEntry(t1);
            thirdPartyCaptchaSolversPurchaseList.addEntry(t2);
            jsonWriterForSupportEntries = new JsonWriterForSupportEntries
                    ("./data/generalThirdPartySolverListTest.json");
            openWriteAndCloseWriter(thirdPartyCaptchaSolversPurchaseList);

            jsonReaderForThirdPartySolvers = new JsonReaderForThirdPartySolvers
                    ("./data/generalThirdPartySolverListTest.json");
            jsonReaderForThirdPartySolvers.read();
            assertEquals(thirdPartyCaptchaSolversPurchaseList.getType(), EntryType.ThirdPartSolver);
            assertEquals(thirdPartyCaptchaSolversPurchaseList.getLength(), 2);
            checkSupportEntry(t1.getName(), t1.getPricePaid(), thirdPartyCaptchaSolversPurchaseList.get(0));
            checkSupportEntry(t2.getName(), t2.getPricePaid(), thirdPartyCaptchaSolversPurchaseList.get(1));

        } catch (FileNotFoundException e) {
            fail("file should have been found");
        } catch (IOException e) {
            fail("exception IO should not have been thrown");
        }


    }


}


