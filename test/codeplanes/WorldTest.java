package codeplanes;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.HashSet;

public class WorldTest {

    @Test
    public void testEquals() {
        World w1 = new World(1);
        World w2 = new World(1);
        World w3 = new World(2);

        assertFalse(w1.equals(null));
        assertTrue(w1.equals(w2));
        assertFalse(w1.equals(w3));
        assertFalse(w1.equals("hello"));

        HashSet<World> worldSet = new HashSet<>();
        worldSet.add(w1);
        assertTrue(worldSet.contains(w1));
        assertTrue(worldSet.contains(w2));
        assertFalse(worldSet.contains(w3));
    }

}
