package codeplanes;

import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimulationTest {

    @Test
    public void testMove() {

        final List<Bullet> bullets = new ArrayList<>();
        bullets.add(new Bullet(1, new Point2D.Double(20, 100), -Math.PI/6, 2, 3));
        final World world = new World(0, bullets, new ArrayList<Plane>());
        final Simulation simulation = new Simulation(world, 800, 600, 4);

        final BattleCollector collector = new BattleCollector();
        simulation.addHandler(collector);

        simulation.run();

        final BattleCollector expected = new BattleCollector();
        expected.onStart(800, 600);
        for (int i = 0; i <= 4; ++i) {
            expected.onTurn(new World(
                    i,
                    new ArrayList<>(Arrays.asList(new Bullet(
                            1,
                            new Point2D.Double(20  + i * 2 * Math.cos(-Math.PI/6),
                                               100 + i * 2 * Math.sin(-Math.PI/6)),
                            -Math.PI/6,
                            2,
                            3
                    ))),
                    new ArrayList<Plane>()
            ));
        }

        assertEquals(expected, collector);
    }
}
