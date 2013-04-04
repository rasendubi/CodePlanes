package codeplanes;

import java.awt.geom.Point2D;

/**
 * The common class for bodies in the battle simulation.
 */
public class Body {

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

    @Override
    public int hashCode() {
        int result = id;
        long temp;
        temp = angle != +0.0d ? Double.doubleToLongBits(angle) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = speed != +0.0d ? Double.doubleToLongBits(speed) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + position.hashCode();
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Body)) {
            return false;
        }

        final Body that = (Body) obj;
        return this.id == that.id &&
               Double.compare(this.speed, that.speed) == 0 &&
               Double.compare(this.angle, that.angle) == 0 &&
               this.position.equals(that.position);
    }
}
