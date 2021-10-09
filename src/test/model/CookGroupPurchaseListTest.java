package model;

import model.investment.CookGroupPurchaseList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//tests the cookGroupPurchaseList class
public class CookGroupPurchaseListTest {

   CookGroupPurchaseList cookGroupPurchaseList;

    //initialize some Cook Group Subscription Entries to test add and remove methods
    private CookGroupSubscriptionEntry forbidden = new CookGroupSubscriptionEntry("Forbidden", 46.99);
    private CookGroupSubscriptionEntry theNorthCop = new CookGroupSubscriptionEntry("The North Cop", 36.99);
    private CookGroupSubscriptionEntry DropAlert = new CookGroupSubscriptionEntry("Drop alert", 30.00);
    private CookGroupSubscriptionEntry secretSauce = new CookGroupSubscriptionEntry("Secret Sauce", 65.00);

    @BeforeEach
    public void setUp(){
        cookGroupPurchaseList = new CookGroupPurchaseList();

    }

    @Test
    public void testAddOneEntry() {
        assertTrue(cookGroupPurchaseList.addEntry(forbidden));
        assertEquals(1,cookGroupPurchaseList.getLength());
        assertEquals(46.99, cookGroupPurchaseList.getTotalMoneySpent());
        assertEquals(0, cookGroupPurchaseList.indexOf(forbidden));
    }

    @Test
    public void testAddSecondEntryDifferentType() {
        assertTrue(cookGroupPurchaseList.addEntry(forbidden));
        assertTrue(cookGroupPurchaseList.addEntry(theNorthCop));
        assertEquals(2,cookGroupPurchaseList.getLength());
        assertEquals(46.99+36.99, cookGroupPurchaseList.getTotalMoneySpent());
        assertEquals(0, cookGroupPurchaseList.indexOf(forbidden));
        assertEquals(1, cookGroupPurchaseList.indexOf(theNorthCop));

    }

    @Test
    public void testAddSecondEntrySameType() {
        assertTrue(cookGroupPurchaseList.addEntry(forbidden));
        assertFalse(cookGroupPurchaseList.addEntry(forbidden));
        assertEquals(1,cookGroupPurchaseList.getLength());
        assertEquals(46.99*2, cookGroupPurchaseList.getTotalMoneySpent());
        assertEquals(0, cookGroupPurchaseList.indexOf(forbidden));
    }

    @Test
    public void testAddManyEntriesDifferentType() {
        assertTrue(cookGroupPurchaseList.addEntry(forbidden));
        assertTrue(cookGroupPurchaseList.addEntry(theNorthCop));
        assertTrue(cookGroupPurchaseList.addEntry(DropAlert));
        assertTrue(cookGroupPurchaseList.addEntry(secretSauce));
        assertEquals(4,cookGroupPurchaseList.getLength());
        assertEquals(46.99+36.99+30.00+65.00, cookGroupPurchaseList.getTotalMoneySpent());
        assertEquals(0, cookGroupPurchaseList.indexOf(forbidden));
        assertEquals(1, cookGroupPurchaseList.indexOf(theNorthCop));
        assertEquals(2, cookGroupPurchaseList.indexOf(DropAlert));
        assertEquals(3, cookGroupPurchaseList.indexOf(secretSauce));
    }

    @Test
    public void testAddManyEntriesSameType() {
        assertTrue(cookGroupPurchaseList.addEntry(forbidden));
        assertTrue(cookGroupPurchaseList.addEntry(theNorthCop));
        assertTrue(cookGroupPurchaseList.addEntry(DropAlert));
        //ADDING multiple overlapping entries
        assertFalse(cookGroupPurchaseList.addEntry(forbidden));
        assertFalse(cookGroupPurchaseList.addEntry(DropAlert));

        assertEquals(3,cookGroupPurchaseList.getLength());
        assertEquals(46.99*2 + 36.99 + 30.00*2, cookGroupPurchaseList.getTotalMoneySpent());
        assertEquals(0, cookGroupPurchaseList.indexOf(forbidden));
        assertEquals(1, cookGroupPurchaseList.indexOf(theNorthCop));
        assertEquals(2, cookGroupPurchaseList.indexOf(DropAlert));
    }

    //remove method test
    @Test
    public void testRemoveDoesNotExist(){
        assertFalse(cookGroupPurchaseList.removeEntry(forbidden));
        assertFalse(cookGroupPurchaseList.removeEntry(DropAlert));
    }

    @Test
    public void testRemoveOneEntry(){
        cookGroupPurchaseList.addEntry(forbidden);
        assertEquals(1,cookGroupPurchaseList.getLength());
        //remove the entry
        assertTrue(cookGroupPurchaseList.removeEntry(forbidden));
        assertEquals(0,cookGroupPurchaseList.getLength());

        //two entries in the list
        cookGroupPurchaseList.addEntry(forbidden);
        cookGroupPurchaseList.addEntry(theNorthCop);
        //REMOVING one entry
        assertTrue(cookGroupPurchaseList.removeEntry(theNorthCop));
        assertEquals(1,cookGroupPurchaseList.getLength());
        assertEquals(0, cookGroupPurchaseList.indexOf(forbidden));
        //continue to remove another entry
        assertTrue(cookGroupPurchaseList.removeEntry(forbidden));
        assertEquals(0,cookGroupPurchaseList.getLength());

        //initialize four entries in the list
        cookGroupPurchaseList.addEntry(forbidden);
        cookGroupPurchaseList.addEntry(theNorthCop);
        cookGroupPurchaseList.addEntry(DropAlert);
        cookGroupPurchaseList.addEntry(secretSauce);
        //remove an entry in the middle
        assertTrue(cookGroupPurchaseList.removeEntry(DropAlert));
        assertEquals(3, cookGroupPurchaseList.getLength());
        assertEquals(2, cookGroupPurchaseList.indexOf(secretSauce));

        //remove all entries
        assertTrue(cookGroupPurchaseList.removeEntry(forbidden));
        assertTrue(cookGroupPurchaseList.removeEntry(theNorthCop));
        assertTrue(cookGroupPurchaseList.removeEntry(secretSauce));
        assertEquals(0, cookGroupPurchaseList.getLength());
    }

    @Test
    public void testTotalMoneySpend(){

        //cases: no purchases on cook group
        assertEquals(0, cookGroupPurchaseList.getTotalMoneySpent());

        //case 2: one entry
        cookGroupPurchaseList.addEntry(forbidden);
        assertEquals(46.99, cookGroupPurchaseList.getTotalMoneySpent());

        //case 3: add another entry
        cookGroupPurchaseList.addEntry(DropAlert);
        assertEquals(30.00 + 46.99, cookGroupPurchaseList.getTotalMoneySpent());

        //case 4: add existing purchase
        cookGroupPurchaseList.addEntry(forbidden);
        assertEquals(30.00 + 46.99*2, cookGroupPurchaseList.getTotalMoneySpent());

        //case 5: add two other entries that do not already exist in the list
        cookGroupPurchaseList.addEntry(theNorthCop);
        cookGroupPurchaseList.addEntry(secretSauce);
        assertEquals(30.00 + 46.99*2 + 36.99 + 65.00,
                cookGroupPurchaseList.getTotalMoneySpent());


    }



}
