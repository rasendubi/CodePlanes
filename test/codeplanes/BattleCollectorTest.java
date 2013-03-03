package codeplanes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;

import org.hamcrest.internal.ArrayIterator;
import org.junit.Test;

public class BattleCollectorTest {

    class TestBattle extends Battle {
        World worlds[] = {new World(1), new World(2), new World(3)};

        @Override
        public void start() {
            for (World world : worlds) {
                turnEnd(world);
            }
        }
    }

    @Test
    public void testCollector() {
        BattleCollector collector = new BattleCollector();

        TestBattle battle = new TestBattle();
        battle.addHandler(collector);

        battle.start();

        Iterator collectorIter = collector.iterator();
        for (World battleWorld : battle.worlds) {
            assertTrue(collectorIter.hasNext());
            assertEquals(battleWorld, collectorIter.next());
        }
        assertFalse(collectorIter.hasNext());
    }

    @Test
    public void testTransfer() {
        BattleCollector sender = new BattleCollector();
        sender.turn(new World(1));
        sender.turn(new World(3));
        sender.turn(new World(2));

        BattleCollector receiver = new BattleCollector();
        sender.addHandler(receiver);

        sender.start();

        assertTrue(sender.equals(receiver));
        Iterator<World> senderIter = sender.iterator();
        for (World w1 : receiver) {
            assertTrue(senderIter.hasNext());
            assertEquals(senderIter.next(), w1);
        }
        assertFalse(senderIter.hasNext());
    }
}
