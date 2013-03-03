package codeplanes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BattleCollector extends Battle
        implements Battle.Handler, Iterable<World> {

    private List<World> worlds = new ArrayList<>();

    @Override
    final public void turn(World world) {
        worlds.add(world);
    }

    @Override
    public void start() {
        for (World world : worlds) {
            turnEnd(world);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BattleCollector that = (BattleCollector) o;

        if (worlds != null ? !worlds.equals(that.worlds) : that.worlds != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (worlds != null ? worlds.hashCode() : 0);
        return result;
    }

    @Override
    public Iterator<World> iterator() {
        return new BattleCollectorIterator();
    }

    class BattleCollectorIterator implements Iterator<World> {
        int index = 0;
        @Override
        public boolean hasNext() {
            return worlds.size() > index;
        }

        @Override
        public World next() {
            if (hasNext())
                return worlds.get(index++);
            else
                throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
