package codeplanes;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class BattleCollectorTest {

    class TestBattle extends Battle {
        World w1 = new World(1);
        World w2 = new World(2);
        World w3 = new World(3);

        @Override
        public void start() {
            turnEnd(w1);
            turnEnd(w2);
            turnEnd(w3);
        }
    }

    @Test
    public void testCollector() {
        BattleCollector collector = new BattleCollector();

        TestBattle battle = new TestBattle();
        battle.addHandler(collector);

        battle.start();

        assertEquals(battle.w1, collector.getWorlds().get(0));
        assertEquals(battle.w2, collector.getWorlds().get(1));
        assertEquals(battle.w3, collector.getWorlds().get(2));
    }

    @Test
    public void testTransfer() {
        BattleCollector sender = new BattleCollector();
        sender.getWorlds().addAll(Arrays.asList(new World(1), new World(3), new World(2)));

        BattleCollector receiver = new BattleCollector();
        sender.addHandler(receiver);

        sender.start();

        assertEquals(sender.getWorlds(), receiver.getWorlds());
    }
}
