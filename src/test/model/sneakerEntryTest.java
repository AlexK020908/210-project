package model;

import com.sun.org.apache.xerces.internal.impl.dv.dtd.NMTOKENDatatypeValidator;
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
        try {
            s1 = new SneakerEntry("Nike Dunk Low", 120, 5);
            s2 = new SneakerEntry("Nike Dunk Low", 120, 3);
            s3 = null;
            c1 = new CookGroupSubscriptionEntry("Name", 12);
            s4 = new SneakerEntry("Nike DunkLow", 120, 5);
            s5 = new SneakerEntry("Nike Dunk Low", 20, 5);
            s6 = new SneakerEntry("yeezy", 20, 5);
        } catch (NameException e) {
            fail();
        } catch (AmountException e) {
            fail();
        } catch (QuantityException e) {
            fail();
        }
    }

    @Test
    public void testNull() {
        assertFalse(s2.equals(s3));
    }

    @Test
    public void testNullInvalidSneakerName() {
        try {
            SneakerEntry s1 = new SneakerEntry("", 120, 10);
            SneakerEntry s2 = null;
            assertFalse(s1.equals(s2));
            fail();
        } catch (NameException e) {
            System.out.println("name exception is caught");
        } catch (AmountException e) {
            fail();
        } catch (QuantityException e) {
            fail();
        }
    }

    @Test
    public void testNullInvalidSneakerPrice() {
        try {
            SneakerEntry s1 = new SneakerEntry("sneaker", -120,  10);
            SneakerEntry s2 = null;
            assertFalse(s1.equals(s2));
            fail();
        } catch (NameException e) {
            fail();
        } catch (AmountException e) {
            System.out.println("invalid price");
        } catch (QuantityException e) {
            fail();
        }
    }


    @Test
    public void testNullInvalidSneakerQuantity() {
        try {
            SneakerEntry s1 = new SneakerEntry("sneaker", 120,  -10);
            SneakerEntry s2 = null;
            assertFalse(s1.equals(s2));
            fail();
        } catch (NameException e) {
            fail();
        } catch (AmountException e) {
           fail();
        } catch (QuantityException e) {
            System.out.println("invalid quantity");
        }

    }

    @Test
    public void testNullInvalidAll() {
        try {
            SneakerEntry s1 = new SneakerEntry("", -120,  -10);
            SneakerEntry s2 = null;
            assertFalse(s1.equals(s2));
            fail();
        } catch (NameException e) {
            System.out.println("invalid name");
        } catch (AmountException e) {
           fail();
        } catch (QuantityException e) {
            fail();
        }
    }

    @Test
    public void testNotSameClassInvalidSneakerName() {
        try {
            SneakerEntry s1 = new SneakerEntry("", -120,  -10);
            Revenue r1 = new Revenue(20);
            assertFalse(s1.equals(r1));
            fail();
        } catch (NameException e) {
            System.out.println("invalid name");
        } catch (AmountException e) {
            fail();
        } catch (QuantityException e) {
            fail();
        }
    }

    @Test
    public void testNotSameClassInvalidPrice() {
        try {
            SneakerEntry s1 = new SneakerEntry("sneaker", -120,  10);
            Revenue r1 = new Revenue(20);
            assertFalse(s1.equals(r1));
            fail();
        } catch (NameException e) {
            fail();
        } catch (AmountException e) {
            System.out.println("invalid price");
        } catch (QuantityException e) {
            fail();
        }
    }


    @Test
    public void testHashCode() {
        assertEquals(-1277554732, s2.hashCode());
    }

    @Test
    public void testHashCodeInvalidSneaker() {
        try {
            SneakerEntry s1 = new SneakerEntry("", 120, 20);
            assertEquals(10000, s1.hashCode());
            fail();
        } catch (NameException e) {
            //good
        } catch (AmountException e) {
            fail();
        } catch (QuantityException e) {
            fail();
        }
    }

    @Test
    public void testSameSneakerEntry() {
        assertTrue(s2.equals(s1));
    }

    @Test
    public void testSameSneakerInvalidName() {
        try {
            SneakerEntry s1 = new SneakerEntry("", 120, 20);
            SneakerEntry s2 = new SneakerEntry("", 120, 20);
            assertTrue(s1.equals(s2));
            fail();
        } catch (NameException e) {
            //good
        } catch (AmountException e) {
            fail();
        } catch (QuantityException e) {
            fail();
        }

    }

    @Test
    public void testSameSneakerInvalidPrice() {
        try {
            SneakerEntry s1 = new SneakerEntry("sneaker", 0, 20);
            SneakerEntry s2 = new SneakerEntry("sneaker", 0, 20);
            assertTrue(s1.equals(s2));
            fail();
        } catch (NameException e) {
            fail();
        } catch (AmountException e) {
            //good
        } catch (QuantityException e) {
            fail();
        }
    }

    @Test
    public void testSameSneakerInvalidQuantity() {
        try {
            SneakerEntry s1 = new SneakerEntry("sneaker", 12, 0);
            SneakerEntry s2 = new SneakerEntry("sneaker", 12, 0);
            assertTrue(s1.equals(s2));
            fail();
        } catch (NameException e) {
            fail();
        } catch (AmountException e) {
            fail();
        } catch (QuantityException e) {
            //good
        }
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
