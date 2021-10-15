package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

//testing revenue list
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


}
