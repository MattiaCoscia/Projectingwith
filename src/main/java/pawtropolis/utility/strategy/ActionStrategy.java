package pawtropolis.utility.strategy;

import pawtropolis.model.entity.Player;
import pawtropolis.model.map.GameMap;

import java.util.List;

public interface ActionStrategy {
    public ActionEnum execute(String action);
}
