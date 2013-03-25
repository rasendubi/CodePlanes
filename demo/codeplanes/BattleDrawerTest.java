package codeplanes;

import javax.swing.JFrame;

public class BattleDrawerTest extends JFrame {
    private final BattleDrawer battleDrawer = new BattleDrawer();

    BattleDrawerTest() {
        add(battleDrawer);
    }

    public void setBattle(final Battle battle) {
        battle.addHandler(battleDrawer);
    }

    public static void main(final String[] args) {
        final BattleDrawerTest battleDrawerTest = new BattleDrawerTest();

        final BattleCollector battle = new BattleCollector();
        battle.onStart();
        for (int i = 0; i < 1000; ++i) {
            final World world = new World(i);
            battle.onTurn(world);
        }

        battleDrawerTest.setBattle(battle);

        battleDrawerTest.setSize(500, 500);
        battleDrawerTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        battleDrawerTest.setVisible(true);

        battle.start();
    }
}
