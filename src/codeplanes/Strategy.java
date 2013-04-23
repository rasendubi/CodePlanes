package codeplanes;

public interface Strategy {
    public void turn(Plane plane, World world, Move move);
}
