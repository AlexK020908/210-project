package model;

import model.SneakerEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class sneakerEntryTest {

    private SneakerEntry s1;
    private SneakerEntry s2;
    private SneakerEntry s3;
    private SneakerEntry s4;
    private SneakerEntry s5;
    private SneakerEntry s6;
    private CookGroupSubscriptionEntry c1;

    @BeforeEach
    public void setUp() {
        s1 = new SneakerEntry("Nike Dunk Low", 120, 5);
        s2 = new SneakerEntry("Nike Dunk Low", 120, 3);
        s3 = null;
        c1 = new CookGroupSubscriptionEntry("Name", 12);
        s4 = new SneakerEntry("Nike DunkLow", 120, 5);
        s5 = new SneakerEntry("Nike Dunk Low", 20, 5);
        s6 = new SneakerEntry("yeezy", 20, 5);
    }

    @Test
    public void testNull() {
        assertFalse(s2.equals(s3));
    }

    @Test
    public void testNotSameClass() {
        assertFalse(s2.equals(c1));
    }


    @Test
    public void testHashCode() {
        assertEquals(-1277554732, s2.hashCode());
    }

    @Test
    public void testSameSneakerEntry() {
        assertTrue(s2.equals(s1));
    }

    @Test
    public void testSameNameDifferentPrice() {
        assertFalse(s2.equals(s5));
    }

    @Test
    public void testSamePriceDifferentName() {
        assertFalse(s2.equals(s4));
    }

    @Test
    public void testDifferentNameDifferentPrice() {
        assertFalse(s1.equals(6));

    }
}
