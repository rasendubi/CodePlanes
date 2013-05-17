package codeplanes.bots;

import codeplanes.Move;
import codeplanes.Plane;
import codeplanes.Strategy;
import codeplanes.World;

public class RandomStrategy implements Strategy {
    @Override
    public void turn(final Plane plane, final World world, final Move move) {
        //move.setAngle(100500);
        move.setAngle(Math.random() - Math.random());
        move.setFire(true);
    }
}