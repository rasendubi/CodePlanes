package codeplanes.bots;

import codeplanes.*;

import java.awt.geom.Point2D;

public class Follower implements Strategy {
    public void turn(Plane me, World world, Move move) {
        Plane enemy = null;
        for (Plane plane : world.getPlanes()) {
            if (plane.getId() != me.getId()) {
                enemy = plane;
            }
        }

        Point2D myPos = me.getPosition();
        if (enemy != null) {
            Point2D enemyPos = enemy.getPosition();
            double turnAngle = Math.atan2(myPos.getX() - enemyPos.getX(), myPos.getY() - enemyPos.getY()) + Math.PI/2 - me.getAngle();
            while (turnAngle < -Math.PI) {
                turnAngle += 2*Math.PI;
            }
            System.out.println(turnAngle);
            move.setAngle(turnAngle);
            if (turnAngle < Math.PI/600 * myPos.distance(enemyPos)) {
                move.setFire(true);
            }
        }
    }
}
