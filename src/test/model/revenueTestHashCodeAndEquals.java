package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class revenueTestHashCodeAndEquals {
    private Revenue r1;
    private Revenue r2;
    private Revenue r3;

    @Test
    public void testSameObjectEquals() {
        try {
            r1 = new Revenue(20);
            assertTrue(r1.equals(r1));
        } catch (AmountException e) {
            fail();
        }
    }

    @Test
    public void testSameObjectInvalidRevenue() {
        try {
            r1 = new Revenue(-20);
            assertTrue(r1.equals(r1));
            fail();
        } catch (AmountException e) {
            //good
        }
    }

    @Test
    public void testObjectNullSameClass() {
        try {
            r1 = new Revenue(20);
            r2 = null;
            assertFalse(r1.equals(r2));
        } catch (AmountException e) {
            fail();
        }
    }


    @Test
    public void testObjectNullSameClassInvalidRevenue() {
        try {
            r1 = new Revenue(-20);
            r2 = null;
            assertFalse(r1.equals(r2));
            fail();
        } catch (AmountException e) {
            //good
        }
    }

    @Test
    public void testObjectNullNotSameClass() {
        try {
            r1 = new Revenue(20);
            SneakerEntry s = null;
            assertFalse(r1.equals(s));
        } catch (AmountException e) {
            fail();
        }
    }

    @Test
    public void testObjectNullNotSameClassInvalidRevenue() {
        try {
            r1 = new Revenue(-20);
            SneakerEntry s = null;
            assertFalse(r1.equals(s));
            fail();
        } catch (AmountException e) {
            //good
        }
    }

    @Test
    public void testObjectNotNullNotSameClass() {
        try {
            r1 = new Revenue(20);
            SneakerEntry s = new SneakerEntry("nike dunk low", 12, 3);
            assertFalse(r1.equals(s));
        } catch (AmountException e) {
            fail();
        }
    }

    @Test
    public void testEqual() {
        try {
            r1 = new Revenue(20);
            r2 = new Revenue(20);
            assertTrue(r1.equals(r2));
        } catch (AmountException e) {
            fail();
        }
    }


    @Test
    public void testEqualInvalidRevenue() {
        try {
            r1 = new Revenue(-20);
            r2 = new Revenue(2-0);
            assertTrue(r1.equals(r2));
            fail();
        } catch (AmountException e) {
            //good
        }
    }

    @Test
    public void testNotEqual() {
        try {
            r1 = new Revenue(20);
            r3 = new Revenue(30);
            assertFalse(r1.equals(r3));
        } catch (AmountException e) {
            fail();
        }


    }

    @Test
    public void testHashCode() {
        try {
            r1 = new Revenue(20);
            assertEquals(1077149727, r1.hashCode());
        } catch (AmountException e) {
            fail();
        }
    }

    @Test
    public void testHashCodeInvalidRevenue() {
        try {
            r1 = new Revenue(-20);
            assertEquals(1077149727, r1.hashCode());
            fail();
        } catch (AmountException e) {
            //good
        }
    }

}
