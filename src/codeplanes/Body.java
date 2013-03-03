package codeplanes;

import java.awt.*;

public class Body {

    final private int id;
    final private Point position;

    public Body(int id, Point position) {
        this.id = id;
        this.position = new Point(position);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Body)) {
            return false;
        }

        Body that = (Body)obj;
        return this.id == that.id &&
               this.position.equals(that.position);
    }
}
