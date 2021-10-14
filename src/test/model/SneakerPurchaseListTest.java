package model;

import model.investment.SneakerPurchaseList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SneakerPurchaseListTest {
    SneakerPurchaseList sneakerPurchaseList;

    //initializing some sneakers
    private SneakerEntry s1 = new SneakerEntry("Nike Dunk Low", 130, 5);
    private SneakerEntry s11 = new SneakerEntry("Nike Dunk Low", 130, 10);
    private SneakerEntry s2 = new SneakerEntry("AirForce Low Supreme", 100, 6);
    private SneakerEntry s22 = new SneakerEntry("AirForce Low Supreme", 100, 10);
    private SneakerEntry s3 = new SneakerEntry("Jordan Retro 12", 275 , 2);
    private SneakerEntry s4 = new SneakerEntry("YEEZY 350 light v2", 300, 5);
    private SneakerEntry s44 = new SneakerEntry("YEEZY 350 light v2", 300, 12);
    private SneakerEntry s5 = new SneakerEntry("YEEZY 750 v2", 200, 3);
    private SneakerEntry s6 = new SneakerEntry("Nike Dunk Low OffWhite White", 500, 2);
    private SneakerEntry s7 = new SneakerEntry("Travis Scoot Cactus", 200, 10);



    @BeforeEach
    public void setUp() {
        sneakerPurchaseList = new SneakerPurchaseList();
    }

    @Test
    public void testAddEntry(){
        // add one entry
        sneakerPurchaseList.addEntry(s1);
        assertEquals(1, sneakerPurchaseList.getLength());
        assertEquals(0, sneakerPurchaseList.indexOf(s1));

        //add one entry with repeating name, s2 has the same name
        sneakerPurchaseList.addEntry(s11);
        assertEquals(1, sneakerPurchaseList.getLength());
        assertEquals(15,
                s1.getQuantityBought());
        assertEquals(-1, sneakerPurchaseList.indexOf(s11));
        //the reason why we passed s1 in sneaker entry is because since they have the same name, only the
        //quantity is updated, so the name stays the same.

        //add some more entry of different names
        sneakerPurchaseList.addEntry(s2);
        sneakerPurchaseList.addEntry(s3);
        sneakerPurchaseList.addEntry(s4);

        assertEquals(4, sneakerPurchaseList.getLength());
        assertEquals(0, sneakerPurchaseList.indexOf(s1));
        assertEquals(2, sneakerPurchaseList.indexOf(s3));
        assertEquals(3, sneakerPurchaseList.indexOf(s4));
        assertEquals(-1, sneakerPurchaseList.indexOf(s11));

        //add the remaining entries
        sneakerPurchaseList.addEntry(s5);
        sneakerPurchaseList.addEntry(s6);
        sneakerPurchaseList.addEntry(s7);
        sneakerPurchaseList.addEntry(s22);
        sneakerPurchaseList.addEntry(s44);
        assertEquals(7, sneakerPurchaseList.getLength());
        assertEquals(0, sneakerPurchaseList.indexOf(s1));
        assertEquals(4, sneakerPurchaseList.indexOf(s5));
        assertEquals(-1, sneakerPurchaseList.indexOf(s44));
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





}