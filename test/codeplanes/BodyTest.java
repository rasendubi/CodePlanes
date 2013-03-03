package codeplanes;

import static org.junit.Assert.*;

import org.junit.Test;

import java.awt.*;

public class BodyTest {

    @Test
    public void testEquals() {
        Body b1 = new Body(1, new Point(2,3));
        Body b2 = new Body(1, new Point(2,3));
        Body b3 = new Body(2, new Point(2,3));
        Body b4 = new Body(2, new Point(2,4));

        assertTrue(b1.equals(b2));
        assertFalse(b1.equals(b3));
        assertFalse(b1.equals(b4));
        assertFalse(b3.equals(b4));
    }


}
