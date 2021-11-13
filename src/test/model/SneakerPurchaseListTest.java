package model;


import model.investment.SneakerPurchaseList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//testing sneaker purchase list
class SneakerPurchaseListTest {
    SneakerPurchaseList sneakerPurchaseList;

    //initializing some sneakers
    private SneakerEntry s1;
    private SneakerEntry s11;

    private SneakerEntry s2;
    private SneakerEntry s22;
    private SneakerEntry s3;
    private SneakerEntry s4;
    private SneakerEntry s44;
    private SneakerEntry s5;
    private SneakerEntry s6;
    private SneakerEntry s7;



    @BeforeEach
    public void setUp() {
        try {
            sneakerPurchaseList = new SneakerPurchaseList();
            s1 = new SneakerEntry("Nike Dunk Low", 130, 5);
            s11 = new SneakerEntry("Nike Dunk Low", 130, 10);
            s2 = new SneakerEntry("AirForce Low Supreme", 100, 6);
            s22 = new SneakerEntry("AirForce Low Supreme", 100, 10);
            s3 = new SneakerEntry("Jordan Retro 12", 275, 2);
            s4 = new SneakerEntry("YEEZY 350 light v2", 300, 5);
            s44 = new SneakerEntry("YEEZY 350 light v2", 300, 12);
            s5 = new SneakerEntry("YEEZY 750 v2", 200, 3);
            s6 = new SneakerEntry("Nike Dunk Low OffWhite White", 500, 2);
            s7 = new SneakerEntry("Travis Scoot Cactus", 200, 10);
        } catch (NameException e) {
           fail();
        } catch (AmountException e) {
          fail();
        } catch (QuantityException e) {
          fail();
        }
    }


    @Test
    public void testGetSneakers() {
       List<SneakerEntry> emptyList = sneakerPurchaseList.getEntries();
       assertEquals(0, emptyList.size());

       assertTrue(sneakerPurchaseList.addEntry(s1));
       sneakerPurchaseList.addEntry(s2);
       sneakerPurchaseList.addEntry(s3);
       sneakerPurchaseList.addEntry(s4);
       sneakerPurchaseList.addEntry(s5);
       List<SneakerEntry> sneakerEntries = sneakerPurchaseList.getEntries();
       assertEquals(s1, sneakerEntries.get(0));
       assertEquals(s2, sneakerEntries.get(1));
       assertEquals(s3, sneakerEntries.get(2));
       assertEquals(s4, sneakerEntries.get(3));
       assertEquals(s5, sneakerEntries.get(4));

    }



    @Test
    public void testAddEntry(){
        // add one entry
        assertTrue(sneakerPurchaseList.addEntry(s1));
        assertEquals(1, sneakerPurchaseList.getLength());
        assertEquals(0, sneakerPurchaseList.indexOf(s1));

        //add one entry with repeating name, s2 has the same name
        sneakerPurchaseList.addEntry(s11);
        assertEquals(1, sneakerPurchaseList.getLength());
        assertEquals(15, s1.getQuantityBought());
        assertEquals(0, sneakerPurchaseList.indexOf(s11));


            //add some more entry of different names
        sneakerPurchaseList.addEntry(s2);
        sneakerPurchaseList.addEntry(s3);
        sneakerPurchaseList.addEntry(s4);

        assertEquals(4, sneakerPurchaseList.getLength());
        assertEquals(0, sneakerPurchaseList.indexOf(s1));
        assertEquals(2, sneakerPurchaseList.indexOf(s3));
        assertEquals(3, sneakerPurchaseList.indexOf(s4));
        assertEquals(0, sneakerPurchaseList.indexOf(s11));

            //add the remaining entries
        sneakerPurchaseList.addEntry(s5);
        sneakerPurchaseList.addEntry(s6);
        sneakerPurchaseList.addEntry(s7);
        sneakerPurchaseList.addEntry(s22);
        sneakerPurchaseList.addEntry(s44);
        assertEquals(7, sneakerPurchaseList.getLength());
        assertEquals(0, sneakerPurchaseList.indexOf(s1));
        assertEquals(4, sneakerPurchaseList.indexOf(s5));
        assertEquals(3, sneakerPurchaseList.indexOf(s44));
        assertEquals(16, //16 is the sum of s2 and s22's quantity
                s2.getQuantityBought());;

    }

    @Test
    public void testAddEntryInvalidSneakerName() {

        try {
            assertTrue(sneakerPurchaseList.addEntry(s1));
            assertEquals(1, sneakerPurchaseList.getLength());
            assertEquals(0, sneakerPurchaseList.indexOf(s1));
            SneakerEntry invalidS1 = new SneakerEntry("", 120, 2);
            sneakerPurchaseList.addEntry(invalidS1);
            fail();
        } catch (NameException e) {
           //good
        } catch (AmountException e) {
            fail();
        } catch (QuantityException e) {
            fail();
        }
        assertEquals(1, sneakerPurchaseList.getLength());

    }


    @Test
    public void testAddEntryInvalidSneakerPrice() {

        try {
            assertTrue(sneakerPurchaseList.addEntry(s1));
            assertEquals(1, sneakerPurchaseList.getLength());
            assertEquals(0, sneakerPurchaseList.indexOf(s1));
            SneakerEntry invalidS1 = new SneakerEntry("sneaker", -20, 2);
            sneakerPurchaseList.addEntry(invalidS1);
            fail();
        } catch (NameException e) {
            fail();
        } catch (AmountException e) {
            System.out.println("new sneaker ");
        } catch (QuantityException e) {
            fail();
        }
        assertEquals(1, sneakerPurchaseList.getLength());

    }



    @Test
    public void testAddEntryInvalidSneakerQuantity() {
        assertTrue(sneakerPurchaseList.addEntry(s1));
        assertEquals(1, sneakerPurchaseList.getLength());
        assertEquals(0, sneakerPurchaseList.indexOf(s1));
        try {
            SneakerEntry invalidS1 = new SneakerEntry("sneaker", 120, -2);
            sneakerPurchaseList.addEntry(invalidS1);
            fail();
        } catch (NameException e) {
            fail();
        } catch (AmountException e) {
            fail();
        } catch (QuantityException e) {
           //good
        }
        assertEquals(1, sneakerPurchaseList.getLength());

    }

    @Test
    public void testTotalPayment(){
        //test empty entry list
        assertEquals(0,sneakerPurchaseList.getTotalMoneySpent());
        // add one entry and calculate sum
        sneakerPurchaseList.addEntry(s1);
        assertEquals(650, sneakerPurchaseList.getTotalMoneySpent());
        //add one repeating name sneaker and check the total money spent
        sneakerPurchaseList.addEntry(s11);
        assertEquals(130 * 15, sneakerPurchaseList.getTotalMoneySpent());
        //add some more entries and return sum
        sneakerPurchaseList.addEntry(s2);
        sneakerPurchaseList.addEntry(s22);
        sneakerPurchaseList.addEntry(s3);
        assertEquals(130* 15 + 100*16 + 275 * 2, sneakerPurchaseList.getTotalMoneySpent() );

        //add all entries and return sum
        sneakerPurchaseList.addEntry(s4);
        sneakerPurchaseList.addEntry(s5);
        sneakerPurchaseList.addEntry(s6);
        sneakerPurchaseList.addEntry(s7);
        assertEquals(130* 15 + 100*16 + 275 * 2 + 300*5 + 200*3 + 500*2 + 200 * 10,
                sneakerPurchaseList.getTotalMoneySpent() );
    }

    @Test
    public void testGetLength(){
        //test empty list
        assertEquals(0, sneakerPurchaseList.getLength());

        //test one sneaker entry
        sneakerPurchaseList.addEntry(s1);
        sneakerPurchaseList.addEntry(s11); //same sneaker entry with same name
        assertEquals(1, sneakerPurchaseList.getLength());
        //test list with many sneaker entry
        sneakerPurchaseList.addEntry(s2);
        sneakerPurchaseList.addEntry(s3);
        sneakerPurchaseList.addEntry(s4);
        sneakerPurchaseList.addEntry(s5);
        sneakerPurchaseList.addEntry(s6);
        sneakerPurchaseList.addEntry(s7);
        assertEquals(7, sneakerPurchaseList.getLength());
    }

    @Test
    public void testGetQuantityBought(){
        assertEquals(5, s1.getQuantityBought());
        assertEquals(6, s2.getQuantityBought());
    }

    @Test
    public void testIncreaseQuantityBought(){
        //here we are using s1 as the main sneakerEntry
        //base case
        s1.increaseQuantityBought(0);
        assertEquals(5, s1.getQuantityBought());
        //increase by a small amount
        s1.increaseQuantityBought(2);
        assertEquals(5 + 2, s1.getQuantityBought());
        //increase by a large amount
        s1.increaseQuantityBought(10);
        assertEquals(5 + 2 + 10, s1.getQuantityBought());

    }



    @Test
    public void testIncreaseQuantityBoughtInvalidSneakerName(){
        //here we are using s1 as the main sneakerEntry
        //base case
        try {
            SneakerEntry invalidS1 = new SneakerEntry("", 120, 2);
            invalidS1.increaseQuantityBought(0);
            assertEquals(2, s1.getQuantityBought());
            //increase by a small amount
            invalidS1.increaseQuantityBought(2);
            assertEquals(2 + 2, s1.getQuantityBought());
            //increase by a large amount
            invalidS1.increaseQuantityBought(10);
            assertEquals(2 + 2 + 10, s1.getQuantityBought());
            fail();
        } catch (NameException e) {
            //good
        } catch (AmountException e) {
           fail();
        } catch (QuantityException e) {
           fail();
        }

    }

    @Test
    public void testIncreaseQuantityBoughtInvalidQuantity(){
        //here we are using s1 as the main sneakerEntry
        //base case
        try {
            SneakerEntry invalidS1 = new SneakerEntry("sneaker", 12, -2);
            invalidS1.increaseQuantityBought(0);
            assertEquals(-2, s1.getQuantityBought());
            //increase by a small amount
            invalidS1.increaseQuantityBought(2);
            assertEquals(-2 + 2, s1.getQuantityBought());
            //increase by a large amount
            invalidS1.increaseQuantityBought(10);
            assertEquals(0 + 10, s1.getQuantityBought());
            fail();
        } catch (NameException e) {
            fail();
        } catch (AmountException e) {
            fail();
        } catch (QuantityException e) {
            //good
        }

    }

    @Test
    public void testIncreaseQuantityBoughtInvalidPrice(){
        //here we are using s1 as the main sneakerEntry
        //base case
        try {
            SneakerEntry invalidS1 = new SneakerEntry("sneaker", -120, 2);
            invalidS1.increaseQuantityBought(0);
            assertEquals(5, s1.getQuantityBought());
            //increase by a small amount
            invalidS1.increaseQuantityBought(2);
            assertEquals(5 + 2, s1.getQuantityBought());
            //increase by a large amount
            invalidS1.increaseQuantityBought(10);
            assertEquals(5 + 2 + 10, s1.getQuantityBought());
            fail();
        } catch (NameException e) {
            fail();
        } catch (AmountException e) {
           //good
        } catch (QuantityException e) {
            fail();
        }

    }

    @Test
    public void sneakerEntryToStringTest(){
        //using s1 as main testing sneaker entry again
        assertEquals(" " + s1.getName() + " " + s1.getRetailPrice() + " " + s1.getQuantityBought() + ", "
                , s1.toString());
    }

    @Test
    public void sneakerEntryListToStringTest(){
        //empty list
        assertEquals("", sneakerPurchaseList.toString());
        //List with one sneaker entries
        sneakerPurchaseList.addEntry(s1);
        assertEquals(" " + s1.getName() + " " + s1.getRetailPrice() + " " + s1.getQuantityBought() + ", "
        , sneakerPurchaseList.toString());
        //List with some sneaker entries
        sneakerPurchaseList.addEntry(s2);
        sneakerPurchaseList.addEntry(s3);
        assertEquals(" " + s1.getName() + " " + s1.getRetailPrice() + " " + s1.getQuantityBought() + ", "
                + " " + s2.getName() + " " + s2.getRetailPrice() + " " + s2.getQuantityBought() + ", " +
                        " " + s3.getName() + " " + s3.getRetailPrice() + " " + s3.getQuantityBought() + ", "
                , sneakerPurchaseList.toString());
        //List with many sneaker entries
        sneakerPurchaseList.addEntry(s4);
        sneakerPurchaseList.addEntry(s5);
        sneakerPurchaseList.addEntry(s6);

    }

    @Test
    public void testIndexOf(){
        //base case, index of a sneaker entry that does not exist with an empty list
        assertEquals(-1, sneakerPurchaseList.indexOf(s1));
        //index of a sneaker entry that does not exist
        sneakerPurchaseList.addEntry(s1);
        assertEquals(-1, sneakerPurchaseList.indexOf(s22));
        //add many sneaker entries and check random sneaker entry's position
        sneakerPurchaseList.addEntry(s2);
        sneakerPurchaseList.addEntry(s3);
        sneakerPurchaseList.addEntry(s4);
        sneakerPurchaseList.addEntry(s5);
        sneakerPurchaseList.addEntry(s6);
        sneakerPurchaseList.addEntry(s7);
        assertEquals(0, sneakerPurchaseList.indexOf(s1));
        assertEquals(3, sneakerPurchaseList.indexOf(s4));
        assertEquals(6, sneakerPurchaseList.indexOf(s7));

    }

    @Test
    public void testGetMethodNoSuchEntryExist() {
        //out of bounds
        try {
            sneakerPurchaseList.get(0);
            sneakerPurchaseList.get(1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            //does not exist
        }
        sneakerPurchaseList.addEntry(s1);
        assertEquals(s1, sneakerPurchaseList.get(0));

        try {
            sneakerPurchaseList.get(2);
            fail();
        } catch (IndexOutOfBoundsException e) {
            //does mnot exist
        }


    }

    @Test
    public void testRemoveEntryDoesNotExist() {
        assertFalse(sneakerPurchaseList.removeEntry(s1));

        //add an entry
        sneakerPurchaseList.addEntry(s2);
        assertFalse(sneakerPurchaseList.removeEntry(s1));
        assertEquals(1, sneakerPurchaseList.getLength());
        assertEquals(0, sneakerPurchaseList.indexOf(s2));
    }

    @Test
    public void testRemoveEntryExists() {
        //one entry exist
        sneakerPurchaseList.addEntry(s1);
        assertTrue(sneakerPurchaseList.removeEntry(s1));
        assertEquals(0, sneakerPurchaseList.getLength());

        //many entries exist
        sneakerPurchaseList.addEntry(s1);
        sneakerPurchaseList.addEntry(s2);
        sneakerPurchaseList.addEntry(s3);
        sneakerPurchaseList.addEntry(s4);
        sneakerPurchaseList.addEntry(s5);

        assertTrue(sneakerPurchaseList.removeEntry(s1));
        assertTrue(sneakerPurchaseList.removeEntry(s2));
        assertEquals(3, sneakerPurchaseList.getLength());
        assertEquals(-1, sneakerPurchaseList.indexOf(s1));

    }



}