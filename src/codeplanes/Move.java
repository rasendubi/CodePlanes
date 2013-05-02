package codeplanes;

public class Move {

    public void setFire(final boolean fire) {
        this.fire = fire;
    }

    public void setAngle(final double angle) {
        this.angle = angle;
    }

    public boolean getFire() {
        return fire;
    }

    public double getAngle() {
        return angle;
    }

    private boolean fire = false;
    private double angle = 0.0;
}
