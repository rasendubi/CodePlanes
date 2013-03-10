package codeplanes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BattleTest {

    class TestBattle extends Battle {
        World w1 = new World(1);
        World w2 = new World(2);

        @Override
        protected void run() {
            turn(w1);
            turn(w2);
        }
    }

    class TestHandler implements Battle.Handler {
        int count = 0;
        boolean startCalled = false;

        @Override
        public void onStart() {
            count = 0;
            worlds.clear();
            startCalled = true;
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

        battle.start();

        assertTrue(h1.startCalled);
        assertTrue(h2.startCalled);

        assertEquals("Handler1 count", 2, h1.count);
        assertEquals("Handler2 count", 2, h2.count);

        assertEquals("Handler1 1st world", battle.w1, h1.worlds.get(0));
        assertEquals("Handler2 1st world", battle.w1, h2.worlds.get(0));

        assertEquals("Handler1 2nd world", battle.w2, h1.worlds.get(1));
        assertEquals("Handler2 2nd world", battle.w2, h2.worlds.get(1));
    }

}
