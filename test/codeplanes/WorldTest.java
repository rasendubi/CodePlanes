package codeplanes;

import static org.junit.Assert.*;

import org.junit.Test;

public class WorldTest {

    @Test
    public void testEquals() {
        World w1 = new World(1);
        World w2 = new World(1);
        World w3 = new World(2);
        
        assertTrue(w1.equals(w2));
        assertFalse(w1.equals(w3));
        assertFalse(w1.equals("hello"));
    }

}
