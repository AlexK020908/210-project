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

        //add one entry with repeating name

        //add some more entry


        //add all entries

    }

    @Test
    public void testRemoveEntry(){
        //try to remove from empty list of entries

        //add all entries remove one entry


        //remove some entries

        //remove all entries


    }

    @Test
    public void testTotalPayment(){
        //test empty entry list

        // add one entry and calulate sum


        //add some more entries and return sum


        //add all entries and return sum
    }




}