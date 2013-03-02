package codeplanes;

public class World {
    public final int tick;
    
    World(int tick) {
        this.tick = tick;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof World)) {
            return false;
        } else {     
            World that = (World)obj;
            return this.tick == that.tick;
        }
    }
}
