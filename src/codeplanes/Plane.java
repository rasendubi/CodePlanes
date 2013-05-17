package codeplanes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.awt.geom.Point2D;

/**
 * Represents programmer-controllable plane
 */
public class Plane extends Body {

    /**
     * Plane constructor
     * @param id unique identifier of the plane
     * @param position Cartesian position on the plane
     * @param angle Clockwise angle in radians between vector of plane direction and x-axis
     * @param speed Plane's speed
     * @param playerId Id of player plane belongs to
     */
    @JsonCreator
    public Plane(@JsonProperty("id") final int id,
                 @JsonProperty("position") final Point2D.Double position,
                 @JsonProperty("angle") final double angle,
                 @JsonProperty("speed") final double speed,
                 @JsonProperty("playerId") final int playerId,
                 @JsonProperty("reloadTime") final int reloadTime) {
        super(id, position, angle, speed);
        this.playerId = playerId;
        this.reloadTime = reloadTime;
    }

    /**
     *
     * @return Id of player plane belongs to
     */
    public final int getPlayerId() {
        return playerId;
    }

    public final int getReloadTime() {
        return reloadTime;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        final Plane plane = (Plane) o;

        return playerId == plane.playerId;

    }

    public Plane setReloadTime(final int reloadTime) {
        return new Plane(getId(), getPosition(), getAngle(), getSpeed(), getPlayerId(), reloadTime);
    }

    @Override
    public Plane setAngle(final double angle) {
        return new Plane(getId(), getPosition(), angle, getSpeed(), getPlayerId(), getReloadTime());
    }

    @Override
    public Plane moveForward() {
        return new Plane(
                getId(),
                new Point2D.Double(
                        getPosition().getX() + getSpeed() * Math.cos(getAngle()),
                        getPosition().getY() - getSpeed() * Math.sin(getAngle())),
                getAngle(),
                getSpeed(),
                getPlayerId(),
                Math.max(0, getReloadTime() - 1)
        );
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + playerId;
        return result;
    }

    private final int playerId;
    private final int reloadTime;
}
