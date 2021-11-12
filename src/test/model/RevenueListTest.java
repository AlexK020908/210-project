package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//testing revenue list
public class RevenueListTest {
    RevenueList Revenues;


     //INITIALIZING
    @BeforeEach
    public void setUp() {
        Revenues = new RevenueList();
    }

    @Test
    public void testGetRevenues(){
        try {
            Revenue r1 = new Revenue(20.00);
            Revenue r2 = new Revenue(120.00);
            Revenue r3 = new Revenue(21.00);
            Revenue r4 = new Revenue(10.00);
            Revenue r5 = new Revenue(23.00);
            //add one revenue
            Revenues.addNewRevenue(r1);

            //add many revenues
            Revenues.addNewRevenue(r2);
            Revenues.addNewRevenue(r3);
            Revenues.addNewRevenue(r4);
            Revenues.addNewRevenue(r5);

            List<Revenue> revenues = Revenues.getRevenues();
            assertEquals(5, revenues.size());
            assertEquals(r1, revenues.get(0));
            assertEquals(r2, revenues.get(1));
            assertEquals(r3, revenues.get(2));
            assertEquals(r4, revenues.get(3));
            assertEquals(r5, revenues.get(4));
        } catch (AmountException e) {
            fail();
        }
    }


    @Test
    public void testAddRevenue() {
        try {
            //add one revenue
            Revenue r1 = new Revenue(20.00);
            Revenue r11 = new Revenue(20.00);
            Revenue r2 = new Revenue(120.00);
            Revenue r3 = new Revenue(21.00);
            Revenue r4 = new Revenue(10.00);
            Revenue r5 = new Revenue(23.00);
            Revenues.addNewRevenue(r1);
            assertEquals(1, Revenues.getLength());


            //add all revenues
            Revenues.addNewRevenue(r2);
            Revenues.addNewRevenue(r3);
            Revenues.addNewRevenue(r4);
            Revenues.addNewRevenue(r5);
            assertEquals(5, Revenues.getLength());
        } catch (AmountException e) {
            fail();
        }

    }

    @Test
    public void testAddRevenueInvalidRevenue() {
        try {
            //add one revenue
            Revenue r1 = new Revenue(20.00);
            Revenue r2 = new Revenue(-120.00);
            Revenue r3 = new Revenue(21.00);
            Revenue r4 = new Revenue(10.00);
            Revenue r5 = new Revenue(23.00);
            Revenues.addNewRevenue(r1);
            assertEquals(1, Revenues.getLength());


            //add all revenues
            Revenues.addNewRevenue(r2);
            Revenues.addNewRevenue(r3);
            Revenues.addNewRevenue(r4);
            Revenues.addNewRevenue(r5);
            assertEquals(5, Revenues.getLength());
            fail("an invalid revenue was initialized");
        } catch (AmountException e) {
            //good
        }

    }

    @Test
    public void testTotalRevenue(){
        assertEquals(0, Revenues.calculateTotalRevenue());
        //add one revenue
        try {
            Revenue r1 = new Revenue(20.00);
            Revenue r2 = new Revenue(120.00);
            Revenue r3 = new Revenue(21.00);
            Revenue r4 = new Revenue(10.00);
            Revenue r5 = new Revenue(23.00);

            Revenues.addNewRevenue(r1);
            assertEquals(r1.getRevenue(), Revenues.calculateTotalRevenue());

            //add the same revenue
            Revenues.addNewRevenue(r1);
            assertEquals(r1.getRevenue() * 2, Revenues.calculateTotalRevenue());

            //add many revnues:
            Revenues.addNewRevenue(r2);
            Revenues.addNewRevenue(r3);
            Revenues.addNewRevenue(r4);
            Revenues.addNewRevenue(r5);
            assertEquals(r1.getRevenue() * 2 + r2.getRevenue() + r3.getRevenue() + r4.getRevenue() + r5.getRevenue()
                    , Revenues.calculateTotalRevenue());
        } catch (AmountException e) {
           fail();
        }


    }

    @Test
    public void testRemoveDoesNotExist() {
        try {
            Revenue r1 = new Revenue(20.00);
            Revenue r2 = new Revenue(120.00);


            Revenues.addNewRevenue(r2);
            Revenues.removeRevenue(r1);
            assertEquals(1, Revenues.getLength());
        } catch (AmountException e) {
            System.out.println("invalid revenue was added");
        }
    }

    @Test
    public void testRemoveExists() {
        try {
            Revenue r1 = new Revenue(20.00);
            Revenue r11 = new Revenue(20.00);
            Revenue r2 = new Revenue(120.00);
            Revenue r3 = new Revenue(21.00);
            Revenue r4 = new Revenue(10.00);
            Revenue r5 = new Revenue(23.00);

            Revenues.addNewRevenue(r1);
            Revenues.removeRevenue(r1);
            assertEquals(0, Revenues.getLength());

            Revenues.addNewRevenue(r1);
            Revenues.removeRevenue(r11);
            assertEquals(0, Revenues.getLength());
        } catch (Exception e) {
            System.out.println("invalid revenue was added");
        }
    }


}
