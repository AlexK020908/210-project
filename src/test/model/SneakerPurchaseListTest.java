package model;

import model.investment.SneakerPurchaseList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//testing sneaker purchase list
class SneakerPurchaseListTest {
    SneakerPurchaseList sneakerPurchaseList;

    //initializing some sneakers
    private final SneakerEntry s1 = new SneakerEntry("Nike Dunk Low", 130, 5);
    private final SneakerEntry s11 = new SneakerEntry("Nike Dunk Low", 130, 10);
    private final SneakerEntry s11NegativeQuantity = new SneakerEntry("Nike Dunk Low",
            130, -10);
    private final SneakerEntry s2 = new SneakerEntry("AirForce Low Supreme", 100, 6);
    private final SneakerEntry s22 = new SneakerEntry("AirForce Low Supreme", 100, 10);
    private final SneakerEntry s3 = new SneakerEntry("Jordan Retro 12", 275 , 2);
    private final SneakerEntry s4 = new SneakerEntry("YEEZY 350 light v2", 300, 5);
    private final SneakerEntry s44 = new SneakerEntry("YEEZY 350 light v2", 300, 12);
    private final SneakerEntry s5 = new SneakerEntry("YEEZY 750 v2", 200, 3);
    private final SneakerEntry s6 = new SneakerEntry("Nike Dunk Low OffWhite White", 500, 2);
    private final SneakerEntry s7 = new SneakerEntry("Travis Scoot Cactus", 200, 10);



    @BeforeEach
    public void setUp() {
        sneakerPurchaseList = new SneakerPurchaseList();
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
        assertEquals(15,
                s1.getQuantityBought());
        assertEquals(0, sneakerPurchaseList.indexOf(s11));


        //adding a negative quantity sneaker that is  of the same name as s11 and s1
        assertFalse(sneakerPurchaseList.addEntry(s11NegativeQuantity));
        assertEquals(15, s1.getQuantityBought());
        // the quantity did not change because s11NegativeQuantity has a negative quantity


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
                s2.getQuantityBought());


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