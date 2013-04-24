package codeplanes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents state of world on every turn.
 */
public class World {

    /**
     * Number of battle tick.
     */
    private final int tick;

    private final List<Bullet> bullets;
    private final List<Plane> planes;

    /**
     * Main constructor.
     * @param tick Number of battle tick.
     */
    @JsonCreator
    World(@JsonProperty("tick") final int tick,
          @JsonProperty("bullets") final List<Bullet> bullets,
          @JsonProperty("planes") final List<Plane> planes) {
        this.tick = tick;
        this.bullets = new ArrayList<>(bullets);
        this.planes = new ArrayList<>(planes);
    }

    /**
     * Return tick.
     * @return Number of battle tick.
     */
    final public int getTick() {
        return tick;
    }

    final public List<Bullet> getBullets() {
        return new ArrayList<>(bullets);
    }

    final public List<Plane> getPlanes() {
        return new ArrayList<>(planes);
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
