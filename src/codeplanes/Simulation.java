package codeplanes;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which represents new battle simulation.
 */
public class Simulation extends Battle {
    // Simulation constants
    final double initialSpeed = 2;
    final int initialReload = 5;
    final int maxReloadTime = 75;
    final double maxAngle = Math.PI/120;

    Simulation(final List<Strategy> strategies, final double width, final double height, final int maxTick) {
        this.strategies = new ArrayList<>(strategies);
        this.width = width;
        this.height = height;
        this.maxTick = maxTick;

        // Initial position for two planes
        final List<Plane> planes = new ArrayList<>();
        planes.add( new Plane(nextId(), new Point2D.Double(width/10, height/2), 0, initialSpeed, 0, initialReload) );
        planes.add( new Plane(nextId(), new Point2D.Double(width*9/10, height/2), Math.PI, initialSpeed, 1, initialReload) );

        world = new World(0, new ArrayList<Bullet>(), planes);
    }

    public void run() {
        start(width, height);

        turn(world);

        while (world.getTick() < maxTick && world.getPlanes().size() > 1) {
            final List<Bullet> bullets = new ArrayList<>();
            final List<Plane> planes = new ArrayList<>();

            for (Bullet bullet : world.getBullets()) {

                // Wall collision
                bullet = bullet.moveForward();
                final Point2D position = bullet.getPosition();
                if (position.getX() > 0 && position.getX() < width &&
                    position.getY() > 0 && position.getY() < height) {
                    bullets.add(bullet);
                }
            }

            plane:
            for (Plane plane : world.getPlanes()) {

                // Get player's move
                final Move move = new Move();
                final Strategy strategy = strategies.get(plane.getPlayerId());
                strategy.turn(plane, world, move);

                // If player fires
                if (move.getFire() && plane.getReloadTime() == 0) {
                    plane = plane.setReloadTime(maxReloadTime);
                    bullets.add( new Bullet(nextId(), plane.getPosition(), plane.getAngle(), plane.getSpeed()*2, plane.getPlayerId()) );
                }

                // Turn plane
                final double angle;
                if (move.getAngle() > 0) {
                    angle = Math.min(move.getAngle(), maxAngle);
                } else {
                    angle = Math.max(move.getAngle(), -maxAngle);
                }
                plane = plane.setAngle(plane.getAngle() + angle);

                // Wall collision
                plane = plane.moveForward();
                final Point2D position = plane.getPosition();
                if (position.getX() > 0 && position.getX() < width &&
                    position.getY() > 0 && position.getY() < height) {
                } else {
                    break plane;
                }

                // Plane-bullet collision
                for (final Bullet bullet : bullets) {
                    Point2D p1 = bullet.getPosition();
                    Point2D p2 = plane.getPosition();
                    if (p1.distance(p2) < 5 && bullet.getPlayerId() != plane.getPlayerId()) {
                        break plane;
                    }
                }

                planes.add(plane);
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
