package model.PersistanceTesting;

import model.EntryType;
import model.investment.ProxyPurchaseList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistance.JsonReaderForProxy;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderForProxyTest extends supportEntryTestJson{
    ProxyPurchaseList proxyPurchaseList;


    @BeforeEach
    public void SetUp(){
        proxyPurchaseList = new ProxyPurchaseList();
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReaderForProxy jsonReaderForProxy = new JsonReaderForProxy
                ("./data/noSuchFile.json");
        try {
            proxyPurchaseList = jsonReaderForProxy.read();
            fail();
        } catch (IOException e) {
            System.out.println("please enter a correct file directory ");
        }

    }

    @Test
    void testReaderEmptySolversFile() {
        JsonReaderForProxy jsonReaderForProxy = new JsonReaderForProxy
                ("./data/emptyProxyTest.json");
        try {
            proxyPurchaseList = jsonReaderForProxy.read();
            assertEquals(0, proxyPurchaseList.getLength());
            assertEquals(0, proxyPurchaseList.getTotalMoneySpent());
            assertEquals(EntryType.PROXY, proxyPurchaseList.getType());
        } catch (IOException e) {
            fail("no exception should been caught");
        }

    }

    @Test
    void testReaderGeneralSolversFile() {
        //read from current file
        JsonReaderForProxy jsonReaderForProxy = new JsonReaderForProxy
                ("./data/generalProxyTest.json");
        try {
            proxyPurchaseList = jsonReaderForProxy.read();
            assertEquals(2, proxyPurchaseList.getLength());
            assertEquals(15.99+10.99 , proxyPurchaseList.getTotalMoneySpent());
            assertEquals(EntryType.PROXY, proxyPurchaseList.getType());
            checkSupportEntry( "Oculus", 10.99, proxyPurchaseList.get(0));
            checkSupportEntry("Leaf Proxies",15.99, proxyPurchaseList.get(1));
        } catch (IOException e) {
            fail("no exception should been caught");
        }

    }
}
