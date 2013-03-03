package codeplanes;

import java.util.ArrayList;
import java.util.List;

public class BattleCollector extends Battle implements Battle.Handler {

    private List<World> worlds = new ArrayList<>();

    final public List<World> getWorlds() {
        return worlds;
    }

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

}
