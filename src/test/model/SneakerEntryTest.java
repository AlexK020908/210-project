package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SneakerEntryTest {

    SneakerEntry E1;

    @BeforeEach
    public void setUp() {
        E1 = new SneakerEntry("Nike Dunk low", 120, 5 );
    }

    @Test
    public void testGetQuantityBought(){
        assertEquals(5, E1.getQuantityBought());
    }

    @Test
    public void testIncreaseQuantityBought(){
        //base case
        E1.increaseQuantityBought(0);
        assertEquals(5, E1.getQuantityBought());
        //increase by a small amount
        E1.increaseQuantityBought(2);
        assertEquals(5 + 2, E1.getQuantityBought());
        //increase by a large amount
        E1.increaseQuantityBought(10);
        assertEquals(5 + 2 + 10, E1.getQuantityBought());

    }

    @Test
    public void testDecreaseQuantityBought(){
        //base case
        E1.decreaseQuantityBought(0);
        assertEquals(5, E1.getQuantityBought());
        //Decrease by a small amount
        E1.decreaseQuantityBought(2);
        assertEquals(5 - 2, E1.getQuantityBought());
        //Decrease by a large within the range
        E1.decreaseQuantityBought(2);
        assertEquals(5 - 2 - 2, E1.getQuantityBought());

        //Decrase more than what is already there
        E1.decreaseQuantityBought(2);
        assertEquals(0, E1.getQuantityBought());

    }
}
