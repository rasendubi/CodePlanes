package codeplanes;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Class which draws battle course.
 */
public class BattleDrawer extends JPanel implements Battle.Handler {
    World world = new World(0);
    private int delay = 10;

    void setDelay(final int delay) {
        this.delay = delay;
    }

    @Override
    public void onStart() {
        world = new World(0);
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
    }
}
