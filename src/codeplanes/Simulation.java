package codeplanes;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which represents new battle simulation.
 */
public class Simulation extends Battle {

    final double initialSpeed = 2;
    final int initialReload = 5;

    Simulation(final List<Strategy> strategies, final double width, final double height, final int maxTick) {
        this.strategies = strategies;
        this.width = width;
        this.height = height;
        this.maxTick = maxTick;

        final List<Plane> planes = new ArrayList<>();
        planes.add( new Plane(nextId(), new Point2D.Double(width/10, height/2), 0, initialSpeed, 0, initialReload) );
        planes.add( new Plane(nextId(), new Point2D.Double(width*9/10, height/2), Math.PI, initialSpeed, 1, initialReload) );

        world = new World(0, new ArrayList<Bullet>(), planes);
    }

    public void run() {
        start(width, height);

        turn(world);
        while (world.getTick() < maxTick) {
            final List<Bullet> bullets = new ArrayList<>();
            final List<Plane> planes = new ArrayList<>();

            for (final Bullet bullet : world.getBullets()) {
                bullets.add(bullet.moveForward());
            }

            for (final Plane plane : world.getPlanes()) {
                Move move = new Move();
                Strategy strategy = strategies.get(plane.getPlayerId());
                strategy.turn(plane, world, move);



                planes.add(plane.moveForward());
            }

            world = new World(world.getTick() + 1, bullets, planes);
            turn(world);
        }
    }

    private int nextId() {
        return maxId++;
    }

    private final List<Strategy> strategies;
    private World world;
    private final double width;
    private final double height;
    private final int maxTick;

    private int maxId;
}
