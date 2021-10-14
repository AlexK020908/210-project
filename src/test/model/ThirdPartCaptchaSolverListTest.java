package model;

import model.investment.ThirdPartyCaptchaSolversPurchaseList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThirdPartCaptchaSolverListTest {
    ThirdPartyCaptchaSolversPurchaseList thirdPartyCaptchaSolversPurchaseList;

    //initialize some Cook Group Subscription Entries to test add and remove methods
    private ThirdPartySolverEntry CapMonster = new ThirdPartySolverEntry("CapMonster", 12);
    private ThirdPartySolverEntry CapMonsterNegative = new ThirdPartySolverEntry("CapMonster", -12);
    private ThirdPartySolverEntry TwoCaptcha = new ThirdPartySolverEntry("TwoCaptcha", 10);
    private ThirdPartySolverEntry AntiCaptcha = new ThirdPartySolverEntry("AntiCaptcha", 15);

    //ONLY three exists right now for third party captcha solvers!


    @BeforeEach
    public void setUp() {
        thirdPartyCaptchaSolversPurchaseList = new ThirdPartyCaptchaSolversPurchaseList();

    }

    @Test
    public void testAddOneEntry() {
        assertTrue(thirdPartyCaptchaSolversPurchaseList.addEntry(CapMonster));
        assertEquals(1, thirdPartyCaptchaSolversPurchaseList.getLength());
        assertEquals(CapMonster.getPricePaid(), thirdPartyCaptchaSolversPurchaseList.getTotalMoneySpent());
        assertEquals(0, thirdPartyCaptchaSolversPurchaseList.indexOf(CapMonster));
    }

    @Test

    public void testAddSecondEntryDifferentType() {
        assertTrue(thirdPartyCaptchaSolversPurchaseList.addEntry(CapMonster));
        assertTrue(thirdPartyCaptchaSolversPurchaseList.addEntry(TwoCaptcha));
        assertEquals(2, thirdPartyCaptchaSolversPurchaseList.getLength());
        assertEquals(12.00 +10.00,
                thirdPartyCaptchaSolversPurchaseList.getTotalMoneySpent());
        assertEquals(0, thirdPartyCaptchaSolversPurchaseList.indexOf(CapMonster));
        assertEquals(1, thirdPartyCaptchaSolversPurchaseList.indexOf(TwoCaptcha));//adding a negative value of the same type of CapMonster
        assertFalse(thirdPartyCaptchaSolversPurchaseList.addEntry(CapMonsterNegative));
        assertEquals(12.00 +10.00, thirdPartyCaptchaSolversPurchaseList.getTotalMoneySpent());
        // the value did not change because it OculusNegativePrice has a negative price

    }

    @Test
    public void testAddSecondEntrySameType() {
        assertTrue(thirdPartyCaptchaSolversPurchaseList.addEntry(CapMonster));
        assertFalse(thirdPartyCaptchaSolversPurchaseList.addEntry(CapMonster));
        assertEquals(1, thirdPartyCaptchaSolversPurchaseList.getLength());
        assertEquals(12.00 + 12.00, thirdPartyCaptchaSolversPurchaseList.getTotalMoneySpent());
        //the reason why we did not times oculus's price by two is becuase addEntry multiplied it for us already
        assertEquals(0, thirdPartyCaptchaSolversPurchaseList.indexOf(CapMonster));

    }

    @Test
    public void testAddManyEntriesDifferentType() {
        assertTrue(thirdPartyCaptchaSolversPurchaseList.addEntry(CapMonster));
        assertTrue(thirdPartyCaptchaSolversPurchaseList.addEntry(TwoCaptcha));
        assertTrue(thirdPartyCaptchaSolversPurchaseList.addEntry(AntiCaptcha));
        assertEquals(3, thirdPartyCaptchaSolversPurchaseList.getLength());
        assertEquals(CapMonster.getPricePaid() + TwoCaptcha.getPricePaid()
                        + AntiCaptcha.getPricePaid(),
                thirdPartyCaptchaSolversPurchaseList.getTotalMoneySpent());
        assertEquals(0, thirdPartyCaptchaSolversPurchaseList.indexOf(CapMonster));
        assertEquals(1, thirdPartyCaptchaSolversPurchaseList.indexOf(TwoCaptcha));
        assertEquals(2, thirdPartyCaptchaSolversPurchaseList.indexOf(AntiCaptcha));

    }

    @Test
    public void testAddManyEntriesSameType() {
        assertTrue(thirdPartyCaptchaSolversPurchaseList.addEntry(CapMonster));
        assertTrue(thirdPartyCaptchaSolversPurchaseList.addEntry(TwoCaptcha));
        assertTrue(thirdPartyCaptchaSolversPurchaseList.addEntry(AntiCaptcha));
        //overlapping
        assertFalse(thirdPartyCaptchaSolversPurchaseList.addEntry(CapMonster));
        assertFalse(thirdPartyCaptchaSolversPurchaseList.addEntry(AntiCaptcha));


        assertEquals(3, thirdPartyCaptchaSolversPurchaseList.getLength());
        assertEquals(CapMonster.getPricePaid() + TwoCaptcha.getPricePaid()
                        + AntiCaptcha.getPricePaid()
                , thirdPartyCaptchaSolversPurchaseList.getTotalMoneySpent());
        assertEquals(0, thirdPartyCaptchaSolversPurchaseList.indexOf(CapMonster));
        assertEquals(1, thirdPartyCaptchaSolversPurchaseList.indexOf(TwoCaptcha));
        assertEquals(2, thirdPartyCaptchaSolversPurchaseList.indexOf(AntiCaptcha));


    }

    //remove method test
    @Test
    public void testRemoveDoesNotExist() {
        assertFalse(thirdPartyCaptchaSolversPurchaseList.removeEntry(CapMonster));
        assertFalse(thirdPartyCaptchaSolversPurchaseList.removeEntry(AntiCaptcha));
    }

    @Test
    public void testRemoveOneEntry() {
        thirdPartyCaptchaSolversPurchaseList.addEntry(CapMonster);
        assertEquals(1, thirdPartyCaptchaSolversPurchaseList.getLength());
        //remove the entry
        assertTrue(thirdPartyCaptchaSolversPurchaseList.removeEntry(CapMonster));
        assertEquals(0, thirdPartyCaptchaSolversPurchaseList.getLength());

        //two entries in the list
        thirdPartyCaptchaSolversPurchaseList.addEntry(CapMonster);
        thirdPartyCaptchaSolversPurchaseList.addEntry(AntiCaptcha);
        //REMOVING one entry
        assertTrue(thirdPartyCaptchaSolversPurchaseList.removeEntry(CapMonster));
        assertEquals(1, thirdPartyCaptchaSolversPurchaseList.getLength());
        assertEquals(0, thirdPartyCaptchaSolversPurchaseList.indexOf(AntiCaptcha));
        //continue to remove another entry
        assertTrue(thirdPartyCaptchaSolversPurchaseList.removeEntry(AntiCaptcha));
        assertEquals(0, thirdPartyCaptchaSolversPurchaseList.getLength());

        //initialize ALL entries in the list
        thirdPartyCaptchaSolversPurchaseList.addEntry(CapMonster);
        thirdPartyCaptchaSolversPurchaseList.addEntry(AntiCaptcha);
        thirdPartyCaptchaSolversPurchaseList.addEntry(TwoCaptcha);


        //remove an entry in the middle
        assertTrue(thirdPartyCaptchaSolversPurchaseList.removeEntry(CapMonster));
        assertEquals(2, thirdPartyCaptchaSolversPurchaseList.getLength());
        assertEquals(0, thirdPartyCaptchaSolversPurchaseList.indexOf(AntiCaptcha));

        //remove all entries
        assertTrue(thirdPartyCaptchaSolversPurchaseList.removeEntry(AntiCaptcha));
        assertTrue(thirdPartyCaptchaSolversPurchaseList.removeEntry(TwoCaptcha));
        assertEquals(0, thirdPartyCaptchaSolversPurchaseList.getLength());
    }

    @Test
    public void testTotalMoneySpend() {

        //cases: no purchases on cook group
        assertEquals(0, thirdPartyCaptchaSolversPurchaseList.getTotalMoneySpent());

        //case 2: one entry
        thirdPartyCaptchaSolversPurchaseList.addEntry(CapMonster);
        assertEquals(CapMonster.getPricePaid(), thirdPartyCaptchaSolversPurchaseList.getTotalMoneySpent());

        //case 3: add another entry
        thirdPartyCaptchaSolversPurchaseList.addEntry(AntiCaptcha);
        assertEquals(CapMonster.getPricePaid() + AntiCaptcha.getPricePaid()
                , thirdPartyCaptchaSolversPurchaseList.getTotalMoneySpent());

        //case 4: add existing purchase
        thirdPartyCaptchaSolversPurchaseList.addEntry(CapMonster);
        assertEquals(CapMonster.getPricePaid() + AntiCaptcha.getPricePaid()
                , thirdPartyCaptchaSolversPurchaseList.getTotalMoneySpent());

        //case 5: add the rest of entries that do not already exist in the list
        thirdPartyCaptchaSolversPurchaseList.addEntry(TwoCaptcha);
        assertEquals(CapMonster.getPricePaid() + AntiCaptcha.getPricePaid()
                        + TwoCaptcha.getPricePaid()
                , thirdPartyCaptchaSolversPurchaseList.getTotalMoneySpent());

    }

    @Test
    public void testLengthZero(){
        assertEquals(0, thirdPartyCaptchaSolversPurchaseList.getLength());
    }

    @Test
    public void testLengthManyElements(){
        thirdPartyCaptchaSolversPurchaseList.addEntry(CapMonster);
        assertEquals(1, thirdPartyCaptchaSolversPurchaseList.getLength());
        thirdPartyCaptchaSolversPurchaseList.addEntry(CapMonster);
        assertEquals(1, thirdPartyCaptchaSolversPurchaseList.getLength());
        thirdPartyCaptchaSolversPurchaseList.addEntry(AntiCaptcha);
        thirdPartyCaptchaSolversPurchaseList.addEntry(TwoCaptcha);
        assertEquals(3, thirdPartyCaptchaSolversPurchaseList.getLength());

    }

    @Test
    public void testIndexOfElementDoesNotExist(){
        assertEquals(-1, thirdPartyCaptchaSolversPurchaseList.indexOf(CapMonster));
    }

    @Test
    public void testIndexOfElementInAListExist(){
        thirdPartyCaptchaSolversPurchaseList.addEntry(CapMonster);
        thirdPartyCaptchaSolversPurchaseList.addEntry(AntiCaptcha);
        thirdPartyCaptchaSolversPurchaseList.addEntry(TwoCaptcha);
        assertEquals(1, thirdPartyCaptchaSolversPurchaseList.indexOf(AntiCaptcha));
        assertEquals(0, thirdPartyCaptchaSolversPurchaseList.indexOf(CapMonster));
    }

    @Test
    public void thirdPartSolverToStringTest(){
        //use capMonster as main testing proxy entry
        assertEquals(CapMonster.getName() + " " + CapMonster.getPricePaid() + " ," , CapMonster.toString());
    }

    @Test
    public void getTypeTest(){
        assertEquals(EntryType.ThirdPartSolver, thirdPartyCaptchaSolversPurchaseList.getType());
        assertFalse(EntryType.CookGroup.equals(thirdPartyCaptchaSolversPurchaseList.getType()));
        assertFalse(EntryType.Sneaker.equals(thirdPartyCaptchaSolversPurchaseList.getType()));
        assertFalse(EntryType.PROXY.equals(thirdPartyCaptchaSolversPurchaseList.getType()));
    }

    @Test
    public void testToString() {
        //empty list
        assertEquals("", thirdPartyCaptchaSolversPurchaseList.toString());
        //some Cook group entries
        thirdPartyCaptchaSolversPurchaseList.addEntry(CapMonster);
        assertEquals(CapMonster.getName() + " " + CapMonster.getPricePaid() + " ,"
                , thirdPartyCaptchaSolversPurchaseList.toString());

        //all cook group entries
        thirdPartyCaptchaSolversPurchaseList.addEntry(TwoCaptcha);
        thirdPartyCaptchaSolversPurchaseList.addEntry(AntiCaptcha);
        assertEquals(CapMonster.getName() + " " + CapMonster.getPricePaid() + " ," +
                        TwoCaptcha.getName() + " " + TwoCaptcha.getPricePaid() + " ," +
                        AntiCaptcha.getName() + " " + AntiCaptcha.getPricePaid() + " ,"
                , thirdPartyCaptchaSolversPurchaseList.toString());
    }
}
