package codeplanes;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which represents new battle simulation.
 */
public class Simulation extends Battle {
    Simulation(final World world, final double width, final double height, final int maxTick) {
        this.world = world;
        this.width = width;
        this.height = height;
        this.maxTick = maxTick;
    }

    public void run() {
        start(width, height);

        turn(world);
        while (world.getTick() < maxTick) {
            final List<Bullet> bullets = new ArrayList<>();

            for (final Bullet bullet : world.getBullets()) {
                final Point2D position = bullet.getPosition();
                bullets.add(
                        new Bullet(
                                bullet.getId(),
                                new Point2D.Double(position.getX() + bullet.getSpeed() * Math.cos(bullet.getAngle()),
                                                   position.getY() + bullet.getSpeed() * Math.sin(bullet.getAngle())),
                                bullet.getAngle(),
                                bullet.getSpeed(),
                                bullet.getPlayerId()
                        )
                );
            }

            world = new World(world.getTick() + 1, bullets);
            turn(world);
        }
    }

    private World world;
    private final double width;
    private final double height;
    private final int maxTick;
}
