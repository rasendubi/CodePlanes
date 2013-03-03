package codeplanes;

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

class Point {
    final private int x;
    final private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point copy) {
        this.x = copy.x;
        this.y = copy.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }

        Point that = (Point) obj;
        return this.x == that.x &&
               this.y == that.y;
    }
}
