package codeplanes;

public class World {

    private final int tick;

    World(final int tick) {
        this.tick = tick;
    }

    final public int getTick() {
        return tick;
    }

    @Override
    public int hashCode() {
        return tick;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof World)) {
            return false;
        } else {
            final World that = (World)obj;
            return this.tick == that.tick;
        }

    }

}
