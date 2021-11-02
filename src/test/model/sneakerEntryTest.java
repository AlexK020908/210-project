package model;

import model.SneakerEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class sneakerEntryTest {

    private SneakerEntry s2;
    private SneakerEntry s3;

    @BeforeEach
    public void setUp() {

        s2 = new SneakerEntry("Nike Dunk Low", 120, 3);
        s3 = null;
    }

    @Test
    public void testNull() {
        assertFalse(s2.equals(s3));
    }

    @Test
    public void testHashCode() {
        assertEquals(-1277554732, s2.hashCode());
    }

}
