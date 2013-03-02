package codeplanes;

import java.util.ArrayList;
import java.util.List;

public class BattleCollector implements Battle.Handler {
    
    public List<World> worlds = new ArrayList<World>();

    public void turn(World world) {
        worlds.add(world);
    }
       
}
