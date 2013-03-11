package codeplanes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents state of world on every turn.
 */
public class World {

    /**
     * Number of battle tick.
     */
    private final int tick;

    /**
     * Main constructor.
     * @param tick Number of battle tick.
     */
    @JsonCreator
    World(@JsonProperty("tick") final int tick) {
        this.tick = tick;
    }

    /**
     * Return tick.
     * @return Number of battle tick.
     */
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
