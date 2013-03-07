package codeplanes;

import java.awt.*;

public class Body {

    final private int id;
    final private Point position;

    public Body(final int id, final Point position) {
        this.id = id;
        this.position = new Point(position);
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
