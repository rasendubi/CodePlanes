package codeplanes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BattleTest {

    class TestBattle extends Battle {
        World w1 = new World(1, new ArrayList<Bullet>());
        World w2 = new World(2, new ArrayList<Bullet>());

        @Override
        protected void run() {
            start(2.5, 3.6);
            turn(w1);
            turn(w2);
        }
    }

    class TestHandler implements Battle.Handler {
        int count = 0;
        boolean startCalled = false;
        double width;
        double height;

        @Override
        public void onStart(final double width, final double height) {
            count = 0;
            worlds.clear();
            startCalled = true;
            this.width = width;
            this.height = height;
        }

        @Override
        public void onTurn(final World world) {
            worlds.add(world);
            ++count;
        }

        List<World> worlds = new ArrayList<>();
    }

    @Test
    public void testBattleHandlers() {
        final TestBattle battle = new TestBattle();

        final TestHandler h1 = new TestHandler();
        final TestHandler h2 = new TestHandler();

        battle.addHandler(h1);
        battle.addHandler(h2);

        battle.run();

        assertTrue(h1.startCalled);
        assertTrue(h2.startCalled);

        assertEquals("Handler1 count", 2, h1.count);
        assertEquals("Handler2 count", 2, h2.count);

        assertEquals("Handler1 1st world", battle.w1, h1.worlds.get(0));
        assertEquals("Handler2 1st world", battle.w1, h2.worlds.get(0));

        assertEquals("Handler1 2nd world", battle.w2, h1.worlds.get(1));
        assertEquals("Handler2 2nd world", battle.w2, h2.worlds.get(1));

        assertEquals(2.5, h1.width, 0);
        assertEquals(2.5, h2.width, 0);

        assertEquals(3.6, h1.height, 0);
        assertEquals(3.6, h2.height, 0);
    }

}
