package codeplanes;

import java.util.LinkedList;
import java.util.List;

public abstract class Battle {
    private List<Handler> handlers = new LinkedList<Handler>();


    public abstract void start();

    final public boolean addHandler(Handler handler) {
        return handlers.add(handler);
    }

    final protected void turnEnd(World world) {
        for (Handler handler : handlers) {
            handler.turn(world);
        }
    }

    public interface Handler {
        void turn(World world);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Battle battle = (Battle) o;

        if (handlers != null ? !handlers.equals(battle.handlers) : battle.handlers != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return handlers != null ? handlers.hashCode() : 0;
    }
}
