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

    public final int getAngleInDegrees() {
        return (int)(180 * angle / Math.PI) % 360;
    }

    /**
     *
     * @return speed of bullet
     */
    public final double getSpeed() {
        return speed;
    }

    /**
     *
     * @param angle new angle of the plane
     * @return Body with changed angle
     */
    abstract public Body setAngle(double angle);

    /**
     *
     * @return new body moved forward (relatively to the current angle)
     */
    abstract public Body moveForward();

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Body)) {
            return false;
        }

        final Body body = (Body) o;

        if (Double.compare(body.angle, angle) != 0) {
            return false;
        }
        if (id != body.id) {
            return false;
        }
        if (Double.compare(body.speed, speed) != 0) {
            return false;
        }
        if (!position.equals(body.position)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + position.hashCode();
        temp = Double.doubleToLongBits(angle);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(speed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
