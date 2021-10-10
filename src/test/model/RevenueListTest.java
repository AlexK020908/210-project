package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RevenueListTest {
    RevenueList Revenues;
    private Revenue r1 = new Revenue(20.00);
    private Revenue r2 = new Revenue(120.00);
    private Revenue r3 = new Revenue(21.00);
    private Revenue r4 = new Revenue(10.00);
    private Revenue r5 = new Revenue(23.00);

     //INITIALIZING
    @BeforeEach
    public void setUp(){
        Revenues = new RevenueList();
    }

    /*
    @Test
    public void testGetRevenues(){
        //no revenues in the list
        assertEquals(Revenues, Revenues.getRevenues());
        //add one revenue
        Revenues.addNewRevenue(r1);

        //add many revenues
        Revenues.addNewRevenue(r2);
        Revenues.addNewRevenue(r3);
        Revenues.addNewRevenue(r4);
        Revenues.addNewRevenue(r5);


    }

     */



}
