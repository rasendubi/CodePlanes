package codeplanes;

import java.util.LinkedList;
import java.util.List;

public abstract class Battle {
    final private List<Handler> handlers = new LinkedList<>();


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

        return handlers.equals(battle.handlers);
    }

    @Override
    public int hashCode() {
        return handlers.hashCode();
    }
}
