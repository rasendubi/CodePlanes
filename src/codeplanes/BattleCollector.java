package codeplanes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Collects states of every battle turn and reproduces them in same sequence.
 */
public class BattleCollector extends Battle
        implements Battle.Handler, Iterable<World> {

    /**
     * List of all worlds' states.
     */
    @JsonProperty("worlds")
    final private List<World> worlds;
    @JsonProperty("height")
    private double height;
    @JsonProperty("width")
    private double width;

    public BattleCollector() {
        this.worlds = new ArrayList<>();
    }

    /**
     * Create battle from sequence of worlds' states.
     * @param worlds List of worlds' states.
     */
    public BattleCollector(final List<World> worlds) {
        this.worlds = new ArrayList<>(worlds);
    }

    @Override
    final public void onStart(final double width, final double height) {
        worlds.clear();
        this.width = width;
        this.height = height;
    }

    @Override
    final public void onTurn(final World world) {
        worlds.add(world);
    }

    @Override
    public void run() {
        start(width, height);
        for (final World world : worlds) {
            turn(world);
        }
    }

    /**
     * Write battle record in the file.
     * @param file File to write battle in.
     * @throws IOException
     */
    public void serialize(final File file) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(file, this);
    }

    /**
     * Read battle record from file.
     * @param file The file to read battle from.
     * @return Battle record read.
     * @throws IOException
     */
    public static BattleCollector deserialize(final File file) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(file, BattleCollector.class);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final BattleCollector that = (BattleCollector) o;

        return worlds.equals(that.worlds) &&
               this.width  == that.width  &&
               this.height == that.height;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + worlds.hashCode();
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
            if (hasNext()) {
                return worlds.get(index++);
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
