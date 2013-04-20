package codeplanes;

import javax.swing.JFrame;
import java.awt.geom.Point2D;
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
        Bullet bullet = new Bullet(1, new Point2D.Double(100, 100), -Math.PI/4, 1, 0);
        Plane plane = new Plane(1, new Point2D.Double(50, 130), -Math.PI/3, 1, 0);
        for (int i = 0; i < 1000; ++i) {
            bullet = bullet.moveForward();
            List<Bullet> bullets = new ArrayList<>();
            bullets.add(bullet);

            List<Plane> planes = new ArrayList<>();

            plane = plane.moveForward();
            plane = plane.setAngle(plane.getAngle() + 0.003);
            planes.add(plane);

            final World world = new World(i, bullets, planes);
            battle.onTurn(world);
        }

        battleDrawerTest.setBattle(battle);

        battleDrawerTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        battleDrawerTest.setVisible(true);
        battleDrawerTest.setSize(500,500);

        battle.run();
    }
}
