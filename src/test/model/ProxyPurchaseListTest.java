package model;

import model.investment.ProxyPurchaseList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
 //Testing proxy purchase list
public class ProxyPurchaseListTest {

     ProxyPurchaseList ProxyPurchaseList;

     //initialize some Cook Group Subscription Entries to test add and remove methods
     private final ProxyEntry Oculus = new ProxyEntry("Oculus", 59.99);
     private final ProxyEntry OculusTwo = new ProxyEntry("Oculus", 39.99);
     private final ProxyEntry OculusThree = new ProxyEntry("Oculus", 30.00);
     private final ProxyEntry OculusNegativePrice = new ProxyEntry("Oculus", -2.00);
     private final ProxyEntry LeafProxies = new ProxyEntry("Leaf proxies", 29.99);
     private final ProxyEntry LeafProxiesTwo = new ProxyEntry("Leaf proxies", 19.99);
     private final ProxyEntry LeafProxiesThree = new ProxyEntry("Leaf proxies", 20.00);
     private final ProxyEntry OxyLabs = new ProxyEntry("OxyLabs", 25.00);
     private final ProxyEntry OxyLabsTwo = new ProxyEntry("OxyLabs", 15.00);
     private final ProxyEntry OxyLabsThree = new ProxyEntry("OxyLabs", 19.99);
     private final ProxyEntry SmartProxy = new ProxyEntry("Smart Proxy", 17.00);
     private final ProxyEntry SmartProxyTwo = new ProxyEntry("Smart Proxy", 10.00);
     private final ProxyEntry SmartProxyThree = new ProxyEntry("Smart Proxy", 13.00);


     @BeforeEach
     public void setUp() {
         ProxyPurchaseList = new ProxyPurchaseList();

     }

     @Test
     public void testAddOneEntry() {
         assertTrue(ProxyPurchaseList.addEntry(Oculus));
         assertEquals(1, ProxyPurchaseList.getLength());
         assertEquals(59.99, ProxyPurchaseList.getTotalMoneySpent());
         assertEquals(0, ProxyPurchaseList.indexOf(Oculus));
     }

     @Test
     public void testAddSecondEntryDifferentType() {
         assertTrue(ProxyPurchaseList.addEntry(Oculus));
         assertTrue(ProxyPurchaseList.addEntry(SmartProxy));
         assertEquals(2, ProxyPurchaseList.getLength());
         assertEquals(59.99 + 17.00,
                 ProxyPurchaseList.getTotalMoneySpent());
         assertEquals(0, ProxyPurchaseList.indexOf(Oculus));
         assertEquals(1, ProxyPurchaseList.indexOf(SmartProxy));
         //adding a negative value of the same type of oculus
         assertFalse(ProxyPurchaseList.addEntry(OculusNegativePrice));
         assertEquals(59.99 + 17.00, ProxyPurchaseList.getTotalMoneySpent());
         // the value did not change because it OculusNegativePrice has a negative price
     }

     @Test
     public void testAddSecondEntrySameType() {
         assertTrue(ProxyPurchaseList.addEntry(Oculus));
         assertFalse(ProxyPurchaseList.addEntry(OculusTwo));
         assertEquals(1, ProxyPurchaseList.getLength());
         assertEquals((Oculus.getPricePaid()), ProxyPurchaseList.getTotalMoneySpent());
         //the reason why we did not times oculus's price by two is becuase addEntry multiplied it for us already
         assertEquals(0, ProxyPurchaseList.indexOf(Oculus));
     }

     @Test
     public void testAddManyEntriesDifferentType() {
         assertTrue(ProxyPurchaseList.addEntry(OculusTwo));
         assertFalse(ProxyPurchaseList.addEntry(OculusTwo));
         assertEquals(1, ProxyPurchaseList.getLength());

         assertTrue(ProxyPurchaseList.addEntry(LeafProxies));
         assertTrue(ProxyPurchaseList.addEntry(OxyLabs));
         assertEquals(3, ProxyPurchaseList.getLength());
         assertEquals(OculusTwo.getPricePaid()
                         + LeafProxies.getPricePaid() + OxyLabs.getPricePaid(),
                 ProxyPurchaseList.getTotalMoneySpent());
         assertEquals(0, ProxyPurchaseList.indexOf(OculusTwo));
         assertEquals(-1, ProxyPurchaseList.indexOf(OculusThree));
         assertEquals(1, ProxyPurchaseList.indexOf(LeafProxies));
         assertEquals(2, ProxyPurchaseList.indexOf(OxyLabs));
     }

     @Test
     public void testAddManyEntriesSameType() {
         assertTrue(ProxyPurchaseList.addEntry(OculusTwo));
         assertTrue(ProxyPurchaseList.addEntry(LeafProxies));
         assertTrue(ProxyPurchaseList.addEntry(SmartProxyTwo));
         assertTrue(ProxyPurchaseList.addEntry(OxyLabs));
         //diff portion
         assertFalse(ProxyPurchaseList.addEntry(SmartProxy));
         assertFalse(ProxyPurchaseList.addEntry(OxyLabsTwo));
         assertFalse(ProxyPurchaseList.addEntry(OxyLabsThree));

         //ADDING multiple overlapping entries
         assertFalse(ProxyPurchaseList.addEntry(SmartProxyTwo));
         assertFalse(ProxyPurchaseList.addEntry(OculusTwo));

         assertEquals(4, ProxyPurchaseList.getLength());
         assertEquals(OculusTwo.getPricePaid() + LeafProxies.getPricePaid()
                         + SmartProxyTwo.getPricePaid() + OxyLabs.pricePaid
                 , ProxyPurchaseList.getTotalMoneySpent());
         assertEquals(0, ProxyPurchaseList.indexOf(OculusTwo));
         assertEquals(1, ProxyPurchaseList.indexOf(LeafProxies));
         assertEquals(2, ProxyPurchaseList.indexOf(SmartProxyTwo));
         assertEquals(3, ProxyPurchaseList.indexOf(OxyLabs));
         assertEquals(-1, ProxyPurchaseList.indexOf(SmartProxy));
         assertEquals(-1, ProxyPurchaseList.indexOf(OxyLabsTwo));
         assertEquals(-1, ProxyPurchaseList.indexOf(OxyLabsThree));


     }

     //remove method test
     @Test
     public void testRemoveDoesNotExist() {
         assertFalse(ProxyPurchaseList.removeEntry(OculusTwo));
         assertFalse(ProxyPurchaseList.removeEntry(OxyLabsThree));
     }

     @Test
     public void testRemoveOneEntry() {
         ProxyPurchaseList.addEntry(Oculus);
         assertEquals(1, ProxyPurchaseList.getLength());
         //remove the entry
         assertTrue(ProxyPurchaseList.removeEntry(Oculus));
         assertEquals(0, ProxyPurchaseList.getLength());

         //two entries in the list
         ProxyPurchaseList.addEntry(Oculus);
         ProxyPurchaseList.addEntry(OculusTwo);
         //REMOVING one entry
         assertTrue(ProxyPurchaseList.removeEntry(Oculus));
         assertEquals(0, ProxyPurchaseList.getLength());
         //IT IS ZERO becuase we added the same name, only the price is updated

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
         assertFalse(ProxyPurchaseList.removeEntry(LeafProxiesThree));
         assertEquals(4, ProxyPurchaseList.getLength());
         assertEquals(2, ProxyPurchaseList.indexOf(SmartProxy));

         //remove all entries
         assertTrue(ProxyPurchaseList.removeEntry(Oculus));
         assertTrue(ProxyPurchaseList.removeEntry(LeafProxies));
         assertTrue(ProxyPurchaseList.removeEntry(SmartProxy));
         assertTrue(ProxyPurchaseList.removeEntry(OxyLabs));
         assertEquals(0, ProxyPurchaseList.getLength());
     }

     @Test
     public void testTotalMoneySpend() {

         //cases: no purchases on cook group
         assertEquals(0, ProxyPurchaseList.getTotalMoneySpent());

         //case 2: one entry
         ProxyPurchaseList.addEntry(Oculus);
         assertEquals(Oculus.getPricePaid(), ProxyPurchaseList.getTotalMoneySpent());

         //case 3: add another entry
         ProxyPurchaseList.addEntry(OculusTwo);
         assertEquals(Oculus.getPricePaid()
                 , ProxyPurchaseList.getTotalMoneySpent());

         //case 4: add existing purchase
         ProxyPurchaseList.addEntry(Oculus);
         assertEquals(Oculus.getPricePaid()
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
         assertEquals(Oculus.getPricePaid()
                         + LeafProxies.getPricePaid()
                         + SmartProxy.getPricePaid()
                         + OxyLabs.getPricePaid()
                 , ProxyPurchaseList.getTotalMoneySpent());


     }

     @Test
     public void testLengthZero() {
         assertEquals(0, ProxyPurchaseList.getLength());
     }

     @Test
     public void testLengthManyElements() {
         ProxyPurchaseList.addEntry(Oculus);
         assertEquals(1, ProxyPurchaseList.getLength());
         ProxyPurchaseList.addEntry(Oculus);
         assertEquals(1, ProxyPurchaseList.getLength());
         ProxyPurchaseList.addEntry(OculusTwo);
         ProxyPurchaseList.addEntry(OculusThree);
         ProxyPurchaseList.addEntry(LeafProxies);
         assertEquals(2, ProxyPurchaseList.getLength());

         //add all entries..
         ProxyPurchaseList.addEntry(LeafProxiesTwo);
         ProxyPurchaseList.addEntry(LeafProxiesThree);
         ProxyPurchaseList.addEntry(SmartProxy);
         ProxyPurchaseList.addEntry(SmartProxyTwo);
         ProxyPurchaseList.addEntry(SmartProxyThree);
         ProxyPurchaseList.addEntry(OxyLabs);
         ProxyPurchaseList.addEntry(OxyLabsTwo);
         ProxyPurchaseList.addEntry(OxyLabsThree);
         assertEquals(4, ProxyPurchaseList.getLength());

     }

     @Test
     public void testIndexOfElementDoesNotExist() {
         assertEquals(-1, ProxyPurchaseList.indexOf(Oculus));
     }

     @Test
     public void testIndexOfElementInAListExist() {
         //one proxy entry
         ProxyPurchaseList.addEntry(Oculus);
         assertEquals(0, ProxyPurchaseList.indexOf(Oculus));
         //5 proxy entry with overlapping names
         ProxyPurchaseList.addEntry(OculusTwo);
         ProxyPurchaseList.addEntry(OculusThree);
         ProxyPurchaseList.addEntry(LeafProxies);
         ProxyPurchaseList.addEntry(LeafProxiesTwo);
         assertEquals(1, ProxyPurchaseList.indexOf(LeafProxies));
         assertEquals(-1, ProxyPurchaseList.indexOf(LeafProxiesTwo));

         //add the rest
         ProxyPurchaseList.addEntry(LeafProxiesThree);
         ProxyPurchaseList.addEntry(SmartProxy);
         ProxyPurchaseList.addEntry(SmartProxyTwo);
         ProxyPurchaseList.addEntry(SmartProxyThree);
         ProxyPurchaseList.addEntry(OxyLabs);
         ProxyPurchaseList.addEntry(OxyLabsTwo);
         ProxyPurchaseList.addEntry(OxyLabsThree);
         assertEquals(2, ProxyPurchaseList.indexOf(SmartProxy));
         assertEquals(3, ProxyPurchaseList.indexOf(OxyLabs));
         assertEquals(-1, ProxyPurchaseList.indexOf(OxyLabsThree));


     }

     @Test
     public void ProxyEntryToStringTest() {
         //use Oculus as main testing proxy entry
         assertEquals(Oculus.getName() + " " + Oculus.getPricePaid() + " ,", Oculus.toString());
     }

     @Test
     public void getTypeTest() {
         assertEquals(EntryType.PROXY, ProxyPurchaseList.getType());
         assertFalse(EntryType.CookGroup.equals(ProxyPurchaseList.getType()));
         assertFalse(EntryType.Sneaker.equals(ProxyPurchaseList.getType()));
         assertFalse(EntryType.ThirdPartSolver.equals(ProxyPurchaseList.getType()));
     }

     @Test
     public void testToString() {
         //empty list
         assertEquals("", ProxyPurchaseList.toString());
         //some Cook group entries
         ProxyPurchaseList.addEntry(Oculus);
         ProxyPurchaseList.addEntry(LeafProxies);
         assertEquals(Oculus.getName() + " " + Oculus.getPricePaid() + " ," +
                         LeafProxies.getName() + " " + LeafProxies.getPricePaid() + " ,"
                 , ProxyPurchaseList.toString());

         //all cook group entries
         ProxyPurchaseList.addEntry(OxyLabs);
         ProxyPurchaseList.addEntry(OxyLabsTwo);
         ProxyPurchaseList.addEntry(OxyLabsThree);
         ProxyPurchaseList.addEntry(SmartProxy);
         assertEquals(Oculus.getName() + " " + Oculus.getPricePaid() + " ," +
                         LeafProxies.getName() + " " + LeafProxies.getPricePaid() + " ," +
                         OxyLabs.getName() + " " + OxyLabs.getPricePaid() + " ," +
                         SmartProxy.getName() + " " + SmartProxy.getPricePaid() + " ,"
                 , ProxyPurchaseList.toString());
     }

     @Test
     public void getMethod() {
         //empty list, throw out of index exception
         try {
             ProxyPurchaseList.get(0);
             ProxyPurchaseList.get(1);
             ProxyPurchaseList.get(2);
             fail("can not get index that is out of bound");

         } catch (IndexOutOfBoundsException e) {

         }


         //add some elements to the list and test again

         ProxyPurchaseList.addEntry(Oculus);
         ProxyPurchaseList.addEntry(LeafProxies);
         ProxyPurchaseList.addEntry(SmartProxy);
         assertEquals(Oculus, ProxyPurchaseList.get(0));
         assertEquals(LeafProxies, ProxyPurchaseList.get(1));
         assertEquals(SmartProxy, ProxyPurchaseList.get(2));


         //test for out of bounds
         try {
             ProxyPurchaseList.get(3);
             fail("can not get index that is out of bound");

         } catch (IndexOutOfBoundsException e) {
         }
     }
 }
