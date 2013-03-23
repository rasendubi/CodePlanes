package codeplanes;

import static org.junit.Assert.*;

import org.junit.Test;

import java.awt.Point;
import java.util.HashSet;

public class PlaneTest {
    final private Plane plane1 = new Plane(0, new Point(1,1), 3.1, 0);
    final private Plane plane2 = new Plane(0, new Point(1,1), 3.1, 0);
    final private Plane plane3 = new Plane(3, new Point(3,3), 2.2, 4);

    @Test
    public void testEquals() {
        assertEquals(plane1, plane2);
        assertFalse(plane1.equals(plane3));
    }

    @Test
    public void testHashCode() {

        assertEquals(plane1.hashCode(), plane2.hashCode());

        HashSet<Plane> hashSet = new HashSet<>();
        hashSet.add(plane1);
        assertTrue(hashSet.contains(plane1));
        assertFalse(hashSet.contains(plane3));
    }

    @Test
    public void testGetters() {
        assertEquals(0, Double.compare(plane1.getAngle(), 3.1));
        assertEquals(plane1.getPlayerId(), 0);
        assertEquals(plane3.getPlayerId(), 4);
    }
}