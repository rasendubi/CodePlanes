package codeplanes;

import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashSet;

import static org.junit.Assert.*;

public class BulletTest {
    final private Bullet bullet1 = new Bullet(0, new Point2D.Double(1,1), 3.1, 3.3, 0);
    final private Bullet bullet2 = new Bullet(0, new Point2D.Double(1,1), 3.1, 3.3, 0);
    final private Bullet bullet3 = new Bullet(3, new Point2D.Double(3,3), 2.2, 9.9, 4);

    @Test
    public void testEquals() {
        assertEquals(bullet1, bullet2);
        assertFalse(bullet1.equals(bullet3));
    }

    @Test
    public void testHashCode() {
        assertEquals(bullet1.hashCode(), bullet2.hashCode());

        HashSet<Bullet> hashSet = new HashSet<>();
        hashSet.add(bullet1);
        assertTrue(hashSet.contains(bullet1));
        assertFalse(hashSet.contains(bullet3));
    }

    @Test
    public void testGetters() {
        assertEquals(0, Double.compare(bullet1.getAngle(), 3.1));
        assertEquals(bullet1.getPlayerId(), 0);
        assertEquals(bullet3.getPlayerId(), 4);
        assertEquals(0, Double.compare(bullet1.getSpeed(), 3.3));
    }
}
