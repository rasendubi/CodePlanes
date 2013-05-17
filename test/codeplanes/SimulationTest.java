package codeplanes;

import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SimulationTest {

    class TestStrategy implements Strategy {
        @Override
        public void turn(Plane plane, World world, Move move) {

        }
    }

    @Test
    public void testMove() {
        List<Strategy> strategies = new ArrayList<>();
        strategies.add( new TestStrategy() );
        strategies.add( new TestStrategy() );

        final Simulation simulation = new Simulation(strategies, 800, 600, 4);

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
