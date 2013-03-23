package codeplanes;

import java.awt.Point;

/**
 * Represents programmer-controllable plane
 */
public class Plane extends Body {
    /**
     * Plane constructor
     * @param id unique identifier of the plane
     * @param position Cartesian position on the plane
     * @param angle Clockwise angle in radians between vector of plane direction and x-axis
     * @param playerId Id of player plane belongs to
     */
    public Plane(final int id, final Point position, final double angle, int playerId) {
        super(id, position);
        this.angle = angle;
        this.playerId = playerId;
    }

    /**
     *
     * @return Clockwise angle in radians between vector of plane direction and x-axis
     */
    public double getAngle() {
        return angle;
    }

    /**
     *
     * @return Id of player plane belongs to
     */
    public int getPlayerId() {
        return playerId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Plane plane = (Plane) o;

        if (Double.compare(plane.angle, angle) != 0) return false;
        if (playerId != plane.playerId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = angle != +0.0d ? Double.doubleToLongBits(angle) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + playerId;
        return result;
    }

    private final double angle;
    private final int playerId;
}
