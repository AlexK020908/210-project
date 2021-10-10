package model;

import model.investment.CookGroupPurchaseList;
import model.investment.ProxyPurchaseList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.ProxyType.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProxyPurchaseListTest {

    ProxyPurchaseList ProxyPurchaseList;

    //initialize some Cook Group Subscription Entries to test add and remove methods
    private ProxyEntry Oculus = new ProxyEntry("Oculus", ISP,59.99);
    private ProxyEntry OculusTwo = new ProxyEntry("Oculus", DataCenter,39.99);
    private ProxyEntry OculusThree = new ProxyEntry("Oculus", CaptchaProxy,30.00);
    private ProxyEntry LeafProxies = new ProxyEntry("Leaf proxies", ISP, 29.99);
    private ProxyEntry LeafProxiesTwo = new ProxyEntry("Leaf proxies", DataCenter, 19.99);
    private ProxyEntry LeafProxiesThree = new ProxyEntry("Leaf proxies", CaptchaProxy, 20.00);
    private ProxyEntry OxyLabs = new ProxyEntry("OxyLabs", ISP,25.00);
    private ProxyEntry OxyLabsTwo = new ProxyEntry("OxyLabs", DataCenter,15.00);
    private ProxyEntry OxyLabsThree = new ProxyEntry("OxyLabs", CaptchaProxy ,19.99);
    private ProxyEntry SmartProxy = new ProxyEntry("Smart Proxy", ISP,17.00);
    private ProxyEntry SmartProxyTwo = new ProxyEntry("Smart Proxy", DataCenter,10.00);
    private ProxyEntry SmartProxyThree = new ProxyEntry("Smart Proxy", CaptchaProxy,13.00);


    @BeforeEach
    public void setUp(){
        ProxyPurchaseList = new ProxyPurchaseList();

    }

    @Test
    public void testAddOneEntry() {
        assertTrue(ProxyPurchaseList.addEntry(Oculus));
        assertEquals(1,ProxyPurchaseList.getLength());
        assertEquals(59.99, ProxyPurchaseList.getTotalMoneySpent());
        assertEquals(0, ProxyPurchaseList.indexOf(Oculus));
    }

    @Test
    public void testAddSecondEntryDifferentType() {
        assertTrue(ProxyPurchaseList.addEntry(Oculus));
        assertTrue(ProxyPurchaseList.addEntry(OculusTwo));
        assertEquals(2,ProxyPurchaseList.getLength());
        assertEquals(Oculus.getPricePaidSoFar()+OculusTwo.getPricePaidSoFar(),
                ProxyPurchaseList.getTotalMoneySpent());
        assertEquals(0, ProxyPurchaseList.indexOf(Oculus));
        assertEquals(1, ProxyPurchaseList.indexOf(OculusTwo));

        //diff code portion
        assertTrue(ProxyPurchaseList.addEntry(OculusThree));
        assertEquals(3,ProxyPurchaseList.getLength());
        assertEquals(Oculus.getPricePaidSoFar()+ OculusTwo.getPricePaidSoFar()
                +OculusThree.getPricePaidSoFar(), ProxyPurchaseList.getTotalMoneySpent());
        assertEquals(2, ProxyPurchaseList.indexOf(OculusThree));
    }

    @Test
    public void testAddSecondEntrySameType() {
        assertTrue(ProxyPurchaseList.addEntry(Oculus));
        assertFalse(ProxyPurchaseList.addEntry(Oculus));
        assertEquals(1,ProxyPurchaseList.getLength());
        assertEquals((Oculus.getPricePaidSoFar()), ProxyPurchaseList.getTotalMoneySpent());
        //the reason why we did not times oculus's price by two is becuase addEntry multiplied it for us already
        assertEquals(0, ProxyPurchaseList.indexOf(Oculus));
    }

    @Test
    public void testAddManyEntriesDifferentType() {
        assertTrue(ProxyPurchaseList.addEntry(OculusTwo));
        assertTrue(ProxyPurchaseList.addEntry(OculusThree));
        assertTrue(ProxyPurchaseList.addEntry(LeafProxies));
        assertTrue(ProxyPurchaseList.addEntry(OxyLabs));
        assertEquals(4,ProxyPurchaseList.getLength());
        assertEquals(OculusTwo.getPricePaidSoFar() + OculusThree.getPricePaidSoFar()
                + LeafProxies.getPricePaidSoFar() + OxyLabs.getPricePaidSoFar(),
                ProxyPurchaseList.getTotalMoneySpent());
        assertEquals(0, ProxyPurchaseList.indexOf(OculusTwo));
        assertEquals(1, ProxyPurchaseList.indexOf(OculusThree));
        assertEquals(2, ProxyPurchaseList.indexOf(LeafProxies));
        assertEquals(3, ProxyPurchaseList.indexOf(OxyLabs));
    }

    @Test
    public void testAddManyEntriesSameType() {
        assertTrue(ProxyPurchaseList.addEntry(OculusTwo));
        assertTrue(ProxyPurchaseList.addEntry(LeafProxies));
        assertTrue(ProxyPurchaseList.addEntry(SmartProxyTwo));
        //diff portion
        assertTrue(ProxyPurchaseList.addEntry(SmartProxy));
        assertTrue(ProxyPurchaseList.addEntry(OxyLabsTwo));
        assertTrue(ProxyPurchaseList.addEntry(OxyLabsThree));

        //ADDING multiple overlapping entries
        assertFalse(ProxyPurchaseList.addEntry(SmartProxyTwo));
        assertFalse(ProxyPurchaseList.addEntry(OculusTwo));

        assertEquals(6,ProxyPurchaseList.getLength());
        assertEquals(OculusTwo.getPricePaidSoFar() + LeafProxies.getPricePaidSoFar()
                + SmartProxyTwo.getPricePaidSoFar() + SmartProxy.pricePaidSoFar
                + OxyLabsTwo.getPricePaidSoFar() + OxyLabsThree.getPricePaidSoFar()
                , ProxyPurchaseList.getTotalMoneySpent());
        assertEquals(0, ProxyPurchaseList.indexOf(OculusTwo));
        assertEquals(1, ProxyPurchaseList.indexOf(LeafProxies));
        assertEquals(2, ProxyPurchaseList.indexOf(SmartProxyTwo));
        assertEquals(3, ProxyPurchaseList.indexOf(SmartProxy));
        assertEquals(4, ProxyPurchaseList.indexOf(OxyLabsTwo));
        assertEquals(5, ProxyPurchaseList.indexOf(OxyLabsThree));


    }

    //remove method test
    @Test
    public void testRemoveDoesNotExist(){
        assertFalse(ProxyPurchaseList.removeEntry(OculusTwo));
        assertFalse(ProxyPurchaseList.removeEntry(OxyLabsThree));
    }

    @Test
    public void testRemoveOneEntry(){
        ProxyPurchaseList.addEntry(Oculus);
        assertEquals(1,ProxyPurchaseList.getLength());
        //remove the entry
        assertTrue(ProxyPurchaseList.removeEntry(Oculus));
        assertEquals(0,ProxyPurchaseList.getLength());

        //two entries in the list
        ProxyPurchaseList.addEntry(Oculus);
        ProxyPurchaseList.addEntry(OculusTwo);
        //REMOVING one entry
        assertTrue(ProxyPurchaseList.removeEntry(Oculus));
        assertEquals(1,ProxyPurchaseList.getLength());
        assertEquals(0, ProxyPurchaseList.indexOf(OculusTwo));
        //continue to remove another entry
        assertTrue(ProxyPurchaseList.removeEntry(OculusTwo));
        assertEquals(0,ProxyPurchaseList.getLength());

        //initialize ALL entries in the list
        ProxyPurchaseList.addEntry(Oculus);
        ProxyPurchaseList.addEntry(OculusTwo);
        ProxyPurchaseList.addEntry(OculusThree);
        ProxyPurchaseList.addEntry(LeafProxies);
        ProxyPurchaseList.addEntry(LeafProxiesTwo);
        ProxyPurchaseList.addEntry(LeafProxiesThree);
        ProxyPurchaseList.addEntry(SmartProxy);
        ProxyPurchaseList.addEntry(SmartProxyTwo);
        ProxyPurchaseList.addEntry(SmartProxyThree);
        ProxyPurchaseList.addEntry(OxyLabs);
        ProxyPurchaseList.addEntry(OxyLabsTwo);
        ProxyPurchaseList.addEntry(OxyLabsThree);


        //remove an entry in the middle
        assertTrue(ProxyPurchaseList.removeEntry(LeafProxiesThree));
        assertEquals(11, ProxyPurchaseList.getLength());
        assertEquals(5, ProxyPurchaseList.indexOf(SmartProxy));

        //remove all entries
        assertTrue(ProxyPurchaseList.removeEntry(Oculus));
        assertTrue(ProxyPurchaseList.removeEntry(OculusTwo));
        assertTrue(ProxyPurchaseList.removeEntry(OculusThree));
        assertTrue(ProxyPurchaseList.removeEntry(LeafProxies));
        assertTrue(ProxyPurchaseList.removeEntry(LeafProxiesTwo));
        assertTrue(ProxyPurchaseList.removeEntry(SmartProxy));
        assertTrue(ProxyPurchaseList.removeEntry(SmartProxyTwo));
        assertTrue(ProxyPurchaseList.removeEntry(SmartProxyThree));
        assertTrue(ProxyPurchaseList.removeEntry(OxyLabs));
        assertTrue(ProxyPurchaseList.removeEntry(OxyLabsTwo));
        assertTrue(ProxyPurchaseList.removeEntry(OxyLabsThree));
        assertEquals(0, ProxyPurchaseList.getLength());
    }

    @Test
    public void testTotalMoneySpend(){

        //cases: no purchases on cook group
        assertEquals(0, ProxyPurchaseList.getTotalMoneySpent());

        //case 2: one entry
        ProxyPurchaseList.addEntry(Oculus);
        assertEquals(Oculus.getPricePaidSoFar(), ProxyPurchaseList.getTotalMoneySpent());

        //case 3: add another entry
        ProxyPurchaseList.addEntry(OculusTwo);
        assertEquals(Oculus.getPricePaidSoFar() + OculusTwo.getPricePaidSoFar()
                , ProxyPurchaseList.getTotalMoneySpent());

        //case 4: add existing purchase
        ProxyPurchaseList.addEntry(Oculus);
        assertEquals(Oculus.getPricePaidSoFar() + OculusTwo.getPricePaidSoFar()
                , ProxyPurchaseList.getTotalMoneySpent());

        //case 5: add the rest of entries that do not already exist in the list
        ProxyPurchaseList.addEntry(OculusThree);
        ProxyPurchaseList.addEntry(LeafProxies);
        ProxyPurchaseList.addEntry(LeafProxiesTwo);
        ProxyPurchaseList.addEntry(LeafProxiesThree);
        ProxyPurchaseList.addEntry(SmartProxy);
        ProxyPurchaseList.addEntry(SmartProxyTwo);
        ProxyPurchaseList.addEntry(SmartProxyThree);
        ProxyPurchaseList.addEntry(OxyLabs);
        ProxyPurchaseList.addEntry(OxyLabsTwo);
        ProxyPurchaseList.addEntry(OxyLabsThree);
        assertEquals(Oculus.getPricePaidSoFar() + OculusTwo.getPricePaidSoFar()
                        + OculusThree.getPricePaidSoFar()
                        + LeafProxies.getPricePaidSoFar()
                        + LeafProxiesTwo.getPricePaidSoFar()
                        + LeafProxiesThree.getPricePaidSoFar()
                        + SmartProxy.getPricePaidSoFar()
                        + SmartProxyTwo.getPricePaidSoFar()
                        + SmartProxyThree.getPricePaidSoFar()
                        + OxyLabs.getPricePaidSoFar()
                        + OxyLabsTwo.getPricePaidSoFar()
                        + OxyLabsThree.getPricePaidSoFar()
               , ProxyPurchaseList.getTotalMoneySpent());


    }
}
