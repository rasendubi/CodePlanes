package codeplanes;

import java.util.LinkedList;
import java.util.List;

/**
 * The main class for various types of battle reproduction.
 */
public abstract class Battle {
    /**
     * List of battle handlers.
     */
    final private List<Handler> handlers = new LinkedList<>();

    /**
     * Run the battle reproduction.
     */
    protected abstract void run();

    /**
     * Notify all handlers about battle is started.
     */
    final protected void start(final double width, final double height) {
        for (final Handler handler : handlers) {
            handler.onStart(width, height);
        }
    }

    /**
     * Add handler to list of handlers.
     * @param handler Handler to add.
     */
    final public void addHandler(final Handler handler) {
        handlers.add(handler);
    }

    /**
     * Notify all handlers about end of turn.
     * Must be called from derived classes on end of each turn.
     * @param world State of the world on the end of turn.
     */
    final protected void turn(final World world) {
        for (final Handler handler : handlers) {
            handler.onTurn(world);
        }
    }

    /**
     * Interface to handle course of the battle.
     */
    public interface Handler {
        /**
         * Called on start of the battle.
         */
        void onStart(final double width, final double height);
        /**
         * Called on every turn end.
         * @param world State of the world after turn.
         */
        void onTurn(World world);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Battle battle = (Battle) o;

        return handlers.equals(battle.handlers);
    }

    @Override
    public int hashCode() {
        return handlers.hashCode();
    }
}
