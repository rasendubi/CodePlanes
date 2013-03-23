package codeplanes;

import java.awt.Point;

/**
 * Represents bullet shot by player
 */
public class Bullet extends Body {
    /**
     * Bullet constructor
     * @param id unique identifier of the bullet
     * @param position Cartesian position on the bullet
     * @param angle Clockwise angle in radians between vector of bullet direction and x-axis
     */
    public Bullet(final int id, final Point position, final double angle, final double speed, final int playerId) {
        super(id, position);
        this.angle = angle;
        this.speed = speed;
        this.playerId = playerId;
    }

    /**
     *
     * @return Clockwise angle in radians between vector of bullet direction and x-axis
     */
    public double getAngle() {
        return angle;
    }

    /**
     *
     * @return speed of bullet
     */
    public double getSpeed() {
        return speed;
    }

    /**
     *
     * @return Id of player who shot the bullet
     */
    public int getPlayerId() {
        return playerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Bullet bullet = (Bullet) o;

        if (Double.compare(bullet.angle, angle) != 0) return false;
        if (playerId != bullet.playerId) return false;
        if (Double.compare(bullet.speed, speed) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = angle != +0.0d ? Double.doubleToLongBits(angle) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = speed != +0.0d ? Double.doubleToLongBits(speed) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + playerId;
        return result;
    }

    private final double angle;
    private final double speed;
    private final int playerId;
}
