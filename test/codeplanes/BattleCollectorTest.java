package codeplanes;

import static org.junit.Assert.*;

import org.junit.Test;

public class BattleCollectorTest {
    
    class TestBattle extends Battle {
        World w1 = new World(1);
        World w2 = new World(2);
        World w3 = new World(3);
        
        @Override
        public void start() {
            turn(w1);
            turn(w2);
            turn(w3);
        }
    }
    
    @Test
    public void testCollector() {
        BattleCollector collector = new BattleCollector();
        
        TestBattle battle = new TestBattle();
        battle.addHandler(collector);
        
        battle.start();
        
        assertEquals(battle.w1, collector.worlds.get(0));
        assertEquals(battle.w2, collector.worlds.get(1));
        assertEquals(battle.w3, collector.worlds.get(2));
    }
}
