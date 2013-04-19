package codeplanes;

import java.awt.geom.Point2D;

/**
 * The common class for bodies in the battle simulation.
 */
public abstract class Body {

    /**
     * Identifier of the object.
     */
    final private int id;
    /**
     * Coordinates of object in the world.
     */
    final private Point2D position;

    final private double angle;

    final private double speed;

    public Body(final int id, final Point2D position, final double  angle, final double speed) {
        this.id = id;
        this.position = new Point2D.Double(position.getX(), position.getY());
        this.angle = angle;
        this.speed = speed;
    }

    public final int getId() {
        return id;
    }

    public final Point2D getPosition() {
        return new Point2D.Double(position.getX(), position.getY());
    }

    /**
     *
     * @return Clockwise angle in radians between vector of bullet direction and x-axis
     */
    public final double getAngle() {
        return angle;
    }

    /**
     *
     * @return speed of bullet
     */
    public final double getSpeed() {
        return speed;
    }

}
