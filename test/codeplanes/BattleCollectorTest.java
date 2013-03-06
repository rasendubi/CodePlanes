package codeplanes;

import static org.junit.Assert.*;

import java.lang.UnsupportedOperationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

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

        Iterator<World> collectorIt= collector.iterator();
        for (World battleWorld : battle.worlds) {
            assertTrue(collectorIt.hasNext());
            assertEquals(battleWorld, collectorIt.next());
        }
        assertFalse(collectorIt.hasNext());
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
        Iterator<World> senderIt = sender.iterator();
        for (World w1 : receiver) {
            assertTrue(senderIt.hasNext());
            assertEquals(senderIt.next(), w1);
        }
        assertFalse(senderIt.hasNext());
    }

    @Test
    public void testIterator() {
        BattleCollector collector = new BattleCollector();

        assertFalse(collector.iterator().hasNext());
        try {
            collector.iterator().next();
            fail("Next success");
        } catch (NoSuchElementException e) {

        }

        collector.turn(new World(1));

        Iterator<World> it = collector.iterator();
        assertTrue(it.hasNext());
        assertEquals(it.next(), new World(1));

        try {
            it.remove();
            fail("Deletion success");
        } catch (UnsupportedOperationException e) {

        }
    }
}
