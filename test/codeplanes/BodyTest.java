package codeplanes;

import static org.junit.Assert.*;

import org.junit.Test;

import java.awt.*;
import java.util.HashSet;

public class BodyTest {

    @Test
    public void testEquals() {
        final Body b1 = new Body(1, new Point(2,3));
        final Body b2 = new Body(1, new Point(2,3));
        final Body b3 = new Body(2, new Point(2,3));
        final Body b4 = new Body(2, new Point(2,4));

        assertTrue(b1.equals(b2));
        assertFalse(b1.equals(b3));
        assertFalse(b1.equals(b4));
        assertFalse(b3.equals(b4));
    }

    @Test
    public void testHashCode() {
        final HashSet<Body> hashSet = new HashSet<>();
        hashSet.add(new Body(0, new Point(1, 1)));
        assertTrue(hashSet.contains(new Body(0, new Point(1,1))));
    }


}
