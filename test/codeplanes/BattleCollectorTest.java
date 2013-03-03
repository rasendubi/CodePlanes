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

        Iterator<World> collectorIter = collector.iterator();
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

    @Test
    public void testIterator() {
        BattleCollector collector = new BattleCollector();
        Iterator<World> iter = collector.iterator();

        assertFalse(collector.iterator().hasNext());
        try {
            collector.iterator().next();
            fail("Next success");
        } catch (NoSuchElementException e) {

        }

        collector.turn(new World(1));
        for (World world : collector)
            world = new World(3);

        iter = collector.iterator();
        assertTrue(iter.hasNext());
        assertEquals(iter.next(), new World(1));

        try {
            iter.remove();
            fail("Deletion success");
        } catch (UnsupportedOperationException e) {

        }
    }
}
