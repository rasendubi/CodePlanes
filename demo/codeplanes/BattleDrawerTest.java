package codeplanes;

import javax.swing.JFrame;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

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
        battle.onStart(500, 500);
        Bullet bullet = new Bullet(1, new Point(100, 100), -Math.PI/4, 10, 0);
        for (int i = 0; i < 1000; ++i) {
            bullet = new Bullet(
                    bullet.getId(),
                    new Point(
                            (int)(bullet.getPosition().getX() + Math.sqrt(2)*1),
                            (int)(bullet.getPosition().getY() + Math.sqrt(2)*1)
                    ),
                    bullet.getAngle(),
                    bullet.getSpeed(),
                    bullet.getPlayerId()
            );
            List<Bullet> bullets = new ArrayList<>();
            bullets.add(bullet);
            final World world = new World(i, bullets);
            battle.onTurn(world);
        }

        battleDrawerTest.setBattle(battle);

        battleDrawerTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        battleDrawerTest.setVisible(true);
        battleDrawerTest.setSize(500,500);

        battle.run();
    }
}
