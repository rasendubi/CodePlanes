package codeplanes;

import java.awt.Point;

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
    final private Point position;

    public Body(final int id, final Point position) {
        this.id = id;
        this.position = new Point(position);
    }

    public final int getId() {
        return id;
    }

    public final Point getPosition() {
        return new Point(position);
    }

    @Override
    public int hashCode() {
        int result = id;
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
               this.position.equals(that.position);
    }
}
