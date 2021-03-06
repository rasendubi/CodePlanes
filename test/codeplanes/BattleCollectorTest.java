package codeplanes;

import codeplanes.bots.Follower;
import codeplanes.bots.RandomStrategy;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class BattleCollectorTest {

    class TestBattle extends Battle {
        World[] worlds = {
                new World(1, new ArrayList<Bullet>(), new ArrayList<Plane>()),
                new World(2, new ArrayList<Bullet>(), new ArrayList<Plane>()),
                new World(3, new ArrayList<Bullet>(), new ArrayList<Plane>())
        };

        @Override
        protected void run() {
            start(800, 600);
            for (final World world : worlds) {
                turn(world);
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

        battle.run();

        final Iterator<World> collectorIt = collector.iterator();
        for (final World battleWorld : battle.worlds) {
            assertTrue(collectorIt.hasNext());
            assertEquals(battleWorld, collectorIt.next());
        }
        assertFalse(collectorIt.hasNext());
    }

    @Test
    public void testSequentialCall() {
        final BattleCollector collector = new BattleCollector();

        final TestBattle battle = new TestBattle();
        battle.addHandler(collector);

        battle.run();
        battle.run();

        final Iterator<World> collectorIt = collector.iterator();
        for (final World battleWorld : battle.worlds) {
            assertTrue(collectorIt.hasNext());
            assertEquals(battleWorld, collectorIt.next());
        }
        assertFalse(collectorIt.hasNext());
    }

    @Test
    public void testTransfer() {
        final BattleCollector sender = new BattleCollector();
        sender.onStart(800, 600);
        sender.onTurn(new World(1, new ArrayList<Bullet>(), new ArrayList<Plane>()));
        sender.onTurn(new World(3, new ArrayList<Bullet>(), new ArrayList<Plane>()));
        sender.onTurn(new World(2, new ArrayList<Bullet>(), new ArrayList<Plane>()));

        final BattleCollector receiver = new BattleCollector();
        sender.addHandler(receiver);

        sender.run();

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

        collector.onTurn(new World(1, new ArrayList<Bullet>(), new ArrayList<Plane>()));

        final Iterator<World> it = collector.iterator();
        assertTrue(it.hasNext());
        assertEquals(it.next(), new World(1, new ArrayList<Bullet>(), new ArrayList<Plane>()));
    }

    @Test(expected = NoSuchElementException.class)
    public void testNextInEmpty() {
        new BattleCollector().iterator().next();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemove() {
        new BattleCollector().iterator().remove();
    }

    @Test
    public void testSerialization() throws IOException {
        final File file = temp.newFile("test_battle.json");

        final TestBattle testBattle = new TestBattle();

        final BattleCollector written = new BattleCollector();

        testBattle.addHandler(written);
        testBattle.run();

        written.serialize(file);

        final BattleCollector read = BattleCollector.deserialize(file);

        assertEquals(written, read);
    }

    @Test
    public void testRandomSerialization() throws IOException {
        final File file = temp.newFile("random_test.json");

        final List<Strategy> strategies = new ArrayList<>();
        strategies.add( new RandomStrategy() );
        strategies.add( new Follower() );
        final Battle battle = new Simulation(strategies, 800, 600, 1000);

        final BattleCollector written = new BattleCollector();

        battle.addHandler(written);
        battle.run();

        written.serialize(file);

        final BattleCollector read = BattleCollector.deserialize(file);

        assertEquals(written, read);
    }
}
