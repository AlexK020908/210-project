package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

//testing revenue list
public class RevenueListTest {
    RevenueList Revenues;
    private Revenue r1 = new Revenue(20.00);
    private Revenue r11 = new Revenue(20.00);
    private Revenue r2 = new Revenue(120.00);
    private Revenue r3 = new Revenue(21.00);
    private Revenue r4 = new Revenue(10.00);
    private Revenue r5 = new Revenue(23.00);

     //INITIALIZING
    @BeforeEach
    public void setUp() {
        Revenues = new RevenueList();
    }

    @Test
    public void testGetRevenues(){


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




    }


    @Test
    public void testAddRevenue() {
        //add one revenue
        Revenues.addNewRevenue(r1);
        assertEquals(1, Revenues.getLength());


        //add all revenues
        Revenues.addNewRevenue(r2);
        Revenues.addNewRevenue(r3);
        Revenues.addNewRevenue(r4);
        Revenues.addNewRevenue(r5);
        assertEquals(5, Revenues.getLength());

    }

    @Test
    public void testTotalRevenue(){
        assertEquals(0, Revenues.calculateTotalRevenue());
        //add one revenue
        Revenues.addNewRevenue(r1);
        assertEquals(r1.getRevenue(), Revenues.calculateTotalRevenue());

        //add the same revenue
        Revenues.addNewRevenue(r1);
        assertEquals(r1.getRevenue()*2, Revenues.calculateTotalRevenue());

        //add many revnues:
        Revenues.addNewRevenue(r2);
        Revenues.addNewRevenue(r3);
        Revenues.addNewRevenue(r4);
        Revenues.addNewRevenue(r5);
        assertEquals(r1.getRevenue()*2 + r2.getRevenue() + r3.getRevenue() + r4.getRevenue() + r5.getRevenue()
                , Revenues.calculateTotalRevenue());


    }

    @Test
    public void testRemoveDoesNotExist() {
        Revenues.addNewRevenue(r2);
        Revenues.removeRevenue(r1);
        assertEquals(1, Revenues.getLength());
    }

    @Test
    public void testRemoveExists() {
        Revenues.addNewRevenue(r1);
        Revenues.removeRevenue(r1);
        assertEquals(0, Revenues.getLength());

        Revenues.addNewRevenue(r1);
        Revenues.removeRevenue(r11);
        assertEquals(0, Revenues.getLength());
    }


}
