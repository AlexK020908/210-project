package model;

import model.investment.CookGroupPurchaseList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.font.FontRenderContext;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//tests the cookGroupPurchaseList class
public class CookGroupPurchaseListTest {

   CookGroupPurchaseList cookGroupPurchaseList;

    //initialize some Cook Group Subscription Entries to test add and remove methods
    private final CookGroupSubscriptionEntry forbidden = new CookGroupSubscriptionEntry("Forbidden", 46.99);
    private final CookGroupSubscriptionEntry forbiddenNegativeInvalidValue = new CookGroupSubscriptionEntry("Forbidden", -5);
    private final CookGroupSubscriptionEntry theNorthCop = new CookGroupSubscriptionEntry("The North Cop", 36.99);
    private final CookGroupSubscriptionEntry DropAlert = new CookGroupSubscriptionEntry("Drop alert", 30.00);
    private final CookGroupSubscriptionEntry secretSauce = new CookGroupSubscriptionEntry("Secret Sauce", 65.00);

    @BeforeEach
    public void setUp(){
        cookGroupPurchaseList = new CookGroupPurchaseList();

    }

    @Test
    public void testGetEntries() {
        List<CookGroupSubscriptionEntry> empty = cookGroupPurchaseList.getEntries();
        assertEquals(0, empty.size());
        cookGroupPurchaseList.addEntry(forbidden);
        cookGroupPurchaseList.addEntry((theNorthCop));
        cookGroupPurchaseList.addEntry(DropAlert);
        cookGroupPurchaseList.addEntry(secretSauce);
        List<CookGroupSubscriptionEntry> entries =  cookGroupPurchaseList.getEntries();
        assertEquals(4, entries.size());
        assertEquals(forbidden, entries.get(0));
        assertEquals(theNorthCop, entries.get(1));
        assertEquals(DropAlert, entries.get(2));
        assertEquals(secretSauce, entries.get(3));
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
        //adding a negative value of the same type of forbidden
        assertFalse(cookGroupPurchaseList.addEntry(forbiddenNegativeInvalidValue));
        assertEquals(46.99+36.99, cookGroupPurchaseList.getTotalMoneySpent());
        // the value did not change because it forbiddenNegativeInvalidValue has a negative amount

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

    @Test
    public void testLengthZero(){
        assertEquals(0, cookGroupPurchaseList.getLength());
    }

    @Test
    public void testLengthManyElements(){
        cookGroupPurchaseList.addEntry(forbidden);
        assertEquals(1, cookGroupPurchaseList.getLength());
        cookGroupPurchaseList.addEntry(forbidden);
        assertEquals(1, cookGroupPurchaseList.getLength());
        cookGroupPurchaseList.addEntry(theNorthCop);
        cookGroupPurchaseList.addEntry(DropAlert);
        cookGroupPurchaseList.addEntry(secretSauce);
        assertEquals(4, cookGroupPurchaseList.getLength());

    }

    @Test
    public void testIndexOfElementDoesNotExist(){
        assertEquals(-1, cookGroupPurchaseList.indexOf(forbidden));
    }

    @Test
    public void testIndexOfElementInAListExist(){
        cookGroupPurchaseList.addEntry(theNorthCop);
        cookGroupPurchaseList.addEntry(forbidden);
        cookGroupPurchaseList.addEntry(DropAlert);
        cookGroupPurchaseList.addEntry(secretSauce);
        assertEquals(1, cookGroupPurchaseList.indexOf(forbidden));
        assertEquals(3, cookGroupPurchaseList.indexOf(secretSauce));
    }

    @Test
    public void cookGroupEntryToStringTest(){
        //use forbidden as main testing cook group entry
        assertEquals(forbidden.getName() + " " + forbidden.getPricePaid() + " ," , forbidden.toString());
    }

    @Test
    public void getTypeTest(){
        assertEquals(EntryType.CookGroup, cookGroupPurchaseList.getType());
        assertFalse(EntryType.PROXY.equals(cookGroupPurchaseList.getType()));
        assertFalse(EntryType.Sneaker.equals(cookGroupPurchaseList.getType()));
        assertFalse(EntryType.ThirdPartSolver.equals(cookGroupPurchaseList.getType()));
    }

    @Test
    public void testToString(){
        //empty list
        assertEquals("", cookGroupPurchaseList.toString());
        //some Cook group entries
        cookGroupPurchaseList.addEntry(forbidden);
        cookGroupPurchaseList.addEntry(theNorthCop);
        assertEquals( forbidden.getName() + " " + forbidden.getPricePaid() + " ," +
                        theNorthCop.getName() + " " + theNorthCop.getPricePaid() + " ,"
                , cookGroupPurchaseList.toString());

        //all cook group entries
        cookGroupPurchaseList.addEntry(DropAlert);
        cookGroupPurchaseList.addEntry(secretSauce);
        assertEquals( forbidden.getName() + " " + forbidden.getPricePaid() + " ," +
                        theNorthCop.getName() + " " + theNorthCop.getPricePaid() + " ," +
                        DropAlert.getName() + " " + DropAlert.getPricePaid() + " ," +
                        secretSauce.getName() + " " + secretSauce.getPricePaid() + " ,"
                , cookGroupPurchaseList.toString());
    }

    @Test
    public void getMethod() {
        //empty list, throw out of index exception
        try {
            cookGroupPurchaseList.get(0);
            cookGroupPurchaseList.get(1);
            cookGroupPurchaseList.get(2);
            fail("can not get index that is out of bound");

        } catch (IndexOutOfBoundsException e) {

        }


        //add some elements to the list and test again

        cookGroupPurchaseList.addEntry(forbidden);
        cookGroupPurchaseList.addEntry(DropAlert);
        cookGroupPurchaseList.addEntry(theNorthCop);
        assertEquals(forbidden, cookGroupPurchaseList.get(0));
        assertEquals(DropAlert, cookGroupPurchaseList.get(1));
        assertEquals(theNorthCop, cookGroupPurchaseList.get(2));




        //test for out of bounds
        try {
            cookGroupPurchaseList.get(3);
            fail("can not get index that is out of bound");

        } catch (IndexOutOfBoundsException e) {

        }



    }



}
