package codeplanes;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import codeplanes.bots.Follower;
import codeplanes.bots.RandomStrategy;

public class SimulationDrawerTest extends JFrame {

    private final BattleDrawer battleDrawer = new BattleDrawer();

    SimulationDrawerTest() {
        add(battleDrawer);
    }

    public void setBattle(final Battle battle) {
        battle.addHandler(battleDrawer);
    }

    public static void main(final String[] args) {
        final SimulationDrawerTest battleDrawerTest = new SimulationDrawerTest();

        final List<Strategy> strategies = new ArrayList<>();
        strategies.add( new RandomStrategy() );
        strategies.add( new Follower() );

        final Simulation battle = new Simulation(strategies, 800, 600, 4000);

        battleDrawerTest.setBattle(battle);

        battleDrawerTest.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        battleDrawerTest.setVisible(true);
        battleDrawerTest.setSize(500,500);

        battle.run();
    }
}
