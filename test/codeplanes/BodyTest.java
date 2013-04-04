package codeplanes;

import static org.junit.Assert.*;

import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.HashSet;

public class BodyTest {

    @Test
    public void testEquals() {
        final Body b1 = new Body(1, new Point2D.Double(2,3), 3.5, 4.5);
        final Body b2 = new Body(1, new Point2D.Double(2,3), 3.5, 4.5);
        final Body b3 = new Body(2, new Point2D.Double(2,3), 3.5, 4.5);
        final Body b4 = new Body(2, new Point2D.Double(2,4), 3.5, 4.5);

        assertTrue(b1.equals(b2));
        assertFalse(b1.equals(b3));
        assertFalse(b1.equals(b4));
        assertFalse(b3.equals(b4));
    }

    @Test
    public void testGetters() {
        final Body body = new Body(1, new Point2D.Double(2,3), 4.5, 6.7);
        final Point2D position = body.getPosition();

        assertEquals(position, new Point2D.Double(2,3));
        position.setLocation(3,2);

        assertFalse(position.equals(body.getPosition()));

        assertEquals(1, body.getId());
        assertEquals(4.5, body.getAngle(), 0);
        assertEquals(6.7, body.getSpeed(), 0);
    }

    @Test
    public void testHashCode() {
        final HashSet<Body> hashSet = new HashSet<>();
        hashSet.add(new Body(0, new Point2D.Double(1, 1), 2, 3));
        assertTrue(hashSet.contains(new Body(0, new Point2D.Double(1,1), 2, 3)));
    }


}
