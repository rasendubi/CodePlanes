package codeplanes;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class BattleCollectorTest {

    class TestBattle extends Battle {
        World[] worlds = { new World(1), new World(2), new World(3) };

        @Override
        public void start() {
            for (final World world : worlds) {
                turnEnd(world);
            }
        }
    }

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    @Test
    public void testCollector() {
        final BattleCollector collector = new BattleCollector();

        final TestBattle battle = new TestBattle();
        battle.addHandler(collector);

        battle.start();

        final Iterator<World> collectorIt= collector.iterator();
        for (final World battleWorld : battle.worlds) {
            assertTrue(collectorIt.hasNext());
            assertEquals(battleWorld, collectorIt.next());
        }
        assertFalse(collectorIt.hasNext());
    }

    @Test
    public void testTransfer() {
        final BattleCollector sender = new BattleCollector();
        sender.turn(new World(1));
        sender.turn(new World(3));
        sender.turn(new World(2));

        final BattleCollector receiver = new BattleCollector();
        sender.addHandler(receiver);

        sender.start();

        assertTrue(sender.equals(receiver));
        final Iterator<World> senderIt = sender.iterator();
        for (final World w1 : receiver) {
            assertTrue(senderIt.hasNext());
            assertEquals(senderIt.next(), w1);
        }
        assertFalse(senderIt.hasNext());
    }

    @Test
    public void testIterator() {
        final BattleCollector collector = new BattleCollector();

        assertFalse(collector.iterator().hasNext());
        try {
            collector.iterator().next();
            fail("Next success");
        } catch (NoSuchElementException e) {

        }

        collector.turn(new World(1));

        final Iterator<World> it = collector.iterator();
        assertTrue(it.hasNext());
        assertEquals(it.next(), new World(1));

        try {
            it.remove();
            fail("Deletion success");
        } catch (UnsupportedOperationException e) {

        }
    }

    @Test
    public void testSerialization() throws IOException {
        final File file = temp.newFile("test_battle.json");

        final TestBattle testBattle = new TestBattle();

        final BattleCollector written = new BattleCollector();

        testBattle.addHandler(written);
        testBattle.start();

        written.serialize(file);

        final BattleCollector read = BattleCollector.deserialize(file);

        assertEquals(written, read);
    }
}
