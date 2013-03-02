package codeplanes;

import java.util.ArrayList;
import java.util.List;

public class BattleCollector implements Battle.Handler {

    private List<World> worlds = new ArrayList<>();

    final public List<World> getWorlds() {
        return worlds;
    }

    final public void turn(World world) {
        worlds.add(world);
    }

}
