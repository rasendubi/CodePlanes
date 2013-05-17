package codeplanes;

import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashSet;

import static org.junit.Assert.*;

public class PlaneTest {
    final private Plane plane1 = new Plane(0, new Point2D.Double(1,1), 3.1, 3.5, 0, 0);
    final private Plane plane2 = new Plane(0, new Point2D.Double(1,1), 3.1, 3.5, 0, 0);
    final private Plane plane3 = new Plane(3, new Point2D.Double(3,3), 2.2, 3.5, 4, 0);

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

}
