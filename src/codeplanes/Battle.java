package codeplanes;

import java.util.LinkedList;
import java.util.List;

public abstract class Battle {
    private List<Handler> handlers = new LinkedList<Handler>();

    public abstract void start();

    final public boolean addHandler(Handler handler) {
        return handlers.add(handler);
    }

    final protected void turn(World world) {
        for (Handler handler : handlers) {
            handler.turn(world);
        }
    }

    public interface Handler {
        void turn(World world);
    }

}
