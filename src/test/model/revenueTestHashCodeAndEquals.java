package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class revenueTestHashCodeAndEquals {
    private Revenue r1;
    private Revenue r2;
    private Revenue r3;

    @BeforeEach
    public void setUp() {
        r1 = new Revenue(20);
        r2 = new Revenue(20);
        r3 = new Revenue(30);
    }

    @Test
    public void testSameObjectEquals() {
        assertTrue(r1.equals(r1));
    }

    @Test
    public void testObjectNullSameClass() {
        assertFalse(r1.equals(null));
    }

    @Test
    public void testObjectNullNotSameClass() {
        SneakerEntry s = null;
        assertFalse(r1.equals(s));
    }

    @Test
    public void testObjectNotNullNotSameClass() {
        SneakerEntry s = new SneakerEntry("nike dunk low", 12, 3);
        assertFalse(r1.equals(s));
    }

    @Test
    public void testEqual() {
        assertTrue(r1.equals(r2));
    }

    @Test
    public void testNotEqual() {
        assertFalse(r1.equals(r3));

    }

    @Test
    public void testHashCode() {
        assertEquals(1077149727, r1.hashCode());
    }

}
