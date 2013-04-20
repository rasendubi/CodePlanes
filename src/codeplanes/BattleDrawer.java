package codeplanes;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Class which draws battle course.
 */
public class BattleDrawer extends JPanel implements Battle.Handler {
    private World world;
    private int delay = 10;

    void setDelay(final int delay) {
        this.delay = delay;
    }

    @Override
    public void onStart(final double width, final double height) {
        world = new World(0, new ArrayList<Bullet>(), new ArrayList<Plane>());
        setSize((int)width, (int)height);
        repaint();
    }

    @Override
    public void onTurn(final World world) {
        this.world = world;

        // Dirty hack
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        repaint();
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);

        final char[] tick = String.valueOf(world.getTick()).toCharArray();
        final int tickX = 10, tickY = 20;
        g.drawChars(tick, 0, tick.length, tickX, tickY);

        // Draw bullets
        for (final Bullet bullet : world.getBullets()) {
            drawBullet(g, bullet);
        }

        // Draw planes
        for (final Plane plane : world.getPlanes()) {
            drawPlane(g, plane);
        }
    }

    public void drawBullet(final Graphics g, Bullet bullet) {
        g.drawRect(
                (int)bullet.getPosition().getX(),
                (int)bullet.getPosition().getY(),
                5,5
        );
    }

    public void drawPlane(final Graphics g, Plane plane) {
        final int centerX = (int) plane.getPosition().getX();
        final int centerY = (int) plane.getPosition().getY();

        // circle
        final int circleRadius = 3;
        g.drawOval(centerX - circleRadius, centerY - circleRadius, 2 * circleRadius, 2 * circleRadius);

        // line
        final int lineLength = 20;
        g.drawLine(centerX, centerY,
                (int)(centerX + lineLength * Math.cos(plane.getAngle())),
                (int)(centerY - lineLength * Math.sin(plane.getAngle())));

        // arc
        final int arcRadius = 20;
        g.drawArc(
                centerX - arcRadius,
                centerY - arcRadius,
                2 * arcRadius, 2 * arcRadius,
                plane.getAngleInDegrees() - 45,
                90
        );
    }
}
