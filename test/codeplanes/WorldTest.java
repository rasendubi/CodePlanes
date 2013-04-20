package codeplanes;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WorldTest {

    @Test
    public void testEquals() {
        final World w1 = new World(1, new ArrayList<Bullet>(), new ArrayList<Plane>());
        final World w2 = new World(1, new ArrayList<Bullet>(), new ArrayList<Plane>());
        final World w3 = new World(2, new ArrayList<Bullet>(), new ArrayList<Plane>());

        assertFalse(w1.equals(null));
        assertTrue(w1.equals(w2));
        assertFalse(w1.equals(w3));
        assertFalse(w1.equals("hello"));

        final HashSet<World> worldSet = new HashSet<>();
        worldSet.add(w1);
        assertTrue(worldSet.contains(w1));
        assertTrue(worldSet.contains(w2));
        assertFalse(worldSet.contains(w3));
    }

}
