package codeplanes;

import java.util.LinkedList;
import java.util.List;

public abstract class Battle {
    final private List<Handler> handlers = new LinkedList<>();

    protected abstract void run();

    final public void start() {
        for (final Handler handler : handlers) {
            handler.onStart();
        }
        run();
    }

    final public boolean addHandler(final Handler handler) {
        return handlers.add(handler);
    }

    final protected void turn(final World world) {
        for (final Handler handler : handlers) {
            handler.onTurn(world);
        }
    }

    public interface Handler {
        void onStart();
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
